package com.cszx.pm.model.datadic;

public class DatadicItems {
	private String id;

	private String dataitemCode;

	private String dataitemName;

	private String groupCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getDataitemCode() {
		return dataitemCode;
	}

	public void setDataitemCode(String dataitemCode) {
		this.dataitemCode = dataitemCode == null ? null : dataitemCode.trim();
	}

	public String getDataitemName() {
		return dataitemName;
	}

	public void setDataitemName(String dataitemName) {
		this.dataitemName = dataitemName == null ? null : dataitemName.trim();
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode == null ? null : groupCode.trim();
	}

}