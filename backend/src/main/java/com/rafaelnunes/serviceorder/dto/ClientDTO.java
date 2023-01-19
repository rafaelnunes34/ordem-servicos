package com.rafaelnunes.serviceorder.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.rafaelnunes.serviceorder.entities.Client;

public class ClientDTO {
	
	private Long id;
	
	@NotBlank(message = "O campo name não pode ser vazio")
	@Size(message = "O campo não pode ser maior que 30 caracteres!", max = 30)
	private String name;
	
	@NotBlank(message = "O campo cpf não pode ser vazio")
	@Size(message = "O campo não pode ser maior que 20 caracteres!", max = 20)
	private String cpf;
	
	@NotBlank(message = "O campo phone não pode ser vazio")
	@Size(message = "O campo não pode ser maior que 20 caracteres!", max = 20)
	private String phone;
	
	public ClientDTO() {
	}

	public ClientDTO(Long id, String name, String cpf, String phone) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.phone = phone;
	}
	
	public ClientDTO(Client entity) {
		id = entity.getId();
		name = entity.getName();
		cpf = entity.getCpf();
		phone = entity.getPhone();
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
