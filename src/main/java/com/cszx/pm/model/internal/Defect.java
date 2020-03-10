package com.cszx.pm.model.internal;

public class Defect {
	
	private String id;

	private String baseInfoId;
	
	private String pname;
	
	private double securityDefect;

	private double schemaDefect;

	private double codeDefect;

	private double functionDefect;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBaseInfoId() {
		return baseInfoId;
	}

	public void setBaseInfoId(String baseInfoId) {
		this.baseInfoId = baseInfoId;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public double getSecurityDefect() {
		return securityDefect;
	}

	public void setSecurityDefect(double securityDefect) {
		this.securityDefect = securityDefect;
	}

	public double getSchemaDefect() {
		return schemaDefect;
	}

	public void setSchemaDefect(double schemaDefect) {
		this.schemaDefect = schemaDefect;
	}

	public double getCodeDefect() {
		return codeDefect;
	}

	public void setCodeDefect(double codeDefect) {
		this.codeDefect = codeDefect;
	}

	public double getFunctionDefect() {
		return functionDefect;
	}

	public void setFunctionDefect(double functionDefect) {
		this.functionDefect = functionDefect;
	}
	
	

}
