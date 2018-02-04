package com.denisgl.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import org.apache.log4j.Logger;

/**
 * @author Denis
 *
 */
public class UserVerification {
	
	private static final Logger logger = Logger.getLogger(UserVerification.class);
	
	//ACCESS is the table where stored all information about users
	private static final String FIND_WHERE_EMAIL = "select 'OK' from ACCESS where email = ? and password = ?";
	private static final String FIND_WHERE_PHONE = "select 'OK' from ACCESS where phone = ? and password = ?";

	
	public static boolean authenticateWithPhone(String phone,String password) {
		return authenticate(phone,password,FIND_WHERE_PHONE);
	}
	
	public static boolean authenticateWithEmail(String email,String password) {
		return authenticate(email,password,FIND_WHERE_EMAIL);
	}
	
	private static boolean authenticate(String phoneOrEmail, String password, String find_where) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		Connection con = jdbcTemplate.connectToDataBase();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(find_where);
			ps.setString(1, phoneOrEmail);
			ps.setString(2, password);
			rs = ps.executeQuery();
			return isResultOK(rs);
		} catch (SQLException e) {
			logger.error(e);
		} finally {
			closeAllConnections(jdbcTemplate, con, ps, rs);
		}
		return false;
	}
	
	private static boolean isResultOK(ResultSet rs) throws SQLException {
		String result;
		if(rs.next()) {
			result = rs.getString(1);
			if(Objects.equals("OK", result)) { 
				return true;
			} else {
				logger.error("user was not ok but was '" + result + "'");
			}
		} else { 
			logger.error("There was no result!!!");
		}
		return false;
	}
	
	private static void closeAllConnections(JdbcTemplate jdbcTemplate,Connection con,PreparedStatement ps, ResultSet rs) {
		jdbcTemplate.closeConnection(con);
		try {
			ps.close();
			rs.close();
		} catch (SQLException e) {
			logger.error(e);
		}
	}
}