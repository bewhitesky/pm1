package com.cszx.pm.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cszx.common.exception.BusinessException;
import com.cszx.common.exception.ExceptionCode;
import com.cszx.common.exception.ServiceException;
import com.cszx.pm.model.user.User;
import com.cszx.pm.model.user.UserDto;
import com.cszx.pm.service.UserService;
import com.cszx.util.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/userController")
public class UserController {
	@Resource
	private UserService userService;

	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 登录验证
	 * 
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(User user) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginId(), user.getLoginPassword());
		logger.info("用户登录");
		try {
			SecurityUtils.getSubject().login(token);
			resultMap.put("mes", "success");
		} catch (IncorrectCredentialsException e1) {
			throw new BusinessException(ExceptionCode.Login_failure, e1);
		} catch (ExcessiveAttemptsException e2) {
			throw new BusinessException(ExceptionCode.more_try_failure, e2);
		}

		return resultMap;
	}

	/**
	 * 用户注销
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loginOut", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loginOut() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		SecurityUtils.getSubject().logout();
		resultMap.put("mes", "success");
		return resultMap;
	}

	/**
	 * 查询用户
	 * 
	 * @param page
	 * @param nums
	 * @return
	 */
	@RequestMapping(value = "/selectUsers", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectUsers(UserDto userDto) {
		int page = userDto.getPage();
		int nums = userDto.getNums();
		String loginId = userDto.getLoginId();
		String userName = userDto.getUserName();
		Map<String, Object> userMap = new HashMap<String, Object>();
		userMap.put("loginId", loginId);
		userMap.put("userName", userName);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<User> users = new ArrayList<User>();
		logger.info("用户查询");
		PageHelper.startPage(page, nums, true);
		users = userService.selectUsers(userMap);
		PageInfo<User> pageInfo = new PageInfo<User>(users);
		resultMap.put("code", 0);
		resultMap.put("mes", "");
		resultMap.put("rows", users);
		resultMap.put("total", pageInfo.getTotal());
		return resultMap;
	}

	/**
	 * 添加或修改用户
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/addOrUpdateUser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addOrUpdateUser(@Valid User user, BindingResult result) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (StringUtil.isNotEmpty(user.getLoginPassword())) {
			String password = new SimpleHash("md5", user.getLoginPassword(), ByteSource.Util.bytes("cxzhao"), 2)
					.toHex();
			user.setLoginPassword(password);
		}
		try {
			if (!StringUtil.isNotEmpty(user.getId())) {
				user.setId(String.valueOf(UUID.randomUUID()));
				logger.info("用户添加");
				userService.addUser(user);
			} else {
				logger.info("用户修改");
				userService.updateUser(user);
			}

			resultMap.put("mes", "success");
		} catch (ServiceException e) {
			resultMap.put("mes", "failure");
			throw new BusinessException(ExceptionCode.add_failure, e);
		}

		return resultMap;
	}

	@RequestMapping(value = "/deleteUsers", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteUsers(String ids) {
		Map<String, Object> requestMap = new HashMap<String, Object>();
		String[] strs = ids.split(",");
		List<String> idList = new ArrayList<String>();
		for (String s : strs) {
			idList.add(s);
		}
		try {
			userService.deleteUsers(idList);
			requestMap.put("mes", "success");
		} catch (ServiceException e) {
			throw new BusinessException(ExceptionCode.delete_failure, e);
		}
		return requestMap;
	}

	/**
	 * 导出excel
	 * 
	 * @param response
	 */
	@RequestMapping(value = "/exportUser", method = RequestMethod.GET)
	@ResponseBody
	public void export(HttpServletResponse response) {
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");// 可以方便地修改日期格式
		String currentTime = dateFormat.format(currentDate);
		String fileName = "export_" + currentTime + ".xlsx";
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ";charset=UTF-8");
		logger.info("导出excel");
		XSSFWorkbook workbook = userService.exportExcelInfo();
		try {
			OutputStream output = response.getOutputStream();
			BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
			workbook.write(bufferedOutPut);
			bufferedOutPut.flush();
			bufferedOutPut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 导入excel
	 * 
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/importUser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> importUser(@RequestParam("file") CommonsMultipartFile file) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			logger.info("导入excel");
			InputStream in = file.getInputStream();
			userService.importExcelInfo(in, file);
			in.close();
			resultMap.put("mes", "success");
		} catch (Exception e) {
			resultMap.put("mes", "failure");
		}
		return resultMap;
	}

	@RequestMapping(value = "/getSession", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getSession() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Session session = SecurityUtils.getSubject().getSession();
		try {
			String userNameString = (String) session.getAttribute("userName");
			resultMap.put("mes", "success");
		} catch (Exception e) {
			resultMap.put("mes", "failure");
		}
		return resultMap;
	}

}
