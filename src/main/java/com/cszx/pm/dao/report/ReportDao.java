package com.cszx.pm.dao.report;

import java.util.List;
import java.util.Map;

import com.cszx.pm.model.report.ReportResult;
import com.cszx.pm.model.report.ReportVo;

public interface ReportDao {
	List<ReportResult> selectReport(Map<String, Object> reportMap);

	void addReport(ReportVo reportVo);

	void updateReport(ReportVo reportVo);

	void deleteReport(List<String> ids);

	boolean checkName(Map<String, Object> reportMap);

	void deleteReportById(List<String> idList);
}