/**
 * by dq 2017年9月14日下午8:00:31
 */
$(function(){
    $.jgrid.defaults.styleUI = 'Bootstrap';
    /*
     * 可疑工单
     */
    $.ajax({
        url: "/sdas/work/test",
        type:"GET",
        success:function(data,status){
            var temp = eval('(' + data + ')'); 
            var list = temp.rows;
            refreshJqGrid(list);
            
        },
        error:function(data){
            alert("fail");
        }
    }); 
    /*
	 * 预警
	 */
    $.ajax({
        url: "/sdas/alarm/currentday",
        type:"GET",
        success:function(data,status){
            var temp = eval('(' + data + ')'); 
            var list = temp.rows;
            refreshJqGrid_alarm(list);
        },
        error:function(data){
            alert("fail");
        }
    });
	 
	/*
     * 指标预警
     */	 
    $.ajax({
        url: "/sdas/indexalarm/currentday",
        type:"GET",
        success:function(data,status){
            var temp = eval('(' + data + ')'); 
            var list = temp.rows;
            refreshJqGrid_alarm_index(list);
        },
        error:function(data){
            alert("fail");
        }
    });
});


/*function scroll2(){
 
    $("#table tr:first").appendTo($("#table"));
    $("#table1 tr:first").appendTo($("#table1"));
}
setInterval(function(){
    scroll2()
},1000);*/

function alarmrefresh(){
    $.ajax({
        url: "/sdas/alarm/currentday",
        type:"GET",
        success:function(data,status){
            $("#table_list_alarm").jqGrid("clearGridData")
            var temp = eval('(' + data + ')'); 
            var list = temp.rows;
            $("#table_list_alarm").jqGrid('setGridParam',{
                 datatype:'local',
                 data:list,// newData是符合格式要求的重新加载的数据
                page:1// 哪一页的值
            }).trigger("reloadGrid");
        }
    }); 
}
setInterval(function(){
    alarmrefresh()
},15*60*1000);

function refreshJqGrid(list){
    $.jgrid.defaults.styleUI = 'Bootstrap';
    $("#table_list_work").jqGrid({
        data:list,
        datatype: "local",
        height: "auto",
        autowidth: true,
        shrinkToFit: true,
        rowNum: 10,
        rowList: [10, 20, 30],
        colNames: ['发生时间', '小区名称', '监控内容'],
        colModel: [
            {
                name: 'occurrence_time',
                index: 'occurrence_time',
                width: 50,
                formatter:function(cellvalue, options, rowObject) {  
                    return $.hd_jqGrid.dateTimeFormatter(cellvalue);  
                }
            },
            {
                name: 'cellid',
                index: '小区名称',
                width: 50
            },
            {
                name: 'monitor_content',
                index: 'monitor_content',
                width: 50
            }
        ],
        pager: "#pager_list_work",
        viewrecords: true,
        hidegrid: false,
        gridComplete:function(){
            // 获取某列的每一行id
            var ids = jQuery("#table_list_work").jqGrid("getDataIDs");
            for(var i=0;i<ids.length;i++){
                var id = ids[i];
                var cellid = $("#table_list_work").getCell(id,'cellid');
                if(cellid.indexOf("<a")<0){
                   var link2 = "/sdas/general/cellhome/";
                   var url2='<a href=javascript:gotocellhome("'+link2+'","'+cellid+'")>'+cellid+'</a>';
                   $("#table_list_work").jqGrid('setRowData',id,{cellid:url2});
                }               
                var monitor_content = $("#table_list_work").getCell(id,'monitor_content');
                if(monitor_content.indexOf("<a")<0){
                    var link3 = "/sdas/fault/page";             
                    var url3='<a href=javascript:gotoprb("'+link3+'","'+monitor_content+'")>'+monitor_content+'</a>';           
                    $("#table_list_work").jqGrid('setRowData',id,{monitor_content:url3});
                }                       
            }
        }
    });
    $(window).bind('resize', function () {
        var width = $('.jqGrid_wrapper').width();
        $('#table_list_work').setGridWidth(width);

    });
}

function refreshJqGrid_alarm(list){
    
    $("#table_list_alarm").jqGrid({
        data:list,
        datatype: "local",
        height: "auto",
        autowidth: true,
        shrinkToFit: true,
        rowNum: 5,
        rowList: [10, 20, 30],
        colNames: ['发生时间', '小区名称', '次数'],
        colModel: [
            {
                name: 'yyyyMMdd',
                index: 'yyyyMMdd',
                width: 40
            },
            {
                name: 'cell_code',
                index: 'cell_code',
                width: 50
            },
            {
                name: 'count',
                index: 'count',
                width: 30
            }
        ],
        gridComplete:function(){
        	// 获取某列的每一行id
        	var ids = jQuery("#table_list_alarm").jqGrid("getDataIDs");
        	for(var i=0;i<ids.length;i++){
        	    var id = ids[i];
        	    var cell_code = $("#table_list_alarm").getCell(id,'cell_code');
        	    if(cell_code.indexOf("<a")<0){
                    var link2 = "/sdas/general/cellhome/";
                    var url2='<a href=javascript:gotocellhome("'+link2+'","'+cell_code+'")>'+cell_code+'</a>';
                    $("#table_list_alarm").jqGrid('setRowData',id,{cell_code:url2});
                }   	    
        	}
        },
        pager: "#pager_list_alarm",
        viewrecords: true,
        hidegrid: false
    });
    $(window).bind('resize', function () {
        var width = $('.jqGrid_wrapper').width();
        $('#table_list_alarm').setGridWidth(width);

    });
}


function refreshJqGrid_alarm_index(list){
    
    $("#table_list_alarm_index").jqGrid({
        data:list,
        datatype: "local",
        height: "auto",
        autowidth: true,
        shrinkToFit: true,
        rowNum: 5,
        rowList: [10, 20, 30],
        jsonReader: {  
                 root:"list", page:"pageNum", total:"pages",
                 records:"total", repeatitems:false, id : "id"
             },
        colNames: ['发生时间', '小区名称','指标', '次数'],
        colModel: [
            {
                name: 'yyyyMMdd',
                index: 'yyyyMMdd',
                width: 40
            },
            {
                name: 'cell_code',
                index: 'cell_code',
                width: 50
            },{
                name: 'type',
                index: 'type',
                width: 40
            },
            {
                name: 'count',
                index: 'count',
                width: 30
            }
        ],
        gridComplete:function(){
            // 获取某列的每一行id
            var ids = jQuery("#table_list_alarm_index").jqGrid("getDataIDs");
            for(var i=0;i<ids.length;i++){
                var id = ids[i];
                var cell_code = $("#table_list_alarm_index").getCell(id,'cell_code');
                if(cell_code.indexOf("<a")<0){
                    var link2 = "/sdas/general/cellhome/";
                    var url2='<a href=javascript:gotocellhome("'+link2+'","'+cell_code+'")>'+cell_code+'</a>';
                    $("#table_list_alarm_index").jqGrid('setRowData',id,{cell_code:url2});
                }      
            }
        },
        pager: "#pager_list_alarm_index",
        viewrecords: true,
        hidegrid: false
    });
    $(window).bind('resize', function () {
        var width = $('.jqGrid_wrapper').width();
        $('#table_list_alarm_index').setGridWidth(width);

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
function gotoprb(url,params){
	
	var a_parent = $(".page-tabs-content",window.parent.document);
	var iframe_parent = $("#content-main",window.parent.document);
	
	var item = $('<a href="javascript:;" class="active J_menuTab" data-id="'+url+'">指标监控 <i class="fa fa-times-circle"></i></a>');
	var content = $('<iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="'+url+'?name='+params+'" frameborder="0" data-id="'+url+'" seamless></iframe>');
	
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