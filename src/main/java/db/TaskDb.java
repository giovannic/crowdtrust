package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import crowdtrust.Task;

public class TaskDb {
	
	public static boolean addTask(String name, String question, int accuracy, int type){
		StringBuilder sql = new StringBuilder();
	      sql.append("INSERT INTO accounts (name, question, type, accuracy) ");
	      sql.append("VALUES(?, ?, ?, ?)");
	      try {
	        PreparedStatement preparedStatement = DbAdaptor.connect().prepareStatement(sql.toString());
	        preparedStatement.setString(1, name);
	        preparedStatement.setString(2, question);
	        preparedStatement.setInt(3, type);
	        preparedStatement.setInt(4, accuracy);
	        preparedStatement.execute();
	      }       
	      catch (SQLException e) {
	          return false;
	      }
	      return true;
	}
	
	public static Task getTask(String name){
		StringBuilder sql = new StringBuilder();
	      sql.append("SELECT * FROM accounts");
	      sql.append("WHERE name = ?");
	      try {
	        PreparedStatement preparedStatement = DbAdaptor.connect().prepareStatement(sql.toString());
	        preparedStatement.setString(1, name);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        if(!resultSet.next() || !resultSet.isLast()) {
			      //task does not exist
			      return null;
			    }
	        return TaskDb.map(resultSet);
	      }       
	      catch (SQLException e) {
	          return null;
	      }
	}

	public static Task map(ResultSet resultSet) {
		//TODO ryan is on it
		return null;
	}

	public static boolean checkFinished(int id) throws SQLException {
		StringBuilder sql = new StringBuilder();
	    sql.append("SELECT * FROM tasks JOIN subtasks ON tasks.id = subtasks.task");
	    sql.append("WHERE tasks.id = ?");
	    PreparedStatement preparedStatement = DbAdaptor.connect().prepareStatement(sql.toString());
	    preparedStatement.setString(1, Integer.toString(id));
	    ResultSet resultSet = preparedStatement.executeQuery();
	    if(!resultSet.next() || !resultSet.isLast()) {
		      //task does not exist, grave error TODO log it
		  return true;
		}
	    return false;    

	}
	
	
}
