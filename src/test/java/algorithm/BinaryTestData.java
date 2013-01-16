package algorithm;

import crowdtrust.BinaryResponse;
import crowdtrust.Response;
import crowdtrust.TaskType;

/*
 * All an annotator needs to answer a binary question is the correct answer
 * so the appropriate normal distribution can be chosen.
 */
public class BinaryTestData extends AlgoTestData {
	int actualAnswer;
	
	public BinaryTestData(int actualAnswer){
		super(TaskType.BINARY);
		this.actualAnswer = actualAnswer;
	}

	@Override
	public Response getActualAnswer() {
		return actualAnswer == 1 ? new BinaryResponse(true) : new BinaryResponse(false);
	}
}
