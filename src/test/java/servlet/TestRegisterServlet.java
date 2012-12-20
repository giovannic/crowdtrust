package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;

public class TestRegisterServlet extends TestCase {

	String commandTomcatStart = "tomcat6 start";
	String commandTomcatStop = "tomcat6 stop";
	
	private static final String SERVLET_URL = "http://shell4.doc.ic.ac.uk:45678/servlet/RegisterServlet";
	
	private static final String UN = "test";
	private static final String PW = "test";
	private static final String EMAIL = "test@example.com";
	private static final String CLIENT = "testcl";
	private static final String CROWD = "testcr";
	
	@Before
	public void before() throws IOException {
		Process tomcatServer = Runtime.getRuntime().exec(commandTomcatStart);		
	}
	
	public void testServlet() throws Exception{
		//TODO: INITIALISE DB
		
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
		
		//TODO: lookup name in db
	}
	
	@After
	public void after() throws IOException {
		Process tomcatServer = Runtime.getRuntime().exec(commandTomcatStop);		
	}
}
