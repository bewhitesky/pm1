package com.cszx.pm.dao.project;

import java.util.List;

import com.cszx.pm.model.project.Complete;
import com.cszx.pm.model.project.CompleteVo;

public interface CompleteDao {
	int deleteByPrimaryKey(String id);

	int insert(Complete record);

	int insertSelective(Complete record);

	Complete selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(CompleteVo completeVo);

	int updateByPrimaryKey(Complete record);

	void deleteByBaseInfoId(List<String> idList);

	Complete average();

}