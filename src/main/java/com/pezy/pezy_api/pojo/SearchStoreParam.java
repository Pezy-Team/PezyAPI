package com.pezy.pezy_api.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pezy.pezy_api.enumerate.StoreStatusEnum;

import lombok.Data;

@Data
public class SearchStoreParam implements Serializable{
	
	@JsonProperty(value = "date_from")
	private Date dateFrom;
	
	@JsonProperty(value = "date_until")
	private Date dateUntil; 
	
	private StoreStatusEnum status;
}
