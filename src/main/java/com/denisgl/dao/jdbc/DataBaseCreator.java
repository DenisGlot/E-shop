package com.denisgl.dao.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

import com.denisgl.dao.hash.Hashing;

@WebListener
public class DataBaseCreator implements ServletContextListener{
	
    private final static Logger logger = Logger.getLogger(DataBaseCreator.class);
	
	private static JdbcTemplate jt = new JdbcTemplate();
	
	private static boolean tablesAreNotCreated = true;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		createTables();
		jt = null;//For GC
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) { }
	
	
	private static void createTables() {
		DatabaseMetaData metaData = null;
		Connection connection = jt.connectToDataBase();
		try {
			metaData = connection.getMetaData();//can't put it in other method
		} catch (SQLException e) {
			logger.error(e);
		}
		if (tablesAreNotCreated) {
			
			createACCESS(metaData); // ACCESS for Users
			createROLES(metaData);
			createPRODUCTS(metaData);
			createORDERS(metaData);
			createORDERDETAILS(metaData);
			createSUPLIERS(metaData);
			createCATEGORIES(metaData);
			createCOURIERS(metaData);
			tablesAreNotCreated = false;
		}
		jt.closeConnection(connection);
		metaData = null;//For GC
		connection = null;

	}
	
	private static void createACCESS(DatabaseMetaData metaData) {
		if(tableNotExist(metaData, "ACCESS")) {
			jt.executeDDL(
				"create table ACCESS (id integer not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),firstname varchar(45), lastname varchar(45), dateofbirth timestamp, address varchar(45),phone varchar(45) not null, email varchar(64), password varchar(64) not null, groupid integer not null, CONSTRAINT primary_key PRIMARY KEY (id))");
			jt.executePreparedDDL(
				"insert into ACCESS(firstname,lastname,dateofbirth,address,phone,email,password,groupid) values ('admin','adminof',?,'Street','7777-777-7777','admin','"
						+ Hashing.sha1("admin") + "',1)",
				new Timestamp(123213412l));
			jt.executePreparedDDL(
				"insert into ACCESS(firstname,lastname,dateofbirth,address,phone,email,password,groupid) values ('Iliya','hash',?,'Net','7777-777-7778','iliya','"
						+ Hashing.sha1("123456") + "',1)",
				new Timestamp(123213412l));
			jt.executePreparedDDL(
				"insert into ACCESS(firstname,lastname,dateofbirth,address,phone,email,password,groupid) values ('Denis','hash',?,'Street','7777-777-7779','denis','"
						+ Hashing.sha1("123456") + "',1)",
				new Timestamp(123213412l));
		logger.debug("ACCESS was created");
		}
	}
	
	private static void createROLES(DatabaseMetaData metaData) {
		if(tableNotExist(metaData, "ROLES")) {
			jt.executeDDL("create table ROLES (id integer not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), role varchar(64) not null)");
			jt.executeDDL("insert into ROLES(role) values ('admin')");
			jt.executeDDL("insert into ROLES(role) values ('user')");
		logger.debug("ROLES was created");
		}
	}
	
	private static void createPRODUCTS(DatabaseMetaData metaData) {
		if(tableNotExist(metaData, "PRODUCTS")) {
			jt.executeDDL(
				"create table PRODUCTS (productid integer not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), categoryid integer, suplierid integer not null, price bigint, quantity integer, name varchar(45) not null, description varchar(255),urlofimg varchar(255))");
			jt.executeDDL(
				"insert into PRODUCTS(categoryid, suplierid,price,quantity,name,description,urlofimg) values(1,1,99,10000,'JVM','the best assembly','https://i.ytimg.com/vi/G1ubVOl9IBw/maxresdefault.jpg')");
			jt.executeDDL(
				"insert into PRODUCTS(categoryid, suplierid,price,quantity,name,description,urlofimg) values(2,2,10000,50,'laptop HP190','The super fast processor Intel - 9213, RAM - 8192MB','https://cnet3.cbsistatic.com/img/ttt0dUBA6U9m043Fj1uEui4-6X8=/770x578/2014/09/07/30e56559-a526-47f0-9068-1ca1958ff73d/hp-streammodern-silver.jpg')");
			jt.executeDDL(
				"insert into PRODUCTS(categoryid, suplierid,price,quantity,name,description,urlofimg) values(1,1,100,100,'Windows 10','the best assembly','https://www.windowscentral.com/sites/wpcentral.com/files/styles/larger/public/field/image/2017/03/cloudwallpaper.jpg?itok=VC2ajDrI')");
			jt.executeDDL(
				"insert into PRODUCTS(categoryid, suplierid,price,quantity,name,description,urlofimg) values(2,2,2000,20,'iPhone X','It was announced on September 12, 2017, alongside the iPhone 8 and iPhone 8 Plus at the Steve Jobs Theater in the Apple Park campus. The phone was released on November 3, 2017','http://bm.img.com.ua/berlin/storage/orig/3/ae/1d734f3a21d07121c9c3996a18884ae3.jpg')");
			jt.executeDDL(
				"insert into PRODUCTS(categoryid, suplierid,price,quantity,name,description,urlofimg) values(2,2,99,100,'Fire TV Edition','Element 43-Inch 4K Ultra HD Smart LED TV - Fire TV Edition','https://assets.pcmag.com/media/images/560165-amazon-fire-tv.jpg?thumb=y&width=980&height=416')");
		logger.debug("PRODUCTS was created");
		}
	}
	
	private static void createORDERS(DatabaseMetaData metaData) {
		if(tableNotExist(metaData, "ORDERS")) {
			jt.executeDDL(
				"create table ORDERS (orderid integer not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), userid integer not null,courierid integer, orderdate timestamp, shippereddate timestamp)");
			jt.executeDDL("insert into ORDERS(userid,courierid,orderdate,shippereddate) values(1,1,'" + new Timestamp(1200000000l)
				+ "', '" + new Timestamp(435346453324l) + "')");
		logger.debug("ORDERS was created");
		}
	}
	
	private static void createORDERDETAILS(DatabaseMetaData metaData) {
		if(tableNotExist(metaData, "ORDERDETAILS")) {
			jt.executeDDL(
				"create table ORDERDETAILS (orderdetailsid integer not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),orderid integer ,productid integer, price bigint, quantity integer, discount decimal(3,2))");
			jt.executeDDL("insert into ORDERDETAILS(orderid,productid,price,quantity,discount) values(1,1,100500,1,"
				+ new BigDecimal("0.25") + " )");
		logger.debug("ORDERDETAILS was created");
		}
	}
	
	private static void createSUPLIERS(DatabaseMetaData metaData) {
		if(tableNotExist(metaData, "SUPLIERS")) {
			jt.executeDDL(
				"create table SUPLIERS(suplierid integer not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),name varchar(45) not null, description varchar(255))");
			jt.executeDDL("insert into SUPLIERS(name,description) values('Oracle','The best vendor ever')");
			jt.executeDDL("insert into SUPLIERS(name,description) values('HP','The internation vendor of hardware')");
			jt.executeDDL("insert into SUPLIERS(name,description) values('Microsoft','The internation vendor of OS Windows and other stuff')");
		logger.debug("SUPLIERS was created");
		}
	}
	
	private static void createCATEGORIES(DatabaseMetaData metaData) {
		if(tableNotExist(metaData, "CATEGORIES")) {
			jt.executeDDL(
				"create table CATEGORIES(categoryid integer not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),name varchar(45) not null, description varchar(255))");
			jt.executeDDL("insert into CATEGORIES(name,description) values('Software','OS,JVM and so on')");
			jt.executeDDL("insert into CATEGORIES(name,description) values('Hardware','Laptops,smartphones and so on')");
		logger.debug("CATEGORIES was created");
		}
	}
	
	private static void createCOURIERS(DatabaseMetaData metaData) {
		if(tableNotExist(metaData, "COURIERS")) {
			jt.executeDDL(
					"create table COURIERS (courierid integer not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), firstname varchar(45) not null, lastname varchar(45) not null, hiredate timestamp, birth timestamp)");
			jt.executeDDL("insert into COURIERS(firstname,lastname,hiredate,birth) values('Vasya','Pupkin','" + new Timestamp(1200000000l)
					+ "', '" + new Timestamp(435346453324l) + "')");
			logger.debug("COURIERS was created");
			}
	}
	
	private static boolean tableNotExist(DatabaseMetaData metaData, String tableName) {
		try {
			return !metaData.getTables(null, null, tableName, null).next();
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return false;
		}
	}
	
	private static void dropTables() {
		logger.debug("Droping Tables");
		jt.executeDDL("drop table ACCESS");
		jt.executeDDL("drop table ROLES");
		jt.executeDDL("drop table PRODUCTS");
		jt.executeDDL("drop table SUPLIERS");
		jt.executeDDL("drop table ORDERS");
		jt.executeDDL("drop table ORDERDETAILS");
		jt.executeDDL("drop table CATEGORIES");
		jt.executeDDL("drop table COURIERS");
	}

}