package algorithm;

import crowdtrust.Response;
import crowdtrust.TaskType;

/*
 * This class serves to give the AnnotatorModel everything it needs to answer a question, when
 * modelling the algorithm.
 */
public abstract class AlgoTestData {
	protected TaskType taskType;
	
	public AlgoTestData(TaskType taskType){
		this.taskType = taskType;
	}
	
	public TaskType getTaskType(){
		return this.taskType;
	}
	
	public abstract Response getActualAnswer();
}
