package com.denisgl.models;

import com.denisgl.dao.hash.Hashing;
import com.denisgl.dao.jdbc.UserVerification;

public class Authenticator {
	
	
	public static boolean isSignedUp(String phoneOrEmail, String password) {
		password = Hashing.sha1(password);
		if(phoneOrEmail.contains("@")) {
			return UserVerification.authenticateWithEmail(phoneOrEmail, password);
		} else {
			return UserVerification.authenticateWithPhone(phoneOrEmail, password);
		}
	}
}
