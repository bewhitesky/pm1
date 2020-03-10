function addProgress(){
	openWindow({
		title:"新增进度",
		href:basePath+'/jsp/person/addProgress.jsp',
		width:700,    
	    height:300,    
	    modal:true,
		onLoad:function(){
			
		}
	})
}

function submitProgress(){
	var id=$("#detailForm #id").val();
	var remark=$("#detailForm #taskDescription").val();
	var actualProgress=$("#detailForm #actualProgress").val();

	$.ajax({
		url:basePath+"/taskController/addOrUpdateHistory",
		type:"post",
		async:false,
		data:{
			'createtime':today,
			'progress':actualProgress,
			'remark':remark,
			'taskId':id
		},
		success:function(data){
			messageBox(data);
			initTimeLine(id);
		}
	});
}

function addSubTask(){
	var id=$("#detailForm #id").val();
 	var level=$("#detailForm #level").val();
 	var taskName=$("#detailForm #taskName").val();
 	var taskState=$("#detailForm #taskState").val();
 	var testStage=$("#detailForm #testStage").val();
	openWindow({
		title:"新增子任务",
		href:basePath+'/jsp/task/addSubTask.jsp',
		width:700,    
	    height:350,    
	    modal:true,
	    onLoad:function(){
			$("#subtaskform").form('disableValidation');
	    	$("#subtaskform #pId").val(id);
	    	$("#subtaskform #level").val(parseInt(level)+1);

	    	$("#subtaskform #taskName").val(taskName);
//	    	$("#subtaskform #taskName2").val("xxx");
			taskStateMap=initCombo("task_state","subtaskform #taskState");
			executorMap=initCombo("test_principal","subtaskform #executor");			
			testStageMap=initCombo("test_stage","subtaskform #testStage");
			
			$("#subtaskform #taskState").combobox('setValue',0);
	    	$("#subtaskform #testStage").combobox('setValue',testStage);
			$("#subtaskform").form('enableValidation');
			submitFlag=0;
	    }
	});
}

function submitForm(selector){
	if(!$(selector).form("validate")){
		return;
	}

	var id=$(selector+" #id").val();
	var pId=$(selector+" #pId").val();
	var level=$(selector+" #level").val();
	if(level==2){
		var taskName=$(selector+" #taskName").val()+"-"+$(selector+" #taskName2").val();
	}else{
		var taskName=$(selector+" #taskName").val();
	}	
	var taskState=$(selector+" #taskState").combobox('getValue');
	var testStage=$(selector+" #testStage").combobox('getValue');
	var executor=$(selector+" #executor").combobox('getText');
	var expectStartTime=$(selector+" #expectStartTime").val();
	var actualStartTime=$(selector+" #actualStartTime").val();
	var deadline=$(selector+" #deadline").val();
	var actualEndTime=$(selector+" #actualEndTime").val();
	var workload=$(selector+" #workload").val();
	var workratio=$(selector+" #workratio").val();
	
	if(expectStartTime>deadline){
		$.messager.alert('警告','截止时间不能早于预计开始时间','warning');
		return;
	}
	if(actualEndTime!="" && actualStartTime!="" && actualEndTime<actualStartTime){
		$.messager.alert('警告','实际结束时间不能早于实际开始时间','warning');
		return;
	}
	$.ajax({
		url:basePath+"/taskController/addOrUpdateTask",
		type:'post',
		data:{
			'id':id,
			'pId':pId,
			'taskName':taskName,
			'taskState':taskState,
			'executor':executor,
			'expectStartTime':expectStartTime,
			'actualStartTime':actualStartTime,
			'deadline':deadline,
			'actualEndTime':actualEndTime,
			'level':level,
			'workratio':workratio,
			'testStage':testStage
		},
		async:false,
		success:function(data){
			submitFlag=1;
			messageBox(data);
			$("#subTask").datagrid('reload');
		}
		
	});
}