package servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import db.LoginDb;

import java.sql.SQLException;
import java.util.Properties;

import java.io.IOException;
import java.io.PrintWriter;

import web.*;

public class LoginServlet extends HttpServlet {

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
                 throws ServletException, IOException {
	  

		response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
	  
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    int id = LoginDb.checkUserDetails(username, password);
    if( id > 0) {
		boolean isCrowd;
        try {
        	isCrowd = LoginDb.isUserCrowd(id);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			response.sendRedirect("/index.jsp?login=false");
			return;
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("/index.jsp?login=false");
			return;
		}
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(1200);
		session.setAttribute("account_id", id);
		session.setAttribute("account_name", username);
        if( isCrowd ) {
    		response.sendRedirect("/crowd/profile.jsp");
        } else {
        	response.sendRedirect("/client/profile.jsp");
        }
        return;
    }
	
	out.println("<meta http-equiv=\"Refresh\" content=\"5\"; url=\"/\">");
    out.println("<html>");
    out.println("<body>");
    out.println("bad login! delete cookies and try again. going back to homepage in 5 seconds");                	
    out.println("</body>");
    out.println("</html>");
    
  }
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
	  doPost(request, response);
  }
  
}
