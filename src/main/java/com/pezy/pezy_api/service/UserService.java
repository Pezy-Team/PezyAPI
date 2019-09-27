package com.pezy.pezy_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pezy.pezy_api.entity.User;
import com.pezy.pezy_api.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRep;
	
	
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
	
	public Optional<User> findById(Long id) {
		return userRep.findById(id);
	}
}
