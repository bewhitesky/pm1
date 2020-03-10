$(function(){
	$.each($("dt"),function(i,el){
		if(el.getAttribute("state")!=null && el.getAttribute("state")=="show"){
			el.getElementsByTagName("ul")[0].style.display="block";
			el.setAttribute("state","show");
		}else{
			el.getElementsByTagName("ul")[0].style.display="none";
			el.setAttribute("state","hide");
		}
	});

	$("dt a").click(function(){
		debugger;
		if($(this)[0].parentNode.getAttribute("state")=="show"){
			$(this).next().hide();
			$(this)[0].parentNode.setAttribute("state","hide");
		}else{
			$(this).next().show();
			$(this)[0].parentNode.setAttribute("state","show");
		}
	});
});

function addTab(title, url){
	if ($('#tabs').tabs('exists', title)){
		$('#tabs').tabs('select', title);
	} else {
		var content = '<iframe scrolling="no" frameborder="0"  src="'+url+'" style="width:100%;height:98%;"></iframe>';
		$('#tabs').tabs('add',{
			title:title,
			content:content,
			closable:true,
			height:42

		});
	}
}