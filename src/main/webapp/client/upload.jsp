<%@ page import="crowdtrust.Task" %>
<%@ page import="db.TaskDb" %>
<%@ page import="java.util.List" %>

<html>
  <head>
    <%
      if (session == null) {
    %>
      <META HTTP-EQUIV="refresh" CONTENT="0;URL=/" />
    <%
      }
    %>
  </head>
  <body>

    <h1>FILE UPLOAD</h1>

    Ensure the filetype of your upload matches the media type of the task
    (e.g. .jpg/.bmp etc. for "Image").

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
      </select><br>
      <input type="file" name="file" /><br>
      <input type="submit" />
    </form>

  </body>
</html>
