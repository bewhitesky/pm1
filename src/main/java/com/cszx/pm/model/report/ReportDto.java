package com.cszx.pm.model.report;

public class ReportDto {
	private int page;
	private int rows;

	private String pName;
	private Integer currStateOne;

	private String currStateTwo;

	private Integer riskLevel;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getPName() {
		return pName;
	}

	public void setPName(String pName) {
		this.pName = pName;
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

}