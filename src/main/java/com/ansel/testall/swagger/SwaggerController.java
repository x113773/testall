package com.ansel.testall.swagger;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ansel.testall.exception.ResponseResult;
import com.ansel.testall.mybatis.model.User;
import com.ansel.testall.mybatis.service.UserService;

@RestController
public class SwaggerController {

	@Autowired
	UserService userService;

	@ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
	@ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "Integer", paramType = "path")
	@RequestMapping(value = "/api/user/{userId}", method = RequestMethod.GET)
	public User getUser(@PathVariable() Integer userId) {
		User user = userService.getUserById(userId);
		return user;
	}

	@ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
	@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
	@ApiResponses({ @ApiResponse(code = 400, message = "请求参数没填好"), @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对", response = ResponseResult.class) })
	@RequestMapping(value = "/api/user", method = RequestMethod.POST)
	public String insertUser(@RequestBody User user) {
		userService.insertSelective(user);
		return "201";
	}

}
