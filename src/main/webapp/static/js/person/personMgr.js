/**
 * 
 */
 var today
 var json;
 var historys;
 $(document).ready(function(){
 	testStageMap=initCombo("test_stage",null);
 	var date=new Date();
 	today=dataformatter(date);
 	$('#cc').calendar({    
	    current:new Date(),
	    onSelect:function(date){
	    	today=dataformatter(date);
	    	getTask();    	
	    }
	}); 
 	getTask();
 });
 /**********************获取日程安排******************************/
 function getTask(){
 	$.ajax({
 		url:basePath+"/taskController/getTodayTask",
 		type:"post",
 		data:{
 			'userName':userName,
 			'today':today
 		},
 		async:false,
 		success:function(data){
 			var tasklist=data.rows;
 			var html="";
 			$.each(tasklist,function(i,task){
 				html=html+"<li class='today-li'><a href='javascript:void(0)' onclick='showDetail("+JSON.stringify(task)+")'>"+task.taskName+"</a></li>";
 			});			
 			$("#schedule").html(html);
 			if($(".today-li a").length>0){
 				$(".today-li a")[0].click();
 			}
 		}
 	});
 }
 
 /***************显示当前任务*****************************/
 function showDetail(task){
 	json=JSON.stringify(task);
 	$("#detailForm #id").val(task.id);
 	$("#detailForm #pId").val(task.pId);
 	$("#detailForm #level").val(task.level);
 	$("#detailForm #taskState").val(task.taskState);
 	$("#detailForm #testStage").val(task.testStage);
 	$("#detailForm #taskName").val(task.taskName);
 	if(task.level==1){
 		$("#detailForm #taskName").html("<a href='javascript:void(0)' onclick='toTask()'>"+task.taskName+"</a>"+'<button class="layui-btn layui-btn-xs" type="button" style="margin-left:5px" onclick="addSubTask()">任务分配</button> ');
 	}else{
 		$("#detailForm #taskName").html("<a href='javascript:void(0)' onclick='toTask()'>"+task.taskName+"</a>");
 	}
 	$("#detailForm #expectTime").text((task.expectStartTime==null?"":timestampFormat(task.expectStartTime,"yyyy-MM-dd"))+"~"+(task.deadline==null?"":timestampFormat(task.deadline,"yyyy-MM-dd")));
 	$("#detailForm #testStage").text(testStageMap[task.testStage]);
 	initTimeLine(task.id);
 	initTable(task.id);
 }
 
 /***************初始化历史进度**************************/
 function initTimeLine(id){
	$.ajax({
		url:basePath+"/taskController/selectHistory",
		type:"post",
		data:{
			"taskId":id
		},
		async:false,
		success:function(data){
			historys=data.rows;
			var html='<li class="layui-timeline-item">'+
							 '<i class="layui-icon layui-timeline-axis">&#xe63f;</i>'+
							 '<div class="layui-timeline-content layui-text">'+
							 '<div class="layui-timeline-title"><div class="layui-timeline-break">' +
							 '<button class="layui-btn layui-btn-xs" type="button" onclick="addProgress()">' +
							 '新增进度' +
							 '</button>' +
							 '</div>' +
							 '</div>'+
							 '</div>'+
						 '</li>';
//			if(today!=dataformatter(new Date())){
//				html="";
//			}
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

/******************初始化子任务**********************************/
function initTable(id){
 	$("#subTask").datagrid({
		url:basePath+'/taskController/getSubTask',
		queryParams:{
			"id":id
		},
	    columns:[[
	    	{
	    		field:'id',
				title:'id',
				hidden:true
	    	},
			{
				field:'taskName',
				title:'子任务名称',
				width:350,
				align:'center'
			},
//			{
//				field:'turn',
//				title:'轮次',
//				width:80,
//				align:'center',
//				formatter:function(value,row,index){
//					if(value != null && value != '')
//						return "第"+value+"轮";
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
 
 /*************跳转至任务详情页***********************/
 function toTask(){
 	parent.json=json;
	parent.active.tabDelete("rwxq");
	parent.active.tabAdd(basePath+"/jsp/task/taskDetail.jsp","rwxq","任务详情页");
	parent.active.tabChange("rwxq");
 }
 
function openWindow(options){
	$("#w").window(options);
}
function closeWindow(){
	$("#w").window("close");
}