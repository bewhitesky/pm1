package com.cszx.pm.service.impl;

import com.cszx.pm.dao.warn.WarnDao;
import com.cszx.pm.model.warn.Warn;
import com.cszx.pm.service.WarnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("warnService")
public class WarnServiceImpl implements WarnService {

    @Autowired
    private WarnDao warnDao;

    @Override
    public List<Warn> checkWarn(){
        return warnDao.checkWarn();
    }

    @Override
    public int totalWarn(){
        return warnDao.totalWarn();
    }
}
