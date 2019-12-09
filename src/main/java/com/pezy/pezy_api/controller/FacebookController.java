package com.pezy.pezy_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pezy.pezy_api.pojo.FacebookUserProfile;
import com.pezy.pezy_api.service.FacebookService;

@RestController
@RequestMapping("/v1/facebook")
@CrossOrigin(origins = "*")
public class FacebookController {
	
	@Autowired
	private FacebookService service;
	
	@PostMapping("/auth")
	public ResponseEntity<?> getUserProfile(@RequestBody FacebookUserProfile token){
		
		return service.getProfile(token);
		
	}
	
	@PostMapping("/unlink")
	public ResponseEntity<?> unlinkUser(@RequestBody FacebookUserProfile token){
		return service.unlinkUser(token);
	}
	

}
