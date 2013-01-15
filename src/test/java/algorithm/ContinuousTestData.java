package algorithm;

import crowdtrust.ContinuousResponse;
import crowdtrust.Response;
import crowdtrust.TaskType;

public class ContinuousTestData extends AlgoTestData {
	int pictureX;
	int pictureY;
	int[] actualAnswer;
	
	public ContinuousTestData(int pictureX, int pictureY, int[] actualAnswer){
		super(TaskType.CONTINUOUS);
		this.pictureX  = pictureX;
		this.pictureY  = pictureY;
		this.actualAnswer = actualAnswer;
	}
	
	public int getPictureX(){
		return this.pictureX;
	}
	
	public int getPictureY(){
		return this.pictureY;
	}
	
	public Response getActualAnswer(){
		return new ContinuousResponse (actualAnswer);
	}
}
