package algorithm;

import crowdtrust.TaskType;

public class ContinuousTestData extends AlgoTestData {
	int pictureArea;
	double[] actualAnswer;
	
	public ContinuousTestData(int pictureArea, double[] actualAnswer){
		super(TaskType.CONTINUOUS);
		this.pictureArea  = pictureArea;
		this.actualAnswer = actualAnswer;
	}
	
	public int getPictureArea(){
		return this.pictureArea;
	}
	
	public double[] getActualAnswer(){
		return actualAnswer;
	}
}
