package com.cszx.pm.controller;



import com.cszx.common.exception.BusinessException;
import com.cszx.pm.model.imptask.Imptask;
import com.cszx.pm.model.imptask.ImptaskDto;
import com.cszx.pm.model.imptask.ImptaskVo;
import com.cszx.pm.service.ImptaskService;
import com.cszx.util.StringUtil;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/ImpTaskController")
public class ImpTaskController {

    private final Logger logger = LoggerFactory.getLogger(BaseInfoController.class);
    @Resource
    private ImptaskService imptaskService;



    @RequestMapping(value = "/selectImpTask",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> selectTask(ImptaskDto imptaskDto){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        Map<String,Object> ImptaskMap = new HashMap<String, Object>();
        int rows = imptaskDto.getRows();
        int page = imptaskDto.getPage();

        ImptaskMap.put("imptask_name",imptaskDto.getImptask_name());
        ImptaskMap.put("imptask_taskcls",imptaskDto.getImptask_taskcls());
        ImptaskMap.put("p_complete_time",imptaskDto.getP_complete_time());

        PageHelper.startPage(page,rows,true);

        List<Imptask> imptasks = imptaskService.selectImptask(ImptaskMap);
        PageInfo<Imptask> pageInfo = new PageInfo<Imptask>(imptasks);
        resultMap.put("rows", imptasks);
        resultMap.put("total",pageInfo.getTotal());
        return resultMap;
    }


    @RequestMapping(value = "/addOrUpdateImptask",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addOrUpdateImptask(ImptaskVo imptaskVo){
        Map<String,Object> resultMap = new HashMap<String, Object>();

        if(!StringUtil.isNotEmpty(imptaskVo.getId())){
            imptaskVo.setId(UUID.randomUUID().toString());
            imptaskService.addImptask(imptaskVo);
        }else {
            imptaskService.updateImptask(imptaskVo);
        }
        resultMap.put("mes","success");
        return resultMap;
    }

    @RequestMapping(value = "/deleteImptask",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deleteImptask(String ids){
        String[] strs = ids.split(",");
        List<String> idList = new ArrayList<String>();
        for (String str : strs){
            idList.add(str);
        }

        Map<String,Object> resultMap = new HashMap<String,Object>();

        try {
            imptaskService.deleteImptask(idList);
        } catch (Exception e) {
            throw new BusinessException("删除失败");
        }

        resultMap.put("mes","success");
        return resultMap;
    }

    @ResponseBody
    @RequestMapping(value = "/exportImptask",method = RequestMethod.GET)
    public Map<String,Object> exportImptask(HttpServletResponse response){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        Map<String,Object> map = new HashMap<String,Object>();

        Date imptaskDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");

        String imptaskTime = dateFormat.format(imptaskDate);
        String fileName = "export" + imptaskTime +".xlsx";

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ";charset=UTF-8");
        XSSFWorkbook xssfWorkbook = imptaskService.exportImptask(map);

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

    /**
     * 导入excel
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> importExcel(@RequestParam("file") CommonsMultipartFile file) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            InputStream in = file.getInputStream();
            imptaskService.importExcel(in,file);
            in.close();
            resultMap.put("mes", "success");
            logger.info("导入成功");
        } catch (Exception e) {
            logger.info("导入失败");
            throw new BusinessException(e);
        }
        return resultMap;
    }
}
