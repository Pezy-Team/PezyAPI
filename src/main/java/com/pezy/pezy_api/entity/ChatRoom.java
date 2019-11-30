package com.pezy.pezy_api.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Data
@Entity
@Table(name = "chatroom")
//@JsonIgnoreProperties(value = {"password", "token"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = ChatRoom.class)
public class ChatRoom implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 5)
	private Long id;
	
	@JsonProperty("room_name")
	@Column(name = "room_name", length = 500)
	private String roomName;
	
	@Column(name = "invite_id")
	@JsonProperty(value = "invite_id")
	private Long inviteID;

	@Column(name = "accept_id")
	@JsonProperty(value = "accept_id")
	private Long acceptID;

	@CreatedDate
	@Column(name = "create_date")
	@JsonProperty("create_date")
	private Date createDate = new Date();

	@LastModifiedDate
	@Column(name = "update_date")
	@JsonProperty("update_date")
	private Date updateDate = new Date();

}
