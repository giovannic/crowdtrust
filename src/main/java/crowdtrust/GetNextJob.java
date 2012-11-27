package crowdtrust;

import db.GetNextJobDB;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GetNextJob extends HttpServlet {

protected void doPost(HttpServletRequest request, HttpServletResponse response)
                 throws ServletException, IOException {
                 
            // if not logged in sent to log in
    if(!request.isRequestedSessionIdValid()) {
        response.sendRedirect("/login.html");
    }  
    
    HttpSession session = request.getSession();
    int account_id = session.getAttribute("account_id");           
                 
                                                                              
    int subtask = GetNextJobDB.getNextJob(account_id);     
                                        
  }                

}

