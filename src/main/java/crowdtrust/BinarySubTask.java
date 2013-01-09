package crowdtrust;

import java.util.Collection;

import db.SubTaskDb;

public class BinarySubTask extends SubTask {

	public BinarySubTask(int id, double confidence_threshold, 
			int number_of_labels, int max_labels){
		super(id, 0.8, number_of_labels, max_labels);
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
			double alpha = ba.getTruePositive()*total;
			double advAlpha = ba.getTrueNegative()*total;
			double estimate = 0;
			double prior = 0.5;
			w = total/(total + 1);
			//prior normal
			
			if(bz.isTrue())
				estimate = 1;
			
			if (total > 4){
				prior = (alpha+advAlpha-2)/(2*total - 4);
			}
			
			ba.setTruePositive(w*prior + (1-w)*estimate);
			ba.incrementPositiveN();
			
		} else {
			//maximize trueNegative
			total = ba.getNegativeN();
			double alpha = ba.getTrueNegative()*total;
			double advAlpha = ba.getTruePositive()*total;
			w = total/(total + 1);
			double estimate = 0;
			double prior = 0.5;
			//prior normal
			if(bz.isTrue())
				estimate = 1;
			
			if (total > 4){
				prior = (alpha+advAlpha-2)/(2*total - 4);
			}
			
			ba.setTrueNegative(w*prior + (1-w)*estimate);
			
			ba.incrementNegativeN();
		}
		
		
	}
	/*	
	private double peak(double alpha, double advAlpha, double logExpected,
			int total) {
		
		double start = 0;
		double end = 1;
		double mid;
		double diff;
		
		while (end - start > 0.0001){
			mid = (start + end)/2;
			diff = logDiffPrior(mid, alpha, advAlpha, total) + (1/logExpected);
			if (diff > 0)
				start = mid;
			else if (diff < 0)
				end = mid;
			else if (diff == 0){
				return mid;
			}
		}
		return (start + end)/2;
	}

	private double logDiffPrior(double x, double alpha, double alphaAdv, int total) {
		double beta = total - alpha;
		double betaAdv = total - alphaAdv;
		
		double f = Math.pow(x,alpha)*Math.pow(1-x, beta);
		double fAdv = Math.pow(x,alphaAdv)*Math.pow(1-x, betaAdv);
		
		double coF = x*(alpha + beta - 2) - alpha + 1;
		double coFAdv = x*(alphaAdv + betaAdv - 2) - alphaAdv + 1;
		
		double coDen = (x - 1)*x;
		
		return (f*coF + fAdv*coFAdv)/(coDen*(f + fAdv));
	}
	*/
	
	@Override
	protected void updateLikelihoods(Response r,  Accuracy a, 
			Collection<Estimate> state){
		BinaryR br = (BinaryR) r;
		BinaryAccuracy ba = (BinaryAccuracy) a;
		
		double accuracy;
		if (br.isTrue())
			accuracy = ba.getTruePositive();
		else
			accuracy = ba.getTrueNegative();
		
		if (state.isEmpty()){
			BinaryR tR = new BinaryR(true);
			Estimate t = new Estimate(tR, Math.log(getZPrior()/(1 - getZPrior())),0);
			BinaryR fR = new BinaryR(false);
			Estimate f = new Estimate(fR, Math.log(getZPrior()/(1 - getZPrior())),0);
			state.add(t);
			initEstimate(t);
			state.add(f);
			initEstimate(f);
		}
			
		for (Estimate record : state){
			Response recordResponse = record.getR();
			if (!recordResponse.equals(br)){
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
