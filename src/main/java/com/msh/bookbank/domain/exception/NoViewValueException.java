package com.msh.bookbank.domain.exception;

/**
 * view단에서 파라미터 값이 없을 때 발생하는 예외
 * @author moon
 * @since 2019.07.24.
 */
public class NoViewValueException extends RuntimeException  {

	private static final long serialVersionUID = -5791102607425361396L;

	public NoViewValueException(String msg) {
		super(msg);
	}
	
}
