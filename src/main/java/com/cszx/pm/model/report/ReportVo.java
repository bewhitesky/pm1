package com.cszx.pm.model.report;

import com.cszx.common.validator.CustomRegular;

public class ReportVo {

	/**
	 * id
	 */
	private String id;

	/**
	 * 基础信息id
	 */
	private String baseInfoId;

	/**
	 * 项目名称
	 */
	private String pName;
	/**
	 * 当前阶段
	 */
	@CustomRegular(regType = "customValidator.number.nonnegative")
	private Integer currStateOne;

	/**
	 * 当前状态
	 */
	private String currStateTwo;

	/**
	 * 风险等级
	 */
	@CustomRegular(regType = "customValidator.number.nonnegative")
	private String riskLevel;

	/**
	 * 联系人
	 */
	@CustomRegular(regType = "customValidator.name")
	private String linkman;

	private String linkmanTel;

	private String linkmanEmail;

	private String riskPoint;

	private String remark;

	private String applyFunTime;

	private String applySecTime;

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

	public Integer getCurrStateOne() {
		return currStateOne;
	}

	public void setCurrStateOne(Integer currStateOne) {
		this.currStateOne = currStateOne;
	}

	public String getCurrStateTwo() {
		return currStateTwo;
	}

	public void setCurrStateTwo(String currStateTwo) {
		this.currStateTwo = currStateTwo == null ? null : currStateTwo.trim();
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

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getApplyFunTime() {
		return applyFunTime;
	}

	public void setApplyFunTime(String applyFunTime) {
		this.applyFunTime = applyFunTime;
	}

	public String getApplySecTime() {
		return applySecTime;
	}

	public void setApplySecTime(String applySecTime) {
		this.applySecTime = applySecTime;
	}

}