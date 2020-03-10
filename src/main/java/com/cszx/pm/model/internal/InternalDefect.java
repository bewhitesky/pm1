package com.cszx.pm.model.internal;

public class InternalDefect {
	private String id;

	private String internalTestId;

	private Double defect;

	private Integer defectNum;

	private Integer caseNum;

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

	public Double getDefect() {
		return defect;
	}

	public void setDefect(Double defect) {
		this.defect = defect;
	}

	public Integer getDefectNum() {
		return defectNum;
	}

	public void setDefectNum(Integer defectNum) {
		this.defectNum = defectNum;
	}

	public Integer getCaseNum() {
		return caseNum;
	}

	public void setCaseNum(Integer caseNum) {
		this.caseNum = caseNum;
	}

}