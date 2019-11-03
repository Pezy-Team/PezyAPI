package com.pezy.pezy_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pezy.pezy_api.entity.StoreAds;
import com.pezy.pezy_api.service.StoreAdsService;

@RestController
@RequestMapping("/v1/store-ads")
@CrossOrigin(origins = "*")
public class StoreAdsController {
	
	@Autowired
	private StoreAdsService service;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody StoreAds ads){
		return service.create(ads);
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody StoreAds ads){
		return service.update(ads);
	}
	
	@GetMapping
	public ResponseEntity<?> findAll(){
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id){
		return service.findById(id);
	}
	
	@GetMapping("/by-store-id/{id}")
	public ResponseEntity<?> findByStoreId(@PathVariable("id")Long id){
		return service.findAdsByStoreId(id);
	}

}
