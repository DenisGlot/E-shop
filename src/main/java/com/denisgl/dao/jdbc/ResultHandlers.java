package com.denisgl.dao.jdbc;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;

public class ResultHandlers {

	static final ResultSetHandler<List<Object[]>> handlerForList = initResultSetHandlerWithList();

	static final ResultSetHandler<Object[]> handlerForOneRow = initResultSetHandlerWithOne();
	
	private static ResultSetHandler<List<Object[]>> initResultSetHandlerWithList() {
		return new ResultSetHandler<List<Object[]>>() {
			public List<Object[]> handle(ResultSet rs) throws SQLException {
				ResultSetMetaData meta = rs.getMetaData();
				int columns = meta.getColumnCount();
				List<Object[]> result = new ArrayList<Object[]>();
				while(rs.next()) {
					//I can't put this outside of while loop
					//otherwise it wouldn't work properly
					Object[] row = new Object[columns];
					for (int j = 0; j < columns; j++) {
						row[j] = rs.getObject(j + 1);
					}
					result.add(row);
					row = null;
				}
				meta = null;
				return result;
			}
		};
	}
	
	/**
	 * 
	 * @return just one row for DAO.finById,and DAO.findByCriteria.
	 * WARNING!!! : And even if more than one row it returns the first one.
	 */
	private static ResultSetHandler<Object[]> initResultSetHandlerWithOne() {
		return new ResultSetHandler<Object[]>() {
			public Object[] handle(ResultSet rs) throws SQLException {
				ResultSetMetaData meta = rs.getMetaData();
				int columns = meta.getColumnCount();
				//This is a row array 
				Object[] result = new Object[columns];
				rs.next();
				for (int j = 0; j < columns; j++) {
					result[j] = rs.getObject(j + 1);
				}
				meta = null;
				return result;
			}
		};
	}
}
