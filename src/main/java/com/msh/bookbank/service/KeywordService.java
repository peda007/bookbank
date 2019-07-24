package com.msh.bookbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msh.bookbank.domain.model.KeywordVO;
import com.msh.bookbank.repository.KeywordRepository;

/**
 * 키워드 서비스
 * @author moon
 * @since 2019.07.24.
 */
@Service
public class KeywordService {
	
	@Autowired
	private KeywordRepository keywordRepo;
	
	/**
	 * 키워드를 저장
	 * @param keyword
	 */
	public void addKeyword(KeywordVO keyword) {
		keywordRepo.save(keyword);
	}
	
	/**
	 * 키워드를 가져옴
	 * @param keyword
	 * @return
	 */
	public KeywordVO getKeyword(String keyword) {
		return keywordRepo.findByKeyword(keyword);
	}
	
	/**
	 * 인기 검색어 10개를 가져옴
	 * @return
	 */
	public List<KeywordVO> getHotKeywordList() {
		return keywordRepo.findTop10ByOrderByCntDesc();
	}
}
