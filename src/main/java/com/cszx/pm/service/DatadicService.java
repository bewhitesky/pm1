package com.cszx.pm.service;

import java.util.List;
import java.util.Map;

import com.cszx.pm.model.datadic.Datadic;
import com.cszx.pm.model.datadic.DatadicGroups;
import com.cszx.pm.model.datadic.DatadicItems;

public interface DatadicService {

	List<DatadicGroups> selectGroups(Map<String, Object> groupsMap);

	List<DatadicItems> selectItems(Map<String, Object> itemsMap);

	void addItems(List<DatadicItems> datadicItemsList);

	void addGroups(DatadicGroups datadicGroups);

	Datadic selectItemsByGroupCode(String groupCode);

	void deleteItems(String groupCode);

	void deleteGroups(String groupCode);

	void insertWorkDays() throws Exception;

}
