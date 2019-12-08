package com.pezy.pezy_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pezy.pezy_api.entity.ChatRoom;

@Repository
public interface ChatRoomRepository extends CrudRepository<ChatRoom, Long> {
	
	public Optional<ChatRoom> findByUserIDAndStoreID(Long userID, Long storeID);
	
	public List<ChatRoom> findByUserID(Long userID);
	
	public List<ChatRoom> findByStoreID(Long storeID);
	
	public Optional<ChatRoom> findByRoomName(String name);

}
