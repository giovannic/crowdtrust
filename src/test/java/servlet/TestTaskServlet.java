package servlet;

import java.io.IOException;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;

public class TestTaskServlet extends TestCase{

	String commandTomcatStart = "tomcat6 start";
	String commandTomcatStop = "tomcat6 stop";

	public TestTaskServlet(String name) {
		super(name);
	}
	
	@Before
	public void before() throws IOException {
		//Process tomcatServer = Runtime.getRuntime().exec(commandTomcatStart);		
	}
	
	public void testServlet() {
		
	}
	
	@After
	public void after() throws IOException {
		//Process tomcatServer = Runtime.getRuntime().exec(commandTomcatStop);		
	}
}
