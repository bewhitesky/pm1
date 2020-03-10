package com.cszx.pm.model.internal;

import java.util.Date;

public class InternalDto {
	private int page;
	private int nums;

	private String id;

	private String pName;

	private String baseInfoId;

	private String testItem;

	private Date receiveTime;

	private String testServer;

	private Integer internalTestState;

	private int testPrincipal;

	private String deploymentTime;

	private String testerName;

	private String testerTel;

	private String testerEmail;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
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

	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
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

	public int getTestPrincipal() {
		return testPrincipal;
	}

	public void setTestPrincipal(int testPrincipal) {
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
}