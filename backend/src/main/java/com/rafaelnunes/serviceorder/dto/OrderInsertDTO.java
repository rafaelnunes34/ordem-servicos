package com.rafaelnunes.serviceorder.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderInsertDTO {
	
	private Long clientId;
	private Long vehicleId;
	private List<OrderServiceItemDTO> itens = new ArrayList<>();
	
	public OrderInsertDTO() {
	}

	public OrderInsertDTO(Long clientId, Long vehicleId) {
		this.clientId = clientId;
		this.vehicleId = vehicleId;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public List<OrderServiceItemDTO> getItens() {
		return itens;
	}
}
