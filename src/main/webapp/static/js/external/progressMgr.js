var table;
var pDepartmentMap;
var currStateMap;
var pTypeMap;
var testTypeMap;
var testStateMap;
var dorgMap;
$(document).ready(function(){
	initTable(initSelect());
	initDateInput();
	var date=new Date()
	$("#testYear").val(date.getFullYear());
});


function initSelect(){
	pDepartmentMap=initCombobox("project_department","pDepartment");
	currStateMap=initCombobox("current_state","currStateOne");
	initCombobox("current_state","currStateTwo");
	pTypeMap=initCombobox("project_type","pType");
	testTypeMap=initCombobox("test_type",null);
	testStateMap=initCombobox("test_state","testState");
	dorgMap=initCombobox("d_org","dorg");
}
/**
 * 表格初始化
 */
function initTable(_call){
	layui.use('table', function(){
	  var complete={'id':''};
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
	      {type:'checkbox',fixed: 'left',rowspan:2 },
	      {field:'pDepartment',  fixed: 'left' ,title: '所属部门',sort:false,align:'center',width:'9%',rowspan:2,
		      templet: function(d){
		      	if(d.baseInfo.pdepartment==null)return '';
		        return pDepartmentMap[d.baseInfo.pdepartment];
		      }
		  },
	      {field:'productName', fixed: 'left' , title: '产品名称', sort: false,align:'center',width:'12%',rowspan:2,
		      templet: function(d){
		        return d.baseInfo.productName;
		      }
		  },
	      {field:'ptype' , title: '项目类别', sort: false,align:'center',rowspan:2,
		      templet: function(d){
		      	if(d.baseInfo.ptype==null)return '';
		        return pTypeMap[d.baseInfo.ptype];
		      }
		  },
	      {field:'testType' , title: '测试需求',width:'13%', sort: false,align:'center',rowspan:2,
		      templet: function(d){
		        return d.baseInfo.testType
		      }
		  },
		  {field:'dorg' , title: '研发机构', sort: false,align:'center',rowspan:2,
		      templet: function(d){
		      	if(d.baseInfo.dorg==null)return '';
		        return dorgMap[d.baseInfo.dorg];
		      }
		  },
	      {field:'planRemark' , title: '计划备注', sort: false,align:'center',rowspan:2,
		      templet: function(d){
		        return d.baseInfo.remark;
		      }
		  },
	      {field:'testYear', title: '测试年度', sort: false,align:'center',rowspan:2,
		      templet: function(d){
		        return d.baseInfo.testYear;
		      }
		  },
		  {field:'remark', title: '备注', align:'center',rowspan:2,
		      templet: function(d){
		      	if(d.progress.remark==null)return '';
		        return d.progress.remark;
		      }
		  },
	      {field:'currStateOne',  title: '当前阶段1',width:'7%', sort: false,align:'center',rowspan:2,
		      templet: function(d){
		      	if(d.progress.currStateOne==null)return '';
		        return currStateMap[d.progress.currStateOne];
		      }
		  },
//	      {field:'currStateTwo',  title: '当前阶段2',width:'7%', sort: false,align:'center',rowspan:2,
//		      templet: function(d){
//		      	if(d.progress.currStateTwo==null)return '';
//		        return currStateMap[d.progress.currStateTwo];
//		      }
//		  },暂时屏蔽
	      {field:'testState',  title: '测试状态', sort: false,align:'center',rowspan:2,
		      templet: function(d){
		      	if(d.progress.testState==null)return '';
		        return d.progress.testState;
		      }
		  },
	      {field:'internalCompleteTime',  title: '内部完成时间', sort: true,width:'9%',align:'center',rowspan:2,
		      templet: function(d){
		      	if(d.progress.internalCompleteTime==null || d.progress.internalCompleteTime==''){
		      			return '';
		      	}else{
		      		 return timestampFormat(d.progress.internalCompleteTime,"yyyy-MM-dd");
		      	}  
		      }
	      },
	      {field:'factoryCompleteTime',  title: '出厂完成时间', sort: true,width:'9%',align:'center',rowspan:2,
		      templet: function(d){
		        if(d.progress.factoryCompleteTime==null || d.progress.factoryCompleteTime==''){
		      			return '';
		      	}else{
		      		 return timestampFormat(d.progress.factoryCompleteTime,"yyyy-MM-dd");
		      	}  
		      }
		  },
	      {field:'thiridCompleteTime',  title: '第三方完成时间', sort: true,width:'10%',align:'center',rowspan:2,
		      templet: function(d){
		         if(d.progress.thirdCompleteTime==null || d.progress.thirdCompleteTime==''){
		      			return '';
		      	}else{
		      		 return timestampFormat(d.progress.thirdCompleteTime,"yyyy-MM-dd");
		      	}  
		      }
		  },
		  {title: '出厂指标完成情况',align:'center',colspan:8},

		  {title: '第三方指标完成情况',align:'center',colspan:8},


		  {fixed: 'right', title:'操作' ,width:'6%', align:'center',rowspan:2, toolbar: '#toolbar'} //这里的toolbar值是模板元素的选择器
	    ],[
	      
		  {field:'facFunTimes',  title: '功能轮数(次)',edit:'text',align:'center',width:'7%',
		      templet: function(d){
		      	if(d.complete.facFunTimes==null)return '';
		        return d.complete.facFunTimes;
		      }
		  },
		  {field:'facFunTwoPassed',  title: '功能两轮通过率(%)',edit:'text',align:'center',width:'9%',
		      templet: function(d){
		      	if(d.complete.facFunTwoPassed==null)return '';
		        return d.complete.facFunTwoPassed;
		      }
		  },
		  {field:'factoryFunction',  title: '出厂功能(%)',edit:'text',align:'center',width:'7%',
		      templet: function(d){
		      	if(d.complete.factoryFunction==null)return '';
		        return d.complete.factoryFunction;
		      }
		  },
		  {field:'facSecurityTimes',  title: '安全轮数(次)', edit:'text',align:'center',width:'7%',
		      templet: function(d){
		      	if(d.complete.facSecurityTimes==null)return '';
		        return d.complete.facSecurityTimes;
		      }
		  },
		  {field:'facSecurityTwoPassed',  title: '安全两轮通过率(%)',edit:'text',align:'center',width:'10%',
		      templet: function(d){
		      	if(d.complete.facSecurityTwoPassed==null)return '';
		        return d.complete.facSecurityTwoPassed;
		      }
		  },
		  {field:'factorySecurity',  title: '出厂安全(%)', edit:'text',align:'center',width:'7%',
		      templet: function(d){
		      	if(d.complete.factorySecurity==null)return '';
		        return d.complete.factorySecurity;
		      }
		  },
		  {field:'factoryCode',  title: '代码安全(%)', edit:'text',align:'center',width:'7%',
		      templet: function(d){
		      	if(d.complete.factoryCode==null)return '';
		        return d.complete.factoryCode;
		      }
		  },
		  {field:'factorySchema',  title: '安全防护方案(%)',edit:'text',align:'center',width:'9%',
		      templet: function(d){
		      	if(d.complete.factorySchema==null)return '';
		        return d.complete.factorySchema;
		      }
		  },

		  {field:'thirdFunTimes',  title: '功能轮数(次)',edit:'text',align:'center',width:'8%',
		      templet: function(d){
		      	if(d.complete.thirdFunTimes==null)return '';
		        return d.complete.thirdFunTimes;
		      }
		  },
		  {field:'thirdFunTwoPassed',  title: '功能两轮通过率(%)',edit:'text',align:'center',width:'10%',
		      templet: function(d){
		      	if(d.complete.thirdFunTwoPassed==null)return '';
		        return d.complete.thirdFunTwoPassed;
		      }
		  },
		  {field:'thirdFunction',  title: '第三方功能(%)',edit:'text',align:'center',width:'8%',
		      templet: function(d){
		      	if(d.complete.thirdFunction==null)return '';
		        return d.complete.thirdFunction;
		      }
		  },
		  {field:'thirdSecurityTimes',  title: '安全轮数(次)', edit:'text',align:'center',width:'8%',
		      templet: function(d){
		      	if(d.complete.thirdSecurityTimes==null)return '';
		        return d.complete.thirdSecurityTimes;
		      }
		  },
		  {field:'thirdSecurityTwoPassed',  title: '安全两轮通过率(%)', edit:'text',align:'center',width:'10%',
		      templet: function(d){
		      	if(d.complete.thirdSecurityTwoPassed==null)return '';
		        return d.complete.thirdSecurityTwoPassed;
		      }
		  },
		  {field:'thirdSecurity',  title: '第三方安全(%)', edit:'text',align:'center',width:'8%',
		      templet: function(d){
		      	if(d.complete.thirdSecurity==null)return '';
		        return d.complete.thirdSecurity;
		      }
		  },
		  {field:'thirdCode',  title: '代码安全(%)', edit:'text',align:'center',width:'7%',
		      templet: function(d){
		      	if(d.complete.thirdCode==null)return '';
		        return d.complete.thirdCode;
		      }
		  },
		  {field:'thirdSchema',  title: '安全防护方案(%)',edit:'text',align:'center',width:'9%',
		      templet: function(d){
		      	if(d.complete.thirdSchema==null)return '';
		        return d.complete.thirdSchema;
		      }
		  }
		  ]
	    ],
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
			if(count!=0){
				average=res.avg.complete;
				$(".layui-table-fixed-l tbody").append("<tr data-index='"+count+"'><td colspan='7' align='right'><span style='padding-right:10px'>平均值：</span></tr>");
				$(".layui-table-fixed-r tbody").append("<tr data-index='"+count+"'><td style='height:31px'></td></tr>");
				$(".layui-table-main tbody").append("<tr data-index='"+count+"'><td colspan='13' align='right'></td>" +
						"<td data-field='factoryFunction' align='center' data-content>"+(average.factoryFunction).toFixed(2)+"</td>" +
						"<td data-field='facFunTimes' align='center' data-content>"+(average.facFunTimes).toFixed(2)+"</td>" +
						"<td data-field='facFunTwoPassed' align='center' data-content>"+(average.facFunTwoPassed).toFixed(2)+"</td>" +
						
						"<td data-field='factorySecurity' align='center' data-content>"+(average.factorySecurity).toFixed(2)+"</td>" +
						"<td data-field='facSecurityTimes' align='center' data-content>"+(average.facSecurityTimes).toFixed(2)+"</td>" +
						"<td data-field='facSecurityTwoPassed' align='center' data-content>"+(average.facSecurityTwoPassed).toFixed(2)+"</td>" +
						
						"<td data-field='factoryCode' align='center' data-content>"+(average.factoryCode).toFixed(2)+"</td>" +
						"<td data-field='factorySchema' align='center' data-content>"+(average.factorySchema).toFixed(2)+"</td>" +

						"<td data-field='thirdFunction' align='center' data-content>"+(average.thirdFunction).toFixed(2)+"</td>" +
						"<td data-field='thirdFunTimes' align='center' data-content>"+(average.thirdFunTimes).toFixed(2)+"</td>" +
						"<td data-field='thirdFunTwoPassed' align='center' data-content>"+(average.thirdFunTwoPassed).toFixed(2)+"</td>" +
						"<td data-field='thirdSecurity' align='center' data-content>"+(average.thirdSecurity).toFixed(2)+"</td>" +
						"<td data-field='thirdSecurityTimes' align='center' data-content>"+(average.thirdSecurityTimes).toFixed(2)+"</td>" +
						"<td data-field='thirdSecurityTwoPassed' align='center' data-content>"+(average.thirdSecurityTwoPassed).toFixed(2)+"</td>" +
						"<td data-field='thirdCode' align='center' data-content>"+(average.thirdCode).toFixed(2)+"</td>" +
						"<td data-field='thirdSchema' align='center' data-content>"+(average.thirdSchema).toFixed(2)+"</td><td></td></tr>");
			}
	    }
	  });
	  table.on('edit(progressOpt)', function(obj){
	  		var data = obj.data.complete;
	  		complete.id=data.id;
	  		complete[obj.field]=obj.value;
	  		
	  });
	  table.on('tool(progressOpt)', function(obj){
	  	  if(complete.id!="" && obj.data.complete.id==complete.id){
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
	  	  }else{
	  	  	layer.alert('数据未修改！', {icon: 0,title:"警告",btnAlign: 'c'});
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
	var dorg=$("#dorg option:selected").val();
	var internalCompleteTime=$("#internalCompleteTime").val();
	var pName=$("#pName").val();
	var testYear=$("#testYear").val();
	var factoryCompleteTime=$("#factoryCompleteTime").val();
	var thirdCompleteTime=$("#thirdCompleteTime").val();
	var currStateOne=$("#currStateOne").val();
//	var currStateTwo=$("#currStateTwo").val();
	var testState=$("#testState").val();
	var testStateValue;
	if(testState=="" || testState==null){
		testStateValue=""
	}else{
		testStateValue=testStateMap[testState];
	}
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
//			'currStateTwo':currStateTwo,
			'testState':testStateValue,
			'dorg':dorg
		}
	});
}

function resetInfo(){
	$("#factoryTime").val("");
	$("#thirdTime").val("");
	$("#pDepartment").val("");
	$("#internalCompleteTime").val("");
	$("#pName").val("");
	var date=new Date()
	$("#testYear").val(date.getFullYear());
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
	if(checkStatus.data.length>1){
		layer.alert('只允许对一行数据进行修改!', {icon: 0,title:"警告",btnAlign: 'c'});
		return;
	}
	if(checkStatus.data.length==0){
		layer.alert('请选择一行数据进行修改!', {icon: 0,title:"警告",btnAlign: 'c'});
		return;
	}
	var baseInfo=checkStatus.data[0].baseInfo;
	var progress=checkStatus.data[0].progress;

	var pName=baseInfo.pname;
	var productName=baseInfo.productName;
	var internalCompleteTime=progress.internalCompleteTime;
	var factoryCompleteTime=progress.factoryCompleteTime;
	var thirdCompleteTime=progress.thirdCompleteTime;
	var testState=progress.testState;
	var remark=progress.remark;
	var id=progress.id;
	var currStateOne=progress.currStateOne;
//	var currStateTwo=progress.currStateTwo;
	var testStateValue;
	layer.open({
		  title:'修改外部进度信息',
		  type: 2, 
		  area: ['800px', '550px'],
		  content: basePath+'/jsp/external/addProgress.jsp', //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
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

	      	$.each(testStateMap,function(n,val){
	      		if(val==testState){
	      			testStateValue=n;
	      		}
	      	});
	      	
	      	layui.use('layer',function(){
	  			var layer = layui.layer;
	  			layer.select(body.contents().find("#currStateOne")).setValue(currStateOne);
//	  			layer.select(body.contents().find("#currStateTwo")).setValue(currStateTwo);
	  			layer.select(body.contents().find("#testState")).setValue(testStateValue);
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
	window.location.href=basePath+'/progressController/exportExcel?json='+JSON.stringify(projectDto);
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



