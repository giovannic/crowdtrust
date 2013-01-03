package algorithm;

import crowdtrust.TaskType;

public class BinaryTestData extends AlgoTestData {
	int actualAnswer;
	
	public BinaryTestData(int actualAnswer){
		super(TaskType.BINARY);
		this.actualAnswer = actualAnswer;
	}
	
	public int getActualAnswer(){
		return this.actualAnswer;
	}
}
