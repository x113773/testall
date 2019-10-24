package com.ansel.testall.springsecurity5;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.ansel.testall.helper.HttpHelper;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Autowired
    private ObjectMapper objectMapper;

    private Logger logger = LoggerFactory.getLogger(getClass());

 	private RequestCache requestCache = new HttpSessionRequestCache();

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException,
			ServletException {
		boolean isAjax = HttpHelper.isAjaxRequest(request);
		if (isAjax) {
			JSONObject result = new JSONObject();
			result.put("success", false);
			result.put("message", objectMapper.writeValueAsString(exception.getMessage()));
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().print(result.toString());
			response.getWriter().flush();
		} else {
//			redirectStrategy.sendRedirect(request, response, targetUrl);
		}

	}
}