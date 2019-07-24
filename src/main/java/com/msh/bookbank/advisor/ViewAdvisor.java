package com.msh.bookbank.advisor;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.msh.bookbank.domain.exception.NoViewValueException;
import com.msh.bookbank.domain.exception.PageIsMoreThanMaxException;
import com.msh.bookbank.domain.exception.UnknownException;

/**
 * 뷰 페이지에서 발생하는 예외 처리 어드바이저
 * 각 exception의 메시지를 model에 담아 리턴한다
 * @author moon
 * @since 2019.07.24.
 */
@ControllerAdvice
public class ViewAdvisor {

	@ExceptionHandler(value={NoViewValueException.class, PageIsMoreThanMaxException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String searcjApiCallException(RuntimeException e, Model model) {
		model.addAttribute("error", e);
		return "/error/400";
	}
	
	@ExceptionHandler(value={UnknownException.class})
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String unknownException(UnknownException e, Model model) {
		model.addAttribute("error", e);
		return "/error/500";
	}
}
