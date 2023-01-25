package com.rafaelnunes.serviceorder.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rafaelnunes.serviceorder.entities.ModelVehicle;

public interface ModelVehicleRepository extends JpaRepository<ModelVehicle, Long> {
	
	@Query("SELECT obj FROM ModelVehicle obj "
			+ "JOIN FETCH obj.brand "
			+ "WHERE LOWER(obj.name) LIKE LOWER(CONCAT('%',:modelVehicle,'%'))")
	List<ModelVehicle> findVehicleByModel(String modelVehicle);
}
