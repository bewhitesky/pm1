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
		<input type="hidden" id="id" name="id">
		<input type="hidden" id="defectId" name="defectId">
		<input type="hidden" id="defectNum" name="defectNum">
		<input type="hidden" id="caseNum" name="caseNum">
		<input type="hidden" id="count" name="count">
		<button type="button" id="btn" onclick="search()" style="display:none"></button>
		<label class="layui-form-label" style="width:80px">产品名称:</label>
		<div class="layui-input-inline">
			<input type="text" id="pName" name="pName" style="width:180px" autocomplete="off" class="layui-input" disabled="disabled">
		</div>
		<label class="layui-form-label" style="width:80px">测试项:</label>
		<div class="layui-input-inline">
			<input type="text" id="testItem" name="testItem" style="width:180px" autocomplete="off" class="layui-input" disabled="disabled">
		</div>	
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label" style="width:80px">整改总用时:</label>
		<div class="layui-input-inline">
			<input type="text" id="correctTime" name="correctTime" style="width:180px" autocomplete="off" class="layui-input" disabled="disabled">
		</div>
		<label class="layui-form-label" style="width:80px">测试总用时:</label>
		<div class="layui-input-inline">
			<input type="text" id="testTime" name="testTime" style="width:180px" autocomplete="off" class="layui-input" disabled="disabled">
		</div>
		<label class="layui-form-label" style="width:100px">首轮缺陷率(%):</label>
		<div class="layui-input-inline">
			<input type="text" id="defect" name="defect" style="width:180px" autocomplete="off" class="layui-input" disabled="disabled">
		</div>	
	</div>
	
</form>
</div>
<hr class="layout-search-hr-green">
	<div class="layui-btn-group">
	  <button class="layui-btn layui-btn-md" onclick="addItems()">
	    <i class="layui-icon">&#xe654;</i>添加
	  </button>
	  <button class="layui-btn layui-btn-md" onclick="updateItems()">
	    <i class="layui-icon">&#xe642;</i>修改
	  </button>
	  <button class="layui-btn layui-btn-danger layui-btn-md" onclick="deleteItems()">
	    <i class="layui-icon">&#xe640;</i>删除
	  </button>

	</div>
	<div>
		<table  id="internalItemTable" lay-filter=""></table>
	</div>
</body>
<script type="text/javascript" src="../../static/js/internal/internalItemMgr.js"></script>
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
