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
		double responseSpace = (range[1] - range[0])*precision;
		
		if (sa.accuracy == 1){
			NormalDistribution nd = 
					new NormalDistribution(
							cr.value, Math.sqrt(variance));
			
			for (Estimate record : state){
				if(record.r.equals(r)){
					matched = true;
				}
				ContinuousR cr2 = (ContinuousR) record.r;
				record.confidence *= nd.density(cr2.value*precision);
			}
			
			if (!matched){
				newState = Arrays.copyOf(state, state.length+1);
				Estimate e = new Estimate(r, 1/responseSpace);
				e.confidence *= nd.density(cr.value*precision);
				newState[newState.length] = e;
			} else {
				newState = state.clone();
			}
			
		} else {
			//TODO abstract it
			for (Estimate record : state){
				if(record.r.equals(r)){
					matched = true;
				}
				record.confidence *= 1/responseSpace;
			}
			
			if (!matched){
				newState = Arrays.copyOf(state, state.length+1);
				Estimate e = new Estimate(r, 1/responseSpace);
				e.confidence *= 1/responseSpace;
				newState[newState.length] = e;
			} else {
				newState = state.clone();
			}
		}
		
		return newState;
	}

}
