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
	initDateInput();
	upload();
	var date=new Date();
	$("#testYear").val(date.getFullYear());
});


function initSelect(){
	pDepartmentMap=initCombobox("project_department","pDepartment");
	pTypeMap=initCombobox("project_type","pType");
	testTypeMap=initCombobox("test_type",null);
	pLevelMap=initCombobox("project_level",null);
	thirdTestMap=initCombobox("third_test",null);
	testStateMap=initCombobox("test_state","testState");
	dorgMap=initCombobox("d_org","dorg");

}
/**
 * 表格初始化
 */
function initTable(_call){
	layui.use('table', function(){
	  table = layui.table;  
	  table.render({
	  	id:'baseInfo',
	    elem: '#baseInfoTable',
	    url: basePath+'/baseInfoController/selectBaseInfo',
	    method:'post',
	    cellMinWidth: 80 ,//全局定义常规单元格的最小宽度，layui 2.2.1 新增,
	    height: 'full-200',
	    page: true, //开启分页
	    cols: [[
	      {type:'checkbox',fixed: 'left' },
	      {field:'pDepartment', fixed: 'left' , title: '所属部门',sort:false,align:'center',width:'10%',
		      templet: function(d){
		      	if(d.pdepartment==null)return '';
		        return pDepartmentMap[d.pdepartment];
		      }
		  },
	      {field:'pname', fixed: 'left' , title: '项目名称', sort: false,align:'center',width:'10%'},
	      {field:'ptype',  title: '项目类别', sort: false,align:'center',width:'6%',
		      templet: function(d){
		      	if(d.ptype==null)return '';
		        return pTypeMap[d.ptype];
		      }
		  },
	      {field:'testType',  title: '测试需求', sort: false,align:'center',width:'10%',
		      templet: function(d){
		      	if(d.testType==null)return '';
		        return testTypeMap[d.testType];
		      }
		  },
	      {field:'plevel',  title: '等保等级', sort: false,align:'center',width:'6%',
		      templet: function(d){
		      	if(d.plevel==null)return '';
		      	return pLevelMap[d.plevel];     
		      }
		  },
	      {field:'internalTime',  title: '计划内部测试时间', sort: true,width:'10%',align:'center',
		      templet: function(d){
		      	if(d.internalTime==null || d.internalTime==''){
		      			return '';
		      	}else{
		      		 return timestampFormat(d.internalTime,"yyyy-MM-dd");
		      	}  
		      }
	      },
	      {field:'factoryTime',  title: '计划出厂测试时间', sort: true,width:'10%',align:'center',
		      templet: function(d){
		        if(d.factoryTime==null || d.factoryTime==''){
		      			return '';
		      	}else{
		      		 return timestampFormat(d.factoryTime,"yyyy-MM-dd");
		      	}  
		      }
		  },
	      {field:'thirdTime',  title: '计划第三方测试时间', sort: true,width:'10%',align:'center',
		      templet: function(d){
		         if(d.thirdTime==null || d.thirdTime==''){
		      			return '';
		      	}else{
		      		 return timestampFormat(d.thirdTime,"yyyy-MM-dd");
		      	}  
		      }
		  },
		  {field:'thirdTest',  title: '第三方测试机构',sort:false,align:'center',width:'9%',
		      templet: function(d){
		      	if(d.thirdTest==null)return '';
		        return thirdTestMap[d.thirdTest];
		      }
		  },
		  {field:'dorg',  title: '研发机构',sort:false,align:'center',width:'6%',
		      templet: function(d){
		      	if(d.dorg==null)return '';
		        return dorgMap[d.dorg];
		      }
		  },
		  {field:'testState',  title: '测试状态', sort: false,align:'center',width:'6%'},
	      {field:'remark',  title: '计划备注', sort: false,align:'center',width:'6%'},
	      {field:'testYear',  title: '测试年度', sort: false,align:'center',width:'6%'},
	      {field:'pmName',  title: '项目经理', sort: false,align:'center',width:'6%'},
	      {field:'pmTel',  title: '联系电话', sort: false,align:'center',width:'6%'},
	      {field:'pmEmail',  title: '邮箱地址', sort: false,align:'center',width:'6%'}
	//    {fixed: 'right', width:150, align:'center', toolbar: '#toolbar'} //这里的toolbar值是模板元素的选择器
	    ]],
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
		    
	    }
	  });
	});
}
function initDateInput(){
	layui.use('laydate', function(){
	  var laydate = layui.laydate;
	  	laydate.render({ 
		  elem: '#internalTime'
		  ,range: '~' //或 range: '~' 来自定义分割字符
		});
	
		laydate.render({ 
		  elem: '#factoryTime'
		  ,range:'~'  //或 range: '~' 来自定义分割字符
		});
	
		laydate.render({ 
		  elem: '#thirdTime'
		  ,range:'~' //或 range: '~' 来自定义分割字符
		});
	});
}


function searchBaseInfo(){
	var pDepartment=$("#pDepartment option:selected").val();
	var pType=$("#pType option:selected").val();
	var dorg=$("#dorg option:selected").val();
	var internalTime=$("#internalTime").val();
	var pName=$("#pName").val();
	var testYear=$("#testYear").val();
	var factoryTime=$("#factoryTime").val();
	var thirdTime=$("#thirdTime").val();
	var submitFlag = true;
	$("#pName").isValid(function(v){
		if(v == false)
			submitFlag = false;
	});
	$("#testYear").isValid(function(v){
		if(v == false)
			submitFlag = false;
	});
	if(submitFlag == false){
		return;
	}
	var testState=$("#testState").val();
	var testStateValue;
	if(testState=="" || testState==null){
		testStateValue=""
	}else{
		testStateValue=testStateMap[testState];
	}
	table.reload("baseInfo",{
		url:basePath+'/baseInfoController/selectBaseInfo',
		where:{
			'pDepartment':pDepartment,
			'internalTime':internalTime,
			'pName':pName,
			'pType':pType,
			'testYear':testYear,
			'factoryTime':factoryTime,
			'thirdTime':thirdTime,
			'testState':testStateValue,
			'dorg':dorg
		}
	});
}

function reset(){
	$("#pDepartment").val("");
	$("#internalTime").val("");
	$("#pName").val("");
	$("#pType").val("");
	$("#testYear").val("2018");
	$("#factoryTime").val("");
	$("#thirdTime").val("");
}


/**
 * 添加用户
 */
function addBaseInfo(){
layer.open({
	  title:'添加项目基础信息',
	  type: 2, 
	  area: ['800px', '750px'],
	  content: basePath+'/jsp/baseInfo/addBaseInfo.jsp', //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
	  success: function(layero, index){
	  	var body = layer.getChildFrame('body', index);
	  	var date=new Date();
	  	body.contents().find("#testYear").val(date.getFullYear());
	  	body.contents().find("#validate").click();

  	  }
	}); 
}

/**
 * 修改用户
 */
function updateBaseInfo(){
	var checkStatus = table.checkStatus('baseInfo'); //test即为基础参数id对应的值
	var baseInfolist=checkStatus.data;
	if(baseInfolist.length>1){
		layer.alert('只允许对一行数据进行修改!', {icon: 0,title:"警告",btnAlign: 'c'});
		return;
	}
	if(baseInfolist.length==0){
		layer.alert('请选择一行数据进行修改!', {icon: 0,title:"警告",btnAlign: 'c'});
		return;
	}
	var pDepartment=baseInfolist[0].pdepartment;
	var pType=baseInfolist[0].ptype;
	var testType=baseInfolist[0].testType;
	var pLevel=baseInfolist[0].plevel;
	var pName=baseInfolist[0].pname;
	var productName=baseInfolist[0].productName;
	var internalTime=baseInfolist[0].internalTime;
	var factoryTime=baseInfolist[0].factoryTime;
	var thirdTime=baseInfolist[0].thirdTime;
	var testYear=baseInfolist[0].testYear;
	var remark=baseInfolist[0].remark;
	var pmName=baseInfolist[0].pmName;
	var pmTel=baseInfolist[0].pmTel;
	var pmEmail=baseInfolist[0].pmEmail;
	var thirdTest=baseInfolist[0].thirdTest;
	var id=baseInfolist[0].id;
	var dorg=baseInfolist[0].dorg;
	var codeCheck=baseInfolist[0].codeCheck;
	layer.open({
		  title:'修改项目基础信息',
		  type: 2, 
		  area: ['800px', '750px'],
		  content: basePath+'/jsp/baseInfo/addBaseInfo.jsp', //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
		  success: function(layero, index){
	      	var body = layer.getChildFrame('body', index);
	      	body.contents().find("#id").val(id);
	      	body.contents().find("#pmName").val(pmName);
	      	body.contents().find("#pmTel").val(pmTel);
	      	body.contents().find("#pmEmail").val(pmEmail);
	      	body.contents().find("#remark").val(remark);
	      	body.contents().find("#testYear").val(testYear);
	      	if(thirdTime==null || thirdTime==""){
	      		body.contents().find("#thirdTime").val("");
	      	}else{
	      		body.contents().find("#thirdTime").val(timestampFormat(thirdTime,"yyyy-MM-dd"));
	      	}
	      	if(factoryTime==null || factoryTime==""){
	      		body.contents().find("#factoryTime").val("");
	      	}else{
	      		body.contents().find("#factoryTime").val(timestampFormat(factoryTime,"yyyy-MM-dd"));
	      	}
	      	if(internalTime==null || internalTime==""){
	      		body.contents().find("#internalTime").val("");
	      	}else{
	      		body.contents().find("#internalTime").val(timestampFormat(internalTime,"yyyy-MM-dd"));
	      	}

	      	body.contents().find("#productName").val(productName);
	      	body.contents().find("#pName").val(pName);
	      	layui.use('layer',function(){
	  			var layer = layui.layer;
	  			layer.select(body.contents().find("#pLevel")).setValue(pLevel);
	  			layer.select(body.contents().find("#testType")).setValue(testType);
	  			layer.select(body.contents().find("#pType")).setValue(pType);
	  			layer.select(body.contents().find("#pDepartment")).setValue(pDepartment);
	  			layer.select(body.contents().find("#thirdTest")).setValue(thirdTest);
	  			layer.select(body.contents().find("#dorg")).setValue(dorg);
	  			layer.select(body.contents().find("#codeCheck")).setValue(codeCheck);
			});	
			body.contents().find("#validate").click();
	  	  }
		});
}

/**
 * 删除用户
 */
function deleteBaseInfo(){
	var index=0;
	var checkStatus = table.checkStatus('baseInfo'); //test即为基础参数id对应的值
	var baseInfolist=checkStatus.data;
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
				table.reload("baseInfo",{
					url:basePath+'/baseInfoController/selectBaseInfo'	
				});
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
/**
 * excel导出
 */
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

/**
 * excel上传
 */
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


function download(){
	window.location.href=basePath+'/baseInfoController/download';
}

