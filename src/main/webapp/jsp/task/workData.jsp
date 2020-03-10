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
<form id="workForm">
    <div  style="padding-top:10px">
		<label style="padding-left:25px">计划测试月份:</label>
		<div class="layui-input-inline">
		<input class="easyui-datebox" style="width:173px" type="text" id="expectMonth" name="expectMonth" />
		</div>
				
		<label style="padding-left:25px">实际测试月份:</label>
		<div class="layui-input-inline">
		<input class="easyui-datebox" style="width:173px" type="text" id="actualMonth" name="actualMonth" />
		</div>
		
		<button type="button" class="layui-btn layui-btn-md"  style="margin-left:20px" onclick="searchWork()">
		    搜索
		</button>
		<button type="button" class="layui-btn layui-btn-md" style="margin-left:10px" onclick="resetForm()">
	            重置
	  	</button>
    </div>
</form>
<div style="padding-top: 20px">
	<table id="worktable" class="easyui-datagrid" style="width:100%;height:420px"></table>
</div>
<div style="padding-left:600px;padding-top:10px">
	<button class="layui-btn layui-btn-md" onclick="exportWordData()">
	    导出
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
