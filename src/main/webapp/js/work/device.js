/**
 * by dq 
 * 2017年9月20日下午9:10:31
 * TODO
 */
var capacityworkurl = ctx + "/devicework/getlist";


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