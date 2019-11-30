package com.pezy.pezy_api.pojo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pezy.pezy_api.entity.ChatRoom;

import lombok.Data;

@Data
public class ResponseMessageChatRoom extends ResponseMessage implements Serializable{
	
	@JsonProperty("chat_rooms")
	private List<ChatRoom> chatRooms;

}
