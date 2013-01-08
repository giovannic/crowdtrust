package algorithm;

import crowdtrust.Response;
import crowdtrust.Task;
import crowdtrust.TaskType;

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
