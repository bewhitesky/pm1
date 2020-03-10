package com.cszx.pm.model.imptask;

public class Imptask {
    private int page;
    private int rows;


    private String id;
    private String imptask_name;
    private String achievements;
    private Integer imptask_taskcls;
    private String plan;
    private String p_charge;
    private Integer imptask_state;
    private String percent;
    private String explan;
    private String remarks;
    private String p_complete_time;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImptask_name() {
        return imptask_name;
    }

    public void setImptask_name(String imptask_name) {
        this.imptask_name = imptask_name;
    }

    public String getAchievements() {
        return achievements;
    }

    public void setAchievements(String achievements) {
        this.achievements = achievements;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getExplan() {
        return explan;
    }

    public void setExplan(String explan) {
        this.explan = explan;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getP_complete_time() {
        return p_complete_time;
    }

    public void setP_complete_time(String p_complete_time) {
        this.p_complete_time = p_complete_time;
    }

    public Integer getImptask_taskcls() {
        return imptask_taskcls;
    }

    public void setImptask_taskcls(Integer imptask_taskcls) {
        this.imptask_taskcls = imptask_taskcls;
    }

    public String getP_charge() {
        return p_charge;
    }

    public void setP_charge(String p_charge) {
        this.p_charge = p_charge;
    }

    public Integer getImptask_state() {
        return imptask_state;
    }

    public void setImptask_state(Integer imptask_state) {
        this.imptask_state = imptask_state;
    }
}
