package com.rafaelnunes.serviceorder.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.rafaelnunes.serviceorder.entities.enums.TypePayment;

@Entity
@Table(name = "tb_payment")
public class Payment implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant moment;
	
	@Enumerated(EnumType.STRING)
	private TypePayment type;
	
	@Column(nullable = false, precision = 2)
	private Double price;
	private Double discount;
	
	@MapsId
	@OneToOne
	private Order order;
	
	public Payment() {
	}

	public Payment(Long id, Instant moment, TypePayment type, Double price, Double discount, Order order) {
		this.id = id;
		this.moment = moment;
		this.type = type;
		this.price = price;
		this.discount = discount;
		this.order = order;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public TypePayment getType() {
		return type;
	}

	public void setType(TypePayment type) {
		this.type = type;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
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
		Payment other = (Payment) obj;
		return Objects.equals(id, other.id);
	}
}
