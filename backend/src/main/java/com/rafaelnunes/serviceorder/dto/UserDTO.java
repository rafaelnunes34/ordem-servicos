package com.rafaelnunes.serviceorder.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.rafaelnunes.serviceorder.entities.User;

public class UserDTO {
	
	private Long id;
	
	@NotBlank(message = "O campo name não pode ser vazio")
	@Size(max = 30, message = "O campo não pode ser maior que 30 caracteres!")
	private String name;
	
	@NotBlank(message = "O email name não pode ser vazio")
	@Size(max = 30, message = "O campo não pode ser maior que 50 caracteres!")
	@Email(message = "Informe um email valido")
	private String email;
	
	@NotNull(message = "Informe uma permissão")
	private Set<RoleDTO> authorities = new HashSet<>();
	
	public UserDTO() {
	}

	public UserDTO(Long id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}
	
	public UserDTO(User entity) {
		id = entity.getId();
		name = entity.getName();
		email = entity.getEmail();
		entity.getAuthorities().forEach(role -> this.authorities.add(new RoleDTO(role)));
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

	public Set<RoleDTO> getAuthorities() {
		return authorities;
	}
}
