package com.cszx.pm.model.datadic;

public class DatadicGroupsDto {
	private int page;

	private int nums;

	private String id;

    private String groupCode;

    private String groupName;
    
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
}