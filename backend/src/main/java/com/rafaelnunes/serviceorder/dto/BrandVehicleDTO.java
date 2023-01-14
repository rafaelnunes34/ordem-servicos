package com.rafaelnunes.serviceorder.dto;

import java.io.Serializable;

import com.rafaelnunes.serviceorder.entities.BrandVehicle;

public class BrandVehicleDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	
	public BrandVehicleDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public BrandVehicleDTO(BrandVehicle entity) {
		id = entity.getId();
		name = entity.getName();
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
}
