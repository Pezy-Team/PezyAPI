package com.pezy.pezy_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pezy.pezy_api.entity.ChatRoom;

@Repository
public interface ChatRoomRepository extends CrudRepository<ChatRoom, Long> {
	
	public List<ChatRoom> findByInviteIDOrAcceptID(Long invite, Long accept);
	
	public List<ChatRoom> findByInviteIDAndAcceptIDOrInviteIDAndAcceptID(Long id1, Long id2, Long id3, Long id4);
	
	public Optional<ChatRoom> findByRoomName(String name);

}
