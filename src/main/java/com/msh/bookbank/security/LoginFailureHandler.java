package com.msh.bookbank.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.msh.bookbank.domain.constant.EPortalUrl;

/**
 * 로그인 실패 핸들러
 * 여기서 담긴 exception이 로그인 페이지에서 alert로 출력된다.
 * @author moon
 * @since 2019.07.22.
 */
public class LoginFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		request.setAttribute("error", exception);
		request.getRequestDispatcher(EPortalUrl.SIGN_IN.getUrl()).forward(request, response);
	}

}
