package com.denisgl.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.log4j.Logger;

import com.denisgl.dao.jdbc.exceptions.NotDeclaredConnection;

/**
 * Never forget that here i used the array with 100 rows in initResultSetHandler
 * So if database will grow you must increase the amount of rows
 * @author Denis
 *
 */
public class JdbcTemplate {

	private final Logger logger = Logger.getLogger(JdbcTemplate.class);
	
	public JdbcTemplate() {
		try {
			Class.forName(JdbcPropertiesLoader.driver);
		} catch (ClassNotFoundException e) {
			logger.error(e);
		}
	}
	
	/**
	 * Declares connection
	 * Be careful! Do not use it without method closeConnection
	 * @throws NotDeclaredConnection 
	 */
    Connection connectToDataBase() {
		try {
			return DriverManager.getConnection(JdbcPropertiesLoader.jdbc_url);
		} catch (SQLException e) {
			logger.error(e);
			throw new NotDeclaredConnection();
		}
	}
    
	void closeConnection(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			logger.error(e);
		}
	}

	public boolean executeDDL(String myQuery) {
		Connection con = connectToDataBase();
		try(Statement statement = con.createStatement()) {
		     statement.executeUpdate(myQuery);
		     return true;
		} catch (SQLException e) {
			logger.error(e);
		} finally {
			closeConnection(con);
		}
		return false;
	}
	
	public boolean executePreparedDDL(String myQuery, Object ... param) {
		Connection con = connectToDataBase();
		try(PreparedStatement ps = con.prepareStatement(myQuery)){
			for(int i = 1; i<=param.length; i++) {
				ps.setObject(i, param[i-1]);
			}
			ps.executeUpdate();
			return true;
			
		} catch (SQLException e) {
			logger.error(e);
		} finally {
			closeConnection(con);
		}
		return false;
	}
	
	public List<Object[]> execSelect(String myQuery) {
		return executeAnySelect(myQuery, ResultHandlers.handlerForList, null);

	}
	
	public List<Object[]> execPreparedSelect(String myQuery, Object ... param ) {
		return executeAnySelect(myQuery, ResultHandlers.handlerForList, param);

	}
	
	/**
	 * It was created for method DAOImpl.findById 
	 */
	public Object[] execSelectForOneRow(String myQuery) {
		return executeAnySelect(myQuery, ResultHandlers.handlerForOneRow, null);
	}
	
	/**
	 * It was created for method DAOImpl.findByCriteria 
	 */
	public Object[] execPreparedSelectForOneRow(String myQuery, Object ... param ) {
		return executeAnySelect(myQuery, ResultHandlers.handlerForOneRow, param);
	}
	
	private<T> T executeAnySelect(String myQuery, ResultSetHandler<T> handler, Object ... param) {
		Connection con = connectToDataBase();
		QueryRunner run = new QueryRunner();
		try {
			if(param == null) {
				return run.query(con, myQuery, handler);
			} else {
			    return run.query(con, myQuery, handler, param);
			}
		} catch (SQLException e) {
			logger.error(e);
		} finally {
			closeConnection(con);
		}
		return null;
	}

}
