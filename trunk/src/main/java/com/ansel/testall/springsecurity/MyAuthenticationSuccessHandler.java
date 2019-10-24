package com.ansel.testall.springsecurity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.ansel.testall.helper.HttpHelper;
/**
 * 备用
 * @author Administrator
 *
 */
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private RequestCache requestCache = new HttpSessionRequestCache();
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException,
			ServletException {
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		String targetUrl = savedRequest.getRedirectUrl();
		boolean isAjax = HttpHelper.isAjaxRequest(request);
		if (isAjax) {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("url", targetUrl);
			response.getWriter().print(result.toString());
			response.getWriter().flush();
		} else {
			redirectStrategy.sendRedirect(request, response, targetUrl);
		}

		System.out.println("Redirecting to DefaultSavedRequest Url: " + targetUrl);
	}

}
