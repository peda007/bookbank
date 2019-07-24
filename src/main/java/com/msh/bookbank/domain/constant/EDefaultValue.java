package com.msh.bookbank.domain.constant;

import lombok.Getter;

/**
 * API로 받아온 값이 없을 때 사용되는 기본 값
 * @author moon
 * @since 2019.07.23.
 */
@Getter
public enum EDefaultValue {
	
	NO_ISBN("-"),
	NO_THUMBNAIL("https://www.kitajimakensetsu.co.jp/wp-content/plugins/lightbox/images/No-image-found.jpg"),
	NO_TITLE("제목 없음"),
	
	NO_AUTHORS("알 수 없음"),
	NO_TRANSLATORS("-"),
	
	NO_PUBLISHER("출판사 정보<br/>없음"),
	NO_PUBLISH_DATE("출판일 정보<br/>없음"),
	
	NO_PRICE("가격 정보<br/>없음"),
	NO_DISCOUNTED_PRICE("-"),
	
	NO_DESCRIPTION("설명 없음");
	
	private String value;
	
	EDefaultValue(String value) {
		this.value = value;
	}
	
	
}
