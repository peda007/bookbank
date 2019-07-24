package com.msh.bookbank.domain.constant;

import java.util.function.Function;

import org.apache.http.client.methods.HttpGet;
import org.json.JSONObject;

import com.msh.bookbank.util.CommonUtil;

/**
 * 도서 검색 API 정보
 * @author moon
 * @since 2019.07.22.
 */
public enum EApiType {

	KAKAO("errorType", json -> json.getJSONObject("meta").getInt("pageable_count"), "https://dapi.kakao.com/v3/search/book", null, "Authorization:KakaoAK 32097ae47aec4f98486191b0394ee2cf"),
	NAVER("errorMessage", json -> json.getInt("total"), "https://openapi.naver.com/v1/search/book.json","https://openapi.naver.com/v1/search/book_adv.xml", "X-Naver-Client-Id:U6cOFvsj9hEwJue3i8Di", "X-Naver-Client-Secret:zdg5j0HrSW"),
	;
	
	private String errorKey;
	private String listApiUrl;
	private String detailApiUrl;
	private String[] headers;
	private Function<JSONObject, Integer> expression;
	
	EApiType(String errorKey, Function<JSONObject, Integer> expression, String listApiUrl, String detailApiUrl, String ...headers) {
		this.errorKey = errorKey;
		this.expression = expression;
		this.listApiUrl = listApiUrl;
		this.detailApiUrl = detailApiUrl;
		this.headers = headers;
	}
	
	/**
	 * API 주소 불러오기
	 * @param paramStr
	 * @param searchType
	 * @return
	 */
	public String getApiUrl(String paramStr, ESearchType searchType) {
		String apiUrl = null;
		switch(searchType) {
		case LIST:
			apiUrl = listApiUrl + paramStr; 
			break;
		case DETAIL:
			if(CommonUtil.isNullOrEmpty(detailApiUrl)) {
				detailApiUrl = listApiUrl;
			}
			apiUrl = detailApiUrl + paramStr;
			break;
		}
		return apiUrl;
	}
	
	/**
	 * http 요청시 헤더에 인증 정보 추가
	 * @param http
	 */
	public void initHeaders(HttpGet http) {
		for(String header : headers) {
			String[] keyValue = header.split(":");
			http.addHeader(keyValue[0], keyValue[1]);			
		}
	}

	/**
	 * error키 값 확인시 사용
	 * @return
	 */
	public String getErrorKey() {
		return errorKey;
	}
	
	/**
	 * API에서 반환하는 totalCount값
	 * @param json
	 * @return
	 */
	public int getTotalCount(JSONObject json) {
		return expression.apply(json);
	}
}
