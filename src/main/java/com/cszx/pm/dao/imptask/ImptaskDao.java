package com.cszx.pm.dao.imptask;

import com.cszx.pm.model.imptask.Imptask;
import com.cszx.pm.model.imptask.ImptaskDto;
import com.cszx.pm.model.imptask.ImptaskResult;
import com.cszx.pm.model.imptask.ImptaskVo;

import java.util.List;
import java.util.Map;

public interface ImptaskDao {
    List<Imptask> selectImptask(Map<String, Object> taskMap);

    void addImptask(ImptaskVo imptaskVo);

    void updateImptask(ImptaskVo imptaskVo);

    void deleteImptask(List<String> idList);
}
