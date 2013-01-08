package algorithm;

import crowdtrust.MultiValueR;
import crowdtrust.Response;
import crowdtrust.TaskType;

public class MultiTestData extends AlgoTestData {
	int actualAnswer;
	
	public MultiTestData(int actualAnswer){
		super(TaskType.MULTI);
		this.actualAnswer = actualAnswer;
	}
	
	public Response getActualAnswer(){
		return new MultiValueR(this.actualAnswer);
	}
}
