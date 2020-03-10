package com.cszx.pm.model.project;

public class BaseInfoVo {
	private String id;

	private Integer pDepartment;

	private String pName;

	private String productName;

	private Integer pType;

	private String testType;

	private Integer pLevel;

	private String internalTime;

	private String factoryTime;

	private String thirdTime;

	private String demandExpTime;

	private String demandActTime;

	private String designExpTime;

	private String designActTime;

	private String testYear;

	private String remark;

	private String pmName;

	private String pmTel;

	private String pmEmail;

	private Integer thirdTest;

	private Integer dorg;

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

	public String getInternalTime() {
		return internalTime;
	}

	public void setInternalTime(String internalTime) {
		this.internalTime = internalTime == null ? null : internalTime.trim();
	}

	public String getFactoryTime() {
		return factoryTime;
	}

	public void setFactoryTime(String factoryTime) {
		this.factoryTime = factoryTime == null ? null : factoryTime.trim();
	}

	public String getThirdTime() {
		return thirdTime;
	}

	public void setThirdTime(String thirdTime) {
		this.thirdTime = thirdTime == null ? null : thirdTime.trim();
	}

	public String getDemandExpTime() {
		return demandExpTime;
	}

	public void setDemandExpTime(String demandExpTime) {
		this.demandExpTime = demandExpTime;
	}

	public String getDemandActTime() {
		return demandActTime;
	}

	public void setDemandActTime(String demandActTime) {
		this.demandActTime = demandActTime;
	}

	public String getDesignExpTime() {
		return designExpTime;
	}

	public void setDesignExpTime(String designExpTime) {
		this.designExpTime = designExpTime;
	}

	public String getDesignActTime() {
		return designActTime;
	}

	public void setDesignActTime(String designActTime) {
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

	public Integer getDorg() {
		return dorg;
	}

	public void setDorg(Integer dorg) {
		this.dorg = dorg;
	}

}