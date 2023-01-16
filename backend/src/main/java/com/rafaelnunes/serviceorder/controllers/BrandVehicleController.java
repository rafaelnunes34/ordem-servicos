package com.rafaelnunes.serviceorder.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rafaelnunes.serviceorder.dto.BrandVehicleDTO;
import com.rafaelnunes.serviceorder.services.BrandVehicleService;

@RestController
@RequestMapping(value = "/brands")
public class BrandVehicleController {
	
	@Autowired
	private BrandVehicleService service;
	
	@GetMapping
	public ResponseEntity<List<BrandVehicleDTO>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<BrandVehicleDTO> findById(@PathVariable Long id) {
		BrandVehicleDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<BrandVehicleDTO> findById(@Valid @RequestBody BrandVehicleDTO dto, @PathVariable Long id) {
		dto = service.update(dto, id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<BrandVehicleDTO> insert(@Valid @RequestBody BrandVehicleDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
