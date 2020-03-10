/**
 * 任务
 * @type 
 */
var taskRequireMap;
var taskStateMap;
var currStateMap;
var executorMap;
var pnameMap;
var turnMap;
$(document).ready(function(){
	taskRequireMap=initCombo("task_require",null);
	taskStateMap=initCombo("task_state","searchForm #taskState");
	testStageMap=initCombo("test_stage",null);
	executorMap=initCombo("test_principal","searchForm #executor");
	//initTable();
	initTree();
	month("#searchForm #expectMonth");
	month("#searchForm #actualMonth");	
});

function month(seletor){
	        //日期选择对象
   var p = $(seletor).datebox('panel'), 
        //日期选择对象中月份
   tds = false, 
        //显示月份层的触发控件
   span = p.find('span.calendar-text'); 
   var curr_time = new Date();
	$(seletor).datebox({
       //显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
       onShowPanel: function () {
          //触发click事件弹出月份层
          span.trigger('click'); 
          if (!tds)
            //延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
            setTimeout(function() { 
                tds = p.find('div.calendar-menu-month-inner td');
                tds.click(function(e) {
                   //禁止冒泡执行easyui给月份绑定的事件
                   e.stopPropagation(); 
                   //得到年份
                   var year = /\d{4}/.exec(span.html())[0] ,
                   //月份
                   //之前是这样的month = parseInt($(this).attr('abbr'), 10) + 1; 
                   month = parseInt($(this).attr('abbr'), 10);  

         //隐藏日期对象                     
         $(seletor).datebox('hidePanel') 
           //设置日期的值
           .datebox('setValue', year + '-' + month); 
                        });
                    }, 0);
            },
            //配置parser，返回选择的日期
            parser: function (s) {
                if (!s) return new Date();
                var arr = s.split('-');
                return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
            },
            //配置formatter，只返回年月 之前是这样的d.getFullYear() + '-' +(d.getMonth()); 
            formatter: function (d) { 
                var currentMonth = (d.getMonth()+1);
                var currentMonthStr = currentMonth < 10 ? ('0' + currentMonth) : (currentMonth + '');
                return d.getFullYear() + '-' + currentMonthStr; 
            }
        });

}

function monthformatter(date) {
    //获取年份
    var y = date.getFullYear();
    //获取月份
    var m = date.getMonth() + 1;
    return y + '-' + m;
}

function getPName(){
	$.ajax({
		url:basePath+'/internalController/getPnames',
		type:'post',
		async:false,
		success:function(data){
			datas=data.maps;
			var objs=new Array();
			var obj={};
				obj.key=null;
				obj.value="请选择......";
				objs.push(obj);
			$.each(datas,function(i){
				var obj={};
				obj.key=datas[i].id;
				obj.value=datas[i].productName;
				objs.push(obj);
			});
			$("#taskForm #relProject").combobox({
				valueField:'key',    
    			textField:'value',
    			data: objs,
    			editable:true,
    			value:"",
    			panelWidth:300,
    			onSelect:function(param){
    				$("#taskForm #baseInfoId").val(param.key);
    			}
			});
		}
	});
}

/******************初始化任务树****************/
function initTree(){
	$("#taskTable").treegrid({
    url:basePath+'/taskController/getTaskTree',
    queryParams:{
		"executor":userName
	},
    idField:'id',    
    treeField:'taskName',
    columns:[[
    	   {
	    		field:'id',
				title:'id',
				width:3,
				hidden:true
	    	},
	    	{
	    		field:'caseNum',
				title:'caseNum',
				width:3,
				hidden:true
	    	},
	    	{
	    		field:'caseDay',
				title:'caseDay',
				width:3,
				hidden:true
	    	},
			{
				field:'taskName',
				title:'任务名称',
				width:300,
				align:'left'
			},
//			{
//				field:'taskRequire',
//				title:'测试项',
//				width:100,
//				align:'center',
//				formatter:function(value,row,index){
//					return taskRequireMap[value];
//				}
//			},
			{
				field:'executor',
				title:'测试负责人',
				width:90,
				align:'center',
				formatter:function(value,row,index){
					return value;
				}
			},
			{
				field:'testStage',
				title:'任务阶段',
				width:90,
				align:'center',
				formatter:function(value,row,index){
					return testStageMap[value];
				}
			},
//			{
//				field:'taskRequire',
//				title:'测试需求',
//				width:100,
//				align:'center',
//				formatter:function(value,row,index){
//					return taskRequireMap[value];
//				}
//			},
			{
				field:'expectStartTime',
				title:'预计开始时间',
				width:100,
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
				title:'预计结束时间',
				width:100,
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
				field:'expectDay',
				title:'预计工时',
				width:70,
				align:'center',
				formatter:function(value,row,index){
					if(row.level==1){
						return "";
					}
					return value;
				}
			},
			{
				field:'expectProgress',
				title:'计划进度(%)',
				width:90,
				align:'center',
				formatter:function(value,row,index){
					if(row.level==1){
						return "";
					}
					if(value==null || value==''){
						return 0;
					}else{
						return value.toFixed(0)
					}			
				}
			},
			{
				field:'actualStartTime',
				title:'实际开始时间',
				width:100,
				align:'center',
				formatter:function(value,row,index){
					if(row.level==1){
						return "";
					}
					if(value==null || value==''){
		      			return '';
			      	}else{
			      		 return timestampFormat(value,"yyyy-MM-dd");
			      	}
				}
			},
			{
				field:'actualEndTime',
				title:'实际结束时间',
				width:100,
				align:'center',
				formatter:function(value,row,index){
					if(row.level==1){
						return "";
					}
					if(value==null || value==''){
		      			return '';
			      	}else{
			      		 return timestampFormat(value,"yyyy-MM-dd");
			      	}
				}
			},
			{
				field:'actualDay',
				title:'实际工时',
				width:70,
				align:'center',
				formatter:function(value,row,index){
//					if(row.level==1){
//						return "";
//					}
					return value;
				}
			},
			{
				field:'actualProgress',
				title:'实际进度(%)',
				width:90,
				align:'center',
				formatter:function(value,row,index){
					if(row.level==1){
						return "";
					}
					return value;
				}
			},
			{
				field:'workload',
				title:'工作量',
				width:60,
				align:'center',
				formatter:function(value,row,index){
					if(row.level==1){
						return "";
					}
					return value;
				}
			},		
			{
				field:'workratio',
				title:'项目系数',
				width:70,
				align:'center',
				formatter:function(value,row,index){
					if(row.level==1){
						return "";
					}
					return value;
				}
			},
			{
				field:'taskState',
				title:'当前状态',
				width:80,
				align:'center',
				formatter:function(value,row,index){
					if(row.level==1){
						return "";
					}
					return taskStateMap[value];
				}
			},
			{
				field:'operate',
				title:'操作',
				width:240,
				align:'center',
				formatter:function(value,row,index){
//					var task=JSON.stringify(row);
					return taskButton(row);
				}
			}
    ]],
    onLoadSuccess:function(row, data){
        $('#taskTable').treegrid('collapseAll');//全部展开树节点
        if (data.total == 0) {
			   	var body = $(this).data().datagrid.dc.body2;  
   				var width = body.width();  

 				body.find('table tbody').append(
 				'<tr>center>' +
 				'<td width = '+width+' ' +
 						'style="height: 25px; text-align:center;color:#999;border: 0px solid ;" ' +
 						'colspan='+15+'>无数据</td></center></tr>'); 
		}

    },
//    onClickCell:function(field,row){
//    	if(field=="operate"){
//    		return;
//    	}else{
//	    	var json=JSON.stringify(row);
//	    	parent.active.tabDelete("123456");
//	    	parent.active.tabAdd(basePath+"/jsp/task/taskDetail.jsp?json="+encodeURI(json),"123456","任务详情页");
//	    	parent.active.tabChange("123456");
//    	}
//    },
//    onBeforeExpand: function(row){
//    	taskDto={
//    		'id':row.id,
//    		'level': ""+row.level
//    	};
//    	debugger
//        $('#taskTable').treegrid("options").url=basePath+'/taskController/getTaskTree';  
//        $('#taskTable').treegrid("options").queryParams=taskDto;
//    },
		checkOnSelect :true,
		ctrlSelect : true,
		singleSelect : true,
		width : window.screen.width-220,
		pagination : true,
		border : true,
		fit : true,
		scrollbarSize  : 0,
		rownumbers : false
});  
			//自定义分页栏
		var pager = $('#taskTable').treegrid('getPager');
		pager.pagination({
			beforePageText : '第',
			afterPageText : '/ {pages} 页',
			displayMsg : '当前 {from} - {to} 条记录, 共 {total} 条记录'
		});
//		$('#taskTable').treegrid('doCellTip',{cls:{'background-color':'#ffffff'},delay:100});
}

function initWorkData(){
	var expectMonth=$("#workForm #expectMonth").val();
	var actualMonth=$("#workForm #actualMonth").val();
	$("#worktable").datagrid({
		url:basePath+'/taskController/getWorkData',
		queryParams:{
			'actualMonth':actualMonth,
			'expectMonth':expectMonth,
			'level':2
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
				width:300,
				align:'left'
			},
//			{
//				field:'taskRequire',
//				title:'测试项',
//				width:100,
//				align:'center',
//				formatter:function(value,row,index){
//					return taskRequireMap[value];
//				}
//			},
			{
				field:'executor',
				title:'测试负责人',
				width:90,
				align:'center',
				formatter:function(value,row,index){
					return value;
				}
			},
			{
				field:'testStage',
				title:'任务阶段',
				width:90,
				align:'center',
				formatter:function(value,row,index){
					return testStageMap[value];
				}
			},
//			{
//				field:'taskRequire',
//				title:'测试需求',
//				width:100,
//				align:'center',
//				formatter:function(value,row,index){
//					return taskRequireMap[value];
//				}
//			},
			{
				field:'expectStartTime',
				title:'预计开始时间',
				width:100,
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
				title:'预计结束时间',
				width:100,
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
				field:'expectDay',
				title:'预计工时',
				width:70,
				align:'center',
				formatter:function(value,row,index){
					if(row.level==1){
						return "";
					}
					return value;
				}
			},
			{
				field:'expectProgress',
				title:'计划进度(%)',
				width:90,
				align:'center',
				formatter:function(value,row,index){
					if(row.level==1){
						return "";
					}
					if(value==null || value==''){
						return 0;
					}else{
						return value.toFixed(0)
					}			
				}
			},
			{
				field:'actualStartTime',
				title:'实际开始时间',
				width:100,
				align:'center',
				formatter:function(value,row,index){
					if(row.level==1){
						return "";
					}
					if(value==null || value==''){
		      			return '';
			      	}else{
			      		 return timestampFormat(value,"yyyy-MM-dd");
			      	}
				}
			},
			{
				field:'actualEndTime',
				title:'实际结束时间',
				width:100,
				align:'center',
				formatter:function(value,row,index){
					if(row.level==1){
						return "";
					}
					if(value==null || value==''){
		      			return '';
			      	}else{
			      		 return timestampFormat(value,"yyyy-MM-dd");
			      	}
				}
			},
			{
				field:'actualDay',
				title:'实际工时',
				width:70,
				align:'center',
				formatter:function(value,row,index){
					if(row.level==1){
						return "";
					}
					return value;
				}
			},
			{
				field:'actualProgress',
				title:'实际进度(%)',
				width:90,
				align:'center',
				formatter:function(value,row,index){
					if(row.level==1){
						return "";
					}
					return value;
				}
			},
			{
				field:'workload',
				title:'工作量',
				width:60,
				align:'center',
				formatter:function(value,row,index){
					if(row.level==1){
						return "";
					}
					return value;
				}
			},		
			{
				field:'workratio',
				title:'项目系数',
				width:70,
				align:'center',
				formatter:function(value,row,index){
					if(row.level==1){
						return "";
					}
					return value;
				}
			},
			{
				field:'taskState',
				title:'当前状态',
				width:80,
				align:'center',
				formatter:function(value,row,index){
					if(row.level==1){
						return "";
					}
					return taskStateMap[value];
				}
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
				//自定义分页栏
		var pager = $('#worktable').treegrid('getPager');
		pager.pagination({
			beforePageText : '第',
			afterPageText : '/ {pages} 页',
			displayMsg : '当前 {from} - {to} 条记录, 共 {total} 条记录'
		});
}

function searchTask(){
	var taskName=$("#searchForm #taskName").val();
	if(userName==""){
		var executor=$("#searchForm #executor").combobox('getText');
	}else{
		var executor=userName;
	}
	var expectMonth=$("#searchForm #expectMonth").val();
	var actualMonth=$("#searchForm #actualMonth").val();
	var expectStartTime=$("#searchForm #expectStartTime").val();
	var actualStartTime=$("#searchForm #actualStartTime").val();
	var actualEndTime=$("#searchForm #actualEndTime").val();
	var deadline=$("#searchForm #deadline").val();
	var taskState=$("#searchForm #taskState").val();
	$("#taskTable").treegrid("reload",
		{
			'taskName':taskName,
			'executor':executor,
			'expectMonth':expectMonth,
			'expectStartTime':expectStartTime,
			'deadline':deadline,
			'actualStartTime':actualStartTime,
			'actualEndTime':actualEndTime,
			'actualMonth':actualMonth,
			'taskState':taskState
		});
}

function searchWork(){
	var expectMonth=$("#workForm #expectMonth").val();
	var actualMonth=$("#workForm #actualMonth").val();
	$("#worktable").datagrid("reload",
		{
			'expectMonth':expectMonth,
			'actualMonth':actualMonth,
			'level':2
		});
}

function exportTask(){
	var expectMonth=$("#searchForm #expectMonth").val();
	var actualMonth=$("#searchForm #actualMonth").val();
	var taskDto={
		'actualMonth':actualMonth,
		'expectMonth':expectMonth
	}
//	window.location.href=basePath+'/taskController/exportTask?taskDto='+taskDto;
	window.location.href=basePath+'/taskController/exportTask?json='+JSON.stringify(taskDto);
}

function exportWork(){
//	parent.active.tabDelete("bgsj");
//	parent.active.tabAdd(basePath+"/jsp/task/taskDetail.jsp","bgsj","报工数据");
//	parent.active.tabChange("bgsj");
	openWindow({
		title:"报工数据",
		href:basePath+'/jsp/task/workData.jsp',
		width:1400,    
	    height:600,    
	    modal:true,
	    onLoad:function(){
			initWorkData();
			month("#workForm #expectMonth");
			month("#workForm #actualMonth");
	    }
	});
}
function exportWordData(){
	var expectMonth=$("#workForm #expectMonth").val();
	var actualMonth=$("#workForm #actualMonth").val();
	var taskDto={
		'actualMonth':actualMonth,
		'expectMonth':expectMonth,
		'level':2
	}
//	window.location.href=basePath+'/taskController/exportTask?taskDto='+taskDto;
	window.location.href=basePath+'/taskController/exportWordData?json='+JSON.stringify(taskDto);
}
function openWindow(options){
	$("#w").window(options);
}
function closeWindow(){
	$("#w").window("close");
}

function resetForm(){
	$("#searchForm #expectStartTime").val("");
	$("#searchForm #deadline").val("");
	$("#searchForm #actualStartTime").val("");
	$("#searchForm #actualEndTime").val("");
	$("#searchForm #executor").combobox('setValue',"");
	$("#searchForm #taskState").combobox('setValue',"");
	$("#searchForm #taskName").val("");
	$("#searchForm #expectMonth").textbox('setValue','');
	$("#searchForm #actualMonth").textbox('setValue','');
//	$("#searchForm #actualMonth").datebox('hidePanel') 
//           //设置日期的值
//           .datebox('setValue', ' '); 
}
