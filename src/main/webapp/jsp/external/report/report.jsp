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
  	<link rel="stylesheet" type="text/css" href="../../../assets/easyui/demo/demo.css">
	<link rel="stylesheet" type="text/css" href="../../../assets/easyui/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="../../../assets/easyui/icon.css">
    <script type="text/javascript" src="../../../assets/layui/layui.js"></script>
	<script type="text/javascript" src="../../../assets/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="../../../assets/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../../../assets/easyui/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="../../../assets/easyui/validator.js"></script>
	<script type="text/javascript" src="../../../assets/easyui/doCellTip.js"></script>
	<script type="text/javascript" src="../../../static/js/common.js"></script>
	<script type="text/javascript" src="../../../static/js/external/report/reportMgr.js"></script>
	<script type="text/javascript" src="../../../static/js/external/report/reportOpt.js"></script>
	
</head>
<body class="easyui-layout" style="margin: 10px">
<div>
<form id="searchForm">
	<div>
		<label style="padding-left:10px">产品名称:</label>
		<div class="layui-input-inline">
			<input class="easyui-validatebox layui-input" type="text" id="pName" name="pName"/>
		</div>

		<label style="padding-left:25px">当前阶段:</label>
		<div class="layui-input-inline">
			<input class="easyui-combobox" style="width:198px" type="text" id="currStateReport" name="currStateReport"/>
		</div>
		<label style="padding-left:10px">风险等级:</label>
		<div class="layui-input-inline">
			<input class="easyui-combobox" style="width:198px" type="text" id="riskLevel" name="riskLevel"/>
		</div>

		<button type="button" class="layui-btn layui-btn-md"  style="margin-left:10px" onclick="searchReport()">
			搜索
		</button>
		<button type="button" class="layui-btn layui-btn-md" style="margin-left:10px" onclick="resetForm()">
			重置
		</button>
	</div>
</form>
</div>
<hr class="layout-search-hr-green" style="margin-top: 10px">
<div id="toolbar" class="layui-btn-group">
	  <button class="layui-btn layui-btn-md" onclick="addReport()">
	    新增
	  </button>
	  <button class="layui-btn layui-btn-md" onclick="updateReport()">
	    修改
	  </button>
	  <button class="layui-btn layui-btn-danger layui-btn-md" onclick="deleteReport()">
	     删除
	  </button>
	  <button class="layui-btn layui-btn-md" onclick="exportReport()">
           导出
  	  </button>
</div>
<div id="reportDiv" style="margin-top:15px">
	<table id="reportTable" class="easyui-datagrid" style="width:100%;height:500px"></table>
</div>
<div id="w"></div>
</body>
<script type="text/javascript">
var basePath='<%=basePath%>';
</script>
</html>
