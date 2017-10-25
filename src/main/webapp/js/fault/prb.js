/**
 * by dq 2017年9月13日上午10:46:07 TODO
 */
var Interval;
var date_value = 0;// 时间范围
var ajax_url = "";// 请求地址
var date_attr = "";// 属性
var dom_id = "";// dom_id
var line_color = "";// 颜色
var starttime="";
var endtime="";
$(function() {
	
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
						} else if ($(this).html() == "最近7日") {
							date_value = 7;
							$(this).removeClass("btn-white");
							$(this).addClass("btn-info");
						} else if ($(this).html() == "最近30日") {
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
									simpleCharts(ajax_url, date_value, date_attr, dom_id, title,
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
	$("#topTabs").find("li").click(function(){
		date_value = 0;
		starttime="";
		endtime="";
		var title = $(this).find("a").html();
		$(".datePicker").find(".btn-info").removeClass("btn-info");
		$(".datePicker").find(".btn-white").removeClass("btn-white");
		$(".datePicker").find("button").addClass("btn-white");
		$(".datePicker").children(":first").removeClass("btn-white");
		$(".datePicker").children(":first").addClass("btn-info");
		$(".datePicker").parent().children(":last").css("display", "none");
		if (title.indexOf("PRB利用率")>-1) {
			PRBCharts(date_value);
		}else{
			switchCharts(date_value);
		}
	});
	$("#tab").find("li").click(function() {
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
					simpleCharts(ajax_url, date_value, date_attr, dom_id,
							title, line_color);
				}
			});
	// 初始化数据
	PRBCharts(date_value);
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
			simpleCharts(ajax_url, date_value, date_attr, dom_id, title,
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
function PRBCharts(daynum) {
	$.ajax({
		url : "/sdas/fault/prb",
		type : "get",
		data : {
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
				}
				drawEcharts("#rrc", 'MR-RRC连接建立最大用户数', times, rrcdata,
						'#CE0000');
				drawEcharts("#up_prb_rate", '集团-502上行PRB平均利用率', times,
						upprbdata, '#8600FF');
				drawEcharts("#down_prb_rate", '集团-502下行PRB平均利用率', times,
						downprbdata, '#9AFF02');
			}

		}
	});
	if (Interval != undefined) {
		clearInterval(Interval);
	}
	Interval = setInterval(function() {
		PRBCharts(daynum)
	}, 5 * 60 * 1000);
}
function switchCharts(daynum) {
	$.ajax({
		url : "/sdas/fault/switch",
		type : "get",
		data : {
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
				}
				drawEcharts("#switch_mon", 'YY-切换成功率分母', times, yymon,
						'#CE0000');
				drawEcharts("#switch_success_rate", 'YY-切换成功率', times,
						yysucces, '#8600FF');
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
function simpleCharts(url, daynum, attr, id, title, color) {
	$.ajax({
		url : url,
		type : "get",
		data : {
			"daynum" : daynum,
			"starttime":starttime,
			"endtime":endtime
		},
		dataType : "json",
		success : function(data, status) {
			if (data.success) {
				var list = data.rows;
				var data = [];
				var times = [];
				for (var i = 0; i < list.length; i++) {
					var item = list[i];
					// 时间格式化
					var timestamp = new Date(item.timestamp);
					var date = timestamp.toLocaleDateString().replace(/\//g,
							"-")
							+ " " + timestamp.toTimeString().substr(0, 8);
					times.push(date);
					if (url.indexOf("switch") > -1) {
						data.push(item[attr].replace(/%/, ""));
					} else {
						data.push(item[attr]);
					}

				}
				drawEcharts(id, title, times, data, color);
			}

		}
	});
	if (Interval != undefined) {
		clearInterval(Interval);
	}
	Interval = setInterval(function() {
		simpleCharts(url, daynum, attr, id, title, color)
	}, 5 * 1000);
}
function drawEcharts(id, title, times, data, color) {
	var dataZoom=100;
	if(data.length>10000){
		dataZoom=2;
	}else if(data.length>2000){
		dataZoom=5;
	}
	var mycharts = echarts.init($(id).get(0));
	var option = {
		legend : {
			left : 'left',
			data : [ title ]
		},
		tooltip : {
			trigger : 'axis',
			formatter : function(params) {
				var str = params[0].name + "</br>" + params[0].seriesName
						+ " : ";
				if (id.indexOf("rate") > -1) {
					return str + params[0].value + "%";
				} else {
					return str + params[0].value;
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
			type : 'value',
		},
		series : [ {
			name : title,
			type : 'line',
			itemStyle : {
				normal : {
					color : color
				}
			},
			data : data
		} ]
	};
	mycharts.setOption(option);
	mycharts.resize();
	/*
	 * window.onresize = function(){ myChart.resize(); });
	 */
	// mycharts.resize();
}
