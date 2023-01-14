package com.rafaelnunes.serviceorder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafaelnunes.serviceorder.entities.BrandVehicle;

public interface BrandVehicleRepository extends JpaRepository<BrandVehicle, Long> {
}
