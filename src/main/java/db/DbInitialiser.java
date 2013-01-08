package db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;

import org.apache.commons.io.FileUtils;

public class DbInitialiser {	

	private final static String INITSQL_LOC = "src/main/sql/init.sql";
	private static final String TASKS_DIRECTORY = "/vol/project/2012/362/g1236218/TaskFiles/";
	
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
			FileUtils.deleteDirectory(new File(TASKS_DIRECTORY));
			FileUtils.forceMkdir(new File(TASKS_DIRECTORY));
		} catch (Exception e) {
			System.err.println("SOMEBODY MOVED OR FUDGED init.sql");
			System.err.println("currently looking for it in: " + INITSQL_LOC);
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
