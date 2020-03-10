package com.cszx.pm.dao.Internal;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cszx.pm.model.internal.InternalItem;
import com.cszx.pm.model.internal.InternalItemVo;

public interface InternalItemDao {
	int selectInternalItems(InternalItem record);

	int addInternalItems(InternalItemVo internalItemVo);

	void updateInternalItems(InternalItemVo internalItemVo);

	List<InternalItem> selectInternalItems(String id);

	void deleteItems(List<String> idList);

	Date getTestEndTime(Map<String, Object> map);
}