package com.pezy.pezy_api.utils;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import com.pezy.pezy_api.entity.User;
import com.pezy.pezy_api.helper.AuthHelper;
import com.pezy.pezy_api.properties.SecuritiesProperties;
import com.pezy.pezy_api.service.UserService;

public class UserUtils {

	public User verifyPassword(List<User> users, String pwd) {
		
		for(User user : users) {
			boolean chk = AuthHelper.verifyPassword(pwd, user.getPassword());
			if(chk) {
				return user;
			}
		}
		return null;
	}
	
	public boolean isTokenExpired(Date expire) {
		Date now = new Date();
		return now.after(expire);
	}

	public String storeToken(UserService userServ, User userResult, SecuritiesProperties secProp) {
		String befToken = new Date().toString() + userResult.getEmail() + userResult.getCreateDate() + secProp.getSALT();
		String aftToken = AuthHelper.bcrypt(secProp.getSTRENGTH(), befToken);
		Date date = new Date();
		DateTime dt = new DateTime();
		userResult.setUpdateDate(date);
		userResult.setToken(aftToken);
		userResult.setTokenExpire(dt.plusMinutes(secProp.getTOKEN_USAGE_DURATION_MIN()).toDate());
		userServ.save(userResult);
		return aftToken;
	}
	
	public boolean isEmailExist(String email, UserService userServ) {
		if(!userServ.findUserByEmail(email).isEmpty()) {
			return true;
		}
		return false;
	}

	public boolean isUsernameExist(String username, UserService userServ) {
		if(!userServ.findUserByUsername(username).isEmpty()) {
			return true;
		}
		return false;
	}

}
