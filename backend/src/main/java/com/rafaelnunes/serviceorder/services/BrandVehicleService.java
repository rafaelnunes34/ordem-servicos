package com.rafaelnunes.serviceorder.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rafaelnunes.serviceorder.dto.BrandVehicleDTO;
import com.rafaelnunes.serviceorder.entities.BrandVehicle;
import com.rafaelnunes.serviceorder.repositories.BrandVehicleRepository;
import com.rafaelnunes.serviceorder.services.exceptions.DatabaseException;
import com.rafaelnunes.serviceorder.services.exceptions.ResourceNotFoundException;

@Service
public class BrandVehicleService {
	
	@Autowired
	private BrandVehicleRepository repository;
	
	@Transactional(readOnly = true)
	public List<BrandVehicleDTO> findAll() {
		List<BrandVehicle> list = repository.findAll();
		return list.stream().map(x -> new BrandVehicleDTO(x)).toList();
	}
	
	@Transactional(readOnly = true)
	public BrandVehicleDTO findById(Long id) {
		BrandVehicle entity = repository.findById(id)
							.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
		return new BrandVehicleDTO(entity);
	}
	
	@Transactional
	public BrandVehicleDTO insert(BrandVehicleDTO dto) {
		BrandVehicle entity = new BrandVehicle();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new BrandVehicleDTO(entity);
	}
	
	@Transactional
	public BrandVehicleDTO update(BrandVehicleDTO dto, Long id) {
		try {
			BrandVehicle entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new BrandVehicleDTO(entity);
		}
		catch(EntityNotFoundException ex) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
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
	
	private void copyDtoToEntity(BrandVehicleDTO dto, BrandVehicle entity) {
		entity.setName(dto.getName());
	}
}
