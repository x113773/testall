package com.ansel.testall.mybatis.mapper;

import java.util.List;

import com.ansel.testall.mybatis.model.Role;

public interface RoleMapper {
	int deleteByPrimaryKey(Integer roleId);

	int insert(Role record);

	int insertSelective(Role record);

	Role selectByPrimaryKey(Integer roleId);

	int updateByPrimaryKeySelective(Role record);

	int updateByPrimaryKey(Role record);

	List<String> listRoleTypeByUsername(String username);
}