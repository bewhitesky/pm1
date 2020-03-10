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
	<link rel="stylesheet" type="text/css" href="../../assets/easyui/demo/demo.css">
	<link rel="stylesheet" type="text/css" href="../../assets/easyui/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="../../assets/easyui/icon.css">
    <script type="text/javascript" src="../../assets/layui/layui.js"></script>
	<script type="text/javascript" src="../../assets/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="../../assets/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../../assets/easyui/validator.js"></script>
	<script type="text/javascript" src="../../assets/easyui/doCellTip.js"></script>
	<script type="text/javascript" src="../../static/js/common.js"></script>
	<script type="text/javascript" src="../../static/js/baseInfo/baseInfoMgr.js"></script>
	<script type="text/javascript" src="../../static/js/baseInfo/baseInfoOpt.js"></script>
	
</head>
<body class="easyui-layout" style="margin: 10px">
<div>
<form class="layui-form" id="searchForm">
	<div>		
		<label style="padding-left:10px">所属部门:</label>
		<div class="layui-input-inline" style="width:10%">
			<input class="easyui-combobox" style="width:100%" type="text" id="pDepartment" name="pDepartment"/>
		</div>
		
		<label style="padding-left:10px">产品名称:</label>
		<div class="layui-input-inline" style="width:10%">
			<input class="easyui-validatebox layui-input"  type="text" id="pName" name="pName"/>
		</div>
		
		<label style="padding-left:10px">测试机构:</label>
		<div class="layui-input-inline" style="width:10%">
			<input class="easyui-combobox"  style="width:100%" type="text" id="dorg" name="dorg"/>
		</div>
		
		<label style="padding-left:10px">计划内部测试时间:</label>
		<div class="layui-input-inline" style="width:10%">
        	<input type="text" class="layui-input" id="internalTime" placeholder="开始时间 ~ 结束时间">
      	</div>
      	<label style="padding-left:10px">计划第三方测试时间:</label>
		<div class="layui-input-inline" style="width:10%">
        	<input type="text" class="layui-input" id="thirdTime"  placeholder="开始时间 ~ 结束时间">
      	</div>		
	</div>
	<div style="padding-top:10px">	
		<label style="padding-left:10px">项目类别:</label>
		<div class="layui-input-inline" style="width:10%">
			<input class="easyui-combobox" style="width:100%" type="text" id="pType" name="pType"/>
		</div>
		<label style="padding-left:10px">测试年度:</label>
		<div class="layui-input-inline" style="width:10%">
			<input class="easyui-validatebox layui-input" type="text" id="testYear" name="testYear"/>
		</div>
		
		<label style="padding-left:10px">测试状态:</label>
		<div class="layui-input-inline" style="width:10%">
			<input class="easyui-combobox" style="width:100%" type="text" id="testState" name="testState"/>
		</div>		
		<label style="padding-left:10px">计划出厂测试时间:</label>
		<div class="layui-input-inline" style="width:10%">
        	<input type="text" class="layui-input" id="factoryTime" placeholder="开始时间 ~ 结束时间">
      	</div>
		
		<button type="button" class="layui-btn layui-btn-md"  style="margin-left:10px" onclick="searchBaseInfo()">
		    查询
		</button>
		<button type="button" class="layui-btn layui-btn-md" style="margin-left:10px" onclick="resetInfo()">
	            重置
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
	<div id="baseInfoDiv" style="margin-top:15px">
		<table id="baseInfoTable" class="easyui-datagrid" style="width:auto"></table>
	</div>
	<div id="w"></div>
</body>
<script type="text/javascript">
var basePath='<%=basePath%>';
$("#baseInfoTable").height(500/window.devicePixelRatio);
</script>
<script>
//JavaScript代码区域
layui.use(['element', 'form'] , function(){
  var element = layui.element;
 // var form=layui.form;
});
</script>


</html>
