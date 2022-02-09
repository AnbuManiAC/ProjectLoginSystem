package com.login;

import static javax.swing.JOptionPane.showMessageDialog;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.login.bean.LoginBean;
import com.login.dao.LoginDao;
import com.login.encryption.PasswordUtils;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDao loginDao;
	
	public void init()
	{
		loginDao = new LoginDao();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect(request.getContextPath()+"/main.jsp");

	}
     
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String email = req.getParameter("email");
		String pwd = req.getParameter("pwd");
		//hashed pwd
		LoginBean loginBean = new LoginBean();
        loginBean.setEmail(email);
        loginBean.setPwd(pwd);
		
		String[] check = new String[3];
		check = loginDao.checkLogin(loginBean);
		if(check[0].equals("true"))
		{
			req.setAttribute("hasSubmitted", true);
			if(PasswordUtils.verifyUserPassword(loginBean.getPwd(), check[1], check[2]))
			{
				Cookie cookie = new Cookie("user",email);
				res.addCookie(cookie);
				req.setAttribute("email", loginBean.getEmail());
				res.sendRedirect(req.getContextPath()+"/welcome.jsp");
			}
			else
			{
				req.setAttribute("email", email);
				showMessageDialog(null,"Incorrect Password");
				req.getRequestDispatcher("/login.jsp").forward(req, res);
			}
		}
		else
		{
			showMessageDialog(null,"User doesn't exists\r\nCreate user first");
			res.sendRedirect("signup.jsp");
		}
		
	}

}
