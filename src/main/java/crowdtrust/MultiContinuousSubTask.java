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
	protected void maximiseAccuracy(Accuracy a, Response r, Response z){
		SingleAccuracy sa = (SingleAccuracy) a;
		ContinuousMultiR cr = (ContinuousMultiR) r;
		ContinuousMultiR cz = (ContinuousMultiR) z;
		
		int total = a.getN();
		double w = total/total + 1;
		double alpha = sa.accuracy*total;
		
		MultiGaussianDistribution mgd =
				new MultiGaussianDistribution(
						cz.getValues(precision), variance);
		double responseSpace = 1;
		for (int i = 0; i < ranges.length; i++){
			responseSpace *= (ranges[i][1] - ranges[i][0])*precision;
		}
		
		//mle
		double pLabel = mgd.probability(cr.getValues(precision));
		double mle = pLabel/(pLabel + 1/responseSpace);
		sa.accuracy = w*(alpha/total) + (1-w)*mle;
		a.increaseN();
	}


	@Override
	protected Estimate[] updateLikelihoods(Response r, Accuracy a,
			Estimate[] state) {
		ContinuousMultiR cr = (ContinuousMultiR) r;
		SingleAccuracy sa = (SingleAccuracy) a;
		
		boolean matched = false;
		Estimate [] newState;
		double responseSpace = 1;
		for (int i = 0; i < ranges.length; i++){
			responseSpace *= (ranges[i][1] - ranges[i][0])*precision;
		}
		
		MultiGaussianDistribution mgd =
				new MultiGaussianDistribution(
						cr.getValues(precision), variance);
			
		for (Estimate record : state){
			if(record.r.equals(r)){
				matched = true;
			}
			ContinuousMultiR cr2 = (ContinuousMultiR) record.r;
			double p = sa.accuracy*mgd.probability(cr2.getValues(precision)) +
					(1 - sa.accuracy)/responseSpace;
			record.confidence *= p/1-p;
		}
			
		if (!matched){
			newState = Arrays.copyOf(state, state.length+1);
			Estimate e = new Estimate(r, getZPrior());
			double p = sa.accuracy*mgd.probability(cr.getValues(precision)) +
					(1 - sa.accuracy)/responseSpace;
			e.confidence *= p/1-p;
			newState[newState.length] = e;
		} else {
				newState = state.clone();
		}
	
		return newState;
	}

	@Override
	protected double getZPrior() {
		// TODO Auto-generated method stub
		double responseSpace = 1;
		for (int i = 0; i < ranges.length; i++){
			responseSpace *= (ranges[i][1] - ranges[i][0])*precision;
		}
		double p = 1/responseSpace;
		return p/(1-p);
	}
}
