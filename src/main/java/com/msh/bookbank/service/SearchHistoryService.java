package com.msh.bookbank.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.msh.bookbank.domain.model.KeywordVO;
import com.msh.bookbank.domain.model.SearchHistoryVO;
import com.msh.bookbank.repository.SearchHistoryRepository;

/**
 * 검색 기록 서비스
 * @author moon
 * @since 2019.07.24.
 */
@Service
public class SearchHistoryService {

	@Autowired
	private UserService userService;

	@Autowired
	private KeywordService keywordService;
	
	@Autowired
	private SearchHistoryRepository historyRepo;
	
	/**
	 * 검색 기록 저장
	 * 트랜잭션 처리 및 DB Lock을 걸어서 대용량 처리에도 데이터의 정확도를 보장
	 * @param query
	 * @param username
	 */
	@Transactional
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	public void saveHistory(String query, String username) {
		
		KeywordVO targetKeyword; 
		
		KeywordVO keyword = keywordService.getKeyword(query);
		if(keyword == null) {
			// 키워드가 없으면 INSERT
			keywordService.addKeyword(KeywordVO.builder()
												.keyword(query)
												.cnt(1)
												.build());
			
			targetKeyword = keywordService.getKeyword(query);
		} else {
			keyword.setCnt(keyword.getCnt() + 1);
			targetKeyword = keyword;
		}
		
		historyRepo.save(SearchHistoryVO.builder()
					.user(userService.getUser(username))
					.keyword(targetKeyword)
					.datetime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
					.build());
		
	}
	
	/**
	 * 개인 검색 기록 가져오기
	 * @param username
	 * @return
	 */
	public List<SearchHistoryVO> getMyHistoryList(String username) {
		return historyRepo.findTop10ByUserOrderByDatetimeDesc(userService.getUser(username));
	}
		
}
