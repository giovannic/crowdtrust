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


public class CrowdRegisterServlet extends HttpServlet {

  private Connection connection = null;

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
                 throws ServletException, IOException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String email = request.getParameter("email");
    boolean client = request.getParameter("client");
    boolean crowd = request.getParameter("crowd");
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
      if(checkAccountExists("username", username)) {
        //username already exists
        return;
      }
      if(checkAccountExists("email", email)) {
        //email already exists
        return;
      }
      int type = getAccountType(client, crowd);
      String sql = "INSERT INTO accounts (email, username, password, type) ";
      sql += "VALUES(?, digest(?, sha256), ?)";
      try {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, username);
        preparedStatement.setString(3, password);
        preparedStatement.setInt(4, type);
        ResultSet resultSet = preparedStatement.executeQuery();
      }
      catch (SQLException e) {
        throw new ServletException(e);
      }
      response.sendRedirect("login.html");
    }
  }

  private int getAccountType(boolean client, boolean crowd) {
    int type;
    if(client && crowd) {
      type = 111;
    }
    else if(client) {
      type = 100;
    }
    else if(crowd) {
      type = 010;
    }
    else {
      type = 000;
    }
    return type;
  }

  private boolean checkAccountExists(String field, String data) {
    String sql = "SELECT account_id FROM accounts WHERE " + field + " = ?";
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
