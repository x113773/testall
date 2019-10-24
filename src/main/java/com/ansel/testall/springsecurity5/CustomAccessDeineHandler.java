package com.ansel.testall.springsecurity5;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.alibaba.fastjson.JSONObject;

public class CustomAccessDeineHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
    	JSONObject result = new JSONObject();
		result.put("success", false);
		result.put("message", "请先登录！");
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(result.toString());
		response.getWriter().flush();
    }

}
