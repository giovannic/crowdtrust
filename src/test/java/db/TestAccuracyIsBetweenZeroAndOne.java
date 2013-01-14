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
    

  public void testBinaryAccuracyIsAboveZeroForTruePositiveInsert() {
	System.setProperty("test", "false");
    BinaryAccuracy accuracy = new BinaryAccuracy(belowZero, correct, 0, 0);
    assertFalse(CrowdDb.insertBinaryAccuracy(id, accuracy));
  }

  public void testBinaryAccuracyIsBelowOneForTruePositiveInsert() {
		System.setProperty("test", "false");
    BinaryAccuracy accuracy = new BinaryAccuracy(aboveOne, correct, 0, 0);
    assertFalse(CrowdDb.insertBinaryAccuracy(id, accuracy));
  }

  public void testBinaryAccuracyIsAboveZeroForTrueNegativeInsert() {
		System.setProperty("test", "false");
    BinaryAccuracy accuracy = new BinaryAccuracy(correct, belowZero, 0, 0);
    assertFalse(CrowdDb.insertBinaryAccuracy(id, accuracy));
  }

  public void testBinaryAccuracyIsBelowOneForTrueNegativeInsert() {
		System.setProperty("test", "false");
    BinaryAccuracy accuracy = new BinaryAccuracy(correct, aboveOne, 0, 0);
    assertFalse(CrowdDb.insertBinaryAccuracy(id, accuracy));
  }

  public void testBinaryAccuracyIsAboveZeroForTruePositiveUpdate() {
		System.setProperty("test", "false");
    BinaryAccuracy accuracy = new BinaryAccuracy(belowZero, correct, 0, 0);
    AccuracyRecord record = new AccuracyRecord(new Bee(id), accuracy);
    List<AccuracyRecord> list = new ArrayList<AccuracyRecord>();
    list.add(record);
    assertFalse(CrowdDb.updateBinaryAccuracies(list));
  }

  public void testBinaryAccuracyIsBelowOneForTruePositiveUpdate() {
		System.setProperty("test", "false");
    BinaryAccuracy accuracy = new BinaryAccuracy(aboveOne, correct, 0, 0);
    AccuracyRecord record = new AccuracyRecord(new Bee(id), accuracy);
    List<AccuracyRecord> list = new ArrayList<AccuracyRecord>();
    list.add(record);
    assertFalse(CrowdDb.updateBinaryAccuracies(list));
  }

  public void testBinaryAccuracyIsAboveZeroForTrueNegativeUpdate() {
		System.setProperty("test", "false");
    BinaryAccuracy accuracy = new BinaryAccuracy(correct, belowZero, 0, 0);
    AccuracyRecord record = new AccuracyRecord(new Bee(id), accuracy);
    List<AccuracyRecord> list = new ArrayList<AccuracyRecord>();
    list.add(record);
    assertFalse(CrowdDb.updateBinaryAccuracies(list));
  }

  public void testBinaryAccuracyIsBelowOneForTrueNegativeUpdate() {
		System.setProperty("test", "false");
    BinaryAccuracy accuracy = new BinaryAccuracy(correct, aboveOne, 0, 0);
    AccuracyRecord record = new AccuracyRecord(new Bee(id), accuracy);
    List<AccuracyRecord> list = new ArrayList<AccuracyRecord>();
    list.add(record);
    assertFalse(CrowdDb.updateBinaryAccuracies(list));
  }


  public void testMultiValueAccuracyIsAboveZero() {
		System.setProperty("test", "false");
    SingleAccuracy accuracy = new SingleAccuracy(belowZero, 0);
    AccuracyRecord record = new AccuracyRecord(new Bee(id), accuracy);
    List<AccuracyRecord> list = new ArrayList<AccuracyRecord>();
    list.add(record);
    assertFalse(CrowdDb.updateMultiValueAccuracies(list));
  }

  public void testMultiValueAccuracyIsBelowOne() {
		System.setProperty("test", "false");
    SingleAccuracy accuracy = new SingleAccuracy(aboveOne, 0);
    AccuracyRecord record = new AccuracyRecord(new Bee(id), accuracy);
    List<AccuracyRecord> list = new ArrayList<AccuracyRecord>();
    list.add(record);
    assertFalse(CrowdDb.updateMultiValueAccuracies(list));
  }

  public void testContinuousAccuracyIsAboveZero() {
		System.setProperty("test", "false");
    SingleAccuracy accuracy = new SingleAccuracy(belowZero, 0);
    AccuracyRecord record = new AccuracyRecord(new Bee(id), accuracy);
    List<AccuracyRecord> list = new ArrayList<AccuracyRecord>();
    list.add(record);
    assertFalse(CrowdDb.updateContinuousAccuracies(list));
  }

  public void testContinuousAccuracyIsBelowOne() {
		System.setProperty("test", "false");
    SingleAccuracy accuracy = new SingleAccuracy(aboveOne, 0);
    AccuracyRecord record = new AccuracyRecord(new Bee(id), accuracy);
    List<AccuracyRecord> list = new ArrayList<AccuracyRecord>();
    list.add(record);
    assertFalse(CrowdDb.updateContinuousAccuracies(list));
  }

}
