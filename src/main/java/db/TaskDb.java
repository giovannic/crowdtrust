package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import crowdtrust.BinaryTask;
import crowdtrust.Task;

public class TaskDb {
	
	public static boolean addTask(String name, String question, int accuracy, int type){
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO tasks (name, question, type, accuracy)\n");
		sql.append("VALUES(?, ?, ?, ?)");
		Connection c;
		try {
			c = DbAdaptor.connect();
		}
		catch (ClassNotFoundException e) {
			System.err.println("Error connecting to DB on add Task: PSQL driver not present");
		  	return false;
		} catch (SQLException e) {
		  	System.err.println("SQL Error on add Task");
		  	return false;
		}
		try {
		    PreparedStatement preparedStatement = c.prepareStatement(sql.toString());
		    preparedStatement.setString(1, name);
		    preparedStatement.setString(2, question);
		    preparedStatement.setInt(3, type);
		    preparedStatement.setInt(4, accuracy);
		    preparedStatement.execute();
		    preparedStatement.close();
		    c.close();
		}       
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}	      
		return true;
	}
	
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

	public static Task map(ResultSet resultSet) {
		Task thisTask = null;
		try {
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String question = resultSet.getString("question");
				int type = resultSet.getInt("type");
				int accuracy = resultSet.getInt("accuracy");
				switch(type) {
				case 1:
					thisTask = new BinaryTask(id, name, question, accuracy);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return thisTask;
	}

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
	
	public List<String> getTasksForCrowdId(int id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT task.name FROM task ");
		sql.append("WHERE EXISTS (SELECT * FROM account WHERE ? = id AND expert ");
		sql.append("OR task.ex_time + task.date_created < NOW()");
		PreparedStatement preparedStatement;
		try {
			preparedStatement = DbAdaptor.connect().prepareStatement(sql.toString());
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			List<String> tasks = new ArrayList<String>();
			while(resultSet.next()) {
				String taskName = resultSet.getString("task.name");
				tasks.add(taskName);
			}
			return tasks;
		}
	    catch (ClassNotFoundException e) {
	    	System.err.println("Error connecting to DB on get tasks for id: PSQL driver not present");
	      	return null;
	    } catch (SQLException e) {
	      	System.err.println("SQL Error on get tasks for id");
	      	return null;
	    }
		
	}
	
}
