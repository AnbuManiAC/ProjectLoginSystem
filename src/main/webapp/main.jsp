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
 <h1>Home Page</h1>
  <a href="login.jsp" style="text-decoration:none">Login</a>&emsp;
  <a href="signup.jsp" style="text-decoration:none">Sign up</a>&emsp;
  <a href="welcome.jsp" style="text-decoration:none">Welcome</a>&emsp;
</div>
<%}} %>

</body>
</html>