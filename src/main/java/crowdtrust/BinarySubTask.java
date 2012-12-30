package crowdtrust;

import java.util.Arrays;
import java.util.Map;

public class BinarySubTask extends SubTask {
	
	BinarySubTask(){
		super();
	}

	@Override
	protected Accuracy maximiseAccuracy(Accuracy a, Response r, Response z){
		BinaryAccuracy ba = (BinaryAccuracy) a;
		BinaryR bz = (BinaryR) z;
		BinaryR br = (BinaryR) r;
		
		double accuracy;
		
		if (bz.isTrue()){
			//maximise positive rate
			accuracy = ba.truePositive;
		} else {
			//maximise negative rate
			accuracy = ba.trueNegative;
		}
		
		return null;
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
			Estimate e = new Estimate(r, getPrior());
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
	protected double getPrior() {
		return 1;
	}
}
