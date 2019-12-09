package com.pezy.pezy_api.facebook;

import java.io.IOException;

import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pezy.pezy_api.pojo.FacebookUserProfile;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.mail.imap.protocol.UID;

public class FacebookAuthTest {
	
	private String ACCESS_TOKEN = "EAAY3hh3JWU0BALFwSsTUbbvD3r8RG5vrIHSZBevQtv3mjxuaPXi1Jv3QJ6NP4qMqTkmZCm7qN6zqzZAHoFICCPATUgOQxZBZBgRZBg4kmg7Q8cMbA7RiXEVvQvd9w47wnK3IDN9n76GfmClikADrF3ZBeylBjrMOsy9dePQC9nrYAZDZD";
	
	private String UID = "2580704451975235";
	
	@Test
	public void testFacebookGetProfile() throws JsonParseException, JsonMappingException, IOException {


		Client client = Client.create();
		WebResource webResource = client
				.resource("https://graph.facebook.com/v5.0/" + UID + "?fields=email,first_name,gender,last_name&access_token=" + ACCESS_TOKEN);
		ClientResponse response = webResource.accept("application/json")
//				.header("Content-Type", "application/json")
				.get(ClientResponse.class);
		JSONObject jsonResponse = response.getEntity(JSONObject.class);
		
		ObjectMapper mapper = new ObjectMapper();
		FacebookUserProfile profile = mapper.readValue(jsonResponse.toString(), FacebookUserProfile.class);
		
		profile.setAccessToken(ACCESS_TOKEN);
		
		System.out.println("Result : " + profile.toString());
	
		
	}
	
}
