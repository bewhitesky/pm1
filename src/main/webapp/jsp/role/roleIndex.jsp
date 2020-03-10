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
	<link rel="stylesheet" type="text/css" href="../assets/layui/css/layui.css"/>
	<script type="text/javascript" src="../assets/layui/layui.js"></script>
	<script type="text/javascript" src="../static/js/roleMgr.js"></script>
	

</head>
<body class="layui-layout-body" scroll="no">
	<div class="layui-btn-group">
	  <button class="layui-btn layui-btn-md">
	    <i class="layui-icon">&#xe654;</i>添加
	  </button>
	  <button class="layui-btn layui-btn-md">
	    <i class="layui-icon">&#xe642;</i>修改
	  </button>
	  <button class="layui-btn layui-btn-danger layui-btn-md">
	    <i class="layui-icon">&#xe640;</i>删除
	  </button>
	</div>
	<div id="roleDiv">
		<table  id="roleTable"></table>
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

</html>
