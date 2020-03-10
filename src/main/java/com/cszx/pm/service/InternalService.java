package com.cszx.pm.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cszx.pm.model.internal.Defect;
import com.cszx.pm.model.internal.Internal;
import com.cszx.pm.model.internal.InternalDefect;
import com.cszx.pm.model.internal.InternalItem;
import com.cszx.pm.model.internal.InternalItemVo;
import com.cszx.pm.model.internal.InternalVo;
import com.cszx.pm.model.internal.Statistics;

public interface InternalService {

	List<Internal> selectInternalTest(Map<String, Object> internalMap);

	List<Defect> selectDefect(Map<String, Object> defectMap);

	List<InternalItem> selectInternalItems(String id);

	void addInternal(InternalVo internalVo);

	void updateInternal(InternalVo internalVo);

	void addItems(InternalItemVo internalItemVo);

	void addDefect(Defect defect);

	void updateItems(InternalItemVo internalItemVo);

	void deleteItems(List<String> idList);

	void deleteDefect(List<String> idList);

	void deleteInternal(List<String> idList);

	void deleteInternalByBaseInfoId(List<String> idList);

	void deleteItemsByInternalId(List<String> idList);

	Date getTestEndTime(Map<String, Object> map);

	void updateDefect(Defect defect);

	int countWorkday(String testStartTime, String testEndTime);

	XSSFWorkbook exportInternal(Map<String, Object> map);

	void addInternalDefect(InternalDefect internalDefect);

	void updateInternalDefect(InternalDefect internalDefect);

	InternalDefect selectInternalDefect(String id);

	void deleteInternalDefect(String id);

	List<Statistics> statistics(Map<String, Object> map);

	XSSFWorkbook exportStatistics(Map<String, Object> map);

}
