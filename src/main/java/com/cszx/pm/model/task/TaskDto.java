package com.cszx.pm.model.task;

public class TaskDto {
	private int page;
	private int rows;
	private String id;

	private String taskName;

	private Integer taskRequire;

	private Integer taskState;

	private String executor;

	private String expectStartTime;

	private String expectMonth;

	private String actualStartTime;

	private String deadline;

	private String actualEndTime;

	private String creatTime;

	private String taskDescription;

	private String remark;

	private Integer level;

	private String actualMonth;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName == null ? null : taskName.trim();
	}

	public Integer getTaskRequire() {
		return taskRequire;
	}

	public void setTaskRequire(Integer taskRequire) {
		this.taskRequire = taskRequire;
	}

	public Integer getTaskState() {
		return taskState;
	}

	public void setTaskState(Integer taskState) {
		this.taskState = taskState;
	}

	public String getExecutor() {
		return executor;
	}

	public void setExecutor(String executor) {
		this.executor = executor;
	}

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

	public String getExpectStartTime() {
		return expectStartTime;
	}

	public void setExpectStartTime(String expectStartTime) {
		this.expectStartTime = expectStartTime;
	}

	public String getExpectMonth() {
		return expectMonth;
	}

	public void setExpectMonth(String expectMonth) {
		this.expectMonth = expectMonth;
	}

	public String getActualStartTime() {
		return actualStartTime;
	}

	public void setActualStartTime(String actualStartTime) {
		this.actualStartTime = actualStartTime;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public String getActualEndTime() {
		return actualEndTime;
	}

	public void setActualEndTime(String actualEndTime) {
		this.actualEndTime = actualEndTime;
	}

	public String getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription == null ? null : taskDescription.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getActualMonth() {
		return actualMonth;
	}

	public void setActualMonth(String actualMonth) {
		this.actualMonth = actualMonth;
	}

}