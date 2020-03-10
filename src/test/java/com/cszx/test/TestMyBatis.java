package com.cszx.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cszx.pm.service.InternalService;
import com.cszx.pm.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner�?
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class TestMyBatis {
	private static Logger logger = Logger.getLogger(TestMyBatis.class);
	// private ApplicationContext ac = null;
	// @Resource
	// private UserService userService = null;
	// @Resource
	// private StudentInfoService studentInfoService=null;

	@Resource
	private UserService userService = null;

	@Resource
	private InternalService internalService = null;
	// @Before
	// public void before() {
	// ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	// userService = (IUserService) ac.getBean("userService");
	// }

	@Test
	public void test1() {
		// User user=new User();
		// user=userService.getUserByLoginId("admin");
		// System.out.println(user.getUserName());
		//
		// URL ss = getClass().getResource("/ehcache.xml");
		// System.out.print(ss);
		String password = new SimpleHash("md5", "ylkj-8116", ByteSource.Util.bytes("cxzhao"), 2).toHex();
		System.out.println(password);
	}

	@Test
	public void test2() {
		String value = "你";
		String reg = "^[\u4e00-\u9fa5a-zA-Z]{1,32}+$";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(value.toString());
		System.out.print(matcher.matches() + "");
	}
}