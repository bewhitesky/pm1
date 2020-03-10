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
	<script type="text/javascript" src="../../static/js/person/adminMgr.js"></script>
	<script type="text/javascript" src="../../static/js/person/adminOpt.js"></script>
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
<body style="padding: 20px; background-color: #F2F2F2;height:90%">
	<div class="layui-row layui-col-space10">
    	<div class="layui-col-md10">
	      	<div class="layui-card">
		        <div class="layui-card-header">当前项目：</div>
		        <div class="layui-card-body">
		        	<div class="layui-row layui-col-space10">
		        		<div class="layui-col-md6">
		        			<div class="" style="height:630px">
		        				<div class="card-body" style="">
		        					<form id="detailForm" method="post" style="display: inline-block; width: 100%;margin-top: 20px">
		        						<input type="hidden" id="id"/>
										<div class="layui-form-item">
											<label class="layui-form-label" for="pName" style="width:100px">项&nbsp;&nbsp;目&nbsp;&nbsp;名&nbsp;&nbsp;称:</label>
											<div class="layui-input-block">
												<span id="pName"></span>
											</div>
										</div>
										<div class="layui-form-item">
											<label class="layui-form-label" for="productName" style="width:100px">产&nbsp;&nbsp;品&nbsp;&nbsp;名&nbsp;&nbsp;称:</label>
											<div class="layui-input-block">
												<span id="productName"></span>
											</div>
										</div>
										<div class="layui-form-item">
											<label class="layui-form-label" for="pDepartment" style="width:100px">所&nbsp;&nbsp;属&nbsp;&nbsp;部&nbsp;&nbsp;门:</label>
											<div class="layui-input-block">
												<span id="pDepartment"></span>
											</div>
										</div>
										<div class="layui-form-item">
											<label class="layui-form-label" for="testType" style="width:100px">测&nbsp;&nbsp;试&nbsp;&nbsp;需&nbsp;&nbsp;求:</label>
											<div class="layui-input-block">
												<span id="testType"></span>
											</div>
										</div>
										<div class="layui-form-item">
											<label class="layui-form-label" for="pType" style="width:100px">项&nbsp;&nbsp;目&nbsp;&nbsp;类&nbsp;&nbsp;别:</label>
											<div class="layui-input-block">
												<span id="pType"></span>
											</div>
										</div>
										<div class="layui-form-item">
											<label class="layui-form-label" for="pLevel" style="width:100px">等&nbsp;&nbsp;保&nbsp;&nbsp;等&nbsp;&nbsp;级:</label>
											<div class="layui-input-block">
												<span id="pLevel"></span>
											</div>
										</div>
										<div class="layui-form-item">
											<label class="layui-form-label" for="pmName" style="width:100px">项&nbsp;&nbsp;目&nbsp;&nbsp;经&nbsp;&nbsp;理:</label>
											<div class="layui-input-block">
												<span id="pmName"></span>
											</div>
										</div>
										<div class="layui-form-item">
											<label class="layui-form-label" for="pmTel" style="width:100px">联&nbsp;&nbsp;系&nbsp;&nbsp;方&nbsp;&nbsp;式:</label>
											<div class="layui-input-block">
												<span id="pmTel"></span>
											</div>
										</div>
										<div class="layui-form-item" style="width: 100%">
											<label class="layui-form-label" for="history" ></label>
											<div class="layui-input-block">
												<div style="height:0px;overflow-x: hidden;overflow-y: auto;padding-top:5px">
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
			        				<form id="detailForm" method="post" style="display: inline-block; width: 100%;margin-top: 20px">
										<div class="layui-form-item">
											<label class="layui-form-label" for="internalTime" style="width:120px">计划内部测试时间:</label>
											<div class="layui-input-block">
												<span id="internalTime"></span>
											</div>
										</div>
										<div class="layui-form-item">
											<label class="layui-form-label" for="factoryTime" style="width:120px">计划出厂测试时间:</label>
											<div class="layui-input-block">
												<span id="factoryTime"></span>
											</div>
										</div>
										<div class="layui-form-item">
											<label class="layui-form-label" for="thirdTime" style="width:120px">计划三方测试时间:</label>
											<div class="layui-input-block">
												<span id="thirdTime"></span>
											</div>
										</div>
										<div class="layui-form-item">
											<label class="layui-form-label" for="demandExpTime" style="width:120px">计划需求完成时间:</label>
											<div class="layui-input-block">
												<span id="demandExpTime"></span>
											</div>
										</div>
										<div class="layui-form-item">
											<label class="layui-form-label" for="designExpTime" style="width:120px">计划概设完成时间:</label>
											<div class="layui-input-block">
												<span id="designExpTime"></span>
											</div>
										</div>
										<div class="layui-form-item" style="width: 100%">
											<label class="layui-form-label" for="history" ></label>
											<div class="layui-input-block">
												<div style="height:0px;overflow-x: hidden;overflow-y: auto;padding-top:5px">
													<ul class="layui-timeline">
													</ul>
										        </div>
											</div>
										</div>
									</form>
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
