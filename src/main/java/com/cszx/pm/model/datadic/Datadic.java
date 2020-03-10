package com.cszx.pm.model.datadic;

import java.util.List;

public class Datadic {
	private String id;

	private String groupCode;

	private String groupName;

	private List<DatadicItems> datadicItems;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode == null ? null : groupCode.trim();
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName == null ? null : groupName.trim();
	}

	public List<DatadicItems> getDatadicItems() {
		return datadicItems;
	}

	public void setDatadicItems(List<DatadicItems> datadicItems) {
		this.datadicItems = datadicItems;
	}

}