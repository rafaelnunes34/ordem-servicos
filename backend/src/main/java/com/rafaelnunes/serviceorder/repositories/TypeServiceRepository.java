package com.rafaelnunes.serviceorder.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rafaelnunes.serviceorder.entities.TypeService;

public interface TypeServiceRepository extends JpaRepository<TypeService, Long> {
	
	@Query("SELECT obj FROM TypeService obj "
			+ "WHERE LOWER(obj.description) LIKE LOWER(CONCAT('%', :description, '%'))")
	Page<TypeService> findAllPaged(String description, Pageable pageable);
}
