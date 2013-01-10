package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import crowdtrust.Bee;
import crowdtrust.BinaryR;
import crowdtrust.MultiValueR;
import crowdtrust.Response;
import crowdtrust.SubTask;

/**
 * Servlet implementation class ResponseServlet
 */
@WebServlet("/ResponseServlet")
public class ResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResponseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = -1;
		if(request.isRequestedSessionIdValid()){
			HttpSession s = request.getSession();
			try{
				id = (Integer) s.getAttribute("account_id");
			}catch(Exception e) {
				response.sendRedirect("/");
			}
		}
		
		if (id < 1){
			response.sendRedirect("/");
		} else {
			Response r = null;
			SubTask subtask = null;
			int type = Integer.parseInt(request.getParameter("annotation_type"));
			int subTaskId = Integer.parseInt(request.getParameter("sid"));
			System.out.println("x1: " + request.getParameter("x1"));
			System.out.println("y1: " + request.getParameter("y1"));
			System.out.println("x2: " + request.getParameter("x2"));
			System.out.println("y2: " + request.getParameter("y2"));
			System.out.println("width: " + request.getParameter("width"));
			System.out.println("height: " + request.getParameter("height"));
			//TODO 
			switch(type){
			case 1:
				int rInt = Integer.parseInt(request.getParameter("response"));
				if (rInt == 0)
					r = new BinaryR(true);
				else
					r = new BinaryR(false);
				subtask = db.SubTaskDb.getSubtask(subTaskId);
				break;
			case 2:
				int s = Integer.parseInt(request.getParameter("response"));
				r = new MultiValueR(s);
				subtask = db.SubTaskDb.getSubtask(subTaskId);
				break;
			case 3:
				
				break;
			}
			
			subtask.addResponse(new Bee(id), r);
			response.sendRedirect("/crowd/complete_task.jsp");
		}
	}

}
