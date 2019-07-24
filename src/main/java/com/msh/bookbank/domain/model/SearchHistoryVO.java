package com.msh.bookbank.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 검색 기록 엔티티
 * @author moon
 * @since 2019.07.22.
 */
@Entity
@Getter
@Builder
@Table(name="search_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SearchHistoryVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int searchSeq;
	
	@ManyToOne
	@JoinColumn(name="userSeq")
	private UserVO user;
	
	@ManyToOne
	@JoinColumn(name="keywordSeq")
	private KeywordVO keyword;
	
	private String datetime;

}
