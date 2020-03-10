package com.cszx.pm.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cszx.common.exception.BusinessException;
import com.cszx.pm.model.report.ReportDto;
import com.cszx.pm.model.report.ReportResult;
import com.cszx.pm.model.report.ReportVo;
import com.cszx.pm.service.ReportService;
import com.cszx.util.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/reportController")
public class ReportController {
	private final Logger logger = LoggerFactory.getLogger(ReportController.class);
	@Resource
	private ReportService reportService;

	/**
	 * 查询项目基础信息
	 * 
	 * @param reportDto
	 * @return
	 * 
	 */
	@RequestMapping(value = "/selectReport", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectReport(ReportDto reportDto) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> reportMap = new HashMap<String, Object>();
		int rows = reportDto.getRows();
		int page = reportDto.getPage();
		reportMap.put("pName", reportDto.getPName());
		reportMap.put("currStateOne", reportDto.getCurrStateOne());
		reportMap.put("riskLevel", reportDto.getRiskLevel());
		PageHelper.startPage(page, rows, true);
		List<ReportResult> reportList = reportService.selectReport(reportMap);
		PageInfo<ReportResult> pageInfo = new PageInfo<ReportResult>(reportList);
		resultMap.put("rows", reportList);
		resultMap.put("total", pageInfo.getTotal());
		return resultMap;
	}

	@RequestMapping(value = "/addOrUpdateReport", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addOrUpdateReport(@Valid ReportVo reportVo, BindingResult result) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> reportMap = new HashMap<String, Object>();

		if (!StringUtil.isNotEmpty(reportVo.getId())) {
			reportMap.put("pName", reportVo.getpName());
			reportMap.put("currStateOne", reportVo.getCurrStateOne());
			// if (reportService.checkName(reportMap)) {
			// throw new BusinessException("已存在的日报信息,请确认是否存在相同阶段的项目日报信息！");
			// }
			reportVo.setId(UUID.randomUUID().toString());
			reportService.addReport(reportVo);
		} else {
			reportService.updateReport(reportVo);
		}
		resultMap.put("mes", "success");
		return resultMap;
	}

	@RequestMapping(value = "/deleteReport", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteReport(String ids) {
		String[] strs = ids.split(",");
		List<String> idList = new ArrayList<String>();
		for (String str : strs) {
			idList.add(str);
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			reportService.deleteReportById(idList);
		} catch (Exception e) {
			throw new BusinessException("删除失败");
		}

		resultMap.put("mes", "success");
		return resultMap;

	}

	@RequestMapping(value = "/exportReport", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> exportReport(HttpServletResponse response) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");// 可以方便地修改日期格式
		String currentTime = dateFormat.format(currentDate);
		String fileName = "export_" + currentTime + ".xlsx";
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ";charset=UTF-8");
		XSSFWorkbook workbook = reportService.exportReport(map);
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
