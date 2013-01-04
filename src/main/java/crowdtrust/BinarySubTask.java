package crowdtrust;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public class BinarySubTask extends SubTask {
	
	BinarySubTask(){
		super();
	}

	@Override
	protected void maximiseAccuracy(Accuracy a, Response r, Response z){
		BinaryAccuracy ba = (BinaryAccuracy) a;
		BinaryR br = (BinaryR) r;
		BinaryR bz = (BinaryR) z;
		
		int total;
		double w;
		if (br.isTrue()){
			//maximise truePositive
			total = ba.getPositiveN();
			w = total/total + 1;
			
			double alpha = ba.truePositive*total;
			if(bz.isTrue())
				ba.truePositive = w*(alpha/total) + (1-w);
			else {
				ba.truePositive = w*(alpha/total);
			}
			
			ba.incrementPositiveN();
			
		} else {
			//maximize trueNegative
			total = ba.getNegativeN();
			w = total/total + 1;
			
			double alpha = ba.trueNegative*total;
			if(bz.isTrue())
				ba.trueNegative = w*(alpha/total) + (1-w);
			else {
				ba.trueNegative = w*(alpha/total);
			}
			
			ba.incrementNegativeN();
		}
	}
	
	@Override
	protected Estimate [] updateLikelihoods(Response r,  Accuracy a, 
			Estimate [] state){
		//TODO add new cases
		BinaryR br = (BinaryR) r;
		BinaryAccuracy ba = (BinaryAccuracy) a;
		
		double accuracy;
		if (br.isTrue())
			accuracy = ba.truePositive;
		else
			accuracy = ba.trueNegative;
		
		boolean matched = false;
		for (Estimate record : state){
			if(record.r.equals(br)){
				record.confidence *= accuracy/(1-accuracy);
				matched = true;
			} else {
				record.confidence *= (1-accuracy)/accuracy;
			}
		}
		
		Estimate [] newState;
		
		if (!matched){
			newState = Arrays.copyOf(state, state.length+1);
			Estimate e = new Estimate(r, getZPrior());
			e.confidence *= accuracy/(1-accuracy);
			newState[newState.length] = e;
		} else {
			newState = state.clone();
		}
		
		return newState;
	}
	
	@Override
	protected Accuracy getAccuracy(int annotatorId) {
		return db.CrowdDb.getBinaryAccuracy(annotatorId);
	}

	@Override
	protected Map<Bee, Response> getResponses(Bee[] annotators) {
		return db.SubTaskDb.getBinaryResponses(id, annotators);
	}

	@Override
	protected AccuracyRecord[] getAccuracies(Bee[] annotators) {
		return db.CrowdDb.getBinaryAccuracies(annotators);
	}

	@Override
	protected void updateAccuracies(AccuracyRecord[] accuracies) {
		db.CrowdDb.updateBinaryAccuracies(accuracies);
	}

	@Override
	protected double getZPrior() {
		return 1;
	}

	@Override
	protected double expertLimit() {
		return 2;
	}

	@Override
	protected void updateExperts(Collection<Bee> experts) {
		db.CrowdDb.updateBinaryExperts(experts);
		
	}

	@Override
	protected void updateBots(Collection<Bee> bots) {
		db.CrowdDb.updateBinaryBots(bots);
	}
}
