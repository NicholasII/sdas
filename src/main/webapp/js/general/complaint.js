/**
 * by dq 
 * 2017年9月15日上午9:27:33
 * TODO
 */
var starttime="";
var endtime="";
var work_date=7;

$.jgrid.defaults.styleUI = 'Bootstrap';
if(celldata != undefined){
	var list = JSON.parse(celldata);
	refreshJqGrid(list);
}else{
	$.ajax({
		url: "/sdas/complain/getcomplist",
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
			if ($(this).html() == "今日") {
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
			if(work_date!=null){
				select(work_date);
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
function select(daynum){
	$("#table_list_1").jqGrid("clearGridData");
	var cellname = $("#name").val();
	if(work_date==null){
    	starttime = $("#starttime").val();
        endtime = $("#endtime").val();
    }
	$.ajax({
		url: "/sdas/complain/getcomplist",
		type:"POST",
		data:{
			'cellname':cellname,
			'daynum':daynum,
            'starttime':starttime,
            'endtime':endtime,
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


