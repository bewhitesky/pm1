package com.cszx.pm.model.internal;

public class InternalVo {
	private String id;

	private String baseInfoId;

	private String testItem;

	private String receiveTime;

	private String testServer;

	private Integer internalTestState;

	private Integer testPrincipal;

	private String deploymentTime;

	private String testerName;

	private String testerTel;

	private String testerEmail;

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

	public String getTestItem() {
		return testItem;
	}

	public void setTestItem(String testItem) {
		this.testItem = testItem;
	}

	public String getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getTestServer() {
		return testServer;
	}

	public void setTestServer(String testServer) {
		this.testServer = testServer == null ? null : testServer.trim();
	}

	public Integer getInternalTestState() {
		return internalTestState;
	}

	public void setInternalTestState(Integer internalTestState) {
		this.internalTestState = internalTestState;
	}

	public Integer getTestPrincipal() {
		return testPrincipal;
	}

	public void setTestPrincipal(Integer testPrincipal) {
		this.testPrincipal = testPrincipal;
	}

	public String getDeploymentTime() {
		return deploymentTime;
	}

	public void setDeploymentTime(String deploymentTime) {
		this.deploymentTime = deploymentTime;
	}

	public String getTesterName() {
		return testerName;
	}

	public void setTesterName(String testerName) {
		this.testerName = testerName == null ? null : testerName.trim();
	}

	public String getTesterTel() {
		return testerTel;
	}

	public void setTesterTel(String testerTel) {
		this.testerTel = testerTel == null ? null : testerTel.trim();
	}

	public String getTesterEmail() {
		return testerEmail;
	}

	public void setTesterEmail(String testerEmail) {
		this.testerEmail = testerEmail == null ? null : testerEmail.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}