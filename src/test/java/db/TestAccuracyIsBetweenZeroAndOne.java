package db;

import junit.framework.TestCase;

import java.util.List;
import java.util.ArrayList;

import crowdtrust.AccuracyRecord;
import crowdtrust.Bee;
import crowdtrust.BinaryAccuracy;
import crowdtrust.SingleAccuracy;

public class TestAccuracyIsBetweenZeroAndOne extends TestCase {

  private final double belowZero = -0.5;
  private final double aboveOne = 1.5;
  private final double correct = 0.5;
  private final int id = 1;
	private String labs = "true";

  public void testBinaryAccuracyForCorrectInputInsert() {
		System.setProperty("test", labs);
	  BinaryAccuracy accuracy = new BinaryAccuracy(correct, correct, 0, 0);
	  assertTrue(CrowdDb.insertBinaryAccuracy(id, accuracy));
  }
  
  public void testBinaryAccuracyForCorrectInputUpdate() {
		System.setProperty("test", labs);
	  BinaryAccuracy accuracy = new BinaryAccuracy(correct, correct, 0, 0);
	  AccuracyRecord record = new AccuracyRecord(new Bee(id), accuracy);
    List<AccuracyRecord> list = new ArrayList<AccuracyRecord>();
    list.add(record);
    assertTrue(CrowdDb.updateBinaryAccuracies(list));
  }

  public void testBinaryAccuracyIsAboveZeroForTruePositiveInsert() {
	  System.setProperty("test", labs);
    BinaryAccuracy accuracy = new BinaryAccuracy(belowZero, correct, 0, 0);
    assertFalse(CrowdDb.insertBinaryAccuracy(id, accuracy));
  }

  public void testBinaryAccuracyIsBelowOneForTruePositiveInsert() {
		System.setProperty("test", labs);
    BinaryAccuracy accuracy = new BinaryAccuracy(aboveOne, correct, 0, 0);
    assertFalse(CrowdDb.insertBinaryAccuracy(id, accuracy));
  }

  public void testBinaryAccuracyIsAboveZeroForTrueNegativeInsert() {
		System.setProperty("test", labs);
    BinaryAccuracy accuracy = new BinaryAccuracy(correct, belowZero, 0, 0);
    assertFalse(CrowdDb.insertBinaryAccuracy(id, accuracy));
  }

  public void testBinaryAccuracyIsBelowOneForTrueNegativeInsert() {
		System.setProperty("test", labs);
    BinaryAccuracy accuracy = new BinaryAccuracy(correct, aboveOne, 0, 0);
    assertFalse(CrowdDb.insertBinaryAccuracy(id, accuracy));
  }

  public void testBinaryAccuracyIsAboveZeroForTruePositiveUpdate() {
		System.setProperty("test", labs);
    BinaryAccuracy accuracy = new BinaryAccuracy(belowZero, correct, 0, 0);
    AccuracyRecord record = new AccuracyRecord(new Bee(id), accuracy);
    List<AccuracyRecord> list = new ArrayList<AccuracyRecord>();
    list.add(record);
    assertFalse(CrowdDb.updateBinaryAccuracies(list));
  }

  public void testBinaryAccuracyIsBelowOneForTruePositiveUpdate() {
		System.setProperty("test", labs);
    BinaryAccuracy accuracy = new BinaryAccuracy(aboveOne, correct, 0, 0);
    AccuracyRecord record = new AccuracyRecord(new Bee(id), accuracy);
    List<AccuracyRecord> list = new ArrayList<AccuracyRecord>();
    list.add(record);
    assertFalse(CrowdDb.updateBinaryAccuracies(list));
  }

  public void testBinaryAccuracyIsAboveZeroForTrueNegativeUpdate() {
		System.setProperty("test", labs);
    BinaryAccuracy accuracy = new BinaryAccuracy(correct, belowZero, 0, 0);
    AccuracyRecord record = new AccuracyRecord(new Bee(id), accuracy);
    List<AccuracyRecord> list = new ArrayList<AccuracyRecord>();
    list.add(record);
    assertFalse(CrowdDb.updateBinaryAccuracies(list));
  }

  public void testBinaryAccuracyIsBelowOneForTrueNegativeUpdate() {
		System.setProperty("test", labs);
    BinaryAccuracy accuracy = new BinaryAccuracy(correct, aboveOne, 0, 0);
    AccuracyRecord record = new AccuracyRecord(new Bee(id), accuracy);
    List<AccuracyRecord> list = new ArrayList<AccuracyRecord>();
    list.add(record);
    assertFalse(CrowdDb.updateBinaryAccuracies(list));
  }

  public void testMultiValueAccuracyIsAboveZeroUpdate() {
		System.setProperty("test", labs);
    SingleAccuracy accuracy = new SingleAccuracy(belowZero, 0);
    AccuracyRecord record = new AccuracyRecord(new Bee(id), accuracy);
    List<AccuracyRecord> list = new ArrayList<AccuracyRecord>();
    list.add(record);
    assertFalse(CrowdDb.updateMultiValueAccuracies(list));
  }

  public void testMultiValueAccuracyIsBelowOneUpdate() {
		System.setProperty("test", labs);
    SingleAccuracy accuracy = new SingleAccuracy(aboveOne, 0);
    AccuracyRecord record = new AccuracyRecord(new Bee(id), accuracy);
    List<AccuracyRecord> list = new ArrayList<AccuracyRecord>();
    list.add(record);
    assertFalse(CrowdDb.updateMultiValueAccuracies(list));
  }

	public void testMultiValueAccuracyIsAboveZeroInsert() {
		System.setProperty("test", labs);
    SingleAccuracy accuracy = new SingleAccuracy(belowZero, 0);
    assertFalse(CrowdDb.insertMVAccuracy(id, accuracy));
  }

  public void testMultiValueAccuracyIsBelowOneInsert() {
		System.setProperty("test", labs);
    SingleAccuracy accuracy = new SingleAccuracy(aboveOne, 0);
    assertFalse(CrowdDb.insertMVAccuracy(id, accuracy));
  }

	public void testMultiValueAccuracyIsCorrectInsert() {
		System.setProperty("test", labs);
	  SingleAccuracy accuracy = new SingleAccuracy(correct, 0);
		assertTrue(CrowdDb.insertMVAccuracy(id, accuracy));
	}

  public void testContinuousAccuracyIsAboveZeroUpdate() {
		System.setProperty("test", labs);
    SingleAccuracy accuracy = new SingleAccuracy(belowZero, 0);
    AccuracyRecord record = new AccuracyRecord(new Bee(id), accuracy);
    List<AccuracyRecord> list = new ArrayList<AccuracyRecord>();
    list.add(record);
    assertFalse(CrowdDb.updateContinuousAccuracies(list));
  }

  public void testContinuousAccuracyIsBelowOneUpdate() {
		System.setProperty("test", labs);
    SingleAccuracy accuracy = new SingleAccuracy(aboveOne, 0);
    AccuracyRecord record = new AccuracyRecord(new Bee(id), accuracy);
    List<AccuracyRecord> list = new ArrayList<AccuracyRecord>();
    list.add(record);
    assertFalse(CrowdDb.updateContinuousAccuracies(list));
  }

	public void testContinuousAccuracyIsAboveZeroInsert() {
		System.setProperty("test", labs);
    SingleAccuracy accuracy = new SingleAccuracy(belowZero, 0);
    assertFalse(CrowdDb.insertContinuousAccuracy(id, accuracy));
  }

  public void testContinuousAccuracyIsBelowOneInsert() {
		System.setProperty("test", labs);
    SingleAccuracy accuracy = new SingleAccuracy(aboveOne, 0);
    assertFalse(CrowdDb.insertContinuousAccuracy(id, accuracy));
  }

	public void testContinuousAccuracyIsCorrectInsert() {
		System.setProperty("test", labs);
	  SingleAccuracy accuracy = new SingleAccuracy(correct, 0);
		assertTrue(CrowdDb.insertContinuousAccuracy(id, accuracy));
	}
}
