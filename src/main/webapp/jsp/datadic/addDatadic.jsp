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
	<script type="text/javascript" src="../../assets/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="../../static/js/common.js"></script>	
	<script type="text/javascript" src='../../static/js/datadic/datadicMgr.js'></script>
  <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head> 
  <body style="padding-top: 20px">
    <form class="layui-form" id="datadicForm" >
      <div class="layui-form-item">
	    <input type="hidden" id="id" name="id" >
	    <label class="layui-form-label">分组编码:<span style="color:red;">*</span></label>
	    <div class="layui-input-inline">
	      <input type="text" id="groupCode" name="groupCode"  placeholder="分组编码" autocomplete="off" class="layui-input">
	    </div>

	    <label class="layui-form-label">分组名称:<span style="color:red">*</span></label>
	    <div class="layui-input-inline">
	      <input type="text" id="groupName" name="groupName" placeholder="分组名称" class="layui-input">
	    </div>
	    <button id="btn" type="button" style="display: none" class="layui-btn"  onclick="searchItems()">查询子项</button>
	  </div>

	  <div class="layui-form-item">
	      <input type="hidden" id="id" name="id" >
	    <label class="layui-form-label">子项编码:<span style="color:red;">*</span></label>
	    <div class="layui-input-inline">
	      <input type="text" id="dataitemCode" name="dataitemCode"  placeholder="子项编码" autocomplete="off" class="layui-input">
	    </div>

	    <label class="layui-form-label">子项名称:<span style="color:red">*</span></label>
	    <div class="layui-input-inline">
	      <input type="text" id="dataitemName" name="dataitemName"  placeholder="子项名称" class="layui-input">
	    </div>
	    <button type="button" class="layui-btn"  onclick="addItems()">添加子项</button>
	  </div>

	  
	  <div class="layui-form-item">
		  <div style="padding-left:30px;padding-right:30px;width:680px;height:200px">
		  	<table id="subitemsTable" lay-filter="itemsOpt"></table>
		  </div>
	  </div>
	</form>
	
	<div class="layui-footer">
		<div style="margin-left: 280px;margin-top:150px">
		  	<button type="button" class="layui-btn"  onclick="submitDatadic()" >提交</button>
	  		<button type="button" class="layui-btn layui-btn-primary" onclick="closeWindow()">关闭</button> 
		</div>    
	</div>
  </body>
<script>
//JavaScript代码区域
var basePath='<%=basePath%>'
var addTable;
var itemsList=new Array();
var index=0;
layui.use('form', function(){
  var form = layui.form;  
});
</script>
<script type="text/html" id="toolbar">
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
</html>
