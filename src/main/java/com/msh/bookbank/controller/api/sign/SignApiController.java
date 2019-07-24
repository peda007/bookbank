package com.msh.bookbank.controller.api.sign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.msh.bookbank.domain.model.UserVO;
import com.msh.bookbank.domain.object.ApiResponse;
import com.msh.bookbank.service.UserService;

/**
 * 회원가입 API 컨트롤러
 * @author moon
 * @since 2019.07.21
 */
@RestController
public class SignApiController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/signup")
	public ResponseEntity<ApiResponse> signup(@RequestBody UserVO user) {
		
		// 회원 가입
		userService.signup(user);
		
		// 별다른 예외 없을시 성공 처리
		return new ResponseEntity<ApiResponse>(ApiResponse.builder()
															.success(true)
															.message("회원가입에 성공했습니다.")
															.build(), 
															HttpStatus.CREATED);
	}

}
