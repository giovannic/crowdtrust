package servlet;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;

public class TestTaskServlet {

	String commandTomcatStart = "tomcat6 start";
	String commandTomcatStop = "tomcat6 stop";
	
	@Before
	public void before() throws IOException {
		Process tomcatServer = Runtime.getRuntime().exec(commandTomcatStart);		
	}
	
	@After
	public void after() throws IOException {
		Process tomcatServer = Runtime.getRuntime().exec(commandTomcatStop);		
	}
}
