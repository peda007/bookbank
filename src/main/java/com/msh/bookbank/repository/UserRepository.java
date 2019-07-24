package com.msh.bookbank.repository;

import org.springframework.data.repository.CrudRepository;

import com.msh.bookbank.domain.model.UserVO;

/**
 * 유저 리포지터리
 * 다른 리포지터리들과 다르게 정렬 및 페이징 기능등이 필요 없으므로 CrudRepository 상속
 * @author moon
 * @since 2019.07.21.
 */
public interface UserRepository extends CrudRepository<UserVO, Long> {

	public UserVO findByUsername(String username);
	
	
}
