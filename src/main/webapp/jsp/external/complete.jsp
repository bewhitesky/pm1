<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>项目维护</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="stylesheet" type="text/css" href="../../assets/layui/css/layui.css"/>
	<script type="text/javascript" src="../../assets/layui/layui.js"></script>
	<script type="text/javascript" src="../../assets/layui/lay/modules/form.js"></script>
	<script type="text/javascript" src='../../static/jquery-easyui-1.5.4.2/jquery.min.js'></script>
	<script type="text/javascript" src="../../static/js/common.js"></script>
	<script type="text/javascript" src="../../static/js/external/progressMgr.js"></script>
  <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
  
</head> 
  <body style="padding-top: 20px">
	  <div  id="completeDiv" style="padding-left:30px;padding-right:30px;height:200px">
		<table id="completeTable" lay-filter="completeOpt">
		</table>
		<table class="layui-table" id="averageTable" lay-filter="averageOpt">
		</table>
	  </div> 
  </body>
<script>
//JavaScript代码区域
var basePath='<%=basePath%>'
var table;
</script>


</html>
