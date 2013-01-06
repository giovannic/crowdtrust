package crowdtrust;

import java.util.Collection;
import java.util.Map;

public abstract class ContinuousSubTask extends SubTask {

	double precision;
	
	public ContinuousSubTask(int id, double confidence_threshold, 
			int number_of_labels, int max_labels){
		super(id, confidence_threshold, number_of_labels, max_labels);
	}
	
	@Override
	protected Accuracy getAccuracy(int annotatorId) {
		return db.CrowdDb.getContinuousAccuracy(annotatorId);
	}

	@Override
	protected Map<Integer, Response> getResponses() {
		return db.SubTaskDb.getContinuousResponses(id);
	}

	@Override
	protected Collection<AccuracyRecord> getAccuracies(Collection<Bee> annotators) {
		return db.CrowdDb.getContinuousAccuracies(annotators);
	}

	@Override
	protected void updateAccuracies(Collection<AccuracyRecord> accuracies) {
		db.CrowdDb.updateContinuousAccuracies(accuracies);
	}
	
	@Override
	protected void updateExperts(Collection<Bee> experts) {
		db.CrowdDb.updateContinuousExperts(experts);
		
	}

	@Override
	protected void updateBots(Collection<Bee> bots) {
		db.CrowdDb.updateContinuousBots(bots);
	}



	@Override
	protected abstract void maximiseAccuracy(Accuracy a, 
			Response response, Response z);
	
	@Override
	protected double expertLimit() {
		return 0.85;
	}
	
}
