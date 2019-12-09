package com.pezy.pezy_api.pojo;

import java.io.Serializable;

import com.pezy.pezy_api.entity.User;

import lombok.Data;

@Data
public class ResponseMessageFacebookUserProfile extends ResponseMessage implements Serializable{
	
	private Boolean status = true;
	
	private User data;

}
