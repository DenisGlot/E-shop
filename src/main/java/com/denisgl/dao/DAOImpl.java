package com.denisgl.dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.denisgl.dao.entities.annotations.MyColumn;
import com.denisgl.dao.entities.annotations.MyEntity;
import com.denisgl.dao.jdbc.JdbcTemplate;


/**
 * Keep in mind that findByCriteria returns Object and getAllByCriteria returns List of Objects.
 * @author Denis
 *
 * @param <E> Entity
 * @param <K> Key
 */
public class DAOImpl<E,K> implements DAO<E, K> {

private final Logger logger = Logger.getLogger(DAOImpl.class);
	
	private JdbcTemplate jt;
	private String tableName;
	private String idName;
	private Field[] fields;
	private String[] nameOfColumns;
	private Field fieldId;
	@SuppressWarnings("rawtypes")
	private Class[] classesOfFields;
	private Class<E> type;
	
	public DAOImpl(Class<E> type) {
		jt = new JdbcTemplate();
		this.type = type;
		MyEntity annotation  = type.getAnnotation(MyEntity.class);
		tableName = annotation.tableName();
		idName = annotation.id();
		fields = type.getDeclaredFields();
		declareFieldId();
		classesOfFields = new Class[fields.length];
		nameOfColumns = new String[fields.length];
		declareClassesOfFields();
	}
	private void declareFieldId() {
		try {
			fieldId = type.getDeclaredField(idName);
			fieldId.setAccessible(true);
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
			logger.error(e.getMessage() + " in " + type.getName());
		}
	}
	private void declareClassesOfFields() {
		int i = 0;
		for (Field field : fields) {
			field.setAccessible(true);
			MyColumn column = field.getAnnotation(MyColumn.class);
			classesOfFields[i] = column.clazz();
			nameOfColumns[i] = column.columnName();
		    i++;
		}
	}
	

	@Override
	public List<E> getAll() {
		List<Object[]> listOfArrays = jt.execSelect("select * from " + tableName);
		return createListOfEntities(listOfArrays);
	}
	@Override
	public List<E> getAllByCriteria(String name,Object like){
		List<Object[]> listOfArray = jt.execPreparedSelect("select * from " + tableName + " where " + name + " = ?", like.toString());
		return createListOfEntities(listOfArray);
		
	}
	private List<E> createListOfEntities(List<Object[]> listOfArrays) {
		if(isEmptyList(listOfArrays)) {
			logger.debug("list of arrays of objects contains nulls with " + type.getSimpleName());
			return Collections.emptyList();
		}
		return replaceArraysOnEntities(listOfArrays);
	}
	private boolean isEmptyList(List<Object[]> listOfArrays) {
		return listOfArrays==null || listOfArrays.isEmpty() ||listOfArrays.get(0)[0]==null;
	}
	private List<E> replaceArraysOnEntities(List<Object[]> listOfArrays) {
        List<E> listOfEntities = new ArrayList<E>();
		Object[] row = new Object[listOfArrays.get(0).length];
		for (int i = 0; i < listOfArrays.size(); i++) {
			for(int j = 0; j < row.length;j++) {
				row[j] = listOfArrays.get(i)[j];
			}
			if(listOfArrays.get(i)[0]==null) { continue;}
			addEntityToList(listOfEntities, row);
		}
		return listOfEntities;
	}
	private void addEntityToList(List<E> listOfEntities, Object[] row) {
		try {
			listOfEntities.add(type.getConstructor(classesOfFields).newInstance(row));
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			logger.error(e.getMessage()+ " in " + type.getSimpleName());
		}
	}
	
	
	
	
	@Override
	public E findById(K id) {
		Object[] array = jt.execPreparedSelectForOneRow("select * from " + tableName + " where " + idName + " = ?",id);
		return createEntityFromArray(array);
	
	}
	private E createEntityFromArray(Object[] array) {
		if(array==null || array[0] == null) {
			logger.debug("The array of objects contains nulls with " + type.getSimpleName());
			return createInstanceByArray(new Object[classesOfFields.length]);
		}
		return createInstanceByArray(array);
	}
	private E createInstanceByArray(Object[] array) {
		try {
			return type.getConstructor(classesOfFields).newInstance(array);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage()+ " in " + type.getSimpleName());
		}
		return null;
	}
	
	@Override
	public E findByCriteria(String name, Object like) {
		Object[] array = jt.execPreparedSelectForOneRow("select * from " + tableName + " where " + name + " = ?",like.toString());
		return createEntityFromArray(array);
	}
	
	
	
	@Override
	public boolean delete(E entity) {
		if(entity == null) {
        	logger.debug("Entity was null");
        	return false;
        }
		StringBuffer sb = new StringBuffer();
		sb.append("delete from " + tableName);
		return addWhereAndExecute(sb, entity);
	}
	
	private boolean addWhereAndExecute(StringBuffer sb, E entity) {
    	try {
			sb.append(" where " + idName + " = " + fieldId.get(entity));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			logger.error(" in " + type.getSimpleName(),e);
			return false;
		}
		return jt.executeDDL(sb.toString());
	}

	@Override
	public boolean update(E entity) {
		if(entity == null) {
        	logger.debug("Entity was null");
        	return false;
        }
		StringBuffer sb = new StringBuffer();
		sb.append("update " + tableName + " set ");
	    addCahgesOfColumn(sb,entity);
		return addWhereAndExecute(sb, entity);
	}
	
	private void addCahgesOfColumn(StringBuffer sb,E entity) {
		int i = 1;
		for(Field field : fields) {
			if(i==1) {
				i++;
				continue; //avoid idColumn
			}
			MyColumn column = field.getAnnotation(MyColumn.class);
			String colummnName= column.columnName();
			try {
				char extraSign = needApostroph(field,entity);
				char endSign = isLastInLoop(field, i) ? ' ' : ',';
				sb.append(colummnName + " = " + extraSign + field.get(entity) + extraSign + endSign);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				logger.error(" in " + type.getSimpleName(),e);
			}
			i++;
		}
	}
	
    private char needApostroph(Field field,E entity) 
    		throws IllegalArgumentException, IllegalAccessException {
    	 boolean noNeedApostroph = Number.class.isAssignableFrom(field.getType()) || field.get(entity)==null;
    	 if(noNeedApostroph) return ' ';
    	 else return '\'';
    }
    
    private boolean isLastInLoop(Field field, int i) {
    	return i == fields.length;
    }

    /**
     * DML is protected from sql injection
     */
	@Override
	public boolean save(E entity) {
        if(entity == null) {
        	logger.debug("Entity was null");
        	return false;
        }
        StringBuffer sb = new StringBuffer();
        sb.append("insert into " + tableName + "(");
        addColumnNames(sb);
		sb.append("values(");
		Object[] param = addSqlAndReturnParameters(sb,entity);
		return jt.executePreparedDDL(sb.toString(), param);	
	}
	private void addColumnNames(StringBuffer sb) {
		 int i = 1;
			for(String str : nameOfColumns) {
					if(!idName.equals(str)) {
	       				sb.append(str + (i == nameOfColumns.length?") ":", "));
				}
				i++;
			}
	}
	private Object[] addSqlAndReturnParameters(StringBuffer sb,E entity) {
		Object[] parameters = new Object[fields.length-1];
		int i = 1;
		for (Field field : fields) {
			if(i==1) {
				i++;
				continue; //avoid idColumn
			}
			try {
				char endSign = isLastInLoop(field, i)? ')' : ',';
				sb.append("?" + endSign + " ");
				parameters[i-2] = field.get(entity);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				logger.error(" in " + type.getSimpleName(),e);
			}
			i++;
		}
		return parameters;
	}
}