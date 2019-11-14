package com.pezy.pezy_api.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pezy.pezy_api.entity.User;
import com.pezy.pezy_api.pojo.ResponseMessage;
import com.pezy.pezy_api.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRep;
	
	private ResponseMessage msg = new ResponseMessage();
	
	public User save(User user) {
		return userRep.save(user);
	}
	
	public Iterable<User> saveAll(List<User> users) {
		return userRep.saveAll(users);
	}
	
	public List<User> findUserByEmail(String email) {
		return userRep.findUserAuthenticateByEmail(email);
	}
	
	public List<User> findUserByUsername(String username){
		return userRep.findUserAuthenticateByUsername(username);
	}
	
	public User findUserByToken(String token){
		return userRep.findUserByToken(token);
	}
	
	public Optional<User> findById(Long id) {
		return userRep.findById(id);
	}
	
	public ResponseEntity<?> getProfileByTokenAndTokenExpireAfter(String token){
		List<User> users = userRep.findByTokenAndTokenExpireAfter(token, new Date());
		if(users.isEmpty()) {
			msg.setMessage("User not found or token has expired.");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(msg);
		}
		User user = users.get(0);
		user.setPassword("***");
		user.setToken("***");
		user.setTokenExpire(null);
		return ResponseEntity.ok(user);
	}
	
	public ResponseEntity<?> isTokenExpire(String token){
		List<User> users = userRep.findByTokenAndTokenExpireAfter(token, new Date());
		if(users.isEmpty()) {
			msg.setMessage("Token has expired.");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(msg);
		}
		
		msg.setMessage("Token is now available.");
		return ResponseEntity.ok(msg);
	}
}
