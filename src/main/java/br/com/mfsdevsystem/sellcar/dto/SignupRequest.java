package br.com.mfsdevsystem.sellcar.dto;

import java.io.Serializable;


public class SignupRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String email;
	private String password;
	private String name;
	
	public SignupRequest() {}

	public SignupRequest(String email, String password, String name) {
		this.email = email;
		this.password = password;
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	

}
