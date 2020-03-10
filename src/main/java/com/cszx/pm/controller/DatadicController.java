package com.cszx.pm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cszx.common.exception.BusinessException;
import com.cszx.common.exception.ExceptionCode;
import com.cszx.pm.model.datadic.Datadic;
import com.cszx.pm.model.datadic.DatadicGroups;
import com.cszx.pm.model.datadic.DatadicGroupsDto;
import com.cszx.pm.model.datadic.DatadicItems;
import com.cszx.pm.model.datadic.DatadicItemsDto;
import com.cszx.pm.service.DatadicService;
import com.cszx.util.CacheUtil;
import com.cszx.util.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/datadicController")
public class DatadicController {
	private final Logger logger = LoggerFactory.getLogger(DatadicController.class);
	@Resource
	private DatadicService datadicService;

	@RequestMapping(value = "/selectGroups", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectGroups(DatadicGroupsDto groupsDto) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int page = groupsDto.getPage();
		int nums = groupsDto.getNums();
		Map<String, Object> groupsMap = new HashMap<String, Object>();
		groupsMap.put("groupCode", groupsDto.getGroupCode());
		groupsMap.put("groupName", groupsDto.getGroupName());
		PageHelper.startPage(page, nums, true);
		List<DatadicGroups> groupsList = datadicService.selectGroups(groupsMap);
		PageInfo<DatadicGroups> pageInfo = new PageInfo<DatadicGroups>(groupsList);
		resultMap.put("code", 0);
		resultMap.put("mes", "");
		resultMap.put("rows", groupsList);
		resultMap.put("total", pageInfo.getTotal());
		return resultMap;
	}

	@RequestMapping(value = "/selectItems", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectItems(DatadicItemsDto itemsDto) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int page = itemsDto.getPage();
		int nums = itemsDto.getNums();
		Map<String, Object> itemsMap = new HashMap<String, Object>();
		itemsMap.put("groupCode", itemsDto.getGroupCode());
		// itemsMap.put("dataitemCode", itemsDto.getDataitemCode());
		// itemsMap.put("dataitemName", itemsDto.getDataitemName());
		List<DatadicItems> itemsList;
		if (page == 0 && nums == 0) {
			itemsList = datadicService.selectItems(itemsMap);
		} else {
			PageHelper.startPage(page, nums, true);
			itemsList = datadicService.selectItems(itemsMap);
			PageInfo<DatadicItems> pageInfo = new PageInfo<DatadicItems>(itemsList);
			resultMap.put("total", pageInfo.getTotal());
		}

		resultMap.put("code", 0);
		resultMap.put("mes", "");
		resultMap.put("rows", itemsList);

		return resultMap;
	}

	@RequestMapping(value = "/addItems", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addItems(@RequestBody Datadic datadic) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<DatadicItems> datadicItemsList = datadic.getDatadicItems();
			if (datadic.getId() != null && !"".equals(datadic.getId())) {
				datadicService.deleteItems(datadic.getGroupCode());
				datadicService.deleteGroups(datadic.getGroupCode());
			}
			for (DatadicItems datadicItems : datadicItemsList) {
				datadicItems.setId(UUID.randomUUID().toString());
			}
			DatadicGroups datadicGroups = new DatadicGroups();
			datadicGroups.setGroupCode(datadic.getGroupCode());
			datadicGroups.setGroupName(datadic.getGroupName());
			datadicGroups.setId(UUID.randomUUID().toString());
			datadicService.addItems(datadicItemsList);
			datadicService.addGroups(datadicGroups);
			removeCache(datadic.getGroupCode(), "datadicCache");
			removeCache(datadic.getGroupCode(), "datadicMapCache");
			resultMap.put("mes", "success");
		} catch (Exception e) {
			throw new BusinessException(ExceptionCode.add_failure, e);
		}

		return resultMap;
	}

	@RequestMapping(value = "/deleteDatadic", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteDatadic(String groupCode) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (StringUtil.isNotEmpty(groupCode)) {
			datadicService.deleteItems(groupCode);
			datadicService.deleteGroups(groupCode);
		}
		removeCache(groupCode, "datadicCache");
		removeCache(groupCode, "datadicMapCache");
		resultMap.put("mes", "success");
		return resultMap;
	}

	@RequestMapping(value = "/initCombobox", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> initCombobox(String groupCode) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		CacheUtil cacheUtil = new CacheUtil();
		Datadic datadic = new Datadic();
		if (cacheUtil.getCache(groupCode, "datadicCache") == null) {
			datadic = datadicService.selectItemsByGroupCode(groupCode);
			this.setCache(datadic.getGroupCode(), datadic);
		} else {
			datadic = (Datadic) cacheUtil.getCache(groupCode, "datadicCache");
		}
		logger.info("获取下拉框信息成功！");
		resultMap.put("mes", "success");
		resultMap.put("rows", datadic);
		return resultMap;
	}

	@RequestMapping(value = "/getWorkDay", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getWorkDay() throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		datadicService.insertWorkDays();
		resultMap.put("mes", "success");
		return resultMap;
	}

	public void setCache(String key, Datadic datadic) {
		CacheUtil cacheUtil = new CacheUtil();
		Map<String, String> itemMap = new HashMap<String, String>();
		for (DatadicItems datadicItems : datadic.getDatadicItems()) {
			itemMap.put(datadicItems.getDataitemCode(), datadicItems.getDataitemName());
		}
		cacheUtil.setCache(key, datadic, "datadicCache");
		cacheUtil.setCache(key, itemMap, "datadicMapCache");

	}

	public void removeCache(String key, String cacheName) {
		CacheUtil cacheUtil = new CacheUtil();
		cacheUtil.remove(key, cacheName);
	}
}
