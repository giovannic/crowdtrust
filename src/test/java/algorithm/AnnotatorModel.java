package algorithm;

import java.util.Iterator;
import java.util.LinkedList;

import crowdtrust.Bee;
import crowdtrust.BinaryR;
import crowdtrust.BinarySubTask;
import crowdtrust.ContinuousR;
import crowdtrust.MultiContinuousSubTask;
import crowdtrust.MultiValueR;
import crowdtrust.MultiValueSubTask;
import crowdtrust.SingleContinuousSubTask;
import crowdtrust.SubTask;

public class AnnotatorModel {
	protected LinkedList<AnnotatorSubTaskAnswer> tasks     ;
	protected BinaryBehaviour                    binary    ;
	protected MultiBehaviour                     multi     ;
	protected ContinuousBehaviour                continuous;
	protected Bee								 bee       ;
	
	//------------Initalisation------------
	
	public AnnotatorModel(int id){
		this.bee = new Bee(id);
	}
	
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
	
	public void answerTask(SubTask task){
		AnnotatorSubTaskAnswer savedTask = this.findTask(task.getId());
		if(task instanceof BinarySubTask){
			BinaryTestData testData = ((BinaryTestData) savedTask.getAlgoTestData());
			int answer = this.binary.generateAnswer(testData.getActualAnswer());
			//Link with gios code
			boolean boolAnswer;
			if(answer == 1)
				boolAnswer = true;
			else
				boolAnswer = false;
			task.addResponse(this.bee, new BinaryR(boolAnswer));
		}else if(task instanceof MultiValueSubTask){
			MultiTestData testData = ((MultiTestData) savedTask.getAlgoTestData());
			int answer = this.multi.generateAnswer(testData.getActualAnswer());
			//Link with gios code
			task.addResponse(this.bee, new MultiValueR(answer));
		}else if(task instanceof SingleContinuousSubTask){
			//Leave for now
		}else if(task instanceof MultiContinuousSubTask){
			//leave for now
		}
	
	}
	
	public AnnotatorSubTaskAnswer findTask(int id){
		Iterator<AnnotatorSubTaskAnswer> i = this.tasks.iterator();
		while(i.hasNext()){
			AnnotatorSubTaskAnswer temp = i.next();
			if(temp.id == id){
				return temp;
			}
		}
		return null;
	}
}
