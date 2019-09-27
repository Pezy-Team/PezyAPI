package com.pezy.pezy_api.helper;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class AuthHelper {
	
	/**
	 * @see https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security
	 * @param strength
	 * @param rawpassword
	 * @return String hashed password
	 */
	public static String bcrypt(int strength, String rawpassword) {
		return BCrypt.hashpw(rawpassword, BCrypt.gensalt(strength));
	}
	
	/**
	 * @see https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security
	 * @param rawpassword
	 * @param hashedpassword
	 * @return boolean | Return true if can verify.
	 */
	public static boolean verifyPassword(String rawpassword, String hashedpassword) {
		return BCrypt.checkpw(rawpassword, hashedpassword);
	}
	
	
	
}