package com.cszx.pm.controller;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.cszx.common.exception.BusinessException;
import com.cszx.common.exception.ExceptionCode;
import com.cszx.pm.model.internal.Defect;
import com.cszx.pm.model.internal.InternalVo;
import com.cszx.pm.model.project.BaseInfo;
import com.cszx.pm.model.project.BaseInfoDto;
import com.cszx.pm.model.project.BaseInfoVo;
import com.cszx.pm.model.project.RemainDay;
import com.cszx.pm.service.BaseInfoService;
import com.cszx.pm.service.InternalService;
import com.cszx.util.DateUtil;
import com.cszx.util.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/baseInfoController")
public class BaseInfoController {
	private final Logger logger = LoggerFactory.getLogger(BaseInfoController.class);
	@Resource
	private BaseInfoService baseInfoService;

	@Resource
	private InternalService internalService;

	/**
	 * 查询项目基础信息
	 * 
	 * @param baseInfoDto
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/selectBaseInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectBaseInfo(BaseInfoDto baseInfoDto) throws ParseException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int rows = baseInfoDto.getRows();
		int page = baseInfoDto.getPage();
		Map<String, Object> baseInfoMap = new HashMap<String, Object>();
		Date internalStartTime = null;
		Date factoryStartTime = null;
		Date thirdStartTime = null;
		Date internalEndTime = null;
		Date factoryEndTime = null;
		Date thirdEndTime = null;
		if (baseInfoDto.getInternalTime() != null && StringUtil.isNotEmpty(baseInfoDto.getInternalTime())) {
			String internalTime = baseInfoDto.getInternalTime();
			String[] times = internalTime.split(" ~ ");
			internalStartTime = DateUtil.parseDateTime(times[0], null);
			internalEndTime = DateUtil.parseDateTime(times[1], null);
		}
		if (baseInfoDto.getFactoryTime() != null && StringUtil.isNotEmpty(baseInfoDto.getFactoryTime())) {
			String factoryTime = baseInfoDto.getFactoryTime();
			String[] times = factoryTime.split(" ~ ");
			factoryStartTime = DateUtil.parseDateTime(times[0], null);
			factoryEndTime = DateUtil.parseDateTime(times[1], null);
		}
		if (baseInfoDto.getThirdTime() != null && StringUtil.isNotEmpty(baseInfoDto.getThirdTime())) {
			String thirdTime = baseInfoDto.getThirdTime();
			String[] times = thirdTime.split(" ~ ");
			thirdStartTime = DateUtil.parseDateTime(times[0], null);
			thirdEndTime = DateUtil.parseDateTime(times[1], null);
		}
		baseInfoMap.put("pDepartment", StringUtil.parseToInteger(baseInfoDto.getPDepartment()));
		baseInfoMap.put("pType", StringUtil.parseToInteger(baseInfoDto.getPType()));
		baseInfoMap.put("pName", baseInfoDto.getPName());
		baseInfoMap.put("testYear", baseInfoDto.getTestYear());
		baseInfoMap.put("testState", baseInfoDto.getTestState());
		baseInfoMap.put("internalStartTime", internalStartTime);
		baseInfoMap.put("factoryStartTime", factoryStartTime);
		baseInfoMap.put("thirdStartTime", thirdStartTime);
		baseInfoMap.put("internalEndTime", internalEndTime);
		baseInfoMap.put("factoryEndTime", factoryEndTime);
		baseInfoMap.put("thirdEndTime", thirdEndTime);
		baseInfoMap.put("dorg", StringUtil.parseToInteger(baseInfoDto.getDorg()));
		try {
			PageHelper.startPage(page, rows, true);
			List<BaseInfo> baseInfoList = baseInfoService.selectBaseInfo(baseInfoMap);
			PageInfo<BaseInfo> pageInfo = new PageInfo<BaseInfo>(baseInfoList);
			logger.info("查询项目基础信息成功！");
			resultMap.put("rows", baseInfoList);
			resultMap.put("total", pageInfo.getTotal());
		} catch (Exception e) {
			logger.info("查询项目基础信息失败！");
			throw new BusinessException("查询失败", e);
		}
		return resultMap;
	}

	@RequestMapping(value = "/selectBaseInfoById", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectBaseInfoById(String id) throws ParseException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			BaseInfo baseInfo = baseInfoService.selectBaseInfoById(id);
			logger.info("查询项目基础信息成功！");
			resultMap.put("code", 0);
			resultMap.put("mes", "");
			resultMap.put("rows", baseInfo);
		} catch (Exception e) {
			logger.info("查询项目基础信息失败！");
			throw new BusinessException("查询失败", e);
		}
		return resultMap;
	}

	@RequestMapping(value = "/addOrUpdateBaseInfo", method = RequestMethod.POST)
	@Transactional(rollbackFor = Exception.class)
	@ResponseBody
	public Map<String, Object> addOrUpdateBaseInfo(@Validated BaseInfoVo baseInfoVo, BindingResult bindingResult)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (bindingResult.hasErrors()) {
			Map<String, String> err = new HashMap<String, String>();
			List<FieldError> list = bindingResult.getFieldErrors();
			FieldError error = null;
			for (int i = 0; i < list.size(); i++) {
				error = list.get(i);
				err.put(error.getField(), error.getDefaultMessage());
				System.out.print(error.getField() + ":" + error.getDefaultMessage());
			}
			resultMap.put("errors", bindingResult.getFieldErrors());
		} else {
			try {
				if (!StringUtil.isNotEmpty(baseInfoVo.getId())) {
					List<BaseInfo> pNames = baseInfoService.getPnameList(baseInfoVo);
					if (pNames != null && pNames.size() > 0) {
						throw new BusinessException("该项目名称已存在！");
					} else {
						String baseInfoId = UUID.randomUUID().toString();
						baseInfoVo.setId(baseInfoId);
						baseInfoService.addBaseInfo(baseInfoVo);
						Defect defect = new Defect();
						defect.setId(UUID.randomUUID().toString());
						defect.setBaseInfoId(baseInfoId);
						defect.setSecurityDefect(0.0);
						defect.setSchemaDefect(0.0);
						defect.setCodeDefect(0.0);
						defect.setFunctionDefect(0.0);
						internalService.addDefect(defect);
						List<String> types = getTestType(baseInfoVo.getTestType());
						for (String type : types) {
							InternalVo internalVo = new InternalVo();
							internalVo.setId(UUID.randomUUID().toString());
							internalVo.setBaseInfoId(baseInfoId);
							internalVo.setTestItem(type);
							internalService.addInternal(internalVo);
						}
						resultMap.put("mes", "success");
						logger.info("基础信息添加成功！");
					}
				} else {
					List<BaseInfo> pNames = baseInfoService.getPnameList(baseInfoVo);
					if (pNames != null && pNames.size() > 0) {
						throw new BusinessException("该项目名称已存在！");
					} else {
						baseInfoService.updateBaseInfo(baseInfoVo);
						Map<String, Object> map = new HashMap<String, Object>();
						List<String> types = getTestType(baseInfoVo.getTestType());
						for (String type : types) {
							map.put("id", baseInfoVo.getId());
							map.put("testItem", type);
							if (!baseInfoService.checkInternalExist(map)) {
								InternalVo internalVo = new InternalVo();
								internalVo.setId(UUID.randomUUID().toString());
								internalVo.setBaseInfoId(baseInfoVo.getId());
								internalVo.setTestItem(type);
								internalService.addInternal(internalVo);
							}
						}

						resultMap.put("mes", "success");
						logger.info("基础信息修改成功！");
					}
				}
			} catch (Exception e) {
				if (e instanceof BusinessException) {
					throw e;
				}
				logger.info("基础信息添加或修改失败！");
				throw new BusinessException("添加或修改失败", e);
			}

		}

		return resultMap;
	}

	@RequestMapping(value = "/deleteBaseInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteBaseInfo(String ids) {
		Map<String, Object> requestMap = new HashMap<String, Object>();
		String[] strs = ids.split(",");
		List<String> idList = new ArrayList<String>();
		for (String s : strs) {
			idList.add(s);
		}
		try {
			baseInfoService.deleteBaseInfo(idList);
			internalService.deleteInternalByBaseInfoId(idList);// 删除内部信息和轮次信息
			internalService.deleteDefect(idList);// 删除缺陷率
			requestMap.put("mes", "success");
			logger.info("基础信息删除成功！");
		} catch (Exception e) {
			logger.info("基础信息删除失败！");
			throw new BusinessException(ExceptionCode.delete_failure, e);
		}
		return requestMap;
	}

	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
	@ResponseBody
	public void exportExcel(String json, HttpServletResponse response) throws ParseException {
		Map<String, Object> baseInfoMap = new HashMap<String, Object>();
		BaseInfoDto baseInfoDto = JSON.parseObject(json, BaseInfoDto.class);
		Date internalStartTime = null;
		Date factoryStartTime = null;
		Date thirdStartTime = null;
		Date internalEndTime = null;
		Date factoryEndTime = null;
		Date thirdEndTime = null;
		if (baseInfoDto.getInternalTime() != null && StringUtil.isNotEmpty(baseInfoDto.getInternalTime())) {
			String internalTime = baseInfoDto.getInternalTime();
			String[] times = internalTime.split(" ~ ");
			internalStartTime = DateUtil.parseDateTime(times[0], null);
			internalEndTime = DateUtil.parseDateTime(times[1], null);
		}
		if (baseInfoDto.getFactoryTime() != null && StringUtil.isNotEmpty(baseInfoDto.getFactoryTime())) {
			String factoryTime = baseInfoDto.getFactoryTime();
			String[] times = factoryTime.split(" ~ ");
			factoryStartTime = DateUtil.parseDateTime(times[0], null);
			factoryEndTime = DateUtil.parseDateTime(times[1], null);
		}
		if (baseInfoDto.getThirdTime() != null && StringUtil.isNotEmpty(baseInfoDto.getThirdTime())) {
			String thirdTime = baseInfoDto.getThirdTime();
			String[] times = thirdTime.split(" ~ ");
			thirdStartTime = DateUtil.parseDateTime(times[0], null);
			thirdEndTime = DateUtil.parseDateTime(times[1], null);
		}
		baseInfoMap.put("pDepartment", StringUtil.parseToInteger(baseInfoDto.getPDepartment()));
		baseInfoMap.put("pType", StringUtil.parseToInteger(baseInfoDto.getPType()));
		baseInfoMap.put("pName", baseInfoDto.getPName());
		baseInfoMap.put("testYear", baseInfoDto.getTestYear());
		baseInfoMap.put("internalStartTime", internalStartTime);
		baseInfoMap.put("factoryStartTime", factoryStartTime);
		baseInfoMap.put("thirdStartTime", thirdStartTime);
		baseInfoMap.put("internalEndTime", internalEndTime);
		baseInfoMap.put("factoryEndTime", factoryEndTime);
		baseInfoMap.put("thirdEndTime", thirdEndTime);
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");// 可以方便地修改日期格式
		String currentTime = dateFormat.format(currentDate);
		String fileName = "export_" + currentTime + ".xlsx";
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ";charset=UTF-8");
		try {
			XSSFWorkbook workbook = baseInfoService.exportExcel(baseInfoMap);
			OutputStream output = response.getOutputStream();
			BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
			workbook.write(bufferedOutPut);
			bufferedOutPut.flush();
			bufferedOutPut.close();
			logger.info("基础信息导出成功！");
		} catch (Exception e) {
			logger.info("基础信息导出失败！");
			throw new BusinessException("基础信息导出失败！", e);
		}
	}

	/**
	 * 导入excel
	 * 
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> importExcel(@RequestParam("file") CommonsMultipartFile file) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			InputStream in = file.getInputStream();
			baseInfoService.importExcel(in, file);
			in.close();
			resultMap.put("mes", "success");
			logger.info("基础信息导入成功！");
		} catch (Exception e) {
			logger.info("基础信息导入失败！");
			throw new BusinessException(e);
		}
		return resultMap;
	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	@ResponseBody
	public void download(HttpSession session, HttpServletResponse response) throws IOException {
		FileInputStream fileInputStream = null;
		ServletOutputStream servletOutputStream = null;
		String basePath = session.getServletContext().getRealPath("/"); // 获取基本路径
		String fileurl = "/static/template.xlsx";
		String fileName = "template.xlsx";
		try {
			response.setContentType("application/vnd.ms-excel;");
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ";charset=UTF-8");
			// 将本地文件装载到内存
			fileInputStream = new FileInputStream(basePath + fileurl);
			// 实例化输出流
			servletOutputStream = response.getOutputStream();
			byte[] buff = new byte[20480];
			int bytesRead;
			// 每次尝试读取buff.length长字节，直到读完、bytesRead为-1
			while ((bytesRead = fileInputStream.read(buff, 0, buff.length)) != -1) {
				// 每次写bytesRead长字节
				servletOutputStream.write(buff, 0, bytesRead);
			}
			// 刷新缓冲区
			servletOutputStream.flush();
			logger.info("下载文件成功");
		} catch (IOException e) {
			logger.info("下载文件失败");
			throw new BusinessException("下载模板失败");
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					logger.info("下载文件失败");
					throw new BusinessException("关闭文件流失败");
				}
			}
			if (servletOutputStream != null) {
				try {
					servletOutputStream.close();
				} catch (IOException e) {
					logger.info("下载文件失败");
					throw new BusinessException("关闭文件流失败");
				}
			}
		}
	}

	@RequestMapping(value = "/countRemainDay", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> countRemainDay(String today) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		map.put("today", today);
		List<RemainDay> remainDays = baseInfoService.countRemainDay(map);
		resultMap.put("total", remainDays.size());
		resultMap.put("rows", remainDays);
		return resultMap;
	}

	public List<String> getTestType(String testType) {
		List<String> types = new ArrayList<String>();
		if (testType.contains("功能/非功能测试")) {
			types.add("功能/非功能测试");
		}
		if (testType.contains("安全功能测试")) {
			types.add("安全功能测试");
			types.add("安全防护方案");
		}
		if (testType.contains("渗透测试")) {
			types.add("渗透测试");
		}
		if (testType.contains("性能测试")) {
			types.add("性能测试");
		}
		if (testType.contains("代码扫描")) {
			types.add("代码扫描");
		}
		return types;
	}

}
