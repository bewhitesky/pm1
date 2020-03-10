package com.cszx.pm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cszx.pm.dao.caselibrary.CaseDao;
import com.cszx.pm.model.caselibrary.Case;
import com.cszx.pm.service.CaseService;

@Service("caseService")
public class CaseServiceImpl implements CaseService {
	@Resource
	private CaseDao caseDao;

	@Override
	public List<Case> selectCase(Case case1) {
		return caseDao.selectCase(case1);
	}

}
