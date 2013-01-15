package db;

import java.util.List;
import java.util.ArrayList;

import crowdtrust.AccuracyRecord;
import crowdtrust.Bee;
import crowdtrust.BinaryAccuracy;
import crowdtrust.SingleAccuracy;

import junit.framework.TestCase;

public class TestExperts extends TestCase {

	private final double threshold = 0.85;
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
