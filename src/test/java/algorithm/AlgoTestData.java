package algorithm;

import crowdtrust.TaskType;

public class AlgoTestData {
	protected TaskType taskType;
	
	public AlgoTestData(TaskType taskType){
		this.taskType = taskType;
	}
	
	public TaskType getTaskType(){
		return this.taskType;
	}
}
