 var taskRequireMap;
 var taskStateMap;
 var testStageMap;
 var executorMap;
 var submitFlag;
 var turnMap;
 function addSubTask(){
	openWindow({
		title:"新增子任务",
		href:basePath+'/jsp/task/addSubTask.jsp',
		width:700,    
	    height:350,    
	    modal:true,
	    onLoad:function(){
			$("#subtaskform").form('disableValidation');
	    	$("#subtaskform #pId").val(task.id);
	    	$("#subtaskform #level").val(task.level+1);

	    	$("#subtaskform #taskName").val(task.taskName);
//	    	$("#subtaskform #taskName2").val("xxx");
			taskStateMap=initCombo("task_state","subtaskform #taskState");
			executorMap=initCombo("test_principal","subtaskform #executor");			
			testStageMap=initCombo("test_stage","subtaskform #testStage");
			turnMap=initCombo("turn","subtaskform #turn");
			$("#subtaskform #taskState").combobox('setValue',task.taskState);
	    	$("#subtaskform #testStage").combobox('setValue',task.testStage);
			$("#subtaskform").form('enableValidation');
			submitFlag=0;
	    }
	});
}

function updateSubTask(subtask){
		openWindow({
		title:"更新子任务",
		href:basePath+'/jsp/task/addSubTask.jsp',
		width:700,    
	    height:350,    
	    modal:true,
	    onLoad:function(){
			$("#subtaskform").form('disableValidation');
			$("#subtaskform #id").val(subtask.id);
			$("#subtaskform #level").val(parseInt(task.level)+1);
		    $("#subtaskform #taskName").val(task.taskName);
		    
		    
			var taskName2=subtask.taskName.substring(subtask.taskName.indexOf(task.taskName)+task.taskName.length,subtask.taskName.length);
	    	if(taskName2.indexOf("-第")==0 && taskName2.lastIndexOf("-")==0){
	    		taskName2=""
	    	}else if(taskName2.indexOf("-第")==0 && taskName2.lastIndexOf("-")>0){
	    		taskName2=taskName2.substring(taskName2.lastIndexOf("-")+1);
	    	}else if(taskName2.lastIndexOf("-第")>0){
	    		taskName2=taskName2.substring(taskName2.indexOf("-")+1,taskName2.lastIndexOf("-")-taskName2.indexOf("-"));
	    	}else if(taskName2.indexOf("-")==0){
	    		taskName2=taskName2.substring(taskName2.indexOf("-")+1);
	    	}

	    	$("#subtaskform #taskName2").val(taskName2);
			taskStateMap=initCombo("task_state","subtaskform #taskState");
			executorMap=initCombo("test_principal","subtaskform #executor");			
			testStageMap=initCombo("test_stage","subtaskform #testStage");
			turnMap=initCombo("turn","subtaskform #turn");
			if(task.expectStartTime!=null){
				$("#subtaskform #expectStartTime").datebox('setValue', timestampFormat(subtask.expectStartTime,"yyyy-MM-dd"));
			}
			if(task.deadline!=null){
				$("#subtaskform #deadline").datebox('setValue', timestampFormat(subtask.deadline,"yyyy-MM-dd"));
			}
			$("#subtaskform #taskState").combobox('setValue',subtask.taskState);
			$("#subtaskform #executor").combobox('setText',subtask.executor);
	    	$("#subtaskform #testStage").combobox('setValue',subtask.testStage);
	    	$("#subtaskform #turn").combobox('setValue',subtask.turn);
			$("#subtaskform").form('enableValidation');
			submitFlag=0;
	    }
	});
}

/************提交表单*****************/
function submitForm(selector){
	if(!$(selector).form("validate")){
		return;
	}

	var id=$(selector+" #id").val();
	var pId=$(selector+" #pId").val();
	var level=$(selector+" #level").val();
	if(level==2){
		if($(selector+" #taskName2").val()){
			var taskName=$(selector+" #taskName").val()+"-"+$(selector+" #taskName2").val()+($(selector+" #turn").val()!=""?"-第"+$(selector+" #turn").val()+"轮":"");
		}else{
			if(selector=="#detailForm"){
				var taskName=$(selector+" #taskName").val();
			}else{
				var taskName=$(selector+" #taskName").val()+($(selector+" #turn").val()!=""?"-第"+$(selector+" #turn").val()+"轮":"");
			}
			
		}
		var turn=$(selector+" #turn").combobox('getValue');
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
	var caseNum=$(selector+" #caseNum").val();
	var caseDay=$(selector+" #caseDay").val();
	
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
			'turn':turn,
			'taskState':taskState,
			'executor':executor,
			'expectStartTime':expectStartTime,
			'actualStartTime':actualStartTime,
			'deadline':deadline,
			'actualEndTime':actualEndTime,
			'level':level,
			'workratio':workratio,
			'testStage':testStage,
			'caseNum':caseNum,
			'caseDay':caseDay
		},
		async:false,
		success:function(data){
			messageBox(data);
			if(data.mes=="success"){
				submitFlag=1;
				$("#subTask").datagrid('reload');
				parent.active.tabRefresh("5");
				parent.active.tabRefresh("0");
			}

		}
		
	});
}

/**************提交历史进度****************************/
function submitProgress(){
	var remark=$("#detailForm2 #taskDescription").val();
	var actualProgress=$("#detailForm2 #actualProgress").val();
	var currentime=$("#detailForm2 #currentime").val(); 
	var level=$("#detailForm #level").val();

	$.ajax({
		url:basePath+"/taskController/addOrUpdateHistory",
		type:"post",
		async:false,
		data:{
			'createtime':currentime,
			'progress':actualProgress,
			'remark':remark,
			'taskId':task.id,
			'level':level
		},
		success:function(data){
			messageBox(data);
			if(data.mes=="success"){		
				initTimeLine();
				parent.active.tabRefresh("5");
			}
		}
	});
}
function deleteProgress(index){
	var taskId=task.id;
	$.messager.confirm('确认',"确认删除当前进度",function(r){    
	    if (!r){    
	        return;    
	    }else{
	    	$.ajax({
				url:basePath+"/taskController/deleteHistory",
				type:"post",
				async:false,
	    		data:{
	    			'id':historys[index].id,
	    			'taskId':taskId
	    		},
	    		success:function(data){
	    			messageBox(data);
	    			initTimeLine(taskId);
			    	if(data.rows.length>0){
						json=JSON.stringify(data.rows[0]);
					}
					parent.active.tabRefresh("5");
	    		}
	    	});
	    }
	});
}