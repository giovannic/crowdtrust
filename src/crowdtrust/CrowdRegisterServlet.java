package crowdtrust;

import javax.servlet.http.HttpServlet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class CrowdRegisterServlet extends HttpServlet {

  private Connection connection = null;

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
                 throws ServletException, IOException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String email = request.getParameter("email");
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
      String sql = "INSERT INTO users VALUES(?, digest(?, sha256), ?)";
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, username);
      preparedStatement.setString(2, password);
      preparedStatement.setString(3, email);
      resultSet = preparedStatement.executeQuery();
      response.sendRedirect("login.html");
    }
  }

  private boolean checkAccountExists(String field, String data) {
    String sql = "SELECT account_id FROM accounts WHERE " + field " = ?";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setString(1, data);
    ResultSet resultSet = preparedStatement.executeQuery();
    if(resultSet.next()) {
      return true;
    }
    return false;  
  }
}
