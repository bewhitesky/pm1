<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>项目维护1111111111111111111112222</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="stylesheet" type="text/css" href="../../assets/layui/css/layui.css"/>
	<script type="text/javascript" src="../../assets/layui/layui.js"></script>
	<script type="text/javascript" src="../../assets/layui/lay/modules/form.js"></script>
	<script type="text/javascript" src='../../assets/jquery/jquery.min.js'></script>
	<script type="text/javascript" src='../../assets/jquery/jquery.validator.min.js?local=zh-CN'></script>
	<script type="text/javascript" src="../../static/js/common.js"></script>
  <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head> 
  <body style="padding-top: 20px">
    <form class="layui-form" id="addBaseInfoForm" data-validator-option="{timely:3}">
	    <div class="layui-form-item">
		    <input type="hidden" id="id" name="id" >
		    <label class="layui-form-label" style="width:120px"><span style="color:red;">*</span>所属部门:</label>
			<div class="layui-input-inline">
				<select id="pDepartment" name="pDepartment" class="layui-select" style="width:190px" lay-filter="pDepartment" 
				cz-rule="required" cz-label="所属部门">
				</select>		
				<div class="msg-div" id="pDepartmentMsg">
					<span></span>
				</div>
			</div>
			<label class="layui-form-label" style="width:120px"><span style="color:red;">*</span>项目名称:</label>
			<div class="layui-input-inline">
				<input type="text" id="pName" name="pName" class="layui-input" placeholder="请输入项目名称"
				cz-rule="required,length(1~128)" cz-label="项目名称">
				<div class="msg-div" id="pNameMsg">
					<span></span>
				</div>	
			</div>			
		</div>
		<div class="layui-form-item">	
			<label class="layui-form-label" style="width:120px"><span style="color:red;">*</span>产品名称:</label>		
			<div class="layui-input-inline">
				<input type="text" id="productName" name="productName" autocomplete="off" class="layui-input" placeholder="请输入产品名称" 
				cz-rule="required,length(1~128)" cz-label="产品名称">
				<div class="msg-div" id="productNameMsg">
					<span></span>
				</div>	
			</div>

			<label class="layui-form-label" style="width:120px"><span style="color:red;">*</span>项目类别:</label>
			<div class="layui-input-inline">
				<select id="pType" name="pType" class="layui-select" style="width:190px" lay-filter="pType"
				cz-rule="required" cz-label="项目类别">
				</select>
				<div class="msg-div" id="pTypeMsg">
					<span></span>
				</div>
			</div>
			
		</div>
		
		<div class="layui-form-item">	
			<label class="layui-form-label" style="width:120px"><span style="color:red;">*</span>测试需求:</label>
			<div class="layui-input-inline">
				<select id="testType" name="testType" class="layui-select" style="width:190px" lay-filter="testType"
				cz-rule="required" cz-label="测试需求">
				</select>
				<div class="msg-div" id="testTypeMsg">
					<span></span>
				</div>
			</div>
					
			<label class="layui-form-label" style="width:120px">代码检测:</label>	
			<div class="layui-input-inline">
				<select id="codeCheck" name="codeCheck" class="layui-select" style="width:190px" lay-filter="codeCheck">
					<option value="0">是</option>
					<option value="1" selected>否</option>
				</select>
				<div class="msg-div" id="codeCheckMsg">
					<span></span>
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label" style="width:120px"><span style="color:red;">*</span>等保等级:</label>
			<div class="layui-input-inline">
				<select id="pLevel" name="pLevel" class="layui-select" style="width:190px" lay-filter="pLevel"
				cz-rule="required" cz-label="等保等级">
				</select>
				<div class="msg-div" id="pLevelMsg">
					<span></span>
				</div>
			</div>
			
			<label class="layui-form-label" style="width:120px">测试年度:</label>
			<div class="layui-input-inline">
	        	<input type="text" class="layui-input" id="testYear" name="testYear" placeholder="请输入测试年度" 
	        	cz-rule="number,length(0~4)" cz-label="测试年度">
	        	<div class="msg-div" id="testYearMsg">
	        		<span></span>
	        	</div>	
	      	</div>
	      	

		</div>
		<div class="layui-form-item">
			
			<label class="layui-form-label" style="width:120px">内部测试时间:</label>
			<div class="layui-input-inline">
	        	<input type="text" class="layui-input" id="internalTime" name="internalTime" placeholder="请选择计划内部测试时间"
	        	 cz-label="内部测试时间">
	        	<div class="msg-div" id="internalTimeMsg">
	        		<span></span>
	        	</div>
	      	</div>
			
			<label class="layui-form-label" style="width:120px">出厂测试时间:</label>
			<div class="layui-input-inline">
	        	<input type="text" class="layui-input" id="factoryTime" name="factoryTime" placeholder="请选择计划出厂测试时间"
	        	 cz-label="出厂测试时间">
	        	<div class="msg-div" id="factoryTimeMsg">
	        		<span></span>
	        	</div>
	      	</div>
	      	     	
		</div>
	
		<div class="layui-form-item">
			<label class="layui-form-label" style="width:120px">第三方测试时间:</label>
			<div class="layui-input-inline">
	        	<input type="text" class="layui-input" id="thirdTime" name="thirdTime" placeholder="请选择计划第三方测试时间"
	        	 cz-label="第三方测试时间">
	        	<div class="msg-div" id="thirdTimeMsg">
	        		<span></span>
	        	</div>
	      	</div>
	      	<label class="layui-form-label" style="width:120px">第三方测试机构:</label>
			<div class="layui-input-inline">
				<select id="thirdTest" name="thirdTest" class="layui-select" style="width:190px" lay-filter="thirdTest" 
				 cz-label="第三方测试机构">
				</select>		
				<div class="msg-div" id="thirdTestMsg">
					<span></span>
				</div>
			</div>
			
		</div>
		
		<div class="layui-form-item">			
	      	<label class="layui-form-label" style="width:120px">研发机构:</label>
			<div class="layui-input-inline">
				<select id="dorg" name="dorg" class="layui-select" style="width:190px" lay-filter="thirdTest" 
				 cz-label="研发机构">
				</select>		
				<div class="msg-div" id="thirdTestMsg">
					<span></span>
				</div>
			</div> 
			<label class="layui-form-label" style="width:120px">项目经理:</label>
			<div class="layui-input-inline">
	        	<input type="text" class="layui-input" id="pmName" name="pmName" placeholder="请输入项目经理姓名" 	        	
	        	cz-rule="length(0~8)" cz-label="项目经理">
	        	<div class="msg-div" id="pmNameMsg">
	        		<span></span>
	        	</div>
	      	</div>
	      				
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label" style="width:120px">联系电话:</label>
			<div class="layui-input-inline">
	        	<input type="text" class="layui-input" id="pmTel" name="pmTel" placeholder="请输入项目经理联系电话" 
	        	cz-rule="phone,length(0~11)">
				<div class="msg-div" id="pmTelMsg">
					<span></span>
				</div>
	      	</div>	      	
			<label class="layui-form-label" style="width:120px">邮箱地址:</label>
			<div class="layui-input-inline">
	        	<input type="text" class="layui-input" id="pmEmail" name="pmEmail" placeholder="请输入项目经理邮箱地址" 
	        	cz-rule="email,length(0~32)">
	        	<div class="msg-div" id="pmEmailMsg">
	        		<span></span>
	        	</div>	
	      	</div>
	      	
		</div>
		
		<div class="layui-form-item">
			 	
		</div>
	
		<div class="layui-form-item layui-form-text">
	    	<label class="layui-form-label" style="width:120px">计划备注:</label>
		    <div class="layui-input-block" >
			    <textarea class="layui-textarea" id="remark" name="remark" placeholder="请输入备注内容"  style="width:78%"></textarea>
			    <div class="msg-div" id="pmEmailMsg">
		        	<span></span>
		        </div>
			</div>
	  	</div>
	<button id="validate" type="button" onclick="validateForm('addBaseInfoForm')" style="display: none"></button>
	 <div class="layui-footer" style="margin-left: 300px">
	  	 <button type="button" class="layui-btn"  onclick="submitBaseInfo()" >提交</button>
	  	 <button type="button" class="layui-btn layui-btn-primary" onclick="closeWindow()">关闭</button>
	 </div>
	</form>

  </body>
  <script type="text/javascript" src="../../static/js/baseInfo/baseInfoOpt.js"></script>
  <script type="text/javascript" src="../../static/js/validator/cz-validator.js"></script>
  <link rel="stylesheet" type="text/css" href="../../static/js/validator/cz-validator.css"/>
<script>
//JavaScript代码区域
var basePath='<%=basePath%>';
var form;
layui.use('form', function(){
   form= layui.form; 
   form.render(); 
});
</script>

</html>
