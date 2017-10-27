/**
 * by dq 2017年9月13日上午10:46:07 TODO
 */
var Interval;
var date_value = 0;// 曲线时间范围
var work_date=30;//列表时间范围
var ajax_url = "";// 请求地址
var work_url="/sdas/fault/getworkprb";
var date_attr = "";// 属性
var dom_id = "";// dom_id
var line_color = "";// 颜色
var starttime="";
var endtime="";
var work_start="";
var work_end="";
$(function() {
	//曲线时间选择
	$(".datePicker").click(function() {
					$(this).parent().find(".btn-info").removeClass("btn-info");
					$(this).parent().find(".btn-white").removeClass("btn-white");
					$(this).parent().find("button").addClass("btn-white");
					$(".search").removeClass("btn-white");
					$(".search").addClass("btn-info");
					$(this).parent().children(":last").css("display", "none");
					starttime="";
					endtime=""
						if ($(this).html() == "今日") {
							date_value = 0;
							$(this).removeClass("btn-white");
							$(this).addClass("btn-info");
						} else if ($(this).html() == "昨天") {
							date_value = 1;
							$(this).removeClass("btn-white");
							$(this).addClass("btn-info");
						} else if ($(this).html() == "一周") {
							date_value = 7;
							$(this).removeClass("btn-white");
							$(this).addClass("btn-info");
						} else if ($(this).html() == "一月") {
							date_value = 30;
							$(this).removeClass("btn-white");
							$(this).addClass("btn-info");
						} else if ($(this).html() == "按时间选择") {
							date_value==null;
							$(this).removeClass("btn-white");
							$(this).addClass("btn-info");
							$(this).parent().children(":last").css("display", "block");
						}
						if(date_value!=null){
							if ($("#topTabs").find(".active").find("a").html().indexOf("PRB利用率") > -1) {
								var title = $("#tab").find(".active").find("a").html();
								if (title == "关联指标对比") {
									PRBCharts(date_value);
								} else {
									simpleCharts(ajax_url,date_value, date_attr, dom_id, title,
											line_color);
								}
							} else {
								var title = $("#tab_switch").find(".active").find("a").html();
								if (title == "关联指标对比") {
									switchCharts(date_value);
								} else {
									simpleCharts(ajax_url, date_value, date_attr,
											dom_id, title, line_color);
								}
							}
						}
					});
	//列表时间选择
	$(".workPicker").click(function() {
		$(this).parent().find(".btn-info").removeClass("btn-info");
		$(this).parent().find(".btn-white").removeClass("btn-white");
		$(this).parent().find("button").addClass("btn-white");
		$(".search").removeClass("btn-white");
		$(".search").addClass("btn-info");
		$(this).parent().children(":last").css("display", "none");
				work_start="";
				work_end=""
			if ($(this).html() == "今日") {
				work_date = 0;
				$(this).removeClass("btn-white");
				$(this).addClass("btn-info");
			} else if ($(this).html() == "昨天") {
				work_date = 1;
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
				work_date==null;
				$(this).removeClass("btn-white");
				$(this).addClass("btn-info");
				$(this).parent().children(":last").css("display", "block");
			}
			if(work_date!=null){
				if ($("#topTabs").find(".active").find("a").html().indexOf("PRB利用率") > -1) {
					work_data("#work_table","/sdas/fault/getworkprb",work_date,work_start,work_end);
				} else {
					work_data("#work_table_switch","/sdas/fault/getworkswitch",work_date,work_start,work_end);
				}
			}
		});
	
	$("#topTabs").find("li").click(function(){
		date_value = 0;
		starttime="";
		endtime="";
		//
		work_date = 30;
		work_start="";
		work_end="";
		var title = $(this).find("a").html();
		$(".btn-group").find(".btn-info").removeClass("btn-info");
		$(".btn-group").find(".btn-white").removeClass("btn-white");
		$(".btn-group").find("button").addClass("btn-white");
		$(".btn-group button:first-child").removeClass("btn-white");
		$(".btn-group button:first-child").addClass("btn-info");
		$(".btn-group div:last-child").css("display", "none");
		//
		if (title.indexOf("PRB利用率")>-1) {
			work_url="/sdas/fault/getworkprb";
			PRBCharts(date_value);
			work_data("#work_table","/sdas/fault/getworkprb",work_date,work_start,work_end);
		}else{
			work_url="/sdas/fault/getworkswitch";
			switchCharts(date_value);
			work_data("#work_table_switch","/sdas/fault/getworkswitch",work_date,work_start,work_end);
		}
	});
	$("#tab").find("li").click(function() {
				work_url="/sdas/fault/getworkprb";
				var title = $(this).find("a").html();
				if (title == "关联指标对比") {
					PRBCharts(date_value);
				} else {
					ajax_url = "/sdas/fault/getprbothers";
					if (title == "用户面最大激活UE数") {
						date_attr = "uecounts";
						dom_id = "#UE";
						line_color = "#2EC7C9";
					} else if (title == "PUCCH SR 资源使用量") {
						date_attr = "pucch";
						dom_id = "#PUCCH";
						line_color = "#385Ad3";
					} else if (title == "CCE聚合度为2的次数") {
						date_attr = "cce";
						dom_id = "#CCE";
						line_color = "#823B93";
					} else if (title == "小区载频PUSCH实际使用PRB个数") {
						date_attr = "puschprb";
						dom_id = "#PUSCH_PRB";
						line_color = "#27727B";
					} else if (title == "小区上行信道实际使用PRB个数") {
						date_attr = "uprealprb";
						dom_id = "#PRB_count";
						line_color = "#9BCA63";
					}
					simpleCharts(ajax_url, date_value, date_attr, dom_id,
							title, line_color);
				}
			});
	$("#tab_switch").find("li").click(
			function() {
				work_url="/sdas/fault/getworkswitch";
				var title = $(this).find("a").html();
				if (title == "关联指标对比") {
					switchCharts(date_value);
				} else {
					ajax_url = "/sdas/fault/getswitchothers";
					if (title == "YY-RRC连接建立成功率") {
						date_attr = "yyrrc";
						dom_id = "#YY-RRC_rate";
						line_color = "#9BCA63";
					} else if (title == "YY-无线接通率") {
						date_attr = "yywire";
						dom_id = "#yywire_rate";
						line_color = "#385Ad3";
					}
					simpleCharts(ajax_url,date_value, date_attr, dom_id,
							title, line_color);
				}
			});
	// 初始化数据
	PRBCharts(date_value);
	work_table("#work_table","#page_work_table");
	work_table("#work_table_switch","#page_work_table_switch",[]);
	work_data("#work_table","/sdas/fault/getworkprb",work_date,work_start,work_end);
});
//按日期查询按钮
function query(str){
	if(str.indexOf("switch")>-1){
		starttime=$("#starttime_switch").val();
		endtime=$("#endtime_switch").val();
	}else{
		starttime=$("#starttime_prb").val();
		endtime=$("#endtime_prb").val();
	}
	date_value=null;
	if (str.indexOf("prb")>-1) {
		var title = $("#tab").find(".active").find("a").html();
		if (title == "关联指标对比") {
			PRBCharts(date_value);
		} else {
			simpleCharts(ajax_url,date_value, date_attr, dom_id, title,
					line_color);
		}
	} else {
		var title = $("#tab_switch").find(".active").find("a").html();
		if (title == "关联指标对比") {
			switchCharts(date_value);
		} else {
			simpleCharts(ajax_url,date_value, date_attr,
					dom_id, title, line_color);
		}
	}
}
function query_work(str){
	if(str.indexOf("switch")>-1){
		work_start=$("#starttime_work_switch").val();
		work_end=$("#endtime_work_switch").val();
		work_data("#work_table_switch","/sdas/fault/getworkswitch",work_date,work_start,work_end);
	}else{
		work_start=$("#starttime_work").val();
		work_end=$("#endtime_work").val();
		work_data("#work_table","/sdas/fault/getworkprb",work_date,work_start,work_end);
	}
	work_date=null;
	
}
function PRBCharts(daynum) {
	$.ajax({
		url : "/sdas/fault/prb",
		type : "get",
		data : {
			"cellname":cellname,
			"daynum" : daynum,
			"starttime":starttime,
			"endtime":endtime
		},
		dataType : "json",
		async : false,
		success : function(data, status) {
			if (data.success) {
				var list = data.rows;
				var rrcdata = [];
				var upprbdata = [];
				var downprbdata = [];
				var times = [];
				var work_data=[];
				var markPointData_rrc=[];var markPointData_upprb=[];var markPointData_downprb=[];
				for (var i = 0; i < list.length; i++) {
					var item = list[i];
					// 时间格式化
					var timestamp = new Date(item.timestamp);
					var date = timestamp.toLocaleDateString().replace(/\//g,
							"-")
							+ " " + timestamp.toTimeString().substr(0, 8);
					times.push(date);
					rrcdata.push(parseInt(item.rrc));
					upprbdata.push(parseInt(item.upprb));
					downprbdata.push(parseInt(item.downprb));
					//
					var arr=[];
					arr.push(date);
					if(data.works!=null){
						$.each(data.works,function(j,e){
							if(e.timestamp==item.timestamp){
								arr.push(10);
								arr.push(e.monitor_value);
								arr.push(e.reasons);
								//mark
								var mark_obj_rrc={};var mark_obj_upprb={};var mark_obj_downprb={};
								mark_obj_rrc.name="性能工单"; mark_obj_upprb.name="性能工单"; mark_obj_downprb.name="性能工单";
								mark_obj_rrc.symbolSize=30;mark_obj_upprb.symbolSize=30;mark_obj_downprb.symbolSize=30;
								mark_obj_rrc.xAxis=date;  mark_obj_upprb.xAxis=date;   mark_obj_downprb.xAxis=date;
								mark_obj_rrc.yAxis=rrcdata[i]; mark_obj_upprb.yAxis=upprbdata[i]; mark_obj_downprb.yAxis=downprbdata[i];
								markPointData_rrc.push(mark_obj_rrc);markPointData_upprb.push(mark_obj_upprb);markPointData_downprb.push(mark_obj_downprb);
							}
						});
					}else{
						arr.push(0);
					}
					work_data.push(arr);
				}
				drawEcharts("#rrc", 'MR-RRC连接建立最大用户数', times, rrcdata,work_data,markPointData_rrc,'#CE0000');
	 			drawEcharts("#up_prb_rate", '集团-502上行PRB平均利用率', times,upprbdata,work_data,markPointData_upprb, '#8600FF');
	 			drawEcharts("#down_prb_rate", '集团-502下行PRB平均利用率', times,downprbdata,work_data,markPointData_downprb, '#9AFF02');
			}
		}
	});
	if (Interval != undefined) {
		clearInterval(Interval);
	}
	Interval = setInterval(function() {
		PRBCharts(date_value)
	}, 5 * 60 * 1000);
}
function switchCharts(daynum) {
	$.ajax({
		url : "/sdas/fault/switch",
		type : "get",
		data : {
			"cellname":cellname,
			"daynum" : daynum,
			"starttime":starttime,
			"endtime":endtime
		},
		dataType : "json",
		async : false,
		success : function(data, status) {
			if (data.success) {
				var list = data.rows;
				var yymon = [];
				var yysucces = [];
				var times = [];
				var work_data=[];
				var markPointData_yymon=[];var markPointData_yysucces=[];
				for (var i = 0; i < list.length; i++) {
					var item = list[i];
					// 时间格式化
					var timestamp = new Date(item.timestamp);
					var date = timestamp.toLocaleDateString().replace(/\//g,
							"-")
							+ " " + timestamp.toTimeString().substr(0, 8);
					times.push(date);
					yymon.push(parseInt(item.yymon));
					yysucces.push(item.yysucces.replace(/%/, ""));
					//
					var arr=[];
					arr.push(date);
					if(data.works!=null){
						$.each(data.works,function(j,e){
							if(e.timestamp==item.timestamp){
								arr.push(10);
								arr.push(e.monitor_value);
								arr.push(e.reasons);
								//mark
								var mark_obj_yymon={};var mark_obj_upprb={};
								mark_obj_yymon.name="性能工单"; mark_obj_yysucces.name="性能工单";
								mark_obj_yymon.symbolSize=30;mark_obj_yysucces.symbolSize=30;
								mark_obj_yymon.xAxis=date;  mark_obj_yysucces.xAxis=date;
								mark_obj_yymon.yAxis=rrcdata[i]; mark_obj_yysucces.yAxis=upprbdata[i];
								markPointData_yymon.push(mark_obj_yymon);markPointData_yysucces.push(mark_obj_yysucces);
							}
						});
					}else{
						arr.push(0);
					}
					work_data.push(arr);
				}
				drawEcharts("#switch_mon", 'YY-切换成功率分母', times, yymon,work_data,markPointData_yymon,'#CE0000');
	 			drawEcharts("#switch_success_rate", 'YY-切换成功率', times,yysucces,work_data, markPointData_yysucces,'#8600FF');
			}

		}
	});
	if (Interval != undefined) {
		clearInterval(Interval);
	}
	Interval = setInterval(function() {
		switchCharts(daynum)
	}, 5 * 60 * 1000);
}
function simpleCharts(url ,daynum, attr, id, title, color) {
	$.ajax({
		url : url,
		type : "get",
		data : {
			"cellname":cellname,
			"daynum" : daynum,
			"starttime":starttime,
			"endtime":endtime
		},
		dataType : "json",
		success : function(data, status) {
			if (data.success) {
				var list = data.rows;
				var line_data = [];
				var times = [];
				var work_data=[];
				var markPointData=[];
				for (var i = 0; i < list.length; i++) {
					var item = list[i];
					// 时间格式化
					var timestamp = new Date(item.timestamp);
					var date = timestamp.toLocaleDateString().replace(/\//g,
							"-")
							+ " " + timestamp.toTimeString().substr(0, 8);
					times.push(date);
					if (url.indexOf("switch") > -1) {
						line_data.push(item[attr].replace(/%/, ""));
					} else {
						line_data.push(item[attr]);
					}
					//
					var arr=[];
					arr.push(date);
					if(data.works!=null){
						$.each(data.works,function(j,e){
							if(e.timestamp==item.timestamp){
								arr.push(10);
								arr.push(e.monitor_value);
								arr.push(e.reasons);
								//mark
								var mark_obj={};
								mark_obj.name="性能工单";
								mark_obj.symbolSize=30;
								mark_obj.xAxis=date;
								mark_obj.yAxis=line_data[i];
								markPointData.push(mark_obj);
							}
						});
					}else{
						arr.push(0);
					}
					work_data.push(arr);
				}
				drawEcharts(id, title, times, line_data,work_data,markPointData, color);
				
			}

		}
	});
	if (Interval != undefined) {
		clearInterval(Interval);
	}
	Interval = setInterval(function() {
		simpleCharts(url, daynum, attr, id, title, color)
	}, 5 *60* 1000);
}
function drawEcharts(id, title, times, data,work_data,markPointData, color) {
	var dataZoom=100;
	if(data.length>200){
		dataZoom=30;
	}
	var mycharts = echarts.init($(id).get(0));
	var option = {
		legend : {
			left : 'top',
			data : [ title ,'性能工单']
		},
		tooltip : {
			trigger : 'axis', // 触发类型：坐标轴触发
			position: ['50%', '50%'],
			formatter : function(params) {
				if (params.length > 1) {
					var res = params[0].seriesName + ': ' + params[0].value
							+ '<br/>';
					if(params[1].value.length>1){
						res += params[1].seriesName + '<br/>发生时间: ' + params[1].value[0]
						+ '<br/>监控时值: '+ params[1].value[2]+'<br/>原因: '
						+ params[1].value[3];
					}else{
						res += '时间: ' + params[0].name;
					}
					return res;
				}
			}
		},
		dataZoom : {
			start : 0,
			end : dataZoom
		},
		xAxis : {
			type : 'category',
			data : times
		},
		yAxis : {
			scale : true,
			splitArea : {
				show : true
			}
		},
		series : [ {
			name : title,
			type : 'line',
			itemStyle : {
				normal : {
					color : color
				}
			},
			data : data,
			markPoint : {
                data :markPointData
            },
		},{
			name : '性能工单',
			data : work_data,
			type : 'scatter',
			symbolSize : function(data) {
				return data[1]*2.5;
			},
			label : {
				emphasis : {
					show : true,
					formatter : function(param) {
						return '性能工单';
					},
					position : 'top'
				}
			},
			itemStyle : {
				normal : {
					shadowBlur : 10,
					shadowColor : 'rgba(255,99,71, 0.73)',
					shadowOffsetY : 5,
					color : new echarts.graphic.RadialGradient(0.4, 0.3, 1,
							[{
										offset : 0,
										color : 'rgb(255,99,71)'
									}, {
										offset : 1,
										color : 'rgb(255,99,71)'
									}])
				}
			}
		} ]
	};
	mycharts.setOption(option);
	mycharts.resize();
	/*
	 * window.onresize = function(){ myChart.resize(); });
	 */
	// mycharts.resize();
}
//
function work_data(id,url,date_value,starttime,endtime){
	$.ajax({
 		url : url,
 		data : {
			"cellname":cellname,
			"daynum" : date_value,
			"starttime":starttime,
			"endtime":endtime
		},
 		type : "get",
 		dataType : "json",
 		success : function(data, status) {
 			var list = data.rows;
 			$(id).bootstrapTable('load',list);
 		}
 	});
}
function work_table(id,pageid,list) {
	$(id).bootstrapTable({
	    data:list,
	    dataType: "json",
	    toolbar: pageid,      //工具按钮用哪个容器
	    striped: true,                      //是否显示行间隔色
	    singleSelect: false,
	    pagination: true, //分页
	    pageNumber:1,                       //初始化加载第一页，默认第一页
	    pageSize: 5,                       //每页的记录行数（*）
	    pageList: [5,10, 25],        //可供选择的每页的行数（*）
	    search: false, //显示搜索框
	          columns: [{
	              field: 'timestamp',
	              title: '发生时间',
	              width:500,
	              formatter:function(value,row,index){
	                  var jsDate = new Date(value);
	                  var UnixTimeToDate = jsDate.getFullYear() + '/' + (jsDate.getMonth() + 1) + '/'+jsDate.getDate()+ ' ' + jsDate.getHours() + ':' + jsDate.getMinutes() + ':' + jsDate.getSeconds();
	                   return UnixTimeToDate;
	                 }
	          },{
	              field: 'monitor_value',
	              title: '监控时值',
	              width:500
	          },
	          {
	              field: 'reasons',
	              title: '原因',
	              width:500
	          }]
	      });
	//
}
