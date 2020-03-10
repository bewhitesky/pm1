package com.cszx.pm.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cszx.common.excel.ExcelBean;
import com.cszx.common.excel.ExcelUtil;
import com.cszx.common.exception.ExceptionCode;
import com.cszx.common.exception.ServiceException;
import com.cszx.pm.dao.role.RoleDao;
import com.cszx.pm.dao.user.UserDao;
import com.cszx.pm.model.user.User;
import com.cszx.pm.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource
	UserDao userDao;
	// @Resource
	// private PermissionDao permissionDao;
	@Resource
	private RoleDao roleDao;

	@Override
	public User getUserByLoginId(String loginId) {
		return userDao.getUserByLoginId(loginId);
	}

	@Override
	public List<User> getUsers() {
		List<User> users = userDao.getUsers();
		return users;
	}

	@Override
	public List<User> selectUsers(Map<String, Object> userMap) {
		return userDao.selectUsers(userMap);
	}

	@Override
	public void addUser(User user) throws ServiceException {
		try {
			userDao.insert(user);
		} catch (Exception e) {
			throw new ServiceException(ExceptionCode.add_failure, e);
		}

	}

	@Override
	public void updateUser(User user) throws ServiceException {
		try {
			userDao.updateByPrimaryKeySelective(user);
		} catch (Exception e) {
			throw new ServiceException(ExceptionCode.update_failure, e);
		}

	}

	@Override
	public void deleteUsers(List<String> idList) throws ServiceException {
		try {
			userDao.deleteUsers(idList);
		} catch (Exception e) {
			throw new ServiceException(ExceptionCode.delete_failure, e);
		}
	}

	@Override
	public XSSFWorkbook exportExcelInfo() {
		XSSFWorkbook xssfWorkbook = null;
		try {
			List<User> users = userDao.getUsers();
			List<ExcelBean> excel = new ArrayList<>();
			Map<Integer, List<ExcelBean>> map = new LinkedHashMap<>();

			// 设置标题栏
			excel.add(new ExcelBean("ID", "id", 0, false));
			excel.add(new ExcelBean("登录账号", "loginId", 0, false));
			excel.add(new ExcelBean("用户名", "userName", 0, false));
			map.put(0, excel);
			String sheetName = "userInfo";
			xssfWorkbook = ExcelUtil.createExcelFile(User.class, users, map, sheetName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xssfWorkbook;
	}

	@Override
	public void importExcelInfo(InputStream in, CommonsMultipartFile file) throws ServiceException {
		try {
			List<List<Object>> listob = ExcelUtil.getBankListByExcel(in, file.getOriginalFilename());
			List<User> users = new ArrayList<User>();
			// 遍历listob数据，把数据放到List中
			for (int i = 0; i < listob.size(); i++) {
				List<Object> ob = listob.get(i);
				User user = new User();
				user.setId(String.valueOf(ob.get(0)));
				user.setLoginId(String.valueOf(ob.get(1)));
				user.setUserName(String.valueOf(ob.get(2)));
				users.add(user);
			}
			userDao.insertUsers(users);
		} catch (Exception e) {
			throw new ServiceException(ExceptionCode.add_failure, e);
		}

	}

	@Override
	public Set<String> findRolesByLoginId(String loginId) {
		return new HashSet<String>(roleDao.getRolesByLoginId(loginId));
	}

	@Override
	public List<String> getRolesByLoginId(String loginId) {
		return roleDao.getRolesByLoginId(loginId);
	}

	// @Override
	// public Set<String> findPermissionsByLoginId(String loginId) {
	// return new
	// HashSet<String>(permissionDao.getPermissionsByLoginId(loginId));
	// }

}
