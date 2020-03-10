package com.cszx.pm.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cszx.common.model.PageHelp;
import com.cszx.pm.model.task.Task;
import com.cszx.pm.model.task.TaskDto;
import com.cszx.pm.model.task.TaskHistory;
import com.cszx.pm.model.task.TaskHistoryVo;
import com.cszx.pm.model.task.TaskTree;
import com.cszx.pm.model.task.TaskVo;

public interface TaskService {

	List<Task> selectTask(Map<String, Object> reportMap);

	void addTask(TaskVo taskVo);

	void updateTask(TaskVo taskVo);
	
	void updateTaskActualDay(String id);
	
	void updateTaskActualDay2(String id);

	void deleteTask(String id);

	List<Task> selectTaskByUser(Map<String, Object> taskMap);

	PageHelp<TaskTree> getTasks(Map<String, Object> map);

	List<TaskTree> getTasksByNodeId(TaskDto taskDto);

	XSSFWorkbook exportTask(Map<String, Object> taskMap) throws Exception;

	int countNode(String id);

	int countAllTreeNum(Map<String, Object> map);

	List<Task> getSubTask(String id);

	boolean checkRepeat(Map<String, Object> map);

	void updateHistory(TaskHistoryVo taskHistoryVo);

	void addHistory(TaskHistoryVo taskHistoryVo);

	Integer countWorkLoad(String taskId);

	List<TaskHistory> selectHistory(String taskId);

	Double maxProgress(String taskId);

	List<Task> getTodayTask(Map<String, Object> map);

	List<Task> getWorkData(Map<String, Object> map);

	XSSFWorkbook exportWorkData(Map<String, Object> taskMap) throws Exception;

	void deleteHistory(String id);
}
