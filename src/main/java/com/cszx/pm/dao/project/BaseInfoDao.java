package com.cszx.pm.dao.project;

import java.util.List;
import java.util.Map;

import com.cszx.pm.model.project.BaseInfo;
import com.cszx.pm.model.project.BaseInfoVo;
import com.cszx.pm.model.project.RemainDay;

public interface BaseInfoDao {
	// int deleteByPrimaryKey(String id);
	//
	// int insert(BaseInfo record);
	//
	// BaseInfo selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(BaseInfoVo record);

	List<BaseInfo> selectBaseInfo(Map<String, Object> baseInfoMap);

	void addBaseInfo(BaseInfoVo baseInfoVo);

	void deleteBaseInfo(List<String> idList);

	void insertBaseInfos(List<BaseInfo> baseInfoList);

	List<BaseInfo> getPnames();

	List<BaseInfo> getPnameList(BaseInfoVo baseInfoVo);

	BaseInfo selectBaseInfoById(String id);

	String getTestType(String id);

	int countPname(String pName);

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