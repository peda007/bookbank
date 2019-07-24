package com.msh.bookbank.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.msh.bookbank.domain.model.UserVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class getUserTest {

	@Autowired
	private UserRepository userRepo;
	
	/**
	 * 테스트를 위해 user 정보 입력
	 */
	@Before
	public void init() {
		userRepo.save(UserVO.builder()
				.username("SeonHo Moon")
				.password("msh1234")
				.build());
		
		userRepo.save(UserVO.builder()
				.username("Steve Jobs")
				.password("iloveapple")
				.build());
		
		userRepo.save(UserVO.builder()
				.username("Bill Gates")
				.password("windows")
				.build());
	}
	
	/**
	 * 테스트 이후 다른 테스트의 영향 없도록
	 * repository 전체 비우는 코드
	 */
	@After
	public void cleanUp() {
		userRepo.deleteAll();
	}
	
	/**
	 * 사용자 불러오기 테스트
	 */
	@Test
	public void getUser() {
		
		UserVO user = userRepo.findByUsername("SeonHo Moon");
		
		assertThat(user.getUsername(), is("SeonHo Moon"));
		assertThat(user.getPassword(), is("msh1234"));
	}
	
}
