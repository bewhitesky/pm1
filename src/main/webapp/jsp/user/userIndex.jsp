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
	<script type="text/javascript" src="../../static/js/userMgr.js"></script>
	

</head>
<body class="layui-layout-body">
<div class="layout-search" id="search">
	<label >登录账号:</label>
	<div class="layui-input-inline">
		<input type="text" id="loginId" name="loginId"  autocomplete="off" class="layui-input">
	</div>
	<label >用户名:</label>
	<div class="layui-input-inline">
		<input type="text" id="userName" name="userName"  autocomplete="off" class="layui-input">
	</div>
	<button id="searchbtn" class="layui-btn layui-btn-md" onclick="searchUser()">
	    <i class="layui-icon">&#xe615;</i>查询
	</button>
	<button id="reset" class="layui-btn layui-btn-md" onclick="reset()">
	    <i class="layui-icon">&#xe669;</i>重置
	</button>
</div>
<hr class="layout-search-hr-green">
	<div class="layui-btn-group">
	  <button class="layui-btn layui-btn-md" onclick="addUser()">
	    <i class="layui-icon">&#xe654;</i>添加
	  </button>
	  <button class="layui-btn layui-btn-md" onclick="updateUser()">
	    <i class="layui-icon">&#xe642;</i>修改
	  </button>
	  <button class="layui-btn layui-btn-danger layui-btn-md" onclick="deleteUser()">
	    <i class="layui-icon">&#xe640;</i>删除
	  </button>
	  <!--  button class="layui-btn layui-btn-md" onclick="updateUser()">
	    <i class="layui-icon">&#xe642;</i>关联角色
	  </button-->
	  <button class="layui-btn layui-btn-md" onclick="exportUser()">
    	<i class="layui-icon">&#xe602;</i>导出
  	  </button>
  	  <button class="layui-btn layui-btn-md" id="importExcel">
    	<i class="layui-icon">&#xe603;</i>导入
  	  </button>  
	</div>
	<div id="userDiv">
		<table  id="userTable"></table>
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
