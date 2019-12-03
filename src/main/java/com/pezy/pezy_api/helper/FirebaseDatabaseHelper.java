package com.pezy.pezy_api.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pezy.pezy_api.pojo.FirebaseChat;
import com.pezy.pezy_api.pojo.ResponseMessage;
import com.pezy.pezy_api.pojo.ResponseMessageFirebaseChat;
import com.pezy.pezy_api.properties.PezyConfigProperties;

public class FirebaseDatabaseHelper {

	
	private static FirebaseDatabaseHelper instance = new FirebaseDatabaseHelper();
	
	public static FirebaseDatabaseHelper init() {
		return instance;
	}
	
	private FirebaseApp apps;
	
	private FirebaseDatabase database;
	
	private ResponseMessageFirebaseChat msg = new ResponseMessageFirebaseChat();
	
	public ResponseMessageFirebaseChat pushMessage(FirebaseChat message, String room) {
		try {
			
			getFirebaseDatabase();
			
			DatabaseReference ref = database.getReference("chatrooms");
			
			ApiFuture<Void> task = ref.child(room).push().setValueAsync(message);
			
			int i = 1;
			while(!task.isDone()) {
				if(i == 20) {
					throw new Exception("Firebase's connection got timeouts.");
				}
				
				System.out.println("Waiting connection to firebase.");
				++i;
				Thread.sleep(3000);
			}
			msg.setMessage("Pushing message in room " + room + " success.");
			apps.delete();
			return msg;
		} catch (IOException e) {
			msg.setMessage("IOException : " + e.getMessage());
			System.out.println(msg.getMessage());
		} catch (URISyntaxException e) {
			msg.setMessage("URISyntaxException : " + e.getMessage());
			System.out.println(msg.getMessage());
		} catch (InterruptedException e) {
			msg.setMessage("InterruptedException : " + e.getMessage());
			System.out.println(msg.getMessage());
		} catch (Exception e) {
			msg.setMessage("Exception : " + e.getMessage());
			System.out.println(msg.getMessage());
		}
		msg.setStatus(false);
		if(null != apps) {
			apps.delete();
		}
		return msg;
	}
	
	private void getFirebaseDatabase() throws Exception {
		initializeFirebase();
		database = FirebaseDatabase.getInstance(apps);
	}
	
	private void initializeFirebase() throws Exception {
		
		FirebaseOptions options = new FirebaseOptions.Builder()
//				.setCredentials(GoogleCredentials.fromStream(new FileInputStream("C:\\Users\\Administrator\\Documents\\api\\firebase_key\\testpezychat-firebase-adminsdk-bxi1c-54de75f8da.json")))
				  .setCredentials(GoogleCredentials.fromStream(new FileInputStream(PezyConfigProperties.init().getProperty("firebase.keyfile.path"))))
//				  .setCredentials(GoogleCredentials.getApplicationDefault())
				  .setDatabaseUrl("https://testpezychat.firebaseio.com")
				  .build();

		apps = FirebaseApp.initializeApp(options);
	}
}
