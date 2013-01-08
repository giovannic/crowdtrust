<%@ page import="java.util.Collection" %>
<%@ page import="db.SubTaskDb" %>
<%@ page import="crowdtrust.Task" %>

<html>
  <%
    int tid = Integer.parseInt(request.getParameter("task_id"));
    Collection <Result> results = SubTaskDb.getResults(tid);
    
  %>
  <head>
    
  </head>  


  <body>
    <table>
    <tr>
      <th>Subtask File<th>
      <th>Estimated Result</th>
      <th>Percentage Confidence in Answer</th>
    </tr>
    <%
    for( Result r : results ) {
      subtask = r.getSubTask();
      estimate = r.getEstimate();    
      %>
    <tr>
      <td><%=subtask.getFileName()%></td>
      <td><%=estimate.getR().toString()%></td>
      <td><%=estimate.getPercentage()%></td>
    </tr>
    <%
    }
    %>
    </table>
  </body>

</html>
