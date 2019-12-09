package com.pezy.pezy_api.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pezy.pezy_api.entity.User;
import com.pezy.pezy_api.enumerate.BooleanEnum;
import com.pezy.pezy_api.enumerate.GenderEnum;
import com.pezy.pezy_api.enumerate.RegisterByDeviceEnum;
import com.pezy.pezy_api.enumerate.RegisterByEnum;
import com.pezy.pezy_api.enumerate.UserTypeEnum;
import com.pezy.pezy_api.helper.FacebookAuthHelper;
import com.pezy.pezy_api.pojo.FacebookUserProfile;
import com.pezy.pezy_api.pojo.ResponseMessage;
import com.pezy.pezy_api.pojo.ResponseMessageFacebookUserProfile;
import com.pezy.pezy_api.repository.UserRepository;

@Service
public class FacebookService {
	
	@Autowired
	private UserRepository userRepo;
	
	private ResponseMessageFacebookUserProfile msgProfile = new ResponseMessageFacebookUserProfile();
	
	public ResponseEntity<?> getProfile(FacebookUserProfile fbUserProfile){
		try {
			FacebookUserProfile user = FacebookAuthHelper.init().getProfile(fbUserProfile);
			
			Optional<User> findUser = userRepo.findByEmailIs(user.getEmail());
			if(!findUser.isPresent()) {
				
				/**
				 * Add new user.
				 */
				User createUser = new User();
				createUser.setEmail(user.getEmail());
				createUser.setGender("male".equals(user.getGender()) ? GenderEnum.MALE : GenderEnum.FEMAIL);
				createUser.setName(user.getFirstName() + " " + user.getLastName());
				createUser.setFbAccessToken(user.getAccessToken());
				createUser.setFbLinked(BooleanEnum.TRUE);
				createUser.setFbUID(String.valueOf(user.getId()));
				createUser.setUserType(UserTypeEnum.CUSTOMER);
				createUser.setRegisterBy(RegisterByEnum.FACEBOOK);
				
				msgProfile.setStatus(true);
				msgProfile.setMessage("Get facebook user's profile and create new success.");
				msgProfile.setData(userRepo.save(createUser));
			}else {
				User getUser = findUser.get();
				getUser.setFbLinked(BooleanEnum.TRUE);
				
				msgProfile.setStatus(true);
				msgProfile.setMessage("This user is already exists. And now user was linked to facebook account.");
				msgProfile.setData(userRepo.save(getUser));
			}
			return ResponseEntity.ok(msgProfile);
		} catch (Exception e) {
			msgProfile.setMessage(e.getMessage());
			msgProfile.setStatus(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msgProfile);
		}
	}
	
	public ResponseEntity<?> unlinkUser(FacebookUserProfile fbUserProfile){
		try {
			FacebookUserProfile user = FacebookAuthHelper.init().getProfile(fbUserProfile);
			Optional<User> findUser = userRepo.findByEmailIs(user.getEmail());
			if(!findUser.isPresent()) {
				msgProfile.setStatus(false);
				msgProfile.setMessage("This user was not found in our system. Please sign up with Facebook.");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msgProfile);
			} else {
				User getUser = findUser.get();
				getUser.setFbLinked(BooleanEnum.FALSE);
				getUser.setUpdateDate(new Date());
				
				msgProfile.setStatus(true);
				msgProfile.setMessage("Unlinked user success.");
				msgProfile.setData(userRepo.save(getUser));
				return ResponseEntity.ok(msgProfile);
			}
			
		} catch (Exception e) {
			msgProfile.setMessage(e.getMessage());
			msgProfile.setStatus(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msgProfile);
		}
		
	}

}
