package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import crowdtrust.Bee;
import crowdtrust.Response;
import crowdtrust.Estimate;
import crowdtrust.Task;

public class SubTaskDb {

	public static boolean close(int id) {
			StringBuilder sql = new StringBuilder();
		      sql.append("UPDATE subtasks (active) ");
		      sql.append("VALUES(FALSE)");
		      try {
		        PreparedStatement preparedStatement = DbAdaptor.connect().prepareStatement(sql.toString());
		        preparedStatement.execute();
		      }
		      catch (ClassNotFoundException e) {
		      	  System.err.println("Error connecting to DB on subtask close: PSQL driver not present");
		      	  return false;
		        } catch (SQLException e) {
		      	  System.err.println("SQL Error on subtask close");
		      	  return false;
		        }
		      return true;
	}

	public static Task getTask(int id) {
		StringBuilder sql = new StringBuilder();
	      sql.append("SELECT * FROM tasks JOIN subtasks ON tasks.id = subtasks.task");
	      sql.append("WHERE subtasks.id = ?");
	      try {
	        PreparedStatement preparedStatement = DbAdaptor.connect().prepareStatement(sql.toString());
	        preparedStatement.setString(1, Integer.toString(id));
	        ResultSet resultSet = preparedStatement.executeQuery();
	        if(!resultSet.next() || !resultSet.isLast()) {
		      //task does not exist, grave error TODO log it
	        	System.err.println("Subtask: " + id + " doesn't exist");
	        	return null;
		    }
            return TaskDb.map(resultSet);
	      }
	      catch (ClassNotFoundException e) {
	      	  System.err.println("Error connecting to DB on get Subtask: PSQL driver not present");
	      	  return null;
	      } catch (SQLException e) {
	      	  System.err.println("SQL Error on get Subtask");
	      	  return null;
	      }
	}
	
	public static List<String> getImageSubtasks() {
		StringBuilder sql = new StringBuilder();
	      sql.append("SELECT tasks.name, subtasks.file_name, tasks.date_created FROM tasks JOIN subtasks ON tasks.id = subtasks.task");
	      sql.append("WHERE subtasks.file_name LIKE '%.jpg' OR subtasks.file_name LIKE '%.png' ORDER BY tasks.date_created");
	      List<String> list = new LinkedList<String>();
	      try {
	        PreparedStatement preparedStatement = DbAdaptor.connect().prepareStatement(sql.toString());
	        ResultSet resultSet = preparedStatement.executeQuery();
	        if(!resultSet.next() || !resultSet.isLast()) {
		      //task does not exist, grave error TODO log it
		    }
	        for (int i = 0 ; !resultSet.isLast() && i < 5 ; i++) {
	        	String subtask = resultSet.getString(2);
	        	String task = resultSet.getString(1);
	        	list.add(task + "/" + subtask);
	        }
	      }
	      catch (ClassNotFoundException e) {
	      	  System.err.println("Error connecting to DB on get Subtask: PSQL driver not present");
	      } catch (SQLException e) {
	      	  System.err.println("SQL Error on get Subtask");
	      }		
	      return list;
	}
	
	public static Map<Bee, Response> getBinaryResponses(int id, Bee[] annotators) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Map<Bee, Response> getMultiValueResponses(int id,
			Bee[] annotators) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Map<Bee, Response> getContinuousResponses(int id,
			Bee[] annotators) {
		// TODO Auto-generated method stub
		return null;
	}

}
