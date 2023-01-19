package com.rafaelnunes.serviceorder.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.rafaelnunes.serviceorder.entities.Vehicle;

public class VehicleDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotBlank(message = "O campo licensePlate não pode ser vazio")
	@Size(message = "O campo não pode ser maior que 10 caracteres!", max = 10)
	private String licensePlate;
	
	@NotBlank(message = "O campo color não pode ser vazio")
	@Size(message = "O campo não pode ser maior que 10 caracteres!", max = 10)
	private String color;
	
	@NotBlank(message = "O campo year não pode ser vazio")
	@Size(message = "O campo não pode ser maior que 20 caracteres!", max = 20)
	private String year;
	
	@NotNull(message = "O modelo do veiculo não pode ser vazia")
	private ModelVehicleDTO model;
	
	public VehicleDTO() {
	}

	public VehicleDTO(Long id, String licensePlate, String color, String year, ModelVehicleDTO model) {
		this.id = id;
		this.licensePlate = licensePlate;
		this.color = color;
		this.year = year;
		this.model = model;
	}
	
	public VehicleDTO(Vehicle entity) {
		id = entity.getId();
		licensePlate = entity.getLicensePlate();
		color = entity.getColor();
		year = entity.getYear();
		model = new ModelVehicleDTO(entity.getModel());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public ModelVehicleDTO getModel() {
		return model;
	}

	public void setModel(ModelVehicleDTO model) {
		this.model = model;
	}
}
