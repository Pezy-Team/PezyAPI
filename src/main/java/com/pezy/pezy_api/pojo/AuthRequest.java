package com.pezy.pezy_api.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
public class AuthRequest {
	
	private String username;
	
	private String email;
	
	private String password;

}
