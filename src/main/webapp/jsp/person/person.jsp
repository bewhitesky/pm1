<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
  	<link rel="stylesheet" type="text/css" href="../../assets/easyui/demo/demo.css"/>
	<link rel="stylesheet" type="text/css" href="../../assets/easyui/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="../../assets/easyui/icon.css">
    <script type="text/javascript" src="../../assets/layui/laydate.js"></script>
    <script type="text/javascript" src="../../assets/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="../../assets/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../../assets/easyui/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="../../assets/layui/layer.js"></script>
	<script type="text/javascript" src="../../assets/easyui/validator.js"></script>
	<script type="text/javascript" src="../../assets/easyui/doCellTip.js"></script>
	<script type="text/javascript" src="../../static/js/common.js"></script>
	<script type="text/javascript" src="../../static/js/person/personMgr.js"></script>
	<script type="text/javascript" src="../../static/js/person/personOpt.js"></script>
	<style>
		.layui-form-label {
			padding-bottom: 20px
		}
		textarea{
			font-size:14px; 
			font-family:Helvetica Neue, Helvetica,PingFang SC,Tahoma,Arial,sans-serif
		}
		.today-li{
			margin-top:2px;
			border-bottom: solid 1px #f6f6f6;
		    list-style: none;
		    white-space: pre-line;
		}
		span{
			line-height: 30px;
		}
		.card-body{
			border:solid 1px #ddd;
		}
		.datagrid-wrap{
			border:none;
		}
	</style>
</head>
<body style="padding: 20px; background-color: #F2F2F2;">
	<div class="layui-row layui-col-space10">
    	<div class="layui-col-md10">
	      	<div class="layui-card">
		        <div class="layui-card-header">当前任务：</div>
		        <div class="layui-card-body">
		        	<div class="layui-row layui-col-space10">
		        		<div class="layui-col-md6">
		        			<div class="" style="height:630px">
		        				<div class="card-body" style="">
		        					<form id="detailForm" method="post" style="display: inline-block; width: 100%;margin-top: 20px">
		        						<input type="hidden" id="id"/>
										<input type="hidden" id="pId"/>
										<input type="hidden" id="level"/>
										<input type="hidden" id="taskState"/>
										<input type="hidden" id="testStage"/>
										<div class="layui-form-item">
											<label class="layui-form-label" for="taskName" >任务名称:</label>
											<div class="layui-input-block">
												<span id="taskName"></span>
											</div>
										</div>
										<div class="layui-form-item">
											<label class="layui-form-label" for="expectTime" >计划时间:</label>
											<div class="layui-input-block">
												<span id="expectTime"></span>
											</div>
										</div>
										<div class="layui-form-item">
											<label class="layui-form-label" for="testStage" >测试阶段:</label>
											<div class="layui-input-block">
												<span id="testStage"></span>
											</div>
										</div>
										<div class="layui-form-item" style="width: 100%">
											<label class="layui-form-label" for="history" >历史进度:</label>
											<div class="layui-input-block">
												<div style="height:430px;overflow-x: hidden;overflow-y: auto;padding-top:5px">
													<ul class="layui-timeline">
													</ul>
										        </div>
											</div>
										</div>
									</form>
		        				</div>

		        			</div>
		        		</div>
		        		<div class="layui-col-md6">
		        			<div class="" style="height:630px">
			        			<div class="card-body">
			        				<table id="subTask" class="easyui-datagrid" style="width:100%;height:624px"></table>
			        			</div>

		        			</div>
		        		</div>
		        	</div>
		        </div>
	        </div>
	    </div>
	    <div class="layui-col-md2">
	    	<div class="layui-row" style="height:270px">
	    		<div class="layui-card">
		        <div class="layui-card-body" style="height:250px">
		        	<div id="cc" style="width:100%;height:230px;margin-top: 10px"></div> 
		        </div>
	        </div>
	        
	    	</div>
	    	<div class="layui-row" style="height:380px;margin-top: 10px">
	    		<div class="layui-card">
		        <div class="layui-card-header">今日日程安排：</div>
		        <div class="layui-card-body" style="height:350px">
		        	<ul id="schedule">
		        	</ul>
		        </div>
	        </div>
	    	</div>
	    </div>
	</div>
	<div id="w"></div>
</body>
<script type="text/javascript">
var basePath='<%=basePath%>';
var userName= parent.userName;
</script>
</html>
