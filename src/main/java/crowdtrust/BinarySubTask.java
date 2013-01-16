package crowdtrust;

import java.util.Collection;

import db.SubTaskDb;

public class BinarySubTask extends SubTask {

	
	public BinarySubTask(int id, double confidence_threshold, 
			int number_of_labels, int max_labels){
		super(id, confidence_threshold, number_of_labels, max_labels);
	} 

	@Override
	protected void maximiseAccuracy(Accuracy a, Response r, Response z){
		BinaryAccuracy ba = (BinaryAccuracy) a;
		BinaryResponse br = (BinaryResponse) r;
		BinaryResponse bz = (BinaryResponse) z;
		
		int total;
		double w;
		if (br.isTrue()){
			//maximise truePositive
			total = ba.getPositiveN() + 2;
			double alpha = ba.getTruePositive()*total;
			double advAlpha = ba.getTrueNegative()*total;
			double estimate = 0;
			w = (double) total/(total + 1);
			
			if(bz.isTrue())
				estimate = 1;
			
			double prior = (alpha+advAlpha)/(2*total);
			ba.setTruePositive(w*prior + (1-w)*estimate);
			ba.incrementPositiveN();
			
		} else {
			//maximize trueNegative
			total = ba.getNegativeN() + 2;
			double alpha = ba.getTrueNegative()*total;
			double advAlpha = ba.getTruePositive()*total;
			w = (double) total/(total + 1);
			double estimate = 0;

			if(bz.isTrue())
				estimate = 1;
			
			double prior = (alpha+advAlpha)/(2*total);
			
			ba.setTrueNegative(w*prior + (1-w)*estimate);
			ba.incrementNegativeN();
		}
		
		
	}
	
	@Override
	protected void updateLikelihoods(Response r,  Accuracy a, 
			Collection<Estimate> state){
		BinaryResponse br = (BinaryResponse) r;
		BinaryAccuracy ba = (BinaryAccuracy) a;
		
		double accuracy;
		if (br.isTrue())
			accuracy = ba.getTruePositive();
		else
			accuracy = ba.getTrueNegative();
		
		if (state.isEmpty()){
			BinaryResponse tR = new BinaryResponse(true);
			Estimate t = new Estimate(tR, Math.log(getZPrior()/(1 - getZPrior())),0);
			BinaryResponse fR = new BinaryResponse(false);
			Estimate f = new Estimate(fR, Math.log(getZPrior()/(1 - getZPrior())),0);
			state.add(t);
			initEstimate(t);
			state.add(f);
			initEstimate(f);
		}
			
		for (Estimate record : state){
			Response recordResponse = record.getR();
			if (recordResponse.equals(br)){
				record.setConfidence(record.getConfidence()
						+ Math.log(accuracy/(1-accuracy)));
				record.incFrequency();
			} else {
				record.setConfidence(record.getConfidence()
						+ Math.log(((1-accuracy)/accuracy)));
			}
		}
		
	}

	@Override
	protected Accuracy getAccuracy(int annotatorId) {
		return db.CrowdDb.getBinaryAccuracy(annotatorId);
	}

	@Override
	protected void updateAccuracies(Collection<AccuracyRecord> accuracies) {
		db.CrowdDb.updateBinaryAccuracies(accuracies);
	}

	@Override
	protected double getZPrior() {
		return 0.5;
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

	@Override
	protected Collection <Estimate> getEstimates(int id) {
		return SubTaskDb.getBinaryEstimates(id);
	}

	@Override
	protected Collection<AccuracyRecord> getAnnotators() {
		return db.CrowdDb.getBinaryAnnotators(id);
	}
	
	

}
