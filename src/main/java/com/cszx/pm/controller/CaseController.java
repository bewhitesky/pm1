package com.cszx.pm.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cszx.pm.model.caselibrary.Case;
import com.cszx.pm.model.caselibrary.CaseDto;
import com.cszx.pm.service.CaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/caseController")
public class CaseController {
	private final Logger logger = LoggerFactory.getLogger(CaseController.class);
	@Resource
	private CaseService caseService;

	/**
	 * 查询项目基础信息
	 * 
	 * @param baseInfoDto
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/selectCase", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectCase(CaseDto caseDto) throws ParseException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		int page = caseDto.getPage();
		int nums = caseDto.getNums();
		PageHelper.startPage(page, nums, true);
		Case case1 = new Case();
		List<Case> caseList = caseService.selectCase(case1);
		PageInfo<Case> pageInfo = new PageInfo<Case>(caseList);
		resultMap.put("code", 0);
		resultMap.put("mes", "");
		resultMap.put("rows", caseList);
		resultMap.put("total", pageInfo.getTotal());
		return resultMap;
	}

}
