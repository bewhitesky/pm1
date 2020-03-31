$(document).ready(function () {
    w_typeMap = initCombo("w_type","searchForm #w_type");

    WeeklyTable();

    if(userName=="系统管理员"){
        userName="";
    }

})

function WeeklyTable(){
    $("#WeeklyTable").datagrid({
        url:basePath+'/WeeklyController/selectWeekly',
        queryParams:{
            'w_name':userName
        },
        columns:[[
            {
                field:'id',
                title:'id',
                hidden:true
            },
            {
                field:'w_name',
                title:'姓名',
                width:100,
                align:'center'
            },
            {
                field:'time',
                title:'时间',
                width:100,
                align:'center',
                formatter:function (value,row,index) {
                    if(value==null || value==''){
                        return '';
                    }else{
                        return timestampFormat(value,"yyyy-MM-dd");
                    }
                }
            },
            {
                field:'last_work_sketch',
                title:'上周工作情况',
                width:400,
                align:'center'
            },
            {
                field:'w_type',
                title:'任务分类',
                width:200,
                align:'center',
                formatter:function (value,row,index) {
                    if(value==null)return "";
                    return w_typeMap[value];
                }
            },
            {
                field:'u_time',
                title:'耗时',
                width:60,
                align:'center'

            },
            {
                field:'this_work_sketch',
                title:'本周计划',
                width:400,
                align:'center'
            },
            {
                field:'remarks',
                title:'备注',
                width:200,
                align:'center'
            },

        ]],
        onLoadSuccess : function(data) {
            if (data.total == 0) {
                var body = $(this).data().datagrid.dc.body2;
                var width = body.width();

                body.find('table tbody').append(
                    '<tr>center>' +
                    '<td width = '+width+' ' +
                    'style="height: 25px; text-align:center;color:#999;border: 0px solid ;" ' +
                    'colspan='+9+'>无数据!</td></center></tr>');
            }
        },
        checkOnSelect :true,
        ctrlSelect : true,
        singleSelect : false,
        width : 'auto',
        pagination : true,
        fitColumns:true,
        border : true,
        fit : true,
        scrollbarSize  : 0,
        rownumbers : true,
        striped : true,
        emptyMsg:'<span style="color:#999;>无数据</span>',
        frozenColumns:[[
            {field:'ck',checkbox:true}
        ]]
    });

    //自定义分页栏
    var pager = $('#WeeklyTable').datagrid('getPager');
    pager.pagination({
        beforePageText : '第',
        afterPageText : '/ {pages} 页',
        displayMsg : '当前 {from} - {to} 条记录, 共 {total} 条记录'
    });
    $('#WeeklyTable').datagrid('doCellTip',{cls:{'background-color':'#ffffff'},delay:100});
}


function selectWeekly() {

    var w_name = $("#searchForm #w_name").val();

    var w_type = $("#searchForm #w_type").val();
    var time = $("#searchForm #time").val();
    $("#WeeklyTable").datagrid("reload",
        {
            'w_name':userName,
            'w_type':w_type,
            'time':time
        });
}

function resetForm() {
    $("#searchForm #w_name").val("");
    $("#searchForm #w_type").combobox('setValue',"");
    $("#searchForm #time").val("");
}




