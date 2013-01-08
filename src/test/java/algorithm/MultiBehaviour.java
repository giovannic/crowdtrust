package algorithm;

import java.util.Random;

import crowdtrust.MultiValueR;

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
	
	public int generateAnswer(MultiValueR multiValueR, int numOptions){
		Random generator = new Random();
		int answer;
		if(generator.nextDouble() > this.successRate){
			if(multiValueR.getSelection() < numOptions)
				answer = multiValueR.getSelection() + 1;
			else
				answer = multiValueR.getSelection() - 1;
		}else{
			this.correctAnswers++; this.totalAnswers++;
			answer = multiValueR.getSelection();
		}
		//this.updateRates();
		return answer;
	}
	
	//Creates a success rate correct answers / total answers
	public void updateRates(){
		this.successRate = ((this.correctAnswers * 1.0)/(this.totalAnswers * 1.0));
	}
	
}
