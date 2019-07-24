package com.msh.bookbank.domain.constant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.json.JSONArray;

import com.msh.bookbank.util.CommonUtil;

/**
 * 책의 속성별 파싱 처리
 * @author moon
 * @since 2019.07.23.
 */
public enum EBookAttr {

	ISBN(EDefaultValue.NO_ISBN, null, null),
	ISBN_SPLIT(EDefaultValue.NO_ISBN, v -> v.substring(v.indexOf(" ")+1), v -> v.substring(v.indexOf(" ")+1)),
	TITLE(EDefaultValue.NO_TITLE, null, null),
	THUMBNAIL(EDefaultValue.NO_THUMBNAIL, null, null),
	
	AUTHORS(EDefaultValue.NO_AUTHORS, null, v -> v.replaceAll("\\|", "<br/>")),
	TRANSLATORS(EDefaultValue.NO_TRANSLATORS, null, null),
	
	PUBLISHER(EDefaultValue.NO_PUBLISHER, null, null),
	PUBLISH_DATE(EDefaultValue.NO_PUBLISH_DATE, 
					v -> LocalDateTime.parse(v, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSz")).format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")), 
					v -> LocalDate.parse(v, DateTimeFormatter.ofPattern("yyyyMMdd")).format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"))),
	
	PRICE(EDefaultValue.NO_PRICE, null, null),
	DISCOUNTED_PRICE(EDefaultValue.NO_DISCOUNTED_PRICE, null, null),
	
	DESCRIPTION(EDefaultValue.NO_DESCRIPTION, null, null);
	
	
	private Function<String, String> kakaoExpression;
	private Function<String, String> naverExpression;
	private EDefaultValue defaultValue;
	
	/**
	 * 생성자
	 * 기본값(null 이거 empty일 때)과 각 API별 표현
	 * @param defaultValue
	 * @param kakaoExpression
	 * @param naverExpression
	 */
	EBookAttr(EDefaultValue defaultValue, Function<String, String> kakaoExpression, Function<String, String> naverExpression) {
		this.defaultValue = defaultValue;
		this.kakaoExpression = kakaoExpression;
		this.naverExpression = naverExpression;
	}
	
	/**
	 * API별로 책의 속성 파싱
	 * @param orgValue
	 * @param apiType
	 * @return
	 */
	public String withFormat(Object orgValue, EApiType apiType) {
		String resultValue = "";

		String paramValue = null;
		try {
			if(orgValue instanceof Integer) {
				Integer iValue = (Integer)orgValue;
				if(iValue <= 0) {
					throw new NullPointerException();
				} else {
					paramValue = String.format("%,d원", iValue);
				}
			} else if(orgValue instanceof JSONArray) {
				JSONArray jArr = (JSONArray)orgValue;
				if(CommonUtil.isNullOrEmpty(jArr)) {
					throw new NullPointerException();
				} else {
					paramValue = jArr.toList().stream().map(Object::toString).collect(Collectors.joining("<br/>"));
				}
			} else {
				String sValue = (String)orgValue;
				if(CommonUtil.isNullOrEmpty(sValue)) {
					throw new NullPointerException();
				} else {
					paramValue = sValue;
				}
			}
		} catch(NullPointerException e) {
			paramValue = defaultValue.getValue();
		}
		
		switch(apiType) {
		case KAKAO:
			resultValue = kakaoExpression == null ? paramValue : kakaoExpression.apply(paramValue);
			break;
		case NAVER:
			resultValue = naverExpression == null ? paramValue : naverExpression.apply(paramValue);
			break;
		}
		
		return resultValue;
	}
}
