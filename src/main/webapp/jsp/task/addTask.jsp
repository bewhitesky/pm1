 <%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
 <%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
<body class="easyui-layout" >
<div style="padding-top: 20px">

	<form id="taskForm" method="post">
	<input type="hidden" id="id"/>
	<input type="hidden" id="baseInfoId"/>
	<input type="hidden" id="pId"/>
	<input type="hidden" id="level"/>
	<input type="hidden" id="pname"/>
    <div class="layui-form-item">
		<label class="layui-form-label" for="taskName" style="width:120px">任务名称:</label>
		<div class="layui-input-inline">
			<input class="easyui-validatebox layui-input" type="text"  id="taskName" name="taskName" data-options="required:true,missingMessage:'该字段为必填项'" />
		</div>
		
		<label class="layui-form-label" for="executor" style="width:120px">测试负责人:</label>
		<div class="layui-input-inline">
			<input class="easyui-combobox" style="width:198px" type="text" id="executor" name="executor" data-options="required:true,missingMessage:'该字段为必填项'" />
		</div>
    </div>

    <div class="layui-form-item" id="t">
		<label class="layui-form-label" for="expectStartTime" style="width:120px">预计开始时间:</label>
		<div class="layui-input-inline">
			<input class="easyui-datebox" style="width:198px" type="text" id="expectStartTime" name="expectStartTime" data-options="formatter:dataformatter,parser:myparser,editable:false,required:true,missingMessage:'该字段为必填项'" />
		</div>
		<label class="layui-form-label" for="deadline" style="width:120px">预计结束时间:</label>
		<div class="layui-input-inline">
			<input class="easyui-datebox" style="width:198px" type="text" id="deadline" name="deadline" data-options="formatter:dataformatter,parser:myparser,editable:false,required:true,missingMessage:'该字段为必填项'" />
		</div>	
    </div>

    <div class="layui-form-item">
		<label class="layui-form-label" for="actualStartTime" style="width:120px">实际开始时间:</label>
		<div class="layui-input-inline">
			<input class="easyui-datebox" style="width:198px" type="text" id="actualStartTime" name="actualStartTime" data-options="formatter:dataformatter,parser:myparser,editable:false" />
		</div>
		<label class="layui-form-label" for="actualEndTime" style="width:120px">实际结束时间:</label>
		<div class="layui-input-inline">
			<input class="easyui-datebox" style="width:198px" type="text" id="actualEndTime" name="actualEndTime" data-options="formatter:dataformatter,parser:myparser,editable:false" />
		</div>
    </div>
    <div class="layui-form-item">
    	<label class="layui-form-label" for="actualProgress" style="width:120px">实际进度(%):</label>
		<div class="layui-input-inline">
			<input class="easyui-validatebox layui-input" type="text"  id="actualProgress" name="actualProgress" data-options="validType:'intOrFloat'" />
		</div>	
			
		<label class="layui-form-label" for="testStage" style="width:120px">当前状态:</label>
		<div class="layui-input-inline">
			<input class="easyui-combobox"  style="width:198px" id="taskState" name="taskState" data-options="" />
		</div>	

    </div>
    
    <div class="layui-form-item">
		<label class="layui-form-label" for="testStage" style="width:120px">测试阶段:</label>
		<div class="layui-input-inline">
			<input class="easyui-combobox"  style="width:198px" id="testStage" name="testStage" data-options="" />
		</div>
		
		<label class="layui-form-label" for="relProject" style="width:120px">关联项目:</label>
		<div class="layui-input-inline">
			<input class="easyui-combobox"  style="width:198px" id="relProject" name="relProject" data-options="" />
		</div>
		
    </div>
    <div class="layui-form-item">
		<label class="layui-form-label" for="workload" style="width:120px">工作量:</label>
		<div class="layui-input-inline">
			<input class="easyui-validatebox layui-input" type="text"  id="workload" name="workload" data-options="validType:'intOrFloat'" />
		</div>
		
		<label class="layui-form-label" for="workratio" style="width:120px">项目系数:</label>
		<div class="layui-input-inline">
			<input class="easyui-validatebox layui-input" type="text"  id="workratio" name="workratio" data-options="validType:'intOrFloat'" />
		</div>
    </div>
    <div class="layui-form-item">	
		<label class="layui-form-label" for="taskDescription" style="width:120px">任务描述:</label>
		<div>
			<textarea class="easyui-validatebox layui-textarea" id="taskDescription" name="taskDescription" style="width:530px;height:80px"></textarea>
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
