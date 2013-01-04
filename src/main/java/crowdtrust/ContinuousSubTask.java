package crowdtrust;

import java.util.Collection;
import java.util.Map;

public abstract class ContinuousSubTask extends SubTask {

	double precision;
	
	@Override
	protected Accuracy getAccuracy(int annotatorId) {
		return db.CrowdDb.getContinuousAccuracy(annotatorId);
	}

	@Override
	protected Map<Bee, Response> getResponses(Bee[] annotators) {
		return db.SubTaskDb.getContinuousResponses(id, annotators);
	}

	@Override
	protected AccuracyRecord[] getAccuracies(Bee[] annotators) {
		return db.CrowdDb.getContinuousAccuracies(annotators);
	}

	@Override
	protected void updateAccuracies(AccuracyRecord[] accuracies) {
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
