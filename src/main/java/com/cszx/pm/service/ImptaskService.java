package com.cszx.pm.service;

import com.cszx.pm.model.imptask.Imptask;
import com.cszx.pm.model.imptask.ImptaskDto;
import com.cszx.pm.model.imptask.ImptaskVo;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface ImptaskService {
    List<Imptask> selectImptask(Map<String,Object> ImptaskMap);

    void addImptask(ImptaskVo imptaskVo);

    void updateImptask(ImptaskVo imptaskVo);

    void deleteImptask(List<String> idList);

    XSSFWorkbook exportImptask(Map<String,Object> map);

    void importExcel(InputStream in, CommonsMultipartFile file) throws Exception;

}
