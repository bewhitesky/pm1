var table;
var pDepartmentMap;
var pTypeMap;
var testTypeMap;
var pLevelMap;
var thirdTestMap;
$(document).ready(function(){	
	initTable(initSelect());
	initDateInput();
	upload();
});


function initSelect(){
	pDepartmentMap=initCombobox("project_department","pDepartment");
	pTypeMap=initCombobox("project_type","pType");
	testTypeMap=initCombobox("test_type",null);
	pLevelMap=initCombobox("project_level",null);
	thirdTestMap=initCombobox("third_test",null);
}
/**
 * 表格初始化
 */
function initTable(_call){
	layui.use('table', function(){
	  table = layui.table;  
	  table.render({
	  	id:'securityCase',
	    elem: '#securityCaseTable',
	    url: basePath+'/caseController/selectCase',
	    method:'post',
	    cellMinWidth: 80 ,//全局定义常规单元格的最小宽度，layui 2.2.1 新增,
	    height: 'full-200',
	    page: true, //开启分页
	    cols: [[
	      {type:'checkbox'},
	      {field:'num' , title: '序号',sort:false,align:'center',width:'5%'
//	      ,
//		      templet: function(d){
//		      	
//		      }
		  },
	      {field:'checkModule' , title: '测试模块', sort: false,align:'center',width:'6%'
//	      ,
//		      templet: function(d){
//		      }
		  },
	      {field:'checkItem',  title: '检测项', sort: false,align:'center',width:'6%'
//	      ,
//		      templet: function(d){
//		      }
		  },
	      {field:'checkPoint',  title: '检测点', sort: false,align:'center',width:'23%'
	      ,
		      templet: function(d){
		      	debugger;
		      	var data=d.checkPoint;		      	
		      	var width=this.width;
		      	var ss=new Array();
				var aa=info(data,width,ss);
		      	return aa;
		      }
		  },
	      {field:'testPoint',  title: '测试点', sort: false,align:'center',width:'23%'
//	      ,
//		      templet: function(d){   
//		      }
		  },
	      {field:'testStep',  title: '测试步骤', sort: false,width:'28%',align:'center'
//	      ,
//		      templet: function(d){  
//		      }
	      },
	      {field:'testLevel',  title: '等级', sort: false,width:'6%',align:'center'
//	      ,
//		      templet: function(d){
//		      }
		  }

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
	table.reload("baseInfo",{
		url:basePath+'/baseInfoController/selectBaseInfo',
		where:{
			'pDepartment':pDepartment,
			'internalTime':internalTime,
			'pName':pName,
			'pType':pType,
			'testYear':testYear,
			'factoryTime':factoryTime,
			'thirdTime':thirdTime
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
	  title:'添加用户',
	  type: 2, 
	  area: ['800px', '700px'],
	  content: basePath+'/jsp/project/addBaseInfo.jsp', //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
	  success: function(layero, index){
	  	var body = layer.getChildFrame('body', index);
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
	layer.open({
		  title:'修改用户',
		  type: 2, 
		  area: ['800px', '700px'],
		  content: basePath+'/jsp/project/addBaseInfo.jsp', //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
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
				searchBaseInfo();
				layer.close(index);
				messageBox(data);
			}else{
				messageBox(data);
			}
		}
	})
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
	window.location.href=basePath+'/baseInfoController/exportExcel?baseInfoDto='+JSON.stringify(baseInfoDto);
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


function info(data,width,ss){
	var str="";
	var len=data.gblen()*8;//获取数据字节长度
	if(len>width){
		var s1=data.gbstr(width/8);//获取前半部分字符串
		ss.push(s1+"<br>");
		var s2=data.gbelse(width/8+2);//获取剩余部分字符串
		str=info(s2,width,ss);
	}else{
		ss.push(data);
		$.each(ss,function(i){
			str=str+ss[i];	
		});	
	}
	return str;     	
}
