package com.pezy.pezy_api.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FacebookUserProfile implements Serializable{
	
	private Long id;
	
	@JsonProperty(value = "access_token")
	private String accessToken;
	
	private String email;
	
	@JsonProperty(value = "first_name")
	private String firstName;
	
	@JsonProperty(value = "last_name")
	private String lastName;
	
	private String gender;
	
}
