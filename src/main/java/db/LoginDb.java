package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.security.MessageDigest;
import java.util.Properties;
import java.lang.StringBuilder;


public class LoginDb {

  public static int checkUserDetails(String username, String password) {
    String sql = "SELECT id FROM accounts WHERE username = ? AND password = ?";
      PreparedStatement preparedStatement;
      try {
    	  preparedStatement = DbAdaptor.connect().prepareStatement(sql);
      } 
      catch (ClassNotFoundException e) {
    	  System.err.println("Error connecting to DB on login: PSQL driver not present");
    	  return 0;
      } catch (SQLException e) {
    	  System.err.println("SQL Error on login");
    	  return 0;
      }
      try {
    	  preparedStatement.setString(1, username);
	      preparedStatement.setString(2, sha256(password));
	      ResultSet resultSet = preparedStatement.executeQuery();
	      if(!resultSet.next() || !resultSet.isLast()) {
	        //user does not exist - display error message
	        return 0;
	      }
	      int id = resultSet.getInt("id");
	      return id;
      }
      catch (SQLException e) {
          return 0;
      }
  }

  private static String sha256(String password) {
    try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.update(password.getBytes());
			byte bytes[] = digest.digest();
			StringBuffer buffer = new StringBuffer();
			for(int i = 0; i < bytes.length; i++){
				buffer.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
      return buffer.toString();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

}
