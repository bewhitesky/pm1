package com.cszx.pm.dao.role;

import java.util.List;

import com.cszx.pm.model.role.Role;

public interface RoleDao {
	int deleteByPrimaryKey(String id);

	int insert(Role record);

	int insertSelective(Role record);

	Role selectByPrimaryKey(String id);

	List<Role> findRolesByLoginId(String loginId);

	int updateByPrimaryKeySelective(Role record);

	int updateByPrimaryKey(Role record);

	List<String> getRolesByLoginId(String loginId);

}