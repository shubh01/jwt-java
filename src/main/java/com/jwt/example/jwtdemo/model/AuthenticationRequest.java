package com.jwt.example.jwtdemo.model;

import java.io.Serializable;

public class AuthenticationRequest implements Serializable{

	private String Username;
	private String pasword;
	
	protected AuthenticationRequest() {
		super();
	}
	public AuthenticationRequest(String username, String pasword) {
		Username = username;
		this.pasword = pasword;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPasword() {
		return pasword;
	}
	public void setPasword(String pasword) {
		this.pasword = pasword;
	}
	
	
	
}
