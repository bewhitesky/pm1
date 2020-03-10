$.extend($.fn.validatebox.defaults.rules, {    
    customValidator: {    
        validator: function(value,param){
        	var str=decodeURIComponent(param[0]);
        	$.fn.validatebox.defaults.rules.customValidator.message=param[1];
        	var reg=new RegExp(str);
            return reg.test(value);    
        },    
        message: 'Field do not match.'   
    },
    intOrFloat:{
    	validator:function(value){
    		return /^\d+(\.\d+)?$/i.test(value);
    	},
    	message: "请输入数字!"
    }
}); 