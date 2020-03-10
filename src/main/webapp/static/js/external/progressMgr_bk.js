var table;
var pDepartmentMap;
var currStateMap;
var pTypeMap;
var testTypeMap;
$(document).ready(function(){
	initTable(initSelect());
	initDateInput();
});


function initSelect(){
	pDepartmentMap=initCombobox("project_department","pDepartment");
	currStateMap=initCombobox("current_state","currStateOne");
	initCombobox("current_state","currStateTwo");
	pTypeMap=initCombobox("project_type","pType");
	testTypeMap=initCombobox("test_type",null);
}
/**
 * 表格初始化
 */
function initTable(_call){
	layui.use('table', function(){
	  table = layui.table;  
	  var progressIns=table.render({
	  	id:'progress',
	    elem: '#progressTable',
	    url: basePath+'/progressController/selectProgress',
	    method:'post',
	    cellMinWidth: 86 ,//全局定义常规单元格的最小宽度，layui 2.2.1 新增,
	    height: 'full-220',
	    page: true, //开启分页
	    cols: [[
	      {type:'checkbox'},
	      {field:'pDepartment',  title: '所属部门',sort:false,align:'center',width:'9%',
		      templet: function(d){
		      	if(d.baseInfo.pdepartment==null)return '';
		        return pDepartmentMap[d.baseInfo.pdepartment];
		      }
		  },
	      {field:'pname',  title: '项目名称', sort: false,align:'center',
		      templet: function(d){
		        return d.baseInfo.pname;
		      }
		  },
	      {field:'ptype',  title: '项目类别', sort: false,align:'center',
		      templet: function(d){
		      	if(d.baseInfo.ptype==null)return '';
		        return pTypeMap[d.baseInfo.ptype];
		      }
		  },
	      {field:'testType',  title: '测试需求',width:'13%', sort: false,align:'center',
		      templet: function(d){
		      	if(d.baseInfo.testType==null)return '';
		        return testTypeMap[d.baseInfo.testType];
		      }
		  },	      
	      {field:'planRemark',  title: '计划备注', sort: false,align:'center',
		      templet: function(d){
		        return d.baseInfo.remark;
		      }
		  },
	      {field:'testYear',  title: '测试年度', sort: false,align:'center',
		      templet: function(d){
		        return d.baseInfo.testYear;
		      }
		  },
	      {field:'currStateOne',  title: '当前阶段1',width:95, sort: false,align:'center',
		      templet: function(d){
		      	if(d.progress.currStateOne==null)return '';
		        return currStateMap[d.progress.currStateOne];
		      }
		  },
	      {field:'currStateTwo',  title: '当前阶段2',width:95, sort: false,align:'center',
		      templet: function(d){
		      	if(d.progress.currStateTwo==null)return '';
		        return currStateMap[d.progress.currStateTwo];
		      }
		  },
	      {field:'testState',  title: '测试状态', sort: false,align:'center',
		      templet: function(d){
		      	if(d.progress.testState==null)return '';
		        return d.progress.testState;
		      }
		  },
	      {field:'internalCompleteTime',  title: '内部完成时间', sort: true,width:'8%',align:'center',
		      templet: function(d){
		      	if(d.progress.internalCompleteTime==null || d.progress.internalCompleteTime==''){
		      			return '';
		      	}else{
		      		 return timestampFormat(d.progress.internalCompleteTime,"yyyy-MM-dd");
		      	}  
		      }
	      },
	      {field:'factoryCompleteTime',  title: '出厂完成时间', sort: true,width:'8%',align:'center',
		      templet: function(d){
		        if(d.progress.factoryCompleteTime==null || d.progress.factoryCompleteTime==''){
		      			return '';
		      	}else{
		      		 return timestampFormat(d.progress.factoryCompleteTime,"yyyy-MM-dd");
		      	}  
		      }
		  },
	      {field:'thiridCompleteTime',  title: '第三方完成时间', sort: true,width:'9%',align:'center',
		      templet: function(d){
		         if(d.progress.thirdCompleteTime==null || d.progress.thirdCompleteTime==''){
		      			return '';
		      	}else{
		      		 return timestampFormat(d.progress.thirdCompleteTime,"yyyy-MM-dd");
		      	}  
		      }
		  },
		  {field:'remark',  title: '备注', align:'center',
		      templet: function(d){
		      	if(d.progress.remark==null)return '';
		        return d.progress.remark;
		      }
		  },		  
	      {fixed: 'right', title:'指标完成情况' ,width:'11%', align:'center', toolbar: '#toolbar'} //这里的toolbar值是模板元素的选择器

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
		    var rows=res.rows;
		    var factoryCode=0;
			var factoryFunction=0;
			var factorySchema=0
			var factorySecurity=0
			var factoryTwoPassed=0
			var thirdCode=0
			var thirdFunction=0
			var thirdSchema=0
			var thirdSecurity=0
		    $.each(rows,function(i){
		    	factoryCode=factoryCode+rows[i].complete.factoryCode;
				factoryFunction=factoryFunction+rows[i].complete.factoryFunction;
				factorySchema=factorySchema+rows[i].complete.factorySchema;
				factorySecurity=factorySecurity+rows[i].complete.factorySecurity;
				factoryTwoPassed=factoryTwoPassed+rows[i].complete.factoryTwoPassed;
				thirdCode=thirdCode+rows[i].complete.thirdCode;
				thirdFunction=thirdFunction+rows[i].complete.thirdFunction;
				thirdSchema=thirdSchema+rows[i].complete.thirdSchema;
				thirdSecurity=thirdSecurity+rows[i].complete.thirdSecurity;
		    });
		    avernum=count;
		    average={
		    	'pName':'所有项目平均值',
		    	'factoryCode':(factoryCode/count).toFixed(2),
				'factoryFunction':(factoryFunction/count).toFixed(2),
				'factorySchema':(factorySchema/count).toFixed(2),
				'factorySecurity':(factorySecurity/count).toFixed(2),
				'factoryTwoPassed':(factoryTwoPassed/count).toFixed(2),
				'thirdCode':(thirdCode/count).toFixed(2),
				'thirdFunction':(thirdFunction/count).toFixed(2),
				'thirdSchema':(thirdSchema/count).toFixed(2),
				'thirdSecurity':(thirdSecurity/count).toFixed(2)
			}
	    }
	  });
	  table.on('tool(progressOpt)', function(obj){
			var data = obj.data.complete;
			var baseInfo=obj.data.baseInfo;
			var factory={
				'id':data.id,
				'pName':baseInfo.pname,
				'factoryFunction':data.factoryFunction.toFixed(2),
				'factorySecurity':data.factorySecurity.toFixed(2),
				'factoryTwoPassed':data.factoryTwoPassed.toFixed(2),
				'factoryCode':data.factoryCode.toFixed(2),
				'factorySchema':data.factorySchema.toFixed(2)
			};
			var oldfactory={
				'id':data.id,
				'pName':baseInfo.pname,
				'factoryFunction':data.factoryFunction,
				'factorySecurity':data.factorySecurity,
				'factoryTwoPassed':data.factoryTwoPassed,
				'factoryCode':data.factoryCode,
				'factorySchema':data.factorySchema
			};
			var third={
				'id':data.id,
				'pName':baseInfo.pname,
				'thirdFunction':data.thirdFunction.toFixed(2),
				'thirdSecurity':data.thirdSecurity.toFixed(2),
				'thirdCode':data.thirdCode.toFixed(2),
				'thirdSchema':data.thirdSchema.toFixed(2)
			}
			var oldthird={
				'id':data.id,
				'pName':baseInfo.pname,
				'thirdFunction':data.thirdFunction,
				'thirdSecurity':data.thirdSecurity,
				'thirdCode':data.thirdCode,
				'thirdSchema':data.thirdSchema
			}
			if(obj.event == 'factory'){
				layer.open({
					  title:'出厂指标完成情况',
					  type: 2, 
					  area: ['1000px', '300px'],
					  content: basePath+'/jsp/project/complete.jsp', //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
					  success: function(layero, index){
					  	var body = layer.getChildFrame('body', index);
					  	var completeTable=body.contents().find("#completeTable");
					  	var averageTable=body.contents().find("#averageTable");
					  	var datas=new Array();
					  	datas.push(factory);
					  	averageTable.append('<tr><td id="pName" align="center" width=17%>'+average.pName+'</td>' +
					  			'<td id="factoryFunction" align="center" width=12%>'+average.factoryFunction+'</td>' +
					  			'<td id="factorySecurity" align="center" width=12%>'+average.factorySecurity+'</td>' +
					  			'<td id="factoryTwoPassed" align="center" width=15%>'+average.factoryTwoPassed+'</td>' +
					  			'<td id="factoryCode" align="center" width=12%>'+average.factoryCode+'</td>' +
					  			'<td id="factorySchema" align="center" width=15%>'+average.factorySchema+'</td>' +
					  			'<td align="center" width=17%></td></tr>');
					  	var complete={'id':data.id};
					  	var edit=false;
						layui.use('table', function(){
							  table = layui.table;  
							  var tableIns=table.render({
							    elem: completeTable,
							    cellMinWidth: 60 ,//全局定义常规单元格的最小宽度，layui 2.2.1 新增,
							    cols: [[
							      {field:'pName',  title: '项目',align:'center'},
							      {field:'factoryFunction',  title: '出厂功能(%)',edit:'text',align:'center',width:'12%'},
							      {field:'factorySecurity',  title: '出厂安全(%)', edit:'text',align:'center',width:'12%'},
							      {field:'factoryTwoPassed',  title: '两轮通过率(%)',edit:'text',align:'center',width:'15%'},
							      {field:'factoryCode',  title: '代码安全(%)', edit:'text',align:'center',width:'12%'},
							      {field:'factorySchema',  title: '安全防护方案(%)',edit:'text',align:'center',width:'15%'},
							      {fixed: 'right', title:'操作', align:'center', toolbar: '#completeTool'} //这里的toolbar值是模板元素的选择器
							    ]],
							    data:datas
							  });
							  table.on('edit(completeOpt)', function(obj){ //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
								  var oldValue=oldfactory[obj.field];
								  var newValue=obj.value;
								  complete[obj.field]=obj.value;
								  average[obj.field]=(((parseFloat(average[obj.field]))*avernum-parseFloat(oldValue)+parseFloat(newValue))/avernum).toFixed(2);
								  averageTable.find("#"+obj.field).html(average[obj.field]);
								  oldfactory[obj.field]=newValue;
								  edit=true;
							  });
							  table.on('tool(completeOpt)', function(obj){ //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
								  if(obj.event === 'edit'){
								  	if(!edit){
								  		layer.alert('数据未变更!', {icon: 0,title:"警告",btnAlign: 'c'});
								  	}else{
								  		 $.ajax({
											 url:basePath+'/progressController/updateComplete',
											 type:'post',
											 data:complete,
											 async:false,
											 success:function(data){
											 	$("#searchbtn").click();
											 	messageBox(data);
											 	edit=false;
											 }
									 	});
								  	}							
							      }
							  });
						});
				  	  }
					}); 
			}else if(obj.event=='third'){
				layer.open({
					  title:'第三方指标完成情况',
					  type: 2, 
					  area: ['1000px', '300px'],
					  content: basePath+'/jsp/project/complete.jsp', //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
					  success: function(layero, index){
					  	var body = layer.getChildFrame('body', index);
					  	var completeTable=body.contents().find("#completeTable");
					  	var averageTable=body.contents().find("#averageTable");
					  	var datas=new Array();
					  	datas.push(third);
					  	var complete={'id':data.id};
					  	var edit=false;
					  	averageTable.append('<tr><td id="pName" align="center" width=20%>'+average.pName+'</td>' +
					  			'<td id="thirdFunction" align="center" width=15%>'+average.thirdFunction+'</td>' +
					  			'<td id="thirdSecurity" align="center" width=15%>'+average.thirdSecurity+'</td>' +
					  			'<td id="thirdCode" align="center" width=15%>'+average.thirdCode+'</td>' +
					  			'<td id="thirdSchema" align="center" width=15%>'+average.thirdSchema+'</td>' +
					  			'<td align="center" width=20%></td></tr>');
						layui.use('table', function(){
							  table = layui.table;  
							  var tableIns=table.render({
							    elem: completeTable,
							    cols: [[
							      {field:'pName',  title: '项目',align:'center'},
							      {field:'thirdFunction',  title: '第三方功能(%)',edit:'text',align:'center',width:'15%'},
							      {field:'thirdSecurity',  title: '第三方安全(%)', edit:'text',align:'center',width:'15%'},
							      {field:'thirdCode',  title: '代码安全(%)', edit:'text',align:'center',width:'15%'},
							      {field:'thirdSchema',  title: '安全防护方案(%)',edit:'text',align:'center',width:'15%'},
							      {fixed: 'right', title:'操作', align:'center', toolbar: '#completeTool'} //这里的toolbar值是模板元素的选择器
							    ]],
							    data:datas
							  });
							  table.on('edit(completeOpt)', function(obj){ //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
								  var oldValue=oldthird[obj.field];
								  var newValue=obj.value;
								  complete[obj.field]=obj.value;
								  average[obj.field]=(((parseFloat(average[obj.field]))*avernum-parseFloat(oldValue)+parseFloat(newValue))/avernum).toFixed(2);
								  averageTable.find("#"+obj.field).html(average[obj.field]);
								  oldthird[obj.field]=newValue;
								  edit=true;
							  });
							  table.on('tool(completeOpt)', function(obj){ //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
								  if(obj.event === 'edit'){
								  	if(!edit){
								  		layer.alert('数据未变更!', {icon: 0,title:"警告",btnAlign: 'c'});
								  	}else{
										 $.ajax({
											 url:basePath+'/progressController/updateComplete',
											 type:'post',
											 data:complete,
											 async:false,
											 success:function(data){
											 	$("#searchbtn").click();
											 	messageBox(data);
											 	edit=false;
											 }
										 });
							      	}
								 }
							  });
						});
				  	  }
					});
			}
				
	  });
	});
}
function initDateInput(){
	layui.use('laydate', function(){
	  var laydate = layui.laydate;
	  	laydate.render({ 
		  elem: '#internalCompleteTime'
		  ,range: '~' //或 range: '~' 来自定义分割字符
		});
	
		laydate.render({ 
		  elem: '#factoryCompleteTime'
		  ,range:'~'  //或 range: '~' 来自定义分割字符
		});
	
		laydate.render({ 
		  elem: '#thirdCompleteTime'
		  ,range:'~' //或 range: '~' 来自定义分割字符
		});
	});
}


function searchProgress(){
	var pDepartment=$("#pDepartment option:selected").val();
	var internalCompleteTime=$("#internalCompleteTime").val();
	var pName=$("#pName").val();
	var testYear=$("#testYear").val();
	var factoryCompleteTime=$("#factoryCompleteTime").val();
	var thirdCompleteTime=$("#thirdCompleteTime").val();
	var currStateOne=$("#currStateOne").val();
	var currStateTwo=$("#currStateTwo").val();
	
	table.reload("progress",{
		url:basePath+'/progressController/selectProgress',
		where:{
			'pDepartment':pDepartment,
			'internalCompleteTime':internalCompleteTime,
			'pName':pName,
			'testYear':testYear,
			'factoryCompleteTime':factoryCompleteTime,
			'thirdCompleteTime':thirdCompleteTime,
			'currStateOne':currStateOne,
			'currStateTwo':currStateTwo
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
 * 修改用户
 */
function updateProgress(){
	var checkStatus = table.checkStatus('progress'); //test即为基础参数id对应的值
	var baseInfo=checkStatus.data[0].baseInfo;
	var progress=checkStatus.data[0].progress;
	if(checkStatus.data.length>1){
		layer.alert('只允许对一行数据进行修改!', {icon: 0,title:"警告",btnAlign: 'c'});
		return;
	}
	if(checkStatus.data.length==0){
		layer.alert('请选择一行数据进行修改!', {icon: 0,title:"警告",btnAlign: 'c'});
		return;
	}
	var pName=baseInfo.pname;
	var productName=baseInfo.productName;
	var internalCompleteTime=progress.internalCompleteTime;
	var factoryCompleteTime=progress.factoryCompleteTime;
	var thirdCompleteTime=progress.thirdCompleteTime;
	var testState=progress.testState;
	var remark=progress.remark;
	var id=progress.id;
	var currStateOne=progress.currStateOne;
	var currStateTwo=progress.currStateTwo
	layer.open({
		  title:'修改用户',
		  type: 2, 
		  area: ['800px', '450px'],
		  content: basePath+'/jsp/project/addProgress.jsp', //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
		  success: function(layero, index){
	      	var body = layer.getChildFrame('body', index);
	      	body.contents().find("#id").val(id);
	      	body.contents().find("#remark").val(remark);
	      	body.contents().find("#testState").val(testState);
	      	if(thirdCompleteTime==null || thirdCompleteTime==""){
	      		body.contents().find("#thirdCompleteTime").val("");
	      	}else{
	      		body.contents().find("#thirdCompleteTime").val(timestampFormat(thirdCompleteTime,"yyyy-MM-dd"));
	      	}
	      	if(factoryCompleteTime==null || factoryCompleteTime==""){
	      		body.contents().find("#factoryCompleteTime").val("");
	      	}else{
	      		body.contents().find("#factoryCompleteTime").val(timestampFormat(factoryCompleteTime,"yyyy-MM-dd"));
	      	}
	      	if(internalCompleteTime==null || internalCompleteTime==""){
	      		body.contents().find("#internalCompleteTime").val("");
	      	}else{
	      		body.contents().find("#internalCompleteTime").val(timestampFormat(internalCompleteTime,"yyyy-MM-dd"));
	      	}
	      	body.contents().find("#productName").val(productName);
	      	body.contents().find("#pName").val(pName);
	      	layui.use('layer',function(){
	  			var layer = layui.layer;
	  			layer.select(body.contents().find("#currStateOne")).setValue(currStateOne);
	  			layer.select(body.contents().find("#currStateTwo")).setValue(currStateTwo);
			});
	  	  }
		});
}

/**
 * excel导出
 */
function exportProgress(){
	var pDepartment=$("#pDepartment option:selected").val();
	var internalCompleteTime=$("#internalCompleteTime").val();
	var pName=$("#pName").val();
	var testYear=$("#testYear").val();
	var factoryCompleteTime=$("#factoryCompleteTime").val();
	var thirdCompleteTime=$("#thirdCompleteTime").val();
	var currStateOne=$("#currStateOne").val();
	var currStateTwo=$("#currStateTwo").val();
	var projectDto={
		'pDepartment':pDepartment,
		'internalCompleteTime':internalCompleteTime,
		'pName':pName,
		'testYear':testYear,
		'factoryCompleteTime':factoryCompleteTime,
		'thirdCompleteTime':thirdCompleteTime,
		'currStateOne':currStateOne,
		'currStateTwo':currStateTwo
	};
//	window.location.href=basePath+'/progressController/exportExcel?projectDto='+projectDto;
	window.location.href=basePath+'/progressController/exportExcel?projectDto='+JSON.stringify(projectDto);
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



