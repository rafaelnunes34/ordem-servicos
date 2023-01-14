package com.rafaelnunes.serviceorder.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rafaelnunes.serviceorder.dto.BrandVehicleDTO;
import com.rafaelnunes.serviceorder.entities.BrandVehicle;
import com.rafaelnunes.serviceorder.repositories.BrandVehicleRepository;

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
		BrandVehicle entity = repository.findById(id).get();
		return new BrandVehicleDTO(entity);
	}
	
	@Transactional
	public BrandVehicleDTO insert(BrandVehicleDTO dto) {
		BrandVehicle entity = new BrandVehicle();
		entity.setName(dto.getName());
		entity = repository.save(entity);
		return new BrandVehicleDTO(entity);
	}
	
	@Transactional
	public BrandVehicleDTO update(BrandVehicleDTO dto, Long id) {
		BrandVehicle entity = repository.getReferenceById(id);
		entity.setName(dto.getName());
		entity = repository.save(entity);
		return new BrandVehicleDTO(entity);
	}
	
	@Transactional
	public void delete(Long id) {
		repository.deleteById(id);
	}

}
