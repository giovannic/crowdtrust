package algorithm;

import org.apache.commons.math3.distribution.NormalDistribution;

import crowdtrust.BinaryResponse;

public class BinaryBehaviour {
	/*
	 * Represents how biased the member is towards 0/1 1(low threshold)
	 * 0 (low threshold) No bias if = 0
	 */
	protected double threshold;
	/*
	 * Represents how well the member can distinguish between the two classes of answer
	 */
	protected double sensitivityIndex;
	/*
	 * Assumed in the paper
	 */
	protected int variance    = 1;
	protected int standardDev = 1;
	/*
	 * Records the members answering statistics used to calculate t, d and
	 * therefore ukj
	 */
	protected int truePos;
	protected int trueNeg;
	protected int totalPos;
	protected int totalNeg;
	protected double truePosRate;
	protected double trueNegRate;
	/*
	 * Seed the crowd memeber with these inital values from this the true positive
	 * and negative rates can be calculated which are used to calculate the inital
	 * t and d, if you seeded the member with a t and d and truepos/neg rates the
	 * rates wouldn't actually reflect that t and d.
	 */
	public BinaryBehaviour(int truePos, int trueNeg, int totalPos, int totalNeg){
		this.truePos  = truePos ; this.trueNeg = trueNeg ;
		this.totalPos = totalPos; this.totalNeg = totalNeg;
		this.updateRates();      //Set up the true pos/neg rates
	}
	
	public int generateAnswer(BinaryResponse response){
		int realanswer;
		if (response.isTrue()){
			realanswer = 1;
		}else{
			realanswer = 0;
		}
		//update the sensory index and threshold for the calculation of ujk
		this.updateSensThresh();
		double ujk = calculateujk(response.isTrue() ? 1 : 0);
		//generate the appropriate normal distribution
		NormalDistribution dist = new NormalDistribution(ujk, this.standardDev);
		/*
		 * generate a random number sampled from this distribution representing
		 * the annotators signal
		 */

		double signal = dist.sample();
		//Compare signal to threshold and return the appropriate answer.
		int answer = (Double.compare(signal, this.threshold) > 0) ? 1 : 0;
		return answer;
	}

	
	//Calculates the new true pos/neg rates
	private void updateRates(){
		this.truePosRate = ((this.truePos * 1.0) / (this.totalPos * 1.0));
		this.trueNegRate = ((this.trueNeg * 1.0) / (this.totalNeg * 1.0));
	}
	
	/*
	 * 	|1  1/2|   |t| | inv(aj0)     |  
	 *	|1 -1/2| = |d| | inv(1 - aj1) |
	 *
	 *	From this...
	 *
	 *	t + d/2 = inv(aj0)
	 *  t - d/2 = inv(1 - aj1)
	 */ 
	private void updateSensThresh(){
		NormalDistribution standardNormal = new NormalDistribution();
		double invTrueNeg = standardNormal.inverseCumulativeProbability(this.trueNegRate); 
		double invTruePos = standardNormal.inverseCumulativeProbability(1 - this.truePosRate);
		
		this.sensitivityIndex = invTrueNeg - invTruePos;
		this.threshold = invTrueNeg - (this.sensitivityIndex / (2 * 1.0));
	}
	
	private double calculateujk(int actualAnswer){
		if(actualAnswer == 1){
			return (this.sensitivityIndex / 2);
		}else{
			return -(this.sensitivityIndex / 2);
		}
	}
	
	/*
	 * 	Get methods mainly used for testing
	 */
	
	public int getTotalPos(){
		return this.totalPos;
	}
	
	public int getTotalNeg(){
		return this.totalNeg;
	}
	
	public int getTruePos(){
		return this.truePos;
	}
	
	public int getTrueNeg(){
		return this.trueNeg;
	}
	
	public int getFalseNeg(){
		return (this.totalNeg - this.trueNeg);
	}
	
	public int getFalsePos(){
		return(this.totalPos - this.truePos);
	}
	
	public double getThreshold(){
		return this.threshold;
	}
	
	public double getSensIndex(){
		return this.sensitivityIndex;
	}
	
	public double getTruePosRate(){
		return this.truePosRate;
	}
	
	public double getTrueNegRate(){
		return this.trueNegRate;
	}
	
}
