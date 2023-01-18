package com.rafaelnunes.serviceorder.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class OrderServiceItemPK implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "service_id")
	private TypeService service;
	
	public OrderServiceItemPK() {
	}
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public TypeService getService() {
		return service;
	}
	public void setService(TypeService service) {
		this.service = service;
	}

	@Override
	public int hashCode() {
		return Objects.hash(order, service);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderServiceItemPK other = (OrderServiceItemPK) obj;
		return Objects.equals(order, other.order) && Objects.equals(service, other.service);
	}
}
