package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import crowdtrust.AnnotationType;
import crowdtrust.InputType;
import crowdtrust.MediaType;

import db.TaskDb;

	public class CreateTaskServlet extends HttpServlet {
	
		private static final long serialVersionUID = -2917901106721177733L;
		private static final String TASKS_DIRECTORY = "/vol/project/2012/362/g1236218/TaskFiles/";

		protected void doPost(HttpServletRequest request,
	              HttpServletResponse response) throws IOException {
			
			response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        
	        //validate user credentials
	        HttpSession session = request.getSession();
	        if (session == null||!request.isRequestedSessionIdValid()) {
	        	response.sendRedirect("/");
	        	return;
	        }
	        int accountID = (Integer) session.getAttribute("account_id");
	        
	        //validate user, add task to db, maked task directory
	        String name = request.getParameter("name");
	        float accuracy;
	        int max_labels;
	        int num_answers = 0;
//	        int dimensions = 0;
	        //slider 1 dim, coord 2dim, bounding 4 dim, 
	        double step = 0;
	        MediaType media_type;
	        AnnotationType annotation_type;
	        InputType input_type;
			try {
				accuracy = Float.parseFloat(request.getParameter("accuracy"));
				max_labels = Integer.parseInt(request.getParameter("max_labels"));
				media_type = MediaType.valueOf(request.getParameter("media_type"));
				annotation_type = AnnotationType.valueOf(request.getParameter("annotation_type"));
				if(annotation_type.equals(AnnotationType.CONTINUOUS)) {
//					dimensions = Integer.parseInt(request.getParameter("dimensions"));
//					step = Double.parseDouble(request.getParameter("step"));
				} else if(annotation_type.equals(AnnotationType.BINARY)) {
					num_answers = 2;
				} else {
					num_answers = Integer.parseInt(request.getParameter("num_answers"));
				}
				input_type = InputType.valueOf(request.getParameter("input_type"));
			} catch (NumberFormatException e) {
				printAndRedirect(out, "invalid input");
				e.printStackTrace();
				return;
			}
			long expiry;
			try {
				expiry = getLongDate(request);
			} catch (ParseException e) {	
				printAndRedirect(out, "invalid date format");	
		        return;
			}
			List<String> answers = new LinkedList<String>();
			for(int i = 1 ; i <= num_answers ; i++) {
				String answer = request.getParameter("answer" + i);
				answers.add(answer);
			}
//			List<String> mins = new LinkedList<String>();
//			for(int i = 1 ; i <= dimensions ; i++) {
//				String answer = request.getParameter("min" + i);
//				answers.add(answer);
//			}
//			List<String> maxes = new LinkedList<String>();
//			for(int i = 1 ; i <= dimensions ; i++) {
//				String answer = request.getParameter("max" + i);
//				answers.add(answer);
//			}
			List<String> mins = new LinkedList<String>();
//			mins.add(request.getParameter("min"));
			mins.add("0");
			List<String> maxes = new LinkedList<String>();
//			maxes.add(request.getParameter("max"));
			maxes.add("0");
			int tid = TaskDb.addTask(accountID, name, request.getParameter("question"), 
					accuracy, media_type, annotation_type, input_type, max_labels, expiry,
					answers, mins, maxes, step);
	        if( tid > 0) {
	            File taskFolder = new File(TASKS_DIRECTORY + "/" + tid);
	        	taskFolder.mkdirs();
	        	response.sendRedirect("/client/upload.jsp");	        
	        }
	}
		

	private void printAndRedirect(PrintWriter out, String string) {
        out.println("<html>");
        out.println("<head>");			
    	out.println("<meta http-equiv=\"Refresh\" content=\"3\"; url=\"addtask.jsp\">");
        out.println("</head>");
        out.println("<body>");
        out.println(string + ", returning to add task page");
        out.println("</body>");
        out.println("</html>");
	}


	private long getLongDate(HttpServletRequest request) throws ParseException{
		String expiryDateStr = request.getParameter("day") + request.getParameter("month") + request.getParameter("year");
		Date expiryDate = new SimpleDateFormat("ddMMyyyy").parse(expiryDateStr);
		return expiryDate.getTime();
	}
}
