package com.cszx.pm.model.internal;

public class InternalItemVo {
	private String id;

	private String internalTestId;

	private Integer internalTestTimes;

	private String deployStartTime;

	private String deployEndTime;

	private String testStartTime;

	private String testEndTime;

	private String correctTime;

	private String testTime;

	private Double runabled;

	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getInternalTestId() {
		return internalTestId;
	}

	public void setInternalTestId(String internalTestId) {
		this.internalTestId = internalTestId == null ? null : internalTestId.trim();
	}

	public Integer getInternalTestTimes() {
		return internalTestTimes;
	}

	public void setInternalTestTimes(Integer internalTestTimes) {
		this.internalTestTimes = internalTestTimes;
	}

	public String getDeployStartTime() {
		return deployStartTime;
	}

	public void setDeployStartTime(String deployStartTime) {
		this.deployStartTime = deployStartTime;
	}

	public String getDeployEndTime() {
		return deployEndTime;
	}

	public void setDeployEndTime(String deployEndTime) {
		this.deployEndTime = deployEndTime;
	}

	public String getTestStartTime() {
		return testStartTime;
	}

	public void setTestStartTime(String testStartTime) {
		this.testStartTime = testStartTime;
	}

	public String getTestEndTime() {
		return testEndTime;
	}

	public void setTestEndTime(String testEndTime) {
		this.testEndTime = testEndTime;
	}

	public String getCorrectTime() {
		return correctTime;
	}

	public void setCorrectTime(String correctTime) {
		this.correctTime = correctTime;
	}

	public String getTestTime() {
		return testTime;
	}

	public void setTestTime(String testTime) {
		this.testTime = testTime;
	}

	public Double getRunabled() {
		return runabled;
	}

	public void setRunabled(Double runabled) {
		this.runabled = runabled;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}