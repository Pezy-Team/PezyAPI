package com.pezy.pezy_api.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pezy.pezy_api.entity.Stores;

import lombok.Data;

@Data
public class UsersStore implements Serializable{
	

	@JsonProperty(value = "user_id")
	private Long userId;
	
	private String name;
	
	private String tel;
	
	private String email;
	
	private String username;
	
	private Stores store;

}
