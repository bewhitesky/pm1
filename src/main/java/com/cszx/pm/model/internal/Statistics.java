package com.cszx.pm.model.internal;

import java.util.Date;

public class Statistics {
	private String id;
	private String pName;
	private String testItem;
	private int times;
	private double defect;
	private String testTime;
	private String correctTime;
	private Date testStartTime;
	private Date testEndTime;
	private int defectNum;
	private int caseNum;

	public String getPName() {
		return pName;
	}

	public void setPName(String pName) {
		this.pName = pName;
	}

	public String getTestItem() {
		return testItem;
	}

	public void setTestItem(String testItem) {
		this.testItem = testItem;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public double getDefect() {
		return defect;
	}

	public void setDefect(double defect) {
		this.defect = defect;
	}

	public String getTestTime() {
		return testTime;
	}

	public void setTestTime(String testTime) {
		this.testTime = testTime;
	}

	public String getCorrectTime() {
		return correctTime;
	}

	public void setCorrectTime(String correctTime) {
		this.correctTime = correctTime;
	}

	public Date getTestStartTime() {
		return testStartTime;
	}

	public void setTestStartTime(Date testStartTime) {
		this.testStartTime = testStartTime;
	}

	public Date getTestEndTime() {
		return testEndTime;
	}

	public void setTestEndTime(Date testEndTime) {
		this.testEndTime = testEndTime;
	}

	public int getDefectNum() {
		return defectNum;
	}

	public void setDefectNum(int defectNum) {
		this.defectNum = defectNum;
	}

	public int getCaseNum() {
		return caseNum;
	}

	public void setCaseNum(int caseNum) {
		this.caseNum = caseNum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
