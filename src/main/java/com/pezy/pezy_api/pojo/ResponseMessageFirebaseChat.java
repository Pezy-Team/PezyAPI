package com.pezy.pezy_api.pojo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ResponseMessageFirebaseChat extends ResponseMessage implements Serializable{
	

	private Boolean status = true;
	
	private List<FirebaseChat> result;

}
