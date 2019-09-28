package com.pezy.pezy_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pezy.pezy_api.entity.Store;
import com.pezy.pezy_api.service.StoreService;

@RestController
@RequestMapping("/v1/store")
@CrossOrigin(origins = "*")
public class StoreController {

	@Autowired
	private StoreService service;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Store store){
		store.setId(null);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(store));
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Store store){
		return ResponseEntity.ok(service.save(store));
	}
	
	
	@GetMapping("/structure")
	public ResponseEntity<?> getStructure(){
		return ResponseEntity.ok(new Store());
	}
	
}
