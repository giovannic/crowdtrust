package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.security.MessageDigest;
import java.util.Properties;


public class LoginDb {

  public static int checkUserDetails(String username, String password) {
    String sql = "SELECT id FROM accounts WHERE username = ? AND password = ?";
    try {
      String url = "jdbc:postgresql://db:5432/g1236218_u";
      Properties properties = new Properties();
      properties.setProperty("user", "g1236218_u");
      properties.setProperty("password", "RLTn4ViKks");
      Connection connection = DriverManager.getConnection(url, properties);
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, username);
      preparedStatement.setBytes(2, sha256(password));
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

}
