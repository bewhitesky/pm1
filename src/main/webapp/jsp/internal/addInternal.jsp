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
	<script type="text/javascript" src="../../static/js/internal/internalOpt.js"></script>
  <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
  
</head> 
  
<body style="padding-top: 10px">
    <form class="layui-form" id="internalForm" data-validator-option="{timely:3}">
    
	    <div class="layui-form-item">
		    <input type="hidden" id="id" name="id" >
		    <label class="layui-form-label" style="width:125px"><span style="color:red;">*</span>产品名称:</label>
			<div class="layui-input-inline">
				<select id="pName" name="pName" class="layui-select" style="width:190px" lay-filter="pName">
				</select>
			</div>
			<label class="layui-form-label" style="width:125px"><span style="color:red;">*</span>测试项:</label>
			<div class="layui-input-inline">
				<select id="testItem" name="testItem" class="layui-select" style="width:190px" lay-filter="testItem">
				</select>
			</div>		
		</div>
		
		<div class="layui-form-item">	
			
	      	<label class="layui-form-label" style="width:125px"><span style="color:red;">*</span>项目接收时间:</label>
			<div class="layui-input-inline">
	        	<input type="text" class="layui-input" id="receiveTime" name="receiveTime" placeholder="请选择项目接收时间">
	      	</div>
	      	<label class="layui-form-label" style="width:125px"><span style="color:red;">*</span>计划环境搭建时间:</label>
			<div class="layui-input-inline">
	        	<input type="text" class="layui-input" id="deploymentTime" name="deploymentTime" placeholder="请选择计划环境搭建时间">
	      	</div>
	      	
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label" style="width:125px"><span style="color:red;">*</span>测试负责人:</label>
			<div class="layui-input-inline">
	      		<select id="testPrincipal" name="testPrincipal" class="layui-select" style="width:190px">
				</select>
	      	</div>	
			
			<label class="layui-form-label" style="width:125px"><span style="color:red;">*</span>测试状态:</label>
			<div class="layui-input-inline">
				<select id="internalTestState" name="internalTestState" class="layui-select" style="width:190px" lay-filter="internalTestState">
				</select>
				<span class="msg-box" id="internalTestStateMsg" style="font-size:12px;color:#c33;"></span>
			</div>	
		</div>
		
		<div class="layui-form-item">
		    <label class="layui-form-label" style="width:125px"><span style="color:red;">*</span>测试服务器:</label>
			<div class="layui-input-inline">
				<input type="text" class="layui-input" id="testServer" name="testServer" placeholder="请输入测试服务器" data-rule="测试服务器:required;length(~128)">
			</div>
	      	
	      	<label class="layui-form-label" style="width:125px"><span style="color:red;">*</span>测试配合人员:</label>
			<div class="layui-input-inline">
	        	<input type="text" class="layui-input" id="testerName" name="testerName" placeholder="请输入测试配合人员" data-rule="测试配合人员:required;stringValidate">
	      	</div>
		</div>
		
		<div class="layui-form-item">	      	
	      	<label class="layui-form-label" style="width:125px">联系电话:</label>
			<div class="layui-input-inline">
	        	<input type="text" class="layui-input" id="testerTel" name="testerTel" placeholder="请输入测试配合人员电话" data-rule="mobile;length(~64)">	
	      	</div>
	      	<label class="layui-form-label" style="width:125px">邮箱地址:</label>
			<div class="layui-input-inline">
	        	<input type="text" class="layui-input" id="testerEmail" name="testerEmail" placeholder="请输入测试配合人员邮箱" data-rule="email;length(~64)">	
	      	</div>

		</div>
		
		<div class="layui-form-item">	      	
			<label class="layui-form-label" style="width:130px">备注:</label>
		    <div class="layui-input-block" >
		      <textarea class="layui-textarea" id="remark" name="remark" placeholder="请输入备注内容"  style="width:80%" data-rule="stringValidate;length(~256)"></textarea>
		    </div>
	      	
		</div>

	 <div class="layui-footer" style="margin-left: 300px;margin-top: 50px">
	  	 <button type="button" class="layui-btn"  onclick="submitInternal()" >提交</button>
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
