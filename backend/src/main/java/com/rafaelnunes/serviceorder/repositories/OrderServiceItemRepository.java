package com.rafaelnunes.serviceorder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafaelnunes.serviceorder.entities.OrderServiceItem;
import com.rafaelnunes.serviceorder.entities.OrderServiceItemPK;

public interface OrderServiceItemRepository extends JpaRepository<OrderServiceItem, OrderServiceItemPK> {

}
