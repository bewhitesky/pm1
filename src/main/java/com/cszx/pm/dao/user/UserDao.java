package com.cszx.pm.dao.user;

import java.util.List;
import java.util.Map;

import com.cszx.pm.model.user.User;

public interface UserDao {
    User getUserByLoginId(String loginId);
    
    List<User> getUsers();
    
    List<User> selectUsers(Map<String, Object> userMap);
    
    int insert(User record);
    
	void insertUsers(List<User> users);

	int updateByPrimaryKeySelective(User record);
	
	void deleteUsers(List<String> idList);
}