package com.rafaelnunes.serviceorder.services;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rafaelnunes.serviceorder.dto.OrderDTO;
import com.rafaelnunes.serviceorder.dto.OrderInsertDTO;
import com.rafaelnunes.serviceorder.dto.OrderServiceItemDTO;
import com.rafaelnunes.serviceorder.entities.Client;
import com.rafaelnunes.serviceorder.entities.Order;
import com.rafaelnunes.serviceorder.entities.OrderServiceItem;
import com.rafaelnunes.serviceorder.entities.TypeService;
import com.rafaelnunes.serviceorder.entities.User;
import com.rafaelnunes.serviceorder.entities.Vehicle;
import com.rafaelnunes.serviceorder.entities.enums.OrderStatus;
import com.rafaelnunes.serviceorder.repositories.OrderRepository;
import com.rafaelnunes.serviceorder.repositories.OrderServiceItemRepository;
import com.rafaelnunes.serviceorder.repositories.TypeServiceRepository;
import com.rafaelnunes.serviceorder.repositories.VehicleRepository;
import com.rafaelnunes.serviceorder.services.exceptions.ResourceNotFoundException;
import com.rafaelnunes.serviceorder.services.exceptions.UnauthorizedException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;

	@Autowired
	private VehicleRepository vehicleRepository;

	@Autowired
	private TypeServiceRepository serviceRepository;

	@Autowired
	private OrderServiceItemRepository orderServiceItemRepository;

	@Autowired
	private AuthService authService;

	@Transactional(readOnly = true)
	public List<OrderDTO> findAll() {
		List<Order> list = repository.findAll();
		return list.stream().map(x -> new OrderDTO(x)).toList();
	}

	@Transactional
	public OrderDTO insert(OrderInsertDTO dto) {
		User userLogged = authService.authenticated();
		
		if (userLogged == null) {
			throw new UnauthorizedException("Usuário inválido");
		} 

		Vehicle vehicle = vehicleRepository.findVehicleByIdByClientId(dto.getVehicleId(), dto.getClientId())
				.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));

		Order entity = factoryOrder(userLogged, vehicle.getClient(), vehicle);
		entity = repository.saveAndFlush(entity);

		for (OrderServiceItemDTO itemDto : dto.getItens()) {
			TypeService service = serviceRepository.getReferenceById(itemDto.getService().getId());
			OrderServiceItem item = new OrderServiceItem(entity, service, itemDto.getUnity(), itemDto.getPrice());
			orderServiceItemRepository.save(item);
		}

		entity = repository.save(entity);

		return new OrderDTO(entity);
	}
	
	private Order factoryOrder(User user, Client client, Vehicle vehicle) {
		Order newOrder = new Order();
		newOrder.setMoment(Instant.now());
		newOrder.setStatus(OrderStatus.PENDENTE);
		newOrder.setUser(user);
		newOrder.setClient(client);
		newOrder.setVehicle(vehicle);
		return newOrder;
	}
}
