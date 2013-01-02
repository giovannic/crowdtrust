package crowdtrust;

import java.util.Arrays;

import org.apache.commons.math3.distribution.NormalDistribution;

public class SingleContinuousSubTask extends ContinuousSubTask {

	int [] range;
	double variance;
	
	@Override
	protected void maximiseAccuracy(Accuracy a, Response r, Response z){
		SingleAccuracy sa = (SingleAccuracy) a;
		ContinuousR cr = (ContinuousR) r;
		ContinuousR cz = (ContinuousR) z;
		
		int total = a.getN();
		double w = total/total + 1;
		double alpha = sa.accuracy*total;
		
		NormalDistribution nd = 
				new NormalDistribution(
						cz.getValue(precision), Math.sqrt(variance));
		
		double responseSpace = (range[1] - range[0])*precision;
		
		//mle
		double pLabel = nd.density(cr.getValue(precision));
		double mle = pLabel/(pLabel + 1/responseSpace);
		sa.accuracy = w*(alpha/total) + (1-w)*mle;
		a.increaseN();
	}
	
	@Override
	protected Estimate[] updateLikelihoods(Response r, Accuracy a,
			Estimate[] state) {
		ContinuousR cr = (ContinuousR) r;
		SingleAccuracy sa = (SingleAccuracy) a;
		
		boolean matched = false;
		Estimate [] newState;
		
		NormalDistribution nd = 
				new NormalDistribution(
						cr.getValue(precision), Math.sqrt(variance));
			
		double pResponseSpace = 1/(range[1] - range[0])*precision;
			
		for (Estimate record : state){
			if(record.r.equals(r)){
				matched = true;
			}
			ContinuousR cr2 = (ContinuousR) record.r;
			double p = sa.accuracy*nd.density(cr2.getValue(precision)) + 
				(1-sa.accuracy)*pResponseSpace;
			record.confidence *= p/(1-p);
		}
			
		if (!matched){
			newState = Arrays.copyOf(state, state.length+1);
			Estimate e = new Estimate(r, getZPrior());
			double p = sa.accuracy*nd.density(cr.getValue(precision)) +
					(1-sa.accuracy)*pResponseSpace;
			e.confidence *= p/1-p;
			newState[newState.length] = e;
		} else {
			newState = state.clone();
		}
		
		return newState;
	}

	@Override
	protected double getZPrior() {
		double p = 1/(range[1] - range[0])*precision;
		return p/(1-p);
	}

}
