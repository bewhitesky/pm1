/**
 * 
 */

$(document).ready(function(){
    initWarn();
	if(userName=='null'){
		alert("未正确退出，请重新登录！");

		loginOut();
	}
 });

function loginOut(){
 	$.ajax({
 		url:basePath+"/userController/loginOut",
 		type:'POST',
 		async:false,
 		success:function(data){
			if(data.mes=="success"){
				 window.location.href="login.jsp"
			}else{
				 				
			}
 		}
 	});
 }
 function getSession(){
 	$.ajax({
 		url:basePath+"/userController/getSession",
 		type:'post',
 		async:true,
 		success:function(data){

 		}
 	
 	})
 }
 
 
 function initWarn() {
     $.ajax({
         url:basePath+"/warnController/totalWarn",
         type:"post",
         async:false,
         success:function (data) {
             if(data==0){
                 $("#warn").attr("class","layui-badge layui-bg-gray")
                 $("#warn").html(data)
             }else{
                 $("#warn").attr("class","layui-badge")
                 $("#checkwarn").attr("class","blink")
                 $("#warn").html(data)
             }
         }
	})
 }

// function login(){
// 	var loginId=$("#loginId").val();
// 	var loginPassword=$("#loginPassword").val();
// 	$.ajax({
// 		url:basePath+"userController/getToken",
// 		type:'POST',
// 		async:true,
// 		success:function(data){
// 			 $.ajax({
//			 	url:basePath+"userController/loginAuthenticator",
//			 	type:'POST',
//			 	data:{
//			 		'loginId':loginId,
//			 		'loginPassword':loginPassword
//			 	},
//			 	async:true,
//			 	success:function(data){
//				 	if(data.mes=="success"){
//				 		window.location.href="jsp/index.jsp"
//				 	}else{
//				 				
//				 	}
//			 	}
// 			 });
// 		}
// 	});
//
// }
