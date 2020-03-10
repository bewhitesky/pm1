<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<%
	String basePath=request.getScheme()+"://"
			+request.getServerName()+":"+request.getServerPort()
			+request.getContextPath();
%>
<%@taglib prefix="base" uri="/WEB-INF/tld/validatorTag.tld"%>
<head>
  <meta charset="utf-8">
  <title>项目维护</title>
</head>
<body class="easyui-layout" >
<div style="padding-top: 20px">

<form id="reportForm" method="post">
	<input type="hidden" id="id"/>
	<input type="hidden" id="baseInfoId"/>
    <div class="layui-form-item">   
   	 	<label class="layui-form-label" for="pName" style="width:120px">项目名称:</label>
		<div class="layui-input-inline">
			<input class="easyui-combobox" style="width:198px" id="pName" name="pName" data-options="required:true,missingMessage:'该字段为必填项'" />
		</div>
		
		<label class="layui-form-label" for="pDepartment" style="width:120px">所属部门:</label>
		<div class="layui-input-inline">
			<input class="easyui-combobox" style="width:198px" id="pDepartment" name="pDepartment" data-options="required:true,missingMessage:'该字段为必填项'"  />
		</div>
    </div>
    <div class="layui-form-item">
		<label class="layui-form-label" for="pType" style="width:120px">项目类别:</label>
		<div class="layui-input-inline">
			<input class="easyui-combobox" style="width:198px" type="text" id="pType" name="pType" data-options="required:true,missingMessage:'该字段为必填项'" />
		</div>
		
		<label class="layui-form-label" for="currStateOne" style="width:120px">当前阶段:</label>
		<div class="layui-input-inline">
			<input class="easyui-combobox" style="width:198px" type="text" id="currStateReport" name="currStateReport" data-options="required:true,missingMessage:'该字段为必填项'" />
		</div>
		
    </div>
    <div class="layui-form-item">
		<label class="layui-form-label" for="currState" style="width:120px">当前状态:</label>
		<div class="layui-input-block">
			<input class="easyui-combobox" style="width:540px" id="currState" name="currState" data-options="required:true,missingMessage:'该字段为必填项'" />
		</div>
		
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" for="riskLevel" style="width:120px">风险等级:</label>
		<div class="layui-input-inline">
			<input class="easyui-combobox" style="width:198px" type="text" id="riskLevel" name="riskLevel" data-options="" />
		</div>
		
		<label class="layui-form-label" for="linkman" style="width:120px">联系人:</label>
		<div class="layui-input-inline">
			<base:valid field="ReportVo.linkman">
			<input class="easyui-validatebox layui-input" type="text" id="linkman" name="linkman" data-options="required:true,missingMessage:'该字段为必填项'" />
			</base:valid>
		</div>
    </div>
    
    <div class="layui-form-item">
        <label class="layui-form-label" for="applyFunTime" style="width:120px">功能申请时间:</label>
		<div class="layui-input-inline">
			<input class="easyui-datebox" style="width:198px" type="text" id="applyFunTime" name="applyFunTime" data-options="formatter:dataformatter,parser:myparser,editable:false" />
		</div>
		<label class="layui-form-label" for="applySecTime" style="width:120px">安全申请时间:</label>
		<div class="layui-input-inline">
			<input class="easyui-datebox" style="width:198px" type="text" id="applySecTime" name="applySecTime" data-options="formatter:dataformatter,parser:myparser,editable:false" />
		</div>
    </div>

    <div class="layui-form-item">
		<label class="layui-form-label" for="riskPoint" style="width:120px">风险点:</label>
		<div>
			<textarea class="easyui-validatebox layui-textarea" id="riskPoint" name="riskPoint" style="width:530px;height:80px"></textarea>
		</div>
		
    </div>
    <div class="layui-form-item">
		<label class="layui-form-label" for="remark" style="width:120px">备注:</label>
		<div>
			<textarea class="easyui-validatebox layui-textarea" id="remark" name="remark" style="width:530px;height:80px"></textarea>
		</div>
    </div>
</form>
</div>
<div style="padding-left:300px">
	<button class="layui-btn layui-btn-md" onclick="submitForm()">
	    提交
	</button>
	<button class="layui-btn layui-btn-primary" onclick="closeWindow()">
           关闭
  	</button>
</div>
</body>
<script type="text/javascript">
var basePath='<%=basePath%>';
</script>
</html>
