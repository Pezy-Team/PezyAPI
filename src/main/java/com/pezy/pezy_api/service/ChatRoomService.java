package com.pezy.pezy_api.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pezy.pezy_api.entity.ChatRoom;
import com.pezy.pezy_api.entity.Stores;
import com.pezy.pezy_api.entity.User;
import com.pezy.pezy_api.helper.AuthHelper;
import com.pezy.pezy_api.helper.FirebaseDatabaseHelper;
import com.pezy.pezy_api.pojo.FirebaseChat;
import com.pezy.pezy_api.pojo.ResponseMessage;
import com.pezy.pezy_api.pojo.ResponseMessageChatRoom;
import com.pezy.pezy_api.pojo.ResponseMessageFirebaseChat;
import com.pezy.pezy_api.repository.ChatRoomRepository;
import com.pezy.pezy_api.repository.StoreRepository;
import com.pezy.pezy_api.repository.UserRepository;
import com.pezy.pezy_api.utils.UserUtils;

@Service
public class ChatRoomService {
	
	@Autowired
	private ChatRoomRepository repo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private StoreRepository storeRepo;
	
	private final String SALT = "CHATROOMPEZY";
	
	private ResponseMessage msg = new ResponseMessage();
	
	private ResponseMessageChatRoom msgChat = new ResponseMessageChatRoom(); 
	
	private ResponseMessageFirebaseChat msgFChat = new ResponseMessageFirebaseChat();
	
	public ResponseEntity<?> pushMessage(FirebaseChat message, String roomname){
		Optional<ChatRoom> room = repo.findByRoomName(roomname);
		if(room.isPresent()) {
			msgFChat = FirebaseDatabaseHelper.init().pushMessage(message, roomname);
			List<FirebaseChat> chatList = new ArrayList<FirebaseChat>();
			chatList.add(message);
			msgFChat.setResult(chatList);
			if(msgFChat.getStatus()) {
				return ResponseEntity.ok(msgFChat);
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msgFChat);
			}
		} else {
			msg.setMessage("Room not found.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
		}
	}
	
	public ResponseEntity<?> create(Long userID, Long storeID){
		Optional<User> userOpt = userRepo.findById(userID);
		Optional<Stores> storeOptional = storeRepo.findById(storeID);
		if(storeOptional.isPresent() && userOpt.isPresent()) {
			Optional<ChatRoom> roomOpt = repo.findByUserIDAndStoreID(userID, storeID);
			if(!roomOpt.isPresent()) {
				ChatRoom chat = new ChatRoom();
				chat.setUserID(userID);
				chat.setStoreID(storeID);
				
				String name = AuthHelper.bcrypt(5, userOpt.get().getName() + storeOptional.get().getName() + SALT + new Date().toString());
				name = StringUtils.remove(name, "*");
				name = StringUtils.remove(name, "$");
				name = StringUtils.remove(name, "/");
				name = StringUtils.remove(name, "\\");
				name = StringUtils.remove(name, ".");
				name = StringUtils.remove(name, "[");
				name = StringUtils.remove(name, "]");
				name = StringUtils.remove(name, "(");
				name = StringUtils.remove(name, ")");
				chat.setRoomName(name);
				return ResponseEntity.ok(repo.save(chat));
			} else {
				List<ChatRoom> rooms = new ArrayList<ChatRoom>();
				rooms.add(roomOpt.get());
				msgChat.setMessage("They matchs together already.");
				msgChat.setChatRooms(rooms);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msgChat);
			}
		} else {
			msg.setMessage("Store or user not found.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
		}
	}
	
	public ResponseEntity<?> findBoth(Long userID, Long storeID){
		Optional<ChatRoom> rooms = repo.findByUserIDAndStoreID(userID, storeID);
		if(!rooms.isPresent()) {
			msg.setMessage("Chat room not found.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
		}
		msgChat.setMessage("Finding chat room success.");
		List<ChatRoom> roomList = new ArrayList<ChatRoom>();
		roomList.add(rooms.get());
		msgChat.setChatRooms(roomList);
		return ResponseEntity.ok(msgChat);
	}
	
	public ResponseEntity<?> findByStoreId(Long storeID){
		List<ChatRoom> rooms = repo.findByStoreID(storeID);
		if(rooms.isEmpty()) {
			msg.setMessage("Chat room not found.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
		}
		return ResponseEntity.ok(rooms);
	}
	
	public ResponseEntity<?> findByUserId(Long userID){
		List<ChatRoom> rooms = repo.findByUserID(userID);
		if(rooms.isEmpty()) {
			msg.setMessage("Chat room not found.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
		}
		return ResponseEntity.ok(rooms);
	}

}
