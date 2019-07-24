package com.msh.bookbank.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.msh.bookbank.util.EncryptionUtil;

/**
 * 커스텀 비밀번호 암호화 인코더
 * @author moon
 * @since 2019.07.20.
 */
public class CustomPasswordEncoder implements PasswordEncoder {

	private final String PREFIX = "SeonHo";
	private final String SUFFIX = "Moon";
	
	@Override
	public String encode(CharSequence rawPassword) {
		String plainStr = PREFIX + rawPassword + SUFFIX;
		String encoded = EncryptionUtil.sha512(plainStr);
		return encoded;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return encode(rawPassword).equals(encodedPassword);
	}

}
