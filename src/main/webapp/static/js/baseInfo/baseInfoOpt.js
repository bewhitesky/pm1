$(document).ready(function(){
	initCombobox("project_department","pDepartment");
	initCombobox("project_type","pType");
	initCombobox("test_type","testType");
	initCombobox("project_level","pLevel");
	initCombobox("third_test","thirdTest");
	initCombobox("d_org","dorg");
	layui.use('form', function(){
	  var form = layui.form; 
	  form.render('select');
	});
//	validateForm("addBaseInfoForm");
});

function initDateInput(select){
	layui.use('laydate', function(){
	  var laydate = layui.laydate;
	  	laydate.render({ 
		  elem: select+' #internalTime'
		});
	
		laydate.render({ 
		  elem:  select+' #factoryTime'
		});
	
		laydate.render({ 
		  elem:  select+' #thirdTime'
		});
		
		laydate.render({ 
		  elem:  select+' #demandExpTime'
		});
		
		laydate.render({ 
		  elem:  select+' #demandActTime'
		});
		
		laydate.render({ 
		  elem:  select+' #designExpTime'
		});
		
		laydate.render({ 
		  elem:  select+' #designActTime'
		});
	});
}

function submitBaseInfo(){
	if(!$("#addBaseInfoForm").form("validate")){
		preStep();
		return;
	}
	var pDepartment=$("#addBaseInfoForm #pDepartment").val();
	var pType=$("#addBaseInfoForm #pType").val();
	var testType1=$("#addBaseInfoForm #testType1:checked").val()==undefined ?"":$("#addBaseInfoForm #testType1:checked").val();
	var testType2=$("#addBaseInfoForm #testType2:checked").val()==undefined ?"":$("#addBaseInfoForm #testType2:checked").val();
	var testType3=$("#addBaseInfoForm #testType3:checked").val()==undefined ?"":$("#addBaseInfoForm #testType3:checked").val();
	var testType4=$("#addBaseInfoForm #testType4:checked").val()==undefined ?"":$("#addBaseInfoForm #testType4:checked").val();
	var testType5=$("#addBaseInfoForm #testType5:checked").val()==undefined ?"":$("#addBaseInfoForm #testType5:checked").val();
//	var testType =  testType1 + (testType1 == "" ? "" : "+") + 
//					testType2 + (testType2 == "" ? "" : "+") + 
//					testType3 + (testType3 == ""? "" : "+") + 
//					testType4 + (testType4 == ""? "" : "+") + testType5;
	var testType="";
	var testTypes=new Array();
	if(testType1!="")testTypes.push(testType1);
	if(testType2!="")testTypes.push(testType2);
	if(testType3!="")testTypes.push(testType3);
	if(testType4!="")testTypes.push(testType4);
	if(testType5!="")testTypes.push(testType5);
	$.each(testTypes,function(i,type){
		if(testTypes.length==1 || testTypes.length==(i+1)){
			testType=testType+type;
		}else{
			testType=testType+type+"+";
		}
	});
	//	var testType=$("#addBaseInfoForm #testType").combobox('getText');
	var pLevel=$("#addBaseInfoForm #pLevel").val();
	var dorg=$("#addBaseInfoForm #dorg").val();
	var pName=$("#addBaseInfoForm #pName").val();
	var productName=$("#addBaseInfoForm #productName").val();
	var internalTime=$("#addBaseInfoForm2 #internalTime").val();
	var factoryTime=$("#addBaseInfoForm2 #factoryTime").val();
	var thirdTime=$("#addBaseInfoForm2 #thirdTime").val();
	var demandExpTime=$("#addBaseInfoForm2 #demandExpTime").val();
	var demandActTime=$("#addBaseInfoForm2 #demandActTime").val();
	var designExpTime=$("#addBaseInfoForm2 #designExpTime").val();
	var designActTime=$("#addBaseInfoForm2 #designActTime").val();
	
	var testYear=$("#addBaseInfoForm2 #testYear").val();
	var remark=$("#addBaseInfoForm #remark").val();
	var id=$("#addBaseInfoForm #id").val();
	var pmName=$("#addBaseInfoForm #pmName").val();
	var pmTel=$("#addBaseInfoForm #pmTel").val();
	var pmEmail=$("#addBaseInfoForm #pmEmail").val();
	var thirdTest=$("#addBaseInfoForm #thirdTest").val();
//	if(internalTime>factoryTime||internalTime>thirdTime
//			||factoryTime>thirdTime){
//		layer.alert('请注意计划内部测试时间<出厂测试时间<第三方测试时间!', {icon: 0,title:"警告",btnAlign: 'c'});
//		return;
//	}
	$.ajax({
		url:basePath+'/baseInfoController/addOrUpdateBaseInfo',
		type:"POST",
		data:{
			'id':id,
			'pDepartment':pDepartment,
			'pType':pType,
			'testType':testType,
			'pLevel':pLevel,
			'pName':pName,
			'productName':productName,
			'internalTime':internalTime,
			'factoryTime':factoryTime,
			'thirdTime':thirdTime,
			'demandExpTime':demandExpTime,
			'demandActTime':demandActTime,
			'designExpTime':designExpTime,
			'designActTime':designActTime,
			'testYear':testYear,
			'remark':remark,
			'pmName':pmName,
			'pmTel':pmTel,
			'pmEmail':pmEmail,
			'thirdTest':thirdTest,
			'dorg':dorg
		},
		async:false,
		success:function(data){
			if(data.mes=='success'){
				$("#baseInfoTable").datagrid("reload");
				closeWindow();
				messageBox(data);
			}else if(data.success=='false'){
				messageBox(data)
			}else{
				messageBox(data);
				var errors=data.errors;
				$.each(errors,function(i){
					$("#"+errors[i].field).focus();
					$("#"+errors[i].field).blur();
				});
			}
		}
	});	
}

/************************新增项目基础信息*******************************/
function addBaseInfo(){
	openWindow({
		title:"新增项目基础信息",
		href:basePath+'/jsp/baseInfo/addBaseInfo.jsp',
		width:800,    
	    height:620,    
	    modal:true,
	    onLoad:function(){
	    	var date=new Date();
			$("#addBaseInfoForm2 #testYear").val(date.getFullYear());
			initCombo("project_department","addBaseInfoForm #pDepartment");
			initCombo("project_type","addBaseInfoForm #pType");
			initCombo("test_type","addBaseInfoForm #testType");
			initCombo("project_level","addBaseInfoForm #pLevel");
			initCombo("third_test","addBaseInfoForm #thirdTest");
			initCombo("d_org","addBaseInfoForm #dorg");
			initCombo("test_state","addBaseInfoForm testState");
			
			$("#addBaseInfoForm #testType").combobox({
				onClick:function(value){
					if(value[value.key]==null){
						return;
					}
				}
			})
			
			initDateInput("#addBaseInfoForm2");
	    }
	});
}
/*************************修改信息*********************************************/
function updateBaseInfo(){
	var datas=$("#baseInfoTable").datagrid("getSelections");
	if(datas.length>1){
		layer.alert('只允许对一行数据进行修改!', {icon: 0,title:"警告",btnAlign: 'c'});
		return;
	}
	if(datas.length==0){
		layer.alert('请选择一行数据进行修改!', {icon: 0,title:"警告",btnAlign: 'c'});
		return;
	}
	var pDepartment=datas[0].pdepartment;
	var pType=datas[0].ptype;
	var testType=datas[0].testType;
	var testTypes=testType.split("+");
	var pLevel=datas[0].plevel;
	var pName=datas[0].pname;
	var productName=datas[0].productName;
	var internalTime=datas[0].internalTime;
	var factoryTime=datas[0].factoryTime;
	var thirdTime=datas[0].thirdTime;
	var testYear=datas[0].testYear;
	var remark=datas[0].remark;
	var pmName=datas[0].pmName;
	var pmTel=datas[0].pmTel;
	var pmEmail=datas[0].pmEmail;
	var thirdTest=datas[0].thirdTest;
	var id=datas[0].id;
	var dorg=datas[0].dorg;
//	var codeCheck=datas[0].codeCheck;
	var testState=datas[0].testState;
	openWindow({
		title:"修改项目基础信息",
		href:basePath+'/jsp/baseInfo/addBaseInfo.jsp',
		width:800,    
	    height:700,    
	    modal:true,
	    onLoad:function(){
	    	initCombo("project_department","addBaseInfoForm #pDepartment");
			initCombo("project_type","addBaseInfoForm #pType");
			initCombo("test_type","addBaseInfoForm #testType");
			initCombo("project_level","addBaseInfoForm #pLevel");
			initCombo("third_test","addBaseInfoForm #thirdTest");
			initCombo("d_org","addBaseInfoForm #dorg");
			initCombo("test_state","addBaseInfoForm testState");
			$("#addBaseInfoForm").form('disableValidation');
			$("#addBaseInfoForm #id").val(id);
	      	$("#addBaseInfoForm #pmName").val(pmName);
	      	$("#addBaseInfoForm #pmTel").val(pmTel);
	      	$("#addBaseInfoForm #pmEmail").val(pmEmail);
	      	$("#addBaseInfoForm #remark").val(remark);
	      	$("#addBaseInfoForm2 #testYear").val(testYear);
	      	if(thirdTime==null || thirdTime==""){
	      		$("#addBaseInfoForm2 #thirdTime").val("");
	      	}else{
	      		$("#addBaseInfoForm2 #thirdTime").val(timestampFormat(thirdTime,"yyyy-MM-dd"));
	      	}
	      	if(factoryTime==null || factoryTime==""){
	      		$("#addBaseInfoForm2 #factoryTime").val("");
	      	}else{
	      		$("#addBaseInfoForm2 #factoryTime").val(timestampFormat(factoryTime,"yyyy-MM-dd"));
	      	}
	      	if(internalTime==null || internalTime==""){
	      		$("#addBaseInfoForm2 #internalTime").val("");
	      	}else{
	      		$("#addBaseInfoForm2 #internalTime").val(timestampFormat(internalTime,"yyyy-MM-dd"));
	      	}
	      	
	      	$("#addBaseInfoForm #productName").val(productName);
	      	$("#addBaseInfoForm #pName").val(pName);
	      	
	      	$("#addBaseInfoForm #pLevel").combobox('setValue',pLevel);
	      	$.each(testTypes,function(i,type){
				switch(type){
					case "功能/非功能测试":$("#addBaseInfoForm #testType1").prop("checked",true);
					break;
					case "安全功能测试":$("#addBaseInfoForm #testType2").prop("checked",true);
					break;
					case "渗透测试":$("#addBaseInfoForm #testType3").prop("checked",true);
					break;
					case "代码扫描":$("#addBaseInfoForm #testType4").prop("checked",true);
					break;
					case "性能测试":$("#addBaseInfoForm #testType5").prop("checked",true);
					break;	
				}
			});
	      	$("#addBaseInfoForm #pType").combobox('setValue',pType);
	      	$("#addBaseInfoForm #pDepartment").combobox('setValue',pDepartment);
	      	$("#addBaseInfoForm #thirdTest").combobox('setValue',thirdTest);
	      	$("#addBaseInfoForm #dorg").combobox('setValue',dorg);
//	      	$("#addBaseInfoForm #codeCheck").combobox('setValue',codeCheck);
	      	$("#addBaseInfoForm #testState").combobox('setValue',testState);
	      	$("#addBaseInfoForm").form('enableValidation');
	      	
	      	initDateInput("#addBaseInfoForm2");
	    }
	});
}

/**********************删除用户*******************************************/
function deleteBaseInfo(){
	var baseInfolist=$("#baseInfoTable").datagrid("getSelections");
	if(baseInfolist.length==0){
		layer.alert('至少选择一行数据进行删除!', {icon: 0,title:"警告",btnAlign: 'c'});
		return;
	}
	var ids='';
	$.each(baseInfolist,function(i){
		if(i==(baseInfolist.length-1)){
			ids=ids+baseInfolist[i].id;
		}else{
			ids=ids+baseInfolist[i].id+',';
		}
	});
	layer.confirm('确定删除所选项？', {
	  btn: ['确定','取消'] //按钮
	}, function(){
	  	$.ajax({
		url:basePath+"/baseInfoController/deleteBaseInfo",
		type:"POST",
		async:false,
		data:{
			'ids':ids
		},
		beforeSend:function(){
			index=layer.msg('正在删除，请稍候!', {
					  icon: 16
					  ,shade: 0.01
					});
		},
		success:function(data){
			if(data.mes=="success"){
//				searchBaseInfo();
				$("#baseInfoTable").datagrid("reload");
				layer.close(index);
				messageBox(data);
			}else{
				messageBox(data);
			}
		}
	})
	}, function(){
	});
}
function nextStep(){
	if(!$("#addBaseInfoForm").form("validate")){
		layer.alert('请先维护好当前tab页!', {icon: 0,title:"警告",btnAlign: 'c'});
		return;
	}
	var tab = $('#tt').tabs('getSelected');
	var index = $('#tt').tabs('getTabIndex',tab);
	$("#tt").tabs("enableTab",index+1);
	$("#tt").tabs("select",index+1);
	$("#tt").tabs("disableTab",index);
}
function preStep(){
	var tab = $('#tt').tabs('getSelected');
	var index = $('#tt').tabs('getTabIndex',tab);
	$("#tt").tabs("enableTab",index-1);
	$("#tt").tabs("select",index-1);
	$("#tt").tabs("disableTab",index);
}
/*************************excel导出*****************************/
function exportBaseInfo(){
	var pDepartment=$("#pDepartment option:selected").val();
	var pType=$("#pType option:selected").val();
	var internalTime=$("#internalTime").val();
	var pName=$("#pName").val();
	var testYear=$("#testYear").val();
	var factoryTime=$("#factoryTime").val();
	var thirdTime=$("#thirdTime").val();
	var baseInfoDto={
		'pDepartment':pDepartment,
		'internalTime':internalTime,
		'pName':pName,
		'testYear':testYear,
		'factoryTime':factoryTime,
		'thirdTime':thirdTime
	};
//	window.location.href=basePath+'/baseInfoController/exportExcel?baseInfoDto='+baseInfoDto;
	window.location.href=basePath+'/baseInfoController/exportExcel?json='+JSON.stringify(baseInfoDto);
}

/*************************excel上传*********************************/
function upload(){
	layui.use('upload', function(){
	  var upload = layui.upload;
	   
	  //执行实例
	  var uploadInst = upload.render({
	    elem: '#importExcel',
	    url: basePath+'/baseInfoController/importExcel', //上传接口
	    accept:"file",
	    acceptMime:"file/xls,file/xlsx",
	    exts:'xls|xlsx',
	    done: function(res){
	      messageBox(res);
	      searchBaseInfo();
	    },
	    error: function(){
	      //请求异常回调
	    }
	  });
	});
}

/*************************模板下载*********************************/
function download(){
	window.location.href=basePath+'/baseInfoController/download';
}
