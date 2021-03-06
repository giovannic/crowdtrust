package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import crowdtrust.*;

public class TaskDb {
	
  /* adds a new task to the database */
  public static int addTask(int accountID, String name, String question, float accuracy, 
		MediaType media_type, AnnotationType annotation_type, InputType input_type, int max_labels, long expiryTime, 
		List<String> answerList, List<String> mins, List<String> maxes, double step){
		Connection c;
		try {
			c = DbAdaptor.connect();
		}
		catch (ClassNotFoundException e) {
			System.err.println("Error connecting to DB on add Task: PSQL driver not present");
		  	return -1;
		} catch (SQLException e) {
		  	System.err.println("SQL Error on add Task");
		  	return -1;
		}
		String answerChoice = "";
		for (String thisChoice : answerList) {
			answerChoice += thisChoice + "/";
		}
        long currentTime = (new Date()).getTime();
        int tid;
		PreparedStatement insertTask;
        try {
        	insertTask = c.prepareStatement("INSERT INTO tasks VALUES(DEFAULT,?,?,?,?,?,?,?,?,?,?,?) RETURNING id");
			insertTask.setInt(1, accountID);
			insertTask.setString(2, name);
			insertTask.setString(3, question);
			insertTask.setFloat(4, accuracy);
			insertTask.setInt(5, media_type.ordinal());
			insertTask.setInt(6, annotation_type.ordinal());
			insertTask.setInt(7, input_type.ordinal());
			insertTask.setString(8, answerChoice);
			insertTask.setInt(9, max_labels);
			insertTask.setTimestamp(10, new Timestamp(expiryTime));
			insertTask.setTimestamp(11, new Timestamp(currentTime));
			insertTask.execute();
			ResultSet rs = insertTask.getResultSet();
			rs.next();
			tid = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
        if( mins.size() != 0) {
	        try {
	        	String minStr = StringUtils.join(mins, "/");
	        	String maxStr = StringUtils.join(maxes, "/");
	        	insertTask = c.prepareStatement("INSERT INTO ranged VALUES(?,?,?,?)");
	        	insertTask.setInt(1,tid);
	        	insertTask.setString(2,minStr);
	        	insertTask.setString(3,maxStr);
	        	insertTask.setDouble(4, step);
	        	insertTask.execute();
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        }
        }
        return tid;
	}

	/*public static int getSubTaskId(String name){
 		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id FROM subtasks\n");
		sql.append("WHERE name = ?");
 		PreparedStatement preparedStatement;
		try {
			preparedStatement = DbAdaptor.connect().prepareStatement(sql.toString());
		}
		catch (ClassNotFoundException e) {
		  	System.err.println("Error connecting to DB on get Task: PSQL driver not present");
		  	return -1;
		} catch (SQLException e) {
		  	System.err.println("SQL Error on get Task");
		  	return -1;
		}
		try {
		    preparedStatement.setString(1, name);
		    ResultSet resultSet = preparedStatement.executeQuery();
	    	resultSet.next();
		    return resultSet.getInt(1);
		} catch (SQLException e) {
		  	System.err.println("SELECT task query invalid");
		  	return -1;

		}
	}*/

  /* gets a task from the database given the task name */
	public static Task getTask(String name){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tasks\n");
		sql.append("WHERE name = ?");
		PreparedStatement preparedStatement;
		try {
			preparedStatement = DbAdaptor.connect().prepareStatement(sql.toString());
		}
		catch (ClassNotFoundException e) {
		  	System.err.println("Error connecting to DB on get Task: PSQL driver not present");
		  	return null;
		} catch (SQLException e) {
		  	System.err.println("SQL Error on get Task");
		  	return null;
		}
		try {
		    preparedStatement.setString(1, name);
		    ResultSet resultSet = preparedStatement.executeQuery();
		    return TaskDb.map(resultSet);
		} catch (SQLException e) {
		  	System.err.println("SELECT task query invalid");
		  	return null;
		}
	}

  /* gets the task id of a task given the task name */
	public static int getTaskId(String name){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id FROM tasks\n");
		sql.append("WHERE name = ?");
		PreparedStatement preparedStatement;
		try {
			preparedStatement = DbAdaptor.connect().prepareStatement(sql.toString());
		}
		catch (ClassNotFoundException e) {
		  	System.err.println("Error connecting to DB on get Task: PSQL driver not present");
		  	return -1;
		} catch (SQLException e) {
		  	System.err.println("SQL Error on get Task");
		  	return -1;
		}
		try {
		    preparedStatement.setString(1, name);
		    ResultSet resultSet = preparedStatement.executeQuery();
		    resultSet.next();
		    return resultSet.getInt(1);
		} catch (SQLException e) {
		  	System.err.println("SELECT task query invalid");
		  	return -1;
		}
	}

  /* checks if the given task and account are present in the database */
	public static boolean isPresent(int taskID, int accountID) {
    	PreparedStatement checkTask;
		try {
			checkTask = DbAdaptor.connect().prepareStatement("SELECT id FROM tasks WHERE id = ? AND submitter = ?");
			checkTask.setInt(1, taskID);
			checkTask.setInt(2, accountID);
	    	checkTask.execute();
	    	ResultSet rs = checkTask.getResultSet();
	    	rs.next();
	    	return taskID == rs.getInt(1);
		} catch (SQLException e) { 
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public static Task map(ResultSet resultSet) {
		Task thisTask = null;
		try {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String question = resultSet.getString("question");
				int media_type = resultSet.getInt("media_type");
				int annotation_type = resultSet.getInt("annotation_type");
				int input_type = resultSet.getInt("input_type");
				String answersString = resultSet.getString("answers");
				String[] answersArr = answersString.split("/");
				List<String> answers = new LinkedList<String>();
				for (int i = 0 ; i < answersArr.length ; i++) {
					answers.add(answersArr[i]);
				}
				int accuracy = resultSet.getInt("accuracy");
				if(annotation_type == 1) {
					thisTask = new BinaryTask(id, name, question, accuracy, media_type, input_type, answers);
				}
				if(annotation_type == 2) {
					thisTask = new MultiValueTask(id, name, question, accuracy, media_type, input_type, answers);
				}							
				if(annotation_type == 3) {
					String minJoined = resultSet.getString("start");
					String[] minArr = minJoined.split("/");
					List<String> mins = new LinkedList<String>();
					for (int i = 0; i < minArr.length ; i++ ) {
						mins.add(minArr[i]);
					}
					String maxJoined = resultSet.getString("finish");
					String[] maxArr = maxJoined.split("/");
					List<String> maxes = new LinkedList<String>();
					for (int i = 0; i < maxArr.length ; i++ ) {
						maxes.add(maxArr[i]);
					}
					double step = resultSet.getInt("p");
					thisTask = new SingleContinuousTask(id, name, question, accuracy, media_type, input_type, mins, maxes, step );
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return thisTask;
	}

  /* check if a task if finished or not */
	public static boolean checkFinished(int id) throws SQLException {
		StringBuilder sql = new StringBuilder();
	    sql.append("SELECT * FROM tasks JOIN subtasks ON tasks.id = subtasks.task ");
	    sql.append("WHERE tasks.id = ?");
	    PreparedStatement preparedStatement;
	    try {
	    preparedStatement = DbAdaptor.connect().prepareStatement(sql.toString());
	    }
	    catch (ClassNotFoundException e) {
	    	System.err.println("Error connecting to DB on check finished: PSQL driver not present");
	      	return true;
	    } catch (SQLException e) {
	      	System.err.println("SQL Error on check finished");
	      	return true;
	    }
	    preparedStatement.setString(1, Integer.toString(id));
	    ResultSet resultSet = preparedStatement.executeQuery();
	    if(!resultSet.next() || !resultSet.isLast()) {
		      //task does not exist, grave error TODO log it
		  return true;
		}
	    return false;    

	}

  /* gets task for a crowd member */
	public static List<Task> getTasksForCrowdId(int id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tasks LEFT JOIN ranged ");
		sql.append("ON tasks.id = ranged.task");
		PreparedStatement preparedStatement;
		List<Task> tasks = new ArrayList<Task>();
		try {
			preparedStatement = DbAdaptor.connect().prepareStatement(sql.toString());
//			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				tasks.add(map(resultSet));
			}
		}
	    catch (ClassNotFoundException e) {
	    	System.err.println("Error connecting to DB on get tasks for id: PSQL driver not present");
	    } catch (SQLException e) {
	      	System.err.println("SQL Error on get tasks for crowdid");
e.printStackTrace();
	    }
		return tasks;
	}

  /* gets the uploaded tasks for a given user */
	public static List<Task> getTasksForClientId(int id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tasks  LEFT JOIN ranged ON tasks.id = ranged.task WHERE submitter = ?");
		List<Task> tasks = new ArrayList<Task>();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = DbAdaptor.connect().prepareStatement(sql.toString());
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				tasks.add(map(resultSet));
			}
		}
		catch (ClassNotFoundException e) {
			System.err.println("Error connecting to DB on get Task: PSQL driver not present");
		} catch (SQLException e) {
			System.err.println("SQL Error on get Tasks for clientid");
e.printStackTrace();
		}
		return tasks;
	}
	
}
