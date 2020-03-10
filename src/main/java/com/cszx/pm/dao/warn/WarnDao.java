package com.cszx.pm.dao.warn;

import com.cszx.pm.model.warn.Warn;

import java.util.List;
import java.util.Map;

public interface WarnDao {
    List<Warn> checkWarn();

    int totalWarn();
}
