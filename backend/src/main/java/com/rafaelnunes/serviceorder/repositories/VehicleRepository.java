package com.rafaelnunes.serviceorder.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rafaelnunes.serviceorder.entities.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
	
	@Query("SELECT obj FROM Vehicle obj "
			+ "WHERE obj.id = :vehicleId AND obj.client.id = :clientId")
	Optional<Vehicle> findVehicleByIdByClientId(Long vehicleId, Long clientId);
}
