package com.msh.bookbank.domain.constant;

import lombok.Getter;

/**
 * Book Bank가 제공하는 API URL들
 * @author moon
 * @since 2019.07.20.
 */
@Getter
public enum EPortalUrl {

	SIGN_IN("/signin"),
	SIGN_UP("/signup"),
	SIGN_OUT("/signout"),
	
	API_SIGN_UP("/api/signup"),
	;
	
	private String url;
	
	EPortalUrl(String url) {
		this.url = url;
	}

}
