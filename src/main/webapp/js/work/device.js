/**
 * by dq 
 * 2017年9月20日下午9:10:31
 * TODO
 */
var capacityworkurl = ctx + "/devicework/getlistdevice";
var starttime="";
var endtime="";
var work_date=null;

$(function(){
	$.jgrid.defaults.styleUI = 'Bootstrap';
	$.ajax({
		url: capacityworkurl,
		type:"GET",
		data:{
        	'daynum':work_date,
        	'starttime':starttime,
            'endtime':endtime
            },
		dataType:"json",
		success:function(data,status){
			var list = data.rows;
			refreshJqGrid(list);
			$('body').addClass('loaded');
	        $('#loader-wrapper .load_title').remove();
		}
	});
	//列表时间选择
	$(".datePicker").click(function() {
		 $("#starttime").val("");
	     $("#endtime").val("");
		$(this).parent().find(".btn-info").removeClass("btn-info");
		$(this).parent().find(".btn-white").removeClass("btn-white");
		$(this).parent().find("button").addClass("btn-white");
		$(".search").removeClass("btn-white");
		$(".search").addClass("btn-info");
		$(this).parent().children(":last").css("display", "none");
				starttime="";
				endtime=""
			if($(this).html() == "全部"){
				work_date = null;	
				$(this).removeClass("btn-white");
				$(this).addClass("btn-info");
			}else if ($(this).html() == "今日") {
				work_date = 0;
				$(this).removeClass("btn-white");
				$(this).addClass("btn-info");
			} else if ($(this).html() == "一周") {
				work_date = 7;
				$(this).removeClass("btn-white");
				$(this).addClass("btn-info");
			} else if ($(this).html() == "一月") {
				work_date = 30;
				$(this).removeClass("btn-white");
				$(this).addClass("btn-info");
			} else if ($(this).html() == "按时间选择") {
				work_date=null;
				$(this).removeClass("btn-white");
				$(this).addClass("btn-info");
				$(this).parent().children(":last").css("display", "block");
			}
			if($("#timeselect").is(":hidden")){
				select(work_date);
			}
		});
});
function refreshJqGrid(list){
	$("#table_list_1").jqGrid({
		data:list,
		datatype: "local",
        height: "auto",
        autowidth: false,
        shrinkToFit: false,
        rowNum: 10,
        rowList: [10, 20, 30],
        colNames: ['工单主题', '建单时间', '告警标准名','网管告警消除时间' ,'告警网管来源','网元名称','故障发生时间','分派原因'],
        colModel: [
            {
                name: 'order_title',
                index: 'order_title',
                width: 600
                
            },
            {
                name: 'build_bill_time',
                index: 'build_bill_time',
                width: 200,
                formatter:function(cellvalue, options, rowObject) {  
                    return $.hd_jqGrid.dateTimeFormatter(cellvalue);  
                }
            },
            {
                name: 'net_alarm_name',
                index: 'net_alarm_name',
                width: 150
            },
            {
                name: 'net_remove_time',
                index: 'net_remove_time',
                width: 150,
                formatter:function(cellvalue, options, rowObject) {  
                    return $.hd_jqGrid.dateTimeFormatter(cellvalue);  
                }
            },
            {
                name: 'net_alarm_source',
                index: 'net_alarm_source',
                width: 100
            },
            {
                name: 'cell_name',
                index: 'cell_name',
                width: 200
            },
            {
                name: 'fault_occus_time',
                index: 'fault_occus_time',
                width: 150
            },
            {
                name: 'transt_reason',
                index: 'transt_reason',
                width: 100
            }
        ],
        pager: "#pager_list_1",
        viewrecords: true,
        hidegrid: false
	});
}
function select(daynum){
	/*var name = $("#name").val();
	var scene = $("#scene").val();
	var type = $("#type").val();*/
	if(work_date==null&&!$("#timeselect").is(":hidden")){
    	starttime = $("#starttime").val();
        endtime = $("#endtime").val();
    }
	$("#table_list_1").jqGrid("clearGridData");
	$.ajax({
		url:capacityworkurl,
		type:"post",
		dataType:"json",
		data:{
			'daynum':daynum,
            'starttime':starttime,
            'endtime':endtime,
		},
		success:function(data,status){
			var list = data.rows;
			$("#table_list_1").jqGrid('setGridParam',{
			      datatype:'local',
			      data : list,   
			      page:1
			}).trigger("reloadGrid");
			$('body').addClass('loaded');
	        $('#loader-wrapper .load_title').remove();
		},
		error:function(data,status){
			alert(status);
		}
	});
}