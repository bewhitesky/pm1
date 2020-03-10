var pNameMap={};
var testTypeMap={};
$(document).ready(function(){
	getPName();
	$.each(pNameMap,function(i){
		$("#pName").append("<option value='"+pNameMap[i].id+"'>"+pNameMap[i].productName+"</option>");				
	});
	if(pNameMap[0]!=null && pNameMap[0].id!=null){
		getTestType(pNameMap[0].id);
	}
	initDateInput2();
	layui.use('element', function(){
		var element = layui.element;
		var form=layui.form;
		form.on('select(pName)', function(data){
			getTestType(data.value);
		});
	});
	initTestState();
});

/*
 * 初始化下拉框
 */
function initTestState(){
	initCombobox("test_state","internalTestState");
	initCombobox("test_principal","testPrincipal");
	$("#internalTestState").val("0");
	layui.use('element', function(){
		var element = layui.element;
		var form=layui.form;
		form.on('select(internalTestState)',function(data){
			if($("#internalTestState").val()==null||$("#internalTestState").val()==""){
				$("#internalTestStateMsg").html("&nbsp;&nbsp;测试状态不能为空");
			}else{
				$("#internalTestStateMsg").html("");
			}
		});
	});
	layui.use('form', function(){
		var form = layui.form; 
		form.render('select');
	});
}

/*
 * 获取项目名称
 */
function getPName(){
	$.ajax({
		url:basePath+'/internalController/getPnames',
		type:'post',
		async:false,
		success:function(data){
			if(data.mes=="success"){
				pNameMap=data.maps;
			}
		}	
	});
}

/*
 * 获取测试项
 */
function getTestType(id){
	$.ajax({
		url:basePath+'/internalController/getTestType',
		type:'post',
		async:false,
		data:{
			'id':id
		},
		success:function(data){
			if(data.mes=="success"){
				testTypeMap=data.map;
			}
		}	
	});
	$("#testItem").html("");
	$.each(testTypeMap,function(i){
		$("#testItem").append("<option value='"+testTypeMap[i]+"'>"+testTypeMap[i]+"</option>");				
	});
	layui.use('form', function(){
		var form = layui.form; 
		form.render('select'); 
	});
}

/*
 * 初始化日期
 */
function initDateInput2(){
	layui.use('laydate', function(){
	  var laydate = layui.laydate;
	  laydate.render({ 
		  elem: '#receiveTime'
	  });

	  laydate.render({ 
		  elem: '#deploymentTime'
	  });
	});
}

function submitInternal(){
	var submitFlag = false;
	$("#internalForm").isValid(function(v){
		if(v == true)
			submitFlag = true;
	});
	if(submitFlag == false){
		return;
	}
	var id=$("#id").val();
	if(id == null){
		id = "";
	}
	var baseInfoId=$("#pName option:selected").val();
	var testItem=$("#testItem option:selected").val();
	var receiveTime=$("#receiveTime").val();
	var testPrincipal=$("#testPrincipal").val();
	var testServer=$("#testServer").val();
	var internalTestState=$("#internalTestState option:selected").val();
	var deploymentTime=$("#deploymentTime").val();
	var testerName=$("#testerName").val();
	var testerTel=$("#testerTel").val();
	var testerEmail=$("#testerEmail").val();
	var remark=$("#remark").val();
	if(receiveTime==""){
		layer.alert('请选择项目接收时间!', {icon: 0,title:"警告",btnAlign: 'c'});
		return;
	}
	if(deploymentTime==""){
		layer.alert('请选择计划环境搭建时间!', {icon: 0,title:"警告",btnAlign: 'c'});
		return;
	}
	if(receiveTime>deploymentTime){
		layer.alert('计划环境搭建时间不能早于项目接收时间!', {icon: 0,title:"警告",btnAlign: 'c'});
		return;
	}
	if(internalTestState==""){
		layer.alert('请选择测试状态!', {icon: 0,title:"警告",btnAlign: 'c'});
		return;
	}
	$.ajax({
		url:basePath+'internalController/addOrUpdateInternal',
		type:"POST",
		data:{
			'id':id,
			'baseInfoId':baseInfoId,
			'testItem':testItem,
			'receiveTime':receiveTime,
			'testPrincipal':testPrincipal,
			'testServer':testServer,
			'internalTestState':internalTestState,
			'deploymentTime':deploymentTime,
			'testerName':testerName,
			'testerTel':testerTel,
			'testerEmail':testerEmail,
			'remark':remark
		},
		async:false,
		success:function(data){
			if(data.mes=='success'){
				parent.$("#searchbtn").click();
				closeWindow();
				parent.messageBox(data);
			}else{
				var errors=data.errors;
				$.each(errors,function(i){
					$("#"+errors[i].field).focus();
					$("#"+errors[i].field).blur();
				});
			}
		}
	});

	
}

/**
 * 关闭弹出层
 */
function closeWindow(){
	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	parent.layer.close(index);
}