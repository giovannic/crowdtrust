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
      HttpSession session = request.getSession();
      session.setMaxInactiveInterval(1200);
      //session.setAttribute("account_id", resultSet.getInt("id"));
    }
    Lobby userLobby = new Lobby("test"); //TODO
    userLobby.addClientTable();
    PrintWriter out = response.getWriter();
    out.print(userLobby.generate());
    out.print("some kinda schpiel");
  }

  /*private byte[] sha256(String password) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] hash = digest.digest(password.getBytes("UTF-8"));
      return hash;
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }*/
}
