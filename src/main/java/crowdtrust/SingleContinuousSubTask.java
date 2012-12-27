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
		
		NormalDistribution nd = new NormalDistribution(cr.value, Math.sqrt(variance));

		double responseSpace = (range[1] - range[0])*precision;
		
		boolean matched = false;
		for (Estimate record : state){
			if(record.r.equals(r)){
				matched = true;
			}
			ContinuousR cr2 = (ContinuousR) record.r;
			record.confidence = 
					sa.accuracy * nd.probability(cr2.value*precision) +
					(1 - sa.accuracy) * responseSpace;
		}
		
		Estimate [] newState;
		
		if (!matched){
			newState = Arrays.copyOf(state, state.length+1);
			Estimate e = new Estimate(r, 1/responseSpace);
			e.confidence *= sa.accuracy * nd.probability(cr.value * precision) +
					(1 - sa.accuracy) * responseSpace;
			newState[newState.length] = e;
		} else {
			newState = state.clone();
		}
		
		return newState;
	}

}
