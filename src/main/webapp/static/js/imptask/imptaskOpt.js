var submitFlag=0;

$(document).ready(function () {

})


function addImpTask() {
        openWindow({
            title:"新增重点工作跟踪",
            href:basePath+'/jsp/imptask/addImpTask.jsp',
            width:800,
            height:600,
            modal:true,
            onLoad:function(){
                initCombo("imptask_taskcls","imptaskForm #imptask_taskcls");
                initCombo("test_principal","imptaskForm #p_charge");
                initCombo("imptask_state","imptaskForm #imptask_state");

                $('#impTaskTable #p_complete_time').datebox({
                    buttons: buttons
                });
            }
        })
}

function submitForm() {
    if(!$("#imptaskForm").form("validate")){
        return;
    }
    if(submitFlag==1){
        $.messager.alert('警告','请勿重复提交！');
        return;
    }

    var id = $("#id").val();
    var imptask_name=$("#imptaskForm #imptask_name").val();

    var achievements = $("#imptaskForm #achievements").val();
    var imptask_taskcls=$("#imptaskForm #imptask_taskcls").val();
    var p_charge=$("#imptaskForm #p_charge").val();

    /**
    var p_charge_one=p_charge.split(",");
    var p_charge_two="";

    for(var i=0;i<p_charge_one.length;i++){
        a=p_charge_one[i];
        if(p_charge_one.length ==1 || p_charge_one.length==(i+1)){
            p_charge_two = p_charge_two + imptask_p_chargeMap[a]
        }else{
            p_charge_two = p_charge_two + imptask_p_chargeMap[a]+",";
        }
    }
*/
    var p_complete_time = $("#imptaskForm #p_complete_time").val();
    var imptask_state=$("#imptaskForm #imptask_state").val();
    var percent = $("#imptaskForm #percent").val();
    var plan=$("#imptaskForm #plan").val();
    var explan=$("#imptaskForm #explan").val();
    var remarks=$("#imptaskForm #remarks").val();

    $.ajax({
        url:basePath+"/ImpTaskController/addOrUpdateImptask",
        type:'POST',
        async: false,
        data:{
            'id':id,
            'imptask_name':imptask_name,
            'achievements':achievements,
            'imptask_taskcls':imptask_taskcls,
            'p_charge':p_charge,
            'p_complete_time':p_complete_time,
            'imptask_state':imptask_state,
            'percent':percent,
            'plan':plan,
            'explan':explan,
            'remarks':remarks
        },
        success:function(data) {
            if (data.mes == "success") {
                submitFlag = 1;
                closeWindow();
            }
            messageBox(data);
            ImpTaskTable();
        }
    })
}


function updateImpTask() {
    var datas=$("#impTaskTable").datagrid("getSelections");
    if(datas.length>1){
        $.messager.alert("请只勾选一条数据进行修改");
        return;
    }
    if(datas.length==0){
        $.messager.alert("请选择修改项！");
        return;
    }
    var id=datas[0].id;
    var imptask_name=datas[0].imptask_name;
    var achievements=datas[0].achievements;
    var imptask_taskcls=datas[0].imptask_taskcls;
    var p_charge=datas[0].p_charge;
    var p_complete_time=datas[0].p_complete_time;
    var plan=datas[0].plan;
    var imptask_state=datas[0].imptask_state;
    var percent=datas[0].percent;
    var explan=datas[0].explan;
    var remarks=datas[0].remarks;

    openWindow({
        title:"更新进度跟踪日报",
        href:basePath+'/jsp/imptask/addImpTask.jsp',
        width:800,
        height:600,
        modal:true,
        onLoad:function(){
            initCombo("imptask_taskcls","imptaskForm #imptask_taskcls");
            initCombo("test_principal","imptaskForm #p_charge");
            initCombo("imptask_state","imptaskForm #imptask_state");

            $("#imptaskForm").form('disableValidation');
            $("#imptaskForm #id").val(id);
            $("#imptaskForm #imptask_name").val(imptask_name);
            $("#imptaskForm #achievements").val(achievements);
            $("#imptaskForm #imptask_taskcls").combobox('setValue',imptask_taskcls);
            $("#imptaskForm #p_charge").combobox('setValues',p_charge);
            $("#imptaskForm #plan").val(plan);
            $("#imptaskForm #imptask_state").combobox('setValue',imptask_state);
            $("#imptaskForm #percent").val(percent);
            $("#imptaskForm #explan").val(explan);
            $("#imptaskForm #remarks").val(remarks);
            $('#imptaskForm #p_complete_time').datebox({
                buttons: buttons
            });
            if(p_complete_time!=null){
                $("#imptaskForm #p_complete_time").datebox('setValue', timestampFormat(p_complete_time,"yyyy-MM-dd"));
            }

            $("#imptaskForm").form('enableValidation');
            submitFlag=0;
        }
    })
}

function deleteImpTask() {
    var datas=$("#impTaskTable").datagrid("getSelections");
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
                url:basePath+'/ImpTaskController/deleteImptask',
                type:'POST',
                asnyc:false,
                data:{
                    'ids':ids
                },
                success:function (data) {
                    messageBox(data);
                    ImpTaskTable();
                }
            })
        }
    })
}

function importImgTask() {
    layui.use('upload',function () {
        var upload = layui.upload;

        var uploadInst = upload.render({
            elem:'#importExcel',
            url:basePath+'/ImpTaskController/importExcel',
            accept:"file",
            acceptMime:"file/xls,file/xlsx",
            exts:'xls|xlsx',
            done: function(res){
                messageBox(res);
                ImpTaskTable();
            },
            error: function() {
                //请求异常回调
            }
        })

    })
}

function exportImgTask() {
    window.location.href=basePath+"/ImpTaskController/exportImptask";
}



function openWindow(options){
    $("#w").window(options);
}
function closeWindow(){
    $("#w").window("close");
}