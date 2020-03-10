<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<%
    String basePath=request.getScheme()+"://"
            +request.getServerName()+":"+request.getServerPort()
            +request.getContextPath();
%>
<head>
    <meta charset="utf-8">

    <title>重点工作跟踪</title>
</head>

<body class="easyui-layout">

    <div style="padding-top: 20px">
        <form id="imptaskForm">
            <input type="hidden" id="id">
            <div class="layui-form-item">
                <label class="layui-form-label" for="imptask_name"style="width: 120px">任务名称：</label>
                <div class="layui-input-inline">
                    <input class="easyui-validatebox layui-input" type="text" style="width:550px" id="imptask_name" name="imptask_name" data-options="required:true,missingMessage:'该字段为必填项'"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label" for="achievements" style="width: 120px">交付成果：</label>
                <div class="layui-input-inline">
                    <input class="easyui-validatebox layui-input" type="text" style="width:550px" id="achievements" name="imptask_taskcls" data-options="required:true,missingMessage:'该字段为必填项'"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label" for="imptask_taskcls" style="width: 120px">任务分类：</label>
                <div class="layui-input-inline">
                    <input class="easyui-combobox" type="text" style="width:198px" id="imptask_taskcls" name="imptask_taskcls" data-options="required:true,missingMessage:'该字段为必填项'"/>
                </div>
                <label class="layui-form-label" for="p_charge" style="width: 120px;">责任人：</label>
                <div class="layui-input-inline">
                    <input class="easyui-combobox" type="text" style="width: 198px" id="p_charge" name="p_charge" data-options="required:true,multiple:true,missingMessage:'该字段为必填项'">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label" for="p_complete_time" style="width: 120px">计划完成时间：</label>
                <div class="layui-input-inline">
                    <input type="text" class="easyui-datebox" id="p_complete_time" name="p_complete_time" data-options="formatter:dataformatter,parser:myparser,editable:false,required:true,missingMessage:'该字段为必填项'"/>
                </div>

                <label class="layui-form-label" for="imptask_state" style="width: 80px;">任务状态：</label>
                <div class="layui-input-inline" style="width: 100px">
                    <input class="easyui-combobox" type="text" style="width: 100px" id="imptask_state" name="imptask_state" data-options="required:true,missingMessage:'该字段为必填项'">
                </div>

                <label class="layui-form-label" for="percent" style="width: 85px;">完成度(%)：</label>
                <div class="layui-input-inline" style="width: 30px">
                    <input class="easyui-validatebox layui-input" type="text" style="width: 30px" id="percent" name="percent" data-options="required:true,missingMessage:'该字段为必填项'">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label" for="plan" style="width:120px">阶段计划:</label>
                <div>
                    <textarea class="easyui-validatebox layui-textarea" id="plan" name="plan" style="width:530px;height:80px"></textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label" for="explan" style="width:120px">进度说明:</label>
                <div>
                    <textarea class="easyui-validatebox layui-textarea" id="explan" name="explan" style="width:530px;height:80px"></textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label" for="remarks" style="width:120px">备注:</label>
                <div>
                    <textarea class="easyui-validatebox layui-textarea" id="remarks" name="remarks" style="width:530px;height:40px"></textarea>
                </div>
            </div>
        </form>
    </div>
    <div style="padding-left:300px">
        <button class="layui-btn layui-btn-md" onclick="submitForm()">
            提交
        </button>
        <button class="layui-btn layui-btn-primary" onclick="closeWindow()">
            关闭
        </button>
    </div>
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