/**
 * by dq 
 * 2017年9月15日上午9:27:33
 * TODO
 */
$.jgrid.defaults.styleUI = 'Bootstrap';
if(celldata != undefined){
	var list = JSON.parse(celldata);
	refreshJqGrid(list);
}else{
	$.ajax({
		url: "/sdas/complain/getalllist",
		type:"GET",
		dataType:"json",
		success:function(data,status){
			var list = data.rows;
			refreshJqGrid(list);
			
		}
	});
}


function refreshJqGrid(list){
	$("#table_list_1").jqGrid({
		data:list,
		datatype: "local",
        height: "auto",
        autowidth: true,
        shrinkToFit: true,
        rowNum: 10,
        rowList: [10, 20, 30],
        colNames: ['时间', '电话', '常住小区1', '常住小区2','常住小区3'],
        colModel: [
            {
                name: 'record_time',
                index: 'record_time',
                width: 40,
                formatter:function(cellvalue, options, rowObject) {  
                    return $.hd_jqGrid.dateTimeFormatter(cellvalue);  
                }
            },
            {
                name: 'phone_number',
                index: 'phone_number',
                width: 40
            },
            {
                name: 'live_cellname1',
                index: 'live_cellname1',
                width: 60
            },
            {
                name: 'live_cellname2',
                index: 'live_cellname2',
                width: 60
            },
            {
                name: 'live_cellname3',
                index: 'live_cellname3',
                width: 60
            }
        ],
        pager: "#pager_list_1",
        viewrecords: true,
        hidegrid: false,
        gridComplete:function(){
        	//获取某列的每一行id
        	/*var ids = jQuery("#table_list_1").jqGrid("getDataIDs");
        	for(var i=0;i<ids.length;i++){
        	    var id = ids[i];
        	    var names = $("#table_list_1").getCell(id,'countnum');
        	    var datas = $("#table_list_1").getCell(id,'cellname');
        	    var url="<a href=/sdas/work/complaint/?cellname='"+datas+"'>"+names+"</a>";
        	    $("#table_list_1").jqGrid('setRowData',id,{countnum:url});
        	}*/
        }
	});
	// Add responsive to jqGrid
    $(window).bind('resize', function () {
        var width = $('.jqGrid_wrapper').width();
        $('#table_list_1').setGridWidth(width);

    });
}
function select(){
	$("#table_list_1").jqGrid("clearGridData");
	var cellname = $("#name").val();
	$.ajax({
		url: "/sdas/complain/getcelllist",
		type:"POST",
		data:{
			'cellname':cellname
		},
		dataType:"json",
		success:function(data,status){
			var list = data.rows;
			$("#table_list_1").jqGrid('setGridParam',{  // 重新加载数据
			      datatype:'local',
			      data : list,   //  newdata 是符合格式要求的需要重新加载的数据 
			      page:1
			}).trigger("reloadGrid");
		}
	}); 
}


