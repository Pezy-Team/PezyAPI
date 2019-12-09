package com.pezy.pezy_api.helper;

import java.io.IOException;

import org.codehaus.jettison.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pezy.pezy_api.pojo.FacebookUserProfile;
import com.pezy.pezy_api.properties.PezyConfigProperties;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class FacebookAuthHelper {
	
	private static FacebookAuthHelper instance = new FacebookAuthHelper();
	
	public static FacebookAuthHelper init() {
		return instance;
	}
	
	public static FacebookUserProfile getProfile(FacebookUserProfile token) throws Exception {
		PezyConfigProperties conf = PezyConfigProperties.init();

		String uri = conf.getProperty("graph.facebook.url");
		String scope = conf.getProperty("graph.facebook.scope");
		
		String url = String.format("%s/%s?fields=%s&access_token=%s", uri, token.getId(), scope, token.getAccessToken());
		
		System.out.println("URL :" + url);

		Client client = Client.create();
		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.accept("application/json")
//				.header("Content-Type", "application/json")
				.get(ClientResponse.class);
		JSONObject jsonResponse = response.getEntity(JSONObject.class);
		
		ObjectMapper mapper = new ObjectMapper();
		FacebookUserProfile profile = mapper.readValue(jsonResponse.toString(), FacebookUserProfile.class);
		
		profile.setAccessToken(token.getAccessToken());
		System.out.println("Result : " + profile.toString());
		return profile;
	}
	
}
