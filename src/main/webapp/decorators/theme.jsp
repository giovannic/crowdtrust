<!DOCTYPE html>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<html>

	<head>
		<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
		<link href="css/styles.css" rel="stylesheet" type="text/css" media="screen">
	</head>
	
	<body>
	<div id="wrapper">
		<div id="banner">
				<a href="/"><img src="images/header txt.png" /></a>
		</div>
					
		<div id="about_us">
			<ul>
				<li><a href="crowd_info.jsp">AS A CROWDMEMBER</a></li>
				<li><a href="client_info.jsp">AS A CLIENT</a></li>
				<li><a href="about.jsp">ABOUT US</a></li>
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
    		<p><a href="register.jsp">Click Here to Register</a></p>
  
		</div>
		
    <decorator:body />
		
		<div id="footer">
			<p>Created by Giovanni Charles, Adam Fiksen, Ryan Jackson, Sahil Jain, John Walker</p>
		</div>

		<div id="dbinit">
		  <form action="servlet/init" method="post">
		    <input type="submit" />
      </form>
    </div>
	</div>
	</body>

</html>
