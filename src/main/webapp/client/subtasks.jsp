<%@ page import="java.util.Collection" %>
<%@ page import="db.SubTaskDb" %>
<%@ page import="crowdtrust.Task" %>
<%@ page import="crowdtrust.Result" %>
<%@ page import="crowdtrust.SubTask" %>
<%@ page import="crowdtrust.Estimate" %>

<html>


  <%
    int aid = (Integer) session.getAttribute("account_id");
    int tid = Integer.parseInt(request.getParameter("task_id"));
    Collection <Result> results = SubTaskDb.getResults(tid, aid);
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
    if(results != null) {
      for( Result r : results ) {
        SubTask subtask = r.getSubtask();
        Estimate estimate = r.getE();    
    %>
    <tr>
      <td><%=subtask.getFileName()%></td>
      <td><%=estimate.getR().toString()%></td>
      <td><%=estimate.getPercentage()%></td>
    </tr>
    <%
      }
    } else {
    %>
      <td>no responses to task yet!</td>
    <%
    }
    %>
    </table>
  </body>

</html>
