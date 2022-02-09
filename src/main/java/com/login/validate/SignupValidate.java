package com.login.validate;

import static javax.swing.JOptionPane.showMessageDialog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.login.bean.SignupBean;

public class SignupValidate {
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
	        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	public boolean isValidEmail(String email) {
	    Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
	    return matcher.find();
	}
	
	public  boolean isValidPassword(String password)
    {
            boolean isValid = true;
            if (password.length() > 15 || password.length() < 8)
            {
            	showMessageDialog(null, "Password must be less than 20 and more than 8 characters in length.");
                isValid = false;
            }
            String upperCaseChars = "(.*[A-Z].*)";
            if (!password.matches(upperCaseChars ))
            {
            	showMessageDialog(null, "Password must have atleast one uppercase character");
            	isValid = false;
            }
            String lowerCaseChars = "(.*[a-z].*)";
            if (!password.matches(lowerCaseChars ))
            {
            	showMessageDialog(null, "Password must have atleast one lowercase character");
                isValid = false;
            }
            String numbers = "(.*[0-9].*)";
            if (!password.matches(numbers ))
            {
            	showMessageDialog(null, "Password must have atleast one number");
            	isValid = false;
            }
            String specialChars = "(.*[@,#,$,%].*$)";
            if (!password.matches(specialChars ))
            {
            	showMessageDialog(null, "Password must have atleast one special character among @#$%");
            	isValid = false;
            }
            return isValid; 
    }

	public boolean isValidUser(SignupBean signupBean) {
	  
	  if(!isValidEmail(signupBean.getEmail())){
		  	showMessageDialog(null, "Invalid Email address");
	        return false;
	  }
	  else if(!Pattern.matches("[^ ]*", signupBean.getEmail())) {
		  	showMessageDialog(null, "Email cannot contain space");
		  	return false;
	  }
	  	
	  else if(!isValidPassword(signupBean.getPwd())){
		  return false;
	  }
	  
	  else if(!Pattern.matches("[^ ]*", signupBean.getPwd())){
		 showMessageDialog(null, "Password cannot contain space");
	     return false;
	  }
	  
	  else if(!signupBean.getCpwd().equals(signupBean.getPwd())) {
		  showMessageDialog(null, "Password doesn't match");
		  return false;
	  }
	  
	  return true;

	}

}
