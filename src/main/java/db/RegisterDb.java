package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.security.MessageDigest;
import java.util.Properties;

public class RegisterDb {
	

  public static boolean addUser(String email, String username, String password, String client, String crowd) {
    byte type = getAccountType(client, crowd);
    StringBuilder sql = new StringBuilder();
    sql.append("INSERT INTO accounts (email, username, password, type) ");
    sql.append("VALUES(?, ?, CAST(? AS bytea), ?)");
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
      preparedStatement.setBytes(3, sha256(password));
      preparedStatement.setByte(4, type);
      preparedStatement.execute();
    }
    catch (SQLException e) {
      return false;
    }
    return true;   
  }

  private static byte[] sha256(String password) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] hash = digest.digest(password.getBytes("UTF-8"));
      return hash;
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private static byte getAccountType(String client, String crowd) {
    int type = 0;
    if(client != null && client.equalsIgnoreCase("on")) {
      type = type ^ 4;
    }
    if(crowd != null && crowd.equalsIgnoreCase("on")) {
      type = type ^ 2;
    }
    return (byte) type;
  } 

}
