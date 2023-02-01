package com.rafaelnunes.serviceorder.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rafaelnunes.serviceorder.dto.VehicleDTO;
import com.rafaelnunes.serviceorder.entities.Client;
import com.rafaelnunes.serviceorder.entities.ModelVehicle;
import com.rafaelnunes.serviceorder.entities.Vehicle;
import com.rafaelnunes.serviceorder.repositories.ClientRepository;
import com.rafaelnunes.serviceorder.repositories.ModelVehicleRepository;
import com.rafaelnunes.serviceorder.repositories.VehicleRepository;
import com.rafaelnunes.serviceorder.services.exceptions.DatabaseException;
import com.rafaelnunes.serviceorder.services.exceptions.ResourceNotFoundException;

@Service
public class VehicleService {
	
	@Autowired
	private VehicleRepository repository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ModelVehicleRepository modelRepository;
	
	@Transactional
	public VehicleDTO insertVehicleToClient(String cpf, VehicleDTO dto) {
		try {
			Client client = clientRepository.findByCpf(cpf).orElseThrow(() -> new ResourceNotFoundException("CPF não encontrado"));
			Vehicle entity = new Vehicle();
			copyDtoToEntity(dto, entity);
			entity.setClient(client);
			entity = repository.save(entity);
			return new VehicleDTO(entity);
		}
		catch(DataIntegrityViolationException ex) {
			throw new DatabaseException("Falha de integridade referencial");
		}
	}
	
	@Transactional
	public VehicleDTO update(Long id, VehicleDTO dto) {
		try {
			Vehicle entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new VehicleDTO(entity);
		}
		catch(EmptyResultDataAccessException ex) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
	}
	
	@Transactional(readOnly = true)
	public List<VehicleDTO> findVehiclesByClient(Long clientId) {
		List<Vehicle> list = repository.findVehiclesByIdByClient(clientId);
		return list.stream().map(x -> new VehicleDTO(x)).toList();
	}
	
	private void copyDtoToEntity(VehicleDTO dto, Vehicle entity) {
		entity.setLicensePlate(dto.getLicensePlate());
		entity.setColor(dto.getColor());
		entity.setYear(dto.getYear());
		ModelVehicle modelVehicle = modelRepository.getReferenceById(dto.getModel().getId());
		entity.setModel(modelVehicle);
	}
}
