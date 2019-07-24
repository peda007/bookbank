package com.msh.bookbank.domain.param;

import lombok.Data;

/**
 * Pagination 기능을 제공하기 위한 parameter object
 * 책 정보가 DB에 있는것이 아니므로 JPA의 Pagination기능을 활용 할 수 없기에 생성
 * @author moon
 * @since 2019.07.23.
 */
@Data
public class PageParam {

	private boolean hasNext;
	private boolean hasPrev;
	
	private int next;
	private int prev;
	
}
