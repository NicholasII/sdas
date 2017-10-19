/**
 * by dq 
 * 2017年9月20日下午9:10:31
 * TODO
 */
var capacityworkurl = ctx + "/capacitywork/gettable";
var doubutworkurl = ctx +"/work/validatedoubt";
var aeraurl = ctx + "/capacitywork/belongare";
var validateurl = ctx +"/work/validate";
$(function(){
	$.jgrid.defaults.styleUI = 'Bootstrap';
	$.ajax({
		url: capacityworkurl,
		type:"GET",
		dataType:"json",
		success:function(data,status){
			var list = data.rows;
			refreshJqGrid(list);
		}
	});   
	$.ajax({
		url: aeraurl,
		type:"GET",
		dataType:"json",
		success:function(data,status){
			var areas = data.rows;
			for(var i=0;i<areas.length;i++){
				if (areas[i]!=null) {
					var option = $('<option>'+areas[i]+'</option>');
					$("#area").append(option);
				}
				
			}
		}
	});
	$("#timeselect").change(function(){
		if ($("#timeselect").val()=='按时间段') {
			$("#starttime").removeAttr("disabled");
			$("#endtime").removeAttr("disabled");
			$("#starttime").attr("placeholder",'请输入开始时间');
		}else if($("#timeselect").val()=='按日期'){
			$("#endtime").attr("disabled",true);
			$("#starttime").attr("placeholder",'请输入发生时间');
		}else {
			$("#starttime").attr("disabled",true);
			$("#endtime").attr("disabled",true);
			$("#starttime").attr("placeholder",'请输入开始时间');
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
        colNames: ['发生时间', '小区名称', '所属区域','监控内容' ,'监控时值','告警级别','原因','精品级别','越限次数','完成时间','状态'],
        colModel: [
            {
                name: 'occurrence_time',
                index: 'occurrence_time',
                width: 150,
                formatter:function(cellvalue, options, rowObject) {  
                    return $.hd_jqGrid.dateTimeFormatter(cellvalue);  
                }
            },
            {
                name: 'cellid',
                index: 'cellid',
                width: 250
            },
            {
                name: 'belong_area',
                index: 'belong_area',
                width: 50
            },
            {
                name: 'monitor_content',
                index: 'monitor_content',
                width: 200
            },
            {
                name: 'monitor_value',
                index: 'monitor_value',
                width: 100
            },
            {
                name: 'alerm_level',
                index: 'alerm_level',
                width: 50
            },
            {
                name: 'reasions',
                index: 'reasions',
                width: 50
            },
            {
                name: 'boutique_level',
                index: 'boutique_level',
                width: 100
            },
            {
            	name: 'limit_times',
                index: 'limit_times',
                width: 50
            },
            {
            	name: 'complete_time',
                index: 'complete_time',
                width: 150,
                formatter:function(cellvalue, options, rowObject) {  
                    return $.hd_jqGrid.dateTimeFormatter(cellvalue);  
                }
            },{
                name: 'questionflag',
                index: 'questionflag',
                width: 100,
                hidden:true
            }
        ],
        pager: "#pager_list_1",
        viewrecords: true,
        hidegrid: false,
        gridComplete:function(){
            //获取某列的每一行id
            var ids = jQuery("#table_list_1").jqGrid("getDataIDs");
            for(var i=0;i<ids.length;i++){
                var id = ids[i];
                var questionflag = $("#table_list_1").getCell(id,'questionflag');
                var rowData = $("#table_list_1").getRowData(id);
                 if(questionflag=="1"){
                     $('#'+ids[i]).find("td").addClass("SelectRed");
                 }else if(questionflag=="0" || questionflag=="2"){
                    $('#'+ids[i]).find("td").addClass("SelectGre");
                 }
            }
        }
	});
    $(window).bind('resize', function () {
        var width = $('.jqGrid_wrapper').width();
        $('#table_list_1').setGridWidth(width);

    });
}
function select(){
	var name = $("#name").val();
	var area = $("#area").val();
	var starttime = $("#starttime").val();
	var endtime = $("#endtime").val();
	var content = $("#content").val();
	if ($("#timeselect").val()=='最近一日') {
		
	}
	$("#table_list_1").jqGrid("clearGridData");
	$.ajax({
		url:capacityworkurl,
		type:"post",
		dataType:"json",
		data:{
			'cellname':name,
			'area':area,
			'starttime':starttime,
			'endtime':endtime,
			'content':content
		},
		success:function(data,status){
			var list = data.rows;
			$("#table_list_1").jqGrid('setGridParam',{
			      datatype:'local',
			      data : list,   
			      page:1
			}).trigger("reloadGrid");
		},
		error:function(data,status){
			alert(status);
		}
	});
}
$(function(){
	$("#doubtwork").change(function() {
		if ($("#doubtwork").is(':checked')) {
			$.ajax({
				url: doubutworkurl,
				type:"GET",
				dataType:"json",
				success:function(data,status){
					jQuery("#table_list_1").jqGrid("clearGridData");
					var data1 = data.rows;
					$("#table_list_1").jqGrid('setGridParam',{
					      datatype:'local',
					      data : data1,   
					      page:1
					}).trigger("reloadGrid");
				}
			});
		}else{
			$.ajax({
				url: capacityworkurl,
				type:"GET",
				dataType:"json",
				success:function(data,status){	
					jQuery("#table_list_1").jqGrid("clearGridData");
					var list = data.rows;
					$("#table_list_1").jqGrid('setGridParam',{
					      datatype:'local',
					      data : list,   
					      page:1
					}).trigger("reloadGrid");
				}
			});
		}
		
	});
	
});
function validate(){
    $.ajax({
                url: validateurl,
                type:"GET",
                dataType:"json",
                success:function(data,status){  
                    var success = data.success;
                    jQuery("#table_list_1").jqGrid("clearGridData");
                    if(success){
                        var list = data.rows;
                        $("#table_list_1").jqGrid('setGridParam',{
                            datatype:'local',
                            data : list,   
                            page:1
                        }).trigger("reloadGrid");
                    }                                
                }
            });
}