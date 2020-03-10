package com.cszx.pm.model.report;

public class Report {
	private String id;

	private String baseInfoId;

	private Integer currStateOne;

	private String currStateTwo;

	private Integer riskLevel;

	private String linkman;

	private String linkmanTel;

	private String linkmanEmail;

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

	public Integer getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(Integer riskLevel) {
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
}