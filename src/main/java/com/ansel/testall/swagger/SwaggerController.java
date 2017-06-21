package com.ansel.testall.swagger;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.boot.context.embedded.MimeMappings;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ansel.testall.mybatis.model.Error400;
import com.ansel.testall.mybatis.model.User;


@RestController
public class SwaggerController {

	
	@ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
	@RequestMapping(value = "/api/{id}", method = RequestMethod.GET)
	public Error400 getUser(@PathVariable Long id) {
		Error400 er = new Error400();
		return er;
	}

	@ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
	@ApiResponses({ @ApiResponse(code = 400, message = "请求参数没填好"), @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对", response = Error400.class) })
	@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
	@RequestMapping(value = "/api/user", method = RequestMethod.POST)
	public String postUser(@RequestBody User user) {
		return "success";
	}
	
}
