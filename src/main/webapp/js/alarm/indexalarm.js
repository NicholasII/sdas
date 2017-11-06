/**
 * 预警页面
 * by dq 
 * 2017年10月13日下午9:10:31
 * TODO
 */
var alarmurl = ctx + "/indexalarm/all";
var starttime = "";
var endtime = "";
var work_date = null;
$(function(){
    $('#table_list_1').bootstrapTable({
        cache : false,
        striped : true,
        pagination : true,
        toolbar : '#toolbar',
        pageSize : 10,
        pageNumber : 1,
        pageList : [ 5, 10, 20 ],
        clickToSelect : true,
        sidePagination : 'server',// 设置为服务器端分页
        columns : [
            { field : "yyyyMMdd", title : "发生时间", align : "center", valign : "middle"},
            { field : "cell_code", title : "小区名称", align : "center", valign : "middle",
                formatter:function(value,row,index){
                    var url = "/sdas/general/cellhome/";
                    var params = "[{\"key\":\"name\",\"value\":\""+value+"\"}]";
                    var url = '<a href=javascript:iframeconvert("' + url + '","日常监控",' + params + ')>' + value + '</a>';
                     return url;
                }},
            { field : "type", title : "指标类型", align : "center", valign : "middle" },
            { field : 'count', title : '次数', align : 'left', valign : 'middle' }
        ],
        onPageChange : function(size, number) {
            searchInfo();
        },
        formatNoMatches : function() {
            return "NOT_FOUND_DATAS";
        }
    });
    searchInfo();
    //
});

// 查询表格信息
function searchInfo() {
    var data = {};
    data.daynum = work_date;
    data.starttime =  starttime;
    data.endttime = endtime;
    commonRowDatas("table_list_1", data, alarmurl, "commonCallback", true);
}

$(function() {
			// 列表时间选择
			$(".datePicker").click(function() {
                $("#starttime").val("");
						$("#endtime").val("");
						$(this).parent().find(".btn-info")
								.removeClass("btn-info");
						$(this).parent().find(".btn-white")
								.removeClass("btn-white");
						$(this).parent().find("button").addClass("btn-white");
						$(".search").removeClass("btn-white");
						$(".search").addClass("btn-info");
						$(this).parent().children(":last").css("display",
								"none");
						starttime = "";
						endtime = ""
						if ($(this).html() == "全部") {
							work_date = null;
							$(this).removeClass("btn-white");
							$(this).addClass("btn-info");
						} else if ($(this).html() == "今日") {
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
							work_date = null;
							$(this).removeClass("btn-white");
							$(this).addClass("btn-info");
							$(this).parent().children(":last").css("display",
									"block");
						}
						if ($("#timeselect").is(":hidden")) {
							select(work_date);
						}
					});
		});


function select(daynum) {
	var name = $("#name").val();
	if (work_date == null && !$("#timeselect").is(":hidden")) {
		starttime = $("#starttime").val();
		endtime = $("#endtime").val();
	}
    var data = {};
    data.daynum = daynum;
    data.starttime =  starttime;
    data.endttime = endtime;
    data.cellname = name;
    data.type=$("#type").val();
    
    commonRowDatas("table_list_1", data, alarmurl, "commonCallback", true);
}