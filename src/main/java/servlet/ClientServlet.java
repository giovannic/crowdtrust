package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import crowdtrust.Client;

/**
 * Servlet implementation class ClientServlet
 */
@WebServlet("/ClientServlet")
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/HTML");
	    PrintWriter out = response.getWriter();
	    
	    out.print("<html>\n");
	    out.print("no webpage here \n");
	    out.print("</html>\n");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			long session = Long.parseLong(request.getParameter("session"));
			ObjectMapper mapper = new ObjectMapper();
			String taskJSON = request.getParameter("task");
			//test cases
			taskJSON = "{ \"name\" : \"test\", \"password\" : \"some encrypted password\"}";
			
			Client t = mapper.readValue(taskJSON, Client.class);
			
			response.setContentType("text/plain");
		    PrintWriter out = response.getWriter();
		    
		    out.print("bonjour monde\n");
		    out.print("new task made " + t.getName() + "\n");
		} catch (Exception e){
			System.out.print("argument error - TODO - separate out exceptions");
			e.printStackTrace();
		}
	}

}
