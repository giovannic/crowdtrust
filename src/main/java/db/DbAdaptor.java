package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbAdaptor {
	
	private final static String URL = "jdbc:postgresql://db:5432/g1236218_u";
	private final static String USER = "g1236218_u";
	private final static String PASSWORD = "RLTn4ViKks";
	
	public static Connection connect(){
		try {
	    	Properties properties = new Properties();
	    	properties.setProperty("user", USER);
	    	properties.setProperty("password", PASSWORD);
	    	Connection connection = DriverManager.getConnection(URL, properties);
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
