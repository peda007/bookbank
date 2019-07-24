package com.msh.bookbank.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import com.msh.bookbank.domain.constant.EPortalUrl;

/**
 * 커스텀 스프링 시큐리티 설정
 * 기본이라고 생각되는 보안 옵션(CSRF 등)을 넣음 
 * @author moon
 * @since 2019.07.20.
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserService userService;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(	"/css/**",
									"/js/**",
									"/image/**",
									"/vendor/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers(EPortalUrl.SIGN_UP.getUrl(), EPortalUrl.API_SIGN_UP.getUrl())
				.permitAll()
				.anyRequest().authenticated()
				.and()
			.headers()
				.frameOptions().deny()
				.and()
			.exceptionHandling()
				.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint(EPortalUrl.SIGN_IN.getUrl()))
				.and()
			.formLogin()
				.failureHandler(failureHandler())
				.loginPage(EPortalUrl.SIGN_IN.getUrl())
				.loginProcessingUrl(EPortalUrl.SIGN_IN.getUrl())
				.defaultSuccessUrl("/index", true)
				.permitAll()
				.and()
			.logout()
				.logoutUrl(EPortalUrl.SIGN_OUT.getUrl())
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.and()
			.csrf();
		
		super.configure(http);
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider());
	}
	
	@Bean
	public AuthenticationFailureHandler failureHandler() {
		return new LoginFailureHandler();
	}
	
	public CustomAuthenticationProvider authProvider() {
		CustomAuthenticationProvider provider = new CustomAuthenticationProvider(passwordEncoder(), userService);
		return provider;
	}
	
	public PasswordEncoder passwordEncoder() {
		return new CustomPasswordEncoder();
	}
	
}
