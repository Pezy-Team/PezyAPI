package com.pezy.pezy_api.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "securities")
public class SecuritiesProperties {

	public final String SALT = "PezyOnline2019";
	
	public final int STRENGTH = 9;
	
	public final int TOKEN_USAGE_DURATION_MIN = 60;
			
	
}
