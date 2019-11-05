package com.pezy.pezy_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pezy.pezy_api.entity.Stores;
import com.pezy.pezy_api.pojo.ResponseMessage;
import com.pezy.pezy_api.repository.StoreRepository;

@Service
public class StoreService {

	@Autowired
	private StoreRepository repository;
	
	private ResponseMessage msg = new ResponseMessage();
	
	public Stores save(Stores store) {
		return repository.save(store);
	}
	
	public Optional<Stores> findById(Long id) {
		return repository.findById(id);
	}
	
	public ResponseEntity<?> findAll() {
		List<Stores> stores = (List<Stores>) repository.findAll();
		if(!stores.isEmpty()) {
			return ResponseEntity.ok(stores);
		}
		msg.setMessage("Store not found");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
}
