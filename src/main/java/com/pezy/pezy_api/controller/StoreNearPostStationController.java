package com.pezy.pezy_api.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pezy.pezy_api.entity.StoreNearPostStation;
import com.pezy.pezy_api.service.StoreNearPostStationService;

@RestController
@RequestMapping("/v1/store-nearly-poststation")
@CrossOrigin(origins = "*")
public class StoreNearPostStationController {
	

	@Autowired
	StoreNearPostStationService service;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody StoreNearPostStation post){
		return service.create(post);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id")Long id, @RequestBody StoreNearPostStation post){
		return service.update(post, id);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id")Long id){
		return service.findById(id);
	}
	
	@GetMapping
	public ResponseEntity<?> findAll(){
		return service.findAll();
	}
	
	@GetMapping("/by-store-id/{id}")
	public ResponseEntity<?> findByStoreId(@PathVariable("id")Long id){
		return service.findByStoreId(id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id")Long id){
		return service.deleteById(id);
	}
	
}
