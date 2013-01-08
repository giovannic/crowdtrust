<%@ page import="java.util.List" %>
<%@ page import="db.SubTaskDb" %>
<%@ page import="crowdtrust.Task" %>
<%@ page import="crowdtrust.ClientEstimate" %>

<html>
  <%
    int tid = Integer.parseInt(request.getParameter("task_id"));
    estimates = SOME JAVA CALL;
    
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
    for( int i = 0 ; i < estimates.length ; estimates++ ) {
      e = estimates[i];
      subtask = e.getSubTask();
      response = e.getResponse();    
    <tr>
      <td><%=subtask.getFileName%></td>
      <td><%=response.serialise()%></td>
      <td><%=get confidence (t=e^c/(1+e^c))%></td>
    </tr>
    <%
    }
    %>
    </table>
  </body>

</html>
