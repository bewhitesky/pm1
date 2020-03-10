package com.cszx.pm.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cszx.pm.model.project.BaseInfo;
import com.cszx.pm.model.project.BaseInfoVo;
import com.cszx.pm.model.project.RemainDay;

public interface BaseInfoService {

	List<BaseInfo> selectBaseInfo(Map<String, Object> baseInfoMap) throws Exception;

	void addBaseInfo(BaseInfoVo baseInfoVo) throws Exception;

	void updateBaseInfo(BaseInfoVo baseInfoVo) throws Exception;

	void deleteBaseInfo(List<String> idList) throws Exception;

	XSSFWorkbook exportExcel(Map<String, Object> baseInfoMap) throws Exception;

	void importExcel(InputStream in, CommonsMultipartFile file) throws Exception;

	List<BaseInfo> getPnames() throws Exception;

	List<BaseInfo> getPnameList(BaseInfoVo baseInfoVo) throws Exception;

	String getTestType(String id) throws Exception;

	BaseInfo selectBaseInfoById(String id);

	List<RemainDay> countRemainDay(Map<String, Object> map);

	/**
	 * @Description
	 * @author chenzhaojie
	 * @date 2019年11月18日
	 * @param map
	 * @return
	 */
	boolean checkInternalExist(Map<String, Object> map);

}
