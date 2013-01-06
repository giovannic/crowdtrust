package servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import db.DbAdaptor;
import db.SubTaskDb;
import db.TaskDb;

public class UploadServlet extends HttpServlet {

	private static final long serialVersionUID = -8279791785237277465L;
	private static final String TASKS_DIRECTORY = "/vol/project/2012/362/g1236218/TaskFiles/";
	
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        //validate user credentials
        HttpSession session = request.getSession();
        if (session == null) {
        	response.sendRedirect("/index.jsp");
        	return;
        }
        
        int accountID = (Integer) session.getAttribute("account_id");
        //Process post parameters
		List<FileItem> items = null;
    	FileItem files = null;
    	String taskDir = "";
    	int taskID = -1;
		try {
			items = (List<FileItem>) new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
    	for( FileItem item : items ) {
        	if(item.isFormField()) {
        		String field = item.getFieldName();
        		if (field.equals("task")) {
            		String task = item.getString();
        			if (task == null) {
        				//output need task name
        			} else {
        				//task is task name - add task to db if not yet inputted
            			        				
        			}
        		}
        		if( field.equals("taskID") ) {
        			taskID = Integer.parseInt(item.getString());
        			taskDir = TASKS_DIRECTORY + taskID + "/";
        		}
        	}
        	//file to be uploaded
        	else {
        		files = item;
        	}
    	}
        //add to db - check task in db, add to subtasks,
    	if (!TaskDb.isPresent(taskID, accountID))
    		return;
    	
        //on upload page retrieve task id, submit task id as a parameter
        //check id and submitter appear in tasks
		
		List<String> filenames = new LinkedList<String>();
		
    	//add to filesystem
		if (files.getName().substring(files.getName().length()-4).toLowerCase().equals(".zip")) {
			ZipInputStream zipIn = new ZipInputStream(files.getInputStream());
			ZipEntry entry = zipIn.getNextEntry();
			while( entry != null ) {
				String filename = entry.getName();
				OutputStream fileOut = new FileOutputStream(taskDir + filename);
				IOUtils.copy(zipIn, fileOut);
				zipIn.closeEntry();
				fileOut.close();
				filenames.add(filename);
				entry = zipIn.getNextEntry();
			}
			zipIn.close();
		} else {
			//not a zip archive
			String filename = files.getName();
			filenames.add(filename);        
	        
	        //adds to filesystem
	        InputStream fileIn = files.getInputStream();
	        OutputStream fileOut = new FileOutputStream(taskDir + filename);
	        IOUtils.copy(fileIn, fileOut);
	        fileIn.close();
	        fileOut.close();
		}
    	
        //add to subtasks
    	for (int i = 0 ; i < filenames.size() ; i++) {
	        String filename = filenames.get(i);
    		if( !SubTaskDb.addSubtask(filename, taskID) ) 
			return; 
    	}		
		
    	out.println("<meta http-equiv=\"Refresh\" content=\"5\"; url=\"/client/upload.jsp\">");
        out.println("<html>");
        out.println("<body>");
        out.println("uploaded to task " + taskDir + "<br>");                	
        out.println("click <a href=/client/addtask.jsp>here</a> to to add tasks");
        out.println("</body>");
        out.println("</html>");
    }
	
}
