<%@ page import="java.util.List" %>
<%@ page import="db.TaskDb" %>
<<<<<<< HEAD
<%@ page import="crowdtrust.Task;" %>
=======
<%@ page import="crowdtrust.Task" %>
>>>>>>> d6a2e1ea5e3551688c2c0c62d96fa1559f69b0c3
<html>
<body>

<h1>Tasks</h1>

	<%
		int id = (Integer) session.getAttribute("account_id");
		List<Task> tasks = TaskDb.getTasksForClientId(id);
	%>

	<table>
		<tr>
			<th>Task Name</th>
			<th>Question</th>
			<th>View Subtasks</th>
		</tr>

		<%
			for(Task task : tasks) {
		%>
		<tr>
			<td><%=task.getName()%></td>
			<td><%=task.getQuestion()%></td>
			<td><form action="/client/subtasks.jsp" method=post>
			  <input type="hidden" name="task_id" value=<%=task.getId()%> />
			  <input type="submit" value="View Task" />
			</form></td>
		</tr>
		<%
			}
		%>
	</table>


</body>
</html>
