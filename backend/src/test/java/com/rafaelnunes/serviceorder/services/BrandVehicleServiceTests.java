package com.rafaelnunes.serviceorder.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rafaelnunes.serviceorder.dto.BrandVehicleDTO;
import com.rafaelnunes.serviceorder.entities.BrandVehicle;
import com.rafaelnunes.serviceorder.repositories.BrandVehicleRepository;
import com.rafaelnunes.serviceorder.services.exceptions.ResourceNotFoundException;

@ExtendWith(SpringExtension.class)
public class BrandVehicleServiceTests {
	
	@InjectMocks
	private BrandVehicleService service;
	
	@Mock
	private BrandVehicleRepository repository;
	
	private long existingId;
	private long nonExistingId;
	private BrandVehicle brandVehicle;
	private BrandVehicleDTO brandVehicleDto;
	private List<BrandVehicle> list = new ArrayList<>();
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 2L;
		brandVehicle = new BrandVehicle(1L, "CHEVROLET");
		brandVehicleDto = new BrandVehicleDTO(1L, "CHEVROLET");
		list.add(brandVehicle);
		
		Mockito.when(repository.findAll()).thenReturn(list);
		
		Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(brandVehicle);
		
		Mockito.when(repository.getReferenceById(existingId)).thenReturn(brandVehicle);
		Mockito.when(repository.getReferenceById(nonExistingId)).thenThrow(ResourceNotFoundException.class);
		
		Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(brandVehicle));
		Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());
		
		Mockito.doNothing().when(repository).deleteById(existingId);
		Mockito.doThrow(ResourceNotFoundException.class).when(repository).deleteById(nonExistingId);
	}
	
	@Test
	public void findAllShouldReturnList() {
		List<BrandVehicleDTO> list = service.findAll();
		Assertions.assertNotNull(list);
	}
	
	@Test
	public void saveShouldReturnBrandVehicleDto() {
		BrandVehicleDTO result = service.insert(brandVehicleDto);
		Assertions.assertNotNull(result);
	}
	
	@Test
	public void findByIdShouldReturnBrandVehicleDtoWhenIdExists() {
		BrandVehicleDTO result = service.findById(existingId);
		Assertions.assertNotNull(result);
		Mockito.verify(repository).findById(existingId);
	}
	
	@Test
	public void findByIdShouldThrowResourceNotFoundExceptionWhenDoesNotExists() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.findById(nonExistingId);
		});
		Mockito.verify(repository).findById(nonExistingId);
	}
	
	@Test
	public void updateShouldReturnBrandVehicleDtoWhenIdExists() {
		BrandVehicleDTO result = service.update(brandVehicleDto, existingId);
		Assertions.assertNotNull(result);
	}
	
	@Test
	public void updateShouldReturnResourceNotFoundExceptionWhenIdDoesNotExists() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.update(brandVehicleDto, nonExistingId);
		});
	}
	
	@Test
	public void deleteShouldDoNothingWhenIdExists() {
		Assertions.assertDoesNotThrow(() -> {
			service.delete(existingId);
		});
		Mockito.verify(repository, Mockito.times(1)).deleteById(existingId);
	}
	
	@Test
	public void deleteShouldThrowResourceNotFoundExceptionwhenIdDoesNotExists() {
		Assertions.assertThrows(ResourceNotFoundException.class ,() -> {
			service.delete(nonExistingId);
		});
		Mockito.verify(repository, Mockito.times(1)).deleteById(nonExistingId);
	}
	
}
