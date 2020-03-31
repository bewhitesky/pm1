package com.cszx.pm.service;

import com.cszx.pm.model.weekly.Weekly;
import com.cszx.pm.model.weekly.WeeklyDto;
import com.cszx.pm.model.weekly.WeeklyItemsDto;
import com.cszx.pm.model.weekly.WeeklyVo;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface WeeklyService {
    List<Weekly> selectWeekly(Map<String,Object> weeklyMap);

    void addWeekly(List<WeeklyItemsDto> weeklyItemsDtos);

    void updateWeekly(WeeklyVo weeklyVo);

    void deleteWeekly(List<String> idList);

    XSSFWorkbook exportWeekly(Map<String,Object> map);

    void importExcel(InputStream in, CommonsMultipartFile file) throws Exception;

}
