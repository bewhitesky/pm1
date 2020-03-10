var table;
var counts;
window.onload = function() {
	initDateInput();
	initTable();
}
function initTable(){
layui.use('table',function(){
	table = layui.table;  
			table.render({
				id:'statistics',
				elem: "#statisticsTable",
				url: basePath+'/internalController/statistics',
				method:'post',
				height: 'full-200',
				cellMinWidth: 60 ,//全局定义常规单元格的最小宽度，layui 2.2.1 新增,
				page: false, //开启分页
				cols: [[
				{type:'checkbox'},
				{field:'pname',  title: '产品名称',align:'center',width:'15%'},
				{field:'testItem',  title: '测试项',align:'center',width:'13%'},
				{field:'times',  title: '测试轮数',align:'center',width:'9%'},
				{field:'defect',  title: '首轮缺陷率(%)',align:'center',width:'11%',
					templet: function(d){
				      	return (d.defect).toFixed(2); 
				    }
				},
				{field:'testTime',  title: '测试总用时(天)',align:'center',width:'11%'},
				{field:'correctTime',  title: '整改总用时(天)',align:'center',width:'11%'},
				{field:'testStartTime',  title: '首轮测试开始时间',align:'center',width:'13%',
					templet: function(d){
				      	if(d.testStartTime==null || d.testStartTime==''){
				      			return '';
				      	}else{
				      		 return timestampFormat(d.testStartTime,"yyyy-MM-dd");
				      	}  
				    }
				},
				{field:'testEndTime',  title: '末轮测试结束时间',align:'center',width:'13%',
					templet: function(d){
				      	if(d.testEndTime==null || d.testEndTime==''){
				      			return '';
				      	}else{
				      		 return timestampFormat(d.testEndTime,"yyyy-MM-dd");
				      	}  
				    }
				}
				]],
				request: {
				  pageName: 'page', //页码的参数名称，默认：page
				  limitName: 'nums' //每页数据量的参数名，默认：limit
				},
			    response: {
				  msgName: 'mes' ,//状态信息的字段名称，默认：msg
//				  countName: 'total', //数据总数的字段名称，默认：count
				  dataName: 'rows'//数据列表的字段名称，默认：data
				},
				done: function(res, curr, count){
			    }
			});
});	
}

function initDateInput(){
	layui.use('laydate', function(){
	  var laydate = layui.laydate;
	  	laydate.render({ 
		  elem: '#time',
		  type: 'month',
		  range:true//或 range: '~' 来自定义分割字符
		});
	});
}

function search(){
	var time=$("#time").val();
	var pName=$("#pName").val();
	table.reload("statistics",{
		url:basePath+'/internalController/statistics',
		where:{
			'time':time,
			'pName':pName
		}
	});
}

function exportStatistics(){
	var time=$("#time").val();
	var pName=$("#pName").val();
	var projectDto={
		'time':time,
		'pName':pName
	};
	window.location.href=basePath+'/internalController/exportStatistics?json='+JSON.stringify(projectDto);
//	window.location.href=basePath+'/progressController/exportExcel?projectDto='+JSON.stringify(projectDto);
}





