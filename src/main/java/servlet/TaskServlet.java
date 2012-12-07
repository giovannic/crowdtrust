package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.ServletException;

import com.fasterxml.jackson.databind.ObjectMapper;

import crowdtrust.BinaryTask;

//@MultipartConfig
public class TaskServlet extends HttpServlet
{

  private static final long serialVersionUID = 8468005883344084876L;

  protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                            throws ServletException, IOException
  {
	
	try{
		long session = Long.parseLong(request.getParameter("session"));
		ObjectMapper mapper = new ObjectMapper();
		String taskJSON = request.getParameter("task");
		//test cases
		taskJSON = "{ \"name\" : \"test\", \"question\" : \"what's your fave number?\"}";
		
		BinaryTask t = mapper.readValue(taskJSON, BinaryTask.class);
		
		response.setContentType("text/plain");
	    PrintWriter out = response.getWriter();
	    
	    out.print("bonjour monde\n");
	    out.print("new task made " + t.getName() + "\n");
	} catch (Exception e){
		System.out.print("argument error - TODO - separate out exceptions");
		e.printStackTrace();
	}
  }

};
