package com.rafaelnunes.serviceorder.dto;

import java.io.Serializable;

import com.rafaelnunes.serviceorder.entities.ModelVehicle;

public class ModelVehicleDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
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
