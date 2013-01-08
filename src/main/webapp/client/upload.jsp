<%@ page import="crowdtrust.Task" %>
<%@ page import="db.TaskDb" %>
<%@ page import="java.util.List" %>

<html>
<body>

<h1>FILE UPLOAD</h1>



<form action="/servlet/upload" method="post" enctype="multipart/form-data">
  <% 
		int id = (Integer) session.getAttribute("account_id");
		List<Task> tasks = TaskDb.getTasksForClientId(id);
  %>
  <select name="taskID">
  <%
    for( Task task : tasks ) {
      String taskName = task.getName();
      int taskID = task.getId();
  %>
  <option value=<%=taskID%>><%=taskName%></option>
  <%
    }
  %>
  </select>
  <input type="file" name="file" /><br>
  <input type="submit" />
</form>

</body>
</html>
