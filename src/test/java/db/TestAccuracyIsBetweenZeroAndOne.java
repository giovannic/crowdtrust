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
    BinaryAccuracy accuracy = new BinaryAccuracy(belowZero, correct, 0, 0);
    assertFalse(CrowdDb.insertBinaryAccuracy(id, accuracy));
  }

  public void testBinaryAccuracyIsBelowOneForTruePositiveInsert() {
    BinaryAccuracy accuracy = new BinaryAccuracy(aboveOne, correct, 0, 0);
    assertFalse(CrowdDb.insertBinaryAccuracy(id, accuracy));
  }

  public void testBinaryAccuracyIsAboveZeroForTrueNegativeInsert() {
    BinaryAccuracy accuracy = new BinaryAccuracy(correct, belowZero, 0, 0);
    assertFalse(CrowdDb.insertBinaryAccuracy(id, accuracy));
  }

  public void testBinaryAccuracyIsBelowOneForTrueNegativeInsert() {
    BinaryAccuracy accuracy = new BinaryAccuracy(correct, aboveOne, 0, 0);
    assertFalse(CrowdDb.insertBinaryAccuracy(id, accuracy));
  }

  public void testBinaryAccuracyIsAboveZeroForTruePositiveUpdate() {
    BinaryAccuracy accuracy = new BinaryAccuracy(belowZero, correct, 0, 0);
    AccuracyRecord record = new AccuracyRecord(new Bee(id), accuracy);
    List<AccuracyRecord> list = new ArrayList<AccuracyRecord>();
    list.add(record);
    assertFalse(CrowdDb.updateBinaryAccuracies(list));
  }

  public void testBinaryAccuracyIsBelowOneForTruePositiveUpdate() {
    BinaryAccuracy accuracy = new BinaryAccuracy(aboveOne, correct, 0, 0);
    AccuracyRecord record = new AccuracyRecord(new Bee(id), accuracy);
    List<AccuracyRecord> list = new ArrayList<AccuracyRecord>();
    list.add(record);
    assertFalse(CrowdDb.updateBinaryAccuracies(list));
  }

  public void testBinaryAccuracyIsAboveZeroForTrueNegativeUpdate() {
    BinaryAccuracy accuracy = new BinaryAccuracy(correct, belowZero, 0, 0);
    AccuracyRecord record = new AccuracyRecord(new Bee(id), accuracy);
    List<AccuracyRecord> list = new ArrayList<AccuracyRecord>();
    list.add(record);
    assertFalse(CrowdDb.updateBinaryAccuracies(list));
  }

  public void testBinaryAccuracyIsBelowOneForTrueNegativeUpdate() {
    BinaryAccuracy accuracy = new BinaryAccuracy(correct, aboveOne, 0, 0);
    AccuracyRecord record = new AccuracyRecord(new Bee(id), accuracy);
    List<AccuracyRecord> list = new ArrayList<AccuracyRecord>();
    list.add(record);
    assertFalse(CrowdDb.updateBinaryAccuracies(list));
  }


  public void testMultiValueAccuracyIsAboveZero() {
    SingleAccuracy accuracy = new SingleAccuracy(belowZero, 0);
    AccuracyRecord record = new AccuracyRecord(new Bee(id), accuracy);
    List<AccuracyRecord> list = new ArrayList<AccuracyRecord>();
    list.add(record);
    assertFalse(CrowdDb.updateMultiValueAccuracies(list));
  }

  public void testMultiValueAccuracyIsBelowOne() {
    SingleAccuracy accuracy = new SingleAccuracy(aboveOne, 0);
    AccuracyRecord record = new AccuracyRecord(new Bee(id), accuracy);
    List<AccuracyRecord> list = new ArrayList<AccuracyRecord>();
    list.add(record);
    assertFalse(CrowdDb.updateMultiValueAccuracies(list));
  }

  public void testContinuousAccuracyIsAboveZero() {
    SingleAccuracy accuracy = new SingleAccuracy(belowZero, 0);
    AccuracyRecord record = new AccuracyRecord(new Bee(id), accuracy);
    List<AccuracyRecord> list = new ArrayList<AccuracyRecord>();
    list.add(record);
    assertFalse(CrowdDb.updateContinuousAccuracies(list));
  }

  public void testContinuousAccuracyIsBelowOne() {
    SingleAccuracy accuracy = new SingleAccuracy(aboveOne, 0);
    AccuracyRecord record = new AccuracyRecord(new Bee(id), accuracy);
    List<AccuracyRecord> list = new ArrayList<AccuracyRecord>();
    list.add(record);
    assertFalse(CrowdDb.updateContinuousAccuracies(list));
  }

}
