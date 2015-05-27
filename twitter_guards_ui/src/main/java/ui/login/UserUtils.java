package ui.login;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class UserUtils {
	
	public static String getPasswordHash(String value, String salt) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest((value + salt).getBytes("UTF-8"));
			return new String(hash, "UTF-8");
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}


	public static String getRandomSalt() {
		return new BigInteger(130, new SecureRandom()).toString(32);
	}
}
