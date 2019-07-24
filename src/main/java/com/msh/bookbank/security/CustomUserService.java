package com.msh.bookbank.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.msh.bookbank.domain.model.UserVO;
import com.msh.bookbank.service.UserService;

/**
 * 커스텀 userService
 * @author moon
 * @since 2019.07.20.
 */
@Service
public class CustomUserService implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserVO user = userService.getUser(username);
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("USER"));
		if(user == null) {
			throw new InternalAuthenticationServiceException("닉네임이나 비밀번호를 다시 확인해 주세요.");
		}
		return new User(user.getUsername(), user.getPassword(), authorities);
	}

}
