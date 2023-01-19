package com.rafaelnunes.serviceorder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafaelnunes.serviceorder.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
