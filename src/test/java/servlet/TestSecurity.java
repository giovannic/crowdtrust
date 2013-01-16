package servlet;

import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import crowdtrust.AnnotationType;
import crowdtrust.Bee;
import crowdtrust.BinaryResponse;
import crowdtrust.InputType;
import crowdtrust.MediaType;
import crowdtrust.Result;
import crowdtrust.SubTask;

import db.DbInitialiser;
import db.RegisterDb;
import db.SubTaskDb;
import db.TaskDb;
import junit.framework.TestCase;

public class TestSecurity extends TestCase {


    public void before() throws ClassNotFoundException, SQLException {
            DbInitialiser.init();
    }

    public TestSecurity(String name) {
            super(name);
    }

    public void testUserRequestUnAuthTaskId() {
    	DbInitialiser.init();
            //add users 1 and 2
    	RegisterDb.addUser("test", "test1", "test", false);
    	RegisterDb.addUser("test", "test2", "test", true);
    	
            //add task
    	List<String> answerList = new LinkedList<String>();
    	answerList.add("ans1");
    	answerList.add("ans2");
		TaskDb.addTask(1, "testtask", "test", (float) 0.8, MediaType.IMAGE, AnnotationType.BINARY, InputType.RADIO, 10, 1387934723, answerList , new LinkedList<String>(), new LinkedList<String>(), 0);
            //add subtask
		SubTaskDb.addSubtask("testfile.jpg", 1);
		SubTask subtask = SubTaskDb.getSubtask(1);
		subtask.addResponse(new Bee(2), new BinaryResponse(true));
            //send in false acct id
          Collection<Result> results = SubTaskDb.getResults(1,2);
          assertTrue(results == null || results.isEmpty());

    }

    public void testUserSendUnAuthResponse() {
    	DbInitialiser.init();
    	RegisterDb.addUser("test", "test1", "test", false);
    	RegisterDb.addUser("test", "test2", "test", true);
    	
            //add task
    	List<String> answerList = new LinkedList<String>();
    	answerList.add("ans1");
    	answerList.add("ans2");
		TaskDb.addTask(1, "testtask", "test", (float) 0.8, MediaType.IMAGE, AnnotationType.BINARY, InputType.RADIO, 10, 1387934723, answerList , new LinkedList<String>(), new LinkedList<String>(), 0);
            //add subtask
		SubTaskDb.addSubtask("testfile2.jpg", 1);
		SubTask subtask = SubTaskDb.getSubtask(1);
		assertTrue(subtask.addResponse(new Bee(2), new BinaryResponse(true)));
		assertFalse(subtask.addResponse(new Bee(2), new BinaryResponse(true)));
	}

}

