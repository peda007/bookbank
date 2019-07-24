package com.msh.bookbank.domain.exception;

/**
 * 알 수 없는 예외 발생시
 * @author moon
 * @since 2019.07.24.
 */
public class UnknownException extends RuntimeException {

	private static final long serialVersionUID = 2818347399270201112L;

	public UnknownException(String msg) {
		super(msg);
	}
	
}
