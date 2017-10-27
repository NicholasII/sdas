/**
 * by dq 
 * 2017年9月15日上午9:27:33
 * TODO
 */
var starttime="";
var endtime="";
var work_date=7;

$.jgrid.defaults.styleUI = 'Bootstrap';
if (celldata != undefined) {
	var list = JSON.parse(celldata);
	refreshJqGrid(list);
} else {
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
			
		},
                error:function(data){
                    alert("fail");
                }
			});
}
function refreshGrid(target) {
	$('#table_list_1').bootstrapTable({
		data : target,
		cache : false,
		striped : true,
		pagination : true,
		pageSize : 10,
		pageNumber : 1,
		pageList : [5, 10, 15, 20],
		clickToSelect : true,
		sidePagination : 'server',// 设置为服务器端分页
		columns : [{
					field : 'record_time',
					title : '受理时间',
					align : 'center',
					valign : "middle"
				}, {
					field : "phone_number",
					title : "受理电话",
					align : "center",
					valign : "middle"
				}, {
					field : "live_cellname1",
					title : "常驻小区1",
					align : "center",
					valign : "middle",
					formatter : function(value, row, index) {
						var operation = '<a href="#" onclick="">' + value
								+ '</a>';
						return operation;
					}
				}, {
					field : "live_cellname2",
					title : "常驻小区2",
					align : "center",
					valign : "middle",
					formatter : function(value, row, index) {
						var operation = '<a href="#" onclick="">' + value
								+ '</a>';
						return operation;
					}
				}, {
					field : "live_cellname3",
					title : "常驻小区3",
					align : "center",
					valign : "middle",
					formatter : function(value, row, index) {
						var operation = '<a href="#" onclick="">' + value
								+ '</a>';
						return operation;
					}
				}],
		onPageChange : function(size, number) {
			// searchData();
		},
		queryParams: function queryParams(params) {   //设置查询参数  
              var param = {    
                  pageNumber: params.pageNumber,    
                  pageSize: params.pageSize  
              };    
              return param;                   
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
function refreshJqGrid(data1) {
	$("#table_list_1").jqGrid({
				data : data1,
				datatype : "local",
				height : "auto",
				autowidth : true,
				shrinkToFit : true,
				rowNum : 10,
				rowList : [10, 20, 30],
				colNames : ['受理时间', '受理电话','常住小区1', '常住小区2', '常住小区3'],
				colModel : [{
							name : 'record_time',
							index : '1',
							width : 40,
							formatter : function(cellvalue, options, rowObject) {
								return $.hd_jqGrid.dateTimeFormatter(cellvalue);
							}
						}, {
							name : 'phone_number',
							index : '2',
							width : 40
						}, {
							name : 'live_cellname1',
							index : 'live_cellname1',
							width : 60
						}, {
							name : 'live_cellname2',
							index : 'live_cellname2',
							width : 60
						}, {
							name : 'live_cellname3',
							index : 'live_cellname3',
							width : 60
						}],
				pager : "#pager_list_1",
				viewrecords : true,
				hidegrid : false,
				jsonReader : {
					root : 'row',
					total : 'pages',
					page : 'pageNum',
					records : 'total',
					repeatitems : false
				},
				gridComplete : function() {
					//获取某列的每一行id
					var ids = jQuery("#table_list_1").jqGrid("getDataIDs");
					for (var i = 0; i < ids.length; i++) {
						var id = ids[i];
						var live_cellname1 = $("#table_list_1").getCell(id,'live_cellname1');
                        var live_cellname2 = $("#table_list_1").getCell(id,'live_cellname2');
                        var live_cellname3 = $("#table_list_1").getCell(id,'live_cellname3');
                        var link = "/sdas/general/cellhome/";
                        var url1='<a href=javascript:iframeconvert("'+link+'","日常监控","'+live_cellname1+'")>'+live_cellname1+'</a>';
                        var url2='<a href=javascript:iframeconvert("'+link+'","日常监控","'+live_cellname2+'")>'+live_cellname2+'</a>';
                        var url3='<a href=javascript:iframeconvert("'+link+'","日常监控","'+live_cellname3+'")>'+live_cellname3+'</a>';
                        $("#table_list_1").jqGrid('setRowData',id,{live_cellname1:url1});
                        $("#table_list_1").jqGrid('setRowData',id,{live_cellname2:url2});
                        $("#table_list_1").jqGrid('setRowData',id,{live_cellname3:url3});
					}
				}
			});
	// Add responsive to jqGrid
	$(window).bind('resize', function() {
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
