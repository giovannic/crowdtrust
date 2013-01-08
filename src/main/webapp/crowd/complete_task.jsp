<!DOCTYPE html>

<%@ page import="crowdtrust.SubTask" %>
<%@ page import="db.SubTaskDb" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.LinkedList" %>

<html>
  <head>
  <%
    String question = (String) session.getAttribute("question");
    String answersStr = (String) session.getAttribute("answers");
    String taskName = (String) session.getAttribute("name");
    int taskID = 0;
    int annotationType = 0;
    int mediaType = 0;
    int inputType = 0;
    int userID = 0;
    try {
      taskID = Integer.parseInt((String)session.getAttribute("taskID"));
      annotationType = Integer.parseInt((String)session.getAttribute("annotation_type"));
      mediaType = Integer.parseInt((String)session.getAttribute("media_type"));
      inputType = Integer.parseInt((String)session.getAttribute("input_type"));
      userID = (Integer) session.getAttribute("account_id");
    } catch( Exception e ) {
	e.printStackTrace();
        return;
    }
    String[] answersArr = answersStr.split("/");
    List<String> answers = new LinkedList();
    for(int i = 0 ; i < answersArr.length ; i++) {
      String ans = answersArr[i];
      if(ans != null) {
        answers.add(ans);
      }
    }
    
    String TASKS_DIRECTORY = "http://www.doc.ic.ac.uk/project/2012/362/g1236218/TaskFiles/";
    SubTask subtask = SubTaskDb.getRandomSubTask(taskID, userID);
  %>
    <title>Task: <%=taskName%></title>
    <%
      if(subtask == null) {
    %>
    <META HTTP-EQUIV="refresh" CONTENT="3;URL=/crowd/tasklist.jsp">
    <%
      }
    %>
  </head>

  <body>
    <h1>Task: <%=taskName%></h1>
    <h2 id="question"><%=question%>?</h2>
    <%
    if(subtask != null) {
      String subtaskFile = TASKS_DIRECTORY + taskID + "/" + subtask.getFileName();
      int sid = subtask.getId();
		  switch(mediaType) {
		  case 1: /*image*/ 
	  %>
	  <img src="<%=subtaskFile %>" />
		<%
		  	break;
		  case 2: /*audio*/
	  %>
	  <audio src="<%=subtaskFile%>" controls preload="auto">
      Your browser does not support the audio element, please choose a new task.
    </audio> 
    <%
			  break;
		  case 3: /*video*/
	  %>
	  <video height=240 width=320 src="<%=subtaskFile%>" />
	  <%
		  }
	  %>
	  <form action="/servlet/response" method="post">
      <%
      int it = 0;
		    for( String answer : answers) {
		      switch(inputType) {
		      case 1: /*radio buttons*/
		  %><br>
      <input type="radio" name="response" value=<%=it%>
      <% if(it == 0) {%> checked <%}%> > <%=answer%> </input>
		  <%
                      break;
		      }
		    }
	    %>
	    <input type="hidden" name="annotation_type" value=<%=annotationType%> />
	    <input type="hidden" name="sid" value=<%=sid%> />
		  <input type="submit" /><br>
		  
    </form>
	  <%
    } else {
                %>
      <h2>Task completed! Thank you, returning to your task list now</h2>
    <%}
    %>
  </body>
</html>
		  







</html>
