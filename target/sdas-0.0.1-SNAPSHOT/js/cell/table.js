/**
 * 
 */

$(function(){
	$.jgrid.defaults.styleUI = 'Bootstrap';
	$.ajax({
		url: "/sdas/cell/getcelllist",
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
        autowidth: true,
        shrinkToFit: true,
        rowNum: 10,
        rowList: [10, 20, 30],
        colNames: ['小区名称', '基站名称', '地区','级别' ,'覆盖场景','日常监控','PRB利用率','切换出成功'],
        colModel: [
            {
                name: 'network_name',
                index: 'network_name',
                width: 100
            },
            {
                name: 'station_name',
                index: 'station_name',
                width: 100
            },
            {
                name: 'location',
                index: 'location',
                width: 30
            },
            {
                name: 'group_type',
                index: 'group_type',
                width: 30
            },
            {
                name: 'cover_scene',
                index: 'cover_scene',
                width: 30
            },
            {
                name: '日常监控',
                index: '日常监控',
                width: 30
            },
            {
                name: 'PRB利用率',
                index: 'PRB利用率',
                width: 40,
                value: 'PRB利用率'
            },
            {
                name: '切换出成功',
                index: '切换出成功',
                width: 35,
                value: '切换出成功'
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
        	    $("#table_list_1").jqGrid('setRowData',id,{切换出成功:url4});
        	}
        }
	});
	// Add responsive to jqGrid
    $(window).bind('resize', function () {
        var width = $('.jqGrid_wrapper').width();
        $('#table_list_1').setGridWidth(width);

    });
}


function getdetail(id,datas){
	$.ajax({
		url:"/sdas/detail/page",
		type:"post",
		async:"false",
		data:{
			name:datas
		},
		success:function(data,status){
			alert(status);
		},
		error:function(data){
			alert(data);
		}
	});
}

function select(){
	var name = $("#name").val();
	var scene = $("#scene").val();
	var type = $("#type").val();
	$("#table_list_1").jqGrid("clearGridData");
	$.ajax({
		url:"/sdas/cell/getcelllist",
		type:"post",
		dataType:"json",
		data:{
			'name':name,
			'scene':scene,
			'type':type
		},
		success:function(data,status){
			var list = data.rows;
			$("#table_list_1").jqGrid('setGridParam',{  // 重新加载数据
			      datatype:'local',
			      data : list,   //  newdata 是符合格式要求的需要重新加载的数据 
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
function clear(){

	$("#name").val("");
	$("#scene").val("");
	$("#type").val("");
}

function gotocellhome(url,params){
	
	var a_parent = $(".page-tabs-content",window.parent.document);
	var iframe_parent = $("#content-main",window.parent.document);
	
	var item = $('<a href="javascript:;" class="active J_menuTab" data-id="'+url+'">日常监控</a>');
	var content = $('<iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="'+url+'?name='+params+'" frameborder="0" data-id="'+url+'" seamless></iframe>');
	
	a_parent.children("a").removeClass("active");

	if(a_parent.has('a[data-id="'+url+'"]').length>0){
		
		a_parent.children('a[data-id="'+url+'"]').addClass("active");
		iframe_parent.children("iframe").css("display","none");
		iframe_parent.children('iframe[data-id="'+url+'"]').css("display","inline");
	}else {
		
		content.css("display","inline");
		a_parent.append(item);
		$("#content-main",window.parent.document).children("iframe").css("display","none");
		$("#content-main",window.parent.document).prepend(content);
	}

	

}
function gotoprb(url,params){
	
	var a_parent = $(".page-tabs-content",window.parent.document);
	var iframe_parent = $("#content-main",window.parent.document);
	
	var item = $('<a href="javascript:;" class="active J_menuTab" data-id="'+url+'">PRB利用率</a>');
	var content = $('<iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="'+url+'?name='+params+'" frameborder="0" data-id="'+url+'" seamless></iframe>');
	
	a_parent.children("a").removeClass("active");
	
    if(a_parent.has('a[data-id="'+url+'"]').length>0){
		
		a_parent.children('a[data-id="'+url+'"]').addClass("active");
		iframe_parent.children("iframe").css("display","none");
		iframe_parent.children('iframe[data-id="'+url+'"]').css("display","inline");
	}else {
		
		content.css("display","inline");
		a_parent.append(item);
		$("#content-main",window.parent.document).children("iframe").css("display","none");
		$("#content-main",window.parent.document).prepend(content);
	}
}
