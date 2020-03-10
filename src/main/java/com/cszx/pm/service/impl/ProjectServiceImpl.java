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
import com.cszx.pm.dao.project.CompleteDao;
import com.cszx.pm.dao.project.ProgressDao;
import com.cszx.pm.model.project.Complete;
import com.cszx.pm.model.project.CompleteVo;
import com.cszx.pm.model.project.ProgressVo;
import com.cszx.pm.model.project.Project;
import com.cszx.pm.model.project.ProjectExport;
import com.cszx.pm.service.ProjectService;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {
	@Resource
	private ProgressDao progressDao;
	@Resource
	private CompleteDao completeDao;

	@Override
	public List<Project> selectProgress(Map<String, Object> progressMap) {
		return progressDao.selectProgress(progressMap);
	}

	@Override
	public void updateProgress(ProgressVo progressVo) {
		progressDao.updateByPrimaryKeySelective(progressVo);
	}

	@Override
	public void updateComplete(CompleteVo completeVo) {
		completeDao.updateByPrimaryKeySelective(completeVo);

	}

	@Override
	public XSSFWorkbook exportExcel(Map<String, Object> projectMap) {
		XSSFWorkbook xssfWorkbook = null;
		try {
			List<Project> projectList = progressDao.selectProgress(projectMap);
			List<ProjectExport> exportList = new ArrayList<ProjectExport>();
			for (Project project : projectList) {
				ProjectExport projectExport = new ProjectExport();
				setValue(projectExport, project);
				exportList.add(projectExport);
			}
			List<ExcelBean> excel = new ArrayList<>();
			List<ExcelBean> excel1 = new ArrayList<>();
			Map<Integer, List<ExcelBean>> map = new LinkedHashMap<>();
			// 设置标题栏
			excel.add(new ExcelBean("序号", "num", 0, false));
			excel.add(new ExcelBean("所属部门", "pDepartment", 0, false));
			excel.add(new ExcelBean("项目名称", "pName", 0, false));
			excel.add(new ExcelBean("产品名称", "productName", 0, false));
			excel.add(new ExcelBean("项目类别", "pType", 0, false));
			excel.add(new ExcelBean("测试需求", "testType", 0, false));
			excel.add(new ExcelBean("计划备注", "planRemark", 0, false));
			excel.add(new ExcelBean("测试年度", "testYear", 0, false));
			excel.add(new ExcelBean("当前阶段1", "currStateOne", 0, false));
			excel.add(new ExcelBean("当前阶段2", "currStateTwo", 0, false));
			excel.add(new ExcelBean("测试状态", "testState", 0, false));
			excel.add(new ExcelBean("内部测试完成时间", "internalCompleteTime", 0, false));
			excel.add(new ExcelBean("出厂测试完成时间", "factoryCompleteTime", 0, false));
			excel.add(new ExcelBean("第三方测试完成时间", "thirdCompleteTime", 0, false));
			excel.add(new ExcelBean("出厂指标完成情况", null, 14, true));
			excel.add(new ExcelBean("", null, 15, true));
			excel.add(new ExcelBean("", null, 16, true));
			excel.add(new ExcelBean("", null, 17, true));
			excel.add(new ExcelBean("", null, 18, true));
			excel.add(new ExcelBean("", null, 19, true));
			excel.add(new ExcelBean("", null, 20, true));
			excel.add(new ExcelBean("", null, 21, true));
			excel.add(new ExcelBean("第三方指标完成情况", null, 22, true));
			excel.add(new ExcelBean("", null, 23, true));
			excel.add(new ExcelBean("", null, 24, true));
			excel.add(new ExcelBean("", null, 25, true));
			excel.add(new ExcelBean("", null, 26, true));
			excel.add(new ExcelBean("", null, 27, true));
			excel.add(new ExcelBean("", null, 28, true));
			excel.add(new ExcelBean("", null, 29, true));
			excel.add(new ExcelBean("备注", "remark", 0, false));
			excel1.add(new ExcelBean("序号", "num", 0, false));
			excel1.add(new ExcelBean("所属部门", "pDepartment", 0, false));
			excel1.add(new ExcelBean("项目名称", "pName", 0, false));
			excel1.add(new ExcelBean("产品名称", "productName", 0, false));
			excel1.add(new ExcelBean("项目类别", "pType", 0, false));
			excel1.add(new ExcelBean("测试需求", "testType", 0, false));
			excel1.add(new ExcelBean("计划备注", "planRemark", 0, false));
			excel1.add(new ExcelBean("测试年度", "testYear", 0, false));
			excel1.add(new ExcelBean("当前阶段1", "currStateOne", 0, false));
			excel1.add(new ExcelBean("当前阶段2", "currStateTwo", 0, false));
			excel1.add(new ExcelBean("测试状态", "testState", 0, false));
			excel1.add(new ExcelBean("内部测试完成时间", "internalCompleteTime", 0, false));
			excel1.add(new ExcelBean("出厂测试完成时间", "factoryCompleteTime", 0, false));
			excel1.add(new ExcelBean("第三方测试完成时间", "thirdCompleteTime", 0, false));
			excel1.add(new ExcelBean("出厂功能(%)", "factoryFunction", 0, true));
			excel1.add(new ExcelBean("功能轮数(次)", "facFunTimes", 0, true));
			excel1.add(new ExcelBean("功能两轮通过率(%)", "facFunTwoPassed", 0, true));
			excel1.add(new ExcelBean("出厂安全(%)", "factorySecurity", 0, true));
			excel1.add(new ExcelBean("安全轮数(%)", "facSecurityTimes", 0, true));
			excel1.add(new ExcelBean("安全两轮通过率(%)", "facSecurityTwoPassed", 0, true));
			excel1.add(new ExcelBean("代码安全(%)", "factoryCode", 0, true));
			excel1.add(new ExcelBean("安全防护方案(%)", "factorySchema", 0, true));

			excel1.add(new ExcelBean("第三方功能(%)", "thirdFunction", 0, true));
			excel1.add(new ExcelBean("功能轮数(次)", "thirdFunTimes", 0, true));
			excel1.add(new ExcelBean("功能两轮通过率(%)", "thirdFunTwoPassed", 0, true));

			excel1.add(new ExcelBean("第三方安全(%)", "thirdSecurity", 0, true));
			excel1.add(new ExcelBean("安全轮数(次)", "thirdSecurityTimes", 0, true));
			excel1.add(new ExcelBean("安全两轮通过率(%)", "thirdSecurityTwoPassed", 0, true));

			excel1.add(new ExcelBean("代码安全(%)", "thirdCode", 0, true));
			excel1.add(new ExcelBean("安全防护方案(%)", "thirdSchema", 0, true));
			excel1.add(new ExcelBean("备注", "remark", 0, false));

			map.put(0, excel);
			map.put(1, excel1);
			String sheetName = "外部测试进度管理";
			xssfWorkbook = ExcelUtil.createExcelFile(ProjectExport.class, exportList, map, sheetName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xssfWorkbook;
	}

	private void setValue(ProjectExport projectExport, Project project) {
		projectExport.setCurrStateOne(project.getProgress().getCurrStateOne());
		projectExport.setCurrStateTwo(project.getProgress().getCurrStateTwo());
		projectExport.setFactoryCompleteTime(project.getProgress().getFactoryCompleteTime());
		projectExport.setInternalCompleteTime(project.getProgress().getInternalCompleteTime());
		projectExport.setThirdCompleteTime(project.getProgress().getThirdCompleteTime());
		projectExport.setRemark(project.getProgress().getRemark());
		projectExport.setTestState(project.getProgress().getTestState());

		projectExport.setPDepartment(project.getBaseInfo().getPDepartment());
		projectExport.setPlanRemark(project.getBaseInfo().getRemark());
		projectExport.setPName(project.getBaseInfo().getPName());
		projectExport.setProductName(project.getBaseInfo().getProductName());
		projectExport.setPType(project.getBaseInfo().getPType());

		projectExport.setTestType(project.getBaseInfo().getTestType());
		projectExport.setTestYear(project.getBaseInfo().getTestYear());

		projectExport.setFactoryCode(project.getComplete().getFactoryCode());
		projectExport.setFactoryFunction(project.getComplete().getFactoryFunction());
		projectExport.setFacFunTimes(project.getComplete().getFacFunTimes());
		projectExport.setFacFunTwoPassed(project.getComplete().getFacFunTwoPassed());
		projectExport.setFactorySchema(project.getComplete().getFactorySchema());
		projectExport.setFactorySecurity(project.getComplete().getFactorySecurity());
		projectExport.setFacSecurityTimes(project.getComplete().getFacSecurityTimes());
		projectExport.setFacSecurityTwoPassed(project.getComplete().getFacSecurityTwoPassed());
		projectExport.setThirdCode(project.getComplete().getThirdCode());
		projectExport.setThirdFunction(project.getComplete().getThirdFunction());
		projectExport.setThirdFunTimes(project.getComplete().getThirdFunTimes());
		projectExport.setThirdFunTwoPassed(project.getComplete().getThirdFunTwoPassed());
		projectExport.setThirdSchema(project.getComplete().getThirdSchema());
		projectExport.setThirdSecurity(project.getComplete().getThirdSecurity());
		projectExport.setThirdSecurityTimes(project.getComplete().getThirdSecurityTimes());
		projectExport.setThirdSecurityTwoPassed(project.getComplete().getThirdSecurityTwoPassed());

	}

	@Override
	public Complete average() {
		return completeDao.average();
	}

}
