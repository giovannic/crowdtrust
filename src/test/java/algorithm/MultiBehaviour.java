package algorithm;

import java.util.Random;

public class MultiBehaviour {
	public int correctAnswers;
	public int totalAnswers;
	
	protected double successRate;
	
	//Seed this user with a number of correct answers and their total answers
	public MultiBehaviour(int correctAnswers, int totalAnswers){
		this.correctAnswers = correctAnswers;
		this.totalAnswers   = totalAnswers;
		this.updateRates();
	}
	
	/*
	 * return 1: user given correct answer
	 * return 0: user given wrong answer
	 * 
	 * Returns 1 with the probability correct answers/total answers
	 * and returns 0 with the probability total - correct answers / total answers
	 * 
	 * It's done like this because generating an incorrect answer is suprisingly hard
	 * unless you have all the possible answers it can take.
	 */
	public int generateAnswer(int actualAnswer){
		Random generator = new Random();
		int answer;
		if(generator.nextDouble() > this.successRate){
			this.totalAnswers++;
			answer = 0;
		}else{
			this.correctAnswers++; this.totalAnswers++;
			answer = 1;
		}
		this.updateRates();
		return answer;
	}
	
	//Creates a success rate correct answers / total answers
	public void updateRates(){
		this.successRate = ((this.correctAnswers * 1.0)/(this.totalAnswers * 1.0));
	}
	
}
