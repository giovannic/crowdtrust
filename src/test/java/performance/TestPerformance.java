package performance;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import crowdtrust.AnnotationType;
import crowdtrust.Bee;
import crowdtrust.BinaryResponse;
import crowdtrust.InputType;
import crowdtrust.MediaType;
import crowdtrust.SubTask;
import db.DbInitialiser;
import db.RegisterDb;
import db.SubTaskDb;
import db.TaskDb;
import junit.framework.TestCase;

public class TestPerformance extends TestCase{
	
	public TestPerformance(String name) {
		super(name);
	}
	
	public void testBatchUploadAccuracies() {
		DbInitialiser.init();
	}
	
	public void testConcurrentResponses() {
    	DbInitialiser.init();
		RegisterDb.addUser("test", "test1", "test", false);
    	for( int i = 2 ; i <= 10 ; i++ )
    	RegisterDb.addUser("test", "test2", "test", true);
    	
            //add task
    	List<String> answerList = new LinkedList<String>();
    	answerList.add("ans1");
    	answerList.add("ans2");
		TaskDb.addTask(1, "testtask", "test", (float) 0.8, MediaType.IMAGE, AnnotationType.BINARY, InputType.RADIO, 10, 1387934723, answerList , new LinkedList<String>(), new LinkedList<String>(), 0);
            //add subtask
		SubTaskDb.addSubtask("testfile2.jpg", 1);
		SubTask subtask = SubTaskDb.getSubtask(1);
		for( int i = 2 ; i <= 10 ; i++ ) {
			new Thread(new Respond(i, subtask)).run();
		}
		assertTrue(SubTaskDb.getResponses(1) == 9);
	}
	
	private class Respond implements Runnable {

		private int accountId;
		private SubTask subtask;
		
		public Respond(int accountId, SubTask subtask) {
			this.accountId = accountId;
			this.subtask = subtask;
		}
		
		@Override
		public void run() {
			subtask.addResponse(new Bee(accountId), new BinaryResponse(new Random().nextBoolean()));
		}
		
	}
}
