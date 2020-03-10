package com.cszx.pm.model.project;

public class ProjectDto {
	private int page;
	private int nums;
	private String pDepartment;
	private String dorg;
	private String pName;

	private String testYear;
	private String testState;
	private String currStateOne;

	private String currStateTwo;

	private String internalCompleteTime;

	private String factoryCompleteTime;

	private String thirdCompleteTime;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getNums() {
		return nums;
	}

	public void setNums(int nums) {
		this.nums = nums;
	}

	public String getPDepartment() {
		return pDepartment;
	}

	public void setPDepartment(String pDepartment) {
		this.pDepartment = pDepartment;
	}

	public String getDorg() {
		return dorg;
	}

	public void setDorg(String dorg) {
		this.dorg = dorg;
	}

	public String getPName() {
		return pName;
	}

	public void setPName(String pName) {
		this.pName = pName;
	}

	public String getTestYear() {
		return testYear;
	}

	public void setTestYear(String testYear) {
		this.testYear = testYear;
	}

	public String getTestState() {
		return testState;
	}

	public void setTestState(String testState) {
		this.testState = testState;
	}

	public String getCurrStateOne() {
		return currStateOne;
	}

	public void setCurrStateOne(String currStateOne) {
		this.currStateOne = "".equals(currStateOne) ? null : currStateOne;
	}

	public String getCurrStateTwo() {
		return currStateTwo;
	}

	public void setCurrStateTwo(String currStateTwo) {
		this.currStateTwo = "".equals(currStateTwo) ? null : currStateTwo;
	}

	public String getInternalCompleteTime() {
		return internalCompleteTime;
	}

	public void setInternalCompleteTime(String internalCompleteTime) {
		this.internalCompleteTime = internalCompleteTime;
	}

	public String getFactoryCompleteTime() {
		return factoryCompleteTime;
	}

	public void setFactoryCompleteTime(String factoryCompleteTime) {
		this.factoryCompleteTime = factoryCompleteTime;
	}

	public String getThirdCompleteTime() {
		return thirdCompleteTime;
	}

	public void setThirdCompleteTime(String thirdCompleteTime) {
		this.thirdCompleteTime = thirdCompleteTime;
	}

}