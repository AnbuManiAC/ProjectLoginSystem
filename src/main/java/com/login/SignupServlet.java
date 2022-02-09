package com.login;

import static javax.swing.JOptionPane.showMessageDialog;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.login.bean.SignupBean;
import com.login.dao.SignupDao;
import com.login.encryption.PasswordUtils;
import com.login.validate.SignupValidate;

@WebServlet("/Signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SignupDao signupDao;
	
	public void init()
	{
		signupDao = new SignupDao();
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath()+"/signup.jsp");

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uuid = UUID.randomUUID().toString();
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		String pwd1 = request.getParameter("pwd1");
		
		
		SignupBean signupBean = new SignupBean();
		signupBean.setUuid(uuid);
		signupBean.setEmail(email);
		signupBean.setPwd(pwd);
		signupBean.setCpwd(pwd1);
		
		SignupValidate sv = new SignupValidate();
		if(sv.isValidUser(signupBean)) 
		{
			if(!signupDao.checkEmail(signupBean.getEmail()))
			{
				String salt = PasswordUtils.getSalt(signupBean.getEmail().length());
		        
		        String mySecurePassword = PasswordUtils.generateSecurePassword(signupBean.getPwd(), salt);
		        signupBean.setPwd(mySecurePassword);
		        signupBean.setCpwd(mySecurePassword);
		        signupBean.setSalt(salt);
				signupDao.setSignup(signupBean);
				showMessageDialog(null, "Signup successful");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
			else
			{
				showMessageDialog(null, "User already exists");
				response.sendRedirect(request.getContextPath()+"/signup.jsp");
			}
			
			
		}
		else
		{
			request.setAttribute("hasSubmitted", true);
			request.setAttribute("pwd", pwd);
			request.setAttribute("email", email);
			request.getRequestDispatcher("/signup.jsp").forward(request, response);
		}
		
		
		
	}

}
