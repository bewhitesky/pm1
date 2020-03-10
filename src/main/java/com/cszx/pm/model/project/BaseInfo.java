package com.cszx.pm.model.project;

import java.util.Date;

public class BaseInfo {

	private String num;// 导出excel的序号
	private String id;

	private Integer pDepartment;

	private Integer dorg;

	private Integer codeCheck;

	private String pName;

	private String productName;

	private Integer pType;

	private String testType;

	private Integer pLevel;

	private Date internalTime;

	private Date factoryTime;

	private Date thirdTime;

	private Date demandExpTime;
	
	private Date demandActTime;
	
	private Date designExpTime;
	
	private Date designActTime;

	private String testYear;

	private String remark;

	private String pmName;

	private String pmTel;

	private String pmEmail;

	private Integer thirdTest;

	private String testState;

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public Integer getPDepartment() {
		return pDepartment;
	}

	public void setPDepartment(Integer pDepartment) {
		this.pDepartment = pDepartment;
	}

	public Integer getDorg() {
		return dorg;
	}

	public void setDorg(Integer dorg) {
		this.dorg = dorg;
	}

	public Integer getCodeCheck() {
		return codeCheck;
	}

	public void setCodeCheck(Integer codeCheck) {
		this.codeCheck = codeCheck;
	}

	public String getPName() {
		return pName;
	}

	public void setPName(String pName) {
		this.pName = pName == null ? null : pName.trim();
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName == null ? null : productName.trim();
	}

	public Integer getPType() {
		return pType;
	}

	public void setPType(Integer pType) {
		this.pType = pType;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	public Integer getPLevel() {
		return pLevel;
	}

	public void setPLevel(Integer pLevel) {
		this.pLevel = pLevel;
	}

	public Date getInternalTime() {
		return internalTime;
	}

	public void setInternalTime(Date internalTime) {
		this.internalTime = internalTime;
	}

	public Date getFactoryTime() {
		return factoryTime;
	}

	public void setFactoryTime(Date factoryTime) {
		this.factoryTime = factoryTime;
	}

	public Date getThirdTime() {
		return thirdTime;
	}

	public void setThirdTime(Date thirdTime) {
		this.thirdTime = thirdTime;
	}

	public Date getDemandExpTime() {
		return demandExpTime;
	}

	public void setDemandExpTime(Date demandExpTime) {
		this.demandExpTime = demandExpTime;
	}

	public Date getDemandActTime() {
		return demandActTime;
	}

	public void setDemandActTime(Date demandActTime) {
		this.demandActTime = demandActTime;
	}

	public Date getDesignExpTime() {
		return designExpTime;
	}

	public void setDesignExpTime(Date designExpTime) {
		this.designExpTime = designExpTime;
	}

	public Date getDesignActTime() {
		return designActTime;
	}

	public void setDesignActTime(Date designActTime) {
		this.designActTime = designActTime;
	}

	public String getTestYear() {
		return testYear;
	}

	public void setTestYear(String testYear) {
		this.testYear = testYear == null ? null : testYear.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getPmName() {
		return pmName;
	}

	public void setPmName(String pmName) {
		this.pmName = pmName;
	}

	public String getPmTel() {
		return pmTel;
	}

	public void setPmTel(String pmTel) {
		this.pmTel = pmTel;
	}

	public String getPmEmail() {
		return pmEmail;
	}

	public void setPmEmail(String pmEmail) {
		this.pmEmail = pmEmail;
	}

	public Integer getThirdTest() {
		return thirdTest;
	}

	public void setThirdTest(Integer thirdTest) {
		this.thirdTest = thirdTest;
	}

	public String getTestState() {
		return testState;
	}

	public void setTestState(String testState) {
		this.testState = testState;
	}

}