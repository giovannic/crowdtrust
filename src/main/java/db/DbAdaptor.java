package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.postgresql.Driver;

public class DbAdaptor {
	
	private final static String URL = "jdbc:postgresql://db:5432/g1236218_u";
	private final static String USER = "g1236218_u";
	private final static String PASSWORD = "RLTn4ViKks";
	
<<<<<<< HEAD
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
=======
	public static Connection connect() throws ClassNotFoundException, SQLException{
		Class.forName("org.postgresql.Driver");
		Properties properties = new Properties();
		properties.setProperty("user", USER);
		properties.setProperty("password", PASSWORD);
		return DriverManager.getConnection(URL, properties);
>>>>>>> 442f6939a38faa10af5a3e2d8eb32de230227dda
	}
	
}
