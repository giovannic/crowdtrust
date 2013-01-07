<!DOCTYPE html>


<html>
  <head>
    <META HTTP-EQUIV="refresh" CONTENT="3;URL=/crowd/complete_task.jsp" />
  </head>
  <%
    session.setAttribute("taskID", request.getParameter("taskID"));
    session.setAttribute("name", request.getParameter("name"));
    session.setAttribute("question", request.getParameter("question"));
    session.setAttribute("media_type", request.getParameter("media_type"));
    session.setAttribute("annotation_type", request.getParameter("annotation_type"));
    System.out.println("input type in add params:" + request.getParameter("input_type"));
    session.setAttribute("input_type", request.getParameter("input_type"));
    session.setAttribute("answers", request.getParameter("answers"));
  %>
  <body>
    <h1 id="question">Loading subtasks</h1>
    <div id="question">
      <progress max="100">
        <strong>Progress: 60% done.</strong>
      </progress>
    </div>
  </body>
</html>
    
