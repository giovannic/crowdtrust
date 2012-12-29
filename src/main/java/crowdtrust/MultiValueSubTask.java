package crowdtrust;

import java.util.Arrays;
import java.util.Map;

public class MultiValueSubTask extends SubTask{
	
	int options;

	MultiValueSubTask(int options){
		super();
		this.options = options;
	}

	@Override
	protected Estimate [] updateLikelihoods(Response r, Accuracy a, Estimate [] state) {
		MultiValueR mvr = (MultiValueR) r;
		SingleAccuracy sa = (SingleAccuracy) a;
		
		boolean matched = false;
		for (Estimate record : state){
			if(record.r.equals(mvr)){
				record.confidence *= sa.accuracy;
				matched = true;
			} else {
				record.confidence *= inverseAccuracy(sa.accuracy);
			}
		}
		
		Estimate [] newState;
		
		if (!matched){
			newState = Arrays.copyOf(state, state.length+1);
			Estimate e = new Estimate(r, (1/options));
			e.confidence *= sa.accuracy;
			newState[newState.length] = e;
		} else {
			newState = state.clone();
		}
		
		return newState;
	}

	private double inverseAccuracy(double accuracy) {
		return (1 - accuracy)/(options - 1);
	}

	@Override
	protected Accuracy maximiseAccuracy(Accuracy a, Response response, Response z) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Accuracy getAccuracy(int annotatorId) {
		return db.CrowdDb.getMultiValueAccuracy(annotatorId);
	}

	@Override
	protected Map<Bee, Response> getResponses(Bee[] annotators) {
		return db.SubTaskDb.getMultiValueResponses(id, annotators);
	}

	@Override
	protected AccuracyRecord[] getAccuracies(Bee[] annotators) {
		return db.CrowdDb.getMultiValueAccuracies(annotators);
	}

	@Override
	protected void updateAccuracies(AccuracyRecord[] accuracies) {
		db.CrowdDb.updateMultiValueAccuracies(accuracies);
	}

}
