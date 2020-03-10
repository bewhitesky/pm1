package com.cszx.common.aspect;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cszx.pm.model.user.User;

@Component
public class LogAspect {
	private final Logger logger = LoggerFactory.getLogger(LogAspect.class);
	@Autowired
	private HttpServletRequest request;

	public void before() {
		logger.info("-----------日志记录开始-------------");
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
		if (user != null) {
			System.out.println(user.getUserName());
			System.out.println(request.getRemoteAddr());
			System.out.println(request.getRequestURI());
			System.out.println(request.getMethod());
			System.out.println();
		}

	}

	public void after() {
		logger.info("-----------日志结束记录-------------");
		System.out.println();
	}
}