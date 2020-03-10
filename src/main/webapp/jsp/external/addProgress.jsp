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
	<script type="text/javascript" src='../../static/jquery/jquery.min.js'></script>
	<script type="text/javascript" src='../../assets/jquery/jquery.validator.min.js?local=zh-CN'></script>
	<script type="text/javascript" src="../../static/js/common.js"></script>
	<script type="text/javascript" src="../../static/js/external/progressOpt.js"></script>
  <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
  
</head> 
  <body style="padding-top: 20px">
    <form class="layui-form" id="addProgressForm" >
	    <div class="layui-form-item">
	    	<input type="hidden" id="id" name="id" >
			<label class="layui-form-label" style="width:130px"><span style="color:red;">*</span>项目名称:</label>
			<div class="layui-input-inline">
				<input type="text" id="pName" name="pName" autocomplete="off" lay-verify="required" class="layui-input" placeholder="请输入项目名称" disabled="disabled">
			</div>
			<label class="layui-form-label" style="width:140px"><span style="color:red;">*</span>产品名称:</label>			
			<div class="layui-input-inline">
				<input type="text" id="productName" name="productName" autocomplete="off" class="layui-input" placeholder="请输入产品名称" disabled="disabled">
			</div>	
		</div>
		
		<div class="layui-form-item">			
			<label class="layui-form-label" style="width:130px"><span style="color:red;">*</span>当前阶段1:</label>
			<div class="layui-input-inline">
				<select id="currStateOne" name="currStateOne"  lay-verify="" class="layui-select" style="width:190px" lay-filter="currStateOne">
				</select>
				<span class="msg-box" id="currStateOneMsg" style="font-size:12px;color:#c33;"></span>
			</div>
		<!--暂时隐藏  	<label class="layui-form-label" style="width:140px"><span style="color:red;">*</span>当前阶段2:</label>
			<div class="layui-input-inline">
				<select id="currStateTwo" name="currStateTwo"  lay-verify="" class="layui-select" style="width:190px" lay-filter="currStateTwo">
				</select>
				<span class="msg-box" id="currStateTwoMsg" style="font-size:12px;color:#c33;"></span>
			</div>	-->
			<label class="layui-form-label" style="width:140px">测试状态:</label>
			<div class="layui-input-inline">
				<select id="testState" name="testState"  lay-verify="" placeholder="请选择..." lay-filter="state">
				</select> 
			</div>
		</div>
		
	
		<div class="layui-form-item">
			
			<label class="layui-form-label" style="width:130px">内部测试完成时间:</label>
			<div class="layui-input-inline">
	        	<input type="text" class="layui-input" id="internalCompleteTime" name="internalCompleteTime" placeholder="请选择测试完成时间" >
	      	</div>
	      	<label class="layui-form-label" style="width:140px">出厂测试完成时间:</label>
			<div class="layui-input-inline">
	        	<input type="text" class="layui-input" id="factoryCompleteTime" name="factoryCompleteTime" placeholder="请选择测试完成时间">
	      	</div> 	     	
		</div>
	
		<div class="layui-form-item">
			
			<label class="layui-form-label" style="width:130px">第三方测试完成时间:</label>
			<div class="layui-input-inline">
	        	<input type="text" class="layui-input" id="thirdCompleteTime" name="thirdCompleteTime" placeholder="请选择测试完成时间">
	      	</div>			
		</div>
	
		<div class="layui-form-item layui-form-text">
	    	<label class="layui-form-label" style="width:130px">备注:</label>
		    <div class="layui-input-block" >
		      <textarea class="layui-textarea" id="remark" name="remark" placeholder="请输入备注内容"  style="width:80%" data-rule="length(~256)"></textarea>
		    </div>
	  	</div>

	 <div class="layui-footer" style="margin-left: 300px;">
	  	 <button type="button" class="layui-btn" lay-submit onclick="submitProgress()" >提交</button>
	  	 <button type="button" class="layui-btn layui-btn-primary" onclick="closeWindow()">关闭</button>
	 </div>
	</form>

  </body>
<script>
//JavaScript代码区域
var basePath='<%=basePath%>'
layui.use('form', function(){
  var form = layui.form; 
  form.render(); 
});
</script>

</html>
