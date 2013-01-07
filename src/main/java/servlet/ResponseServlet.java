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
			id = (Integer) s.getAttribute("account_id");
		}
		
		if (id < 1){
			response.sendRedirect("/login.html");
		} else {
			Response r = null;
			SubTask subtask = null;
			int type = Integer.parseInt(request.getParameter("annotation_type"));
			int subTaskId = Integer.parseInt(request.getParameter("sid"));
			//TODO 
			switch(type){
			case 1:
				if (request.getParameter("response").equals(request.getParameter("answer1")))
					r = new BinaryR(true);
				else
					r = new BinaryR(false);
				subtask = db.SubTaskDb.getBinarySubTask(subTaskId);
				break;
			case 2:
				int s = Integer.parseInt(request.getParameter("selection"));
				r = new MultiValueR(s);
				subtask = db.SubTaskDb.getMultiValueSubtask(subTaskId);
				break;
			case 3:
				break;
			}
			
			subtask.addResponse(new Bee(id), r);
			response.sendRedirect("/crowd/complete_task.jsp");
		}
	}

}
