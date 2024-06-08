package br.com.mfsdevsystem.sellcar.dto;

import java.io.Serializable;

import br.com.mfsdevsystem.sellcar.entities.UserRole;

public class UserDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;	
	private String name;
	private String email;
	private UserRole userRole;
	
	public UserDTO() {
		
	}
	
	public UserDTO(Long id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	
}
