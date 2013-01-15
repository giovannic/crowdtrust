package algorithm;

import java.util.Random;
import crowdtrust.ContinuousResponse;
import be.ac.ulg.montefiore.run.distributions.MultiGaussianDistribution;


public class ContinuousBehaviour {
	/*
	 * A rating of how often the user can be bothered to attempt
	 * to give a correct answer
	 */
	protected double honestyRating;
		
	//Create this supplied with honesty rating 0 - 1.0
	public ContinuousBehaviour(double honestyRating){
		this.honestyRating = honestyRating;
	}
	
	public int[] generateContinuousAnswer(int pictureX, int pictureY, ContinuousResponse response){
		Random rand = new Random();
		if(rand.nextDouble() > this.honestyRating){
			return getRandomCoords(pictureX, pictureY);
		}else{
			//Mean is the actual answer
			int[]   intmean        = response.getRawValues();
			double[] mean = new double[4];
			for(int i = 0; i < 4; i++){
				mean[i] = intmean[i] * 1.0;
			}
			//Covariance as stated in the paper
			double[][] covariance  = {
								  	{4, 0, 0, 0},
								  	{0, 4, 0, 0},
								  	{0, 0, 4, 0},
								  	{0, 0, 0, 4},
									};
			MultiGaussianDistribution mgd = new MultiGaussianDistribution(mean, covariance);
			double[] ans = mgd.generate();
			int [] intans = new int[4];
			for(int i = 0; i < 4; i++){
				intans[i] = (int) Math.round(ans[i]);
			}
		
			return intans;
		}
	}
	
	
	//Generated 2 random coords within the area of the picture
	public int[] getRandomCoords(int pictureX, int pictureY){
		int[] ret = new int[4];
		Random rand = new Random();
		ret[0] = rand.nextInt(pictureX);
		ret[1] = rand.nextInt(pictureY);
		ret[2] = rand.nextInt(pictureX);
		ret[3] = rand.nextInt(pictureY);
		return ret;
	}
	
}
