package com.cszx.pm.controller;

import com.cszx.pm.service.WarnService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/warnController")
public class WarnController {

    @Resource
    private WarnService warnService;


    @RequestMapping(value = "/totalWarn",method = RequestMethod.POST)
    @ResponseBody
    public int totalWarn(){

        ModelAndView modelAndView = new ModelAndView();

        int sum = warnService.totalWarn();
        modelAndView.addObject("sum",sum);
        return sum;
    }

//    @RequestMapping(value = "/getWarnMes",method = RequestMethod.POST)
//    @Resource
//    public Map<String,Object> getWarnMes(){
//
//    }
}
