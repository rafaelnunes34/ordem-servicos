package com.rafaelnunes.serviceorder.dto;

import javax.validation.constraints.NotBlank;

public class UserInsertDTO extends UserDTO {
	
	@NotBlank(message = "O campo password não pode ser vazio")
	private String password;

	public UserInsertDTO(Long id, String name, String email, String password) {
		super(id, name, email);
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
