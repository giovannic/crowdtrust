<!DOCTYPE html>
<%@ page import="java.util.List" %>
<%@ page import="db.TaskDb" %>
<%@ page import="crowdtrust.Task;" %>
<html>


	<head>
		<title>Crowd: TaskList</title>
	</head>
	
	<body>
		

	<%
		int id = (Integer) session.getAttribute("account_id");
		List<Task> tasks = TaskDb.getTasksForCrowdId(id);
	%>

	<table>
		<tr>
			<th>Task Name</th>
			<th>Question</th>
		</tr>

		<%
			for(Task task : tasks) {
		%>
		<tr>
			<td><%=task.getName()%></td>
			<td><%=task.getQuestion()%></td>
			<td><form action="/crowd/add_params.jsp" method=post>
			  <input type="submit" value="Complete Task"/>
			  <input type="hidden" name="taskID" value=<%=task.getId()%> />
			  <input type="hidden" name="name" value="<%=task.getName()%>" />
			  <input type="hidden" name="question" value="<%=task.getQuestion()%>" />
			  <input type="hidden" name="media_type" value=<%=task.getMediaType()%> />
			  <input type="hidden" name="annotation_type" value=<%=task.getAnnotationType()%> />
			  <input type="hidden" name="input_type" value=<%=task.getInputType()%> />
			  <input type="hidden" name="answers" value="<%=task.getAnswers()%>" />
		  </form></td>
		</tr>
		<%
			}
		%>
	</table>		
	</body>


</html>
