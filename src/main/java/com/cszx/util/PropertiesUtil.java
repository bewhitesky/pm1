package com.cszx.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import com.cszx.common.exception.BusinessException;

public class PropertiesUtil {

	public static String getPropertyValue(String key, String fileName) {
		String name = PropertiesUtil.class.getResource("/").getPath();
		String path = name + fileName;
		File file = new File(path);
		Properties prop = new Properties();
		// 根据key获取value值
		try {
			FileInputStream is = new FileInputStream(file);
			InputStreamReader reader = new InputStreamReader(is, "UTF-8");
			prop.load(reader);
		} catch (IOException e) {
			throw new BusinessException("加载文件内容失败!");
		}
		String value = prop.getProperty(key);
		return value;
	}

}
