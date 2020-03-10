package com.cszx.pm.model.report;

import java.util.Date;

public class ReportResult {
	private String id;

	private String baseInfoId;

	private Integer pDepartment;

	private String pName;

	private Integer pType;

	private Integer currStateReport;

	private String currState;

	private String riskLevel;

	private String linkman;

	private String linkmanTel;

	private String linkmanEmail;

	private String riskPoint;

	private String remark;

	private Date createTime;

	private Date applyFunTime;

	private Date applySecTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getBaseInfoId() {
		return baseInfoId;
	}

	public void setBaseInfoId(String baseInfoId) {
		this.baseInfoId = baseInfoId == null ? null : baseInfoId.trim();
	}

	public Integer getPDepartment() {
		return pDepartment;
	}

	public void setPDepartment(Integer pDepartment) {
		this.pDepartment = pDepartment;
	}

	public String getPName() {
		return pName;
	}

	public void setPName(String pName) {
		this.pName = pName;
	}

	public Integer getPType() {
		return pType;
	}

	public void setPType(Integer pType) {
		this.pType = pType;
	}

	public Integer getCurrStateReport() {
		return currStateReport;
	}

	public void setCurrStateReport(Integer currStateReport) {
		this.currStateReport = currStateReport;
	}

	public String getCurrState() {
		return currState;
	}

	public void setCurrState(String currState) {
		this.currState = currState;
	}

	public String getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman == null ? null : linkman.trim();
	}

	public String getLinkmanTel() {
		return linkmanTel;
	}

	public void setLinkmanTel(String linkmanTel) {
		this.linkmanTel = linkmanTel == null ? null : linkmanTel.trim();
	}

	public String getLinkmanEmail() {
		return linkmanEmail;
	}

	public void setLinkmanEmail(String linkmanEmail) {
		this.linkmanEmail = linkmanEmail == null ? null : linkmanEmail.trim();
	}

	public String getRiskPoint() {
		return riskPoint;
	}

	public void setRiskPoint(String riskPoint) {
		this.riskPoint = riskPoint;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getApplyFunTime() {
		return applyFunTime;
	}

	public void setApplyFunTime(Date applyFunTime) {
		this.applyFunTime = applyFunTime;
	}

	public Date getApplySecTime() {
		return applySecTime;
	}

	public void setApplySecTime(Date applySecTime) {
		this.applySecTime = applySecTime;
	}

}