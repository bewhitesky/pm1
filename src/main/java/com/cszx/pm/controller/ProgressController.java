package com.cszx.pm.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cszx.pm.model.project.Complete;
import com.cszx.pm.model.project.CompleteVo;
import com.cszx.pm.model.project.ProgressVo;
import com.cszx.pm.model.project.Project;
import com.cszx.pm.model.project.ProjectDto;
import com.cszx.pm.service.ProjectService;
import com.cszx.util.DateUtil;
import com.cszx.util.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/progressController")
public class ProgressController {
	@Resource
	private ProjectService projectService;

	/**
	 * 查询项目进度
	 * 
	 * @param progressDto
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/selectProgress", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectProgress(ProjectDto projectDto) throws ParseException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int page = projectDto.getPage();
		int nums = projectDto.getNums();
		Map<String, Object> progressMap = new HashMap<String, Object>();
		Date internalStartTime = null;
		Date factoryStartTime = null;
		Date thirdStartTime = null;
		Date internalEndTime = null;
		Date factoryEndTime = null;
		Date thirdEndTime = null;
		if (projectDto.getInternalCompleteTime() != null
				&& StringUtil.isNotEmpty(projectDto.getInternalCompleteTime())) {
			String internalTime = projectDto.getInternalCompleteTime();
			String[] times = internalTime.split(" ~ ");
			internalStartTime = DateUtil.parseDateTime(times[0], null);
			internalEndTime = DateUtil.parseDateTime(times[1], null);
		}
		if (projectDto.getFactoryCompleteTime() != null && StringUtil.isNotEmpty(projectDto.getFactoryCompleteTime())) {
			String factoryTime = projectDto.getFactoryCompleteTime();
			String[] times = factoryTime.split(" ~ ");
			factoryStartTime = DateUtil.parseDateTime(times[0], null);
			factoryEndTime = DateUtil.parseDateTime(times[1], null);
		}
		if (projectDto.getThirdCompleteTime() != null && StringUtil.isNotEmpty(projectDto.getThirdCompleteTime())) {
			String thirdTime = projectDto.getThirdCompleteTime();
			String[] times = thirdTime.split(" ~ ");
			thirdStartTime = DateUtil.parseDateTime(times[0], null);
			thirdEndTime = DateUtil.parseDateTime(times[1], null);
		}
		progressMap.put("pDepartment", StringUtil.parseToInteger(projectDto.getPDepartment()));
		progressMap.put("currStateOne", StringUtil.parseToInteger(projectDto.getCurrStateOne()));
		progressMap.put("currStateTwo", StringUtil.parseToInteger(projectDto.getCurrStateTwo()));
		progressMap.put("pName", projectDto.getPName());
		progressMap.put("testYear", projectDto.getTestYear());
		progressMap.put("internalStartTime", internalStartTime);
		progressMap.put("factoryStartTime", factoryStartTime);
		progressMap.put("thirdStartTime", thirdStartTime);
		progressMap.put("internalEndTime", internalEndTime);
		progressMap.put("factoryEndTime", factoryEndTime);
		progressMap.put("thirdEndTime", thirdEndTime);
		progressMap.put("testState", projectDto.getTestState());
		progressMap.put("dorg", StringUtil.parseToInteger(projectDto.getDorg()));
		Project project = new Project();
		Complete average = projectService.average();
		project.setComplete(average);
		PageHelper.startPage(page, nums, true);
		List<Project> projectList = projectService.selectProgress(progressMap);
		PageInfo<Project> pageInfo = new PageInfo<Project>(projectList);
		resultMap.put("code", 0);
		resultMap.put("mes", "");
		resultMap.put("rows", projectList);
		resultMap.put("total", pageInfo.getTotal());
		resultMap.put("avg", project);
		return resultMap;
	}

	@RequestMapping(value = "/updateProgress", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateProgress(ProgressVo progressVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		projectService.updateProgress(progressVo);
		resultMap.put("mes", "success");
		return resultMap;
	}

	@RequestMapping(value = "/updateComplete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateComplete(CompleteVo completeVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		projectService.updateComplete(completeVo);
		resultMap.put("mes", "success");
		return resultMap;
	}

	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
	@ResponseBody
	public void exportExcel(String json, HttpServletResponse response) throws ParseException {
		Map<String, Object> progressMap = new HashMap<String, Object>();
		ProjectDto projectDto = JSON.parseObject(json, ProjectDto.class);
		Date internalStartTime = null;
		Date factoryStartTime = null;
		Date thirdStartTime = null;
		Date internalEndTime = null;
		Date factoryEndTime = null;
		Date thirdEndTime = null;
		if (projectDto.getInternalCompleteTime() != null
				&& StringUtil.isNotEmpty(projectDto.getInternalCompleteTime())) {
			String internalTime = projectDto.getInternalCompleteTime();
			String[] times = internalTime.split(" ~ ");
			internalStartTime = DateUtil.parseDateTime(times[0], null);
			internalEndTime = DateUtil.parseDateTime(times[1], null);
		}
		if (projectDto.getFactoryCompleteTime() != null && StringUtil.isNotEmpty(projectDto.getFactoryCompleteTime())) {
			String factoryTime = projectDto.getFactoryCompleteTime();
			String[] times = factoryTime.split(" ~ ");
			factoryStartTime = DateUtil.parseDateTime(times[0], null);
			factoryEndTime = DateUtil.parseDateTime(times[1], null);
		}
		if (projectDto.getThirdCompleteTime() != null && StringUtil.isNotEmpty(projectDto.getThirdCompleteTime())) {
			String thirdTime = projectDto.getThirdCompleteTime();
			String[] times = thirdTime.split(" ~ ");
			thirdStartTime = DateUtil.parseDateTime(times[0], null);
			thirdEndTime = DateUtil.parseDateTime(times[1], null);
		}
		progressMap.put("pDepartment", StringUtil.parseToInteger(projectDto.getPDepartment()));
		progressMap.put("currStateOne", StringUtil.parseToInteger(projectDto.getCurrStateOne()));
		progressMap.put("currStateTwo", StringUtil.parseToInteger(projectDto.getCurrStateTwo()));
		progressMap.put("pName", projectDto.getPName());
		progressMap.put("testYear", projectDto.getTestYear());
		progressMap.put("internalStartTime", internalStartTime);
		progressMap.put("factoryStartTime", factoryStartTime);
		progressMap.put("thirdStartTime", thirdStartTime);
		progressMap.put("internalEndTime", internalEndTime);
		progressMap.put("factoryEndTime", factoryEndTime);
		progressMap.put("thirdEndTime", thirdEndTime);
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");// 可以方便地修改日期格式
		String currentTime = dateFormat.format(currentDate);
		String fileName = "export_" + currentTime + ".xlsx";
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ";charset=UTF-8");
		XSSFWorkbook workbook = projectService.exportExcel(progressMap);
		try {
			OutputStream output = response.getOutputStream();
			BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
			workbook.write(bufferedOutPut);
			bufferedOutPut.flush();
			bufferedOutPut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
