package com.cszx.pm.model.task;

public class TaskVo {
	private String id;

	private String pId;

	private String baseInfoId;

	private String taskName;
	
	private String turn;

	private String taskRequire;

	private String taskState;

	private String executor;

	private String expectStartTime;

	private String actualStartTime;

	private String deadline;

	private String actualEndTime;

	private String creatTime;

	private String taskDescription;

	private String remark;

	private String expectDay;

	private String actualDay;

	private String actualProgress;

	private String workload;

	private String workratio;

	private String level;

	private String testStage;

	private String caseDay;

	private String caseNum;
	//
	// private Double currentProgress;
	//
	// private Double actualProgress;
	//
	// private String currState;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getPId() {
		return pId;
	}

	public void setPId(String pId) {
		this.pId = pId;
	}

	public String getBaseInfoId() {
		return baseInfoId;
	}

	public void setBaseInfoId(String baseInfoId) {
		this.baseInfoId = baseInfoId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName == null ? null : taskName.trim();
	}
	
	public String getTurn() {
		return turn;
	}

	public void setTurn(String turn) {
		this.turn = turn;
	}

	public String getTaskRequire() {
		return taskRequire;
	}

	public void setTaskRequire(String taskRequire) {
		this.taskRequire = taskRequire;
	}

	public String getTaskState() {
		return taskState;
	}

	public void setTaskState(String taskState) {
		this.taskState = taskState;
	}

	public String getExecutor() {
		return executor;
	}

	public void setExecutor(String executor) {
		this.executor = executor;
	}

	public String getExpectStartTime() {
		return expectStartTime;
	}

	public void setExpectStartTime(String expectStartTime) {
		this.expectStartTime = expectStartTime;
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

	public String getExpectDay() {
		return expectDay;
	}

	public void setExpectDay(String expectDay) {
		this.expectDay = expectDay;
	}

	public String getActualDay() {
		return actualDay;
	}

	public void setActualDay(String actualDay) {
		this.actualDay = actualDay;
	}

	public String getActualProgress() {
		return actualProgress;
	}

	public void setActualProgress(String actualProgress) {
		this.actualProgress = actualProgress;
	}

	public String getWorkload() {
		return workload;
	}

	public void setWorkload(String workload) {
		this.workload = workload;
	}

	public String getWorkratio() {
		return workratio;
	}

	public void setWorkratio(String workratio) {
		this.workratio = workratio;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getTestStage() {
		return testStage;
	}

	public void setTestStage(String testStage) {
		this.testStage = testStage;
	}

	public String getCaseDay() {
		return caseDay;
	}

	public void setCaseDay(String caseDay) {
		this.caseDay = caseDay;
	}

	public String getCaseNum() {
		return caseNum;
	}

	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}

}