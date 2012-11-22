package crowdtrust;

import db.RegisterDb;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

/*import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.Properties;*/

import java.io.IOException;

public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = -6820932460475477607L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
                 throws ServletException, IOException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String cpassword = request.getParameter("cpassword");
    if(!password.equals(cpassword)) {
      return; //passwords do not match. fix this
    }
    String email = request.getParameter("email");
    String client = request.getParameter("client");
    String crowd = request.getParameter("crowd");
    RegisterDb.addUser(email, username, password, client, crowd);
    response.sendRedirect("/login.html");
    /*try {
      String url = "jdbc:postgresql://db:5432/g1236218_u";
      Properties properties = new Properties();
      properties.setProperty("user", "g1236218_u");
      properties.setProperty("password", "RLTn4ViKks");
      connection = DriverManager.getConnection(url, properties);
    }
    catch (SQLException e) {
      throw new ServletException(e);
    }*/
    //if (connection != null) {
      /*if(checkAccountExists("username", username)) {
        //username already exists
        return;
      }
      if(checkAccountExists("email", email)) {
        //email already exists
        return;
      }*/
      
      /*StringBuilder sql = new StringBuilder();
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
      }*/
      
  }
  

  /*private boolean checkAccountExists(String field, String data) {
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
  }*/
}
