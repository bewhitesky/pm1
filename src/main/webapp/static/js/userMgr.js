var table;

/**
 * 表格初始化
 */
layui.use('table', function(){
  table = layui.table;
  
  table.render({
  	id:'user',
    elem: '#userTable',
    url: basePath+'/userController/selectUsers',
    method:'post',
    cellMinWidth: 80 ,//全局定义常规单元格的最小宽度，layui 2.2.1 新增,
    page: true, //开启分页
    cols: [[
      {type:'checkbox'},
      {field:'loginId',  title: '登录账号',sort:true},
      {field:'userName',  title: '用户名', sort: true},
      {field:'userTel',  title: '手机号码', sort: true},
      {field:'userEmail',  title: '邮箱地址', sort: true}
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
	    //如果是异步请求数据方式，res即为你接口返回的信息。
	    //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
	    console.log(res);
	    //得到当前页码
	    console.log(curr);
	    //得到数据总量
	    console.log(count);
    }
  });
});
function searchUser(){
	var loginId=$("#search #loginId").val();
	var userName=$("#search #userName").val();
	table.reload("user",{
		url:basePath+'/userController/selectUsers',
		where:{
			'loginId':loginId,
			'userName':userName
		}
	})
}

function reset(){
	$("#search #loginId").val("");
	$("#search #userName").val("");
}
/**
 * excel导出
 */
function exportUser(){
	window.location.href=basePath+'/userController/exportUser';
}

/**
 * excel上传
 */
layui.use('upload', function(){
  var upload = layui.upload;
   
  //执行实例
  var uploadInst = upload.render({
    elem: '#importExcel',
    url: basePath+'/userController/importUser', //上传接口
    accept:"file",
    acceptMime:"file/xls,file/xlsx",
    exts:'xls|xlsx',
    done: function(res){
      //上传完毕回调
    },
    error: function(){
      //请求异常回调
    }
  });
});

/**
 * 添加用户
 */
function addUser(){
layer.open({
	  title:'添加用户',
	  type: 2, 
	  area: ['500px', '500px'],
	  content: basePath+'/jsp/user/addUser.jsp', //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
	  success: function(layero, index){
  	  }
	}); 
}

/**
 * 修改用户
 */
function updateUser(){
	var checkStatus = table.checkStatus('user'); //test即为基础参数id对应的值
	var userlist=checkStatus.data;
	if(userlist.length>1){
		layer.alert('只允许对一行数据进行修改!', {icon: 0,title:"警告",btnAlign: 'c'});
		return;
	}
	if(userlist.length==0){
		layer.alert('请选择一行数据进行修改!', {icon: 0,title:"警告",btnAlign: 'c'});
		return;
	}
layer.open({
	  title:'修改用户',
	  type: 2, 
	  area: ['500px', '500px'],
	  content: basePath+'/jsp/user/addUser.jsp', //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
	  success: function(layero, index){
      	var id=userlist[0].id;
      	var userName=userlist[0].userName;
      	var loginId=userlist[0].loginId;
      	var userTel=userlist[0].userTel;
      	var userEmail=userlist[0].userEmail;
      	var body = layer.getChildFrame('body', index);
      	body.contents().find("#id").val(id);
      	body.contents().find("#userName").val(userName);
      	body.contents().find("#loginId").val(loginId);
      	body.contents().find("#userTel").val(userTel);
      	body.contents().find("#userEmail").val(userEmail);
  	  }
	});
}

/**
 * 删除用户
 */
function deleteUser(){
	var index=0;
	var checkStatus = table.checkStatus('user'); //test即为基础参数id对应的值
	var userlist=checkStatus.data;
	if(userlist.length==0){
		layer.alert('至少选择一行数据进行删除!', {icon: 0,title:"警告",btnAlign: 'c'});
		return;
	}
	var ids='';
	$.each(userlist,function(i){
		if(i==(userlist.length-1)){
			ids=ids+userlist[i].id;
		}else{
			ids=ids+userlist[i].id+',';
		}
	});
	$.ajax({
		url:basePath+"/userController/deleteUsers",
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
				searchUser();
				layer.close(index);
				messageBox(data);
			}
		}
	})
}

/**
 * 提交添加或修改
 */
function submitUser(){
	var id=$("#userForm #id").val();
	var loginId=$("#userForm #loginId").val();
	var loginPassword=$("#userForm #loginPassword").val();
	var userName=$("#userForm #userName").val();
	var userTel=$("#userForm #userTel").val();
	var userEmail=$("#userForm #userEmail").val();
	$.ajax({
		url:basePath+"/userController/addOrUpdateUser",
		type:"POST",
		data:{
			'id':id,
			'loginId':loginId,
			'loginPassword':loginPassword,
			'userName':userName,
			'userTel':userTel,
			'userEmail':userEmail
		},
		async:false,
		success:function(data){
			debugger;
			parent.$("#searchbtn").click();
//			closeWindow();
			parent.messageBox(data);
		},
		error:function(data){
			debugger;
			console.log(data.errorMsg);
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