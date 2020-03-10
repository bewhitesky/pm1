package com.cszx.pm.controller;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cszx.common.exception.BusinessException;
import com.cszx.common.model.PageHelp;
import com.cszx.pm.model.report.ReportVo;
import com.cszx.pm.model.task.Task;
import com.cszx.pm.model.task.TaskDto;
import com.cszx.pm.model.task.TaskHistory;
import com.cszx.pm.model.task.TaskHistoryVo;
import com.cszx.pm.model.task.TaskTree;
import com.cszx.pm.model.task.TaskVo;
import com.cszx.pm.service.InternalService;
import com.cszx.pm.service.ReportService;
import com.cszx.pm.service.TaskService;
import com.cszx.util.DateUtil;
import com.cszx.util.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/taskController")
public class TaskController {
	private final Logger logger = LoggerFactory.getLogger(TaskController.class);
	@Resource
	private TaskService taskService;
	@Resource
	private InternalService internalService;
	@Resource
	private ReportService reportService;

	@RequestMapping(value = "/getTaskTree", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getTaskTree(TaskDto taskDto) throws ParseException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		// map.put("level", 3);
		map.put("taskName", taskDto.getTaskName());
		map.put("expectMonth", taskDto.getExpectMonth());
		map.put("actualMonth", taskDto.getActualMonth());
		map.put("executor", taskDto.getExecutor());
		map.put("taskState", taskDto.getTaskState());
		Date expectStartTime1 = null;
		Date expectStartTime2 = null;
		if (taskDto.getExpectStartTime() != null && StringUtil.isNotEmpty(taskDto.getExpectStartTime())) {
			String expectStartTime = taskDto.getExpectStartTime();
			String[] times = expectStartTime.split(" ~ ");
			expectStartTime1 = DateUtil.parseDateTime(times[0], null);
			expectStartTime2 = DateUtil.parseDateTime(times[1], null);
		}

		map.put("expectStartTime1", expectStartTime1);
		map.put("expectStartTime2", expectStartTime2);

		Date expectEndTime1 = null;
		Date expectEndTime2 = null;
		if (taskDto.getDeadline() != null && StringUtil.isNotEmpty(taskDto.getDeadline())) {
			String expectEndTime = taskDto.getDeadline();
			String[] times = expectEndTime.split(" ~ ");
			expectEndTime1 = DateUtil.parseDateTime(times[0], null);
			expectEndTime2 = DateUtil.parseDateTime(times[1], null);
		}

		map.put("expectEndTime1", expectEndTime1);
		map.put("expectEndTime2", expectEndTime2);

		Date actualStartTime1 = null;
		Date actualStartTime2 = null;
		if (taskDto.getActualStartTime() != null && StringUtil.isNotEmpty(taskDto.getActualStartTime())) {
			String actualStartTime = taskDto.getActualStartTime();
			String[] times = actualStartTime.split(" ~ ");
			actualStartTime1 = DateUtil.parseDateTime(times[0], null);
			actualStartTime2 = DateUtil.parseDateTime(times[1], null);
		}

		map.put("actualStartTime1", actualStartTime1);
		map.put("actualStartTime2", actualStartTime2);

		Date actualEndTime1 = null;
		Date actualEndTime2 = null;
		if (taskDto.getActualEndTime() != null && StringUtil.isNotEmpty(taskDto.getActualEndTime())) {
			String actualEndTime = taskDto.getActualEndTime();
			String[] times = actualEndTime.split(" ~ ");
			actualEndTime1 = DateUtil.parseDateTime(times[0], null);
			actualEndTime2 = DateUtil.parseDateTime(times[1], null);
		}

		map.put("actualEndTime1", actualEndTime1);
		map.put("actualEndTime2", actualEndTime2);

		int rows = taskDto.getRows();
		int page = taskDto.getPage();
		map.put("rows", rows);
		map.put("page", page);
		PageHelp<TaskTree> tree = taskService.getTasks(map);
		resultMap.put("total", tree.getTotal());
		resultMap.put("rows", tree.getResult());
		return resultMap;
	}

	/**
	 * 
	 * @Description 任务详情获取任务分配情况
	 * @author chenzhaojie
	 * @date 2019年4月12日
	 * @param id
	 * @param rows
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/getSubTask", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getSubTask(String id, int rows, int page) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		PageHelper.startPage(page, rows, true);
		List<Task> tasklist = taskService.getSubTask(id);
		PageInfo<Task> pageInfo = new PageInfo<Task>(tasklist);
		resultMap.put("total", pageInfo.getTotal());
		resultMap.put("rows", tasklist);
		return resultMap;
	}

	/**
	 * 
	 * @Description 获取报工数据
	 * @author chenzhaojie
	 * @date 2019年4月12日
	 * @param id
	 * @param rows
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/getWorkData", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getWorkData(String expectMonth, String actualMonth, int level, int page, int rows) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("expectMonth", expectMonth);
		map.put("actualMonth", actualMonth);
		map.put("level", level);
		PageHelper.startPage(page, rows, true);
		List<Task> tasklist = taskService.getWorkData(map);
		PageInfo<Task> pageInfo = new PageInfo<Task>(tasklist);
		resultMap.put("total", pageInfo.getTotal());
		resultMap.put("rows", tasklist);
		return resultMap;
	}

	@RequestMapping(value = "/selectHistory", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectHistory(String taskId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<TaskHistory> historys = taskService.selectHistory(taskId);
		resultMap.put("rows", historys);
		return resultMap;
	}

	/**
	 * @Description 添加或修改任务
	 * @author chenzhaojie
	 * @date 2019年4月12日
	 * @param taskVo
	 * @return
	 */
	@RequestMapping(value = "/addOrUpdateTask", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addOrUpdateTask(TaskVo taskVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String expectStartTime = taskVo.getExpectStartTime();
		String deadline = taskVo.getDeadline();
		int expectDay = internalService.countWorkday(expectStartTime, deadline);
		// String actualEndTime = taskVo.getActualEndTime();
		// String actualStartTime = taskVo.getActualStartTime();
		// if (StringUtil.isNotEmpty(actualEndTime) &&
		// StringUtil.isNotEmpty(actualStartTime)) {
		// int actualDay = internalService.countWorkday(actualStartTime,
		// actualEndTime);
		// taskVo.setActualDay(String.valueOf(actualDay));
		// }
		taskVo.setExpectDay(String.valueOf(expectDay));
		if (!StringUtil.isNotEmpty(taskVo.getId())) {
			taskVo.setId(UUID.randomUUID().toString());
			taskService.addTask(taskVo);
			if (StringUtil.isNotEmpty(taskVo.getBaseInfoId())) {
				ReportVo reportVo = new ReportVo();
				reportVo.setId(UUID.randomUUID().toString());
				reportVo.setBaseInfoId(taskVo.getBaseInfoId());
				reportVo.setCurrStateOne(Integer.parseInt(taskVo.getTestStage()) - 1);
				reportService.addReport(reportVo);
			}
		} else {
			taskService.updateTask(taskVo);
		}
		resultMap.put("mes", "success");
		return resultMap;
	}

	/**
	 * @Description 添加或修改历史记录
	 * @author chenzhaojie
	 * @date 2019年4月12日
	 * @param taskHistoryVo
	 * @return
	 */
	@RequestMapping(value = "/addOrUpdateHistory", method = RequestMethod.POST)
	@ResponseBody
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> addOrUpdateHistory(TaskHistoryVo taskHistoryVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("createtime", taskHistoryVo.getCreatetime());
		map.put("taskId", taskHistoryVo.getTaskId());
		try {
			if (taskService.checkRepeat(map)) {
				taskService.updateHistory(taskHistoryVo);
			} else {
				taskHistoryVo.setId(UUID.randomUUID().toString());
				taskService.addHistory(taskHistoryVo);
			}
		} catch (Exception e) {
			if (e instanceof BusinessException) {
				throw e;
			}
			logger.info("添加或修改历史记录！");
			throw new BusinessException("添加或修改历史记录", e);
		}
		List<TaskHistory> historys = taskService.selectHistory(taskHistoryVo.getTaskId());
		TaskVo taskVo = new TaskVo();
		taskVo.setId(taskHistoryVo.getTaskId());
		Double maxProgress = taskService.maxProgress(taskHistoryVo.getTaskId());
		int countWorkLoad = taskService.countWorkLoad(taskHistoryVo.getTaskId());
		if (countWorkLoad > 0 && historys.size() > 0) {
			taskVo.setActualStartTime(historys.get(historys.size() - 1).getCreatetime());
		} else {
			taskVo.setActualStartTime("");
		}

		if (maxProgress == 100.0 && historys.size() > 0) {
			taskVo.setActualEndTime(historys.get(0).getCreatetime());
			taskVo.setTaskState("2");
		} else {
			taskVo.setActualEndTime("");
			taskVo.setTaskState("1");
		}
		if (("1").equals(taskHistoryVo.getLevel())) {
			taskVo.setActualEndTime("");
			taskVo.setActualStartTime("");
		}
		taskVo.setActualProgress(String.valueOf(maxProgress));
		taskVo.setActualDay(String.valueOf(countWorkLoad));
		taskService.updateTask(taskVo);
		taskService.updateTaskActualDay(taskVo.getId());// 更新父任务的实际工作天数
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("id", taskHistoryVo.getTaskId());
		List<Task> tasks = taskService.getTodayTask(map2);
		resultMap.put("mes", "success");
		resultMap.put("rows", tasks);
		return resultMap;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteTask", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteTask(String id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int count = taskService.countNode(id);
			if (count > 0) {
				throw new BusinessException("请先删除子任务!");
			} else {
				taskService.updateTaskActualDay2(id);// 父任务的实际工作天数减去被删除任务天数
				taskService.deleteTask(id);
			}

		} catch (Exception e) {
			throw new BusinessException("删除任务失败");
		}

		resultMap.put("mes", "success");
		return resultMap;
	}

	@RequestMapping(value = "/exportTask", method = RequestMethod.GET)
	@ResponseBody
	public void exportTask(String json, HttpServletResponse response) throws ParseException {
		Map<String, Object> taskMap = new HashMap<String, Object>();
		TaskDto taskDto = JSON.parseObject(json, TaskDto.class);
		taskMap.put("expectMonth", taskDto.getExpectMonth());
		taskMap.put("actualMonth", taskDto.getActualMonth());
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");// 可以方便地修改日期格式
		String currentTime = dateFormat.format(currentDate);
		String fileName = "export_" + currentTime + ".xlsx";
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ";charset=UTF-8");
		try {
			XSSFWorkbook workbook = taskService.exportTask(taskMap);
			OutputStream output = response.getOutputStream();
			BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
			workbook.write(bufferedOutPut);
			bufferedOutPut.flush();
			bufferedOutPut.close();
			logger.info("任务导出成功！");
		} catch (Exception e) {
			logger.info("任务导出失败！");
			throw new BusinessException("任务导出失败！", e);
		}
	}

	@RequestMapping(value = "/exportWordData", method = RequestMethod.GET)
	@ResponseBody
	public void exportWordData(String json, HttpServletResponse response) throws ParseException {
		Map<String, Object> taskMap = new HashMap<String, Object>();
		TaskDto taskDto = JSON.parseObject(json, TaskDto.class);
		taskMap.put("expectMonth", taskDto.getExpectMonth());
		taskMap.put("actualMonth", taskDto.getActualMonth());
		taskMap.put("level", taskDto.getLevel());
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");// 可以方便地修改日期格式
		String currentTime = dateFormat.format(currentDate);
		String fileName = "export_" + currentTime + ".xlsx";
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ";charset=UTF-8");
		try {
			XSSFWorkbook workbook = taskService.exportWorkData(taskMap);
			OutputStream output = response.getOutputStream();
			BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
			workbook.write(bufferedOutPut);
			bufferedOutPut.flush();
			bufferedOutPut.close();
			logger.info("任务导出成功！");
		} catch (Exception e) {
			logger.info("任务导出失败！");
			throw new BusinessException("任务导出失败！", e);
		}
	}

	@RequestMapping(value = "/getTodayTask", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getTodayTask(String today, String userName) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("today", today);
		map.put("userName", userName);
		List<Task> tasklist = taskService.getTodayTask(map);
		resultMap.put("total", tasklist.size());
		resultMap.put("rows", tasklist);
		return resultMap;
	}

	@RequestMapping(value = "/deleteHistory", method = RequestMethod.POST)
	@ResponseBody
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> deleteHistory(TaskHistoryVo taskHistoryVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			taskService.deleteHistory(taskHistoryVo.getId());
			List<TaskHistory> historys = taskService.selectHistory(taskHistoryVo.getTaskId());
			TaskVo taskVo = new TaskVo();
			taskVo.setId(taskHistoryVo.getTaskId());
			Double maxProgress = taskService.maxProgress(taskHistoryVo.getTaskId());
			int countWorkLoad = taskService.countWorkLoad(taskHistoryVo.getTaskId());
			if (countWorkLoad > 0 && historys.size() > 0) {
				taskVo.setActualStartTime(historys.get(historys.size() - 1).getCreatetime());
			} else {
				taskVo.setActualStartTime("");
			}
			if (maxProgress != null && maxProgress == 100.0 && historys.size() > 0) {
				taskVo.setActualEndTime(historys.get(0).getCreatetime());
				taskVo.setTaskState("2");
			} else {
				taskVo.setActualEndTime("");
				taskVo.setTaskState("1");
			}
			taskVo.setActualProgress(maxProgress == null ? "" : String.valueOf(maxProgress));
			taskVo.setActualDay(String.valueOf(countWorkLoad));
			taskService.updateTask(taskVo);
			taskService.updateTaskActualDay(taskVo.getId());// 更新父任务的实际工作天数
		} catch (Exception e) {
			if (e instanceof BusinessException) {
				throw e;
			}
			logger.info("添加或修改历史记录失败！");
			throw new BusinessException("添加或修改历史记录失败！", e);
		}
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("id", taskHistoryVo.getTaskId());
		List<Task> tasks = taskService.getTodayTask(map2);
		resultMap.put("mes", "success");
		resultMap.put("rows", tasks);
		return resultMap;
	}

}
