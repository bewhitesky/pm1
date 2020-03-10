package com.cszx.pm.model.project;

public class ProgressVo {
	private String id;

	private String baseInfoId;

	private Integer currStateOne;

	private Integer currStateTwo;

	private String testState;

	private String internalCompleteTime;

	private String factoryCompleteTime;

	private String thirdCompleteTime;
	private String remark;

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

	public Integer getCurrStateTwo() {
		return currStateTwo;
	}

	public void setCurrStateTwo(Integer currStateTwo) {
		this.currStateTwo = currStateTwo;
	}

	public String getTestState() {
		return testState;
	}

	public void setTestState(String testState) {
		this.testState = testState == null ? null : testState.trim();
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}