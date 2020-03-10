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
	<script type="text/javascript" src='../../static/jquery-easyui-1.5.4.2/jquery.min.js'></script>
	<script type="text/javascript" src="../../static/js/common.js"></script>
	

</head>
<body class="layui-layout-body">
<div class="layout-search" id="search">
<form class="layui-form">
	<div class="layui-form-item">	
		<button type="button" id="btn" onclick="search()" style="display:none"></button>
		<label class="layui-form-label" style="width:80px">产品名称:</label>
		<div class="layui-input-inline">
			<input type="text" id="pName" name="pName" style="width:180px" autocomplete="off" class="layui-input">
		</div>
		<label class="layui-form-label" style="width:80px">测试时间:</label>
		<div class="layui-input-inline">
			<input type="text" class="layui-input" id="time" name="time" placeholder="请选择测试月份">
		</div>
		 <button type="button" id="searchbtn" class="layui-btn layui-btn-md" onclick="search()" style="margin-left: 10px">
	    	<i class="layui-icon">&#xe615;</i>查询
		</button>
		<button  type="button" id="button" class="layui-btn layui-btn-md" onclick="reset()">
	    	<i class="layui-icon">&#xe669;</i>重置
		</button>
		<button type="button" class="layui-btn layui-btn-md" onclick="exportStatistics()">
    		<i class="layui-icon">&#xe602;</i>导出
  	    </button>
</div>
</form>
</div>
<hr class="layout-search-hr-green">
	<div>
		<table  id="statisticsTable" lay-filter=""></table>
	</div>
</body>
<script type="text/javascript" src="../../static/js/internal/statisticsMgr.js"></script>
<script type="text/javascript">
var basePath='<%=basePath%>';
</script>
<script>
//JavaScript代码区域
layui.use('element', function(){
  var element = layui.element;
  var form=layui.form;
});
</script>
<script type="text/html" id="toolbar">
  <a class="layui-btn  layui-btn-xs" lay-event="ItemInfo">测试信息</a>
</script>
<script type="text/html" id="completeTool">
  <a class="layui-btn  layui-btn-xs" lay-event="edit">保存</a>
</script>
</html>
