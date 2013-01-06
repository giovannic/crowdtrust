<!doctype html>
<html>
  <head>
    <title>
      CrowdTrust - Register
    </title>
  </head>
  
  <body>
  
    <h1>CrowdTrust - Register</h1>
    <form action="/servlet/register" method="post">
      <table id="registertable">
      <tr>
        <td>Username:</td>
        <td><input type="text" name="username" value=""></td>
      </tr>
      <tr>
        <td>Password:</td>
        <td><input type="password" name="password" value=""></td>
      </tr>
      <tr>
        <td>Confirm Password:</td>
        <td><input type="password" name="cpassword" value=""></td>
      </tr>
      <tr>
        <td>Email:</td> 
        <td><input type="text" name="email" value=""></td>
      </tr>
      <tr>
        <td>Register as:</td> 
        <td><input type="radio" name="custtype" value="crowd">Crowd
        <input type="radio" name="custtype" value="client">Client</td>
      </tr>        
      </table> 
      <input type="submit" value="Register">
    </form>
    <p><a href="/">Click Here to return to the Homepage</a></p>
    
  </body>
</html>
