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
import crowdtrust.SubTask;

/*
 * Provides a model of a human being able to answer binary multivalue and
 * continuous questions
 */
public class AnnotatorModel {
	protected LinkedList<AnnotatorSubTaskAnswer> tasks     ; //Answers for them to refer to
	protected BinaryBehaviour                    binary    ; //Ability to answer binary questions
	protected MultiBehaviour                     multi     ; // ..
	protected ContinuousBehaviour                continuous; // ..
	protected Bee								 bee       ; // Connects John to giovanni code
	protected String							 username  ; // for accessing the database
	protected String							 password  ; // ..
	
	//------------Initalisation------------
	
	public AnnotatorModel(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	public BinaryBehaviour getBinaryBehaviour(){
		return this.binary;
	}
	
	//Give the annotator the answer
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
		//Get the answer to the task and any data with it
		AnnotatorSubTaskAnswer savedTask = this.findTask(task.getId());
		/*
		 * Work out what type the task is then retrieve any testing data and answer the task
		 * then register the response in the database.
		 */
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
		}else if(task instanceof MultiContinuousSubTask){
			ContinuousTestData testData = ((ContinuousTestData) savedTask.getAlgoTestData());
			int[] answer = this.continuous.generateContinuousAnswer(testData.getPictureX(), testData.getPictureY(), (ContinuousResponse)testData.getActualAnswer());
			task.addResponse(this.bee, new ContinuousResponse(answer));
		}
	
	}
	
	//Retrieve the answer for a particular subtask
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
