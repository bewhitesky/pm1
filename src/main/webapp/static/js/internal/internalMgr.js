var table;
var pDepartmentMap;
var currStateMap;
var pTypeMap;
var testTypeMap;
var testStateMap;
var testPrincipalMap;
$(document).ready(function(){
	initTable(initSelect());
	initDateInput();
});

function initSelect(){
	pDepartmentMap=initCombobox("project_department","pDepartment");
	pTypeMap=initCombobox("project_type","pType");
	pLevelMap=initCombobox("project_level","pLevel");
	testStateMap=initCombobox("test_state","internalTestState");
	testPrincipalMap=initCombobox("test_principal","testPrincipal");
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
/**
 * 表格初始化
 */
function initTable(_call){
	layui.use('table', function(){
	  var complete={'id':''};
	  table = layui.table;  
	  table.render({
	  	id:'internal',
	    elem: '#internalTable',
	    url: basePath+'/internalController/selectInternalTest',
	    method:'post',
	    cellMinWidth: 86 ,//全局定义常规单元格的最小宽度，layui 2.2.1 新增,
	    height: 'full-120',
	    page: true, //开启分页
	    cols: [[
	      {type:'checkbox',fixed: 'left' },
	      {field:'pDepartment',  fixed: 'left' ,title: '所属部门',sort:false,align:'center',width:'10%',
		      templet: function(d){
		      	if(d.baseInfo.pdepartment==null)return '';
		        return pDepartmentMap[d.baseInfo.pdepartment];
		      }
		  },
	      {field:'pname', fixed: 'left' , title: '产品名称', sort: false,align:'center',width:'12%',
		      templet: function(d){
		        return d.baseInfo.pname;
		      }
		  },
		  {field:'plevel' , title: '等保等级', sort: false,align:'center',
		      templet: function(d){
		      	if(d.baseInfo.plevel==null)return '';
		        return pLevelMap[d.baseInfo.plevel];
		      }
		  },
	      {field:'ptype' , title: '项目类别', sort: false,align:'center',
		      templet: function(d){
		      	if(d.baseInfo.ptype==null)return '';
		        return pTypeMap[d.baseInfo.ptype];
		      }
		  },
	      {field:'factoryTime' , title: '计划出厂测试时间', sort: true,width:'10%',align:'center',
		      templet: function(d){
		      	if(d.baseInfo.factoryTime==null || d.baseInfo.factoryTime==''){
		      			return '';
		      	}else{
		      		 return timestampFormat(d.baseInfo.factoryTime,"yyyy-MM-dd");
		      	}  
		      }
	      },
	      {field:'testItem', title: '测试项', sort: false,align:'center',width:'8%',
		      templet: function(d){
		      	if(d.internal==null || d.internal.testItem==null)return '';
		        return d.internal.testItem;
		      }
		  },
		  {field:'receiveTime',  title: '项目接收时间', sort: false,align:'center',width:'8%',
		      templet: function(d){
		        if(d.internal==null ||d.internal.receiveTime==null || d.internal.receiveTime==''){
		      			return '';
		      	}else{
		      		 return timestampFormat(d.internal.receiveTime,"yyyy-MM-dd");
		      	}
		      }
		  },
		  {field:'testServer', title: '测试服务器', sort: false,align:'center',width:'12%',
		      templet: function(d){
		      	if(d.internal==null ||d.internal.testServer==null)return '';
		        return d.internal.testServer;
		      }
		  },
		  {field:'internalTestState', title: '测试状态', sort: false,align:'center',width:'8%',
		      templet: function(d){
		      	if(d.internal==null ||d.internal.internalTestState==null)return '';
		        return testStateMap[d.internal.internalTestState];
		      }
		  },
		  {field:'remark', title: '备注', sort: false,align:'center',width:'8%',
		      templet: function(d){
		      	if(d.internal==null ||d.internal.remark==null)return '';
		        return d.internal.remark;
		      }
		  },
		  {field:'testPrincipal',  title: '测试负责人', sort: false,align:'center',width:'8%',
		      templet: function(d){
		        if(d.internal==null ||d.internal.testPrincipal==null)return '';
		        return testPrincipalMap[d.internal.testPrincipal];
		      }
		  },
		  {field:'deploymentTime', title: '计划环境搭建时间', sort: false,align:'center',width:'10%',
		      templet: function(d){
		      	if(d.internal==null ||d.internal.deploymentTime==null || d.internal.deploymentTime==''){
		      		return '';
		      	}else{
		      		return timestampFormat(d.internal.deploymentTime,"yyyy-MM-dd");
		      	}
		      }
		  },
		  {field:'testerName', title: '测试配合人员', sort: false,align:'center',width:'8%',
		      templet: function(d){
		      	if(d.internal==null ||d.internal.testerName==null)return '';
		        return d.internal.testerName;
		      }
		  },
		  {field:'testerTel',  title: '联系电话', sort: false,align:'center',width:'8%',
		      templet: function(d){
		      	if(d.internal==null ||d.internal.testerTel==null)return '';
		        return d.internal.testerTel;
		      }
		  },
		  {field:'testerEmail', title: '邮箱地址', sort: false,align:'center',width:'12%',
		      templet: function(d){
		      	if(d.internal==null ||d.internal.testerEmail==null)return '';
		        return d.internal.testerEmail;
		      }
		  },
		  {fixed: 'right', title:'操作' ,width:'6%', align:'center', toolbar: '#toolbar'} //这里的toolbar值是模板元素的选择器
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
			var internalItems=res.internalItems;
	    }
	  });
	  table.on('edit(internalOpt)', function(obj){
	  		var data = obj.data.complete;
	  		complete.id=data.id;
	  		complete[obj.field]=obj.value;
	  		
	  });
	  table.on('tool(internalOpt)', function(obj){
	  	var data = obj.data;
	  	  if(obj.event==='ItemInfo'){
	  	  	ItemInfo(data);
	  	  }	  	  	
	  });
	});
}

function ItemInfo(data){
	layer.open({
		  title:'测试信息',
		  type: 2, 
		  area: ['1200px', '650px'],
		  resize:true,
		  content: basePath+'/jsp/internal/internalItem.jsp',//这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
		  success: function(layero, index){	
			  	var body = layer.getChildFrame('body', index);
			  	body.contents().find("#id").val(data.internal.id);
			  	body.contents().find("#pName").val(data.baseInfo.pname);
			  	body.contents().find("#testItem").val(data.internal.testItem);
		}	  	
	}); 
}

function searchInternal(){
	var pName=$("#pName").val();
	var deploymentTime=$("#deploymentTime").val();
	var internalTestState=$("#internalTestState").val();
	var submitFlag = false;
	$("#pName").isValid(function(v){
		if(v == true)
			submitFlag = true;
	});
	if(submitFlag == false){
		return;
	}
	table.reload("internal",{
		url:basePath+'/internalController/selectInternalTest',
		where:{
			'pName':pName,
			'deploymentTime':deploymentTime,
			'internalTestState':internalTestState
		}
	});
}

function reset(){
	$("#factoryTime").val("");
	$("#thirdTime").val("");
	$("#pDepartment").val("");
	$("#internalCompleteTime").val("");
	$("#pName").val("");
	$("#testYear").val("2018");
	$("#factoryCompleteTime").val("");
	$("#thirdCompleteTime").val("");
	$("#currStateOne").val("");
	$("#currStateTwo").val("");
}

/**
 * 新增内部项目
 */
function addInternal(){
layer.open({
	  title:'新增内部测试信息',
	  type: 2, 
	  area: ['800px', '550px'],
	  content: basePath+'/jsp/internal/addInternal.jsp', //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
	  success: function(layero, index){
	  	
	  }
		 
}); 
}

/**
 * 首轮缺陷率
 */
function addOrUpdateDefect(){
layer.open({
	  title:'首轮缺陷率',
	  type: 2, 
	  area: ['1000px', '620px'],
	  content: basePath+'/jsp/internal/defectRate.jsp', //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
	  success: function(layero, index){
	  	
	  }
		 
}); 
}

/**
 * 修改内部项目
 */
function updateInternal(){
	var checkStatus = table.checkStatus('internal'); //test即为基础参数id对应的值
	var internallist=checkStatus.data;
	if(internallist.length>1){
		layer.alert('只允许对一行数据进行修改!', {icon: 0,title:"警告",btnAlign: 'c'});
		return;
	}
	if(internallist.length==0){
		layer.alert('请选择一行数据进行修改!', {icon: 0,title:"警告",btnAlign: 'c'});
		return;
	}
	var id=internallist[0].internal.id;
	var baseInfoId=internallist[0].internal.baseInfoId;
	var testItem=internallist[0].internal.testItem;
	var receiveTime=internallist[0].internal.receiveTime;
	var testPrincipal=internallist[0].internal.testPrincipal;
	var testServer=internallist[0].internal.testServer;
	var internalTestState=internallist[0].internal.internalTestState;
	var deploymentTime=internallist[0].internal.deploymentTime;
	var testerName=internallist[0].internal.testerName;
	var testerTel=internallist[0].internal.testerTel;
	var testerEmail=internallist[0].internal.testerEmail;
	var remark=internallist[0].internal.remark;
	layer.open({
		title:'修改内部测试信息',
		type: 2, 
		area: ['800px', '550px'],
		content: basePath+'/jsp/internal/addInternal.jsp', //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
		success: function(layero, index){
			var body = layer.getChildFrame('body', index);
	      	body.contents().find("#id").val(id);
	      	body.contents().find("#testPrincipal").val(testPrincipal);
	      	body.contents().find("#testServer").val(testServer);
	      	body.contents().find("#testerName").val(testerName);
	      	body.contents().find("#testerTel").val(testerTel);
	      	body.contents().find("#testerEmail").val(testerEmail);
	      	body.contents().find("#remark").val(remark);
	      	body.contents().find("#receiveTime").val(receiveTime==null?"":timestampFormat(receiveTime,"yyyy-MM-dd"));
	      	body.contents().find("#deploymentTime").val(deploymentTime=null?"":timestampFormat(deploymentTime,"yyyy-MM-dd"));
	      	layui.use('layer',function(){
	  			var layer = layui.layer;
	  			layer.select(body.contents().find("#pName")).setValue(baseInfoId);
	  			layer.select(body.contents().find("#testItem")).setValue(testItem);
	  			layer.select(body.contents().find("#internalTestState")).setValue(internalTestState);
			});

		}

	}); 
}


/**
 * 删除内部项目
 */
function deleteInternal(){
	var flag=true;
	var index=0;
	var checkStatus = table.checkStatus('internal'); //test即为基础参数id对应的值
	var internallist=checkStatus.data;
	if(internallist.length==0){
		layer.alert('至少选择一行数据进行删除!', {icon: 0,title:"警告",btnAlign: 'c'});
		return;
	}
	var ids='';
	$.each(internallist,function(i){
		if(i==(internallist.length-1)){
			ids=ids+internallist[i].internal.id;
		}else{
			ids=ids+internallist[i].internal.id+',';
		}
	});
	layer.confirm('确定删除所选项？', {
	  btn: ['确定','取消'] //按钮
	}, function(){
		$.ajax({
			url:basePath+"/internalController/deleteInternal",
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
	//				initTable();
					table.reload("internal",{
						url:basePath+'/internalController/selectInternalTest'
					});
					layer.close(index);
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
function exportInternal(){
	window.location.href=basePath+'/internalController/exportInternal';
//	window.location.href=basePath+'/progressController/exportExcel?projectDto='+JSON.stringify(projectDto);
}

function statistics(){
	layer.open({
	  title:'结果统计',
	  type: 2, 
	  area: ['1200px', '650px'],
	  content: basePath+'/jsp/internal/statistics.jsp', //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
	  success: function(layero, index){
	  	
	  }
		 
}); 
}

/**
 * excel上传
 */
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



