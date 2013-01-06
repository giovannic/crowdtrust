package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.TaskDb;

	public class CreateTaskServlet extends HttpServlet {
	
		private static final long serialVersionUID = -2917901106721177733L;

		protected void doPost(HttpServletRequest request,
	              HttpServletResponse response) throws IOException {
			
			response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        
	        //validate user credentials
	        HttpSession session = request.getSession();
	        if (session == null) {
	        	response.sendRedirect("/index.jsp");
	        	return;
	        }
	        int accountID = (Integer) session.getAttribute("account_id");
	        
	        //validate user, add task to db, maked task directory
	        String task = request.getParameter("name");
	        double accuracy;
	        int type;
			try {
				accuracy = Double.parseDouble(request.getParameter("accuracy"));
				type = Integer.parseInt(request.getParameter("type"));
			} catch (NumberFormatException e) {
				out.println("accuracy or type not an integer");
				return;
			}
	        long expiryTime = Long.parseLong(request.getParameter("expiry"));
	        if( !TaskDb.addTask(accountID, task, request.getParameter("question"), accuracy, type, expiryTime))
	        	return;
        	response.sendRedirect("/upload.jsp");	        
	}
}
