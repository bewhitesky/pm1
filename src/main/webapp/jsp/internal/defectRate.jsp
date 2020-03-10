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
  <title>首轮缺陷率</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="stylesheet" type="text/css" href="../../assets/layui/css/layui.css"/>
	<link rel="stylesheet" type="text/css" href="../../assets/common/mycss.css"/>
	<script type="text/javascript" src="../../assets/layui/layui.js"></script>
	<script type="text/javascript" src='../../static/jquery-easyui-1.5.4.2/jquery.min.js'></script>
	<script type="text/javascript" src='../../assets/jquery/jquery.validator.min.js?local=zh-CN'></script>
	<script type="text/javascript" src="../../static/js/common.js"></script>
	<script type="text/javascript" src="../../static/js/internal/defectRateMgr.js"></script>
	

</head>
<body class="layui-layout-body">
	<div class="layout-search" id="search">
		<form class="layui-form">
			<div>
				<input type="hidden" id="id" name="id"> <label
					style="padding-left:10px">产品名称:</label>
				<div class="layui-input-inline">
					<input type="text" id="pName" name="pName" style="width:212px"
						autocomplete="off" class="layui-input" data-rule="stringValidate;length(~64)">
				</div>
				<!--  
				<label style="padding-left:10px">计划环境搭建时间:</label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input" id="deploymentTime"
						placeholder="开始时间 ~ 结束时间">
				</div>
				-->
				<button type="button" id="searchbtn" class="layui-btn layui-btn-md"
					onclick="searchDefect()" style="margin-left: 10px">
					<i class="layui-icon">&#xe615;</i>查询
				</button>
				<button type="button" id="button" class="layui-btn layui-btn-md"
					onclick="reset()">
					<i class="layui-icon">&#xe669;</i>重置
				</button>
			</div>

		</form>
	</div>

	<hr class="layout-search-hr-green">
	
	<div>
		<table id="defectTable" lay-filter="defectTable"></table>
	</div>
</body>
<script>
	//JavaScript代码区域
	var basePath = '<%=basePath%>'
	layui.use('form', function() {
		var form = layui.form;
		form.render();
	});
</script>
<script type="text/html" id="toolbar">
  <a class="layui-btn  layui-btn-xs" lay-event="edit">保存</a>
</script>
</html>
