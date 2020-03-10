package com.cszx.pm.dao.Internal;

import java.util.List;

import com.cszx.pm.model.internal.InternalDefect;

public interface InternalDefectDao {
	int deleteByPrimaryKey(String id);

	int insert(InternalDefect record);

	int insertSelective(InternalDefect record);

	InternalDefect selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(InternalDefect record);

	int updateByPrimaryKey(InternalDefect record);

	Double selectByTestId(String id);

	void deleteDefectByInternalId(List<String> internalIdList);
}