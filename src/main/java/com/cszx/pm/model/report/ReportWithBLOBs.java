package com.cszx.pm.model.report;

public class ReportWithBLOBs extends Report {
    private String riskPoint;

    private String remark;

    public String getRiskPoint() {
        return riskPoint;
    }

    public void setRiskPoint(String riskPoint) {
        this.riskPoint = riskPoint == null ? null : riskPoint.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}