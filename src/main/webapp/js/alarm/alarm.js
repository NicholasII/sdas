/**
 * 预警页面
 * by dq 
 * 2017年10月13日下午9:10:31
 * TODO
 */
var alarmurl = ctx + "/alarm/all";
var starttime="";
var endtime="";
var work_date=null;
$(function(){
    $.jgrid.defaults.styleUI = 'Bootstrap';
    $.ajax({
        url: alarmurl,
        data:{
        	'cellid':'',
        	'daynum':work_date,
        	'starttime':starttime,
            'endtime':endtime},
        type:"GET",
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
        colNames: ['发生时间', '小区名称', '次数'],
        colModel: [
            {
                name: 'yyyyMMdd',
                index: 'yyyyMMdd',
                width: 150
            },
            {
                name: 'cell_code',
                index: 'cell_code',
                width: 250
            },
            {
                name: 'count',
                index: 'count',
                width: 50
            }
        ],
        gridComplete:function(){
        	// 获取某列的每一行id
        	var ids = jQuery("#table_list_1").jqGrid("getDataIDs");
        	for(var i=0;i<ids.length;i++){
        	    var id = ids[i];
        	    var cell_code = $("#table_list_1").getCell(id,'cell_code');
        	    var monitor_content = $("#table_list_1").getCell(id,'monitor_content');
        	    var link2 = "/sdas/general/cellhome/";
        	    var url2='<a href=javascript:gotocellhome("'+link2+'","'+cell_code+'")>'+cell_code+'</a>';
        	    $("#table_list_1").jqGrid('setRowData',id,{cell_code:url2});
        	}
        },
        pager: "#pager_list_1",
        viewrecords: true,
        hidegrid: false
    });
    $(window).bind('resize', function () {
        var width = $('.jqGrid_wrapper').width();
        $('#table_list_1').setGridWidth(width);

    });
}
function select(daynum){
    var name = $("#cellname").val();
    if(work_date==null&&!$("#timeselect").is(":hidden")){
    	starttime = $("#starttime").val();
        endtime = $("#endtime").val();
    }
    $("#table_list_1").jqGrid("clearGridData");
    $.ajax({
        url:alarmurl,
        type:"post",
        dataType:"json",
        data:{
            'cellname':name,
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
        },
        error:function(data,status){
            alert(status);
        }
    });
}
function gotocellhome(url,params,station){
	
	var a_parent = $(".page-tabs-content",window.parent.document);
	var iframe_parent = $("#content-main",window.parent.document);
	
	var item = $('<a href="javascript:;" class="active J_menuTab" data-id="'+url+'">日常监控 <i class="fa fa-times-circle"></i></a>');
	var content = $('<iframe class="J_iframe" name="iframe10" width="100%" height="100%" src="'+url+'?name='+params+'&stationname='+station+'" frameborder="0" data-id="'+url+'" seamless></iframe>');
	
	a_parent.children("a").removeClass("active");

	if(a_parent.has('a[data-id="'+url+'"]').length>0){
		
		a_parent.children('a[data-id="'+url+'"]').addClass("active");
		iframe_parent.children("iframe").css("display","none");
		iframe_parent.children().remove('iframe[data-id="'+url+'"]');
		content.css("display","inline");
		iframe_parent.prepend(content);
	}else {
		
		content.css("display","inline");
		a_parent.append(item);
		iframe_parent.children("iframe").css("display","none");
		iframe_parent.prepend(content);
	}
}