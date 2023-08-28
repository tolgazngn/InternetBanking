package org.internetbanking.business.utils;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

public class MessageDigestUtil {

	public String encryptPassword(String password) throws NoSuchAlgorithmException{
		java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
	
		byte[] messageDigest = md.digest(password.getBytes());
		
		BigInteger bigInt = new BigInteger(1,messageDigest);
		
		return bigInt.toString(16);
	}
}