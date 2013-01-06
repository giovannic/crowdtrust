package servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import db.LoginDb;

import java.util.Properties;

import java.io.IOException;
import java.io.PrintWriter;

import web.*;

public class LoginServlet extends HttpServlet {

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
                 throws ServletException, IOException {
    if(request.isRequestedSessionIdValid()) {
      makeLobby(response, request);
    }  
    String username = request.getParameter("username");
    String password = request.getParameter("password");
		System.out.println(username);
		System.out.println(password);
    int id = LoginDb.checkUserDetails(username, password);
    if(!request.isRequestedSessionIdValid() && id > 0) {
      HttpSession session = request.getSession(true);
      session.setMaxInactiveInterval(1200);
      session.setAttribute("account_id", id);
      session.setAttribute("account_name", username);
			makeLobby(response, request);
			return;
    }
    response.sendRedirect("/index.jsp"); 
  }
  
  private void makeLobby(HttpServletResponse response, HttpServletRequest request){
	  PrintWriter out;
	try {
			out = response.getWriter();
			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("account_name");
			Lobby userLobby = new Lobby(username, request.getContextPath());
	    userLobby.addClientTable();
	    userLobby.addCrowdTable(request);
	    out.print(userLobby.generate());
	  } catch (IOException e) {
			e.printStackTrace();
	  }
  }
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
	  doPost(request, response);
  }
  
}
