/**
 * by ly 2017年10月31日下午14:00:31
 */
function section(){
	var Source=$("#Source").val();
	var Output=$("#Output").val();
	var ifDel=$("input[name='deleteOut']:checked").val();
	$.ajax({
		url:"/sdas/timer/section",
		data:{
			"source":Source,
			"output":Output,
			"ifdel":ifDel
		},
		daraType:"post",
		success:function(data){
			alert("切片成功！");
			//还原
			$("#Source").val("");
			$("#Output").val("");
			$(":radio[name='deleteOut'][value='1']").prop("checked", "checked");
		}
	});
}
function calculate(id,url,areaId){
	var time=$(id).val();
	$.ajax({
		url:url,
		data:{
			"time":time
		},
		daraType:"post",
		success:function(data){
			alert("计算成功！");
		}
	});
}