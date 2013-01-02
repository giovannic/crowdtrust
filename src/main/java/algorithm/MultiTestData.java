package algorithm;

import crowdtrust.TaskType;

public class MultiTestData extends AlgoTestData {
	int actualAnswer;
	
	public MultiTestData(int actualAnswer){
		super(TaskType.MULTI);
		this.actualAnswer = actualAnswer;
	}
	
	public int getActualAnswer(){
		return this.actualAnswer;
	}
}
