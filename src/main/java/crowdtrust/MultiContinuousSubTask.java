package crowdtrust;

import java.util.Arrays;

import be.ac.ulg.montefiore.run.distributions.MultiGaussianDistribution;

public class MultiContinuousSubTask extends ContinuousSubTask {
	
	int dimensions;
	int [][] ranges;
	double [][] variance;

	MultiContinuousSubTask(double precision, double [][] variance, int d, int [][] ranges){
		super();
		this.precision = precision;
		this.variance = variance;
		this.dimensions = d;
		this.ranges = ranges;
	}

	@Override
	protected Accuracy maximiseAccuracy(Accuracy a, Response response,
			Response z) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Estimate[] updateLikelihoods(Response r, Accuracy a,
			Estimate[] state) {
		ContinuousMultiR cr = (ContinuousMultiR) r;
		AbsoluteAccuracy sa = (AbsoluteAccuracy) a;
		
		boolean matched = false;
		Estimate [] newState;
		double responseSpace = 1;
		for (int i = 0; i < ranges.length; i++){
			responseSpace *= (ranges[i][1] - ranges[i][0])*precision;
		}
		
		if (sa.accuracy == 1){
			MultiGaussianDistribution mgd =
					new MultiGaussianDistribution(
							trueValues(cr.values), variance);
			
			for (Estimate record : state){
				if(record.r.equals(r)){
					matched = true;
				}
				ContinuousMultiR cr2 = (ContinuousMultiR) record.r;
				record.confidence *= mgd.probability(trueValues(cr2.values));
			}
			
			if (!matched){
				newState = Arrays.copyOf(state, state.length+1);
				Estimate e = new Estimate(r, 1/responseSpace);
				e.confidence *= mgd.probability(trueValues(cr.values));
				newState[newState.length] = e;
			} else {
				newState = state.clone();
			}
			
		} else {
			
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
	
	protected double [] trueValues (int [] values){
		double [] t = new double [values.length];
		for (int i = 0; i < values.length; i++){
			t[i] = values[i] * precision;
		}
		return t;
	}

}
