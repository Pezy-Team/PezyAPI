package com.pezy.pezy_api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pezy.pezy_api.entity.User;
import com.pezy.pezy_api.pojo.ResponseMessage;
import com.pezy.pezy_api.service.UserService;

@RestController
@RequestMapping("/v1/user")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserService userServ;

	@PostMapping
	public ResponseEntity<?> save(@RequestBody User user) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userServ.save(user));
	}

	@PostMapping("/auth")
	public ResponseEntity<?> auth(@RequestBody User user) {
		List<User> users = userServ.findByEmailAndPassword(user.getEmail(), user.getPassword());
		ResponseMessage resMessage = new ResponseMessage();
		if (users.isEmpty()) {
			resMessage.setMessage("User not found or email or password was invalid.");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resMessage);
		}
		
		return ResponseEntity.ok(users.get(0));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity findById(@PathVariable("id") Long id) {
		Optional<User> userOpt = userServ.findById(id);
		if(userOpt.isPresent()) {
			return ResponseEntity.ok(userOpt);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/structure")
	public ResponseEntity<?> getStructure(){
		User body = new User();
		return ResponseEntity.ok(body);
	}

}
