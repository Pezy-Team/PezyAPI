package com.pezy.pezy_api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pezy.pezy_api.entity.Store;
import com.pezy.pezy_api.repository.StoreRepository;

@Service
public class StoreService {

	@Autowired
	private StoreRepository repository;
	
	public Store save(Store store) {
		return repository.save(store);
	}
	
	public Optional<Store> findById(Long id) {
		return repository.findById(id);
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
}
