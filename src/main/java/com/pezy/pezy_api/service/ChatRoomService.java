package com.pezy.pezy_api.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pezy.pezy_api.entity.ChatRoom;
import com.pezy.pezy_api.entity.User;
import com.pezy.pezy_api.helper.AuthHelper;
import com.pezy.pezy_api.pojo.ResponseMessage;
import com.pezy.pezy_api.pojo.ResponseMessageChatRoom;
import com.pezy.pezy_api.repository.ChatRoomRepository;
import com.pezy.pezy_api.repository.UserRepository;
import com.pezy.pezy_api.utils.UserUtils;

@Service
public class ChatRoomService {
	
	@Autowired
	private ChatRoomRepository repo;
	
	@Autowired
	private UserRepository userRepo;
	
	private final String SALT = "CHATROOMPEZY";
	
	private ResponseMessage msg = new ResponseMessage();
	
	private ResponseMessageChatRoom msgChat = new ResponseMessageChatRoom(); 
	
	public ResponseEntity<?> create(Long invite, Long accept){
		String inviteName = userRepo.findById(invite).get().getName();
		String acceptName = userRepo.findById(accept).get().getName();
		
		List<ChatRoom> rooms = repo.findByInviteIDAndAcceptIDOrInviteIDAndAcceptID(invite, accept, accept, invite);
		if(rooms.isEmpty()) {
			ChatRoom chat = new ChatRoom();
			chat.setInviteID(invite);
			chat.setAcceptID(accept);
			chat.setRoomName(AuthHelper.bcrypt(5, inviteName + acceptName + SALT + new Date().toString()));
			return ResponseEntity.ok(repo.save(chat));
		} else {
			msgChat.setMessage("They matchs together already.");
			msgChat.setChatRooms(rooms);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msgChat);
		}
		
	}
	
	public ResponseEntity<?> findBoth(Long invite, Long accept){
		List<ChatRoom> rooms = repo.findByInviteIDAndAcceptIDOrInviteIDAndAcceptID(invite, accept, accept, invite);
		if(rooms.isEmpty()) {
			msg.setMessage("Chat room not found.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
		}
		msgChat.setMessage("Finding chat room success.");
		msgChat.setChatRooms(rooms);
		return ResponseEntity.ok(msgChat);
	}
	
	public ResponseEntity<?> findByUserId(Long id){
		List<ChatRoom> rooms = repo.findByInviteIDOrAcceptID(id, id);
		if(rooms.isEmpty()) {
			msg.setMessage("Chat room not found.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
		}
		return ResponseEntity.ok(rooms);
	}

}
