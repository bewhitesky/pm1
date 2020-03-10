package com.cszx.pm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.cszx.common.excel.ExcelBean;
import com.cszx.common.excel.ExcelUtil;
import com.cszx.pm.dao.Internal.InternalDao;
import com.cszx.pm.dao.Internal.InternalDefectDao;
import com.cszx.pm.dao.Internal.InternalItemDao;
import com.cszx.pm.model.internal.Defect;
import com.cszx.pm.model.internal.Internal;
import com.cszx.pm.model.internal.InternalDefect;
import com.cszx.pm.model.internal.InternalExport;
import com.cszx.pm.model.internal.InternalItem;
import com.cszx.pm.model.internal.InternalItemVo;
import com.cszx.pm.model.internal.InternalVo;
import com.cszx.pm.model.internal.Statistics;
import com.cszx.pm.service.InternalService;
import com.cszx.util.DateUtil;
import com.cszx.util.StringUtil;

@Service("internalService")
public class InternalServiceImpl implements InternalService {
	@Resource
	private InternalDao internalDao;
	@Resource
	private InternalItemDao internalItemDao;
	@Resource
	private InternalDefectDao internalDefectDao;

	@Override
	public List<Internal> selectInternalTest(Map<String, Object> internalMap) {
		return internalDao.selectInternalTest(internalMap);
	}

	@Override
	public List<Defect> selectDefect(Map<String, Object> defectMap) {
		return internalDao.selectDefect(defectMap);
	}

	@Override
	public List<InternalItem> selectInternalItems(String id) {
		return internalItemDao.selectInternalItems(id);
	}

	@Override
	public void addInternal(InternalVo internalVo) {
		internalDao.addInternal(internalVo);
	}

	@Override
	public void updateInternal(InternalVo internalVo) {
		internalDao.updateInternal(internalVo);
	}

	@Override
	public void addItems(InternalItemVo internalItemVo) {
		internalItemDao.addInternalItems(internalItemVo);
	}

	@Override
	public void addDefect(Defect defect) {
		internalDao.addDefect(defect);
	}

	@Override
	public void updateItems(InternalItemVo internalItemVo) {
		internalItemDao.updateInternalItems(internalItemVo);
	}

	@Override
	public void deleteItems(List<String> idList) {
		internalItemDao.deleteItems(idList);
	}

	@Override
	public void deleteDefect(List<String> idList) {
		internalDao.deleteDefect(idList);
	}

	@Override
	public void deleteInternal(List<String> idList) {
		internalDao.deleteInternal(idList);
		internalDao.deleteItemsByInternalId(idList);
	}

	@Override
	public void deleteInternalByBaseInfoId(List<String> idList) {
		List<String> internalIdList = new ArrayList<String>();
		internalIdList = internalDao.selectInternalTestIdByBaseInfoId(idList);
		if (internalIdList.size() > 0) {
			internalDao.deleteItemsByInternalId(internalIdList);
			internalDefectDao.deleteDefectByInternalId(internalIdList);
		}
		internalDao.deleteInternalByBaseInfoId(idList);
	}

	@Override
	public void deleteItemsByInternalId(List<String> idList) {
		internalDao.deleteItemsByInternalId(idList);
	}

	@Override
	public Date getTestEndTime(Map<String, Object> map) {
		return internalItemDao.getTestEndTime(map);
	}

	@Override
	public void updateDefect(Defect defect) {
		internalDao.updateDefect(defect);
	}

	@Override
	public int countWorkday(String testStartTime, String testEndTime) {
		return internalDao.countWorkday(testStartTime, testEndTime);
	}

	@Override
	public XSSFWorkbook exportInternal(Map<String, Object> internalmap) {
		XSSFWorkbook xssfWorkbook = null;
		try {

			List<InternalExport> internals = internalDao.exportInternal(internalmap);

			List<ExcelBean> excel = new ArrayList<>();
			Map<Integer, List<ExcelBean>> map = new LinkedHashMap<>();
			// 设置标题栏
			excel.add(new ExcelBean("序号", "id", 0, false));
			excel.add(new ExcelBean("所属部门", "pDepartment", 0, false));
			excel.add(new ExcelBean("项目名称", "pName", 0, false));
			excel.add(new ExcelBean("等保等级", "pLevel", 0, false));
			excel.add(new ExcelBean("项目类别", "pType", 0, false));
			excel.add(new ExcelBean("计划出厂测试时间", "factoryTime", 0, false));
			excel.add(new ExcelBean("测试项", "testItem", 0, false));
			excel.add(new ExcelBean("项目接收时间", "receiveTime", 0, false));
			excel.add(new ExcelBean("测试服务器", "testServer", 0, false));
			excel.add(new ExcelBean("测试状态", "internalTestState", 0, false));
			excel.add(new ExcelBean("备注", "remark", 0, false));
			excel.add(new ExcelBean("测试负责人", "testPrincipal", 0, false));
			excel.add(new ExcelBean("计划环境搭建时间", "deploymentTime", 0, false));
			excel.add(new ExcelBean("测试配合人员", "testerName", 0, false));
			excel.add(new ExcelBean("联系电话", "testerTel", 0, false));
			excel.add(new ExcelBean("邮箱", "testerEmail", 0, false));
			excel.add(new ExcelBean("测试轮次", "internalTestTimes", 0, false));
			excel.add(new ExcelBean("实际搭建开始时间", "deployStartTime", 0, false));
			excel.add(new ExcelBean("实际搭建结束时间", "deployEndTime", 0, false));
			excel.add(new ExcelBean("测试开始时间", "testStartTime", 0, false));
			excel.add(new ExcelBean("测试结束时间", "testEndTime", 0, false));
			excel.add(new ExcelBean("每轮整改时间", "correctTime", 0, false));
			excel.add(new ExcelBean("每轮测试时间", "testTime", 0, false));
			map.put(0, excel);
			String sheetName = "内部测试进度管理";
			xssfWorkbook = ExcelUtil.createExcelFile(InternalExport.class, internals, map, sheetName);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return xssfWorkbook;
	}

	@Override
	public void addInternalDefect(InternalDefect internalDefect) {
		internalDefectDao.insertSelective(internalDefect);
	}

	@Override
	public void updateInternalDefect(InternalDefect internalDefect) {
		internalDefectDao.updateByPrimaryKeySelective(internalDefect);
	}

	@Override
	public InternalDefect selectInternalDefect(String id) {
		return internalDefectDao.selectByPrimaryKey(id);
	}

	@Override
	public void deleteInternalDefect(String id) {
		internalDefectDao.deleteByPrimaryKey(id);

	}

	@Override
	public List<Statistics> statistics(Map<String, Object> map) {
		List<Statistics> statisticsList = internalDao.statistics(map);
		String oldId = "";
		int defectNum = 0;
		int caseNum = 0;
		String testTime = "";
		String correctTime = "";
		Date testStateTime = null;
		Date testEndTime = null;
		int times = 0;
		int flag = 0;
		int index = 0;
		List<Statistics> newStatisticsList = new ArrayList<Statistics>();
		for (Statistics s : statisticsList) {
			if (oldId.equals(s.getId())) {
				if ((s.getTestItem().contains("渗透"))) {
					s.setId(UUID.randomUUID().toString());
					defectNum = defectNum + s.getDefectNum();
					caseNum = caseNum + s.getCaseNum();
					if (times > s.getTimes()) {
					} else {
						times = s.getTimes();
					}

					if (StringUtil.isNotEmpty(correctTime) && Integer.parseInt(correctTime) < Integer
							.parseInt(s.getCorrectTime() == null ? "0" : s.getCorrectTime())) {
						correctTime = StringUtil.doNullString(s.getCorrectTime());
					}
					if (StringUtil.isNotEmpty(testTime) && Integer.parseInt(testTime) < Integer
							.parseInt(s.getTestTime() == null ? "0" : s.getTestTime())) {
						testTime = StringUtil.doNullString(s.getTestTime());
					}
					if (testStateTime != null && s.getTestStartTime() != null) {
						if (DateUtil.dateToStr(testStateTime, "yyyyMMdd")
								.compareTo(DateUtil.dateToStr(s.getTestStartTime(), "yyyyMMdd")) < 0) {
						} else {
							testStateTime = s.getTestStartTime();
						}
					} else {
						if (testStateTime != null) {
						} else {
							testStateTime = s.getTestStartTime();
						}
					}
					if (testEndTime != null && s.getTestEndTime() != null) {
						if (DateUtil.dateToStr(testEndTime, "yyyyMMdd")
								.compareTo(DateUtil.dateToStr(s.getTestEndTime(), "yyyyMMdd")) > 0) {

						} else {
							testEndTime = s.getTestEndTime();
						}
					} else {
						if (testEndTime != null) {
						} else {
							testEndTime = s.getTestEndTime();
						}

					}
					Statistics statistics = new Statistics();
					statistics.setId(UUID.randomUUID().toString());
					statistics.setPName(s.getPName());
					statistics.setTestItem("安全测试");
					statistics.setTimes(times);
					if (caseNum == 0) {
						statistics.setDefect(0.0);
					} else {
						statistics.setDefect(defectNum * 100.0 / caseNum);
					}
					statistics.setCaseNum(caseNum);
					statistics.setDefectNum(defectNum);
					statistics.setCorrectTime(correctTime);
					statistics.setTestTime(testTime);
					statistics.setTestStartTime(testStateTime);
					statistics.setTestEndTime(testEndTime);

					if (flag == 1) {
						newStatisticsList.remove(index);
						newStatisticsList.add(s);
						newStatisticsList.add(statistics);
					} else {
						newStatisticsList.add(s);
						newStatisticsList.add(statistics);
						flag = 1;
					}
					index++;
				} else {
					oldId = s.getId();
					s.setId(UUID.randomUUID().toString());
					// if (s.getTestItem().contains("安全防护")) {
					// newStatisticsList.add(index-1,s);
					// } else {
					// newStatisticsList.add(s);
					// }
					newStatisticsList.add(s);
					if (s.getTestItem().contains("安全功能")) {
						defectNum = defectNum + s.getDefectNum();
						caseNum = caseNum + s.getCaseNum();
						if (times > s.getTimes()) {
						} else {
							times = s.getTimes();
						}
						if (StringUtil.isNotEmpty(correctTime) && Integer.parseInt(correctTime) < Integer
								.parseInt(s.getCorrectTime() == null ? "0" : s.getCorrectTime())) {
							correctTime = StringUtil.doNullString(s.getCorrectTime());
						}
						if (StringUtil.isNotEmpty(testTime) && Integer.parseInt(testTime) < Integer
								.parseInt(s.getTestTime() == null ? "0" : s.getTestTime())) {
							testTime = StringUtil.doNullString(s.getTestTime());
						}

						if (testStateTime != null && s.getTestStartTime() != null) {
							if (DateUtil.dateToStr(testStateTime, "yyyyMMdd")
									.compareTo(DateUtil.dateToStr(s.getTestStartTime(), "yyyyMMdd")) < 0) {
							} else {
								testStateTime = s.getTestStartTime();
							}
						} else {
							if (testStateTime != null) {
							} else {
								testStateTime = s.getTestStartTime();
							}
						}
						if (testEndTime != null && s.getTestEndTime() != null) {
							if (DateUtil.dateToStr(testEndTime, "yyyyMMdd")
									.compareTo(DateUtil.dateToStr(s.getTestEndTime(), "yyyyMMdd")) > 0) {

							} else {
								testEndTime = s.getTestEndTime();
							}
						} else {
							if (testEndTime != null) {
							} else {
								testEndTime = s.getTestEndTime();
							}

						}
					}
				}
			} else {
				oldId = s.getId();
				if (s.getTestItem().contains("安全功能")) {
					s.setId(UUID.randomUUID().toString());
					defectNum = s.getDefectNum();
					caseNum = s.getCaseNum();
					testStateTime = s.getTestStartTime();
					testEndTime = s.getTestEndTime();
					testTime = StringUtil.doNullString(s.getTestTime());
					correctTime = StringUtil.doNullString(s.getCorrectTime());
					times = s.getTimes();
				} else {
					defectNum = 0;
					caseNum = 0;
					testTime = "0";
					correctTime = "0";
					testStateTime = null;
					testEndTime = null;
					times = 0;
				}
				flag = 0;
				newStatisticsList.add(s);
			}
			index++;
		}
		return newStatisticsList;
	}

	@Override
	public XSSFWorkbook exportStatistics(Map<String, Object> exportmap) {
		XSSFWorkbook xssfWorkbook = null;
		try {

			List<Statistics> statistics = this.statistics(exportmap);

			List<ExcelBean> excel = new ArrayList<>();
			Map<Integer, List<ExcelBean>> map = new LinkedHashMap<>();
			// 设置标题栏
			excel.add(new ExcelBean("序号", "id", 0, false));
			excel.add(new ExcelBean("项目名称", "pName", 0, false));
			excel.add(new ExcelBean("测试项", "testItem", 0, false));
			excel.add(new ExcelBean("测试轮数", "times", 0, false));
			excel.add(new ExcelBean("首轮缺陷率", "defect", 0, false));
			excel.add(new ExcelBean("测试总用时", "testTime", 0, false));
			excel.add(new ExcelBean("整改总用时", "correctTime", 0, false));
			excel.add(new ExcelBean("首轮测试开始时间", "testStartTime", 0, false));
			excel.add(new ExcelBean("末轮测试结束时间", "testEndTime", 0, false));
			excel.add(new ExcelBean("缺陷数", "defectNum", 0, false));
			excel.add(new ExcelBean("用例数", "caseNum", 0, false));
			map.put(0, excel);
			String sheetName = "内部测试统计结果";
			xssfWorkbook = ExcelUtil.createExcelFile(Statistics.class, statistics, map, sheetName);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return xssfWorkbook;
	}

}
