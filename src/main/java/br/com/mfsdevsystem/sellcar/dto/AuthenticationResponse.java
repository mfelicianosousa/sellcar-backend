package br.com.mfsdevsystem.sellcar.dto;

import java.io.Serializable;

import br.com.mfsdevsystem.sellcar.entities.UserRole;

public class AuthenticationResponse implements Serializable {

	private static final long serialVersionUID = 1L;
		
	private String jwt;
	private Long userId;
	private UserRole userRole;
	
	public AuthenticationResponse() {}

	public AuthenticationResponse(String jwt, Long userId, UserRole userRole) {
		this.jwt = jwt;
		this.userId = userId;
		this.userRole = userRole;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	} 	
	

}
