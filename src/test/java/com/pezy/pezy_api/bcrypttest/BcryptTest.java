package com.pezy.pezy_api.bcrypttest;

import org.eclipse.jgit.transport.CredentialItem.Password;
import org.junit.Test;

import com.pezy.pezy_api.helper.AuthHelper;

public class BcryptTest {
	
	private final String PASSWORD = "Pezy2019";
	private final String SALT = "PezySalt";
	
	@Test
	public void testBcrypt() {
		String pwd = AuthHelper.bcrypt(8, PASSWORD + SALT);
		System.out.println(pwd);
		System.out.println(AuthHelper.verifyPassword(PASSWORD + SALT, pwd));
	}
	
}
