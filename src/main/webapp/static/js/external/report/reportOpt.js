var submitFlag=0;
$(document).ready(function(){
	
});
function addReport(){
	openWindow({
		title:"新增进度跟踪日报",
		href:basePath+'/jsp/external/report/addReport.jsp',
		width:800,    
	    height:600,    
	    modal:true,
	    onLoad:function(){
	    	getPName();
	    	initCombo("project_department","reportForm #pDepartment");
			initCombo("project_type","reportForm #pType");
			initCombo("current_state_report","reportForm #currStateReport");
			initCombo("risk_level","reportForm #riskLevel");
			initCombo("curr_state","reportForm #currState");
			$("#reportForm #pName").combobox({
				onChange:function(param){
					$("#reportForm #baseInfoId").val(param);
					getBaseInfo(param);
				}
			});
			submitFlag=0;
			$("#reportForm #pDepartment").combobox("disable");
			$("#reportForm #pType").combobox("disable");
			$('#reportForm #applyFunTime').datebox({
				buttons: buttons
			});
			$('#reportForm #applySecTime').datebox({
				buttons: buttons
			});
	    }
	});
}

function getBaseInfo(id){
	$.ajax({
		url:basePath+"/baseInfoController/selectBaseInfoById",
		type:'post',
		data:{
			'id':id
		},
		async:false,
		success:function(data){
			baseInfo=data.rows;
			$("#reportForm #pDepartment").combobox("setValue",baseInfo.pdepartment+"");
			$("#reportForm #pType").combobox("setValue",baseInfo.ptype+"");			
			$("#reportForm #currState").combobox({multiple:true});

		}
	});
}

function updateReport(){
	var datas=$("#reportTable").datagrid("getSelections");
	if(datas.length>1){
		return;
	}
	if(datas.length==0){
		return;
	}
	var id=datas[0].id;
	var baseInfoId=datas[0].baseInfoId;
	var pDepartment=datas[0].pdepartment;
	var pName=datas[0].pname;
	var pType=datas[0].ptype;
	var currStateReport=datas[0].currStateReport;
	var currStateTwo=datas[0].currState;
	var riskLevel=datas[0].riskLevel;
	var linkman=datas[0].linkman;
	var riskPoint=datas[0].riskPoint;
	var remark=datas[0].remark;
	var applyFunTime=datas[0].applyFunTime;
	var applySecTime=datas[0].applySecTime;
	openWindow({
		title:"更新进度跟踪日报",
		href:basePath+'/jsp/external/report/addReport.jsp',
		width:800,
		height:600,
		modal:true,
		onLoad:function(){
//			initCombobox("project_department","pDepartment");
			initCombo("project_department","reportForm #pDepartment");
			initCombo("project_type","reportForm #pType");
			initCombo("current_state_report","reportForm #currStateReport");
			initCombo("risk_level","reportForm #riskLevel");
			initCombo("curr_state","reportForm #currState");
			getPName();
			$("#reportForm #pName").combobox({
				onChange:function(param){
					$("#reportForm #baseInfoId").val(param);
					getBaseInfo(param);
				}
			});
			$("#reportForm").form('disableValidation');
			$("#reportForm #id").val(id);
			$("#reportForm #baseInfoId").val(baseInfoId);
			$("#reportForm #pDepartment").combobox('setValue',pDepartment+"");
			$("#reportForm #pName").combobox('setText',pName);
			$("#reportForm #pType").combobox('setValue',pType+"");
			$("#reportForm #currStateReport").combobox('setValue',currStateReport+"");
			$("#reportForm #currState").combobox({multiple:true});
			$("#reportForm #currState").combobox('setValues',currStateTwo);
			$("#reportForm #riskLevel").combobox('setValue',riskLevel);
			$("#reportForm #linkman").val(linkman);
			$("#reportForm #riskPoint").val(riskPoint);
			$("#reportForm #remark").val(remark);
			$('#reportForm #applyFunTime').datebox({
				buttons: buttons
			});
			$('#reportForm #applySecTime').datebox({
				buttons: buttons
			});
			if(applyFunTime!=null){
				$("#reportForm #applyFunTime").datebox('setValue', timestampFormat(applyFunTime,"yyyy-MM-dd"));
			}
			if(applySecTime!=null){
				$("#reportForm #applySecTime").datebox('setValue', timestampFormat(applySecTime,"yyyy-MM-dd"));
			}
			$("#reportForm").form('enableValidation');
			
//			$("#reportForm #pDepartment").combobox("disable");
//			$("#reportForm #pName").combobox("disable");
//			$("#reportForm #pType").combobox("disable");
			$("#reportForm #pDepartment").combobox("disable");
			$("#reportForm #pType").combobox("disable");	
			submitFlag=0;
		}
	})
}
function deleteReport(){
	var datas=$("#reportTable").datagrid("getSelections");
	if(datas.length==0){
		$.messager.alert("警告","请至少选中一项删除！");
		return;
	}
	var ids=''
	$.each(datas,function(i){
		if(datas.length==i){
			ids=ids+datas[i].id;
		}else{
			ids=ids+datas[i].id+',';
		}
	})
	var flag=false;
	$.messager.confirm('确认','确认要删除选中记录吗？',function(r){
		if(r){
			$.ajax({
				url:basePath+'/reportController/deleteReport',
				type:'post',
				asnyc:false,
				data:{
					'ids':ids
				},
				success:function(data){
					messageBox(data);
					searchReport();
				}
				
			});
		}
	});
	
}

function submitForm(){
	if(!$("#reportForm").form("validate")){
		return;
	}
	if(submitFlag==1){
		$.messager.alert('警告','请勿重复提交！');
		return;
	}
	var id=$("#id").val();
	var baseInfoId=$("#reportForm #baseInfoId").val();
	var pDepartment=$("#reportForm #pDepartment").val();
	var pName=$("#reportForm #pName").combobox('getText');
	var pType=$("#reportForm #pType").val();
	var currStateOne=$("#reportForm #currStateReport").val();
	var currStateTwo=$("#reportForm #currState").val();
	var riskLevel=$("#reportForm #riskLevel").val();
	var linkman=$("#reportForm #linkman").val();
	var riskPoint=$("#reportForm #riskPoint").val();
	var remark=$("#reportForm #remark").val();
	var applyFunTime=$("#reportForm #applyFunTime").val();
	var applySecTime=$("#reportForm #applySecTime").val();
	$.ajax({
		url:basePath+"/reportController/addOrUpdateReport",
		type:'post',
		data:{
			'id':id,
			'baseInfoId':baseInfoId,
			'pDepartment':pDepartment,
			'pName':pName,
			'pType':pType,
			'currStateOne':currStateOne,
			'currStateTwo':currStateTwo,
			'riskLevel':riskLevel,
			'linkman':linkman,
			'riskPoint':riskPoint,
			'remark':remark,
			'applyFunTime':applyFunTime,
			'applySecTime':applySecTime
		},
		success:function(data){
			if(data.mes=="success"){
				submitFlag=1;
				closeWindow();
			}
			messageBox(data);
			searchReport();
		}
		
	})
}

function exportReport(){
	window.location.href=basePath+'/reportController/exportReport';
}

