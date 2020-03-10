package com.cszx.pm.service.impl;

import com.cszx.common.excel.ExcelBean;
import com.cszx.common.excel.ExcelUtil;
import com.cszx.pm.dao.datadic.DatadicItemsDao;
import com.cszx.pm.dao.imptask.ImptaskDao;
import com.cszx.pm.model.imptask.Imptask;
import com.cszx.pm.model.imptask.ImptaskDto;
import com.cszx.pm.model.imptask.ImptaskVo;
import com.cszx.pm.service.DatadicService;
import com.cszx.pm.service.ImptaskService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.beans.IntrospectionException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.*;

@Service("ImptaskService")
public class ImptaskServiceImpl implements ImptaskService {

    @Resource
    private DatadicItemsDao datadicItemsDao;

    @Resource
    private ImptaskDao imptaskDao;

    public List<Imptask> selectImptask(Map<String,Object> ImptaskMap){
        return imptaskDao.selectImptask(ImptaskMap);
    }

    public void addImptask(ImptaskVo imptaskVo){
        imptaskDao.addImptask(imptaskVo);
    }

    public void updateImptask(ImptaskVo imptaskVo){
        imptaskDao.updateImptask(imptaskVo);
    }

    public void deleteImptask(List<String> ids){
        imptaskDao.deleteImptask(ids);
    }

    public XSSFWorkbook exportImptask(Map<String,Object> imptaskMap) {
        XSSFWorkbook xssfWorkbook = null;
        try {
            List<Imptask> imptaskList = imptaskDao.selectImptask(imptaskMap);

            List<ExcelBean> excel = new ArrayList<>();

            Map<Integer, List<ExcelBean>> map = new HashMap<>();

            excel.add(new ExcelBean("序号", "id", 0, false));
            excel.add(new ExcelBean("任务名称", "imptask_name", 0, false));
            excel.add(new ExcelBean("交付成果", "achievements", 0, false));
            excel.add(new ExcelBean("任务分类", "imptask_taskcls", 0, false));
            excel.add(new ExcelBean("责任人", "p_charge", 0, false));
            excel.add(new ExcelBean("计划完成时间", "p_complete_time", 0, false));

            excel.add(new ExcelBean("阶段计划", "plan", 0, false));
            excel.add(new ExcelBean("任务状态", "imptask_state", 0, false));

            excel.add(new ExcelBean("完成百分比", "percent", 0, false));
            excel.add(new ExcelBean("进度说明", "explan", 0, false));
            excel.add(new ExcelBean("备注", "remarks", 0, false));

            map.put(0, excel);
            String sheetName = "测试中心重点工作任务跟踪表";
            xssfWorkbook = ExcelUtil.createExcelFile(Imptask.class, imptaskList, map, sheetName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xssfWorkbook;
    }


    public void importExcel(InputStream in, CommonsMultipartFile file) throws Exception{
        List<List<Object>> listob = ExcelUtil.getBankListByExcel(in,file.getOriginalFilename());
        List<ImptaskVo> imptaskVoList = new ArrayList<ImptaskVo>();

        for (int i=0; i<listob.size();i++){
            List<Object> ob = listob.get(i);
            ImptaskVo imptaskVo = new ImptaskVo();
            Map<String,String> dataMap = new HashMap<String,String>();
            dataMap.put("imptask_taskcls","imptask_taskcls");
            dataMap.put("p_charge","test_principal");
            dataMap.put("imptask_state","imptask_state");

            imptaskVo.setId(UUID.randomUUID().toString());
            imptaskVo.setImptask_name(String.valueOf(ob.get(1)));
            imptaskVo.setAchievements(String.valueOf(ob.get(2)));
            imptaskVo.setImptask_taskcls(getInt(dataMap,"imptask_taskcls",String.valueOf(ob.get(3))));
            imptaskVo.setP_charge(getString(dataMap,"p_charge",String.valueOf(ob.get(4))));
            imptaskVo.setP_complete_time(String.valueOf(ob.get(5)));
            imptaskVo.setPlan(String.valueOf(ob.get(6)));
            imptaskVo.setImptask_state(getInt(dataMap,"imptask_state",String.valueOf(ob.get(7))));
            imptaskVo.setPercent(String.valueOf(ob.get(8)));
            imptaskVo.setExplan(String.valueOf(ob.get(9)));
            imptaskVo.setRemarks(String.valueOf(ob.get(10)));

            imptaskVoList.add(imptaskVo);
        }

        for (ImptaskVo imptaskVo:imptaskVoList){
            this.addImptask(imptaskVo);
        }
    }

    public Integer getInt(Map<String,String>dataMap,String key,String itemsName)throws Exception{
        String groupCode = dataMap.get(key);
        Map<String,String> map = new HashMap<String,String>();
        map.put("groupCode",groupCode);
        map.put("dataitemName",itemsName);

        int num = Integer.parseInt(datadicItemsDao.getItemCode(map));
        return num;
    }

    public String getString(Map<String,String>dataMap,String key,String itemsName)throws Exception{
        String groupCode = dataMap.get(key);
        Map<String,String> map = new HashMap<String,String>();
        map.put("groupCode",groupCode);
        map.put("dataitemName",itemsName);

        String num = datadicItemsDao.getItemCode(map);
        return num;
    }

}
