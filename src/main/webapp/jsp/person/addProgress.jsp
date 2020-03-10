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
<body class="easyui-layout" style="padding-top: 20px">
	<div>
		<form id="detailForm" method="post" >
			<div class="layui-form-item">
				<label class="layui-form-label" for="currentime">时间:</label>
				<div class="layui-input-inline">
					<input class="easyui-datebox" style="width:198px" type="text" id="currentime" name="currentime" data-options="formatter:dataformatter,parser:myparser,editable:false" />
				</div>
				<label class="layui-form-label" for="actualProgress">进度(%):</label>
				<div class="layui-input-inline">
					<input class="easyui-validatebox layui-input" type="text"
						id="actualProgress" name="actualProgress"
						data-options="validType:'intOrFloat'" />
				</div>
			</div>
		    <div class="layui-form-item">	
				<label class="layui-form-label" for="taskDescription">任务描述:</label>
				<div>
					<textarea class="easyui-validatebox layui-textarea" id="taskDescription" name="taskDescription" style="width:530px;height:80px"></textarea>
				</div>
		    </div>
		</form>
	</div>
	<div style="padding-left:280px">
		<button class="layui-btn layui-btn-md" onclick="submitProgress()">
		    提交
		</button>
		<button class="layui-btn layui-btn-primary" onclick="closeWindow()">
	           关闭
	  	</button>
	</div>
</body>
</html>
