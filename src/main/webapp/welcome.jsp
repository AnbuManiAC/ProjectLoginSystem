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
			out.println("<div align='center'><h1>Welcome</h1><p>This is the secured Welcome page</p><form action='Logout' method='post'><input type='submit' value='Logout'></form></div>");
		
		}
		else {
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		}
	}
	else
	{
		response.sendRedirect(request.getContextPath()+"/login.jsp");
	}

%>


	
</body>
</html>