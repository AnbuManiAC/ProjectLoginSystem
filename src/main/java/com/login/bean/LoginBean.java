package com.login.bean;

import java.io.Serializable;

public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
    private String email;
    private String pwd;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
    
}
