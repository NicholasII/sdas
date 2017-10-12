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
    
    $.ajax({
        url: "/sdas/capacitywork/doubttabletopsix",
        type:"GET",
        success:function(data,status){
            var temp = eval('(' + data + ')'); 
            var list = temp.rows;
            refreshJqGrid2(list);
            
        },
        error:function(data){
            alert("fail");
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

function refreshJqGrid2(list){
	$.jgrid.defaults.styleUI = 'Bootstrap';
	$("#table_list_2").jqGrid({
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
                index: 'cellid',
                width: 50
            },
            {
                name: 'monitor_content',
                index: 'monitor_content',
                width: 50
            }
        ],
        //pager: "#pager_list_2",
        viewrecords: true,
        hidegrid: false,
        gridComplete:function(){
        	//获取某列的每一行id
        	var ids = jQuery("#table_list_2").jqGrid("getDataIDs");
        	for(var i=0;i<ids.length;i++){
        	    var id = ids[i];
        	    var cellid = $("#table_list_2").getCell(id,'cellid');
        	    var monitor_content = $("#table_list_2").getCell(id,'monitor_content');
        	    var link2 = "/sdas/general/cellhome/";
        	    var url2='<a href=javascript:gotocellhome("'+link2+'","'+cellid+'")>'+cellid+'</a>';
        	    var link3 = "/sdas/fault/page";      	    
        	    var url3='<a href=javascript:gotoprb("'+link3+'","'+monitor_content+'")>'+monitor_content+'</a>';
        	    $("#table_list_2").jqGrid('setRowData',id,{cellid:url2});
        	    $("#table_list_2").jqGrid('setRowData',id,{monitor_content:url3});
        	}
        }
	});
    $(window).bind('resize', function () {
        var width = $('.jqGrid_wrapper').width();
        $('#table_list_2').setGridWidth(width);

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