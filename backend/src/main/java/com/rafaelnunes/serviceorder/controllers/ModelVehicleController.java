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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rafaelnunes.serviceorder.dto.ModelVehicleDTO;
import com.rafaelnunes.serviceorder.services.ModelVehicleService;

@RestController
@RequestMapping(value = "models")
public class ModelVehicleController {
	
	@Autowired
	private ModelVehicleService service;
	
	@GetMapping
	public ResponseEntity<List<ModelVehicleDTO>> findAll(
			@RequestParam(name = "modelVehicle", defaultValue = "") String modelVehicle) {
		return ResponseEntity.ok().body(service.findVehicleByModel(modelVehicle));
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ModelVehicleDTO> findById(@PathVariable Long id) {
		ModelVehicleDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<ModelVehicleDTO> insert(@Valid @RequestBody ModelVehicleDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ModelVehicleDTO> update(@PathVariable Long id, @Valid @RequestBody ModelVehicleDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
