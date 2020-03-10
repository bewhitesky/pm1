<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<%
	String basePath=request.getScheme()+"://"
			+request.getServerName()+":"+request.getServerPort()
			+request.getContextPath();
%>

<head>
<meta charset="utf-8">
<title>项目维护</title>
</head>
<body class="easyui-layout">
	<div style="padding-top: 20px">
		<form id="subtaskform" method="post"
			style="display: inline-block; padding-left: 30px">
			<input type="hidden" id="id"/> 
			<input type="hidden" id="pId"/> 
			<input type="hidden" id="level"/> 
			<input type="hidden" id="pname"/>
			<div class="layui-form-item">
				<label class="layui-form-label" for="taskName">子任务名称:</label>
				<div class="layui-input-inline" style="width:35%;height:34px">
					<input class="easyui-validatebox layui-input" type="text"
						id="taskName" name="taskName" style="height:34px"
						data-options="required:true,missingMessage:'该字段为必填项'" readonly="readonly"/>
				</div>
				<div class="layui-input-inline" style="width:1%;padding:2px">
					<span>-</span>
				</div>
				<div class="layui-input-inline" style="width:20%;height:34px">
					<input class="easyui-validatebox layui-input" type="text"
						id="taskName2" name="taskName2" style="height:34px" placeholder="可不填写"/>
				</div>
				<div class="layui-input-inline" style="width:1%;padding:2px">
				</div>
				<div class="layui-input-inline" style="width:90px">
				    <input  class="easyui-combobox" style="width:90px" type="text"
						id="turn" name="turn"
						data-options="required:true,missingMessage:'该字段为必填项'" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label" for="executor">测试负责人:</label>
				<div class="layui-input-inline">
					<input class="easyui-combobox" style="width:198px" type="text"
						id="executor" name="executor"
						data-options="required:true,missingMessage:'该字段为必填项'" />
				</div>

				
				<label class="layui-form-label" for="workratio">项目系数:</label>
				<div class="layui-input-inline">
					<input class="easyui-validatebox layui-input" type="text"
						id="workratio" name="workratio"
						data-options="validType:'intOrFloat'" />
				</div>
			</div>

			<div class="layui-form-item" id="t">
				<label class="layui-form-label" for="expectStartTime">预计开始:</label>
				<div class="layui-input-inline">
					<input class="easyui-datebox" style="width:198px" type="text"
						id="expectStartTime" name="expectStartTime"
						data-options="formatter:dataformatter,parser:myparser,editable:false,required:true,missingMessage:'该字段为必填项'" />
				</div>
				<label class="layui-form-label" for="deadline">预计结束:</label>
				<div class="layui-input-inline">
					<input class="easyui-datebox" style="width:198px" type="text"
						id="deadline" name="deadline"
						data-options="formatter:dataformatter,parser:myparser,editable:false,required:true,missingMessage:'该字段为必填项'" />
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label" for="testStage">测试阶段:</label>
				<div class="layui-input-inline">
					<input class="easyui-combobox" style="width:198px" id="testStage"
						name="testStage" data-options="" />
				</div>

				<label class="layui-form-label" for="testStage">当前状态:</label>
				<div class="layui-input-inline">
					<input class="easyui-combobox" style="width:198px" id="taskState"
						name="taskState" data-options="" />
				</div>
			</div>
		</form>
	</div>
	<div style="padding-left:300px">
		<button class="layui-btn layui-btn-md" onclick="submitForm('#subtaskform')">保存</button>
	</div>
</body>
<script type="text/javascript">
	var basePath = '<%=basePath%>';
</script>
</html>
