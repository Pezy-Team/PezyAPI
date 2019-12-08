package com.pezy.pezy_api.pojo;

import com.pezy.pezy_api.entity.User;

import lombok.Data;

@Data
public class TokenMessage extends ResponseMessage{
	
	private String token;
	
	private User user;

}
