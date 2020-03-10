package com.cszx.pm.dao.task;

import java.util.List;
import java.util.Map;

import com.cszx.pm.model.task.TaskTree;

public interface TaskTreeDao {
	int insert(TaskTree record);

	int insertSelective(TaskTree record);

	List<TaskTree> getTasks(Map<String, Object> map);

	TaskTree findPreNode(String pId);

	int countNode(String id);

	int countAllTreeNum(Map<String, Object> map);
	
	void getSumActualDay();
}