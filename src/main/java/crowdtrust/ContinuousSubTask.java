package crowdtrust;

import java.util.Collection;

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
	
	@Override
	protected Collection<AccuracyRecord> getAnnotators() {
		return db.CrowdDb.getContinuousAnnotators(id);
	}

	public static SubTask makeSubtask(int id, int taskAccuracy, int responses,
			int maxLabels, String start, String finish, float precision) {
		String [] starts = start.split("/");
		String [] finishs = finish.split("/");
		
		int dimensions = starts.length;
		
		int [][] ranges = new int [dimensions][2];
		
		for (int i = 0; i < dimensions; i++){
			ranges[i][0] = Integer.parseInt(starts[i]);
			ranges[i][1] = Integer.parseInt(finishs[i]);
		}
		
		double responseSpace = 1;
		for (int i = 0; i < dimensions; i++){
			responseSpace *= (ranges[i][1] - ranges[i][0])*precision;
		}
		
		double variance = responseSpace/(7*dimensions);
		
		if (starts.length == 1)
			return new SingleContinuousSubTask(precision, variance , ranges[0], 
					id, taskAccuracy, responses, maxLabels);
		return new MultiContinuousSubTask(precision, identity(responseSpace/50, dimensions), dimensions,
				ranges, id, taskAccuracy, responses, maxLabels);
	}

	private static double[][] identity(double d, int dim) {
		double[][] covariance = new double [dim][dim];
		for (int i = 0; i < dim; i++){
			covariance[i][i] = d;
		}
		return covariance;
	}
	
}
