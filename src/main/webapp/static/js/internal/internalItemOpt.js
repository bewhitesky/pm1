var table;
var pDepartmentMap;
var currStateMap;
var pTypeMap;
var testTypeMap;
var flag=0;
$(document).ready(function(){
	initDateInput();
	
	$("#defectNum").bind("input propertychange",function(){
		var defectNum=$("#defectNum").val();
		var caseNum=$("#caseNum").val();
		if(defectNum !='' && caseNum!=''){
			$("#defect").val(((parseInt(defectNum)/parseInt(caseNum))*100).toFixed(2));
		}else{
			$("#defect").val("");
		}
	});
	$("#caseNum").bind("input propertychange",function(){
		var defectNum=$("#defectNum").val();
		var caseNum=$("#caseNum").val();
		if(defectNum !='' && caseNum!=''){
			$("#defect").val(((parseInt(defectNum)/parseInt(caseNum))*100).toFixed(2));
		}else{
			$("#defect").val("");
		}
	});
});

function initDateInput(){
	layui.use('laydate', function(){
	  var laydate = layui.laydate;
	  	laydate.render({ 
		  elem: '#deployStartTime'
		});
		laydate.render({ 
		  elem: '#deployEndTime'
		});
		laydate.render({ 
		  elem: '#testStartTime'
		});
		laydate.render({
		  elem: '#testEndTime'
		});
	});

}

function submitRecord(){
//	flag=1;
//	if(flag==1){
//		layer.alert('请勿重复提交!', {icon: 0,title:"警告",btnAlign: 'c'});
//		return;
//	}
	var submitFlag = false;
	$("#recordForm").isValid(function(v){
		if(v == true)
			submitFlag = true;
	});
	if(submitFlag == false){
		return;
	}
	var id=$("#id").val();
	var defectId=$("#defectId").val();
	var count=$("#count").val();
	if($("#internalTestId").val()!=""){
		var internalTestId=$("#internalTestId").val();
	}	
	var deployStartTime=$("#deployStartTime").val();
	var deployEndTime=$("#deployEndTime").val();
	var testStartTime=$("#testStartTime").val();
	var testEndTime=$("#testEndTime").val();
	var defect =$("#defect").val();
	var defectNum=$("#defectNum").val();
	var caseNum=$("#caseNum").val();
	var remark=$("#remark").val();
	if(count==1){	
		var runabled=$("#runabled").val();
	}else{		
		var runabled=$("#runabled2").val();
	}
	if(deployStartTime==""){
		layer.alert('请选择搭建开始时间!', {icon: 0,title:"警告",btnAlign: 'c'});
		return;
	}
	if(deployEndTime==""){
		layer.alert('请选择搭建结束时间!', {icon: 0,title:"警告",btnAlign: 'c'});
		return;
	}
	if(testStartTime==""){
		layer.alert('请选择测试开始时间!', {icon: 0,title:"警告",btnAlign: 'c'});
		return;
	}
	if(testEndTime==""){
		layer.alert('请选择测试结束时间!', {icon: 0,title:"警告",btnAlign: 'c'});
		return;
	}
	if(deployStartTime>deployEndTime){
		layer.alert('搭建开始时间不能晚于搭建结束于时间!', {icon: 0,title:"警告",btnAlign: 'c'});
		return;
	}
	if(testStartTime>testEndTime){
		layer.alert('测试开始时间不能晚于测试结束于时间!', {icon: 0,title:"警告",btnAlign: 'c'});
		return;
	}
//	if(deployEndTime>testStartTime){
//		layer.alert('搭建结束时间不能晚于测试开始时间!', {icon: 0,title:"警告",btnAlign: 'c'});
//		return;
//	}
		
	$.ajax({
		url:basePath+'/internalController/addOrUpdateItems',
		data:{
			'id':id,
			'internalTestTimes':count,
			'internalTestId':internalTestId,
			'deployStartTime':deployStartTime,
			'deployEndTime':deployEndTime,
			'testStartTime':testStartTime,
			'testEndTime':testEndTime,
			'runabled':runabled,
			'remark':remark
		},
		type:'post',
		async:false,
		success:function(data){
			if(defect!=''&& defect !=null){
				$.ajax({
					url:basePath+'/internalController/addOrUpdateDefect',
					data:{
						'id':defectId,
						'internalTestId':internalTestId,
						'defect':defect,
						'defectNum':defectNum,
						'caseNum':caseNum
					},
					type:'post',
					async:false,
					success:function(data){
						
					}
				});
			}
			var index =parent.layer.getFrameIndex("layui-layer-iframe1");
			var body = parent.layer.getChildFrame("body",index);
			body.contents().find("#defect").val(defect);
			body.contents().find("#defectId").val(defectId);
			body.contents().find("#btn").click();
			closeWindow();
			parent.messageBox(data);
		}
	});
	
}



function closeWindow(){
	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	parent.layer.close(index);
}




