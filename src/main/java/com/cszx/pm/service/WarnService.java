package com.cszx.pm.service;

import com.cszx.pm.model.warn.Warn;

import java.util.List;

public interface WarnService {
    List<Warn> checkWarn();

    int totalWarn();
}
