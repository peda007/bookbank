package com.msh.bookbank.domain.exception;

/**
 * API에서 제공하는 페이지보다 큰 페이지를 요청할 때 발생하는 예외
 * @author moon
 * @since 2019.07.24.
 */
public class PageIsMoreThanMaxException extends RuntimeException {

	private static final long serialVersionUID = 4614942845488734209L;

	public PageIsMoreThanMaxException(String msg) {
		super(msg);
	}
	
}
