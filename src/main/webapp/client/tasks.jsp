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
	</table>


</body>
</html>
