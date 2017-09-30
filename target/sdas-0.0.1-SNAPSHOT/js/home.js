/**
 * by dq 
 * 2017年9月14日下午8:00:31
 * TODO
 */
$(function(){
	$.jgrid.defaults.styleUI = 'Bootstrap';
	// Examle data for jqGrid
	$.ajax({
		url: "/sdas/complain/getlist",
		type:"GET",
		dataType:"json",
		success:function(data,status){
			var list = data.rows;
			refreshJqGrid(list);
			
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
        rowNum: 5,
        rowList: [10, 20, 30],
        colNames: ['时间', '小区名称', '投诉次数'],
        colModel: [
            {
                name: 'time',
                index: 'time',
                width: 40,
                formatter:function(cellvalue, options, rowObject) {  
                    return $.hd_jqGrid.dateTimeFormatter(cellvalue);  
                }
            },
            {
                name: 'cellname',
                index: 'cellname',
                width: 60
            },
            {
                name: 'countnum',
                index: 'countnum',
                width: 30
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
        	    var names = $("#table_list_1").getCell(id,'countnum');
        	    var datas = $("#table_list_1").getCell(id,'cellname');
        	    var url="<a href=/sdas/complain/page/?cellname="+datas+">"+names+"</a>";
        	    $("#table_list_1").jqGrid('setRowData',id,{countnum:url});
        	}
        }
	});
	// Add responsive to jqGrid
    $(window).bind('resize', function () {
        var width = $('.jqGrid_wrapper').width();
        $('#table_list_1').setGridWidth(width);

    });
}

function scroll2(){
    /*var table = document.getElementById("table").getElementsByTagName("tbody");*/
    /*var row = table.firstChild;
    table.remove(row);    
    table.append(row);*/
    
    $("#table tr:first").appendTo($("#table"));
    $("#table1 tr:first").appendTo($("#table1"));
}

setInterval(() => {
	scroll2()
}, 10000);