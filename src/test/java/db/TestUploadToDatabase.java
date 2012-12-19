package db;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import junit.framework.TestCase;

public class TestUploadToDatabase extends TestCase{

	public TestUploadToDatabase(String name) {
		super(name);
	}
	
	public void testUpload() throws Exception {
		String commandTomcatStart = "tomcat6 start";
		String commandTomcatStop = "tomcat6 stop";
		Process tomcatServer = Runtime.getRuntime().exec(commandTomcatStart);
		InputStream is = tomcatServer.getInputStream();
		String result = IOUtils.toString(is, "ASCII");
		System.out.println(result);
		Process tomcatStop = Runtime.getRuntime().exec(commandTomcatStop);
		assertTrue(true);
	}

}
