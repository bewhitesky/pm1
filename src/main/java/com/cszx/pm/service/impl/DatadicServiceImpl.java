package com.cszx.pm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cszx.pm.dao.datadic.DatadicGroupsDao;
import com.cszx.pm.dao.datadic.DatadicItemsDao;
import com.cszx.pm.dao.datadic.WorkdayDao;
import com.cszx.pm.model.datadic.Datadic;
import com.cszx.pm.model.datadic.DatadicGroups;
import com.cszx.pm.model.datadic.DatadicItems;
import com.cszx.pm.service.DatadicService;
import com.cszx.util.DateApi;

@Service("datadicService")
public class DatadicServiceImpl implements DatadicService {
	@Resource
	DatadicGroupsDao datadicGroupsDao;
	@Resource
	DatadicItemsDao datadicItemsDao;
	@Resource
	WorkdayDao workdayDao;

	@Override
	public List<DatadicGroups> selectGroups(Map<String, Object> groupsMap) {
		return datadicGroupsDao.selectGroups(groupsMap);
	}

	@Override
	public List<DatadicItems> selectItems(Map<String, Object> itemsMap) {
		return datadicItemsDao.selectItems(itemsMap);
	}

	@Override
	public void addItems(List<DatadicItems> datadicItemsList) {
		datadicItemsDao.addItems(datadicItemsList);

	}

	@Override
	public void addGroups(DatadicGroups datadicGroups) {
		datadicGroupsDao.insert(datadicGroups);
	}

	@Override
	public Datadic selectItemsByGroupCode(String groupCode) {
		return datadicItemsDao.selectDatadicByGroupCode(groupCode);
	}

	@Override
	public void deleteItems(String groupCode) {
		datadicItemsDao.deleteItems(groupCode);

	}

	@Override
	public void deleteGroups(String groupCode) {
		datadicGroupsDao.deleteGroups(groupCode);

	}

	@Override
	public void insertWorkDays() throws Exception {
		List<String> list = DateApi.getWorkDays();
		workdayDao.insert(list);
	}

}
