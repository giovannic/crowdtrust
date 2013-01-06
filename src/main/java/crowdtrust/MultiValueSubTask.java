package crowdtrust;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public class MultiValueSubTask extends SubTask{
	
	int options;

	public MultiValueSubTask(int id, double confidence_threshold, 
			int number_of_labels, int max_labels, int options){
		super(id, confidence_threshold, number_of_labels, max_labels);
		this.options = options;
	}

	@Override
	protected Estimate [] updateLikelihoods(Response r, Accuracy a, Estimate [] state) {
		MultiValueR mvr = (MultiValueR) r;
		SingleAccuracy sa = (SingleAccuracy) a;
		
		boolean matched = false;
		for (Estimate record : state){
			if(record.r.equals(mvr)){
				record.confidence *= sa.getAccuracy()/(1-sa.getAccuracy());
				matched = true;
			} else {
				double ia = inverseAccuracy(sa.getAccuracy());
				record.confidence *= ia/(1-ia);
			}
		}
		
		Estimate [] newState;
		
		if (!matched){
			newState = Arrays.copyOf(state, state.length+1);
			Estimate e = new Estimate(r, getZPrior());
			e.confidence *= sa.getAccuracy()/(1-sa.getAccuracy());
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
	protected void maximiseAccuracy(Accuracy a, Response r, Response z){
		SingleAccuracy sa = (SingleAccuracy) a;
		MultiValueR mvr = (MultiValueR) r;
		MultiValueR mvz = (MultiValueR) z;
		
		int total = a.getN();
		double w = total/total + 1;
		double alpha = sa.getAccuracy()*total;
		if (mvr.equals(mvz)){
			sa.setAccuracy(w*(alpha/total) + (1-w));
		} else {
			sa.setAccuracy(w*(alpha/total));
		}
		a.increaseN();
	}

	@Override
	protected Accuracy getAccuracy(int annotatorId) {
		return db.CrowdDb.getMultiValueAccuracy(annotatorId);
	}

	@Override
	protected Map<Integer, Response> getResponses(Bee[] annotators) {
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

	@Override
	protected double getZPrior() {
		// TODO Auto-generated method stub
		double p = 1/options;
		return p/(1-p);
	}

	@Override
	protected double expertLimit() {
		return 0.85;
	}

	@Override
	protected void updateExperts(Collection<Bee> experts) {
		db.CrowdDb.updateMultiValueExperts(experts);
	}

	@Override
	protected void updateBots(Collection<Bee> bots) {
		db.CrowdDb.updateMultiValueBots(bots);
	}

	@Override
	protected Estimate[] getEstimates(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
