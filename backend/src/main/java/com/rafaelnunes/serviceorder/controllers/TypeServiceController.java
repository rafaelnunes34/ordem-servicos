package com.rafaelnunes.serviceorder.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rafaelnunes.serviceorder.dto.ServiceDTO;
import com.rafaelnunes.serviceorder.services.TypeServiceService;

@RestController
@RequestMapping(value = "services")
public class TypeServiceController {
	
	@Autowired
	private TypeServiceService service;
	
	@GetMapping
	public ResponseEntity<Page<ServiceDTO>> findAll(
			@RequestParam(name = "description", defaultValue = "") String description,
			Pageable pageable) {
		return ResponseEntity.ok().body(service.findAll(description.trim(), pageable));
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ServiceDTO> findById(@PathVariable Long id) {
		ServiceDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<ServiceDTO> insert(@Valid @RequestBody ServiceDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ServiceDTO> update(@PathVariable Long id, @Valid @RequestBody ServiceDTO dto) {
		dto = service.update(dto, id);
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
