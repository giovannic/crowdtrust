package algorithm;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import crowdtrust.AnnotationType;
import crowdtrust.BinaryAccuracy;
import crowdtrust.InputType;
import crowdtrust.MediaType;
import crowdtrust.MultiValueSubTask;
import crowdtrust.MultiValueSubTaskBuilder;
import crowdtrust.SubTask;

import db.CrowdDb;
import db.DbInitialiser;
import db.LoginDb;
import db.RegisterDb;
import db.SubTaskDb;
import db.TaskDb;
import junit.framework.TestCase;

public class TestAlgorithmNewMulti extends TestCase {
	protected static int numTasks = 9;
	protected static int numPeople = 10;
	protected static AnnotatorModel[] annotators;
	protected static int options = 5;
	
	public TestAlgorithmNewMulti(String name){
		super(name);
	}
	
	public void testAlgorithmNewMulti(){
		System.setProperty("test", "false");
		boolean labs = false;
		if(labs){
			//Clean the database 
			DbInitialiser.init();
			
			//Lets create some annotators with id's 1 - numPeople and place them in array
			annotators = new AnnotatorModel[numPeople];
			for(int i = 0; i < numPeople; i++){
				String uuid = UUID.randomUUID().toString();
				uuid = uuid.replace("-", "");
				uuid = uuid.substring(0, 12);
				annotators[i] = new AnnotatorModel(uuid, uuid);
			}
			
			//Set up the annotators so they can answer binary question
			Random rand = new Random();
			double percentageNormal = 0.8;
			for(int i = 0; i < numPeople; i++){
					if(rand.nextDouble() > percentageNormal){
						annotators[i].setUpMulti(200, 1000);						
					}else{
						annotators[i].setUpMulti(900, 1000);							
					}

				}
				
			
			
			//Create and print their rates and names
			for(int i = 0; i < numPeople; i++){
				RegisterDb.addUser("test@test.com", annotators[i].getUsername(), annotators[i].getPassword(), true);
				annotators[i].setId(LoginDb.checkUserDetails(annotators[i].getUsername(), annotators[i].getPassword()));
				AnnotatorModel a = annotators[i];
	//			System.out.println("annotator " + 
		//				a.bee.getId() + 
			//			" truePosRate =" + a.binary.truePosRate +
			//			" trueNegRate =" + a.binary.trueNegRate);
			}
			
			//Lets make a client
			RegisterDb.addUser("testClient@test.com", "gio", "gio", false);
			int accountId = LoginDb.checkUserDetails("gio", "gio");
			//Lets add a binary task to the database
			long expiry = getDate();
			float accuracy = (float)0.7;
			List<String> testQs = new LinkedList<String>();
			testQs.add("test q1");
			testQs.add("test q2");
			//GIO
			LinkedList<String> tired = new LinkedList<String>();
			tired.add("5");
			assertTrue(TaskDb.addTask(accountId,"MultiTestTask", "This is a test?", accuracy, MediaType.IMAGE, AnnotationType.MULTIVALUED, InputType.RADIO, numPeople , expiry, testQs, new LinkedList<String>(), tired, 0)>0);
			
			//List of answers
			LinkedList<AnnotatorSubTaskAnswer> answers = new LinkedList<AnnotatorSubTaskAnswer>();
			//System.out.println("About to get Task id");
			//System.out.println("John Task Id: " + TaskDb.getTaskId("BinaryTestTask"));
			//System.out.println("Got it");
			
			//Lets create a linked list of subTasks
			for(int i = 0; i < numTasks; i++){
				String uuid = UUID.randomUUID().toString();
				uuid = uuid.replace("-", "");
				uuid = uuid.substring(0, 12);
				SubTaskDb.addSubtask(uuid, TaskDb.getTaskId("MultiTestTask"));
				int id = SubTaskDb.getSubTaskId(uuid);
			//	System.out.println("Subtask Id: " + id);
				//BinarySubTask bst = new BinarySubTask(id,0.7,0, numPeople);
				//id conf num labels max options
				MultiValueSubTaskBuilder b = new MultiValueSubTaskBuilder(
						id, 0.7, options, numPeople);
				b.options(options);
				SubTask mst = b.build();
				AnnotatorSubTaskAnswer asta = new AnnotatorSubTaskAnswer(mst.getId(), mst, new MultiTestData(rand.nextInt(options + 1), options));
				answers.add(asta);
			}
			
			//Give all the annotators the answers
			for(int i = 0; i < numPeople; i++){
				annotators[i].setTasks(answers);
			}
			System.out.println("Given annotators answers");
			
			MultiValueSubTask t;
			for(int i = 0; i < numTasks; i++){
				for(int j = 0; j < numPeople; j++){
					//System.out.println("Person " + (j + 1) + " answering task " + i);
				    t = (MultiValueSubTask) SubTaskDb.getSequentialSubTask(TaskDb.getTaskId("MultiTestTask"), annotators[j].bee.getId());
				    if(t == null){
				    	break;
				    }
			//	    System.out.println("Sending in task " + t.getId());
					annotators[j].answerTask(t);
				}
			//	System.out.println();
			//	System.out.println("Task " + i + " done.");
				if(i == (numTasks - 1)){
					printAnnotators();					
				}

			}
	
		}	
		
	}
	
	protected void printAnnotators(){
		System.out.println("----------Calculating Annotator Rates-----------------");
		System.out.println("Id |    TPR    |    TNR    |    TPRE    |    TNRE    ");
			for(int i = 0; i < numPeople; i++){
				AnnotatorModel annotator = annotators[i];
				System.out.print(annotator.getBee().getId() +" | " + annotator.getBinaryBehaviour().getTruePosRate() + " | " + annotator.getBinaryBehaviour().getTrueNegRate() + " | " );
				BinaryAccuracy binAccuracy = CrowdDb.getBinaryAccuracy(annotator.getBee().getId());
				System.out.print(binAccuracy.getTruePositive() +" | "+ binAccuracy.getTrueNegative());
				System.out.println("");
			}
		System.out.println("------------------------------------------------------");
	}
	
	protected long getDate(){
		long ret        = 0           ;
		String str_date = "11-June-15";
	    DateFormat formatter ; 
	    Date date ; 
	    formatter = new SimpleDateFormat("dd-MMM-yy");
		try {
			date = (Date)formatter.parse(str_date);
			ret = date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return ret;
	}
}