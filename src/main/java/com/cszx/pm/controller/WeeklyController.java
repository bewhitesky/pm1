package com.cszx.pm.controller;

import com.cszx.common.exception.BusinessException;
import com.cszx.pm.dao.weekly.WeeklyDao;
import com.cszx.pm.model.weekly.Weekly;
import com.cszx.pm.model.weekly.WeeklyDto;
import com.cszx.pm.model.weekly.WeeklyVo;
import com.cszx.pm.service.WeeklyService;
import com.cszx.util.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

@Controller
@RequestMapping(value = "/WeeklyController",method = RequestMethod.POST)
public class WeeklyController {


    @Resource
    private WeeklyService weeklyService;


    @ResponseBody
    @RequestMapping(value = "/selectWeekly",method = RequestMethod.POST)
    public Map<String,Object> selectWeekly(WeeklyDto weeklyDto){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        Map<String,Object> map = new HashMap<String, Object>();

        int rows = weeklyDto.getRows();
        int page = weeklyDto.getPage();

        PageHelper.startPage(page,rows,true);

        List<Weekly> weeklyList = weeklyService.selectWeekly(map);
        PageInfo<Weekly> pageInfo = new PageInfo<Weekly>(weeklyList);

        resultMap.put("rows",weeklyList);
        resultMap.put("total",pageInfo.getTotal());

        return resultMap;
    }

    @ResponseBody
    @RequestMapping(value = "/addOrUpdateWeekly",method = RequestMethod.POST)
    public Map<String,Object> addOrUpdateWeekly(WeeklyVo weeklyVo){
        Map<String,Object> resultMap = new HashMap<String, Object>();

        if(!StringUtil.isNotEmpty(weeklyVo.getId())){
            weeklyVo.setId(UUID.randomUUID().toString());

            weeklyService.addWeekly(weeklyVo);
        }else {
            weeklyService.updateWeekly(weeklyVo);
        }

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
}
