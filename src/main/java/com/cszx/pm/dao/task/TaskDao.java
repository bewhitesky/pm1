package com.cszx.pm.dao.task;

import java.util.List;
import java.util.Map;

import com.cszx.pm.model.task.Task;
import com.cszx.pm.model.task.TaskHistory;
import com.cszx.pm.model.task.TaskHistoryVo;
import com.cszx.pm.model.task.TaskVo;

public interface TaskDao {

	List<Task> selectTask(Map<String, Object> taskMap);

	List<Task> selectTaskByUser(Map<String, Object> taskMap);

	void addTask(TaskVo taskVo);

	void updateTask(TaskVo taskVo);
	
	void updateTaskActualDay(String id);
	
	void updateTaskActualDay2(String id);

	void deleteByPrimaryKey(List<String> ids);

	void deleteTask(String id);

	void deleteTaskByPId(String id);

	List<Task> getSubTask(String id);

	boolean checkRepeat(Map<String, Object> map);

	int countWorkLoad(String taskId);

	void updateHistory(TaskHistoryVo taskHistoryVo);

	void addHistory(TaskHistoryVo taskHistoryVo);

	List<TaskHistory> selectHistory(String taskId);

	Double maxProgress(String taskId);

	List<Task> getTodayTask(Map<String, Object> map);

	List<Task> getWorkData(Map<String, Object> map);

	void deleteHistory(String id);

}