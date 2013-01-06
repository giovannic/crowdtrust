<!DOCTYPE html>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<html>

	<head>
		<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
		<link href="/css/styles.css" rel="stylesheet" type="text/css" media="screen">
	</head>
	
	<body>
	<div id="wrapper">

	  <%@include file="/includes/header.jsp" %>

	  <%@include file="/includes/about.jsp" %>

		<%@include file="/includes/login.jsp" %>
    <decorator:body />

		<div id="dbinit">
		  <form action="/servlet/init" method="post">
		    <input type="submit" value="initDB DO NOT PRESS UNLESS INIT IS DESIRED"/>
      </form>
    </div>

	  <%@include file="/includes/footer.jsp" %>
	</div>
	</body>

</html>
