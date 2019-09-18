package com.revature.utilities;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ConnectionUtility {
	
	private static Connection conn = null;
	
	public static Connection getConnection() {
		try {
			Properties props = new Properties();
			
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			props.load(loader.getResourceAsStream("connection.properties"));
			
			String url = props.getProperty("url");
			String username = props.getProperty("username");
			String password = props.getProperty("password");

//			System.out.println();
			
			conn = DriverManager.getConnection(url, username, password);
			
//			VisualUtility.typingCustomSplitter("*@~@*@~@*@~@*@~@CONNECTED@~@*@~@*@~@*@~@*", 20, 4);
//			System.out.println((char)27 + "[0m");
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
