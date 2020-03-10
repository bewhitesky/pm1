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
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  	<link rel="stylesheet" type="text/css" href="../../assets/easyui/demo/demo.css">
  	
	<link rel="stylesheet" type="text/css" href="../../assets/easyui/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="../../assets/easyui/icon.css">
    <script type="text/javascript" src="../../assets/layui/laydate.js"></script>
    <script type="text/javascript" src="../../assets/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="../../assets/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../../assets/easyui/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="../../assets/layui/layer.js"></script>
	<script type="text/javascript" src="../../assets/easyui/validator.js"></script>
	<script type="text/javascript" src="../../assets/easyui/datagrid-detailview.js"></script>
	<script type="text/javascript" src="../../assets/easyui/doCellTip.js"></script>
	<script type="text/javascript" src="../../static/js/common.js"></script>
	<script type="text/javascript" src="../../static/js/task/taskMgr.js"></script>
	<script type="text/javascript" src="../../static/js/task/taskOpt.js"></script>
	
</head>
<body class="easyui-layout" >
<div>
<form id="searchForm">
    <div>		
		<label style="padding-left:10px">预计开始时间:</label>
		<div class="layui-input-inline">
			<input type="text" class="layui-input" id="expectStartTime" placeholder="开始时间 ~ 结束时间">
		</div>
		
		<label style="padding-left:25px">预计结束时间:</label>
		<div class="layui-input-inline">
			<input type="text" class="layui-input" id="deadline" placeholder="开始时间 ~ 结束时间">	
		</div>
		
		<label style="padding-left:25px">&nbsp;&nbsp;&nbsp;实际开始时间:</label>
		<div class="layui-input-inline">
			<input type="text" class="layui-input" id="actualStartTime" placeholder="开始时间 ~ 结束时间">	
		</div>

		
		<label style="padding-left:25px">&nbsp;&nbsp;&nbsp;实际结束时间:</label>
		<div class="layui-input-inline">
			<input type="text" class="layui-input" id="actualEndTime" placeholder="开始时间 ~ 结束时间">	
		</div>
				
		<shiro:hasRole name="admin">
		<label style="padding-left:25px">&nbsp;&nbsp;&nbsp;测试负责人:</label>
		<div class="layui-input-inline">
			<input class="easyui-combobox"  style="width:173px" id="executor" name="executor"/>
		</div>
		</shiro:hasRole>
    </div>
    <div  style="padding-top:10px">
		<label style="padding-left:10px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;任务名称:</label>
		<div class="layui-input-inline">
			<input class="easyui-validatebox layui-input" type="text" id="taskName" name="taskName"/>
		</div>

		<label style="padding-left:25px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前状态:</label>
		<div class="layui-input-inline">
			<input class="easyui-combobox"  style="width:173px" id="taskState" name="taskState"/>
		</div>
		<label style="padding-left:25px">计划测试月份:</label>
		<div class="layui-input-inline">
		<input class="easyui-datebox" style="width:173px" type="text" id="expectMonth" name="expectMonth" />
		</div>
				
		<label style="padding-left:25px">实际测试月份:</label>
		<div class="layui-input-inline">
		<input class="easyui-datebox" style="width:173px" type="text" id="actualMonth" name="actualMonth" />
		</div>
		
		<button type="button" class="layui-btn layui-btn-md"  style="margin-left:20px" onclick="searchTask()">
		    搜索
		</button>
		<button type="button" class="layui-btn layui-btn-md" style="margin-left:10px" onclick="resetForm()">
	            重置
	  	</button>
    </div>
</form>
</div>
<hr class="layout-search-hr-green" style="margin-top: 10px">
<shiro:hasRole name="admin">
	<div id="toolbar" class="layui-btn-group">
		<button class="layui-btn layui-btn-md" onclick="addTask()">
			 添加主任务
		</button>
		<button class="layui-btn layui-btn-md" onclick="exportTask()">
			 导出
		</button>
		<button class="layui-btn layui-btn-md" onclick="exportWork()">
			 导出报工数据
		</button>
	</div>
</shiro:hasRole>
<div class="easyui-layout" style="width:100%;height:540px;">
	<div id="taskDiv" region="center" style="padding:5px;" border="false">
		<table id="taskTable" class="easyui-treegrid" style="height:480px;"></table>
	</div>
</div>
<div id="w"></div>
</body>
<script type="text/javascript">
var basePath='<%=basePath%>';
var userName= parent.userName;
if(userName=="系统管理员"){
	userName="";
}
var id="";

function taskButton(task){
    var executor=task.executor;
    var level=task.level;
    var currid=task.id;
    var pid=task.pid;
    task=JSON.stringify(task);
    if(userName==""){//如果是系统管理员
    	if(level==3){
    		return "<div>" +
				"<button class='layui-btn layui-btn-xs' style='margin:5px' onclick='updateTask("+task+")'>更新状态</button>"+
				"<button class='layui-btn layui-btn-danger layui-btn-xs' style='margin:5px' onclick='deleteTask("+task+")'>删除任务</button>" +
			"</div>";
    	}
    	else{
    	    return "<div>" +
			"<button class='layui-btn layui-btn-xs' style='margin:5px' onclick='updateTask("+task+")'>更新状态</button>"+
			"<button class='layui-btn layui-btn-danger layui-btn-xs' style='margin:5px' onclick='deleteTask("+task+")'>删除任务</button>" +
			"</div>";
    	}
    }else if(executor==userName){
    	if(level==1){
    	    id=currid;
    		return "<div>" +
			"<button class='layui-btn layui-btn-xs' style='margin:5px' onclick='updateTask("+task+")'>更新状态</button>"+
			"<button class='layui-btn layui-btn-danger layui-btn-xs' style='margin:5px' onclick='deleteTask("+task+")'>删除任务</button>" +
			"</div>"; 
    	}else if(level==2){
    		return "<div>" +
			"<button class='layui-btn layui-btn-xs' style='margin:5px' onclick='updateTask("+task+")'>更新状态</button>"+
			"<button class='layui-btn layui-btn-danger layui-btn-xs' style='margin:5px' onclick='deleteTask("+task+")'>删除任务</button>" +
			"</div>"; 
    	}else if(level==3){
    		return "<div>" +
			"<button class='layui-btn layui-btn-xs' style='margin:5px' onclick='updateTask("+task+")'>更新状态</button>"+
			"<button class='layui-btn layui-btn-danger layui-btn-xs' style='margin:5px' onclick='deleteTask("+task+")'>删除任务</button>" +
			"</div>"; 
    	}
    }else if(executor!=userName){
        if(level==1){
            id="";
        	return "";
        }else if(level==2 && pid==id){
			return "<div>" +
			"<button class='layui-btn layui-btn-xs' style='margin:5px' onclick='updateTask("+task+")'>更新状态</button>"+
			"<button class='layui-btn layui-btn-danger layui-btn-xs' style='margin:5px' onclick='deleteTask("+task+")'>删除任务</button>" +
			"</div>"; 
		}else if(level==3){
			return "<div>" +
			"<button class='layui-btn layui-btn-xs' style='margin:5px' onclick='updateTask("+task+")'>更新状态</button>"+
			"<button class='layui-btn layui-btn-danger layui-btn-xs' style='margin:5px' onclick='deleteTask("+task+")'>删除任务</button>" +
			"</div>"; 
		}

    }
}

laydate.render({ 
	elem: '#expectStartTime'
	,range: '~' //或 range: '~' 来自定义分割字符
});
laydate.render({ 
	elem: '#deadline'
	,range: '~' //或 range: '~' 来自定义分割字符
});
laydate.render({ 
	elem: '#actualStartTime'
	,range: '~' //或 range: '~' 来自定义分割字符
});
laydate.render({ 
	elem: '#actualEndTime'
	,range: '~' //或 range: '~' 来自定义分割字符
});

</script>
</html>
