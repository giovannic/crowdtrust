package algorithm;

import crowdtrust.SubTask;

public class AnnotatorSubTaskAnswer {
	protected int id;
	protected SubTask task;
	protected AlgoTestData data;
	
	public AnnotatorSubTaskAnswer(int id, SubTask task, AlgoTestData data){
		this.id   = id;
		this.task = task;
		this.data = data;
	}
	
	public int getId(){
		return this.id;
	}
	
	public AlgoTestData getAlgoTestData(){
		return this.data;
	}
}
