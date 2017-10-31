/**
 * by dq 
 * 2017年9月20日下午9:10:31
 * TODO
 */
var capacityworkurl = "/sdas/work/allrtworks";
//var capacityworkurl = ctx + "/capacitywork/gettable";
var doubutworkurl = ctx +"/work/validatedoubt";
var aeraurl = ctx + "/capacitywork/belongare";
var validateurl = ctx +"/work/validate";
var starttime="";
var endtime="";
var work_date=null;
$(function(){
	$.jgrid.defaults.styleUI = 'Bootstrap';
	$.ajax({
		url: capacityworkurl,
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
                 if(questionflag=="0"){
                     $('#'+ids[i]).find("td").addClass("SelectRed");
                 }else if(questionflag=="2"){
                    $('#'+ids[i]).find("td").addClass("SelectYel");
                 }else if(questionflag=="1"){
                    $('#'+ids[i]).find("td").addClass("SelectGre");
                 }
                 
                 var id = ids[i];
         	    var cellid = $("#table_list_1").getCell(id,'cellid');
         	    var link = "/sdas/general/cellhome/";
         	    var url='<a href=javascript:gotocellhome("'+link+'","'+cellid+'")>'+cellid+'</a>';
         	    $("#table_list_1").jqGrid('setRowData',id,{cellid:url});
            }
        }
	});
    $(window).bind('resize', function () {
        var width = $('.jqGrid_wrapper').width();
        $('#table_list_1').setGridWidth(width);

    });
}
function select(daynum){
	var name = $("#name").val();
	var area = $("#area").val();
	var content = $("#content").val();
	if(work_date==null&&!$("#timeselect").is(":hidden")){
    	starttime = $("#starttime").val();
        endtime = $("#endtime").val();
    }    
    var questionflag = $("#result").val();
    if(questionflag=="正常工单"){
        questionflag = 1;
    }else if(questionflag=="高度可疑"){
        questionflag = 0;
    }else if(questionflag=="可疑工单"){
        questionflag = 2;
    }else{
       questionflag = 3; 
    }
	
	$("#table_list_1").jqGrid("clearGridData");
	$.ajax({
		url:capacityworkurl,
		type:"post",
		dataType:"json",
		data:{
			'cellid':name,
			'area':area,
			'daynum':daynum,
            'starttime':starttime,
            'endtime':endtime,
			'content':content,
            "questionflag":questionflag
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
				url: '/sdas/work/test',
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
				url: '/sdas/work/alltest',
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
	//load显示
	$(".loading_bk").show();
	$(".loading").show();
    jQuery("#table_list_1").jqGrid("clearGridData");
    $.ajax({
                url: validateurl,
                type:"GET",
                dataType:"json",
                success:function(data,status){  
                    var success = data.success;                   
                    if(success){
                        var list = data.rows;
                        setTimeout(function(){
                        	$("#table_list_1").jqGrid('setGridParam',{
                                datatype:'local',
                                data : list,   
                                page:1
                            }).trigger("reloadGrid");
                        	//load显示
                        	$(".loading_bk").hide();
                        	$(".loading").hide();
                        },3000);
                        
                    }                                
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