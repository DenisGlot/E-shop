package com.denisgl.dao.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.denisgl.dao.jdbc.exceptions.NoPropertiesLoaded;

public class JdbcPropertiesLoader {

	static final String driver = initDriver();
	static final String jdbc_url = initJdbcUrl();
	
	private static String initDriver() {
		return loadProperties("/database.properties").getProperty("driver_derby");
	}
	
	private static String initJdbcUrl() {
		return loadProperties("/database.properties").getProperty("jdbc_url");
	}
	
	/**
	 * @throws NoPropertiesLoaded
	 */
	private static Properties loadProperties(String path) {
		Properties prop = new Properties();
		try(InputStream input = JdbcPropertiesLoader.class.getResourceAsStream(path);) {
			prop.load(input);
		} catch (IOException e) {
			throw new NoPropertiesLoaded();
		}
		return prop;
	}
}
