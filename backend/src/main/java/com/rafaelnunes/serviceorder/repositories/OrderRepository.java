package com.rafaelnunes.serviceorder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafaelnunes.serviceorder.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
