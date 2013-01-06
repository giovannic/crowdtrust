﻿<!DOCTYPE html>
<html>

	<!-- #BeginTemplate "../main.dwt" -->

	<head>
		<!-- #BeginEditable "doctitle" -->
		<title>Client Profile</title>
<!-- #EndEditable -->
		<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
		<link href="../css/styles.css" rel="stylesheet" type="text/css" media="screen">
	</head>
	
	<body>
	<div id="wrapper">
		<div id="banner">
				<img src="../images/header%20txt.png" />
		</div>
					
		<div id="about_us">
			<ul>
				<li><a href="../crowd_info.html">AS A CROWDMEMBER</a></li>
				<li><a href="../client_info.html">AS A CLIENT</a></li>
				<li><a href="../about.html">ABOUT US</a></li>
			</ul>
		</div>

		<div id="login">
			<h3>Login</h3>
		    <form action="/servlet/login" method="post">
		      <table id="logintable">
		      <tr>
		        <td>Username:</td>
		        <td><input type="text" name="username" value=""></td>
		      </tr>
		      <tr>
		        <td>Password:</td>
		        <td><input type="password" name="password" value=""></td>
		      </tr>
		      </table>
		      <input type="submit" id="login" value="Login">
		    </form>
    		<p><a href="../register.html">Click Here to Register</a></p>
  
		</div>
		
		<!-- #BeginEditable "content" -->
		
		<div id="content">
		  <a href="addtask.jsp" > add a new task </a>
		  <a href="upload.jsp" > upload subtasks </a>
		</div>
		
		<!-- #EndEditable -->
		
		<div id="footer">
			<p>Created by Giovanni Charles, Adam Fiksen, Ryan Jackson, Sahil Jain, John Walker</p>
		</div>
	</div>
	</body>

<!-- #EndTemplate -->

</html>