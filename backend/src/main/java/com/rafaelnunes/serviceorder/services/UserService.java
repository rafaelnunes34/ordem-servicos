package com.rafaelnunes.serviceorder.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rafaelnunes.serviceorder.dto.RoleDTO;
import com.rafaelnunes.serviceorder.dto.UserDTO;
import com.rafaelnunes.serviceorder.dto.UserInsertDTO;
import com.rafaelnunes.serviceorder.entities.Role;
import com.rafaelnunes.serviceorder.entities.User;
import com.rafaelnunes.serviceorder.repositories.RoleRepository;
import com.rafaelnunes.serviceorder.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Transactional
	public UserDTO insert(UserInsertDTO dto) {
		User entity = new User();
		copyDtoToEntity(dto, entity);
		entity.setPassword(passwordEncoder.encode(dto.getPassword()));
		entity = repository.save(entity);
		return new UserDTO(entity);
	}
	
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("User Not Found");
		}
		return user;
	}
	
	private void copyDtoToEntity(UserDTO dto, User entity) {
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.getAuthorities().clear();
		
		for(RoleDTO roleDto : dto.getAuthorities()) {
			Role role = roleRepository.getReferenceById(roleDto.getId());
			entity.getAuthorities().add(role);
		}
	}
}
