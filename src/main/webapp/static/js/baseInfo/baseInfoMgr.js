var table;
var pDepartmentMap;
var pTypeMap;
var testTypeMap;
var pLevelMap;
var thirdTestMap;
var testStateMap;
var dorgMap;
$(document).ready(function(){	
	initTable(initSelect());
	initDateRange("#searchForm");
	upload();
	var date=new Date();
	$("#testYear").val(date.getFullYear());
});


function initSelect(){

	pDepartmentMap=initCombo("project_department","pDepartment");
	pTypeMap=initCombo("project_type","pType");
	testTypeMap=initCombo("test_type",null);
	pLevelMap=initCombo("project_level",null);
	thirdTestMap=initCombo("third_test",null);
	testStateMap=initCombo("test_state","testState");
	dorgMap=initCombo("d_org","dorg");

}
/**
 * 表格初始化
 */
function initTable(_call){	
	$("#baseInfoTable").datagrid({
		url:basePath+'/baseInfoController/selectBaseInfo',
		frozenColumns:[[ 
		    {field:'ck',checkbox:true},
		    {
				field:'pdepartment',
				title:'所属部门',
				width:200,
				align:'center',
				fixed:true,
				formatter:function(value,row,index){
					if(value==null)return "";
					return pDepartmentMap[value];
				}
			},
			{
				field:'productName',
				title:'产品名称',
				width:300,
				fixed:true,
				align:'center'
			}
		]],
	    columns:[[
	    	{
	    		field:'id',
				title:'id',
				hidden:true
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
				field:'testType',
				title:'测试需求',
				width:200,
				align:'center'
			},
			{
				field:'plevel',
				title:'等保等级',
				width:100,
				align:'center',
				formatter:function(value,row,index){
					if(value==null)return "";
					return pLevelMap[value];
				}
			},
			{
				field:'internalTime',
				title:'计划内部测试时间',
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
				field:'factoryTime',
				title:'计划出厂测试时间',
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
				field:'thirdTime',
				title:'计划第三方测试时间',
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
				field:'thirdTest',
				title:'第三方测试机构',
				width:150,
				align:'center',
				formatter:function(value,row,index){
					if(value==null)return "";
					return thirdTestMap[value];
				}
			},
			{
				field:'demandExpTime',
				title:'计划需求完成时间',
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
				field:'demandActTime',
				title:'实际需求完成时间',
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
				field:'designExpTime',
				title:'计划概设完成时间',
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
				field:'designActTime',
				title:'设计概设完成时间',
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
				field:'dorg',
				title:'研发机构',
				width:100,
				align:'center',
				formatter:function(value,row,index){
					if(value==null)return "";
					return dorgMap[value];
				}
			},
			{
				field:'testState',
				title:'测试状态',
				width:100,
				align:'center'
			},
			{field:'remark',title:'计划备注',width:100,align:'center'},
			{field:'testYear',title:'测试年度',width:100,align:'center'},
			{field:'pmName',title:'项目经理',width:100,align:'center'},
			{field:'pmTel',title:'联系电话',width:100,align:'center'},
			{field:'pmEmail',  title: '邮箱地址',width:100,align:'center'}
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
		border : true,
		fit : true,
		scrollbarSize  : 0,
		rownumbers : true,
		striped : true,
		emptyMsg:'<span style="color:#999;>无数据</span>'
	});
		//自定义分页栏
		var pager = $('#baseInfoTable').datagrid('getPager');
		pager.pagination({
			beforePageText : '第',
			afterPageText : '/ {pages} 页',
			displayMsg : '当前 {from} - {to} 条记录, 共 {total} 条记录'
		});
		$('#baseInfoTable').datagrid('doCellTip',{cls:{'background-color':'#ffffff'},delay:100});
}

function initDateRange(select){
	layui.use('laydate', function(){
	  var laydate = layui.laydate;
	  	laydate.render({ 
		  elem: select+' #internalTime'
		  ,range: '~' //或 range: '~' 来自定义分割字符
		});
	
		laydate.render({ 
		  elem:  select+' #factoryTime'
		  ,range:'~'  //或 range: '~' 来自定义分割字符
		});
	
		laydate.render({ 
		  elem:  select+' #thirdTime'
		  ,range:'~' //或 range: '~' 来自定义分割字符
		});
	});
}


function searchBaseInfo(){
	var pDepartment=$("#pDepartment").val();
	var pType=$("#pType").val();
	var dorg=$("#dorg").val();
	var internalTime=$("#internalTime").val();
	var pName=$("#pName").val();
	var testYear=$("#testYear").val();
	var factoryTime=$("#factoryTime").val();
	var thirdTime=$("#thirdTime").val();
	var testState=$("#testState").val();
	var testStateValue;
	if(testState=="" || testState==null){
		testStateValue=""
	}else{
		testStateValue=testStateMap[testState];
	}
	$("#baseInfoTable").datagrid("reload",{
			'pDepartment':pDepartment,
			'internalTime':internalTime,
			'pName':pName,
			'pType':pType,
			'testYear':testYear,
			'factoryTime':factoryTime,
			'thirdTime':thirdTime,
			'testState':testStateValue,
			'dorg':dorg
	});
}

function resetInfo(){
	$("#pDepartment").val("");
	$("#internalTime").val("");
	$("#pName").val("");
	$("#pType").val("");
	var date=new Date();
	$("#testYear").val(date.getFullYear());
	$("#factoryTime").val("");
	$("#thirdTime").val("");
}


function openWindow(options){
	$("#w").window(options);
}

function closeWindow(){
	$("#w").window("close");
}
