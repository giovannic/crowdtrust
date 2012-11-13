package crowdtrust;

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

/**
 * Servlet implementation class SubTaskServlet
 */
@WebServlet("/SubTaskServlet")
public class SubTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	final ObjectMapper mapper = new ObjectMapper();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubTaskServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		response.setContentType("text/HTML");
	    PrintWriter out = response.getWriter();
	    
	    out.print("<html>\n");
	    out.print("no webpage here \n");
	    out.print("</html>\n");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		/* parser option
		JsonFactory jf = new JsonFactory();
		JsonParser parser = jf.createJsonParser(
				"{\"media\":{\"data\":\"cGF0aC50by5pbWFnZQ==\"}}");
		*/
		
		response.setContentType("text/plain");
	    PrintWriter out = response.getWriter();
		
		try{
			long session = Long.parseLong(request.getParameter("session"));
			
			int taskId = Integer.parseInt(request.getParameter("tid"));
			
			boolean batch = request.getParameterMap().containsKey("subtasks");
			
			SubTask s = null;
			
			if (batch){
				String taskJSON = request.getParameter("subtasks");
				//test cases
				taskJSON = "[{\"html\":\"what colour is this bird\"},{\"html\":\"what colour is this bird 2\"}]";
				ArrayList<SubTask> ss = mapper.readValue(taskJSON, 
						new TypeReference<ArrayList<SubTask>>() { });
				for (SubTask x : ss)
					out.print("new subtasks made " + x.getHtml() + "\n");
			} else {
				String taskJSON = request.getParameter("subtask");
				//test cases
				taskJSON = "{\"html\":\"what colour is this bird\"}";
				s = mapper.readValue(taskJSON, SubTask.class);
				//test incorrect params
				out.print("new subtask made " + s.getHtml() + "\n");
			}
			
		} catch (Exception e){
			System.out.print("argument error - TODO - separate out exceptions");
			e.printStackTrace();
		}
	}

}
