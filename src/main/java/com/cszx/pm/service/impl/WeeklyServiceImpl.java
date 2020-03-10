package com.cszx.pm.service.impl;

import com.cszx.pm.dao.weekly.WeeklyDao;
import com.cszx.pm.model.weekly.Weekly;
import com.cszx.pm.model.weekly.WeeklyVo;
import com.cszx.pm.service.WeeklyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("WeeklyService")
public class WeeklyServiceImpl implements WeeklyService {

    @Resource
    private WeeklyDao weeklyDao;

    public List<Weekly> selectWeekly(Map<String,Object> weeklyMap){
        return weeklyDao.selectWeekly(weeklyMap);
    }
    public void addWeekly(WeeklyVo weeklyVo){
        weeklyDao.addWeekly(weeklyVo);
    }
    public void updateWeekly(WeeklyVo weeklyVo){
        weeklyDao.updateWeekly(weeklyVo);
    }

    public void deleteWeekly(List<String> idList){
        weeklyDao.deleteWeekly(idList);
    }


}
