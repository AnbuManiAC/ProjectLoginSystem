package com.login;

import static javax.swing.JOptionPane.showMessageDialog;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath()+"/main.jsp");

	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		Cookie cookie = new Cookie("user","");
		cookie.setMaxAge(0);
		res.addCookie(cookie);
		showMessageDialog(null,"Successfully logged out");
		res.sendRedirect("main.jsp");
		
	}

}
