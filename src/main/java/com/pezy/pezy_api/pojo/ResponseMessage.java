package com.pezy.pezy_api.pojo;

import java.util.Date;

import lombok.Data;

@Data
public class ResponseMessage {
	
	private Date timestamp = new Date();
	private String message;
	
}
