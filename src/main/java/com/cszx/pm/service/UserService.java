package com.cszx.pm.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cszx.common.exception.ServiceException;
import com.cszx.pm.model.user.User;

public interface UserService {
	/**
	 * 通过loginId获取用户信息
	 * 
	 * @param loginId
	 * @return
	 */
	User getUserByLoginId(String loginId);

	/**
	 * 获取所有用户信息
	 * 
	 * @return
	 */
	List<User> getUsers();

	/**
	 * 导出excel
	 * 
	 * @return
	 */
	XSSFWorkbook exportExcelInfo();

	/**
	 * 导入excel
	 * 
	 * @param in
	 * @param file
	 * @throws ServiceException
	 */
	void importExcelInfo(InputStream in, CommonsMultipartFile file) throws ServiceException;

	/**
	 * 查询用户
	 * 
	 * @param userMap
	 * @return
	 */
	List<User> selectUsers(Map<String, Object> userMap);

	/**
	 * 添加用户
	 * 
	 * @param user
	 * @throws ServiceException
	 */
	void addUser(User user) throws ServiceException;

	/**
	 * 更新用户
	 * 
	 * @param user
	 * @throws ServiceException
	 */
	void updateUser(User user) throws ServiceException;

	/**
	 * 删除用户
	 * 
	 * @param idList
	 * @throws ServiceException
	 */
	void deleteUsers(List<String> idList) throws ServiceException;

	Set<String> findRolesByLoginId(String loginId);

	List<String> getRolesByLoginId(String loginId);

	// Set<String> findPermissionsByLoginId(String loginId);
}
