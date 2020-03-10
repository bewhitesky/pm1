var table;
var counts;
var total;
window.onload = function() {
	initDateInput();
	initTable();
}
function initTable(){
layui.use('table',function(){
	var id=$("#id").val();
	var testItem=$("#testItem").val();
	var col;
	if(testItem.indexOf("功能/非功能测试")<0){
		col=[
				{type:'checkbox'},
				{field:'internalTestTimes',  title: '轮次',align:'center',width:72},
				{field:'deployStartTime',  title: '部署开始时间',align:'center',width:'12%',
					templet: function(d){
						if(d.deployStartTime==null || d.deployStartTime==''){
							 return '';
						}else{
							 return timestampFormat(d.deployStartTime,"yyyy-MM-dd");
						}
					}
				},
				{field:'deployEndTime',  title: '部署结束时间',align:'center',width:'12%',
					templet: function(d){
						if(d.deployEndTime==null || d.deployEndTime==''){
							 return '';
						}else{
							 return timestampFormat(d.deployEndTime,"yyyy-MM-dd");
						}
					}
				},
				{field:'testStartTime',  title: '测试开始时间',align:'center',width:'12%',
					templet: function(d){
						if(d.testStartTime==null || d.testStartTime==''){
							 return '';
						}else{
							 return timestampFormat(d.testStartTime,"yyyy-MM-dd");
						}
					}
				},
				{field:'testEndTime',  title: '测试结束时间',align:'center',width:'12%',
					templet: function(d){
						if(d.testEndTime==null || d.testEndTime==''){
							  return '';
						}else{
							  return timestampFormat(d.testEndTime,"yyyy-MM-dd");
						}
					}
				},
				{field:'correctTime',  title: '每轮整改用时(天)',align:'center',width:'13%'},
				{field:'testTime',  title: '每轮测试用时(天)',align:'center',width:'13%'},
				{field:'remark',  title: '备注',align:'center',width:'16%'}];
	}else{
			col=[{type:'checkbox'},
				{field:'internalTestTimes',  title: '轮次',align:'center',width:72},
				{field:'deployStartTime',  title: '部署开始时间',align:'center',width:'10%',
					templet: function(d){
						if(d.deployStartTime==null || d.deployStartTime==''){
							 return '';
						}else{
							 return timestampFormat(d.deployStartTime,"yyyy-MM-dd");
						}
					}
				},
				{field:'deployEndTime',  title: '部署结束时间',align:'center',width:'10%',
					templet: function(d){
						if(d.deployEndTime==null || d.deployEndTime==''){
							 return '';
						}else{
							 return timestampFormat(d.deployEndTime,"yyyy-MM-dd");
						}
					}
				},
				{field:'testStartTime',  title: '测试开始时间',align:'center',width:'10%',
					templet: function(d){
						if(d.testStartTime==null || d.testStartTime==''){
							 return '';
						}else{
							 return timestampFormat(d.testStartTime,"yyyy-MM-dd");
						}
					}
				},
				{field:'testEndTime',  title: '测试结束时间',align:'center',width:'10%',
					templet: function(d){
						if(d.testEndTime==null || d.testEndTime==''){
							  return '';
						}else{
							  return timestampFormat(d.testEndTime,"yyyy-MM-dd");
						}
					}
				},
				{field:'correctTime',  title: '每轮整改用时(天)',align:'center',width:'12%'},
				{field:'testTime',  title: '每轮测试用时(天)',align:'center',width:'12%'},
				{field:'runabled',  title: '用例执行率(%)',align:'center',width:'12%'},
				{field:'remark',  title: '备注',align:'center',width:'14%'}];
	}
			
	table = layui.table;  
			table.render({
				id:'internalItem',
				elem: "#internalItemTable",
				url: basePath+'/internalController/selectInternalItems',
				method:'post',
				height: 'full-200',
				cellMinWidth: 60 ,//全局定义常规单元格的最小宽度，layui 2.2.1 新增,
				page: false, //开启分页
//				limit:8,
				limits:[5,10,15,20,25],
				where:{
					'id':id
				},
				cols: [col],
				request: {
				  pageName: 'page', //页码的参数名称，默认：page
				  limitName: 'nums' //每页数据量的参数名，默认：limit
				}, 
			    response: {
				  msgName: 'mes' ,//状态信息的字段名称，默认：msg
				  countName: 'total', //数据总数的字段名称，默认：count
				  dataName: 'rows'//数据列表的字段名称，默认：data
				},
				done: function(res, curr, count){
					$.ajax({
						url: basePath+'/internalController/selectInternalDefect',
						type:"post",
						data:{
							'id':id
						},
						success:function(data){
							$("#defect").val(data.rows.defect);
							$("#defectId").val(data.rows.id);
							$("#defectNum").val(data.rows.defectNum);
							$("#caseNum").val(data.rows.caseNum);
						}
						
					});
					$("#count").val(res.total);
					total=res.total;
					data=res.rows
					var correctTime=0;
					var testTime=0;
					counts= new Array();
					$.each(data,function(i){
						if(data[i].correctTime!=null){
							correctTime=correctTime+parseInt(data[i].correctTime);
						}
						if(data[i].testTime!=null){
							testTime=testTime+parseInt(data[i].testTime);
						}
						counts.push(data[i].internalTestTimes);
					});
					$("#correctTime").val(correctTime+"天");
					$("#testTime").val(testTime+"天");
			    }
			});
});	
}

function initDateInput(){
	layui.use('laydate', function(){
	  var laydate = layui.laydate;
	  	laydate.render({ 
		  elem: '#deploymentTime'
		  ,range: '~' //或 range: '~' 来自定义分割字符
		});
	});
}

function search(){
	var id=$("#id").val();
	table.reload("internalItem",{
		url:basePath+'/internalController/selectInternalItems',
		where:{
			'id':id
		}
	});
}

function addItems(){
var id=$("#id").val();
var pName=$("#pName").val();
var testItem=$("#testItem").val();
//var count=parseInt($("#count").val())+1;
//var count=total+1;
var count=1;
for(var i=0;i<counts.length;i++){
	if((i+1)!=counts[i]){
		count=(i+1);
		break;
	}
	else{
		if((i+1)==counts.length)
		count=counts[i]+1;
		continue;
	}
}
parent.layer.open({
		  title:'添加'+count+'轮信息',
		  type: 2, 
		  area: ['800px', '550px'],
		  resize:true,
		  scrollbar: false,
		  content: basePath+'/jsp/internal/addRecord.jsp',//这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
		  success: function(layero, index){
		  	var body = parent.layer.getChildFrame('body', index);
	      	body.contents().find("#internalTestId").val(id);
	      	body.contents().find("#count").val(count);
	      	body.contents().find("#pName").val(pName);
	      	body.contents().find("#testItem").val(testItem);
	      	if(count==1){ 		
	      		body.contents().find("#defectDiv").show();
	      		body.contents().find("#defectDiv1").show();
	      		if(testItem.indexOf("功能/非功能测试")<0){
	      			body.contents().find("#runabled").hide();
	      			body.contents().find("#runabledlabel").hide();
	      		}
	      	}else{   		
	      		if(testItem.indexOf("功能/非功能测试")>-1){
	      			body.contents().find("#runDiv").show();
	      		}
	      	}
		  }	  	
	});
}



/**
 * 修改用户
 */
function updateItems(){
	var checkStatus = table.checkStatus('internalItem'); //test即为基础参数id对应的值
	var data=checkStatus.data[0];
	if(checkStatus.data.length>1){
		layer.alert('只允许对一行数据进行修改!', {icon: 0,title:"警告",btnAlign: 'c'});
		return;
	}
	if(checkStatus.data.length==0){
		layer.alert('请选择一行数据进行修改!', {icon: 0,title:"警告",btnAlign: 'c'});
		return;
	}
	var id=data.id;
	var internalTestId=data.internalTestId;
	var deployStartTime=data.deployStartTime;
	var deployEndTime=data.deployEndTime;
	var testStartTime=data.testStartTime;
	var testEndTime=data.testEndTime;
	var count=data.internalTestTimes;
	var runabled=data.runabled;
	var remark=data.remark;
	var pName=$("#pName").val();
	var testItem=$("#testItem").val();
	var defect=$("#defect").val();
	var defectId=$("#defectId").val();
	var defectNum=$("#defectNum").val();
	var caseNum=$("#caseNum").val();
	parent.layer.open({
		  title:'修改'+count+'轮信息',
		  type: 2, 
		  area: ['800px', '550px'],
		  content: basePath+'/jsp/internal/addRecord.jsp', //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
		  success: function(layero, index){
	      	var body = parent.layer.getChildFrame('body', index);
	      	body.contents().find("#id").val(id);
	      	body.contents().find("#pName").val(pName);
	      	body.contents().find("#testItem").val(testItem);
	      	body.contents().find("#internalTestId").val(internalTestId);
	      	body.contents().find("#count").val(count);
	      	body.contents().find("#remark").val(remark);
	      	if(testItem.indexOf("功能/非功能测试")>-1){
	      		body.contents().find("#defectDiv1").hide();
	      		body.contents().find("#runDiv").show();
	      		if(count==1){
	      			body.contents().find("#runabled").val(runabled);
	      			body.contents().find("#runDiv").hide();
	      		}else{
	      			body.contents().find("#runabled2").val(runabled);
	      		}
	      	}
	      	if(count==1){ 		
	      		body.contents().find("#defectDiv").show();
	      		body.contents().find("#defectDiv1").show();
	      		body.contents().find("#defect").val(defect);
	      		body.contents().find("#defectId").val(defectId);
	      		body.contents().find("#defectNum").val(defectNum);
	      		body.contents().find("#caseNum").val(caseNum);
	      	}
	      	if(deployStartTime==null || deployStartTime==""){
	      		body.contents().find("#deployStartTime").val("");
	      	}else{
	      		body.contents().find("#deployStartTime").val(timestampFormat(deployStartTime,"yyyy-MM-dd"));
	      	}
	      	if(deployEndTime==null || deployEndTime==""){
	      		body.contents().find("#deployEndTime").val("");
	      	}else{
	      		body.contents().find("#deployEndTime").val(timestampFormat(deployEndTime,"yyyy-MM-dd"));
	      	}
	      	if(testStartTime==null || testStartTime==""){
	      		body.contents().find("#testStartTime").val("");
	      	}else{
	      		body.contents().find("#testStartTime").val(timestampFormat(testStartTime,"yyyy-MM-dd"));
	      	}
	      	if(testEndTime==null || testEndTime==""){
	      		body.contents().find("#testEndTime").val("");
	      	}else{
	      		body.contents().find("#testEndTime").val(timestampFormat(testEndTime,"yyyy-MM-dd"));
	      	}
	  	  }
		});
}

function deleteItems(){
	var index=0;
	var checkStatus = table.checkStatus('internalItem'); //test即为基础参数id对应的值
	var items=checkStatus.data;
	if(items.length==0){
		layer.alert('至少选择一行数据进行删除!', {icon: 0,title:"警告",btnAlign: 'c'});
		return;
	}
	var ids='';
	counts.splice(items.internalItems);
	$.each(items,function(i){
		if(i==(items.length-1)){
			ids=ids+items[i].id;
		}else{
			ids=ids+items[i].id+',';
		}
	});
	layer.confirm('确定删除所选项？', {
	  btn: ['确定','取消'] //按钮
	}, function(){
		$.ajax({
			url:basePath+"/internalController/deleteItems",
			type:"POST",
			async:false,
			data:{
				'ids':ids
			},
			beforeSend:function(){
				index=layer.msg('正在删除，请稍候!', {
						  icon: 16,
						  shade: 0.01
						});
			},
			success:function(data){
				if(data.mes=="success"){
					if(items[0].internalTestTimes==1){
						$.ajax({
							url:basePath+"/internalController/deleteDefect",
							type:"post",
							data:{
								'id':items[0].internalTestId
							},
							async:false,
							success:function(data){
								$("#defect").val("");
							}
						})
					}
					search();
					layer.close(index);
					messageBox(data);
				}
			}
		});
	}, function(){

	});



}




