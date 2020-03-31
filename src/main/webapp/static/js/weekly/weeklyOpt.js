
var editIndex = undefined;

var buttons = $.extend([], $.fn.datebox.defaults.buttons);
var submitFlag=0;

$(document).ready(function () {

    importWeekly();


    buttons.splice(1, 0, {
        text: '清除',
        handler: function(target){
            $(target).datebox('clear');
            $(target).datebox('hidePanel');
        }
    });
})

function addWeekly() {
    openWindow({
        title: "周报",
        href: basePath + '/jsp/weekly/addWeekly.jsp',
        width: 1500,
        height: 600,
        onLoad: function () {

            var w_typeMap_one = initW_type('w_type')

            $('#addweeklyTable').datagrid({
                columns: [[
                    {
                        field: 'time',
                        title: '日期',
                        width: 120,
                        align: 'center',
                        editor:{type:'datebox'
                        }
                    }, {
                        field: 'last_work_sketch',
                        title: '上周工作简述',
                        width: 400,
                        align: 'center',
                        editor:{type:'text'}
                    },
                     {
                        field: 'w_type',
                        title: '类别',
                        width: 100,
                        align: 'center',
                        editor:{type:'combobox',
                            options:{
                                valueField:'dataitemCode',
                                textField:'dataitemName',
                                data: w_typeMap_one,
                                editable:false
                            }
                        },
                         formatter:function (value,row,index) {
                             if(value==null)return "";
                             return w_typeMap[value];
                         }

                    }, {
                        field: 'u_time',
                        title: '耗时',
                        width: 100,
                        align: 'center',
                        editor:{type:'text'}
                    }, {
                        field: 'this_work_sketch',
                        title: '本周工作计划',
                        width: 400,
                        align: 'center',
                        editor:{type:'text'}
                    }, {
                        field: 'remarks',
                        title: '备注',
                        width: 268,
                        align: 'center',
                        editor:{type:'text'}
                    },
                ]],
                onLoadSuccess: function (data) {
                    if (data.total == 0) {
                        var body = $(this).data().datagrid.dc.body2;
                        var width = body.width();

                        body.find('table tbody').append(
                            '<tr>center>' +
                            '<td width = ' + width + ' ' +
                            'style="height: 25px; text-align:center;color:#999;border: 0px solid ;" ' +
                            'colspan=' + 9 + '>无数据!</td></center></tr>');
                    }
                },
                width: 'auto',
                singleSelect:true,
                nowrap:false,
                onClickRow: onClickRow
            });

            //自定义分页栏
            var pager = $('#addweeklyTable').datagrid('getPager');
            pager.pagination({
                beforePageText: '第',
                afterPageText: '/ {pages} 页',
                displayMsg: '当前 {from} - {to} 条记录, 共 {total} 条记录'
            });
            $('#addweeklyTable').datagrid('doCellTip', {cls: {'background-color': '#ffffff'}, delay: 100});
        }
    })
}

function addWeeklyItems() {
    if (endEditing()){
        $('#addweeklyTable').datagrid('appendRow',{status:'P'});
        editIndex = $('#addweeklyTable').datagrid('getRows').length-1;
        $('#addweeklyTable').datagrid('selectRow', editIndex)
            .datagrid('beginEdit', editIndex);
    }
}

function delWeeklyItems() {
    var datas=$("#addweeklyTable").datagrid("getSelections");
    if(datas.length==0){
        $.messager.alert("警告","请至少选中一项删除！");
        return;
    }
    $.messager.confirm('确认','确认要删除选中记录吗？',function(r){
        if(r){
            if (editIndex == undefined){return}
            $('#addweeklyTable').datagrid('cancelEdit', editIndex)
                .datagrid('deleteRow', editIndex);
            editIndex = undefined;
        }
    })
}

function onClickRow(index){
    if (editIndex != index){
        if (endEditing()){
            $('#addweeklyTable').datagrid('selectRow', index)
                .datagrid('beginEdit', index);
            editIndex = index;
        } else {
            $('#addweeklyTable').datagrid('selectRow', editIndex);
        }
    }
}


function endEditing(){
    if (editIndex == undefined){return true}
    if ($('#addweeklyTable').datagrid('validateRow', editIndex)){
        $('#addweeklyTable').datagrid('endEdit', editIndex);
        editIndex = undefined;
        return true;
    } else {
        return false;
    }
}

function submitForm() {
    if (endEditing()){
        $('#addweeklyTable').datagrid('acceptChanges');
    }

    var rows = $('#addweeklyTable').datagrid('getRows');
    var weeklyLists=[];
    for(i=0; i<rows.length;i++){
        var weeklyList = new Object();
        weeklyList.time=rows[i].time;
        weeklyList.w_type=rows[i].w_type;
        weeklyList.last_work_sketch= rows[i].last_work_sketch;
        weeklyList.u_time=rows[i].u_time;
        weeklyList.this_work_sketch=rows[i].this_work_sketch;
        weeklyList.remarks= rows[i].remarks;
        weeklyLists.push(weeklyList)
    }
    var id=$("#id").val();


    var data = {
        'id':id,
        'w_name':userName,
        'weeklyLists':weeklyLists
    }

    $.ajax({
        url:basePath+'/WeeklyController/addWeeklyItem',
        type:"POST",
        async:false,
        dataType:"json",
        contentType:"application/json",
        data:JSON.stringify(data),
        success:function(data){
            if(data.mes =="success"){
                selectWeekly();
                messageBox(data);
                closeWindow();
            }
        }
    });
}



function updateWeekly() {
        var datas=$("#WeeklyTable").datagrid("getSelections");
        if(datas.length>1){
            layer.alert('只允许对一行数据进行修改!', {icon: 0,title:"警告",btnAlign: 'c'});
            return;
        }
        if(datas.length==0){
            layer.alert('请勾选一条数据!', {icon: 0,title:"警告",btnAlign: 'c'});
            return;
        }
        var id=datas[0].id;
        var w_name=datas[0].w_name;
        var time=datas[0].time;
        var last_work_sketch=datas[0].last_work_sketch;
        var w_type=datas[0].w_type;
        var u_time=datas[0].u_time;
        var this_work_sketch=datas[0].this_work_sketch;
        var remarks=datas[0].remarks;

        openWindow({
            title:"更新进度跟踪日报",
            href:basePath+'/jsp/weekly/updateWeekly.jsp',
            width:800,
            height:600,
            modal:true,
            onLoad:function(){
                initCombo("w_type","updateWeekForm #w_type");

                $("#updateWeekForm").form('disableValidation');
                $("#updateWeekForm #id").val(id);
                $("#updateWeekForm #w_name").val(w_name);
                $("#updateWeekForm #last_work_sketch").val(last_work_sketch);
                $("#updateWeekForm #w_type").combobox('setValue',w_type);
                $("#updateWeekForm #u_time").val(u_time);
                $("#updateWeekForm #this_work_sketch").val(this_work_sketch);
                $("#updateWeekForm #remarks").val(remarks);
                $('#updateWeekForm #time').datebox({
                    buttons: buttons
                });
                if(time!=null){
                    $("#updateWeekForm #time").datebox('setValue', timestampFormat(time,"yyyy-MM-dd"));
                }

                $("#updateWeekForm").form('enableValidation');

                submitFlag=0;
            }
        })
}
function updateForm() {

        if(!$("#updateWeekForm").form("validate")){
            return;
        }
        if(submitFlag==1){
            $.messager.alert('警告','请勿重复提交！');
            return;
        }

        var id = $("#id").val();
        var w_name=$("#updateWeekForm #w_name").val();

        var last_work_sketch = $("#updateWeekForm #last_work_sketch").val();
        var w_type=$("#updateWeekForm #w_type").val();
        var u_time=$("#updateWeekForm #u_time").val();

        var this_work_sketch = $("#updateWeekForm #this_work_sketch").val();
        var remarks=$("#updateWeekForm #remarks").val();
        var time = $("#updateWeekForm #time").val();


        $.ajax({
            url:basePath+"/WeeklyController/updateWeekly",
            type:'POST',
            async: false,
            data:{
                'id':id,
                'w_name':w_name,
                'last_work_sketch':last_work_sketch,
                'w_type':w_type,
                'u_time':u_time,
                'this_work_sketch':this_work_sketch,
                'remarks':remarks,
                'time':time,
            },
            success:function(data) {
                if (data.mes == "success") {
                    submitFlag = 1;
                    closeWindow();
                }
                selectWeekly();
                messageBox(data);
            }
        })
    

}


function deleteWeekly() {
    var datas=$("#WeeklyTable").datagrid("getSelections");
    if(datas.length==0){
        $.messager.alert("警告","请至少选中一项删除");
        return;
    }
    var ids='';
    $.each(datas,function (i) {
        if(datas.length==1){
            ids=ids+datas[i].id;
        }else{
            ids=ids+datas[i].id+',';
        }
    })

    var flag = false;
    $.messager.confirm('确认','确认要删除选中的记录吗？',function (r) {
        if(r){
            $.ajax({
                url:basePath+'/WeeklyController/deleteWeekly',
                type:'POST',
                asnyc:false,
                data:{
                    'ids':ids
                },
                success:function (data) {
                    messageBox(data);
                    selectWeekly();
                }
            })
        }
    })
}

function importWeekly() {
    layui.use('upload',function () {
        var upload = layui.upload;

        var uploadInst = upload.render({
            elem:'#importExcel',
            url:basePath+'/WeeklyController/importExcel',
            accept:"file",
            acceptMime:"file/xls,file/xlsx",
            exts:'xls|xlsx',
            done: function(res){
                messageBox(res);
                selectWeekly();
            },
            error: function() {
                //请求异常回调
            }
        })

    })
}

function exportWeekly() {
    window.location.href=basePath+"/WeeklyController/exportWeekly";
}




function openWindow(options){
    $("#w").window(options);
}
function closeWindow(){
    $("#w").window("close");
}

