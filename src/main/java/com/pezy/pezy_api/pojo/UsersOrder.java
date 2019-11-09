package com.pezy.pezy_api.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pezy.pezy_api.entity.Order;

import lombok.Data;

@Data
public class UsersOrder implements Serializable {

	@JsonProperty(value = "user_id")
	private Long userId;
	
	private String name;
	
	private String tel;
	
	private String email;
	
	private String username;
	
	private Order order;
	
}
