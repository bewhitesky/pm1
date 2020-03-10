var imptask_taskclsMap
var buttons = $.extend([], $.fn.datebox.defaults.buttons);

$(document).ready(function () {
    imptask_taskclsMap=initCombo("imptask_taskcls","searchForm #imptask_taskcls");
    imptask_p_chargeMap = initCombo("test_principal","searchForm #p_charge");
    imptask_stateMap = initCombo("imptask_state","searchForm #imptask_state");

    ImpTaskTable();

    importImgTask()

    buttons.splice(1, 0, {
        text: '清除',
        handler: function(target){
            $(target).datebox('clear');
            $(target).datebox('hidePanel');
        }
    });
});


function ImpTaskTable(){
    $("#impTaskTable").datagrid({
        url:basePath+'/ImpTaskController/selectImpTask',

        columns:[[
            {
                field:'id',
                title:'id',
                hidden:true
            },
            {
                field:'imptask_name',
                title:'任务名称',
                width:100,
                align:'center'
            },
            {
                field:'achievements',
                title:'交付成果',
                width:100,
                align:'center'
            },
            {
                field:'imptask_taskcls',
                title:'任务分类',
                width:100,
                align:'center',
                formatter:function (value,row,index) {
                    if(value==null)return ""
                    return imptask_taskclsMap[value];
                }
            },
            {
                field:'p_charge',
                title:'责任人',
                width:100,
                align:'center',
                formatter:function (value,row,index) {
                    if (value==null)return"";
                    var p_one = value.split(",");
                    var p_two="";

                    for(var i=0;i<p_one.length;i++){
                        a=p_one[i];
                        if(p_one.length ==1 || p_one.length==(i+1)){
                            p_two = p_two + imptask_p_chargeMap[a]
                        }else{
                            p_two = p_two + imptask_p_chargeMap[a]+",";
                        }
                    }
                    return p_two;
                }
            },
            {
                field:'p_complete_time',
                title:'计划完成时间',
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
                field:'plan',
                title:'阶段计划（与阶段时间节点一一对应）',
                width:200,
                align:'center'
            },
            {
                field:'imptask_state',
                title:'任务状态',
                width:100,
                align:'center',
                formatter:function (value,row,index) {
                    if(value==null)return "";
                    return imptask_stateMap[value];
                }
            },
            {
                field:'percent',
                title:'完成百分比',
                width:100,
                align:'center'
            },
            {
                field:'explan',
                title:'进度说明',
                width:100,
                align:'center'
            },
            {
                field:'remarks',
                title:'备注',
                width:100,
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
    var pager = $('#impTaskTable').datagrid('getPager');
    pager.pagination({
        beforePageText : '第',
        afterPageText : '/ {pages} 页',
        displayMsg : '当前 {from} - {to} 条记录, 共 {total} 条记录'
    });
    $('#impTaskTable').datagrid('doCellTip',{cls:{'background-color':'#ffffff'},delay:100});
}


function selectImpTaskTable(){
    var imptask_name=$("#searchForm #imptask_name").val();
    var imptask_taskcls=$("#searchForm #imptask_taskcls").val();
    var p_complete_time=$("#searchForm #p_complete_time").val();
    $("#impTaskTable").datagrid("reload",
        {
            'imptask_name':imptask_name,
            'imptask_taskcls':imptask_taskcls,
            'p_complete_time':p_complete_time
        });
}

function resetForm() {
    $("#searchForm #imptask_name").val("");
    $("#searchForm #imptask_taskcls").combobox('setValue',"");
    $("#searchForm #p_complete_time").val("");
}











