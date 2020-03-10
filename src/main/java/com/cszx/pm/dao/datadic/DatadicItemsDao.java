package com.cszx.pm.dao.datadic;

import java.util.List;
import java.util.Map;

import com.cszx.pm.model.datadic.Datadic;
import com.cszx.pm.model.datadic.DatadicItems;

public interface DatadicItemsDao {
	int deleteByPrimaryKey(String id);

	int insert(DatadicItems record);

	int insertSelective(DatadicItems record);

	DatadicItems selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(DatadicItems record);

	int updateByPrimaryKey(DatadicItems record);

	List<DatadicItems> selectItems(Map<String, Object> itemsMap);

	void addItems(List<DatadicItems> datadicItemsList);

	Datadic selectDatadicByGroupCode(String groupCode);

	String getItemCode(Map<String, String> map);

	void deleteItems(String groupCode);
}