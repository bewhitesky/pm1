<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="stylesheet" type="text/css" href="../../assets/layui/css/layui.css"/>
	<link rel="stylesheet" type="text/css" href="../../assets/common/mycss.css"/>
	<script type="text/javascript" src="../../assets/layui/layui.js"></script>
	<script type="text/javascript" src="../../assets/easyui/jquery.min.js"></script>
	<script type="text/javascript" src='../../assets/jquery/jquery.validator.min.js?local=zh-CN'></script>
	<script type="text/javascript" src="../../static/js/common.js"></script>
	<script type="text/javascript" src="../../static/js/datadic/datadicMgr.js"></script>
	

</head>
<body class="layui-layout-body">
<div class="layout-search" id="search">
	<label >分组编码:</label>
	<div class="layui-input-inline">
		<input type="text" id="groupCode" name="groupCode"  autocomplete="off" class="layui-input">
	</div>
	<label >分组名称:</label>
	<div class="layui-input-inline">
		<input type="text" id="groupName" name="groupName"  autocomplete="off" class="layui-input">
	</div>
	<button id="searchbtn" class="layui-btn layui-btn-md" onclick="searchGroups()">
	    <i class="layui-icon">&#xe615;</i>查询分组
	</button>
	<button id="reset" class="layui-btn layui-btn-md" onclick="reset()">
	    <i class="layui-icon">&#xe669;</i>重置
	</button>
</div>
<hr class="layout-search-hr-green">
	<div class="layui-btn-group">
	  <button class="layui-btn layui-btn-md" onclick="addDatadic()">
	    <i class="layui-icon">&#xe654;</i>添加
	  </button>
	  <!--  button class="layui-btn layui-btn-md" onclick="workday()">
	    <i class="layui-icon">&#xe654;</i>同步工作日
	  </button-->
	</div>
	<hr>
	<div id="groupsDiv" style="float: left;width: 48%">
		<table  id="groupsTable" lay-filter="group"></table>
	</div>

	<div id="itemsDiv" style="float: right;width: 48%">
		<table  id="itemsTable"></table>
	</div>
</body>
<script type="text/javascript">
var basePath='<%=basePath%>';
</script>
<script>
//JavaScript代码区域
layui.use('element', function(){
  var element = layui.element;
  
});
</script>
<script type="text/html" id="groupbar">
  <a class="layui-btn layui-btn-xs" lay-event="search">查询</a>
  <a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
</html>
