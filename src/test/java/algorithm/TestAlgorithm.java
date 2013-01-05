package algorithm;

import java.util.Random;
import java.util.UUID;

import junit.framework.TestCase;

public class TestAlgorithm extends TestCase {
	protected static int annotatorNumber = 1000;
	protected static AnnotatorModel[] annotators;
	
	protected static int totalPos = 1000;	//Annotators when created have 
	protected static int totalNeg = 1000;   //'Answered' 2000 questions
	
	public TestAlgorithm(String name){
		super(name);
	}
	
	public void testAlgorithm(){
		//Lets create some annotators with id's 1 - 1000 and place them in array
		annotators = new AnnotatorModel[annotatorNumber];
		for(int i = 0; i < annotatorNumber; i++){
			String uuid = UUID.randomUUID().toString();
			uuid = uuid.replace("-", "");
			uuid = uuid.substring(0, 12);
			annotators[i] = new AnnotatorModel(uuid, uuid);
		}
		
		//Set up the annotators so they can answer binary question
		Random rand = new Random();
		for(int i = 0; i < annotatorNumber; i++){
			int truePos = rand.nextInt(999) + 1;	
			int trueNeg = rand.nextInt(999) + 1;
			annotators[i].setUpBinary(truePos, trueNeg, totalPos, totalNeg);
		}
		
		//Add them to the Database
		for(int i = 0; i < 1000; i++){
			
		}
		
	}
}
