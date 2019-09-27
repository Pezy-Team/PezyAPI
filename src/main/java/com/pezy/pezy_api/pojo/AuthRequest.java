package com.pezy.pezy_api.pojo;

import lombok.Data;

@Data
public class AuthRequest {
	
	private String username;
	
	private String email;
	
	private String password;

}
