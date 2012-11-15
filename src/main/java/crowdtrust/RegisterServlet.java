package crowdtrust;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

import java.util.Properties;

import java.io.IOException;

import java.lang.StringBuilder;

import java.security.MessageDigest;


public class RegisterServlet extends HttpServlet {

  private Connection connection = null;

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
                 throws ServletException, IOException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String cpassword = request.getParameter("cpassword");
    if(!password.equals("cpassword")) {
      return; //passwords do not match. fix this
    }
    String email = request.getParameter("email");
    String client = request.getParameter("client");
    String crowd = request.getParameter("crowd");
    try {
      String url = "jdbc:postgresql://db:5432/g1236218_u";
      Properties properties = new Properties();
      properties.setProperty("user", "g1236218_u");
      properties.setProperty("password", "RLTn4ViKks");
      connection = DriverManager.getConnection(url, properties);
    }
    catch (SQLException e) {
      throw new ServletException(e);
    }
    if (connection != null) {
      /*if(checkAccountExists("username", username)) {
        //username already exists
        return;
      }
      if(checkAccountExists("email", email)) {
        //email already exists
        return;
      }*/
      byte type = (byte) getAccountType(client, crowd);
      StringBuilder sql = new StringBuilder();
      sql.append("INSERT INTO accounts (email, username, password, type) ");
      sql.append("VALUES(?, ?, CAST(? AS bytea), ?)");
      try {
        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, username);
        preparedStatement.setBytes(3, sha256(password));
        preparedStatement.setByte(4, type);
        preparedStatement.execute();
      }
      catch (SQLException e) {
        throw new ServletException(e);
      }
      response.sendRedirect("/login.html");
    }
  }

  private byte[] sha256(String password) {
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

  private int getAccountType(String client, String crowd) {
    int type = 0;
    if(client != null && client.equalsIgnoreCase("on")) {
      type = type ^ 4;
    }
    if(crowd != null && crowd.equalsIgnoreCase("on")) {
      type = type ^ 2;
    }
    return type;
  }

  private boolean checkAccountExists(String field, String data) {
    String sql = "SELECT user_id FROM accounts WHERE " + field + " = ?";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, data);
      ResultSet resultSet = preparedStatement.executeQuery();
      if(resultSet.next()) {
        return true;
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return false;  
  }
}
