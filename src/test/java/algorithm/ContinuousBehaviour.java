package algorithm;

import java.util.Random;

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
	
	public double[] generateContinuousAnswer(int pictureArea, double[] answer){
		
		//Mean is the actual answer
		double[]   mean        = {answer[0], answer[1], answer[2], answer[3]};
		//Covariance as stated in the paper
		double[][] covariance  = {
								  {4, 0, 0, 0},
								  {0, 4, 0, 0},
								  {0, 0, 4, 0},
								  {0, 0, 0, 4},
								 };
		MultiGaussianDistribution mgd = new MultiGaussianDistribution(mean, covariance);
		double[] lhs = mgd.generate();
		double[] rhs = new double[4];
		// aj * N(mean, covariance)
		lhs = matrixMultiply(this.honestyRating, lhs);
		//generate some random co-ordinates anywhere within the picture
		rhs = getRandomCoords(pictureArea);
		// (1 - aj) * 1/chi ^ 2
		rhs = matrixMultiply((1 - this.honestyRating), rhs);
		return matrixAddition(rhs, lhs);
	}
	
	
	//Generated 2 random coords within the area of the picture
	public double[] getRandomCoords(int pictureArea){
		double[] ret = new double[4];
		Random gen = new Random();
		for(int i = 0; i < 4; i++){
			ret[i] = gen.nextInt(101);
		}
		return ret;
	}
	
	//Multiplies a matrix by a constant
	public double[] matrixMultiply(double multiplier, double[] matrix){
		for(int i = 0; i < matrix.length; i++){
			matrix[i] = matrix[i] * multiplier;
		}
		return matrix;
	}
	
	//adds two arrays together basically
	public double[] matrixAddition(double[] m1, double[] m2){
		double[] ret = new double[4];
		for(int i = 0; i < m1.length; i++){
			ret[i] = m1[i] + m2[i];
		}
		return ret;
	}
}
