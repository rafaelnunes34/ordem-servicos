package com.rafaelnunes.serviceorder.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_vehicle")
public class Vehicle implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 10)
	private String licensePlate;
	
	@Column(name = "year_manufacture", nullable = false, length = 10)
	private String year;
	private String color;
	
	@ManyToOne
	@JoinColumn(name = "model_id")
	private ModelVehicle model;
	
	@ManyToOne
	@JoinColumn(name = "brand_id")
	private BrandVehicle brand;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	
	public Vehicle() {
	}

	public Vehicle(Long id, String licensePlate, String year, String color, ModelVehicle model, BrandVehicle brand,
			Client client) {
		super();
		this.id = id;
		this.licensePlate = licensePlate;
		this.year = year;
		this.color = color;
		this.model = model;
		this.brand = brand;
		this.client = client;
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public ModelVehicle getModel() {
		return model;
	}

	public void setModel(ModelVehicle model) {
		this.model = model;
	}

	public BrandVehicle getBrand() {
		return brand;
	}

	public void setBrand(BrandVehicle brand) {
		this.brand = brand;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		return Objects.equals(id, other.id);
	}
}
