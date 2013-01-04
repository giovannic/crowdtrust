package db;

import java.util.Collection;

import crowdtrust.Accuracy;
import crowdtrust.AccuracyRecord;
import crowdtrust.Bee;
import crowdtrust.BinaryAccuracy;
import crowdtrust.SingleAccuracy;

public class CrowdDb {

	public static void addResponse(int id, Byte[] serialise, int id2) {
		// TODO Auto-generated method stub
		
	}

	public static BinaryAccuracy getBinaryAccuracy(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Bee[] getAnnotators(int subtask_id) {
		// TODO Auto-generated method stub
		return null;
	}

	public static AccuracyRecord[] getBinaryAccuracies(Bee[] annotators) {
		// TODO Auto-generated method stub
		return null;
	}

	public static SingleAccuracy getMultiValueAccuracy(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void updateBinaryAccuracies(AccuracyRecord[] accuracies) {
		// TODO Auto-generated method stub
	}

	public static AccuracyRecord[] getMultiValueAccuracies(Bee[] annotators) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void updateMultiValueAccuracies(AccuracyRecord[] accuracies) {
		// TODO Auto-generated method stub
		
	}

	public static Accuracy getContinuousAccuracy(int annotatorId) {
		// TODO Auto-generated method stub
		return null;
	}

	public static AccuracyRecord[] getContinuousAccuracies(Bee[] annotators) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void updateContinuousAccuracies(AccuracyRecord[] accuracies) {
		// TODO Auto-generated method stub
		
	}

	public static void updateBinaryExperts(Collection<Bee> experts) {
		// TODO Auto-generated method stub
		
	}

	public static void updateBinaryBots(Collection<Bee> bots) {
		// TODO Auto-generated method stub
		
	}

	public static void updateContinuousExperts(Collection<Bee> bots) {
		// TODO Auto-generated method stub
		
	}

	public static void updateContinuousBots(Collection<Bee> bots) {
		// TODO Auto-generated method stub
		
	}

	public static void updateMultiValueExperts(Collection<Bee> experts) {
		// TODO Auto-generated method stub
		
	}

	public static void updateMultiValueBots(Collection<Bee> bots) {
		// TODO Auto-generated method stub
		
	}

}
