package com.cszx.pm.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cszx.common.exception.BusinessException;
import com.cszx.common.exception.ExceptionCode;
import com.cszx.common.exception.ServiceException;
import com.cszx.pm.model.internal.Defect;
import com.cszx.pm.model.internal.DefectDto;
import com.cszx.pm.model.internal.Internal;
import com.cszx.pm.model.internal.InternalDefect;
import com.cszx.pm.model.internal.InternalDto;
import com.cszx.pm.model.internal.InternalItem;
import com.cszx.pm.model.internal.InternalItemVo;
import com.cszx.pm.model.internal.InternalVo;
import com.cszx.pm.model.internal.Statistics;
import com.cszx.pm.model.project.BaseInfo;
import com.cszx.pm.service.BaseInfoService;
import com.cszx.pm.service.InternalService;
import com.cszx.util.DateUtil;
import com.cszx.util.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/internalController")
public class InternalController {
	@Resource
	private InternalService internalService;
	@Resource
	private BaseInfoService baseInfoService;

	/**
	 * 查询项目进度
	 * 
	 * @param internalDto
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/selectInternalTest", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectInternalTest(InternalDto internalDto) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int page = internalDto.getPage();
		int nums = internalDto.getNums();
		Map<String, Object> internalMap = new HashMap<String, Object>();
		if (internalDto != null) {
			internalMap.put("pName", internalDto.getpName());
			if (internalDto.getDeploymentTime() != null && internalDto.getDeploymentTime() != "") {
				String deploymentStartTime = internalDto.getDeploymentTime().split("~")[0];
				String deploymentEndTime = internalDto.getDeploymentTime().split("~")[1];
				internalMap.put("deploymentStartTime", deploymentStartTime);
				internalMap.put("deploymentEndTime", deploymentEndTime);
			}
			internalMap.put("internalTestState", internalDto.getInternalTestState());
		}
		PageHelper.startPage(page, nums, true);
		List<Internal> internalList = internalService.selectInternalTest(internalMap);
		PageInfo<Internal> pageInfo = new PageInfo<Internal>(internalList);
		resultMap.put("code", 0);
		resultMap.put("mes", "");
		resultMap.put("rows", internalList);
		resultMap.put("total", pageInfo.getTotal());
		return resultMap;
	}

	/**
	 * 查询首轮缺陷率
	 * 
	 * @param DefectDto
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/selectDefect", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectDefect(DefectDto defectDto) throws ParseException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int page = defectDto.getPage();
		int nums = defectDto.getNums();
		Map<String, Object> defectMap = new HashMap<String, Object>();
		if (defectDto != null) {
			defectMap.put("pName", defectDto.getpName());
			if (defectDto.getDeploymentTime() != null && defectDto.getDeploymentTime() != "") {
				String deploymentStartTime = defectDto.getDeploymentTime().split("~")[0];
				String deploymentEndTime = defectDto.getDeploymentTime().split("~")[1];
				defectMap.put("deploymentStartTime", deploymentStartTime);
				defectMap.put("deploymentEndTime", deploymentEndTime);
			}
		}
		PageHelper.startPage(page, nums, true);
		List<Defect> defectList = internalService.selectDefect(defectMap);
		PageInfo<Defect> pageInfo = new PageInfo<Defect>(defectList);
		resultMap.put("code", 0);
		resultMap.put("mes", "");
		resultMap.put("rows", defectList);
		resultMap.put("total", pageInfo.getTotal());
		return resultMap;
	}

	@RequestMapping(value = "/selectInternalItems", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectInternalItems(String id, int page, int nums) throws ParseException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// PageHelper.startPage(page, nums, true);
		List<InternalItem> itemList = internalService.selectInternalItems(id);
		PageInfo<InternalItem> pageInfo = new PageInfo<InternalItem>(itemList);
		resultMap.put("code", 0);
		resultMap.put("mes", "");
		resultMap.put("rows", itemList);
		resultMap.put("total", pageInfo.getTotal());
		return resultMap;
	}

	@RequestMapping(value = "/selectInternalDefect", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectInternalDefect(String id) throws ParseException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		InternalDefect defect = internalService.selectInternalDefect(id);
		resultMap.put("rows", defect);
		return resultMap;
	}

	@RequestMapping(value = "/getPnames", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPnames() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<BaseInfo> maps = baseInfoService.getPnames();
			resultMap.put("mes", "success");
			resultMap.put("maps", maps);
		} catch (Exception e) {
			throw new BusinessException("获取项目名称失败", e);
		}
		return resultMap;
	}

	@RequestMapping(value = "/getTestType", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getTestType(String id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String testType;
		try {
			testType = baseInfoService.getTestType(id);
			Map<Integer, String> typeMap = new HashMap<Integer, String>();
			int count = 0;
			if (testType.contains("功能/非功能测试")) {
				typeMap.put(count++, "功能/非功能测试");
			}
			if (testType.contains("安全功能测试")) {
				typeMap.put(count++, "安全功能测试");
				typeMap.put(count++, "安全功能测试-脱敏测试");
				typeMap.put(count++, "安全功能测试-IOS");
				typeMap.put(count++, "安全防护方案");
			}
			if (testType.contains("渗透测试")) {
				typeMap.put(count++, "渗透测试");
			}
			if (testType.contains("性能测试")) {
				typeMap.put(count++, "性能测试");
			}
			if (testType.contains("代码扫描")) {
				typeMap.put(count++, "代码扫描");
			}
			resultMap.put("mes", "success");
			resultMap.put("map", typeMap);
		} catch (Exception e) {
			throw new BusinessException("获取测试项失败", e);
		}
		return resultMap;
	}

	/**
	 * 添加或修改内部项目
	 * 
	 * @param internalDto
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/addOrUpdateInternal", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addOrUpdateInternal(InternalVo internalVo) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (!StringUtil.isNotEmpty(internalVo.getId())) {// 新增
			internalVo.setId(UUID.randomUUID().toString());
			internalService.addInternal(internalVo);
		} else {// 修改
			internalService.updateInternal(internalVo);
		}
		resultMap.put("mes", "success");
		return resultMap;
	}

	/**
	 * 添加或修改项目首轮通过率
	 * 
	 * @param internalDefect
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/addOrUpdateDefect", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addOrUpdateDefect(InternalDefect internalDefect) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (!StringUtil.isNotEmpty(internalDefect.getId())) {// 新增
			internalDefect.setId(UUID.randomUUID().toString());
			internalService.addInternalDefect(internalDefect);
		} else {// 修改
			internalService.updateInternalDefect(internalDefect);
		}
		resultMap.put("mes", "success");
		return resultMap;
	}

	/**
	 * 添加或修改轮次信息
	 * 
	 * @param internalItemVo
	 * @return
	 */
	@RequestMapping(value = "/addOrUpdateItems", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addOrUpdateItems(InternalItemVo internalItemVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtil.isNotEmpty(internalItemVo.getTestStartTime())
				&& StringUtil.isNotEmpty(internalItemVo.getTestEndTime())) {
			internalItemVo.setTestTime(String.valueOf(
					internalService.countWorkday(internalItemVo.getTestStartTime(), internalItemVo.getTestEndTime())));
		}
		if (internalItemVo.getInternalTestTimes() > 1) {
			map.put("internalTestId", internalItemVo.getInternalTestId());
			map.put("internalTestTimes", internalItemVo.getInternalTestTimes() - 1);
			Date endTimeDate = internalService.getTestEndTime(map);// 上一轮测试结束时间
			if (endTimeDate != null) {
				String endTime = DateUtil.dateToStr(endTimeDate, "yyyy-MM-dd");
				if (StringUtil.isNotEmpty(internalItemVo.getDeployStartTime())
						&& endTime.compareTo(internalItemVo.getDeployStartTime()) > 0) {
					throw new BusinessException("部署开始时间需大于上一轮测试结束时间");
				}
				if (StringUtil.isNotEmpty(internalItemVo.getDeployEndTime())
						&& endTime.compareTo(internalItemVo.getDeployEndTime()) < 0) {
					internalItemVo.setCorrectTime(String
							.valueOf(internalService.countWorkday(endTime, internalItemVo.getDeployEndTime()) - 1));
				}
			}
		}
		if (!StringUtil.isNotEmpty(internalItemVo.getId())) {
			internalItemVo.setId(UUID.randomUUID().toString());
			internalService.addItems(internalItemVo);
		} else {
			internalService.updateItems(internalItemVo);
		}
		resultMap.put("mes", "success");
		return resultMap;
	}

	/**
	 * 删除轮次信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deleteItems", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteItems(String ids) {
		Map<String, Object> requestMap = new HashMap<String, Object>();
		String[] strs = ids.split(",");
		List<String> idList = new ArrayList<String>();
		for (String s : strs) {
			idList.add(s);
		}
		try {
			internalService.deleteItems(idList);
			requestMap.put("mes", "success");
		} catch (ServiceException e) {
			throw new BusinessException(ExceptionCode.delete_failure, e);
		}
		return requestMap;
	}

	@RequestMapping(value = "/deleteInternal", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteInternal(String ids) {
		Map<String, Object> requestMap = new HashMap<String, Object>();
		String[] strs = ids.split(",");
		List<String> idList = new ArrayList<String>();
		for (String s : strs) {
			idList.add(s);
		}
		try {
			internalService.deleteInternal(idList);
			requestMap.put("mes", "success");
		} catch (ServiceException e) {
			throw new BusinessException(ExceptionCode.delete_failure, e);
		}
		return requestMap;
	}

	/**
	 * 修改首轮缺陷率
	 * 
	 * @param updateDefect
	 * @return
	 */
	@RequestMapping(value = "/deleteDefect", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteDefect(String id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		internalService.deleteInternalDefect(id);
		resultMap.put("mes", "success");
		return resultMap;
	}

	/**
	 * 修改首轮缺陷率
	 * 
	 * @param updateDefect
	 * @return
	 */
	@RequestMapping(value = "/updateDefect", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateDefect(Defect defect) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		internalService.updateDefect(defect);
		resultMap.put("mes", "success");
		return resultMap;
	}

	@RequestMapping(value = "/exportInternal", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> exportInternal(HttpServletResponse response) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");// 可以方便地修改日期格式
		String currentTime = dateFormat.format(currentDate);
		String fileName = "export_" + currentTime + ".xlsx";
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ";charset=UTF-8");
		XSSFWorkbook workbook = internalService.exportInternal(map);
		try {
			OutputStream output = response.getOutputStream();
			BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
			workbook.write(bufferedOutPut);
			bufferedOutPut.flush();
			bufferedOutPut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		resultMap.put("mes", "success");
		return resultMap;
	}

	/**
	 * 测试结果统计
	 * 
	 * @param updateDefect
	 * @return
	 */
	@RequestMapping(value = "/statistics", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> statistics(String time, String pName) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtil.isNotEmpty(time)) {
			String[] times = time.split(" - ");
			String startime = times[0];
			String endtime = times[1];
			map.put("startime", startime);
			map.put("endtime", endtime);
		}
		map.put("pName", pName);

		// PageHelper.startPage(page, nums, true);
		List<Statistics> statisticsList = internalService.statistics(map);
		// PageInfo<Statistics> pageInfo = new
		// PageInfo<Statistics>(statisticsList);
		resultMap.put("code", 0);
		resultMap.put("mes", "");
		resultMap.put("rows", statisticsList);
		// resultMap.put("total", pageInfo.getTotal());
		return resultMap;
	}

	@RequestMapping(value = "/exportStatistics", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> exportStatistics(String json, HttpServletResponse response) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		map = JSON.parseObject(json);
		String time = (String) map.get("time");
		if (StringUtil.isNotEmpty(time)) {
			String[] times = time.split(" - ");
			String startime = times[0];
			String endtime = times[1];
			map.put("startime", startime);
			map.put("endtime", endtime);
		}
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");// 可以方便地修改日期格式
		String currentTime = dateFormat.format(currentDate);
		String fileName = "export_" + currentTime + ".xlsx";
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ";charset=UTF-8");
		XSSFWorkbook workbook = internalService.exportStatistics(map);
		try {
			OutputStream output = response.getOutputStream();
			BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
			workbook.write(bufferedOutPut);
			bufferedOutPut.flush();
			bufferedOutPut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		resultMap.put("mes", "success");
		return resultMap;
	}
}
