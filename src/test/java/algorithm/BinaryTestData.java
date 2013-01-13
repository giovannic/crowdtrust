package algorithm;

import crowdtrust.BinaryResponse;
import crowdtrust.Response;
import crowdtrust.TaskType;

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
