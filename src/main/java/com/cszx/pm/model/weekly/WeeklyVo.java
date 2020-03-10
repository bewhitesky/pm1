package com.cszx.pm.model.weekly;

public class WeeklyVo {
    private int rows;
    private int page;

    private String id;
    private String w_name;
    private String time;
    private String last_work_sketch;
    private String w_type;
    private  String u_time;
    private  String this_work_sketch;
    private String remarks;
    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getW_name() {
        return w_name;
    }

    public void setW_name(String w_name) {
        this.w_name = w_name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLast_work_sketch() {
        return last_work_sketch;
    }

    public void setLast_work_sketch(String last_work_sketch) {
        this.last_work_sketch = last_work_sketch;
    }

    public String getW_type() {
        return w_type;
    }

    public void setW_type(String w_type) {
        this.w_type = w_type;
    }

    public String getU_time() {
        return u_time;
    }

    public void setU_time(String u_time) {
        this.u_time = u_time;
    }

    public String getThis_work_sketch() {
        return this_work_sketch;
    }

    public void setThis_work_sketch(String this_work_sketch) {
        this.this_work_sketch = this_work_sketch;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
