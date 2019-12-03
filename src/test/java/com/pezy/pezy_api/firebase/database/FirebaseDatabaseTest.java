package com.pezy.pezy_api.firebase.database;

import java.awt.SystemTray;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pezy.pezy_api.pojo.FirebaseChat;

public class FirebaseDatabaseTest {
	
	@Test
	public void testReadPropertyFile() {
		InputStream stream = getClass().getClassLoader().getResourceAsStream("pezyconfig.properties");
		if(stream != null) {
			Properties prop = new Properties();
			try {
				prop.load(stream);
				String path = prop.getProperty("firebase.keyfile.path");
				System.out.println("Path : " + path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void testDatabaeFirebase() {
		try {
			Enumeration<URL> adminsSDK = getClass().getClassLoader().getResources("testpezychat-firebase-adminsdk-bxi1c-54de75f8da.json");
			File file = Paths.get(adminsSDK.nextElement().toURI()).toFile();
			String absolutePath = file.getAbsolutePath();
			FileInputStream serviceAccount = new FileInputStream(absolutePath);
			
			FirebaseOptions options = new FirebaseOptions.Builder()
					  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
					  .setDatabaseUrl("https://testpezychat.firebaseio.com")
					  .build();

			FirebaseApp app = FirebaseApp.initializeApp(options);
			
			FirebaseDatabase database = FirebaseDatabase.getInstance(app);
			DatabaseReference ref = database.getReference("chatroom");
			
			List<FirebaseChat> chats = new ArrayList<FirebaseChat>();
			FirebaseChat chat1 = new FirebaseChat(), chat2 = new FirebaseChat();
			
			chat1.setMessage("Hello");
			chat1.setUserId(1);
			chat1.setType("message");
			
			chat2.setMessage("Hi, There!");
			chat2.setUserId(2);
			chat2.setType("image");
			
			chats.add(chat1);
			chats.add(chat2);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("roomB", chats);
			
			
			
			ApiFuture<Void> task = ref.child("test/chat").updateChildrenAsync(map);
			while(!task.isDone()) {
				System.out.println("Waiting connection to firebase.");
				Thread.sleep(3000);
			}
			
			
			DatabaseReference readRef = database.getReference("chatroom/test/chat/roomB");
			readRef.addValueEventListener(new ValueEventListener() {
				
				@Override
				public void onDataChange(DataSnapshot snapshot) {
					System.out.println("Snapshot : " + snapshot.getValue());
				}
				
				@Override
				public void onCancelled(DatabaseError error) {
					// TODO Auto-generated method stub
					System.out.println("Database error : " + error.getMessage());
					
				}
			});
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
