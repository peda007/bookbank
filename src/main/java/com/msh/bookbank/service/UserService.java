package com.msh.bookbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msh.bookbank.domain.exception.NoApiValueException;
import com.msh.bookbank.domain.exception.UserAlreadyExistException;
import com.msh.bookbank.domain.model.UserVO;
import com.msh.bookbank.repository.UserRepository;
import com.msh.bookbank.security.CustomPasswordEncoder;
import com.msh.bookbank.util.CommonUtil;

/**
 * 사용자 서비스
 * @author moon
 * @since 2019.07.21.
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	/**
	 * 사용자 이름으로 검색하여 사용자 정보 반환
	 * @param username
	 * @return
	 */
	public UserVO getUser(String username) {
		return userRepo.findByUsername(username);
	}
	
	/**
	 * 회원 가입
	 * @param newUser
	 */
	public void signup(UserVO newUser) {
		// username이나 password 미입력 시
		if(CommonUtil.isNullOrEmpty(newUser.getUsername()) || CommonUtil.isNullOrEmpty(newUser.getPassword())) { 
			throw new NoApiValueException("이름이나 비밀번호가 누락됐습니다.");
		}
		
		// 이미 존재하는 회원이 있는지 확인
		UserVO user = userRepo.findByUsername(newUser.getUsername());
		if(user != null) {
			throw new UserAlreadyExistException("이미 존재하는 사용자 이름입니다.");
		}
		
		// 신규 가입하는 사용자의 비밀번호 암호화
		newUser.setPassword(new CustomPasswordEncoder().encode(newUser.getPassword()));
		
		// DB에 저장
		userRepo.save(newUser);
	}
	
}
