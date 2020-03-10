/**
 * 
 */
 $(document).ready(function(){
//	loginOut();
 });

 
function login(){
 	var loginId=$("#loginId").val();
 	var loginPassword=$("#loginPassword").val();
 	$.ajax({
 		url:basePath+"/userController/login",
 		type:'POST',
 		data:{
			 	'loginId':loginId,
			 	'loginPassword':loginPassword
		},
 		async:false,
 		success:function(data){
 			debugger;
			if(data.mes=="success"){
				 window.location.href="jsp/index.jsp"
			}else{
				 $("#errorMes").html(data.mes)		
			}

 		}
 	});

 }
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
