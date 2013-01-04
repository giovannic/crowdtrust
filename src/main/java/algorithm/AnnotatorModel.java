package algorithm;

import crowdtrust.SubTask;
import crowdtrust.TaskType;

public class AnnotatorModel {
	protected BinaryBehaviour     binary    ;
	protected MultiBehaviour      multi     ;
	protected ContinuousBehaviour continuous;
	
	//------------Initalisation------------
	
	public AnnotatorModel(){}
	
	public void setUpBinary(int truePos, int trueNeg, int totalPos, int totalNeg){
		this.binary = new BinaryBehaviour(truePos, trueNeg, totalPos, totalNeg);
	}
	
	public void setUpMulti(int correctAnswers, int totalAnswers){
		this.multi = new MultiBehaviour(correctAnswers, totalAnswers);
	}
	
	public void setUpContinuous(double honestyRating){
		this.continuous = new ContinuousBehaviour(honestyRating);
	}
	
	//------------------------------------
	
	public void answerTask(SubTask task){/*
		AlgoTestData testData = task.getTestData();
		TaskType t = testData.getTaskType();
		if(t.equals(TaskType.BINARY)){
			int answer = this.binary.generateAnswer(((BinaryTestData)testData).getActualAnswer());
			AnnotatorResponse response = new BinaryResponse(answer);
			//Now link with gios code
		}else if(t.equals(TaskType.MULTI)){
			int answer = this.multi.generateAnswer(((MultiTestData)testData).getActualAnswer());
			AnnotatorResponse response = new MultiResponse(answer);
			//Now link with gios code
		}else if(t.equals(TaskType.CONTINUOUS)){
			double[] answer = this.continuous.generateContinuousAnswer(((ContinuousTestData)testData).pictureArea,
																	  ((ContinuousTestData)testData).getActualAnswer());
			AnnotatorResponse response = new ContinuousResponse(answer);		
			//Now link with gios code
		}*/
	}
}
