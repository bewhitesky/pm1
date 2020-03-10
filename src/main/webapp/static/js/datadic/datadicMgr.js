var table;
var form;
layui.use('element', function(){
  var form = layui.form;
  
});

/**
 * 表格初始化
 */
layui.use('table', function(){
  table = layui.table;
  
  table.render({
  	id:'groups',
    elem: '#groupsTable',
    url: basePath+'/datadicController/selectGroups',
    method:'post',
    cellMinWidth: 80 ,//全局定义常规单元格的最小宽度，layui 2.2.1 新增,
    page: true, //开启分页
    cols: [[
      {field:'groupCode',  title: '分组编码',sort:true},
      {field:'groupName',  title: '分组名称', sort: true},
      {fixed: 'right',title: '操作', width:150, align:'center', toolbar: '#groupbar'} //这里的toolbar值是模板元素的选择器
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
  table.on('tool(group)', function(obj){
	  var data = obj.data; //获得当前行数据
	  var layEvent = obj.event;
	  var tr = obj.tr; //获得当前行 tr 的DOM对象
	  if(layEvent === 'search'){
		table.reload("items",{
			url: basePath+'/datadicController/selectItems',
			where:{
				'groupCode':data.groupCode
			}
		})
	  } else if(layEvent === 'del'){ //删除
	    layer.confirm('真的删除行么', function(index){
	      obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
	      layer.close(index);
	      deleteDatadic(obj.data.groupCode)
	    });
	  } else if(layEvent === 'edit'){ //编辑
	    updateDatadic(data)
	  }
 });
});

/**
 * 表格初始化
 */
layui.use('table', function(){
	  table = layui.table;  
	  table.render({
	  	id:'items',
	    elem: '#itemsTable',
	    method:'post',
	    cellMinWidth: 80 ,//全局定义常规单元格的最小宽度，layui 2.2.1 新增,
	    page: true, //开启分页
	    cols: [[
	      {type:'checkbox'},
	      {field:'dataitemCode',  title: '子项编码',sort:true},
	      {field:'dataitemName',  title: '子项名称', sort: true}
	//      {fixed: 'right', width:150, align:'center', toolbar: '#toolbar'} //这里的toolbar值是模板元素的选择器
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

/**
 * 查询分组
 */
function searchGroups(){
	var groupCode=$("#search #groupCode").val();
	var groupName=$("#search #groupName").val();
	table.reload("groups",{
		url:basePath+'/datadicController/selectGroups',
		where:{
			'groupCode':groupCode,
			'groupName':groupName
		}
	})
}

function reset(){
	$("#search #groupCode").val("");
	$("#search #groupName").val("");
}


/**
 * 添加用户
 */

function addDatadic(){
layer.open({
	  title:'添加数据字典',
	  type: 2, 
	  area: ['750px', '600px'],
	  resize:true,
	  content: basePath+'/jsp/datadic/addDatadic.jsp',//这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
	  success: function(layero, index){
	  	var body = layer.getChildFrame('body', index);
	  	var subitemsTable=body.contents().find("#subitemsTable");
		layui.use('table', function(){
			  addTable = layui.table;  
			  addTable.render({
			    elem: subitemsTable,
			    height: 'full-500',
			    cols: [[
			      {field:'dataitemCode',  title: '子项编码',sort:true},
			      {field:'dataitemName',  title: '子项名称', sort: true},
			      {title:'操作',fixed: 'right', width:150, align:'center', toolbar: '#toolbar'} //这里的toolbar值是模板元素的选择器
			    ]]
			  });
		});
	  }
	  	
}); 
}

function addItems(){
	var groupCode=$("#groupCode").val();
	var dataitemCode=$("#dataitemCode").val();
	var dataitemName=$("#dataitemName").val();
	if(groupCode==""){
		layer.alert('分组编码及分组名称不能为空!', {icon: 0,title:"警告",btnAlign: 'c'});
		return;
	}
	var items={
		'index':index++,
		'groupCode':groupCode,
		'dataitemCode':dataitemCode,
		'dataitemName':dataitemName
	}
//    var itemsList=datas;
//	datas.push(items);
	itemsList.push(items);
	console.log(itemsList);
	layui.use('table', function(){
				  addTable = layui.table;  
				  addTable.render({
				  	id:'subitems',
		            elem: "#subitemsTable",
				    cellMinWidth: 80 ,//全局定义常规单元格的最小宽度，layui 2.2.1 新增,
				    height: 'full-250',
				    cols: [[
				      {field:'dataitemCode',  title: '子项编码',sort:true,edit:'text'},
				      {field:'dataitemName',  title: '子项名称', sort: true,edit:'text'},
				      {title:'操作',fixed: 'right', width:150, align:'center', toolbar: '#toolbar'} //这里的toolbar值是模板元素的选择器
				    ]],
				    data:itemsList,
					page: false, //开启分页
					limit:20
//					limit:3,
//					limits:[3,6,9]
				  });
				  
				  addTable.on('tool(itemsOpt)', function(obj){
				    var data = obj.data;
					if(obj.event === 'del'){
				      layer.confirm('真的删除行么', function(index){
				        obj.del();
				        layer.close(index);
				        itemsList.removeObject(data);
				      });
				    }
				  }); 
				  addTable.on('edit(itemsOpt)', function(obj){ //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
					  console.log(obj.value); //得到修改后的值
					  console.log(obj.field); //当前编辑的字段名
					  console.log(obj.data); //所在行的所有相关数据  
				  });
			});		
}

/**
 * 修改用户
 */
function updateDatadic(data){
	layer.open({
		  title:'修改数据字典',
		  type: 2, 
		  area: ['750px', '600px'],
		  resize:true,
		  content: basePath+'/jsp/datadic/addDatadic.jsp',//这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
		  success: function(layero, index){
		  	var body = layer.getChildFrame('body', index);
		  	var subitemsTable=body.contents().find("#subitemsTable");
		  	body.contents().find("#id").val(data.id);
		  	body.contents().find("#groupCode").val(data.groupCode);
		  	body.contents().find("#groupName").val(data.groupName);
		  	body.contents().find("#btn").click();
			layui.use('table', function(){
				  addTable = layui.table;  
				  addTable.render({
				    elem: subitemsTable,
				    height: 'full-400',
				    cols: [[
				      {field:'dataitemCode',  title: '子项编码',sort:true},
				      {field:'dataitemName',  title: '子项名称', sort: true},
				      {title:'操作',fixed: 'right', width:150, align:'center', toolbar: '#toolbar'} //这里的toolbar值是模板元素的选择器
				    ]],
				    page: false //开启分页
				  });
			});
		  }	  	
	}); 
}
function searchItems(){	
	var groupCode=$("#groupCode").val();
	$.ajax({
		url: basePath+'/datadicController/selectItems',
		type:'post',
		data:{
			'groupCode':groupCode
		},
		success:function(data){
			itemsList=data.rows;
			layui.use('table', function(){
				  addTable = layui.table;  
				  addTable.render({
				  	id:'subitems',
		            elem: "#subitemsTable",
				    cellMinWidth: 80 ,//全局定义常规单元格的最小宽度，layui 2.2.1 新增,
				    height: 'full-250',
				    cols: [[
				      {field:'dataitemCode',  title: '子项编码',sort:true,edit:'text'},
				      {field:'dataitemName',  title: '子项名称', sort: true,edit:'text'},
				      {title:'操作',fixed: 'right', width:150, align:'center', toolbar: '#toolbar'} //这里的toolbar值是模板元素的选择器
				    ]],
				    data:itemsList,
					page: false, //开启分页
					limit:20
//					limits:[3,6,9]
				  });
				  
				  addTable.on('tool(itemsOpt)', function(obj){
				    var data = obj.data;
					if(obj.event === 'del'){
				      layer.confirm('真的删除行么', function(index){
				        obj.del();
				        layer.close(index);
				        itemsList.removeObject(data);
				      });
				    }
				  }); 
				  addTable.on('edit(itemsOpt)', function(obj){ //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
					  console.log(obj.value); //得到修改后的值
					  console.log(obj.field); //当前编辑的字段名
					  console.log(obj.data); //所在行的所有相关数据  
				  });
			});		
		}
	});
}
/**
 * 删除用户
 */
function deleteDatadic(groupCode){

	layer.confirm('确定删除所选项？', {
	  btn: ['确定','取消'] //按钮
	}, function(){
		$.ajax({
			url:basePath+"/datadicController/deleteDatadic",
			type:"POST",
			async:false,
			data:{
				'groupCode':groupCode
			},
			beforeSend:function(){
				index=layer.msg('正在删除，请稍候!', {
						  icon: 16
						  ,shade: 0.01
						});
			},
			success:function(data){
				if(data.mes=="success"){
					searchGroups();
					layer.close(index);
					messageBox(data);
				}
			}
		})
	}, function(){

	});


}

/**
 * 提交添加或修改
 */
function submitDatadic(){
	var datadicItems =itemsList; //test即为基础参数id对应的值
	var datadicItemsList=new Array();
	console.log(addTable);
	$.each(datadicItems,function(i){
		var items={
			'dataitemCode':datadicItems[i].dataitemCode,
			'dataitemName':datadicItems[i].dataitemName,
			'groupCode':datadicItems[i].groupCode
		};
		datadicItemsList.push(items);
	});
	var groupCode=$("#groupCode").val();
	var groupName=$("#groupName").val();
	var id=$("#id").val();
	data={
		'id':id,
		'groupCode':groupCode,
		'groupName':groupName,
		'datadicItems':datadicItemsList
	};
	$.ajax({
		url:basePath+'/datadicController/addItems',
		type:"POST",
		async:false,
		dataType:"json",
		contentType:"application/json", 
		data:JSON.stringify(data),
		success:function(data){
			if(data.mes=="success"){
				searchGroups();
				parent.messageBox(data);
				closeWindow();
			}
		}
	});
}
function workday(){
	$.ajax({
		url:basePath+'/datadicController/getWorkDay',
		type:"POST",
		async:false,
		success:function(data){
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