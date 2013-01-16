package algorithm;

import crowdtrust.SubTask;

/*
 * A list of these are passed to annotators to use as answers it simply links
 * the task to answer with its id in the DB with any data needed to answer it.
 */
public class AnnotatorSubTaskAnswer {
	protected int id;			 // ID in database
	protected SubTask task;      // task itself
	protected AlgoTestData data; //test data to answer it with
	
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
