package com.cszx.pm.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.cszx.common.excel.ExcelBean;
import com.cszx.common.excel.ExcelUtil;
import com.cszx.pm.dao.report.ReportDao;
import com.cszx.pm.model.report.ReportResult;
import com.cszx.pm.model.report.ReportVo;
import com.cszx.pm.service.ReportService;

@Service("reportService")
public class ReportServiceImpl implements ReportService {
	@Resource
	private ReportDao reportDao;

	@Override
	public List<ReportResult> selectReport(Map<String, Object> reportMap) {
		return reportDao.selectReport(reportMap);
	}

	@Override
	public void addReport(ReportVo reportVo) {
		reportDao.addReport(reportVo);
	}

	@Override
	public void updateReport(ReportVo reportVo) {
		reportDao.updateReport(reportVo);
	}

	@Override
	public XSSFWorkbook exportReport(Map<String, Object> reportMap) {

		XSSFWorkbook xssfWorkbook = null;
		try {

			List<ReportResult> reportList = reportDao.selectReport(reportMap);

			List<ExcelBean> excel = new ArrayList<>();
			Map<Integer, List<ExcelBean>> map = new LinkedHashMap<>();
			// 设置标题栏
			excel.add(new ExcelBean("序号", "id", 0, false));
			excel.add(new ExcelBean("所属部门", "pDepartment", 0, false));
			excel.add(new ExcelBean("产品名称", "pName", 0, false));
			excel.add(new ExcelBean("项目类别", "pType", 0, false));

			excel.add(new ExcelBean("当前阶段", "currStateReport", 0, false));
			excel.add(new ExcelBean("当前状态", "currState", 0, false));
			excel.add(new ExcelBean("风险等级", "riskLevel", 0, false));

			excel.add(new ExcelBean("联系人", "linkman", 0, false));
			excel.add(new ExcelBean("风险点", "riskPoint", 0, false));
			excel.add(new ExcelBean("备注", "remark", 0, false));
			map.put(0, excel);
			String sheetName = "外部测试跟踪日报";
			xssfWorkbook = ExcelUtil.createExcelFile(ReportResult.class, reportList, map, sheetName);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return xssfWorkbook;
	}

	@Override
	public boolean checkName(Map<String, Object> reportMap) {
		return reportDao.checkName(reportMap);
	}

	/**
	 * 根据baseInfoId删除
	 */
	@Override
	public void deleteReport(List<String> ids) {
		reportDao.deleteReport(ids);
	}

	/**
	 * 根据id删除
	 */
	@Override
	public void deleteReportById(List<String> idList) {
		reportDao.deleteReportById(idList);

	}

}
