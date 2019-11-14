package com.pezy.pezy_api.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pezy.pezy_api.service.UserService;

@RestController
@RequestMapping("/v1/admin/user")
@CrossOrigin(origins = "*")
public class AdminUserController {

	@Autowired
	private UserService service;
	
	
	
}