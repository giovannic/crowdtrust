package algorithm;

import crowdtrust.MultiValueResponse;
import crowdtrust.Response;
import crowdtrust.TaskType;
/*
 * To answer a multivalue question the annotator needs the actual
 * answer and the number of options.
 */
public class MultiTestData extends AlgoTestData {
	int actualAnswer;
	int numOptions;
	
	public MultiTestData(int actualAnswer, int numOptions){
		super(TaskType.MULTI);
		this.actualAnswer = actualAnswer;
		this.numOptions = numOptions;
	}
	
	public int getNumOptions(){
		return this.numOptions;
	}
	
	public Response getActualAnswer(){
		return new MultiValueResponse(this.actualAnswer);
	}
}
