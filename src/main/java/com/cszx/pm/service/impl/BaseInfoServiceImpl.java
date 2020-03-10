package com.cszx.pm.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cszx.common.excel.ExcelBean;
import com.cszx.common.excel.ExcelUtil;
import com.cszx.common.exception.ServiceException;
import com.cszx.pm.dao.datadic.DatadicItemsDao;
import com.cszx.pm.dao.project.BaseInfoDao;
import com.cszx.pm.dao.project.CompleteDao;
import com.cszx.pm.dao.project.ProgressDao;
import com.cszx.pm.model.project.BaseInfo;
import com.cszx.pm.model.project.BaseInfoVo;
import com.cszx.pm.model.project.Complete;
import com.cszx.pm.model.project.Progress;
import com.cszx.pm.model.project.RemainDay;
import com.cszx.pm.service.BaseInfoService;

@Service("baseInfoService")
public class BaseInfoServiceImpl implements BaseInfoService {
	@Resource
	private BaseInfoDao baseInfoDao;
	@Resource
	private DatadicItemsDao datadicItemsDao;
	@Resource
	private ProgressDao progressDao;
	@Resource
	private CompleteDao completeDao;

	@Override
	public List<BaseInfo> selectBaseInfo(Map<String, Object> baseInfoMap) throws Exception {
		return baseInfoDao.selectBaseInfo(baseInfoMap);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void addBaseInfo(BaseInfoVo baseInfoVo) throws Exception {
		baseInfoDao.addBaseInfo(baseInfoVo);
		if (baseInfoVo.getThirdTest() != null && baseInfoVo.getThirdTest() == 0) {
			this.addRelation(baseInfoVo.getId());
		}

	}

	@Override
	public void updateBaseInfo(BaseInfoVo baseInfoVo) throws Exception {
		baseInfoDao.updateByPrimaryKeySelective(baseInfoVo);
		List<String> idList = new ArrayList<String>();
		idList.add(baseInfoVo.getId());
		if (baseInfoVo.getThirdTest() != null && baseInfoVo.getThirdTest() == 1) {
			progressDao.deleteByBaseInfoId(idList);
			completeDao.deleteByBaseInfoId(idList);
		} else if (baseInfoVo.getThirdTest() != null && baseInfoVo.getThirdTest() == 0) {
			if (progressDao.selectByBaseInfoId(baseInfoVo.getId()) == 0) {
				this.addRelation(baseInfoVo.getId());
			}
		}

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteBaseInfo(List<String> idList) throws Exception {
		baseInfoDao.deleteBaseInfo(idList);
		progressDao.deleteByBaseInfoId(idList);
		completeDao.deleteByBaseInfoId(idList);
		// reportDao.deleteReport(idList);
	}

	@Override
	public XSSFWorkbook exportExcel(Map<String, Object> baseInfoMap) throws Exception {
		XSSFWorkbook xssfWorkbook = null;
		try {
			List<BaseInfo> baseInfos = baseInfoDao.selectBaseInfo(baseInfoMap);
			List<ExcelBean> excel = new ArrayList<>();
			Map<Integer, List<ExcelBean>> map = new LinkedHashMap<>();
			// 设置标题栏
			excel.add(new ExcelBean("序号", "num", 0, false));
			excel.add(new ExcelBean("所属部门", "pDepartment", 0, false));
			excel.add(new ExcelBean("项目名称", "pName", 0, false));
			excel.add(new ExcelBean("产品名称", "productName", 0, false));
			excel.add(new ExcelBean("项目类别", "pType", 0, false));
			excel.add(new ExcelBean("测试需求", "testType", 0, false));
			excel.add(new ExcelBean("等保等级", "pLevel", 0, false));
			excel.add(new ExcelBean("计划内部测试时间", "internalTime", 0, false));
			excel.add(new ExcelBean("计划出厂测试时间", "factoryTime", 0, false));
			excel.add(new ExcelBean("计划第三方测试时间", "thirdTime", 0, false));
			excel.add(new ExcelBean("第三方测试机构", "thirdTest", 0, false));
			excel.add(new ExcelBean("计划备注", "remark", 0, false));
			excel.add(new ExcelBean("测试年度", "testYear", 0, false));
			excel.add(new ExcelBean("项目经理", "pmName", 0, false));
			excel.add(new ExcelBean("联系电话", "pmTel", 0, false));
			excel.add(new ExcelBean("邮箱", "pmEmail", 0, false));
			map.put(0, excel);
			String sheetName = "项目基础信息";
			xssfWorkbook = ExcelUtil.createExcelFile(BaseInfo.class, baseInfos, map, sheetName);
		} catch (Exception e) {
			throw e;
		}
		return xssfWorkbook;
	}

	@Override
	public void importExcel(InputStream in, CommonsMultipartFile file) throws Exception {
		try {
			List<List<Object>> listob = ExcelUtil.getBankListByExcel(in, file.getOriginalFilename());
			List<BaseInfoVo> baseInfoList = new ArrayList<BaseInfoVo>();
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap.put("pDepartment", "project_department");
			dataMap.put("pType", "project_type");
			dataMap.put("pLevel", "project_level");
			dataMap.put("testType", "test_type");
			// 遍历listob数据，把数据放到List中
			for (int i = 0; i < listob.size(); i++) {
				List<Object> ob = listob.get(i);
				BaseInfoVo baseInfo = new BaseInfoVo();
				baseInfo.setId(UUID.randomUUID().toString());
				baseInfo.setPDepartment(getInt(dataMap, "pDepartment", String.valueOf(ob.get(1))));
				if (baseInfoDao.countPname(ob.get(2).toString()) == 0) {
					baseInfo.setPName(String.valueOf(ob.get(2)));
				} else {
					throw new ServiceException("第" + (i + 1) + "行3列的项目名已存在");
				}
				baseInfo.setProductName(String.valueOf(ob.get(3)));
				baseInfo.setPType(getInt(dataMap, "pType", String.valueOf(ob.get(4))));
				// baseInfo.setTestType(getInt(dataMap, "testType",
				// String.valueOf(ob.get(5))));
				baseInfo.setPLevel(getInt(dataMap, "pLevel", String.valueOf(ob.get(6))));
				baseInfo.setInternalTime(String.valueOf(ob.get(7)));
				baseInfo.setFactoryTime(String.valueOf(ob.get(8)));
				baseInfo.setThirdTime(String.valueOf(ob.get(9)));
				baseInfo.setThirdTest(getInt(dataMap, "thirdTest", String.valueOf(ob.get(10))));
				baseInfo.setRemark(String.valueOf(ob.get(11)));
				baseInfo.setTestYear(String.valueOf(ob.get(12)));
				baseInfo.setPmName(String.valueOf(ob.get(13)));
				baseInfo.setPmTel(String.valueOf(ob.get(14)));
				baseInfo.setPmEmail(String.valueOf(ob.get(15)));

				baseInfoList.add(baseInfo);
			}

			for (BaseInfoVo baseInfo : baseInfoList) {
				this.addBaseInfo(baseInfo);
			}
		} catch (Exception e) {
			// throw new ServiceException("请确认单元格格式是否为文本格式！", e);
			throw e;
		}
	}

	public Integer getInt(Map<String, String> dataMap, String key, String itemsName) throws Exception {
		String groupCode = dataMap.get(key);
		Map<String, String> map = new HashMap<String, String>();
		map.put("groupCode", groupCode);
		map.put("dataitemName", itemsName);
		int num = Integer.parseInt(datadicItemsDao.getItemCode(map));
		return num;
	}

	public void addRelation(String baseInfoId) throws Exception {
		Progress progress = new Progress();
		progress.setBaseInfoId(baseInfoId);
		progress.setId(UUID.randomUUID().toString());
		progress.setCurrStateOne(0);
		progress.setCurrStateTwo(0);
		Complete complete = new Complete();
		complete.setBaseInfoId(baseInfoId);
		complete.setId(UUID.randomUUID().toString());
		progressDao.insertSelective(progress);
		completeDao.insertSelective(complete);
		// ReportVo reportVo = new ReportVo();
		// reportVo.setId(UUID.randomUUID().toString());
		// reportVo.setBaseInfoId(baseInfoId);
		// reportVo.setCurrStateOne(0);
		// reportVo.setCurrStateTwo("0,4,8");
		// reportVo.setRiskLevel(3);
		// reportDao.addReport(reportVo);// 新增出厂测试
		// reportVo.setId("");
		// reportVo.setId(UUID.randomUUID().toString());
		// reportVo.setCurrStateOne(1);
		// reportDao.addReport(reportVo);// 新增第三方测试
	}

	@Override
	public List<BaseInfo> getPnames() throws Exception {
		return baseInfoDao.getPnames();
	}

	@Override
	public List<BaseInfo> getPnameList(BaseInfoVo baseInfoVo) throws Exception {
		return baseInfoDao.getPnameList(baseInfoVo);
	}

	@Override
	public String getTestType(String id) throws Exception {
		return baseInfoDao.getTestType(id);
	}

	@Override
	public BaseInfo selectBaseInfoById(String id) {
		return baseInfoDao.selectBaseInfoById(id);
	}

	@Override
	public List<RemainDay> countRemainDay(Map<String, Object> map) {
		return baseInfoDao.countRemainDay(map);
	}

	@Override
	public boolean checkInternalExist(Map<String, Object> map) {
		return baseInfoDao.checkInternalExist(map);
	}

}
