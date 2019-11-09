package com.pezy.pezy_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.pezy.pezy_api.entity.Order;
import com.pezy.pezy_api.entity.OrderLine;
import com.pezy.pezy_api.service.FileStorageService;
import com.pezy.pezy_api.service.OrderLineService;

@RestController
@RequestMapping("/v1/order-line")
@CrossOrigin(origins = "*")
public class OrderLineController {
	
	@Autowired
	private OrderLineService service;
	
	@Autowired
	private FileStorageService fileService;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody OrderLine o){
		return service.create(o);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateById(@PathVariable("id")Long id, @RequestBody OrderLine o){
		return service.updateById(o, id);
	}
	
	@GetMapping
	public ResponseEntity<?> findAll(){
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id")Long id){
		return service.findById(id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id")Long id){
		return service.deleteById(id);
	}

}
