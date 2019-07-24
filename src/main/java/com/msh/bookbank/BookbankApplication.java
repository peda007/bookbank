package com.msh.bookbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Book Bank의 메인 메서드가 존재하는 클래스
 * @author moon
 * @since 2019.07.20.
 */
@SpringBootApplication
public class BookbankApplication extends SpringBootServletInitializer  {

	@Override 
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BookbankApplication.class); 
	}
	
	public static void main(String[] args) {
		SpringApplication.run(BookbankApplication.class, args);
	}

}
