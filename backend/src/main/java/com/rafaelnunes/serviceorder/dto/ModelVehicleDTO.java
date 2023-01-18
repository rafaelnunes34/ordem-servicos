package com.rafaelnunes.serviceorder.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.rafaelnunes.serviceorder.entities.ModelVehicle;

public class ModelVehicleDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "O campo name não pode ser vazio")
	@Size(max = 50, message = "O campo não pode ser maior que 50 caracteres!")
	private String name;
	
	@NotNull(message = "A marca do veiculo não pode ser vazia")
	private BrandVehicleDTO brand;
	
	public ModelVehicleDTO() {
	}

	public ModelVehicleDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public ModelVehicleDTO(ModelVehicle entity) {
		id = entity.getId();
		name = entity.getName();
		brand = new BrandVehicleDTO(entity.getBrand());
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

	public BrandVehicleDTO getBrand() {
		return brand;
	}

	public void setBrand(BrandVehicleDTO brand) {
		this.brand = brand;
	}
}
