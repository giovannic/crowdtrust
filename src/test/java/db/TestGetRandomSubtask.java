package db;

import junit.framework.TestCase;

public class TestGetRandomSubtask extends TestCase {
	private String labs = "true";

	public void testGetRandomSubtasks() {
		System.setProperty("test", labs);
		assertNotNull(SubTaskDb.getRandomSubTask(1,1));
	}

}
