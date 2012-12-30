package crowdtrust;

import java.util.Arrays;

import org.apache.commons.math3.distribution.NormalDistribution;

public class SingleContinuousSubTask extends ContinuousSubTask {

	int [] range;
	double variance;
	
	@Override
	protected Accuracy maximiseAccuracy(Accuracy a, Response response,
			Response z) {
		// TODO Auto-generated method stub
		return null;
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
						cr.value, Math.sqrt(variance));
			
		double pResponseSpace = 1/(range[1] - range[0])*precision;
			
		for (Estimate record : state){
			if(record.r.equals(r)){
				matched = true;
			}
			ContinuousR cr2 = (ContinuousR) record.r;
			double p = sa.accuracy*nd.density(cr2.value*precision) + 
				(1-sa.accuracy)*pResponseSpace;
			record.confidence *= p/(1-p);
		}
			
		if (!matched){
			newState = Arrays.copyOf(state, state.length+1);
			Estimate e = new Estimate(r, getPrior());
			double p = sa.accuracy*nd.density(cr.value*precision) +
					(1-sa.accuracy)*pResponseSpace;
			e.confidence *= p/1-p;
			newState[newState.length] = e;
		} else {
			newState = state.clone();
		}
		
		return newState;
	}

	@Override
	protected double getPrior() {
		// TODO Auto-generated method stub
		double p = 1/(range[1] - range[0])*precision;
		return p/(1-p);
	}

}
