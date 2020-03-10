package com.cszx.pm.model.task;

import java.util.Date;
import java.util.List;

public class TaskTree {
	private String id;

	private String taskName;

	private Integer taskRequire;

	private Integer taskState;

	private String executor;

	private Date expectStartTime;

	private Date actualStartTime;

	private Date deadline;

	private Date actualEndTime;

	private Date creatTime;

	private Integer expectDay;

	private Integer actualDay;

	private Double expectProgress;

	private Double actualProgress;

	private String currState;

	private Integer workload;

	private Integer level;

	private String pId;

	private String taskDescription;

	private String remark;

	private Double workratio;

	private Integer testStage;

	private Integer caseDay;

	private Integer caseNum;

	private List<TaskTree> children;

	private String state = "open";

	private String iconCls = "";

	private String turn;

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
		this.executor = executor == null ? null : executor.trim();
	}

	public Date getExpectStartTime() {
		return expectStartTime;
	}

	public void setExpectStartTime(Date expectStartTime) {
		this.expectStartTime = expectStartTime;
	}

	public Date getActualStartTime() {
		return actualStartTime;
	}

	public void setActualStartTime(Date actualStartTime) {
		this.actualStartTime = actualStartTime;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public Date getActualEndTime() {
		return actualEndTime;
	}

	public void setActualEndTime(Date actualEndTime) {
		this.actualEndTime = actualEndTime;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public Integer getExpectDay() {
		return expectDay;
	}

	public void setExpectDay(Integer expectDay) {
		this.expectDay = expectDay;
	}

	public Integer getActualDay() {
		return actualDay;
	}

	public void setActualDay(Integer actualDay) {
		this.actualDay = actualDay;
	}

	public Double getExpectProgress() {
		return expectProgress;
	}

	public void setExpectProgress(Double expectProgress) {
		this.expectProgress = expectProgress;
	}

	public Double getActualProgress() {
		return actualProgress;
	}

	public void setActualProgress(Double actualProgress) {
		this.actualProgress = actualProgress;
	}

	public String getCurrState() {
		return currState;
	}

	public void setCurrState(String currState) {
		this.currState = currState == null ? null : currState.trim();
	}

	public Integer getWorkload() {
		return workload;
	}

	public void setWorkload(Integer workload) {
		this.workload = workload;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getPId() {
		return pId;
	}

	public void setPId(String pId) {
		this.pId = pId == null ? null : pId.trim();
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getWorkratio() {
		return workratio;
	}

	public void setWorkratio(Double workratio) {
		this.workratio = workratio;
	}

	public Integer getTestStage() {
		return testStage;
	}

	public void setTestStage(Integer testStage) {
		this.testStage = testStage;
	}

	public Integer getCaseDay() {
		return caseDay;
	}

	public void setCaseDay(Integer caseDay) {
		this.caseDay = caseDay;
	}

	public Integer getCaseNum() {
		return caseNum;
	}

	public void setCaseNum(Integer caseNum) {
		this.caseNum = caseNum;
	}

	public List<TaskTree> getChildren() {
		return children;
	}

	public void setChildren(List<TaskTree> children) {
		this.children = children;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIconCls() {
		if (this.level == 1) {
			iconCls = "icon-tree-level1";
		} else if (this.level == 2) {
			iconCls = "icon-tree-level2";
		}
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getTurn() {
		return turn;
	}

	public void setTurn(String turn) {
		this.turn = turn;
	}

}