<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="org.apache.shiro.SecurityUtils" %>
<!DOCTYPE html>
<html>
<%
    String basePath=request.getScheme()+"://"
            +request.getServerName()+":"+request.getServerPort()
            +request.getContextPath();
    String userName=(String) SecurityUtils.getSubject().getSession().getAttribute("userName");
%>
<head>
    <meta charset="utf-8">

    <title>周报</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" type="text/css" href="../../assets/layui/css/layui.css"/>
    <script type="text/javascript" src="../../assets/layui/layui.js"></script>
    <script type="text/javascript" src="../../assets/layui/lay/modules/form.js"></script>
    <script type="text/javascript" src="../../assets/easyui/jquery.min.js"></script>

    <link rel="stylesheet" type="text/css" href="../../assets/easyui/demo/demo.css">
    <link rel="stylesheet" type="text/css" href="../../assets/easyui/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="../../assets/easyui/icon.css">
    <script type="text/javascript" src="../../assets/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../../assets/easyui/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../../static/js/common.js"></script>

    <script type="text/javascript" src="../../static/js/weekly/weeklyMgr.js"></script>
    <script type="text/javascript" src="../../static/js/weekly/weeklyOpt.js"></script>



</head>

<body class="easyui-layout">

<div style="padding-top: 20px">
    <form id="weeklyForm">
        <input type="hidden" id="id">
        <div class="layui-form-item" style ="padding-left: 1100px">
            <button type="button" class="layui-btn" onclick="addWeeklyItems()">添加周报项</button>
            <button type="button" class="layui-btn" onclick="delWeeklyItems()">删除周报项</button>
        </div>


        <div class="easyui-layout" style="width:1400px;height:400px;">
            <div id="ImptaskDiv" region="center" style="padding:5px;" border="false">
                <table id="addweeklyTable" class="easyui-datagrid" style="width:1300px; height:320px;"></table>
            </div>
        </div>

    </form>
</div>
<div style="margin-left: 650px;margin-top:10px">
    <button class="layui-btn layui-btn-md" onclick="submitForm()">
        提交
    </button>
    <button class="layui-btn layui-btn-primary" onclick="closeWindow()">
        关闭
    </button>
</div>
</body>
<script type="text/javascript">
    var itemsList=new Array();
    var index=0;
    var basePath='<%=basePath%>';

    var userName = parent.userName;

    layui.use('form', function(){
        var form = layui.form;
    });
</script>

<script type="text/html" id = "toolbar">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script type="text/html" id = "toolType">
    <select id="w_type_one" name="w_type_one" lay-verify = "required" lay-filter="business" class="layui-form-select">
        <option value=""></option>
    </select>
</script>

</html>