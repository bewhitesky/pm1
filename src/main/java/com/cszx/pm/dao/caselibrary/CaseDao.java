package com.cszx.pm.dao.caselibrary;

import java.util.List;

import com.cszx.pm.model.caselibrary.Case;

public interface CaseDao {
	int deleteByPrimaryKey(String id);

	int insert(Case record);

	int insertSelective(Case record);

	Case selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Case record);

	int updateByPrimaryKey(Case record);

	List<Case> selectCase(Case case1);
}