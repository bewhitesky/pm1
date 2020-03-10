 var taskRequireMap;
 var taskStateMap;
 var testStageMap;
 var executorMap;
 var turnMap;
 $(document).ready(function(){
 	initForm();
 	initTable();
 	initTimeLine();
 	taskRequireMap=initCombo("task_require",null);
	taskStateMap=initCombo("task_state",null);
	testStageMap=initCombo("test_stage",null);
	executorMap=initCombo("test_principal",null);
	turnMap=initCombo("turn",null);
 })
 

 function initForm(){
	if(task.level==1){
		$("#addSubTask").show();
	}else{
		$("#addSubTask").hide();
	}
 	$("#detailForm").form('disableValidation');
			taskStateMap=initCombo("task_state","detailForm #taskState");					
			testStageMap=initCombo("test_stage","detailForm #testStage");
			executorMap=initCombo("test_principal","detailForm #executor");
			turnMap=initCombo("turn","detailForm #turn");
			//getPName();
			$("#detailForm #id").val(task.id);
			$("#detailForm #level").val(task.level);
			$("#detailForm #taskName").val(task.taskName);
			$("#detailForm #taskState").combobox('setValue',task.taskState);
			$("#detailForm #executor").combobox('setText',task.executor);		
			$("#detailForm #testStage").combobox('setValue',task.testStage);
			if(task.expectStartTime!=null){
				$("#detailForm #expectStartTime").datebox('setValue', timestampFormat(task.expectStartTime,"yyyy-MM-dd"));
			}
			if(task.actualStartTime!=null){
				$("#detailForm #actualStartTime").datebox('setValue', timestampFormat(task.actualStartTime,"yyyy-MM-dd"));
			}
			if(task.deadline!=null){
				$("#detailForm #deadline").datebox('setValue', timestampFormat(task.deadline,"yyyy-MM-dd"));
			}
			if(task.actualEndTime!=null){
				$("#detailForm #actualEndTime").datebox('setValue', timestampFormat(task.actualEndTime,"yyyy-MM-dd"));
			}		
			$("#detailForm #taskDescription").val(task.taskDescription);
			$("#detailForm #remark").val(task.remark);
			$("#detailForm #workload").val(task.workload);
			$("#detailForm #workratio").val(task.workratio);
			$("#detailForm #actualProgress").val(task.actualProgress);
			$("#detailForm #caseDay").val(task.caseDay);
			$("#detailForm #caseNum").val(task.caseNum);
			$("#detailForm #turn").combobox('setValue',task.turn);
			var date=new Date();
			var year=date.getFullYear();
			var month=date.getMonth()+1;
			var day=date.getDate();
			$('#detailForm2 #currentime').datebox().datebox('calendar').calendar({
			    validator : function(date){
			        var now = new Date();
			        var d1 = new Date(now.getFullYear(),now.getMonth(),now.getDate());
			        return d1 >= date;
			    }
			});
			$("#detailForm2 #currentime").datebox('setValue', year+"-"+month+"-"+day);
			
			$("#detailForm").form('enableValidation');
 }
 
 function initTable(){
 	$("#subTask").datagrid({
		url:basePath+'/taskController/getSubTask',
		queryParams:{
			"id":task.id
		},
	    columns:[[
	    	{
	    		field:'id',
				title:'id',
				hidden:true
	    	},
			{
				field:'taskName',
				title:'任务名称',
				width:350,
				align:'center'
			},
//			{
//				field:'turn',
//				title:'轮次',
//				width:80,
//				align:'center',
//				formatter:function(value,row,index){
//					return turnMap[value];
//				}
//			},
//			{
//				field:'taskRequire',
//				title:'测试需求',
//				width:100,
//				align:'center',
//				formatter:function(value,row,index){
//					return taskRequireMap[value];
//				}
//			},
//			{
//				field:'taskState',
//				title:'测试状态',
//				width:100,
//				align:'center',
//				formatter:function(value,row,index){
//					return taskStateMap[value];
//				}
//			},
			{
				field:'executor',
				title:'执行人',
				width:100,
				align:'center',
				formatter:function(value,row,index){
					return value;
				}
			},
			{
				field:'expectStartTime',
				title:'预计开始',
				width:150,
				align:'center',
				formatter:function(value,row,index){
					if(value==null || value==''){
		      			return '';
			      	}else{
			      		 return timestampFormat(value,"yyyy-MM-dd");
			      	}
				}
			},	
			{
				field:'deadline',
				title:'预计结束',
				width:150,
				align:'center',
				formatter:function(value,row,index){
					if(value==null || value==''){
		      			return '';
			      	}else{
			      		 return timestampFormat(value,"yyyy-MM-dd");
			      	}
				}
			},
			{
				field:'actualProgress',
				title:'进度(%)',
				width:100,
				align:'center'
			},
			{
				field:'remark',
				title:'备注',
				width:100,
				align:'center'
			}
	    ]],
		onLoadSuccess : function(data) {
			if (data.total == 0) {
			   	var body = $(this).data().datagrid.dc.body2;  
   				var width = body.width();  

 				body.find('table tbody').append(
 				'<tr>center>' +
 				'<td width = '+width+' ' +
 						'style="height: 25px; text-align:center;color:#999;border: 0px solid ;" ' +
 						'colspan='+9+'>无数据</td></center></tr>'); 
			}
		},
		onSelect:function(index,row){
			updateSubTask(row);
		},
		checkOnSelect :true,
		ctrlSelect : true,
		singleSelect : true,
		width : 'auto',
		pagination : true,
		fitColumns:true,
		border : true,
		fit : true,
		scrollbarSize  : 0,
		rownumbers : true,
		striped : true
	});
		var pager = $('#subTask').datagrid('getPager');
		pager.pagination({
			beforePageText : '第',
			afterPageText : '/ {pages} 页',
			displayMsg : '当前 {from} - {to} 条记录, 共 {total} 条记录'
		});
 }
 
function initTimeLine(){
	$.ajax({
		url:basePath+"/taskController/selectHistory",
		type:"post",
		data:{
			"taskId":task.id
		},
		async:false,
		success:function(data){
			historys=data.rows;
			var html="";
			$.each(historys,function(i,history){
				var content=history.createtime+" "+"当前进度:"+history.progress+"%"+"\n"+history.remark;
				html=html+'<li class="layui-timeline-item">'+
							 '<i class="layui-icon layui-timeline-axis" onClick="deleteProgress('+i+')">&#xe63f;</i>'+
							 '<div class="layui-timeline-content layui-text">'+
							 '<div class="layui-timeline-title"><div class="layui-timeline-break">'+content+'</div></div>'+
							 '</div>'+
						 '</li>'	
			});
			$(".layui-timeline").html(html);
		}
	});
}
function openWindow(options){
	$("#w").window(options);
}
function closeWindow(){
	$("#w").window("close");
}