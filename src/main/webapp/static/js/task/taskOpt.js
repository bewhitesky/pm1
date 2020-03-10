var submitFlag=0;
var update=0;
$(document).ready(function(){
	
});
function addTask(){
	openWindow({
		title:"新增任务",
		href:basePath+'/jsp/task/addTask.jsp',
		width:800,    
	    height:600,    
	    modal:true,
	    onLoad:function(){
	    	$("#taskForm #level").val(1);
	    	taskRequireMap=initCombo("task_require","taskForm #taskRequire");
			taskStateMap=initCombo("task_state","taskForm #taskState");
			executorMap=initCombo("test_principal","taskForm #executor");
			testStageMap=initCombo("test_stage","taskForm #testStage");
			getPName();
			$("#taskForm #relProject").combobox("disable");	
			$("#taskForm #testStage").combobox({
				onSelect:function(params){
					if(params.key>0 && params.key<3){
						$("#taskForm #relProject").combobox("enable");
					}else{
						$("#taskForm #relProject").combobox("disable");	
					}
				}
			});
			submitFlag=0;
	    }
	});
}

function addSubTask(task){
//	var task=$("#taskTable").datagrid("getSelected");
//	if(task==null){
//		$.messager.alert('警告','请选中一条主任务!');
//		return;
//	}
	openWindow({
		title:"新增子任务",
		href:basePath+'/jsp/task/addTask.jsp',
		width:800,    
	    height:600,    
	    modal:true,
	    onLoad:function(){
			$("#taskForm").form('disableValidation');
	    	$("#taskForm #pId").val(task.id);
	    	$("#taskForm #level").val(task.level+1);
	    	$("#taskForm #taskName").val(task.taskName);
	    	taskRequireMap=initCombo("task_require","taskForm #taskRequire");
			taskStateMap=initCombo("task_state","taskForm #taskState");
			executorMap=initCombo("test_principal","taskForm #executor");			
			testStageMap=initCombo("test_stage","taskForm #testStage");
			turnMap=initCombo("turn","taskForm #turn");
			
			$("#taskForm").form('enableValidation');
			submitFlag=0;
	    }
	});
}
function updateTask(task){
	parent.json=JSON.stringify(task);
	parent.active.tabDelete("rwxq");
	parent.active.tabAdd(basePath+"/jsp/task/taskDetail.jsp","rwxq","任务详情页");
	parent.active.tabChange("rwxq");
//	var id=task.id;
//	var taskName=task.taskName;
//	var taskRequire=task.taskRequire;
//	var taskState=task.taskState;
//	var executor=task.executor;
//	var expectStartTime=task.expectStartTime==null?null:timestampFormat(task.expectStartTime,"yyyy-MM-dd");
//	var actualStartTime=task.actualStartTime==null?null:timestampFormat(task.actualStartTime,"yyyy-MM-dd");
//	var deadline=task.deadline==null?null:timestampFormat(task.deadline,"yyyy-MM-dd");
//	var actualEndTime=task.actualEndTime==null?null:timestampFormat(task.actualEndTime,"yyyy-MM-dd");
//	var taskDescription=task.taskDescription;	
//	var remark=task.remark;
//	var actualProgress=task.actualProgress;
//	var workload=task.workload;
//	var workratio=task.workratio;
//	var testStage=task.testStage;
//	openWindow({
//		title:"更新状态",
//		href:basePath+'/jsp/task/addTask.jsp',
//		width:800,
//		height:600,
//		modal:true,
//		onLoad:function(){
//			$("#taskForm").form('disableValidation');
//			taskRequireMap=initCombo("task_require","taskForm #taskRequire");
//			taskStateMap=initCombo("task_state","taskForm #taskState");					
//			testStageMap=initCombo("test_stage","taskForm #testStage");
//			executorMap=initCombo("test_principal","taskForm #executor");
//			//getPName();
//		
//			$("#taskForm #id").val(id);
//			$("#taskForm #taskName").val(taskName);
//			$("#taskForm #taskState").combobox('setValue',taskState);
//			$("#taskForm #executor").combobox('setText',executor);			
//			$("#taskForm #testStage").combobox('setValue',testStage);
//			$("#taskForm #expectStartTime").datebox('setValue', expectStartTime);
//			$("#taskForm #actualStartTime").datebox('setValue', actualStartTime);
//			$("#taskForm #deadline").datebox('setValue', deadline);
//			$("#taskForm #actualEndTime").datebox('setValue', actualEndTime);
//			$("#taskForm #taskDescription").val(taskDescription);
//			$("#taskForm #remark").val(remark);
//			$("#taskForm #workload").val(workload);
//			$("#taskForm #workratio").val(workratio);
//			$("#taskForm #actualProgress").val(actualProgress);
//			$("#taskForm").form('enableValidation');
//			if(userName!=""){
//				$("#taskForm #t").hide();
//			}
//			submitFlag=0;
//			update=0;
//		}
//	})
}

function deleteTask(task){
	if(task.level==1){
		var message="确定删除主任务？"
	}else if(task.level==2){
		var message="确定删除二级任务及其子任务？"
	}else{
		var message="确定删除三级任务？"
	}
	$.messager.confirm('确认',message,function(r){    
	    if (!r){    
	        return;    
	    }else{
	    	$.ajax({
				url:basePath+'/taskController/deleteTask',
				type:'post',
				data:{
					'id':task.id
				},
				async:false,
				success:function(data){
					if(data.mes=="success"){
						messageBox(data);
						searchTask();
					}else{
						messageBox(data);
					}

				}
			});
	    }
	}); 

}
function submitForm(){
	if(!$("#taskForm").form("validate")){
		return;
	}
	if(update!=0 && submitFlag==1){
		$.messager.alert('警告','请勿重复提交！');
		return;
	}
	var id=$("#id").val();
	var pId=$("#pId").val();
	var baseInfoId=$("#taskForm #baseInfoId").val();
	var level=$("#taskForm #level").val();
//	if(level>1){
//		var taskName=$("#taskForm #pname").val()+"-"+$("#taskForm #taskName").val();
//	}else{
		var taskName=$("#taskForm #taskName").val();
//	}
	
	var taskRequire=$("#taskForm #taskRequire").val();
	var taskState=$("#taskForm #taskState").val();
	var executor=$("#taskForm #executor").combobox('getText');
	var expectStartTime=$("#taskForm #expectStartTime").val();
	var actualStartTime=$("#taskForm #actualStartTime").val();
	var deadline=$("#taskForm #deadline").val();
	var actualEndTime=$("#taskForm #actualEndTime").val();
	var taskDescription=$("#taskForm #taskDescription").val();
	var remark=$("#taskForm #remark").val();
	var actualProgress=$("#taskForm #actualProgress").val();
	
	var workload=$("#taskForm #workload").val();
	var workratio=$("#taskForm #workratio").val();
	var testStage=$("#taskForm #testStage").val();

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
			'baseInfoId':baseInfoId,
			'taskName':taskName,
			'taskRequire':taskRequire,
			'taskState':taskState,
			'executor':executor,
			'expectStartTime':expectStartTime,
			'actualStartTime':actualStartTime,
			'deadline':deadline,
			'actualEndTime':actualEndTime,
			'taskDescription':taskDescription,
			'remark':remark,
			'actualProgress':actualProgress,
			'level':level,
			'workload':workload,
			'workratio':workratio,
			'testStage':testStage
		},
		async:false,
		success:function(data){
			messageBox(data);

//			$.messager.show({
//				title:'我的消息',
//				msg:'消息将在5秒后关闭。',
//				timeout:5000,
//				showType:'slide'
//			});
			if(data.mes=="success"){
				submitFlag=1;				
				searchTask();
				closeWindow();
			}
			

		}
		
	});
}

function addagain(){
	submitFlag=0;
}

