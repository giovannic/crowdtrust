package algorithm;

import crowdtrust.ContinuousResponse;
import crowdtrust.Response;
import crowdtrust.TaskType;

/*
 * To answer a bounding box problem the annotator needs the
 * dimensions of the picture as well as the correct answer to
 * draw the normal distribution
 */
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
