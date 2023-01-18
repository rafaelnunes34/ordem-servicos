package com.rafaelnunes.serviceorder.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rafaelnunes.serviceorder.dto.ServiceDTO;
import com.rafaelnunes.serviceorder.entities.TypeService;
import com.rafaelnunes.serviceorder.repositories.TypeServiceRepository;
import com.rafaelnunes.serviceorder.services.exceptions.DatabaseException;
import com.rafaelnunes.serviceorder.services.exceptions.ResourceNotFoundException;

@Service
public class TypeServiceService {
	
	@Autowired
	private TypeServiceRepository repository;
	
	@Transactional(readOnly = true)
	public Page<ServiceDTO> findAll(String description, Pageable pageable) {
		Page<TypeService> page = repository.findAllPaged(description, pageable);
		return page.map(x -> new ServiceDTO(x));
	}
	
	@Transactional(readOnly = true)
	public ServiceDTO findById(Long id) {
		TypeService entity = repository.findById(id)
							.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
		return new ServiceDTO(entity);
	}
	
	@Transactional
	public ServiceDTO insert(ServiceDTO dto) {
		TypeService entity = new TypeService();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ServiceDTO(entity);
	}
	
	@Transactional
	public ServiceDTO update(ServiceDTO dto, Long id) {
		try {
			TypeService entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new ServiceDTO(entity);
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
	
	private void copyDtoToEntity(ServiceDTO dto, TypeService entity) {
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
	}
}
