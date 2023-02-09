package com.rafaelnunes.serviceorder.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafaelnunes.serviceorder.dto.VehicleDTO;
import com.rafaelnunes.serviceorder.services.VehicleService;

@RestController
@RequestMapping(value = "vehicles")
public class VehicleController {
	
	@Autowired
	private VehicleService service;
	
	@PutMapping(value = "/{cpf}/insertVehicle")
	public ResponseEntity<VehicleDTO> insertVehicleToClient(@PathVariable String cpf, @Valid @RequestBody VehicleDTO dto) {
		dto = service.insertVehicleToClient(cpf.trim(), dto);
		return ResponseEntity.ok().body(dto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<VehicleDTO> insertVehicleToClient(@PathVariable Long id, @Valid @RequestBody VehicleDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping(value = "/{clientId}/client")
	public ResponseEntity<List<VehicleDTO>> findVehiclesByClient(@PathVariable Long clientId) {
		List<VehicleDTO> list = service.findVehiclesByClient(clientId);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{licensePlate}/{clientId}")
	public ResponseEntity<VehicleDTO> findVehicleByLicensePlateByClient(@PathVariable String licensePlate, @PathVariable Long clientId) {
		VehicleDTO dto = service.findVehicleByLicenseByClient(licensePlate, clientId);
		return ResponseEntity.ok().body(dto);
	}
}
