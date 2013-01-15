package db;

import junit.framework.TestCase;

public class TestExperts extends TestCase {

	private String labs = "true";

	public void testContinuousExperts() {
		System.setProperty("test", labs);
		assertTrue(CrowdDb.checkContinuousAccuraciesForExperts());
	}

	public void testMultiValueExperts() {
		System.setProperty("test", labs);
		assertTrue(CrowdDb.checkMultiValueAccuraciesForExperts());
	}

}
