package com.msh.bookbank.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 메인 화면
 * @author moon
 * @since 2019.07.23.
 */
@Controller
public class RootController {

	@GetMapping({"/", "/index"})
	public String index() {
		return "/index";
	}
	
}
