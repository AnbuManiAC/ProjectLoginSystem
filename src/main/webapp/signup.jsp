<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login System</title>
</head>
<body>

<%
	response.setHeader("cache-control", "no-cache, no-store, must-revalidate");
	Cookie ck[] = request.getCookies();
	if(ck!=null){
		int i=-1;
		for (Cookie c:ck) {
			i++;
			if(c.getName().equals("user"))
				break;
		}
		if(i!=-1 && !ck[i].getValue().equals("") && ck[i].getName().equals("user"))
		{
			response.sendRedirect(request.getContextPath()+"/welcome.jsp");
		}else{
%>
	<% if(request.getAttribute("hasSubmitted") != null && request.getAttribute("hasSubmitted").equals(true)){ %>
 <div align="center">
  <h1>Sign Up</h1>
  <form action="Signup" method="post">
   <table style="with: 100%">
    <tr>
     <td>E-mail</td>
     <td><input type="email" name="email" required value='${email}'></td>
    </tr>
    <tr>
     <td>Password</td>
     <td><input type="password" name="pwd" id="pass" pattern="^(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,20}$" 
     title=
     "Password length should be between 8 and 20.
Should have at least one special character and one number.
Should have atleast one UpperCase nad one lowercase." required value='${pwd}'></td>
    </tr>
    <tr>
     <td></td>
     <td><input type="checkbox" onclick="showPass()">Show Password</td>
    </tr>
    <tr>
     <td>Confirm Password</td>
     <td><input type="password" name="pwd1" required></td>
    </tr>

   </table>
   <br>
   <input type="submit" value="Sign Up"/>
  </form>
 </div>
	<%}else{%>
 <div align="center">
  <h1>Sign Up</h1>
  <form action="Signup" method="post">
   <table style="with: 100%">
    <tr>
     <td>E-mail</td>
     <td><input type="email" name="email" required></td>
    </tr>
    <tr>
     <td>Password</td>
     <td><input type="password" name="pwd" id="pass" pattern="^(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,20}$" 
     title=
     "Password length should be between 8 and 20.
Should have at least one special character and one number.
Should have atleast one UpperCase nad one lowercase." required></td>
    </tr>
    <tr>
     <td></td>
     <td><input type="checkbox" onclick="showPass()">Show Password</td>
    </tr>
    <tr>
     <td>Confirm Password</td>
     <td><input type="password" name="pwd1" required></td>
    </tr>

   </table>
   <br>
   <input type="submit" value="Sign Up" />
  </form>
  <p>Already have an account. <a href="login.jsp" style="text-decoration:none">Login here</a></p>
  <p>Go to <a href="main.jsp" style="text-decoration:none">Home</a></p>
 </div>
	
	<%}}} %>
	
</body>

<script>
	function showPass() 
	{
	  var x = document.getElementById("pass");
	  if (x.type === "password") {
	    x.type = "text";
	  } else {
	    x.type = "password";
	  }
	}
	if( window.history.replaceState ) 
	{
	  	window.history.replaceState( null, null, window.location.href );
	}
	
</script>

</html>