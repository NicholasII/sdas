/**
 * by dq 
 * 2017年9月20日下午10:19:48
 * TODO
 */
var capacityworkurl = ctx + "/outserverwork/getlistout";
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
        autowidth: true,
        shrinkToFit: true,
        rowNum: 10,
        rowList: [10, 20, 30],
        colNames: ['发生时间', '恢复时间', '故障类型','VIP类型' ,'小区名称','基站名称'],
        colModel: [
            {
                name: 'start_time',
                index: 'start_time',
                width: 35,
                formatter:function(cellvalue, options, rowObject) {  
                    return $.hd_jqGrid.dateTimeFormatter(cellvalue);  
                }
            },
            {
                name: 'recovery_time',
                index: 'recovery_time',
                width: 35,
                formatter:function(cellvalue, options, rowObject) {  
                    return $.hd_jqGrid.dateTimeFormatter(cellvalue);  
                }
            },
            {
                name: 'fault_type',
                index: 'fault_type',
                width: 20
            },
            {
                name: 'vip_type',
                index: 'vip_type',
                width: 50
            },
            {
                name: 'cell_name',
                index: 'cell_name',
                width: 30
            },
            {
                name: 'station_name',
                index: 'station_name',
                width: 10
            }
        ],
        pager: "#pager_list_1",
        viewrecords: true,
        hidegrid: false,
        gridComplete:function(){
        	//获取某列的每一行id
        	var ids = jQuery("#table_list_1").jqGrid("getDataIDs");
        	for(var i=0;i<ids.length;i++){
        	    /*var id = ids[i];
        	    var names = $("#table_list_1").getCell(id,'network_name');
        	    var url='<a href="#">'+names+'</a>';
        	    var link = "/sdas/general/cellhome/";
        	    var url2='<a href=javascript:gotocellhome("'+link+'","'+names+'")>日常监控</a>';
        	    var link2 = "/sdas/fault/page";      	    
        	    var url3='<a href=javascript:gotoprb("'+link2+'","'+names+'")>PRB利用率</a>';
        	    var url4='<a href="#">切换出成功</a>';
        	    $("#table_list_1").jqGrid('setRowData',id,{network_name:url});
        	    $("#table_list_1").jqGrid('setRowData',id,{日常监控:url2});
        	    $("#table_list_1").jqGrid('setRowData',id,{PRB利用率:url3});
        	    $("#table_list_1").jqGrid('setRowData',id,{切换出成功:url4});*/
        	}
        }
	});
	// Add responsive to jqGrid
    $(window).bind('resize', function () {
        var width = $('.jqGrid_wrapper').width();
        $('#table_list_1').setGridWidth(width);

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