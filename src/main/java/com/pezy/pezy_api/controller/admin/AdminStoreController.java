package com.pezy.pezy_api.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pezy.pezy_api.service.admin.AdminStoreService;

@RestController
@RequestMapping("/v1/admin/store")
@CrossOrigin(origins = "*")
public class AdminStoreController {

	@Autowired
	private AdminStoreService service;
	
	@GetMapping("/limit/{limit}/of-page/{offset}")
	public ResponseEntity<?> findAll(@PathVariable("limit")Integer limit, @PathVariable("offset")Integer offset){
		return service.findAll(limit, offset);
	}
	
	
}
