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
 * 유저 엔티티
 * @author moon
 * @since 2019.07.21.
 */
@Entity
@Data
@Builder
@Table(name="user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userSeq;
	
	@Column(unique=true)
	private String username;
	
	private String password;
	
}
