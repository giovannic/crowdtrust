package db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Statement;

public class DbInitialiser {	

	private final static String INITSQL_LOC = "src/main/sql/init.sql";
	
	private static Connection connection = null;
	
	public static boolean init() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(INITSQL_LOC));
			StringBuffer sb = new StringBuffer();
			String sqlLine;
			while((sqlLine = reader.readLine()) != null) {
				sb.append(sqlLine + "\n");
			}
			reader.close();
			connection = DbAdaptor.connect();
			connection.createStatement().execute(sb.toString());
			connection.close();
			connection = null;
		} catch (Exception e) {
			System.err.println("SOMEBODY MOVED OR FUDGED init.sql");
			System.err.println("currently looking for it in: " + INITSQL_LOC);
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
