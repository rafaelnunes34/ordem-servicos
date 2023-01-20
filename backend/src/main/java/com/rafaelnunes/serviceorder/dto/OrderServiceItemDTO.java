package com.rafaelnunes.serviceorder.dto;

public class OrderServiceItemDTO {
	
	private Integer unity;
	private Double price;
	private ServiceDTO service;
	
	public OrderServiceItemDTO() {
	}

	public OrderServiceItemDTO(Integer unity, Double price, ServiceDTO service) {
		this.unity = unity;
		this.price = price;
		this.service = service;
	}

	public Integer getUnity() {
		return unity;
	}

	public void setUnity(Integer unity) {
		this.unity = unity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public ServiceDTO getService() {
		return service;
	}

	public void setService(ServiceDTO service) {
		this.service = service;
	}
}
