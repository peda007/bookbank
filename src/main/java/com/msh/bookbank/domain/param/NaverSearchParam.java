package com.msh.bookbank.domain.param;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.msh.bookbank.util.CommonUtil;

/**
 * 네이버 API에 검색 요청을 하게 될 검색 파라미터
 * @author moon
 * @since 2019.07.22.
 */
public class NaverSearchParam extends SearchParam implements ParamUrlCreatable {

	private final int SIZE = 10;
	
	private String query;
	private int page;
	private String isbn;
	private int start;
	
	public NaverSearchParam(SearchParam param) {
		this.query = param.getQuery();
		this.page = param.getPage();
		this.isbn = param.getIsbn();
	}
	
	@Override
	public String createParamUrl() {
		StringBuilder sb = new StringBuilder();
		
		if(CommonUtil.isNullOrEmpty(isbn)) {
			// isbn이 없음 => list 검색
			try {
				sb.append("?query=")
					.append(URLEncoder.encode(query, "UTF-8"));
				if(page > 0) {
					start = (page - 1) * SIZE + 1;
					sb.append("&start=").append(start);
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} else {
			// isbn이 있음 => detail 검색
			sb.append("?d_isbn=").append(isbn.split(" ")[0]);
		}
		
		return sb.toString();
	}
	
}
