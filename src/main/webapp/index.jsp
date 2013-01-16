<!DOCTYPE html>
<%@ page import="java.util.List" %>
<%@ page import="db.SubTaskDb" %>
<html>

	<head>

		<title>CrowdTrust</title>
		
	</head>
	
	<body>


		<div id="content">
			<h2>Recent tasks added</h2>
			<div id="task_images">
    <%
    //retrieve most recent images, display them
	    String TASKS_DIRECTORY = "http://www.doc.ic.ac.uk/project/2012/362/g1236218/TaskFiles/";
		  List<String> recentImageTasks = SubTaskDb.getImageSubtasks();
		  for(int i = 0; i < recentImageTasks.size() ; i++ ) { %>
            <img src=<%=(TASKS_DIRECTORY + recentImageTasks.get(i))%> />
			  <%}%>
			</div>
		</div>
		
		
	</body>


</html>
