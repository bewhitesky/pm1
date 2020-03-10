<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<%
	String basePath=request.getScheme()+"://"
			+request.getServerName()+":"+request.getServerPort()
			+request.getContextPath();
	//String json=request.getParameter("json");
%>

<head>
  <meta http-equiv="cache-control" content="no-cache,no-store, must-revalidate" />
  <meta http-equiv="pragma" content="no-cache" /><meta http-equiv="Expires" content="0" />
  <meta charset="utf-8">
  <title>项目维护</title>
  	<link rel="stylesheet" type="text/css" href="../../assets/easyui/demo/demo.css"/>
	<link rel="stylesheet" type="text/css" href="../../assets/easyui/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="../../assets/easyui/icon.css">
    <script type="text/javascript" src="../../assets/layui/laydate.js"></script>
    <script type="text/javascript" src="../../assets/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="../../assets/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../../assets/easyui/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="../../assets/layui/layer.js"></script>
	<script type="text/javascript" src="../../assets/easyui/validator.js"></script>
	<script type="text/javascript" src="../../assets/easyui/doCellTip.js"></script>
	<script type="text/javascript" src="../../static/js/common.js"></script>
	<script type="text/javascript" src="../../static/js/task/detailMgr.js"></script>
	<script type="text/javascript" src="../../static/js/task/detailOpt.js"></script>
	<style>
		.layui-form-label {
			padding-bottom: 20px
		}
		textarea{
			font-size:14px; 
			font-family:Helvetica Neue, Helvetica,PingFang SC,Tahoma,Arial,sans-serif
		}
	</style>
</head>
<body style="padding: 20px; background-color: #F2F2F2;">
	<div class="layui-row layui-col-space10" style="height:400px">
		<div class="layui-col-md6">
	      <div class="layui-card">
	        <div class="layui-card-header">任务详情：</div>
	        <div class="layui-card-body" style="height:320px">
		        <form id="detailForm" method="post" style="display: inline-block; padding-left: 30px" >
					<input type="hidden" id="id"/>
					<input type="hidden" id="pId"/>
					<input type="hidden" id="level"/>
					<input type="hidden" id="pname"/>
					<input class="easyui-combobox" type="hidden" id="turn">
				    <div class="layui-form-item">
						<label class="layui-form-label" for="taskName" >任务名称:</label>
						<div class="layui-input-block">
							<input class="easyui-validatebox layui-input" type="text"  id="taskName" name="taskName" data-options="required:true,missingMessage:'该字段为必填项'" readonly="readonly"/>
						</div>
				    </div>
				   	<div class="layui-form-item">					
						<label class="layui-form-label" for="executor"  >测试负责人:</label>
						<div class="layui-input-inline">
							<input class="easyui-combobox" style="width:198px" type="text" id="executor" name="executor" data-options="required:true,missingMessage:'该字段为必填项'" />
						</div>
						<label class="layui-form-label" for="testStage"  >当前状态:</label>
						<div class="layui-input-inline">
							<input class="easyui-combobox"  style="width:198px" id="taskState" name="taskState" data-options="" />
						</div>
				    </div>
				
				    <div class="layui-form-item" id="t">
						<label class="layui-form-label" for="expectStartTime"  >预计开始:</label>
						<div class="layui-input-inline">
							<input class="easyui-datebox" style="width:198px" type="text" id="expectStartTime" name="expectStartTime" data-options="formatter:dataformatter,parser:myparser,editable:false,required:true,missingMessage:'该字段为必填项'" />
						</div>
						<label class="layui-form-label" for="deadline"  >预计结束:</label>
						<div class="layui-input-inline">
							<input class="easyui-datebox" style="width:198px" type="text" id="deadline" name="deadline" data-options="formatter:dataformatter,parser:myparser,editable:false,required:true,missingMessage:'该字段为必填项'" />
						</div>	
				    </div>
				
				    <!--  div class="layui-form-item">
						<label class="layui-form-label" for="actualStartTime"  >实际开始:</label>
						<div class="layui-input-inline">
							<input class="easyui-datebox" style="width:198px" type="text" id="actualStartTime" name="actualStartTime" data-options="formatter:dataformatter,parser:myparser,editable:false" />
						</div>
						<label class="layui-form-label" for="actualEndTime"  >实际结束:</label>
						<div class="layui-input-inline">
							<input class="easyui-datebox" style="width:198px" type="text" id="actualEndTime" name="actualEndTime" data-options="formatter:dataformatter,parser:myparser,editable:false" />
						</div>
				    </div-->
				    <div class="layui-form-item">
						<label class="layui-form-label" for="testStage"  >测试阶段:</label>
						<div class="layui-input-inline">
							<input class="easyui-combobox"  style="width:198px" id="testStage" name="testStage" data-options="" />
						</div>
						
						<label class="layui-form-label" for="workratio"  >项目系数:</label>
						<div class="layui-input-inline">
							<input class="easyui-validatebox layui-input" type="text"  id="workratio" name="workratio" data-options="validType:'intOrFloat'" />
						</div>
				    </div>
				    <div class="layui-form-item">
						<label class="layui-form-label" for="caseDay"  style="width:90px;padding-left: 5px">编写用例天数:</label>
						<div class="layui-input-inline">
							<input class="easyui-validatebox layui-input" type="text"  id="caseDay" name="caseDay" data-options="validType:'intOrFloat'" />
						</div>
						
						<label class="layui-form-label" for="caseNum"  >编写用例数:</label>
						<div class="layui-input-inline">
							<input class="easyui-validatebox layui-input" type="text"  id="caseNum" name="caseNum" data-options="validType:'intOrFloat'" />
						</div>
				    </div>
				</form>
				<div style="padding-left:250px;margin: 15px">
					<button class="layui-btn layui-btn-md" onclick="submitForm('#detailForm')">
					    保存
					</button>
					<button id="addSubTask" class="layui-btn layui-btn-primary" onclick="addSubTask()">
					    任务分配
					</button>
				</div>
			</div>
		  </div>
	    </div>
	    <div class="layui-col-md6">
	      <div class="layui-card">
	        <div class="layui-card-header">任务分配：</div>
	        <div class="layui-card-body" style="height:320px">
				<table id="subTask" class="easyui-datagrid" style="width:100%;height:350px"></table>
	        </div>
	      </div>
	    </div>    
	</div>
	<div class="layui-row layui-col-space10">
		<div class="layui-col-md6">
	      <div class="layui-card">
			<div class="layui-card-header">进度维护</div>
				<div class="layui-card-body" style="height:260px;">
				<form id="detailForm2" method="post" style="display: display: inline-block;">	
				    <div class="layui-form-item">
				    	<label class="layui-form-label" for="currentime" style="width:120px">时间:</label>
						<div class="layui-input-inline">
							<input class="easyui-datebox" style="width:198px" type="text" id="currentime" name="currentime" data-options="formatter:dataformatter,parser:myparser,editable:false" />
						</div>
				    	<label class="layui-form-label" for="actualProgress">进度(%):</label>
						<div class="layui-input-inline">
							<input class="easyui-validatebox layui-input" type="text"  id="actualProgress" name="actualProgress" data-options="validType:'intOrFloat'" />
						</div>
						
				    </div>
				    <div class="layui-form-item">
						<label class="layui-form-label" for="taskDescription" style="width:120px">任务描述:</label>
						<div>
							<textarea class="easyui-validatebox layui-textarea" id="taskDescription" name="taskDescription" style="width:500px;height:80px"></textarea>
						</div>
				    </div>
				</form>
				<div style="padding-left:250px;margin: 15px">
					<button class="layui-btn layui-btn-md" onclick="submitProgress()" style="width:160px">
					    保存
					</button>
				</div>
				</div>
			</div>
	    </div>
	   	<div class="layui-col-md6">
	      <div class="layui-card">
	        <div class="layui-card-header">历史进度：</div>
	        <div class="layui-card-body" style="height:260px;overflow-x: hidden;overflow-y: auto">
				<ul class="layui-timeline">
				</ul>
	        </div>
	      </div>
	    </div>
	</div>

	<div id="w"></div>
</body>
<script type="text/javascript">
var basePath='<%=basePath%>';
var task=JSON.parse(parent.json);
console.log(task.id);
</script>
</html>
