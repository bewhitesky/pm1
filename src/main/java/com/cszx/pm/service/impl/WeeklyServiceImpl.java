package com.cszx.pm.service.impl;

import com.cszx.common.excel.ExcelBean;
import com.cszx.common.excel.ExcelUtil;
import com.cszx.pm.dao.datadic.DatadicItemsDao;
import com.cszx.pm.dao.weekly.WeeklyDao;
import com.cszx.pm.model.imptask.Imptask;
import com.cszx.pm.model.weekly.Weekly;
import com.cszx.pm.model.weekly.WeeklyItemsDto;
import com.cszx.pm.model.weekly.WeeklyVo;
import com.cszx.pm.service.WeeklyService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.*;

@Service("WeeklyService")
public class WeeklyServiceImpl implements WeeklyService {

    @Resource
    private WeeklyDao weeklyDao;

    @Resource
    private DatadicItemsDao datadicItemsDao;

    public List<Weekly> selectWeekly(Map<String,Object> weeklyMap){
        return weeklyDao.selectWeekly(weeklyMap);
    }
    public void addWeekly(List<WeeklyItemsDto> weeklyItemsDtos){
        weeklyDao.addWeekly(weeklyItemsDtos);
    }
    public void updateWeekly(WeeklyVo weeklyVo){
        weeklyDao.updateWeekly(weeklyVo);
    }

    public void deleteWeekly(List<String> idList){
        weeklyDao.deleteWeekly(idList);
    }
    public XSSFWorkbook exportWeekly(Map<String,Object> WeeklyMap) {
        XSSFWorkbook xssfWorkbook = null;
        try {
            List<Weekly> weeklyList = weeklyDao.selectWeekly(WeeklyMap);

            List<ExcelBean> excel = new ArrayList<>();

            Map<Integer, List<ExcelBean>> map = new HashMap<>();

            excel.add(new ExcelBean("序号", "id", 0, false));
            excel.add(new ExcelBean("姓名", "w_name", 0, false));
            excel.add(new ExcelBean("日期", "time", 0, false));
            excel.add(new ExcelBean("工作简述", "last_work_sketch", 0, false));
            excel.add(new ExcelBean("类别", "w_type", 0, false));
            excel.add(new ExcelBean("耗时", "u_time", 0, false));

            excel.add(new ExcelBean("本周工作计划", "this_work_sketch", 0, false));
            excel.add(new ExcelBean("备注", "remarks", 0, false));

            map.put(0, excel);
            String sheetName = "周报";
            xssfWorkbook = ExcelUtil.createExcelFile(Weekly.class, weeklyList, map, sheetName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xssfWorkbook;
    }



    public void importExcel(InputStream in, CommonsMultipartFile file) throws Exception{
        List<List<Object>> listob = ExcelUtil.getBankListByExcel(in,file.getOriginalFilename());
        List<WeeklyItemsDto> weeklyVoList = new ArrayList<WeeklyItemsDto>();

        for (int i=0; i<listob.size();i++){
            List<Object> ob = listob.get(i);
            WeeklyItemsDto weeklyItemsDto = new WeeklyItemsDto();
            Map<String,String> dataMap = new HashMap<String,String>();

            dataMap.put("w_type","w_type");

            weeklyItemsDto.setId(UUID.randomUUID().toString());
            weeklyItemsDto.setW_name(String.valueOf(ob.get(1)));
            weeklyItemsDto.setTime(String.valueOf(ob.get(2)));

            weeklyItemsDto.setLast_work_sketch(String.valueOf(ob.get(3)));
            weeklyItemsDto.setW_type(getInt(dataMap,"w_type",String.valueOf(ob.get(4))));
            weeklyItemsDto.setU_time(String.valueOf(ob.get(5)));
            weeklyItemsDto.setThis_work_sketch(String.valueOf(ob.get(6)));
            weeklyItemsDto.setRemarks(String.valueOf(ob.get(7)));

            weeklyVoList.add(weeklyItemsDto);
        }
            this.addWeekly(weeklyVoList);
    }

    public Integer getInt(Map<String,String>dataMap,String key,String itemsName)throws Exception{
        String groupCode = dataMap.get(key);
        Map<String,String> map = new HashMap<String,String>();
        map.put("groupCode",groupCode);
        map.put("dataitemName",itemsName);

        int num = Integer.parseInt(datadicItemsDao.getItemCode(map));
        return num;
    }


}
