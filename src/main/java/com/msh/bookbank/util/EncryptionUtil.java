package com.msh.bookbank.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 암호화 유틸
 * @author moon
 * @since 2019.07.20.
 */
public class EncryptionUtil {

	/**
	 * SHA-512 암호화
	 * @param value
	 * @return
	 */
	public static String sha512(String value) {
		MessageDigest digest;
		String result = null;
		try {
			digest = MessageDigest.getInstance("SHA-512");
			digest.reset();
			digest.update(value.getBytes("utf8"));
			result = String.format("%0128x", new BigInteger(1, digest.digest()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;

	}
	
}
