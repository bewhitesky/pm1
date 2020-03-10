$(document).ready(function(){	
//	initDateInput();
	initTable();
});


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
function initTable(){
	layui.use('table', function(){
	  table = layui.table;  
	  table.render({
	  	id:'defect',
	    elem: '#defectTable',
	    url: basePath+'/internalController/selectDefect',
	    method:'post',
	    cellMinWidth: 80 ,//全局定义常规单元格的最小宽度，layui 2.2.1 新增,
	    height: 'full-100',
	    page: true, //开启分页
	    cols: [[
	      {type:'checkbox',fixed: 'left' },
	      {field:'pname', fixed: 'left' , title: '项目名称', sort: false,align:'center',width:'15.5%',
		      templet: function(d){
		        return d.pname;
		      }
		  },
	      {field:'securityDefect',  title: '安全功能缺陷率(%)',edit:'text',align:'center',width:'17.5%',
		      templet: function(d){
		      	if(d.securityDefect==null)return '';
		        return d.securityDefect;
		      }
		  },
		  {field:'schemaDefect',  title: '安全防护方案缺陷率(%)', edit:'text',align:'center',width:'17.5%',
		      templet: function(d){
		      	if(d.schemaDefect==null)return '';
		        return d.schemaDefect;
		      }
		  },
		  {field:'codeDefect',  title: '代码扫描缺陷率(%)',edit:'text',align:'center',width:'17.5%',
		      templet: function(d){
		      	if(d.codeDefect==null)return '';
		        return d.codeDefect;
		      }
		  },
		  {field:'functionDefect',  title: '功能/非功能缺陷率(%)', edit:'text',align:'center',width:'17.5%',
		      templet: function(d){
		      	if(d.functionDefect==null)return '';
		        return d.functionDefect;
		      }
		  },
	      {fixed: 'right', title:'操作' ,width:'8%', align:'center', toolbar: '#toolbar'} //这里的toolbar值是模板元素的选择器
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
	  table.on('tool(defectTable)', function(obj){
	  	  $.ajax({
					url:basePath+'/internalController/updateDefect',
					type:'post',
					data:obj.data,
					async:false,
					success:function(data){
						$("#searchbtn").click();
						messageBox(data);
					}
				 });			
	  });
	});
}


function searchDefect(){
	var pName=$("#pName").val();
	var submitFlag = false;
	$("#pName").isValid(function(v){
		if(v == true)
			submitFlag = true;
	});
	if(submitFlag == false){
		return;
	}
	var deploymentTime=$("#deploymentTime").val();
	table.reload("defect",{
		url:basePath+'/internalController/selectDefect',
		where:{
			'pName':pName
		}
	});
}

function reset(){
	$("#pName").val("");
}




