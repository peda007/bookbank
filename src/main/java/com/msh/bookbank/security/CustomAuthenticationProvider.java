package com.msh.bookbank.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 커스텀 스프링 시큐리티 authentication provider
 * 로그인 인증에 필요한 최소한의 기능만 구현
 * @author moon
 * @since 2019.07.20.
 */
public class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
	
	private PasswordEncoder passwordEncoder;
	
	private CustomUserService userService;
	
	public CustomAuthenticationProvider(PasswordEncoder passwordEncoder, CustomUserService userService) {
		this.passwordEncoder = passwordEncoder;
		this.userService = userService;
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		
		String password = authentication.getCredentials().toString();
		
		User user = (User) userService.loadUserByUsername(username);
		if (user == null) {
			throw new InternalAuthenticationServiceException("존재하지 않는 계정입니다. 회원 가입후 이용해 주세요.");
		}
		
		if(!passwordEncoder.matches(password, user.getPassword())) {
			throw new BadCredentialsException("닉네임이나 비밀번호를 다시 확인해 주세요.");
		}
		
		return user;
	}

}
