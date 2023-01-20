package com.rafaelnunes.serviceorder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafaelnunes.serviceorder.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
