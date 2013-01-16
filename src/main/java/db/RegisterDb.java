package db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.security.MessageDigest;

public class RegisterDb {
	
  /* adds a new user to the database */
  public static boolean addUser(String email, String username, String password, boolean crowd) {
    StringBuilder sql = new StringBuilder();
    sql.append("INSERT INTO accounts (email, username, password, type) ");
    sql.append("VALUES(?, ?, ?, ?)");
    PreparedStatement preparedStatement;
    try {
      preparedStatement = DbAdaptor.connect().prepareStatement(sql.toString());
    }
    catch (ClassNotFoundException e) {
  	  System.err.println("Error connecting to DB on Register: PSQL driver not present");
  	  return false;
    } catch (SQLException e) {
  	  System.err.println("SQL Error on Register");
  	  return false;
    }
    try {
      preparedStatement.setString(1, email);
      preparedStatement.setString(2, username);
      preparedStatement.setString(3, sha256(password));
      preparedStatement.setBoolean(4, crowd);
      preparedStatement.execute();
    }
    catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
    return true;   
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
