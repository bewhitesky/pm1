package com.cszx.pm.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cszx.pm.model.report.ReportResult;
import com.cszx.pm.model.report.ReportVo;

public interface ReportService {

	List<ReportResult> selectReport(Map<String, Object> reportMap);

	void addReport(ReportVo reportVo);

	void updateReport(ReportVo reportVo);

	XSSFWorkbook exportReport(Map<String, Object> map);

	boolean checkName(Map<String, Object> reportMap);

	void deleteReport(List<String> idList);

	void deleteReportById(List<String> idList);

}
