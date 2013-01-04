package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.DbAdaptor;

	public class CreateTaskServlet extends HttpServlet {
	
		private static final long serialVersionUID = -2917901106721177733L;
		private static final String TASKS_DIRECTORY = "/vol/project/2012/362/g1236218/TaskFiles/";

		protected void doPost(HttpServletRequest request,
	              HttpServletResponse response) throws IOException {
			
			response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        
	        //validate user credentials
	        HttpSession session = request.getSession();
	        if (session == null) {
	        	//TODO: test this
	        	response.sendRedirect("/index.html");
	        	return;
	        }
	        
	        int accountID = Integer.parseInt((String) session.getAttribute("account_id"));
	        Connection connection;
	        try {
				connection = DbAdaptor.connect();
			} //catch (ClassNotFoundException | SQLException e1) {
	        catch (Exception e1){
				// TODO Auto-generated catch block
	        	out.println("db connection failed");
				e1.printStackTrace();
				return;
			}
	        
	        //validate user, add task to db, maked task directory
	        String task = request.getParameter("name");
	        long expiryTime = Long.parseLong(request.getParameter("expiry"));
	        long currentTime = (new Date()).getTime();
			PreparedStatement insertTask;
	        try {
	        	insertTask = connection.prepareStatement("INSERT INTO tasks VALUES(DEFAULT,?,?,?,?,?,?,?");
				insertTask.setInt(1, accountID);
				insertTask.setString(2, task);
				insertTask.setString(3, request.getParameter("question"));
				try {
					insertTask.setDouble(4, Double.parseDouble(request.getParameter("accuracy")));
					insertTask.setInt(5, Integer.parseInt(request.getParameter("type")));
				} catch (NumberFormatException e) {
					out.println("accuracy or type not an integer");
					return;
				}
				insertTask.setTimestamp(6, new Timestamp(expiryTime));
				insertTask.setTimestamp(7, new Timestamp(currentTime));
				insertTask.execute();
			} catch (SQLException e) {
				e.printStackTrace();
				out.println("error adding task to database, sorry!");
				return;
			}
	        File taskFolder = new File(TASKS_DIRECTORY + accountID + "/" + task);
	        if(taskFolder.isDirectory()) {
	        	try {
					insertTask.cancel();
				} catch (SQLException e) {
					e.printStackTrace();
					out.println("error rolling back transaction");
					return;
				}	        	
	        } else {
	        	taskFolder.mkdirs();
	        }
        	response.sendRedirect("/upload.jsp");	        
	}
}
