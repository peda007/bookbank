package com.msh.bookbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msh.bookbank.domain.model.KeywordVO;

/**
 * 키워드 리포지터리
 * @author moon
 * @since 2019.07.24.
 */
public interface KeywordRepository extends JpaRepository<KeywordVO, Long> {

	public KeywordVO findByKeyword(String keyword);
	
	public List<KeywordVO> findTop10ByOrderByCntDesc();
}
