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
 <div align="center">
  <h1>User Login</h1>
  <form action="Login" method="post">
   <table style="with: 100%">
    <tr>
     <td>E-mail</td>
     <td><input type="text" name="email" 
     <% if(request.getAttribute("hasSubmitted") != null && request.getAttribute("hasSubmitted").equals(true)){%> value='${email}'<%} %> required/></td>
    </tr>
    <tr>
     <td>Password</td>
     <td><input type="password" name="pwd" id="pass" required/></td>
    </tr>
    <tr>
     <td></td>
     <td><input type="checkbox" onclick="showPass()">Show Password</td>
    </tr>

   </table>
   <br>
   <input type="submit" value="Login" />
  </form>
  <p>Don't have an account. <a href="signup.jsp" style="text-decoration:none">Create account</a></p>
  <p>Go to <a href="main.jsp" style="text-decoration:none">Home</a></p> 
 </div>
<%}
}%>

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
</script>
</html>