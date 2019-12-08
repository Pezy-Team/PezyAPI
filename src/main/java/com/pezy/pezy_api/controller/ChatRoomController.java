package com.pezy.pezy_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pezy.pezy_api.pojo.FirebaseChat;
import com.pezy.pezy_api.service.ChatRoomService;

@RestController
@RequestMapping("/v1/chatroom")
@CrossOrigin(origins = "*")
public class ChatRoomController {
	
	@Autowired
	private ChatRoomService service;
	
	@PostMapping("/message")
	public ResponseEntity<?> pushMessage(@RequestBody FirebaseChat message, @RequestParam("room") String room){
		return service.pushMessage(message, room);
	}
	
	@PostMapping("/user/{user}/store/{store}")
	public ResponseEntity<?> create(@PathVariable("user")Long user, @PathVariable("store")Long store){
		return service.create(user, store);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<?> findByUserId(@PathVariable("id")Long id){
		return service.findByUserId(id);
	}
	
	@GetMapping("/store/{storeID}")
	public ResponseEntity<?> findByStoreId(@PathVariable("storeID")Long storeID){
		return service.findByStoreId(storeID);
	}
	
	@GetMapping("/user/{userID}/storeID/{storeID}")
	public ResponseEntity<?> findBoth(@PathVariable("userID")Long userID, @PathVariable("storeID")Long storeID){
		return service.findBoth(userID, storeID);
	}

}
