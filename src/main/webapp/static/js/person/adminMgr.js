/**
 * 
 */
 var today
 $(document).ready(function(){
 	testStageMap=initCombo("test_stage",null);
 	pDepartmentMap=initCombo("project_department",null);
	pTypeMap=initCombo("project_type",null);
	pLevelMap=initCombo("project_level",null);
 	var date=new Date();
 	today=dataformatter(date);
 	$('#cc').calendar({    
	    current:new Date(),
	    onSelect:function(date){
	    	today=dataformatter(date);
	    	getTask();    	
	    }
	}); 
 	getTask();
 });
 /**********************获取日程安排******************************/
 function getTask(){
 	$.ajax({
 		url:basePath+"/baseInfoController/countRemainDay",
 		type:"post",
 		data:{
 			'today':today
 		},
 		async:false,
 		success:function(data){
 			var prolist=data.rows;
 			var html="";
 			$.each(prolist,function(i,project){
 				html=html+"<li class='today-li'><a href='javascript:void(0)' onclick='showDetail("+JSON.stringify(project)+")'>"+project.productName+"距"+project.type+"还有<span style='color:red'>"+project.remainDay+"</span>天</a></li>";
 			});			
 			$("#schedule").html(html);
 			if($(".today-li a").length>0){
 				$(".today-li a")[0].click();
 			}		
 		}
 	});
 }
 
 /***************显示当前任务*****************************/
 function showDetail(project){
 	$("#detailForm #id").val(project.id);
 	$("#detailForm #pName").text(project.pname);
 	$("#detailForm #productName").text(project.productName);
	$("#detailForm #internalTime").text(project.internalTime==null?"":timestampFormat(project.internalTime,"yyyy-MM-dd"));
 	$("#detailForm #factoryTime").text(project.factoryTime==null?"":timestampFormat(project.factoryTime,"yyyy-MM-dd"));
 	$("#detailForm #thirdTime").text(project.thirdTime==null?"":timestampFormat(project.thirdTime,"yyyy-MM-dd"));
 	$("#detailForm #demandExpTime").text(project.demandExpTime==null?"":timestampFormat(project.demandExpTime,"yyyy-MM-dd"));
 	$("#detailForm #designExpTime").text(project.designExpTime==null?"":timestampFormat(project.designExpTime,"yyyy-MM-dd"));
 	$("#detailForm #pDepartment").text(pDepartmentMap[project.pdepartment]);
 	$("#detailForm #pType").text(pTypeMap[project.ptype]);
 	$("#detailForm #pLevel").text(pLevelMap[project.plevel]);
 	$("#detailForm #pmName").text(project.pmName);
 	$("#detailForm #pmTel").text(project.pmTel+"/"+project.pmEmail);
 	$("#detailForm #testType").text(project.testType);
// 	$("#detailForm #testStage").text(testStageMap[task.testStage]);
 }
 
function openWindow(options){
	$("#w").window(options);
}
function closeWindow(){
	$("#w").window("close");
}