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

import com.rafaelnunes.serviceorder.dto.ClientDTO;
import com.rafaelnunes.serviceorder.entities.Client;
import com.rafaelnunes.serviceorder.repositories.ClientRepository;
import com.rafaelnunes.serviceorder.services.exceptions.DatabaseException;
import com.rafaelnunes.serviceorder.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAll(Pageable pageable) {
		Page<Client> page = repository.findAll(pageable);
		return page.map(x -> new ClientDTO(x));
	}
	
	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Client entity = repository.findById(id)
							.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
		return new ClientDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public ClientDTO findByCpf(String cpf) {
		Client entity = repository.findByCpf(cpf)
							.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
		return new ClientDTO(entity);
	}
	
	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		Client entity = new Client();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ClientDTO(entity);
	}
	
	@Transactional
	public ClientDTO update(ClientDTO dto, Long id) {
		try {
			Client entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new ClientDTO(entity);
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
	
	private void copyDtoToEntity(ClientDTO dto, Client entity) {
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setPhone(dto.getPhone());
	}
}
