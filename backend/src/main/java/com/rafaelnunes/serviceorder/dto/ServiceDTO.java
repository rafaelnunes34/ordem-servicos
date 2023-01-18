package com.rafaelnunes.serviceorder.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.rafaelnunes.serviceorder.entities.TypeService;

public class ServiceDTO {
	
	private Long id;
	
	@NotBlank(message = "O campo description não pode ser vazio")
	@Size(message = "O campo não pode ser maior que 50 caracteres!", max = 150)
	private String description;
	
	@NotNull(message = "O campo price não pode ser vazio")
	@PositiveOrZero(message = "O valor só pode ser positivo")
	private Double price;
	
	public ServiceDTO() {
	}

	public ServiceDTO(Long id, String description, Double price) {
		this.id = id;
		this.description = description;
		this.price = price;
	}
	
	public ServiceDTO(TypeService entity) {
		id = entity.getId();
		description = entity.getDescription();
		price = entity.getPrice();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
