package crowdtrust;

import java.util.Collection;

import org.apache.commons.math3.distribution.NormalDistribution;

public class SingleContinuousSubTask extends ContinuousSubTask {

	int [] range;
	double variance;
	
	SingleContinuousSubTask(double precision, double variance, 
			int [] range, int id, double confidence_threshold, 
			int number_of_labels, int max_labels){
		super(id, confidence_threshold, number_of_labels, max_labels);
		this.precision = precision;
		this.variance = variance;
		this.range = range;
	}
	
	@Override
	protected void maximiseAccuracy(Accuracy a, Response r, Response z){
		SingleAccuracy sa = (SingleAccuracy) a;
		ContinuousR cr = (ContinuousR) r;
		ContinuousR cz = (ContinuousR) z;
		
		int total = a.getN();
		double w = total/total + 1;
		double alpha = sa.getAccuracy()*total;
		
		NormalDistribution nd = 
				new NormalDistribution(
						cz.getValues(precision)[0], Math.sqrt(variance));
		
		double responseSpace = (range[1] - range[0])*precision;
		
		//mle
		double pLabel = nd.density(cr.getValues(precision)[0]);
		double mle = pLabel/(pLabel + 1/responseSpace);
		sa.setAccuracy(w*(alpha/total) + (1-w)*mle);
		a.increaseN();
	}
	
	@Override
	protected void updateLikelihoods(Response r, Accuracy a,
			Collection<Estimate> state) {
		ContinuousR cr = (ContinuousR) r;
		SingleAccuracy sa = (SingleAccuracy) a;
		
		boolean matched = false;
		
		NormalDistribution nd = 
				new NormalDistribution(
						cr.getValues(precision)[0], Math.sqrt(variance));
			
		double pResponseSpace = 1/(range[1] - range[0])*precision;
			
		for (Estimate record : state){
			if(record.getR().equals(r)){
				matched = true;
			}
			ContinuousR cr2 = (ContinuousR) record.getR();
			double p = sa.getAccuracy()*nd.density(cr2.getValues(precision)[0]) + 
				(1-sa.getAccuracy())*pResponseSpace;
			record.setConfidence(record.getConfidence() * (p/(1-p)));
		}
			
		if (!matched){
			Estimate e = new Estimate(r, getZPrior());
			double p = sa.getAccuracy()*nd.density(cr.getValues(precision)[0]) +
					(1-sa.getAccuracy())*pResponseSpace;
			e.setConfidence(e.getConfidence() * (p/1-p));
			state.add(e);
			initEstimate(e);
		}
	}

	@Override
	protected double getZPrior() {
		double p = 1/(range[1] - range[0])*precision;
		return p/(1-p);
	}

	@Override
	protected Collection<Estimate> getEstimates(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void updateEstimates(Collection<Estimate> state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initEstimate(Estimate e) {
		// TODO Auto-generated method stub
		
	}

}
