package com.msh.bookbank.domain.param;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 검색 파라미터로써, 다른 검색 파라미터들의 부모 클래스 역할
 * @author moon
 * @since 2019.07.22.
 */
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SearchParam {

	private String query;
	private int page;
	
	private String isbn;
	
}
