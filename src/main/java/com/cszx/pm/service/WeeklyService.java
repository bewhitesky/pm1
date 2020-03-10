package com.cszx.pm.service;

import com.cszx.pm.model.weekly.Weekly;
import com.cszx.pm.model.weekly.WeeklyVo;

import java.util.List;
import java.util.Map;

public interface WeeklyService {
    List<Weekly> selectWeekly(Map<String,Object> weeklyMap);

    void addWeekly(WeeklyVo weeklyVo);

    void updateWeekly(WeeklyVo weeklyVo);

    void deleteWeekly(List<String> idList);
}
