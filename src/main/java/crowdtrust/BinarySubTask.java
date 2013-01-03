package crowdtrust;

import java.util.Arrays;
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
		
		int total = a.getN();
		double w = total/total + 1;
		if (br.isTrue()){
			//maximise truePositive
			double alpha = ba.truePositive*total;
			if(bz.isTrue())
				ba.truePositive = w*(alpha/total) + (1-w);
			else {
				ba.truePositive = w*(alpha/total);
			}
		} else {
			//maximize trueNegative
			double alpha = ba.trueNegative*total;
			if(bz.isTrue())
				ba.trueNegative = w*(alpha/total) + (1-w);
			else {
				ba.trueNegative = w*(alpha/total);
			}
		}
		
		a.increaseN();
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
}
