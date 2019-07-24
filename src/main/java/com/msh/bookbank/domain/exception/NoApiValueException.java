package com.msh.bookbank.domain.exception;

/**
 * 입력값이 null이거나 empty로,
 * 다음 프로세스를 진행할 수 없을 때 발생하는 예외
 * @author moon
 * @since 2019.07.23.
 */
public class NoApiValueException extends RuntimeException {

	private static final long serialVersionUID = 1559572922008016328L;
	
	public NoApiValueException(String msg) {
		super(msg);
	}

}
