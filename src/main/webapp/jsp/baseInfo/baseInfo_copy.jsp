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
	<script type="text/javascript" src='../../assets/jquery/jquery.validator.min.js?local=zh-CN'></script>
	<script type="text/javascript" src="../../static/js/common.js"></script>
	<script type="text/javascript" src="../../static/js/baseInfo/baseInfoMgr.js"></script>
	
</head>
<body class="layui-layout-body">
<div class="layout-search" id="search">
<form class="layui-form">
	<div>
		<label style="padding-left:10px">所属部门:</label>
		<div class="layui-input-inline">
			<select id="pDepartment" name="pDepartment"  lay-verify="">
			</select>
		</div>
		<label style="padding-left:10px">项目名称:</label>
		<div class="layui-input-inline">
			<input type="text" id="pName" name="pName" style="width:212px" autocomplete="off" class="layui-input" data-rule="stringValidate;length(~64)">
		</div>
		<label style="padding-left:10px">测试机构:</label>
		<div class="layui-input-inline">
			<select id="dorg" name="dorg"  lay-verify="" placeholder="请选择...">
			</select> 
		</div>
		<label style="padding-left:10px">计划内部测试时间:</label>
		<div class="layui-input-inline">
        	<input type="text" class="layui-input" id="internalTime" placeholder="开始时间 ~ 结束时间">
      	</div>
      	<label style="padding-left:10px">计划第三方测试时间:</label>
		<div class="layui-input-inline">
        	<input type="text" class="layui-input" id="thirdTime"  placeholder="开始时间 ~ 结束时间">
      	</div>		
	</div>
	<div style="padding-top:10px">
		<label style="padding-left:10px">项目类别:</label>
		<div class="layui-input-inline">
			<select id="pType" name="pType"  lay-verify="" placeholder="请选择13546">
			</select> 
		</div>
		<label style="padding-left:10px">测试年度:</label>
		<div class="layui-input-inline">
			<input type="text" id="testYear" name="testYear" style="width:212px" autocomplete="off" class="layui-input"  data-rule="digits;length(~4)">
		</div>
		<label style="padding-left:10px">测试状态:</label>
		<div class="layui-input-inline">
			<select id="testState" name="testState"  lay-verify="" placeholder="请选择...">
			</select> 
		</div>	
		<label style="padding-left:10px">计划出厂测试时间:</label>
		<div class="layui-input-inline">
        	<input type="text" class="layui-input" id="factoryTime" placeholder="开始时间 ~ 结束时间">
      	</div>

		
		<button type="button" id="searchbtn" class="layui-btn layui-btn-md" onclick="searchBaseInfo()" style="margin-left: 10px">
	    	<i class="layui-icon">&#xe615;</i>查询
		</button>
		<button id="button" class="layui-btn layui-btn-md" onclick="reset()">
	    	<i class="layui-icon">&#xe669;</i>重置
		</button>	
	</div>
	

</form>
</div>
<hr class="layout-search-hr-green">
	<div class="layui-btn-group">
	  <button class="layui-btn layui-btn-md" onclick="addBaseInfo()">
	    <i class="layui-icon">&#xe654;</i>添加
	  </button>
	  <button class="layui-btn layui-btn-md" onclick="updateBaseInfo()">
	    <i class="layui-icon">&#xe642;</i>修改
	  </button>
	  <button class="layui-btn layui-btn-danger layui-btn-md" onclick="deleteBaseInfo()">
	    <i class="layui-icon">&#xe640;</i>删除
	  </button>
	  <button class="layui-btn layui-btn-md" onclick="exportBaseInfo()">
    	<i class="layui-icon">&#xe602;</i>导出
  	  </button>
  	  <button class="layui-btn layui-btn-md" id="importExcel">
    	<i class="layui-icon">&#xe603;</i>导入
  	  </button>
  	  <button class="layui-btn layui-btn-md" id="download" onclick="download()">
    	<i class="layui-icon">&#xe601;</i>下载导入模板
  	  </button>   
	</div>
	<div id="baseInfoDiv">
		<table  id="baseInfoTable"></table>
	</div>
</body>
<script type="text/javascript">
var basePath='<%=basePath%>';
</script>
<script>
//JavaScript代码区域
layui.use(['element', 'form'] , function(){
  var element = layui.element;
  var form=layui.form;
});
</script>


</html>
