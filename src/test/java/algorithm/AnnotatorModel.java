package algorithm;

import java.util.Iterator;
import java.util.LinkedList;

import crowdtrust.Bee;
import crowdtrust.BinaryResponse;
import crowdtrust.BinarySubTask;
import crowdtrust.ContinuousResponse;
import crowdtrust.MultiContinuousSubTask;
import crowdtrust.MultiValueResponse;
import crowdtrust.MultiValueSubTask;
import crowdtrust.SingleContinuousSubTask;
import crowdtrust.SubTask;

public class AnnotatorModel {
	protected LinkedList<AnnotatorSubTaskAnswer> tasks     ;
	protected BinaryBehaviour                    binary    ;
	protected MultiBehaviour                     multi     ;
	protected ContinuousBehaviour                continuous;
	protected Bee								 bee       ;
	protected String							 username  ;
	protected String							 password  ;
	
	//------------Initalisation------------
	
	public AnnotatorModel(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	public BinaryBehaviour getBinaryBehaviour(){
		return this.binary;
	}
	
	public void setTasks(LinkedList<AnnotatorSubTaskAnswer> tasks){
		this.tasks = tasks;
	}
	
	public void setId(int id){
		this.bee = new Bee(id);
	}
	
	public Bee getBee(){
		return this.bee;
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
			int answer = this.binary.generateAnswer((BinaryResponse) testData.getActualAnswer());
			//Link with gios code
			boolean boolAnswer;
			if(answer == 1)
				boolAnswer = true;
			else
				boolAnswer = false;
			task.addResponse(this.bee, new BinaryResponse(boolAnswer));
		}else if(task instanceof MultiValueSubTask){
			MultiTestData testData = ((MultiTestData) savedTask.getAlgoTestData());
			int answer = this.multi.generateAnswer((MultiValueResponse) testData.getActualAnswer(), testData.getNumOptions());
			//Link with gios code
			task.addResponse(this.bee, new MultiValueResponse(answer));
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
	
	public MultiBehaviour getMultiBehaviour(){
		return this.multi;
	}
	
	public String getUsername(){
		return this.username;
	}
	
	public String getPassword(){
		return this.password;
	}
}
