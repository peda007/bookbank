package com.msh.bookbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msh.bookbank.domain.model.SearchHistoryVO;
import com.msh.bookbank.domain.model.UserVO;

/**
 * 검색 기록 리포지터리
 * @author moon
 * @since 2019.07.23.
 */
public interface SearchHistoryRepository extends JpaRepository<SearchHistoryVO, Long> {

	public List<SearchHistoryVO> findTop10ByUserOrderByDatetimeDesc(UserVO user);
	
}
