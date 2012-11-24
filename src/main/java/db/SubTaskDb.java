package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		      catch (SQLException e) {
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
		      return null;
		    }
            return TaskDb.map(resultSet);
	      }       
	      catch (SQLException e) {
	          return null;
	      }
	}

}
