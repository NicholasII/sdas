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
        hidegrid: false
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