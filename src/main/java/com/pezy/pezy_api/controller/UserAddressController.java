package com.pezy.pezy_api.controller;

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

import com.pezy.pezy_api.entity.UserAddress;
import com.pezy.pezy_api.pojo.ResponseMessage;
import com.pezy.pezy_api.service.UserAddressService;

@RestController
@RequestMapping("/v1/user-address")
@CrossOrigin(origins = "*")
public class UserAddressController {

	@Autowired
	private UserAddressService service;

	private ResponseMessage fMsg = new ResponseMessage();

	@PostMapping
	public ResponseEntity<?> create(@RequestBody UserAddress address) {
		address.setId(null);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(address));
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody UserAddress address) {
		return ResponseEntity.ok(service.save(address));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id){
		return ResponseEntity.ok(service.findById(id));
	}

}
