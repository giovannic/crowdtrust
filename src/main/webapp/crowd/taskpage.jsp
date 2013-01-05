<!DOCTYPE HTML PUBLIC>
<%@ page import="java.util.List" %>
<%@ page import="db.TaskDb" %>
<%@ page import="crowdtrust.Task;" %>
<html>
<head>
	<title>
		CrowdTrust - Tasks
	</title>
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
		</tr>
		<%
			}
		%>
	</table>
</body>
