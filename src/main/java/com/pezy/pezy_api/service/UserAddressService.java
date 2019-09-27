package com.pezy.pezy_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pezy.pezy_api.entity.UserAddress;
import com.pezy.pezy_api.repository.UserAddressRepository;

@Service
public class UserAddressService {

	@Autowired
	private UserAddressRepository repository;
	
	public UserAddress save(UserAddress address) {
		return repository.save(address);
	}
	
}
