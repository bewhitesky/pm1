package com.cszx.common.exception;

public enum  WarnException {
    warning_falure("查询失败");

    private String desc;

    private WarnException(String desc){
        this.desc = desc;
    }

    public String getDesc(){return desc;}

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return this.desc;
    }
}
