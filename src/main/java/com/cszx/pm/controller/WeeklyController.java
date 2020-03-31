package com.cszx.pm.controller;

import com.cszx.common.exception.BusinessException;
import com.cszx.common.exception.ExceptionCode;
import com.cszx.pm.dao.weekly.WeeklyDao;
import com.cszx.pm.model.weekly.*;
import com.cszx.pm.service.WeeklyService;
import com.cszx.util.DateUtil;
import com.cszx.util.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/WeeklyController",method = RequestMethod.POST)
public class WeeklyController {


    @Resource
    private WeeklyService weeklyService;


    @ResponseBody
    @RequestMapping(value = "/selectWeekly",method = RequestMethod.POST)
    public Map<String,Object> selectWeekly(WeeklyDto weeklyDto) throws ParseException {
        Map<String,Object> resultMap = new HashMap<String, Object>();
        Map<String,Object> map = new HashMap<String, Object>();

        int rows = weeklyDto.getRows();
        int page = weeklyDto.getPage();

        Date weeklyStartTime =null;
        Date weeklyEndTime = null;

        if(StringUtil.isNotEmpty(weeklyDto.getTime())){
            String Time = weeklyDto.getTime();
            String[] times = Time.split("~");
            weeklyStartTime = DateUtil.parseDateTime(times[0],null);
            weeklyEndTime = DateUtil.parseDateTime(times[1],null);
        }

        map.put("w_name",weeklyDto.getW_name());
        map.put("w_type",weeklyDto.getW_type());
        map.put("time",weeklyDto.getTime());
        map.put("weeklyStartTime",weeklyStartTime);
        map.put("weeklyEndTime",weeklyEndTime);

        PageHelper.startPage(page,rows,true);

        List<Weekly> weeklyList = weeklyService.selectWeekly(map);
        PageInfo<Weekly> pageInfo = new PageInfo<Weekly>(weeklyList);

        resultMap.put("rows",weeklyList);
        resultMap.put("total",pageInfo.getTotal());

        return resultMap;
    }


    @RequestMapping(value = "/addWeeklyItem",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addWeeklyItem(@RequestBody WeeklyItems weeklyItems){
        Map<String,Object> resultMap = new HashMap<String, Object>();

        try {

            List<WeeklyItemsDto> weeklyItemsDtos = weeklyItems.getWeeklyLists();

            for (WeeklyItemsDto weekly : weeklyItemsDtos) {
                weekly.setId(UUID.randomUUID().toString());
                weekly.setW_name(weeklyItems.getW_name());
            }
            weeklyService.addWeekly(weeklyItemsDtos);

        } catch (Exception e) {
            throw new BusinessException(ExceptionCode.add_failure,e);
        }
        resultMap.put("mes","success");
        return resultMap;
    }

    @RequestMapping(value = "/updateWeekly",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateWeekly(WeeklyVo weeklyVo){
        Map<String,Object> resultMap = new HashMap<String, Object>();

        weeklyService.updateWeekly(weeklyVo);

        resultMap.put("mes","success");
        return resultMap;
    }


    @ResponseBody
    @RequestMapping(value = "/deleteWeekly",method = RequestMethod.POST)
    public Map<String,Object> deleteWeekly(String ids){
        String[] strs = ids.split(",");
        List<String> idList = new ArrayList<String>();

        for (String str:strs){
            idList.add(str);
        }

        Map<String,Object> resultMap = new HashMap<String,Object>();

        try {
            weeklyService.deleteWeekly(idList);
        } catch (Exception e) {
            throw new BusinessException("删除失败");
        }
        resultMap.put("mes","success");
        return resultMap;
    }

    @ResponseBody
    @RequestMapping(value = "/exportWeekly",method = RequestMethod.GET)
    public Map<String,Object> exportWeekly(HttpServletResponse response){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        Map<String,Object> map = new HashMap<String,Object>();

        Date imptaskDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");

        String imptaskTime = dateFormat.format(imptaskDate);
        String fileName = "export" + imptaskTime +".xlsx";

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ";charset=UTF-8");
        XSSFWorkbook xssfWorkbook = weeklyService.exportWeekly(map);

        try {
            OutputStream output = response.getOutputStream();
            BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);
            xssfWorkbook.write(bufferedOutput);
            bufferedOutput.flush();
            bufferedOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultMap.put("mes","success");
        return resultMap;
    }


    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> importExcel(@RequestParam("file") CommonsMultipartFile file) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            InputStream in = file.getInputStream();
            weeklyService.importExcel(in,file);
            in.close();
            resultMap.put("mes", "success");
        } catch (Exception e) {
            throw new BusinessException(e);
        }
        return resultMap;
    }

}
