package com.msh.bookbank.domain.exception;

/**
 * 가입시 유저가 이미 존재할 때 발생하는 예외
 * @author moon
 * @since 2019.07.23.
 */
public class UserAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = -9164156602519759055L;

	public UserAlreadyExistException(String msg) {
		super(msg);
	}
}
