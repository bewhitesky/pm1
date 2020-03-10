package com.cszx.pm.model.project;

public class BaseInfoDto {
	private int page;
	private int rows;

	private String id;

	private String pDepartment;

	private String dorg;

	private String pName;

	private String productName;

	private String pType;

	private String testType;

	private String pLevel;

	private String internalTime;

	private String factoryTime;

	private String thirdTime;

	private String testYear;

	private String remark;

	private String testState;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPType() {
		return pType;
	}

	public void setPType(String pType) {
		this.pType = pType;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	public String getPLevel() {
		return pLevel;
	}

	public void setPLevel(String pLevel) {
		this.pLevel = pLevel;
	}

	public String getInternalTime() {
		return internalTime;
	}

	public void setInternalTime(String internalTime) {
		this.internalTime = internalTime;
	}

	public String getFactoryTime() {
		return factoryTime;
	}

	public void setFactoryTime(String factoryTime) {
		this.factoryTime = factoryTime;
	}

	public String getThirdTime() {
		return thirdTime;
	}

	public void setThirdTime(String thirdTime) {
		this.thirdTime = thirdTime;
	}

	public String getTestYear() {
		return testYear;
	}

	public void setTestYear(String testYear) {
		this.testYear = testYear;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTestState() {
		return testState;
	}

	public void setTestState(String testState) {
		this.testState = testState;
	}

}