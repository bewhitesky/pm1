<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
	String basePath=request.getScheme()+"://"
			+request.getServerName()+":"+request.getServerPort()
			+request.getContextPath();
%>
<%@taglib prefix="base" uri="/WEB-INF/tld/validatorTag.tld"%>
<head>
  <meta charset="utf-8">
  <title>项目维护</title>
</head>
<body style="padding-top: 20px">
  <div id="tt" class="easyui-tabs" data-options="border:false">   
    <div title="基础信息" style="padding:20px;display:none;">   
 	     <form id="addBaseInfoForm" method="post">
	    	<input type="hidden" id="id" name="id" >
		    <div class="layui-form-item">	
				<label class="layui-form-label" for="pDepartment" style="width:120px"><span style="color:red;">*</span>所属部门:</label>
				<div class="layui-input-inline">
					<input class="easyui-combobox" style="width:198px" id="pDepartment" name="pDepartment" data-options="required:true,missingMessage:'该字段为必填项'"  />
				</div>
				
				<label class="layui-form-label" for="pName" style="width:120px"><span style="color:red;">*</span>项目名称:</label>
				<div class="layui-input-inline">
					<input class="easyui-validatebox layui-input" type="text" id="pName" name="pName" data-options="required:true,missingMessage:'该字段为必填项'" />
				</div>				
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label" for="productName" style="width:120px"><span style="color:red;">*</span>产品名称:</label>
				<div class="layui-input-inline">
					<input class="easyui-validatebox layui-input" type="text" id="productName" name="productName" data-options="required:true,missingMessage:'该字段为必填项'" />
				</div>
				
				<label class="layui-form-label" for="pType" style="width:120px">项目类别:</label>
				<div class="layui-input-inline">
					<input class="easyui-combobox" style="width:198px" type="text" id="pType" name="pType" data-options="required:true,missingMessage:'该字段为必填项'" />
				</div>		
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label" for="testType" style="width:120px"><span style="color:red;">*</span>测试需求:</label>
				<div class="layui-input-inline" style="width:500px;padding-top:4px">
					<input type="checkbox" id="testType1" value="功能/非功能测试">功能/非功能
					<input type="checkbox" id="testType2" value="安全功能测试" style="margin-left: 20px">安全功能测试
					<input type="checkbox" id="testType3" value="渗透测试" style="margin-left: 20px">渗透测试
					<input type="checkbox" id="testType4" value="代码扫描" style="margin-left: 20px">代码扫描
					<input type="checkbox" id="testType5" value="性能测试" style="margin-left: 20px">性能测试
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label" for="pLevel" style="width:120px"><span style="color:red;">*</span>等保等级:</label>
				<div class="layui-input-inline">
					<input class="easyui-combobox" style="width:198px" type="text" id="pLevel" name="pLevel" data-options="required:true,missingMessage:'该字段为必填项'" />
				</div>
				
				<label class="layui-form-label" for="thirdTest" style="width:120px">第三方测试机构:</label>
				<div class="layui-input-inline">
					<input class="easyui-combobox" style="width:198px" type="text" id="thirdTest" name="thirdTest" data-options="" />
				</div>
			</div>
		
			<div class="layui-form-item">		
				<label class="layui-form-label" for="dorg" style="width:120px">研发机构:</label>
				<div class="layui-input-inline">
					<input class="easyui-combobox" style="width:198px" type="text" id="dorg" name="dorg" data-options="" />
				</div>
		      	
		      	<label class="layui-form-label" for="pmName" style="width:120px">项目经理:</label>
				<div class="layui-input-inline">
					<input class="easyui-validatebox layui-input" type="text" id="pmName" name="pmName" data-options="" />
				</div>		
			</div>
			
			<div class="layui-form-item">
		      	<label class="layui-form-label" for="pmTel" style="width:120px">联系电话:</label>
				<div class="layui-input-inline">
					<input class="easyui-validatebox layui-input" type="text" id="pmTel" name="pmTel" data-options="" />
				</div>
		      	
		      	<label class="layui-form-label" for="pmEmail" style="width:120px">邮箱地址:</label>
				<div class="layui-input-inline">
					<input class="easyui-validatebox layui-input" type="text" id="pmEmail" name="pmEmail" data-options="" />
				</div>      	
			</div>
			
		  	<div class="layui-form-item">
				<label class="layui-form-label" for="remark" style="width:120px">计划备注:</label>
				<div>
					<textarea class="easyui-validatebox layui-textarea" id="remark" name="remark" style="width:530px;height:80px"></textarea>
				</div>
	    	</div>
		</form>
		<div class="layui-footer" style="margin-left: 300px">
			 <button type="button" class="layui-btn"  onclick="nextStep()" >下一步</button>
		  	 <button type="button" class="layui-btn layui-btn-primary" onclick="closeWindow()">关闭</button>
	 	</div>     
    </div>   
    
    <div title="时间节点"  data-options="disabled:true" style="padding:20px;display:none;height:600px">   
    	<form id="addBaseInfoForm2" method="post">
		    <div class="layui-form-item">	
				<label class="layui-form-label" style="width:120px">计划需求完成时间:</label>
				<div class="layui-input-inline">
		        	<input type="text" class="layui-input" id="demandExpTime" name="demandExpTime" placeholder="请选择计划需求完成时间"
		        	 cz-label="计划需求完成时间">
		        	<div class="msg-div" id="demandExpTimeMsg">
		        		<span></span>
		        	</div>
		      	</div>
				
				<label class="layui-form-label" style="width:120px">实际需求完成时间:</label>
				<div class="layui-input-inline">
		        	<input type="text" class="layui-input" id="demandActTime" name="demandActTime" placeholder="请选择实际需求完成时间"
		        	 cz-label="实际需求完成时间">
		        	<div class="msg-div" id="demandActTimeMsg">
		        		<span></span>
		        	</div>
		      	</div>			
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label" style="width:120px">计划概设完成时间:</label>
				<div class="layui-input-inline">
		        	<input type="text" class="layui-input" id="designExpTime" name="designExpTime" placeholder="请选择计划概设完成时间"
		        	 cz-label="计划概设完成时间">
		        	<div class="msg-div" id="demandExpTimeMsg">
		        		<span></span>
		        	</div>
		      	</div>
				
				<label class="layui-form-label" style="width:120px">实际概设完成时间:</label>
				<div class="layui-input-inline">
		        	<input type="text" class="layui-input" id="designActTime" name="designActTime" placeholder="请选择实际概设完成时间"
		        	 cz-label="实际概设完成时间">
		        	<div class="msg-div" id="demandActTimeMsg">
		        		<span></span>
		        	</div>
		      	</div>		
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label" for="testYear" style="width:120px">测试年度:</label>
				<div class="layui-input-inline">
					<input class="easyui-validatebox layui-input" type="text" id="testYear" name="testYear" data-options="" />
				</div>
				
				<label class="layui-form-label" style="width:120px">内部测试时间:</label>
				<div class="layui-input-inline">
		        	<input type="text" class="layui-input" id="internalTime" name="internalTime" placeholder="请选择计划内部测试时间"
		        	 cz-label="内部测试时间">
		        	<div class="msg-div" id="internalTimeMsg">
		        		<span></span>
		        	</div>
		      	</div>
			</div>
		
			<div class="layui-form-item">		
				<label class="layui-form-label" style="width:120px">出厂测试时间:</label>
				<div class="layui-input-inline">
		        	<input type="text" class="layui-input" id="factoryTime" name="factoryTime" placeholder="请选择计划出厂测试时间"
		        	 cz-label="出厂测试时间">
		        	<div class="msg-div" id="factoryTimeMsg">
		        		<span></span>
		        	</div>
		      	</div>
		      	
		      	<label class="layui-form-label" style="width:120px">第三方测试时间:</label>
				<div class="layui-input-inline">
		        	<input type="text" class="layui-input" id="thirdTime" name="thirdTime" placeholder="请选择计划第三方测试时间"
		        	 cz-label="第三方测试时间">
		        	<div class="msg-div" id="thirdTimeMsg">
		        		<span></span>
		        	</div>
		      	</div>  			
			</div>
			
		  	<div class="layui-form-item">		
	    	</div>
		</form>
		<div class="layui-footer" style="margin-left: 250px">
			 <button type="button" class="layui-btn"  onclick="preStep()" >上一步</button>
		  	 <button type="button" class="layui-btn"  onclick="submitBaseInfo()" >提交</button>
		  	 <button type="button" class="layui-btn layui-btn-primary" onclick="closeWindow()">关闭</button>
	 	</div>
    </div>      
</div>
</body>
<script>
var basePath='<%=basePath%>';
</script>
</html>