package com.ansel.testall.springsecurity5;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.ansel.testall.helper.HttpHelper;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper;

    private Logger logger = LoggerFactory.getLogger(getClass());
 	private RequestCache requestCache = new HttpSessionRequestCache();
 	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
 	
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		String targetUrl = "";
		if (savedRequest != null) {
			targetUrl = savedRequest.getRedirectUrl();
		}
		boolean isAjax = HttpHelper.isAjaxRequest(request);
		if (isAjax) {
			JSONObject result = new JSONObject();
			result.put("url", targetUrl);
			result.put("success", true);
			result.put("message", "");
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().print(result.toString());
			response.getWriter().flush();
		} else {
			redirectStrategy.sendRedirect(request, response, targetUrl);
		}

		System.out.println("Redirecting to DefaultSavedRequest Url: " + targetUrl);
    }
}