package com.pezy.pezy_api.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PezyConfigProperties {
	
	private static PezyConfigProperties instance = new PezyConfigProperties();
	
	private static String PLATFORM;
	
	private static InputStream stream;
	
	private static Properties prop = new Properties();
	
	public static PezyConfigProperties init() throws Exception {
		stream = instance.getClass().getClassLoader().getResourceAsStream("pezyconfig.properties");
		if(stream != null) {
			prop.load(stream);
			PLATFORM = prop.getProperty("os");
		} else {
			throw new Exception("pezyconfig.properties was null or not found.");
		}
		return instance;
	}
	
	public static String getPlatformProperty(String name) {
		return prop.getProperty(PLATFORM + "." + name);
	}
	
	public static String getProperty(String name) {
		return prop.getProperty(name);
	}
	
}
