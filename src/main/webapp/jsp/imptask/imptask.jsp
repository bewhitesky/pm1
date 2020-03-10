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
    <title></title>
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

    <script type="text/javascript" src="../../static/js/imptask/imptaskMgr.js"></script>
    <script type="text/javascript" src="../../static/js/imptask/imptaskOpt.js"></script>
</head>
<body class="easyui-layout" >
<div>
    <form id="searchForm">
        <div>
            <label style="padding-left: 10px">任务名称：</label>
            <div class="layui-input-inline">
                <input class="easyui-validatebox layui-input" type="text" id="imptask_name" name="imptask_name"/>
            </div>
            <label style="padding-left: 25px">任务类别：</label>
            <div class="layui-input-inline">
                <input class="easyui-combobox" type="text" id="imptask_taskcls" name="imptask_taskcls"/>
            </div>
            <label style="padding-left:35px">计划完成时间：</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" id="p_complete_time" name="p_complete_time" placeholder="计划完成时间" />
            </div>
            <button type="button" class="layui-btn layui-btn-md" style="margin-left:10px" onclick="selectImpTaskTable()">
                搜索
            </button>
            <button type="button" class="layui-btn layui-btn-md" style="margin-left: 10px" onclick="resetForm()">
                重置
            </button>
        </div>
    </form>
</div>
<div id="toolbar" class="layui-btn-group">
    <button class="layui-btn layui-btn-md" onclick="addImpTask()">
        新增
    </button>
    <button class="layui-btn layui-btn-md" onclick="updateImpTask()">
        修改
    </button>
    <button class="layui-btn layui-btn-md" onclick="deleteImpTask()">
        删除
    </button>
    <button class="layui-btn layui-btn-md" onclick="exportImgTask()">
        导出
    </button>
    <button class="layui-btn layui-btn-md" id="importExcel">
        导入
    </button>
</div>

<div class="easyui-layout" style="width:100%;height:540px;">
    <div id="ImptaskDiv" region="center" style="padding:5px;" border="false">
        <table id="impTaskTable" class="easyui-datagrid" style="height:480px;"></table>
    </div>
</div>
<div id="w"></div>
</body>
<script type="text/javascript">
    var basePath='<%=basePath%>';

    layui.use('laydate', function(){
        var laydate = layui.laydate;
        laydate.render({
            elem: '#p_complete_time'
        });
    });


</script>
</html>
