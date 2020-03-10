$(document).ready(function(){
//	validate("addBaseInfoForm");	
});
function validateForm(id){
	var isValid=false;
	var flag=0;
	var form=document.getElementById(id);
	for(var i = 0 , j = form.length;i <j;++i){
	  var required=false;
	  var length=false;
	  var email=false;
	  var phone=false;
	  var time=false;
	  var number=false;
	  var min=0;
	  var max=0;
	  var id=form[i].id;
	  var type=form[i].type;
	  var value=form[i].value;
	  var rule=form[i].getAttribute("cz-rule");
	  var label=form[i].getAttribute("cz-label");
  	  if(!valide(rule,value,id,label)){
  	  	flag=1;
  	  }
	  if(type.indexOf("text")!=-1 ){
	  	$("#"+id).bind("input propertychange",function(data){
	  		var v=$(this).val();
	  		var i=$(this)[0].id;
	  		var reg="";
	  		var rule=$(this)[0].getAttribute("cz-rule");
	  		var label=$(this)[0].getAttribute("cz-label");
	  		valide(rule,v,i,label);
	  	});
	  }else if(type.indexOf("select")!=-1){
	  	layui.use('element', function(){
			var element = layui.element;
			var form=layui.form;
			form.on('select('+id+')', function(data){
				    var i=data.elem.id;
				    var label=$("#"+i).attr("cz-label");
					if(data.value!=''){
						$("#"+i+"Msg span").hide();
					}else{
						$("#"+i+"Msg span").show();
	  					$("#"+i+"Msg span").html(label+"不能为空");
					}
				}
			);
		});	  	
	  }

	}
	if(flag==0){
		isValid=true;
	}
	return isValid;
}

function valide(rule,v,i,label){
	  var required=false;
	  var length=false;
	  var email=false;
	  var phone=false;
	  var time=false;
	  var number=false;
	  var min=0;
	  var max=0;
	  if(rule!=null && rule.indexOf("required")!=-1){
		  	required=true;	  	
	  }else{
		    required=false;
	  }
	  if(rule!=null && rule.indexOf("length")!=-1){
			length=true;
			min=rule.substring(rule.indexOf("length(")+7,rule.indexOf("~"));
			max=rule.substring(rule.indexOf("~")+1,rule.indexOf(")"));
	  }else{
		    length=false;
	  }
	  if(rule!=null && rule.indexOf("email")!=-1){
			email=true;
			reg=/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+(,([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+)*$/;
	  }else{
		    email=false;
	  }
	  if(rule!=null && rule.indexOf("phone")!=-1){
			phone=true;
			reg=/^(13|14|15|16|17|18|19)\d{9}$/i;
	  }else{
		    phone=false;
	  }
	  if(rule!=null && rule.indexOf("number")!=-1){
	  		number=true;
	  		reg=/^\d+$/;
	  }
	  if((v=="" || v==null) && required){
	  		$("#"+i+"Msg span").show();
	  		$("#"+i+"Msg span").html(label+"不能为空");
	  		return false;
	  }else{
	  		
		  	$("#"+i+"Msg span").hide();
	  }
	  if((v.length<min || v.length>max) && length){
	  		$("#"+i+"Msg span").show();
	  		$("#"+i+"Msg span").html("请输入"+min+"至"+max+"位字符");
	  		return false;
	  }
	  if(v!="" && v!=null && email){
	  		if(!reg.test(v)){
		  		$("#"+i+"Msg span").show();
		  		$("#"+i+"Msg span").html("请输入正确的邮箱！");
		  		return false;
	  		}
	  }
	  if(v!="" && v!=null && phone){
	  		if(!reg.test(v)){
		  		$("#"+i+"Msg span").show();
		  		$("#"+i+"Msg span").html("请输入正确的手机号！");
		  		return false;
	  		}
	  }
	  if(v!="" && v!=null && number){
	  		if(!reg.test(v)){
		  		$("#"+i+"Msg span").show();
		  		$("#"+i+"Msg span").html("请输入整数");
		  		return false;
	  		}
	  }
	  return true;  
}
function valideTime(id){
	var rule=$("#"+id).attr("cz-rule");
	var label=$("#"+id).attr("cz-label");
	var v=$("#"+id).val();
	var required=false;
	if(rule!=null && rule.indexOf("required")!=-1){
	 	required=true;
	}
	if((v==''||v==null) && required){
	  	$("#"+id+"Msg span").show();
	  	$("#"+id+"Msg span").html(label+"不能为空");  		
	}else{
		$("#"+id+"Msg span").hide();
	}
}

