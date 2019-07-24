package com.msh.bookbank.domain.param;

/**
 * 이 인터페이스를 상속받은 param 클래스들의 URL 생성 가능을 보장
 * @author moon
 * @since 2019.07.21.
 */
public interface ParamUrlCreatable {

	public String createParamUrl();
	
}
