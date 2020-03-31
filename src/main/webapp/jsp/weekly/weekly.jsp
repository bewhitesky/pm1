<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>

<%
    String basePath= request.getScheme()+"://"
                    + request.getServerName()+":"+request.getServerPort()
                    + request.getContextPath();
%>

<head>
    <meta charset="utf-8">
    <title>周报</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" type="text/css" href="../../assets/easyui/demo/demo.css">
    <link rel="stylesheet" type="text/css" href="../../assets/easyui/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="../../assets/easyui/icon.css">
    <script type="text/javascript" src="../../assets/layui/layui.js"></script>
    <script type="text/javascript" src="../../assets/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="../../assets/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../../assets/easyui/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../../assets/easyui/validator.js"></script>
    <script type="text/javascript" src="../../assets/easyui/doCellTip.js"></script>
    <script type="text/javascript" src="../../static/js/common.js"></script>

    <script type="text/javascript" src="../../static/js/weekly/weeklyMgr.js"></script>
    <script type="text/javascript" src="../../static/js/weekly/weeklyOpt.js"></script>
</head>

<body class="easyui-layout">
<div>
    <form id="searchForm">
        <div>
            <shiro:hasRole name="admin">
            <label style="padding-left: 10px">姓名：</label>
            <div class="layui-input-inline">
                <input class="easyui-validatebox layui-input" type="text" id="w_name" name="w_name">
            </div>
            </shiro:hasRole>

            <label style="padding-left: 25px">类别：</label>
            <div class="layui-input-inline">
                <input class="easyui-combobox" type="text" id="w_type" name="w_type">
            </div>
            <label style="padding-left: 35px">时间：</label>
            <div class="layui-input-inline">
                <input class="layui-input" type="text" id="time" name="time" placeholder="开始时间~结束时间">
            </div>


            <button type="button" class="layui-btn layui-btn-md" style="margin-left:10px" onclick="selectWeekly()">
                搜索
            </button>
            <button type="button" class="layui-btn layui-btn-md" style="margin-left: 10px" onclick="resetForm()">
                重置
            </button>
        </div>
    </form>
</div>
<div id="toolbar" class="layui-btn-group">
    <button class="layui-btn layui-btn-md" onclick="addWeekly()">
        新增
    </button>
    <button class="layui-btn layui-btn-md" onclick="updateWeekly()">
        修改
    </button>
    <button class="layui-btn layui-btn-md" onclick="deleteWeekly()">
        删除
    </button>
    <button class="layui-btn layui-btn-md" onclick="exportWeekly()">
        导出
    </button>
    <button class="layui-btn layui-btn-md" id="importExcel">
        导入
    </button>
</div>

<div class="easyui-layout" style="width: 100%;height: 600px">
    <div id="WeeklyDiv" region="center" style="padding:5px;" border = "false">
        <table id="WeeklyTable" class="easyui-datagrid" style="height:540px"></table>
    </div>
</div>

<div id="w"></div>

</body>

<script type="text/javascript">
    var basePath = '<%=basePath%>';
    var userName = parent.userName;


    layui.use('laydate', function(){
        var laydate = layui.laydate;
        laydate.render({
            elem: '#time',
            range:'~'
        });
    });


</script>