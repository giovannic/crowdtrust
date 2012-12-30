package crowdtrust;

import java.util.Map;

public abstract class SubTask {
	
	protected int id;
	protected double confidence_threshold;
	protected int number_of_labels = 0;
	protected int max_labels;
	
	/*
	 * E step
	 * */
	
	public void addResponse(Bee annotator, Response r) {
		MultiValueR response = (MultiValueR) r;
		
		db.CrowdDb.addResponse(annotator.id, response.serialise(), this.id);
		Accuracy a = getAccuracy(annotator.id);
		
		Estimate [] state = getEstimates(id);
		Estimate [] newState = updateLikelihoods(response,a,state);
		
		Estimate z = estimate(newState);
		number_of_labels++;
		if(z.confidence > confidence_threshold || 
				number_of_labels >= max_labels){
			close();
			updateAccuracies(z.r);
		}
	}
	
	private Estimate[] getEstimates(int id) {
		return null;
	}
	
	protected abstract Estimate [] updateLikelihoods(Response r, 
			Accuracy a, Estimate [] state);
	
	//returns best estimate
	protected Estimate estimate(Estimate [] state) {
		Estimate best = null;
		for (Estimate record : state){
			if(best != null && record.confidence > best.confidence)
				best = record;
		}
		return best;
	}
	
	/*
	 * M step
	 * */
	protected void updateAccuracies(Response z) {
		Bee [] annotators = db.CrowdDb.getAnnotators(id);
		AccuracyRecord [] accuracies = getAccuracies(annotators);
		Map <Bee, Response> responses = getResponses(annotators);
		
		for (AccuracyRecord r : accuracies){
			r.a = maximiseAccuracy(r.a, responses.get(r.b), z);
		}
		updateAccuracies(accuracies);
	}
	
	protected abstract Accuracy maximiseAccuracy(Accuracy a, Response response, Response z);
	
	
	/*
	 * Helper functions
	 * */
	protected abstract Map<Bee, Response> getResponses(Bee[] annotators);

	protected abstract AccuracyRecord[] getAccuracies(Bee[] annotators);
	
	protected abstract void updateAccuracies(AccuracyRecord [] accuracies);
	
	protected abstract Accuracy getAccuracy(int annotatorId);
	
	public void close(){
		db.SubTaskDb.close(id);
		Task parent = db.SubTaskDb.getTask(id);
		parent.notifyFinished();
	}

	//uniform distribution for the time being
	protected abstract double getPrior();

	public String getHtml() {
		return Integer.toString(id);
	}
	
}
