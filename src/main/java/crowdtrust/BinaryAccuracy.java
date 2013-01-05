package crowdtrust;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.math3.distribution.BetaDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;

import be.ac.ulg.montefiore.run.jahmm.ObservationReal;
import be.ac.ulg.montefiore.run.jahmm.OpdfGaussian;

public class BinaryAccuracy extends Accuracy {
	double truePositive;
	double trueNegative;
	private int negativeN;
	private int positiveN;
	
	public BinaryAccuracy(double pos, double neg, int numpos, int numneg){
		truePositive = pos;
		trueNegative = neg;
		negativeN = numneg;
		positiveN = numpos;
	}
	
	boolean expert(double threshold){
		NormalDistribution n = new NormalDistribution();
		double d = (n.inverseCumulativeProbability(trueNegative) + 
				n.inverseCumulativeProbability(1 - truePositive))/2;
		return d > threshold;
	}

	int getPositiveN(){
		return positiveN;
	}
	
	int getNegativeN(){
		return negativeN;
	}
	
	void incrementPositiveN(){
		positiveN++;
		n++;
	}
	
	void incrementNegativeN(){
		negativeN++;
		n++;
	}
	
	@Override
	Byte[] serialise() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected double variance() {
		
		double alphaPos = truePositive * positiveN;
		double betaPos = positiveN - alphaPos;
		double alphaNeg = trueNegative * negativeN;
		double betaNeg = negativeN - alphaNeg;
		
		BetaDistribution posCurve = new BetaDistribution(alphaPos, betaPos);
		BetaDistribution negCurve = new BetaDistribution(alphaNeg, betaNeg);
		double maxP = alphaPos/positiveN;
		double maxN = alphaNeg/negativeN;
		
		Collection <ObservationReal> peak = new ArrayList <ObservationReal>();
		
		for (double p = -0.1; p < 0.2; p+=0.1){			
			for (double n = -0.1; n < 0.2; n+=0.1){
				peak.add(new ObservationReal(
						posCurve.density(maxP+p)*negCurve.density(maxN+n)
						)
				);
			}
		}
		
		OpdfGaussian estimate = new OpdfGaussian();
		estimate.fit(peak);
		
		return estimate.variance();
	}
}