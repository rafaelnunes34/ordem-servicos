package com.rafaelnunes.serviceorder.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rafaelnunes.serviceorder.dto.ModelVehicleDTO;
import com.rafaelnunes.serviceorder.entities.BrandVehicle;
import com.rafaelnunes.serviceorder.entities.ModelVehicle;
import com.rafaelnunes.serviceorder.repositories.BrandVehicleRepository;
import com.rafaelnunes.serviceorder.repositories.ModelVehicleRepository;
import com.rafaelnunes.serviceorder.services.exceptions.DatabaseException;
import com.rafaelnunes.serviceorder.services.exceptions.ResourceNotFoundException;

@Service
public class ModelVehicleService {
	
	@Autowired
	private ModelVehicleRepository repository;
	
	@Autowired
	private BrandVehicleRepository brandVehicleRepository;
	
	@Transactional(readOnly = true)
	public List<ModelVehicleDTO> findAll() {
		List<ModelVehicle> list = repository.findAll();
		return list.stream().map(x -> new ModelVehicleDTO(x)).toList();
	}
	
	@Transactional(readOnly = true)
	public ModelVehicleDTO findById(Long id) {
		ModelVehicle entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
		return new ModelVehicleDTO(entity);
	}
	
	@Transactional
	public ModelVehicleDTO insert(ModelVehicleDTO dto) {
		try {
			ModelVehicle entity = new ModelVehicle();
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new ModelVehicleDTO(entity);
		}
		catch(DataIntegrityViolationException ex) {
			throw new DatabaseException("Falha de integridade referencial");
		}
	}
	
	@Transactional
	public ModelVehicleDTO update(Long id, ModelVehicleDTO dto) {
		try {
			ModelVehicle entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new ModelVehicleDTO(entity);
		}
		catch(EntityNotFoundException ex) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
	}
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException ex) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
		catch(DataIntegrityViolationException ex) {
			throw new DatabaseException("Falha de integridade referencial");
		}
	}
	
	private void copyDtoToEntity(ModelVehicleDTO dto, ModelVehicle entity) {
		BrandVehicle brandVehicle = brandVehicleRepository.getReferenceById(dto.getBrand().getId());
		entity.setName(dto.getName());
		entity.setBrand(brandVehicle);
	}
}
