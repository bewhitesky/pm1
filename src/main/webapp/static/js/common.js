function fixWidth(percent){
	return document.body.clientWidth*percent;
}

function messageBox(data){
	var mes=data.mes;
	var content="";
	if(mes=="success"){
		mes="操作成功";
		content="<div style='padding:70px 30px 0 100px'><i class='layui-icon layui-icon-face-smile' style='font-size: 25px; color: green;'></i><span style='font-size:25px;padding-left:10px'>"+mes+"</span></div>"; 
	}else{
		content="<div style='padding:70px 30px 0 100px'><i class='layui-icon layui-icon-face-cry' style='font-size: 25px; color: red;'></i><span style='font-size:25px;padding-left:10px'>"+mes+"</span></div>"; 
	}
	layer.open({
		type: 1,
		title: "提示信息",
		shade: 0,
		area: ['340px', '250px'],
		offset: 'rb', //右下角弹出
		time: 5000, //5秒后自动关闭
		anim: 2,
		content:content //弹出的信息写在这个页面		
		});
}

function DateFormat(date,fmt){
var o = {
		"M+" : date.getMonth() + 1, //Month
		"d+" : date.getDate(), //Day
		"h+" : date.getHours(), //Hour
		"m+" : date.getMinutes(), //Minute
		"s+" : date.getSeconds(), //Second
		"q+" : Math.floor((date.getMonth() + 3) / 3), //Season
		"S" : date.getMilliseconds()//millesecond
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (date.getFullYear() +"").substr(4 - RegExp.$1.length));
	for (var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1,(RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}
function timestampFormat(timestamp,fmt){
	return DateFormat(new Date(timestamp),fmt);
}

function initCombobox(groupCode,selector){
	var object={};
	$.ajax({
		url:basePath+'/datadicController/initCombobox',
		method:'post',
		data:{
			'groupCode':groupCode
		},
		async:false,
		success:function(data){
			if(data.mes=="success"){
				items=data.rows.datadicItems;
				if(selector!=null ||selector!=''){
					$("#"+selector).append("<option value=''>请选择...</option>")
				}
				$.each(items,function(i){
					if(selector!=null ||selector!=''){
						$("#"+selector).append("<option value='"+items[i].dataitemCode+"'>"+items[i].dataitemName+"</option>");				
					}
					object[items[i].dataitemCode]=items[i].dataitemName;
				});
			}else{
			}
		}	
	});
	return object;
}

Array.prototype.removeObject=function(obj){
	var a=this;
	var len=a.length;
	var flag=0;
	for(var i=0 ; i<len;i++){
		if(a[i].id==obj.id && a[i].index==obj.index){
			a[i]=a[i+1];
			flag=1;
		}
		if(flag==1){
			a[i]=a[i+1];
		}
	}	
	return a.pop();
}

    String.prototype.gblen = function() {  
      var len = 0;  
      for (var i=0; i<this.length; i++) {  
        if (this.charCodeAt(i)>127 || this.charCodeAt(i)==94) {  
           len += 2;  
         } else {  
           len ++;  
         }  
       }  
      return len;  
    }
     //根据非汉字[字节]长度标准 返回--指定长度的字符
    String.prototype.gbstr = function(size) {  
      var len = 0;  
      var str = "";
      for (var i=0; i<this.length; i++) {  
            if (this.charCodeAt(i) > 127 || this.charCodeAt(i) == 94) {
                len+=2;
                str+=this[i];
            } else {
                len++;
                str+=this[i];
            }
            if(len>=size){
                return str;
            }
       }  
      return str;  
    }
     //根据非汉字[字节]长度标准  返回--指定长度后剩余的字符
    String.prototype.gbelse = function(size) {  
      var len = 0;  
      var str = "";
      for (var i=0; i<this.length; i++) {  
            if (this.charCodeAt(i) > 127 || this.charCodeAt(i) == 94) {
                len+=2;
                if(len>size){
                    str+=this[i];
                }
            } else {
                len++;
                if(len>size){
                    str+=this[i];
                }
            }
       }  
      return str;  
    }
function initCombo(groupCode,selector){
	var objs=new Array();
	var object={};
	$.ajax({
		url:basePath+'/datadicController/initCombobox',
		method:'post',
		data:{
			'groupCode':groupCode
		},
		async:false,
		success:function(data){
			if(data.mes=="success"){
				items=data.rows.datadicItems;
				var obj={};
				obj.key=null;
				obj.value="请选择......";
				objs.push(obj);
				$.each(items,function(i){
					var obj={};
					obj.key=items[i].dataitemCode.toString();
					obj.value=items[i].dataitemName;
					objs.push(obj);
					object[items[i].dataitemCode]=items[i].dataitemName;
				});
			}else{
			}
		}	
	});
	
	$("#"+selector).combobox({
				valueField:'key',    
    			textField:'value',
    			data: objs,
    			editable:false,
    			value:null
			});
	return object;
}
/**
 * 日期格式化
 * @param {} date
 * @return {}
 */
function dataformatter(date){
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
		}
		
function myparser(s){
			if (!s) return new Date();
			var ss = (s.split('-'));
			var y = parseInt(ss[0],10);
			var m = parseInt(ss[1],10);
			var d = parseInt(ss[2],10);
			if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
				return new Date(y,m-1,d);
			} else {
				return new Date();
			}
		}

// $.ajaxSetup({
//      // 同步
//      async:false, // 默认true，异步
//      // 发送cookie
//      // 请求返回
//      complete:function(data,status){
//      		if(data.responseJSON.code=="403"){//登录失效,重定向登录页面
//			window.location.href="/pm/";
//		  }
//      }
//});
