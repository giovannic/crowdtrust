package crowdtrust;

import java.util.Collection;

import db.CrowdDb;
import db.SubTaskDb;

public class MultiValueSubTask extends SubTask{
	
	int options;

	public MultiValueSubTask(int id, double confidence_threshold, 
			int number_of_labels, int max_labels){
		super(id, confidence_threshold, number_of_labels, max_labels);
	}

	@Override
	protected void updateLikelihoods(Response r, Accuracy a, 
			Collection<Estimate> state) {
		MultiValueResponse mvr = (MultiValueResponse) r;
		SingleAccuracy sa = (SingleAccuracy) a;
		
		if (state.isEmpty()){
			for (int option = 1; option <= options; option++){
				MultiValueResponse initR = new MultiValueResponse(option);
				Estimate initE = new Estimate(initR, Math.log(getZPrior()/1 - getZPrior()),0);
				state.add(initE);
				initEstimate(initE);
			}
		}
		
		for (Estimate record : state){
			if(record.getR().equals(mvr)){
				record.setConfidence(record.getConfidence() +
						Math.log(sa.getAccuracy()/(1-sa.getAccuracy())));
				record.incFrequency();
			} else {
				double ia = inverseAccuracy(sa.getAccuracy());
				record.setConfidence(record.getConfidence() +
						Math.log(ia/(1-ia)));
			}
		}
		
	}

	private double inverseAccuracy(double accuracy) {
		return (1 - accuracy)/(options - 1);
	}

	@Override
	protected void maximiseAccuracy(Accuracy a, Response r, Response z){
		SingleAccuracy sa = (SingleAccuracy) a;
		MultiValueResponse mvr = (MultiValueResponse) r;
		MultiValueResponse mvz = (MultiValueResponse) z;
		
		int total = a.getN() + 2;
		double w = (double)total/(total + 1);
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
	protected void updateAccuracies(Collection<AccuracyRecord> accuracies) {
		db.CrowdDb.updateMultiValueAccuracies(accuracies);
	}

	@Override
	protected double getZPrior() {
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
	protected Collection<Estimate> getEstimates(int id) {
		return SubTaskDb.getMultiValueEstimates(id);
	}

	@Override
	protected Collection<AccuracyRecord> getAnnotators() {
		return CrowdDb.getMultiValueAnnotators(id);
	}

}
