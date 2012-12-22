package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;

import com.gargoylesoftware.htmlunit.WebClient;

public class TestRegisterServlet extends TestCase {

	private static final int port = 55550;
	private static final String SERVLET_URL = "http://localhost:"+port+"/servlet/register";
	
	private static final String UN = "test";
	private static final String PW = "test";
	private static final String EMAIL = "test@example.com";
	private static final String CLIENT = "testcl";
	private static final String CROWD = "testcr";
	
	private WebClient client;
	
	@Before
	public void before() {
		client = new WebClient();
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
