package com.rafaelnunes.serviceorder.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rafaelnunes.serviceorder.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
	
	@Query("SELECT obj FROM Client obj "
			+ "WHERE obj.cpf = :cpf")
	Optional<Client> findByCpf(String cpf);
}
