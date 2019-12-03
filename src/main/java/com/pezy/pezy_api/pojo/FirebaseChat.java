package com.pezy.pezy_api.pojo;

import java.awt.datatransfer.StringSelection;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class FirebaseChat implements Serializable{
	
	private String message;
	
	private Long timestamps = new Date().getTime();
	
	private String type;
	
	@JsonProperty(value = "user_id")
	private Integer userId;

}
