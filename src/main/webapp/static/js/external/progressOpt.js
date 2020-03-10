var testStateMap;
$(document).ready(function(){
	initCombobox("current_state","currStateOne");
	initCombobox("current_state","currStateTwo");
	testStateMap=initCombobox("test_state","testState");
	layui.use('form', function(){
	  var form = layui.form; 
	  form.render('select'); 
	});
	layui.use('element', function(){
		var element = layui.element;
		var form = layui.form;
		form.on('select(currStateOne)',function(data){
			if($("#currStateOne").val()==null||$("#currStateOne").val()==""){
				$("#currStateOneMsg").html("&nbsp;&nbsp;当前阶段1不能为空");
			}else{
				$("#currStateOneMsg").html("");
			}
		});
		form.on('select(currStateTwo)',function(data){
			if($("#currStateTwo").val()==null||$("#currStateTwo").val()==""){
				$("#currStateTwoMsg").html("&nbsp;&nbsp;当前阶段2不能为空");
			}else{
				$("#currStateTwoMsg").html("");
			}
		});
//		form.on('select(state)', function(data){
//			testState= data.elem[data.elem.selectedIndex].text;
//		}); 
	});
	

		    
});


layui.use('laydate', function(){
  var laydate = layui.laydate;
  	laydate.render({ 
	  elem: '#internalCompleteTime'
	  ,range: false //或 range: '~' 来自定义分割字符
	});

	laydate.render({ 
	  elem: '#factoryCompleteTime'
	  ,range:false //或 range: '~' 来自定义分割字符
	});

	laydate.render({ 
	  elem: '#thirdCompleteTime'
	  ,range:false //或 range: '~' 来自定义分割字符
	});
});

function submitProgress(){
	var submitFlag = false;
	$("#addProgressForm").isValid(function(v){
		if(v == true)
			submitFlag = true;
	});
	if(submitFlag == false){
		return;
	}
	var currStateOne=$("#currStateOne option:selected").val();
//	var currStateTwo=$("#currStateTwo option:selected").val();
	var productName=$("#productName").val();
	var internalCompleteTime=$("#internalCompleteTime").val();
	var factoryCompleteTime=$("#factoryCompleteTime").val();
	var thirdCompleteTime=$("#thirdCompleteTime").val();
	var testState=$("#testState").val();
	var testStateValue;
	if(testState=="" || testState==null){
		testStateValue=""
	}else{
		testStateValue=testStateMap[testState];
	}

	var remark=$("#remark").val();
	var id=$("#id").val();
//	if(currStateOne==""){
//		layer.alert('请选择当前阶段1!', {icon: 0,title:"警告",btnAlign: 'c'});
//		return;
//	}
//	if(currStateTwo==""){
//		layer.alert('请选择当前阶段2!', {icon: 0,title:"警告",btnAlign: 'c'});
//		return;
//	}
//	if(internalCompleteTime==""){
//		layer.alert('请选择内部测试完成时间!', {icon: 0,title:"警告",btnAlign: 'c'});
//		return;
//	}
//	if(factoryCompleteTime==""){
//		layer.alert('请选择出厂测试完成时间!', {icon: 0,title:"警告",btnAlign: 'c'});
//		return;
//	}
//	if(thirdCompleteTime==""){
//		layer.alert('请选择第三方测试完成时间!', {icon: 0,title:"警告",btnAlign: 'c'});
//		return;
//	}
//	if(internalCompleteTime>factoryCompleteTime||internalCompleteTime>thirdCompleteTime
//			||factoryCompleteTime>thirdCompleteTime){
//		layer.alert('请注意内部测试完成时间<出厂测试完成时间<第三方测试完成时间!', {icon: 0,title:"警告",btnAlign: 'c'});
//		return;
//	}
	$.ajax({
		url:basePath+'/progressController/updateProgress',
		type:"POST",
		data:{
			'id':id,
			'currStateOne':currStateOne,
//			'currStateTwo':currStateTwo,
			'productName':productName,
			'internalCompleteTime':internalCompleteTime,
			'factoryCompleteTime':factoryCompleteTime,
			'thirdCompleteTime':thirdCompleteTime,
			'testState':testStateValue,
			'remark':remark
		},
		async:false,
		success:function(data){
			if(data.mes=='success'){
				parent.$("#searchbtn").click();
				closeWindow();
				parent.messageBox(data);
			}else{
				
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