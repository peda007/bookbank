package com.msh.bookbank.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msh.bookbank.config.ApiProperties;
import com.msh.bookbank.domain.param.PageParam;

/**
 * 페이지 유틸
 * @author moon
 * @since 2019.07.23.
 */
@Component
public class PageUtil {
	
	private static ApiProperties apiProps;
	
	/**
	 * 페이지가 한 화면에 보일 최대 갯수
	 * ex) 10으로 설정할 경우 10페이지까지는 한 화면에 보이고, 11페이지 이후는 다음 페이지로 이동해야 한다.
	 */
	private static final int PAGES_IN_ROW = 10;
	
	@Autowired
	public PageUtil(ApiProperties apiProps) {
		PageUtil.apiProps = apiProps;
	}
	
	/**
	 * 최대 페이지
	 * @param totalCount
	 * @return
	 */
	public static int getMaxPage(int totalCount) {
		return (totalCount % apiProps.getPageSize()) > 0 ? (totalCount / apiProps.getPageSize()) + 1 : (totalCount / apiProps.getPageSize());
	}
	
	/**
	 * 페이지 리스트 가져오기
	 * @param curPage
	 * @param totalCount
	 * @return
	 */
	public static List<Integer> getPageList(int curPage, int totalCount) {
		
		List<Integer> pageList = new ArrayList<>();
		
		int maxPage = getMaxPage(totalCount);
		
		int startPage = curPage < 1 ? 1 : ((curPage - 1) / PAGES_IN_ROW) * apiProps.getPageSize() + 1;
		int lastPage = startPage + apiProps.getPageSize() - 1 > maxPage ? maxPage : startPage + apiProps.getPageSize() - 1;
		
		for(int i=startPage; i<=lastPage; i++) {
			pageList.add(i);
		}
		
		return pageList;
	}
	
	/**
	 * 페이지 양 옆의 화살표(다음/이전 페이지로 이동하게 하는)를 생성하기 위한 정보
	 * @param curPage
	 * @param totalCount
	 * @return
	 */
	public static PageParam getPageParam(int curPage, int totalCount) {
		PageParam pageParam = new PageParam();
		if(curPage > PAGES_IN_ROW) {
			pageParam.setHasPrev(true);
			pageParam.setPrev(curPage - (curPage % PAGES_IN_ROW));
		}
		int maxPage = getMaxPage(totalCount);
		
		if(curPage < maxPage - ((maxPage - 1) % PAGES_IN_ROW)) {
			pageParam.setHasNext(true);
			pageParam.setNext(curPage - ((curPage - 1) % PAGES_IN_ROW) + PAGES_IN_ROW);
		}
		return pageParam;
	}
}
