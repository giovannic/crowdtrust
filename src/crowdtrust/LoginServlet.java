package crowdtrust;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import java.util.Properties;

import java.io.IOException;

import java.security.MessageDigest;

public class LoginServlet extends HttpServlet {

  private Connection connection = null;

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
                 throws ServletException, IOException {
    if(request.isRequestedSessionIdValid()) {
      response.sendRedirect("/lobby.html");
    }  
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    try {
      String url = "jdbc:postgresql://db:5432/g1236218_u";
      Properties properties = new Properties();
      properties.setProperty("user", "g1236218_u");
      properties.setProperty("password", "RLTn4ViKks");
      connection = DriverManager.getConnection(url, properties);
      if (connection != null) {
        String sql = "SELECT id FROM accounts WHERE username = ? AND password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setBytes(2, sha256(password));
        ResultSet resultSet = preparedStatement.executeQuery();
        if(!resultSet.next() || !resultSet.isLast()) {
          //user does not exist - display error message
          return;
        }
        if(!request.isRequestedSessionIdValid()) {
          HttpSession session = request.getSession();
          session.setMaxInactiveInterval(1200);
          session.setAttribute("account_id", resultSet.getInt("id"));
        }
      }
    }
    catch (SQLException e) {
      throw new ServletException(e);
    }
    response.setHeader("Location", "/lobby.html");
    //response.sendRedirect("/lobby.html");
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
}
