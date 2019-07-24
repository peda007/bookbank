package com.msh.bookbank.domain.exception;

/**
 * Restful API가 처리에 실패했을 때 발생하는 예외
 * @author moon
 * @since 2019.07.22.
 */
public class ApiFailException extends Exception {

	private static final long serialVersionUID = -4161173807162072124L;

	public ApiFailException() {}
	
	public ApiFailException(String message) {
		super(message);
	}
	
}
