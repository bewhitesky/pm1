package com.cszx.pm.dao.Internal;

import java.util.List;
import java.util.Map;

import com.cszx.pm.model.internal.Defect;
import com.cszx.pm.model.internal.Internal;
import com.cszx.pm.model.internal.InternalExport;
import com.cszx.pm.model.internal.InternalVo;
import com.cszx.pm.model.internal.Statistics;

public interface InternalDao {
	int deleteByPrimaryKey(String id);

	int addInternal(InternalVo internalVo);

	int updateInternal(InternalVo internalVo);

	void deleteInternal(List<String> idList);

	void deleteInternalByBaseInfoId(List<String> idList);

	void deleteItemsByInternalId(List<String> idList);

	Internal selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Internal record);

	int updateByPrimaryKey(Internal record);

	List<Internal> selectInternalTest(Map<String, Object> internalMap);

	List<Defect> selectDefect(Map<String, Object> defectMap);

	void addDefect(Defect defect);

	void deleteDefect(List<String> idList);

	void updateDefect(Defect defect);

	int countWorkday(String testStartTime, String testEndTime);

	List<String> selectInternalTestIdByBaseInfoId(List<String> idList);

	List<InternalExport> exportInternal(Map<String, Object> map);

	List<Statistics> statistics(Map<String, Object> map);

}