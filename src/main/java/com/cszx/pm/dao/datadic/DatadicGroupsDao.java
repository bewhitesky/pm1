package com.cszx.pm.dao.datadic;

import java.util.List;
import java.util.Map;

import com.cszx.pm.model.datadic.DatadicGroups;

public interface DatadicGroupsDao {
	int deleteByPrimaryKey(String id);

	int insert(DatadicGroups record);

	int insertSelective(DatadicGroups record);

	DatadicGroups selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(DatadicGroups record);

	int updateByPrimaryKey(DatadicGroups record);

	List<DatadicGroups> selectGroups(Map<String, Object> groupsMap);

	void deleteGroups(String groupCode);

}