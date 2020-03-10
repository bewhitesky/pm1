package com.cszx.pm.dao.project;

import java.util.List;
import java.util.Map;

import com.cszx.pm.model.project.Progress;
import com.cszx.pm.model.project.ProgressVo;
import com.cszx.pm.model.project.Project;

public interface ProgressDao {
	int deleteByPrimaryKey(String id);

	void deleteByBaseInfoId(List<String> idList);

	int insert(Progress record);

	int insertSelective(Progress record);

	Progress selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(ProgressVo progressVo);

	int updateByPrimaryKey(Progress record);

	List<Project> selectProgress(Map<String, Object> progressMap);

	int selectByBaseInfoId(String id);
}