package algorithm;

import crowdtrust.ContinuousMultiR;
import crowdtrust.Response;
import crowdtrust.TaskType;

public class ContinuousTestData extends AlgoTestData {
	int pictureArea;
	int[] actualAnswer;
	
	public ContinuousTestData(int pictureArea, int[] actualAnswer){
		super(TaskType.CONTINUOUS);
		this.pictureArea  = pictureArea;
		this.actualAnswer = actualAnswer;
	}
	
	public int getPictureArea(){
		return this.pictureArea;
	}
	
	public Response getActualAnswer(){
		return new ContinuousMultiR (actualAnswer);
	}
}
