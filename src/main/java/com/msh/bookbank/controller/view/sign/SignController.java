package com.msh.bookbank.controller.view.sign;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.msh.bookbank.domain.constant.EPortalUrl;

/**
 * 로그인, 회원가입등의 사용자 계정 관리 뷰 컨트롤러
 * @author moon
 * @since 2019.07.20.
 */
@Controller
public class SignController {
	
	@RequestMapping("/signin")
	public String signin(Model model) {
		model.addAttribute("apiUrlSignin", EPortalUrl.SIGN_IN.getUrl());
		return "/sign/in";
	}
	
	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("apiUrlSignup", EPortalUrl.API_SIGN_UP.getUrl());
		return "/sign/up";
	}
	
}
