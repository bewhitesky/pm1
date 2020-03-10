package com.cszx.pm.model.internal;

import java.util.List;

import com.cszx.pm.model.project.BaseInfo;

public class InternalObj {
	/**
	 * 项目基本信息
	 */
	private BaseInfo baseInfo;
	/**
	 * 内部测试信息
	 */
	private Internal internal;
	/**
	 * 内部测试轮次信息
	 */
	private List<InternalItem> internalItems;

	public BaseInfo getBaseInfo() {
		return baseInfo;
	}

	public void setBaseInfo(BaseInfo baseInfo) {
		this.baseInfo = baseInfo;
	}

	public Internal getInternal() {
		return internal;
	}

	public void setInternal(Internal internal) {
		this.internal = internal;
	}

	public List<InternalItem> getInternalItems() {
		return internalItems;
	}

	public void setInternalItems(List<InternalItem> internalItems) {
		this.internalItems = internalItems;
	}

}