package com.msh.bookbank.advisor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.msh.bookbank.domain.exception.NoApiValueException;
import com.msh.bookbank.domain.exception.UserAlreadyExistException;
import com.msh.bookbank.domain.object.ApiResponse;

/**
 * Restful API 예외 발생 시,
 * Advisor를 통해 ApiResponse와 HttpStatus를 반환
 * @author moon
 * @since 2019.07.23.
 */
@RestControllerAdvice
public class ApiAdvisor {

	/**
	 * 값 없을 때 발생하는 예외
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value={NoApiValueException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiResponse noValueException(NoApiValueException e) {
		return getResponse(e);
	}
	
	/**
	 * 회원 가입시,
	 * 이미 존재하는 회원이 있을 때 발생하는 예외
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value={UserAlreadyExistException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiResponse userAlreadyExistException(UserAlreadyExistException e) {
		return getResponse(e);
	}
	
	/**
	 * ApiResponse builder를 사용한 인스턴스 생성 메서드
	 * @param e
	 * @return
	 */
	private <E extends RuntimeException> ApiResponse getResponse(E e) {
		return ApiResponse.builder()
							.success(false)
							.message(e.getMessage())
							.build();
	}
}
