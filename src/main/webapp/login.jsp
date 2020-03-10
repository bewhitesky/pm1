<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<%
	String basePath=request.getScheme()+"://"
			+request.getServerName()+":"+request.getServerPort()
			+request.getContextPath()+"/";
%>
<head>
	<meta charset="UTF-8">
	<title>Login/Logout animation concept</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=yes">
	<link rel="stylesheet" href="assets/login/style.css">
	<script type="text/javascript" src='static/jquery-easyui-1.5.4.2/jquery.min.js'></script>
	<script type="text/javascript" src="static/js/loginAuthenticator.js"></script>
</head>

<body>
	<div class="cont">
		<div class="demo">
			<div class="login">
				<div class="login__form">
					<div class="login__row">
						<svg class="login__icon name svg-icon" viewBox="0 0 20 20">
            				<path d="M0,20 a10,8 0 0,1 20,0z M10,0 a4,4 0 0,1 0,8 a4,4 0 0,1 0,-8" />
          				</svg>
						<input id="loginId" name="loginId" type="text" class="login__input name" placeholder="Username" />
					</div>
					<div class="login__row">
						<svg class="login__icon pass svg-icon" viewBox="0 0 20 20">
            				<path d="M0,20 20,20 20,8 0,8z M10,13 10,16z M4,8 a6,8 0 0,1 12,0" />
         				</svg>
						<input id="loginPassword" name="loginPassword" type="password" class="login__input pass" placeholder="Password" />
					</div>
					<div class="msg-div" style="height: 20px">
						<span id="errorMes" style="color: red"></span>
					</div>
					<button id="btn" type="button" class="login__submit" onclick="login()">登录</button>
				</div>
			</div>
			

		</div>
	</div>

<script type="text/javascript">
	var basePath='<%=basePath%>';
</script>
</body>
</html>
