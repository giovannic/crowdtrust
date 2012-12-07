package servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import db.LoginDb;

//import db.LoginDb;

import java.util.Properties;

import java.io.IOException;
import java.io.PrintWriter;

import web.*;

public class LoginServlet extends HttpServlet {

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
                 throws ServletException, IOException {
    if(request.isRequestedSessionIdValid()) {
      response.sendRedirect("/lobby.html");
    }  
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    int id = LoginDb.checkUserDetails(username, password);
    if(!request.isRequestedSessionIdValid() && id > 0) {
    	System.out.println("Hello World");
      HttpSession session = request.getSession();
      session.setMaxInactiveInterval(1200);
      session.setAttribute("account_id", id);
      session.setAttribute("account_name", username);
      Lobby userLobby = new Lobby(username);
      userLobby.addClientTable();
      PrintWriter out = response.getWriter();
      out.print(userLobby.generate());
    }
    if(!request.isRequestedSessionIdValid()) {
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");
	    //int id = LoginDb.checkUserDetails(username, password);
	    int id = 1;
	    if(!request.isRequestedSessionIdValid() && id > 0) {
	      HttpSession session = request.getSession();
	      session.setMaxInactiveInterval(1200);
	      //session.setAttribute("account_id", resultSet.getInt("id"));
	    }
    }
    makeLobby(response, request.getContextPath());
  }
  
  private void makeLobby(HttpServletResponse response, String context){
	  PrintWriter out;
	try {
		out = response.getWriter();
		Lobby userLobby = new Lobby("test", context); //TODO
	    userLobby.addClientTable();
	    userLobby.addCrowdTable();
	    
	    out.print(userLobby.generate());
	  } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
  }
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
	  doPost(request, response);
  }
  
}
