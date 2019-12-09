package com.pezy.pezy_api.pojo;

import java.io.Serializable;

import com.pezy.pezy_api.entity.User;

import lombok.Data;

@Data
public class TokenMessage extends ResponseMessage implements Serializable{
	
	private String token;
	
	private User data;

}
