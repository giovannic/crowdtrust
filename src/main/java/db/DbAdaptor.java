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
	
	public static Connection connect(){
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Properties properties = new Properties();
	      properties.setProperty("user", USER);
	      properties.setProperty("password", PASSWORD);
		try {
			return DriverManager.getConnection(URL, properties);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
