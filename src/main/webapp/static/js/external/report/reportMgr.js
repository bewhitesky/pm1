var pDepartmentMap;
var pTypeMap;
var currStateMap;
var currStateReportMap;
var riskLevelMap;
var buttons = $.extend([], $.fn.datebox.defaults.buttons);
$(document).ready(function(){
	pDepartmentMap=initCombo("project_department",null);
	pTypeMap=initCombo("project_type",null);
	currStateReportMap=initCombo("current_state_report","searchForm #currStateReport");
	currStateMap=initCombo("curr_state",null);
	riskLevelMap=initCombo("risk_level","searchForm #riskLevel");
	initTable();

	buttons.splice(1, 0, {
	text: '清除',
	handler: function(target){
		$(target).datebox('clear');
		$(target).datebox('hidePanel');
	}
	});



});

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
			$("#reportForm #pName").combobox({
				valueField:'key',    
    			textField:'value',
    			data: objs,
    			editable:false,
    			value:""
			});
		}
	});
}

function initTable(){
	$("#reportTable").datagrid({
		url:basePath+'/reportController/selectReport',
	    columns:[[
	    	{
	    		field:'id',
				title:'id',
				hidden:true
	    	},
	    	{
	    		field:'baseInfoId',
				title:'baseInfoId',
				hidden:true
	    	},
			{
				field:'pdepartment',
				title:'所属部门',
				width:100,
				align:'center',
				formatter:function(value,row,index){
					if(value==null)return "";
					return pDepartmentMap[value];
				}
			},
			{
				field:'pname',
				title:'产品名称',
				width:100,
				align:'center'
			},
			{
				field:'ptype',
				title:'项目类别',
				width:100,
				align:'center',
				formatter:function(value,row,index){
					if(value==null)return "";
					return pTypeMap[value];
				}
			},
			{
				field:'currStateReport',
				title:'当前阶段',
				width:100,
				align:'center',
				formatter:function(value,row,index){
					if(value==null)return "";
					return currStateReportMap[value];
				}
			},
			{
				field:'currState',
				title:'当前状态',
				width:200,
				align:'center',
				formatter:function(value,row,index){
					var currState="";
					if(value!=null && value!=""){
						var values=value.split(",");
						$.each(values,function(i){
							currState=currState+currStateMap[values[i]]+",";
						})
					}		
					return currState
				}
			},
			{
				field:'riskLevel',
				title:'风险等级',
				width:100,
				align:'center',
				formatter:function(value,row,index){
					if(value==null)return "";
					return riskLevelMap[value];
				}
			},
			{field:'riskPoint',title:'风险点',width:100,align:'center'},
			{field:'remark',title:'备注',width:100,align:'center'},
			{field:'linkman',title:'联系人',width:100,align:'center'},
			{field:'applyFunTime',title:'功能申请时间',width:100,align:'center',
				formatter:function(value,row,index){
					if(value==null || value==''){
		      			return '';
			      	}else{
			      		 return timestampFormat(value,"yyyy-MM-dd");
			      	}
				}
			},
			{field:'applySecTime',title:'安全申请时间',width:100,align:'center',
				formatter:function(value,row,index){
					if(value==null || value==''){
		      			return '';
			      	}else{
			      		 return timestampFormat(value,"yyyy-MM-dd");
			      	}
				}
			},
			{field:'createTime',title:'更新时间',width:100,align:'center',
				formatter:function(value,row,index){
					if(value==null || value==''){
		      			return '';
			      	}else{
			      		 return timestampFormat(value,"yyyy-MM-dd hh:mm:ss");
			      	}
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
		var pager = $('#reportTable').datagrid('getPager');
		pager.pagination({
			beforePageText : '第',
			afterPageText : '/ {pages} 页',
			displayMsg : '当前 {from} - {to} 条记录, 共 {total} 条记录'
		});
		$('#reportTable').datagrid('doCellTip',{cls:{'background-color':'#ffffff'},delay:100});
}

function searchReport(){
	var pName=$("#searchForm #pName").val();
	var currStateOne=$("#searchForm #currStateReport").val();
	var riskLevel=$("#searchForm #riskLevel").val();
	$("#reportTable").datagrid("reload",
		{
			'pName':pName,
			'currStateOne':currStateOne,
			'riskLevel':riskLevel
		});
}


function openWindow(options){
	$("#w").window(options);
}
function closeWindow(){
	$("#w").window("close");
}

function resetForm(){
	$("#searchForm #pName").val("");
	$("#searchForm #riskLevel").combobox('setValue',"");
	$("#searchForm #currStateReport").combobox('setValue',"");
}
