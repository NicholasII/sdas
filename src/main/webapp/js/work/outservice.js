/**
 * by dq 
 * 2017年9月20日下午10:19:48
 * TODO
 */
var capacityworkurl = ctx + "/outserverwork/getlist";


$(function(){
	$.jgrid.defaults.styleUI = 'Bootstrap';
	$.ajax({
		url: capacityworkurl,
		type:"GET",
		dataType:"json",
		success:function(data,status){
			var list = data.rows;
			refreshJqGrid(list);
			$('body').addClass('loaded');
	        $('#loader-wrapper .load_title').remove();
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
function select(){
	var name = $("#name").val();
	var scene = $("#scene").val();
	var type = $("#type").val();
	$("#table_list_1").jqGrid("clearGridData");
	$.ajax({
		url:capacityworkurl,
		type:"post",
		dataType:"json",
		data:{
			'name':name,
			'scene':scene,
			'type':type
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