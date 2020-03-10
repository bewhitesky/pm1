package com.cszx.pm.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cszx.pm.model.project.Complete;
import com.cszx.pm.model.project.CompleteVo;
import com.cszx.pm.model.project.ProgressVo;
import com.cszx.pm.model.project.Project;

public interface ProjectService {

	List<Project> selectProgress(Map<String, Object> progressMap);

	void updateProgress(ProgressVo progressVo);

	void updateComplete(CompleteVo completeVo);

	XSSFWorkbook exportExcel(Map<String, Object> projectMap);

	Complete average();

}
