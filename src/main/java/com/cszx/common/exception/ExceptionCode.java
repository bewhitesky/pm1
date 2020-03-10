package com.cszx.common.exception;

public enum ExceptionCode {
	Login_failure("001", "用户名或密码错误"), more_try_failure("005", "尝试密码超过10次，账号已锁定"), add_failure("002",
			"添加失败"), update_failure("003", "更新失败"), delete_failure("004", "删除失败");

	private String value;
	private String desc;

	private ExceptionCode(String value, String desc) {
		this.setValue(value);
		this.setDesc(desc);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "[" + this.value + "]:" + this.desc;
	}
}
