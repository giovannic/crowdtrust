package servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;

import com.gargoylesoftware.htmlunit.WebClient;

import db.DbAdaptor;

public class TestRegisterServlet extends TestCase {

	private static final int port = 55550;
	private static final String SERVLET_URL = "http://localhost:"+port+"/servlet/register";
	
	private static final String UN = "test";
	private static final String PW = "test";
	private static final String EMAIL = "test@example.com";
	private static final String CLIENT = "testcl";
	private static final String CROWD = "testcr";
	
	private final static String URL = "jdbc:postgresql://db:5432/g1236218_u";
	private final static String USER = "g1236218_u";
	private final static String PASSWORD = "RLTn4ViKks";
	private final static String INITSQL_LOC = "src/main/sql/init.sql";
	private WebClient client;
	
	private Connection connection = null;
	
	@Before
	public void before() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(INITSQL_LOC));
			StringBuffer sb = new StringBuffer();
			String sqlLine;
			while((sqlLine = reader.readLine()) != null) {
				sb.append(sqlLine + "\n");
			}
			reader.close();
			connection = DbAdaptor.connect();
			Statement s = null;
			connection.createStatement().execute(sb.toString());
		} catch (Exception e) {
			System.err.println("SOMEBODY MOVED OR FUDGED init.sql");
			System.err.println("currently looking for it in: " + INITSQL_LOC);
			e.printStackTrace();
		}
		System.out.println("I HAVE RUN");
	}
	
	public void testServlet() throws Exception{
		//TODO: INITIALISE DB
		/*
		URL url = new URL(SERVLET_URL);
		URLConnection connection = url.openConnection();
		connection.setUseCaches(false);
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		PrintWriter outWriter = new PrintWriter(connection.getOutputStream());
		outWriter.print("username="+UN);
		outWriter.print("&password="+PW);
		outWriter.print("&cpassword="+PW);
		outWriter.print("&email="+EMAIL);
		outWriter.print("&client="+CLIENT);
		outWriter.print("&crowd="+CROWD);
		outWriter.close();
		*/
		//TODO: lookup name in db
	}
	
	@After
	public void after() throws IOException {		
	}
}
