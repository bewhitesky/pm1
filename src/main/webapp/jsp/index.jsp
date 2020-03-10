<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="org.apache.shiro.SecurityUtils" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<%
	String basePath=request.getScheme()+"://"
			+request.getServerName()+":"+request.getServerPort()
			+request.getContextPath();
	String userName=(String)SecurityUtils.getSubject().getSession().getAttribute("userName");
%>

<head>
  <meta charset="utf-8">
  <title>项目维护</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="stylesheet" type="text/css" href="../assets/layui/css/layui.css"/>
	<link rel="stylesheet" type="text/css" href="../assets/layui/css/gChange.css">
	<script type="text/javascript" src="../assets/layui/layui.js"></script>
	<script type="text/javascript" src='../assets/easyui/jquery.min.js'></script>
	<script type="text/javascript" src="../assets/layui/tab.js"></script>
	<script type="text/javascript" src="../static/js/index.js"></script>

</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
	  <div class="layui-header">
	    <div class="layui-logo">ProjectMgr</div>
	    <!-- 头部区域（可配合layui已有的水平导航） -->
	    <ul class="layui-nav layui-layout-left">
	      <!--li class="layui-nav-item"><a href="">控制台</a></li>
	      <li class="layui-nav-item"><a href="">商品管理</a></li>
	      <li class="layui-nav-item"><a href="">用户</a></li>
	      <li class="layui-nav-item">
	        <a href="javascript:;">其它系统</a>
	        <dl class="layui-nav-child">
	          <dd><a href="">邮件管理</a></dd>
	          <dd><a href="">消息管理</a></dd>
	          <dd><a href="">授权管理</a></dd>
	        </dl>
	      </li-->
	    </ul>
	    <ul class="layui-nav layui-layout-right">
		  <li class="layui-nav-item">
			  <a href="javascript:;" id="checkwarn" onclick="showWarn()">查看告警<span class="layui-badge" id="warn"></span></a>

		  </li>
	      <li class="layui-nav-item">
	        <a href="javascript:;">
	          <span  class="layui-icon layui-icon-user"> <%=userName %></span>
	        </a>
	        <dl class="layui-nav-child">
	          <dd><a href=""></a></dd>
	          <dd><a href=""></a></dd>
	        </dl>
	      </li>
	      <li class="layui-nav-item"><a href="javascript:;" onclick="loginOut()">sign out</a></li>
	    </ul>
	  </div>
	  
	  <div class="layui-side layui-bg-black">
	    <div class="layui-side-scroll">
	      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
	      <ul class="layui-nav layui-nav-tree"  lay-filter="demo">
	      	<shiro:hasRole name="user">
	      	<li class="layui-nav-item  layui-nav-itemed">
	          <a id="index" data-url="<%=basePath+"/jsp/person/person.jsp" %>" data-id="0" data-name="个人工作台" href="javascript:;">个人工作台</a>  
	        </li>
	        </shiro:hasRole>
	       	<shiro:hasRole name="admin">
	        <li class="layui-nav-item">
	          <a id="index" data-url="<%=basePath+"/jsp/person/person_admin.jsp" %>" data-id="0" data-name="工作台" href="javascript:;">工作台</a>
	        </li>
	        </shiro:hasRole>
	        <li class="layui-nav-item">
	          <a href="javascript:;">项目管理</a>
	          <dl class="layui-nav-child" id="menu">
	          	<dd><a data-url="<%=basePath+"/jsp/baseInfo/baseInfo.jsp" %>" data-id="1" data-name="项目基础信息" href="javascript:;">项目基础信息</a></dd>
	          	<dd><a data-url="<%=basePath+"/jsp/external/progress.jsp" %>" data-id="2" data-name="外部进度管理" href="javascript:;">外部进度管理</a></dd>
	          	<dd><a data-url="<%=basePath+"/jsp/external/report/report.jsp" %>" data-id="3" data-name="外部进度跟踪" href="javascript:;">外部进度跟踪</a></dd>
	          	<dd><a data-url="<%=basePath+"/jsp/internal/internal.jsp" %>" data-id="4" data-name="内部进度管理" href="javascript:;">内部进度管理</a></dd>
			    <dd><a data-url="<%=basePath+"/jsp/imptask/imptask.jsp" %>" data-id="6" data-name="重点工作跟踪" href="javascript:;">重点工作跟踪</a></dd>
	          </dl>
			  </li>
	        <li class="layui-nav-item">
	          <a data-url="<%=basePath+"/jsp/task/task.jsp" %>" data-id="5" data-name="任务管理" href="javascript:;">任务管理</a>
	        </li>

              <li class="layui-nav-item">
                  <a data-url="<%=basePath+"/jsp/weekly/weekly.jsp" %>" data-id="7" data-name="周报" href="javascript:;">周报</a>
              </li>

	        <!--  li class="layui-nav-item">
	          <a href="javascript:;">用例管理</a>
	          <dl class="layui-nav-child">
	            <dd><a data-url="<%=basePath+"/jsp/caselibrary/securityCase.jsp" %>" data-id="6" data-name="测试用例管理" href="javascript:;">安全功能用例库</a></dd>   
	            <dd><a data-url="<%=basePath+"/jsp/caselibrary/penetrationCase.jsp" %>" data-id="7" data-name="测试用例管理" href="javascript:;">渗透测试用例库</a></dd>
	          </dl>
	        </li-->
	        <shiro:hasRole name="admin">
	        <li class="layui-nav-item">
	          <a href="javascript:;">系统管理</a>
	          <dl class="layui-nav-child">
	            <dd><a data-url="<%=basePath+"/jsp/datadic/datadicIndex.jsp" %>" data-id="8" data-name="数据字典" href="javascript:;">数据字典</a></dd>
	          	<dd><a data-url="<%=basePath+"/jsp/user/userIndex.jsp" %>" data-id="9" data-name="用户管理" href="javascript:;">用户管理</a></dd>
	            <!--  dd><a href="javascript:;">角色管理</a></dd>
	            <dd><a href="javascript:;">菜单管理</a></dd -->         	
	          </dl>
	        </li>
	        </shiro:hasRole>
	      </ul>
	    </div>
	  </div>
	  
	  <div class="layui-body">
	    <!-- 内容主体区域
	    <div style="padding: 15px;">
	    	<div id="myFrame" style="width:100%;height:700px;">
	    		<iframe class="larry-iframe" id="iframe"  name="iframe" scrolling="no" frameborder='0'  style="width:100%;height:100%"></iframe>
	    	</div>
	    </div> -->
	    <div class="layui-tab layui-tab-brief" lay-filter="content" lay-allowclose="true">
		    <ul class="layui-tab-title"></ul>
		    <!--  
		    <ul class="rightmenu" style="display: none;position: absolute;">
                    <li data-type="closethis">关闭当前</li>
                    <li data-type="closeall">关闭所有</li>
            </ul>-->
		    <div class="layui-tab-content"></div>
		</div>
		
	  </div>
	 
	</div>

</body>
<script type="text/javascript">
var basePath='<%=basePath%>';
var userName= '<%=userName%>';
var json;
</script>
<script>
//JavaScript代码区域
layui.use('element', function(){
 	$("#index")[0].click();
 })
</script>

</html>
