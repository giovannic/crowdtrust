<%@ page import="java.util.List" %>
<%@ page import="db.SubTaskDb" %>
<%@ page import="crowdtrust.Task;" %>
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
			<td><button onclick="window.location.href='subtasks.jsp'">View Subtasks</button></td>
		</tr>
		<%
			}
		%>
	</table>


</body>
</html>
