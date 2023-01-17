package com.rafaelnunes.serviceorder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafaelnunes.serviceorder.entities.ModelVehicle;

public interface ModelVehicleRepository extends JpaRepository<ModelVehicle, Long> {
}
