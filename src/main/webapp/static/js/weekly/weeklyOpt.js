$(document).ready(function () {
    w_typeMap = initCombo("w_type","selectType #w_type");
    c_type= Object.keys(w_typeMap).length
    console.log(c_type)

})
/*
function addWeekly() {
    openWindow({
        title:"周报",
        href:basePath+'jsp/weekly/addWeekly.jsp',
        width:800,
        height:600,
        modal:true,
        onLoad:function () {
            initCombo("w_type","searchForm #w_type");
        }
    })
}

function addWeeklyItems() {

}


function openWindow(options){
    $("#w").window(options);
}
function closeWindow(){
    $("#w").window("close");
}
*/

var table;
var form;
layui.use('element', function(){
    var form = layui.form;

});
layui.use('laydate', function(){
    var laydate = layui.laydate;

    laydate.render({
        elem: '#time'
    });
})

layui.use('table',function () {
    table = layui.table;
    table.render({
        id:"items",
        elem:"#WeeklyTable",
        method:'post',
        cellMinWidth: 80,
        page:true,
        cols:[[
            {type:'checkbox'},
            {field:'time',title:'日期',sort:true},
            {field:'last_work_sketch',title:'上周工作简述',sort:true},
            {field:'w_type',title:'类别',sort:true},
            {field:'u_time',title:'耗时',sort:true},
            {field:'this_work_sketch',title:'本周工作计划',sort:true},
            {field:'remarks',title:'备注',sort:true},
        ]],
        request:{
            pageName:'page',
            limitName:'nums'
        },
        response:{
            msgName:'mes',
            countName:'total',
            dataName:'rows'
        },
        done:function (res,curr,count){

        }
    });
})

function addWeekly(){
    layer.open({
        title:'添加周报',
        type: 2,
        area:['1550px','800px'],
        resize:true,
        content:basePath+'/jsp/weekly/addWeekly.jsp',
        success:function (layero,index) {
            var body = layer.getChildFrame('body',index);
            var subitemTable = body.contents().find("#subitemsTable");
            layui.use('table',function () {
                addTable = layui.table;
                addTable.render({
                    elem:subitemTable,
                    height:'full-500',
                    cols:[[
                        {field:'time',title:'日期',width:100,sort:true},
                        {field:'last_work_sketch',title:'上周工作简述',width:300},
                        {field:'w_type',title:'类别',width:180,sort:true},
                        {field:'u_time',title:'耗时',width:100,sort:true},
                        {field:'this_work_sketch',title:'本周工作计划',width:300},
                        {field:'remarks',title:'备注',width:300},
                        {title:'操作', width:150, toolbar: '#toolbar'} //这里的toolbar值是模板元素的选择器
                    ]]
                })
            })
        }
    })
}


function addWeeklyItems() {

    var time=$("#time").val();
    var last_work_sketch=$("#last_work_sketch").val();
    var w_type=$("#w_type_one").val();

    var u_time=$("#u_time").val();
    var this_work_sketch=$("#this_work_sketch").val();
    var remarks=$("#remarks").val();

    var items = {
        'index':index++,
        'time':time,
        'last_work_sketch':last_work_sketch,
        'w_type':w_type,
        'u_time':u_time,
        'this_work_sketch':this_work_sketch,
        'remarks':remarks
    }

    itemsList.push(items);
    layui.use('table',function () {
        addTable=layui.table;

        addTable.render({
            elem:"#subitemsTable",
            cellMinWidth: 80 ,
            height:'full-250',
            cols:[[
                {field:'time',title:'日期',width:130,event:'selectTime',sort:true},
                {field:'last_work_sketch',title:'上周工作简述',edit:'text',width:300},

                {
                    field: 'w_type', title: '类别', width: 180, sort: true,templet:'#toolType'
                },

                {field:'u_time',title:'耗时',width:100,edit: 'text',minWidth: 150,sort:true},
                {field:'this_work_sketch',title:'本周工作计划',edit:'text',width:300},
                {field:'remarks',title:'备注',edit:'text',width:300},
                {title:'操作', width:150, toolbar: '#toolbar'} //这里的toolbar值是模板元素的选择器
            ]],
            done: function (res, curr, count) {
                layui.use(['form', 'upload', 'layer'], function () {
                    $('#w_type_one').html()
                    $.each(w_typeMap,function (index,item) {
                        $('#w_type_one').append(new Option(item,index));
                    })
                    layui.form.render();
                })
            },
            data:itemsList,
            page: true, //开启分页
        });

        addTable.on('tool(weeklyitemsOpt)', function(obj){
            var data = obj.data;
            if(obj.event === 'del') {
                layer.confirm('真的删除行么', function (index) {
                    obj.del();
                    layer.close(index);
                    itemsList.removeObject(data);
                });
            } else if(obj.event ==='selectTime'){
                var field = $(this).data('field');
                layui.laydate.render({
                    elem:this.firstChild,
                    show:true,
                    closeStop:this,
                    done:function (value,date) {
                        data[field] = value;
                        obj.update(data);
                    }
                })
            }else {

            }
        });
        addTable.on('edit(weeklyitemsOpt)', function(obj){ //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
            console.log(obj.value); //得到修改后的值
            console.log(obj.field); //当前编辑的字段名
            console.log(obj.data); //所在行的所有相关数据
        });

    })
}

function closeWindow(){
    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    parent.layer.close(index);
}
