/**
 * 预警页面
 * by dq 
 * 2017年10月13日下午9:10:31
 * TODO
 */
var alarmurl = ctx + "/alarm/all";

$(function(){
    $.jgrid.defaults.styleUI = 'Bootstrap';
    $.ajax({
        url: alarmurl,
        type:"GET",
        dataType:"json",
        success:function(data,status){
            var list = data.rows;
            refreshJqGrid(list);
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
function select(){
    var name = $("#name").val();
    var starttime = $("#starttime").val();
    var endtime = $("#endtime").val();
    $("#table_list_1").jqGrid("clearGridData");
    $.ajax({
        url:alarmurl,
        type:"post",
        dataType:"json",
        data:{
            'cellname':name,
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