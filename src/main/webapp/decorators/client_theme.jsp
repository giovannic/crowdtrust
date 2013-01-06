﻿<!DOCTYPE html>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<html>

	<head>
		<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
		<link href="/css/styles.css" rel="stylesheet" type="text/css" media="screen">
	</head>
	
	<body>
	<div id="wrapper">

	  <%@include file="/includes/header.jsp" %>

	  <%@include file="/includes/client_nav_bar.jsp" %>
		
    <decorator:body />

	  <%@include file="/includes/footer.jsp" %>
	</div>
	</body>

</html>
