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

import com.pezy.pezy_api.entity.HelpCenter;
import com.pezy.pezy_api.service.HelpCenterService;

@RestController
@RequestMapping("/v1/help-center")
@CrossOrigin(origins = "*")
public class HelpCenterController {

	@Autowired
	private HelpCenterService service;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody HelpCenter hc){
		return service.create(hc);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id")Long id, @RequestBody HelpCenter hc){
		return service.update(hc, id);
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
