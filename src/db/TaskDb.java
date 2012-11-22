package db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

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
	
	public static Collection<crowdtrust.Task> getTask(String name){
		StringBuilder sql = new StringBuilder();
	      sql.append("SELECT * FROM accounts");
	      sql.append("WHERE name = ?");
	      try {
	        PreparedStatement preparedStatement = DbAdaptor.connect().prepareStatement(sql.toString());
	        preparedStatement.setString(1, name);
	        preparedStatement.execute();
	      }       
	      catch (SQLException e) {
	          return null;
	      }
	      return null;//change to list
	}
}
