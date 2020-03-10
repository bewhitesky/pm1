<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<%@taglib prefix="base" uri="/WEB-INF/tld/validatorTag.tld"%>
<html>
<head>
  <meta charset="utf-8">
  <title>项目维护</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="stylesheet" type="text/css" href="../../assets/layui/css/layui.css"/>
	<script type="text/javascript" src="../../assets/layui/layui.js"></script>
	<script type="text/javascript" src="../../assets/layui/lay/modules/form.js"></script>
	<script type="text/javascript" src='../../static/jquery-easyui-1.5.4.2/jquery.min.js'></script>	
	<script type="text/javascript" src='../../static/js/userMgr.js'></script>

  <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head> 
  <body style="padding-top: 20px">
    <form class="layui-form" id="userForm">
      <div class="layui-form-item">
	      <input type="hidden" id="id" name="id" >
	    <label class="layui-form-label">用户名:<span style="color:red;">*</span></label>
	    <div class="layui-input-inline">
		    <base:valid field="User.userName">  
		      <input type="text" id="userName" name="userName"  placeholder="请输入用户名" autocomplete="off" class="layui-input"/>
		    </base:valid>
	    </div>
		
		
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">登录账号:<span style="color:red">*</span></label>
	    <div class="layui-input-inline">
	      <input type="text" id="loginId" name="loginId"  placeholder="请输入登录账号" class="layui-input">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">登录密码:<span style="color:red">*</span></label>
	    <div class="layui-input-inline">
	      <input type="password" id="loginPassword" name="loginPassword" placeholder="请输入密码"  class="layui-input">
	    </div>
	    <div class="layui-form-mid layui-word-aux">请填写8到12位密码</div>
	  </div>
	  	  
	  <div class="layui-form-item">
	    <div class="layui-inline">
	      <label class="layui-form-label">手机号码:</label>
	      <div class="layui-input-inline">
	        <input type="tel" id="userTel" name="userTel"  placeholder="请输入手机号"  class="layui-input">
	      </div>
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <div class="layui-inline">
	      <label class="layui-form-label">邮箱地址:</label>
	      <div class="layui-input-inline">
	        <input type="text" id="userEmail" name="userEmail"  placeholder="请输入邮箱地址"  class="layui-input">
	      </div>
	    </div>
	  </div>
	  	  
	  <div class="layui-form-item">
	    <div class="layui-input-block">
	      <button class="layui-btn" type="button"  onclick="submitUser()" >提交</button>
	      <button type="button" class="layui-btn layui-btn-primary" onclick="closeLayer()">关闭</button>
	    </div>
	  </div>
	</form>
  </body>
<script>
//JavaScript代码区域
var basePath='<%=basePath%>'
layui.use('form', function(){
  var form = layui.form;  
});
</script>
</html>
