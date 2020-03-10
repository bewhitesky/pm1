<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta charset="utf-8">
  <title>内部项目维护</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="stylesheet" type="text/css" href="../../assets/layui/css/layui.css"/>
	<script type="text/javascript" src="../../assets/layui/layui.js"></script>
	<script type="text/javascript" src="../../assets/layui/lay/modules/form.js"></script>
	<script type="text/javascript" src='../../assets/jquery/jquery.min.js'></script>
	<script type="text/javascript" src='../../assets/jquery/jquery.validator.min.js?local=zh-CN'></script>
	<script type="text/javascript" src="../../static/js/common.js"></script>
	<script type="text/javascript" src="../../static/js/internal/internalItemOpt.js"></script>
  <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
  
</head> 
  
<body style="padding-top: 60px">
    <form class="layui-form" id="recordForm" data-validator-option="{timely:3}">
    
	    <div class="layui-form-item">
		    <input type="hidden" id="id" name="id" >
		    <input type="hidden" id="defectId" name="defectId" >
		    <input type="hidden" id="internalTestId" name="internalTestId">
		    <input type="hidden" id="count" name="count">
		    <input type="hidden" id="index" name="index">
		    <label class="layui-form-label" style="width:120px">产品名称:</label>
			<div class="layui-input-inline">
				<input type="text" class="layui-input" id="pName" name="pName" disabled="disabled">
			</div>
			<label class="layui-form-label" style="width:120px">测试项:</label>
			<div class="layui-input-inline">
				<input type="text" class="layui-input" id="testItem" name="testItem" disabled="disabled">
			</div>	
		</div>
		<div class="layui-form-item">	
			<label class="layui-form-label" style="width:120px"><span style="color:red;">*</span>搭建开始时间:</label>
			<div class="layui-input-inline">
	        	<input type="text" class="layui-input" id="deployStartTime" name="deployStartTime" placeholder="请选择项目搭建开始时间">
	      	</div>
	      	<label class="layui-form-label" style="width:120px"><span style="color:red;">*</span>搭建结束时间:</label>
			<div class="layui-input-inline">
	        	<input type="text" class="layui-input" id="deployEndTime" name="deployEndTime" placeholder="请选择项目搭建结束时间">
	      	</div>	
		</div>
		
		<div class="layui-form-item">	
			<label class="layui-form-label" style="width:120px"><span style="color:red;">*</span>测试开始时间:</label>
			<div class="layui-input-inline">
	        	<input type="text" class="layui-input" id="testStartTime" name="testStartTime" placeholder="请选择项目测试开始时间">
	      	</div>
	      	<label class="layui-form-label" style="width:120px"><span style="color:red;">*</span>测试结束时间:</label>
			<div class="layui-input-inline">
	        	<input type="text" class="layui-input" id="testEndTime" name="testEndTime" placeholder="请选择项目测试结束时间">
	      	</div>
	    </div>
	    <div id="defectDiv" class="layui-form-item" style="display: none">
	    	<label class="layui-form-label" style="width:120px"><span style="color:red;">*</span>缺陷数:</label>
			<div class="layui-input-inline">
				<input type="text" class="layui-input" id="defectNum" name="defectNum" >
			</div>
			<label class="layui-form-label" style="width:120px"><span style="color:red;">*</span>用例数:</label>
			<div class="layui-input-inline">
				<input type="text" class="layui-input" id="caseNum" name="caseNum" >
			</div>	
	    </div>
	    <div id="defectDiv1" class="layui-form-item" style="display: none;" >
	    	<label class="layui-form-label" style="width:120px"><span style="color:red;">*</span>首轮缺陷率(%):</label>
			<div class="layui-input-inline">
				<input type="text" class="layui-input" id="defect" name="defect" disabled="disabled">
			</div>
			<label class="layui-form-label" style="width:120px" id="runabledlabel">用例执行率(%):</label>
			<div class="layui-input-inline">
				<input type="text" class="layui-input" id="runabled" name="runabled">
			</div>
	    </div>
	   	<div id="runDiv" class="layui-form-item" style="display: none">
	   		<label class="layui-form-label" style="width:120px">用例执行率(%):</label>
			<div class="layui-input-inline">
				<input type="text" class="layui-input" id="runabled2" name="runabled2">
			</div>
	    </div>
	    
	    <div class="layui-form-item layui-form-text">
	    	<label class="layui-form-label" style="width:120px">备注:</label>
		    <div class="layui-input-block" >
			    <textarea class="layui-textarea" id="remark" name="remark" placeholder="请输入备注内容"  style="width:78%"></textarea>
			</div>
	  	</div>

	 <div class="layui-footer" style="margin-left: 300px;margin-top: 20px">
	  	 <button type="button" class="layui-btn"  onclick="submitRecord()" >提交</button>
	  	 <button type="button" class="layui-btn layui-btn-primary" onclick="closeWindow()">关闭</button>
	 </div>
	</form>

  </body>
<script>
//JavaScript代码区域
var basePath='<%=basePath%>'
layui.use('form', function(){
   var form= layui.form; 
   form.render(); 
});
</script>

</html>