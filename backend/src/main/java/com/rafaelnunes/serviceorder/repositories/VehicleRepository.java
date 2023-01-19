package com.rafaelnunes.serviceorder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafaelnunes.serviceorder.entities.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
