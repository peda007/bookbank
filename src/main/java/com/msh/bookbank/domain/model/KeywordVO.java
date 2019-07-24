package com.msh.bookbank.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * history 테이블에서 query로 인기 검색어를 가져올 수 있으나,
 * 데이터가 많아질 수록 group by 후 order by로 인한 성능 하락 이슈가 있기 때문에
 * keyword 엔티티를 통해 count를 계산
 * @author moon
 * @since 2019.07.23.
 */
@Entity
@Data
@Builder
@Table(name="keyword")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class KeywordVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int keywordSeq;
	
	@Column(unique=true)
	private String keyword;
	
	private int cnt;
	
}
