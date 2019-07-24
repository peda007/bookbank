package com.msh.bookbank.domain.param;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.msh.bookbank.util.CommonUtil;

/**
 * 카카오 API에 검색 요청을 하게 될 검색 파라미터
 * @author moon
 * @since 2019.07.21.
 */
public class KakaoSearchParam extends SearchParam implements ParamUrlCreatable {

	private String query;
	private int page;
	private String isbn;
	
	public KakaoSearchParam(SearchParam param) {
		this.query = param.getQuery();
		this.page = param.getPage();
		this.isbn = param.getIsbn();
	}
	
	@Override
	public String createParamUrl() {
		StringBuilder sb = new StringBuilder("?query=");
		
		if(CommonUtil.isNullOrEmpty(isbn)) {
			// isbn이 없음 => list 검색
			try {
				sb.append(URLEncoder.encode(query, "UTF-8"));
				if(page > 0) {
					sb.append("&page=").append(page);
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} else {
			// isbn이 있음 => detail 검색
			sb.append(isbn.split(" ")[0])
				.append("&target=isbn");
		}
		
		return sb.toString();
	}
	
}
