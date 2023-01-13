package com.rafaelnunes.serviceorder.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_order_service_item")
public class OrderServiceItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private OrderServiceItemPK id = new OrderServiceItemPK();
	
	@Column(nullable = false)
	private Integer unity;
	
	@Column(nullable = false, precision = 2)
	private Double price;
	
	public OrderServiceItem() {
	}

	public OrderServiceItem(Order order, Service service, Integer unity, Double price) {
		id.setOrder(order);
		id.setService(service);
		this.unity = unity;
		this.price = price;
	}
	
	public Order getOrder() {
		return id.getOrder();
	}
	
	public void setOrder(Order order) {
		id.setOrder(order);
	}
	
	public Service getService() {
		return id.getService();
	}
	
	public void setService(Service service) {
		id.setService(service);
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
		OrderServiceItem other = (OrderServiceItem) obj;
		return Objects.equals(id, other.id);
	}
}
