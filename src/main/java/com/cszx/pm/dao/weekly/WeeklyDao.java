package com.cszx.pm.dao.weekly;

import com.cszx.pm.model.weekly.Weekly;
import com.cszx.pm.model.weekly.WeeklyItemsDto;
import com.cszx.pm.model.weekly.WeeklyVo;

import java.util.List;
import java.util.Map;

public interface WeeklyDao {
    List<Weekly> selectWeekly(Map<String,Object> weeklyMap);

    void addWeekly(List<WeeklyItemsDto> weeklyItemsDtos);

    void updateWeekly(WeeklyVo weeklyVo);

    void deleteWeekly(List<String> idList);
}
