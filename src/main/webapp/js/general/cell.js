/**
 * by dq 2017年9月14日下午4:04:56 TODO
 */
$.jgrid.defaults.styleUI = 'Bootstrap';
var index = ctx + "/cell/index";
var weighturl = ctx + "/cell/weight";
var healthtrendurl = ctx + "/cell/healthtrend";
var alarm_url = ctx + "/cell/alarm_healthtrend";
var name1 = '历史分析';
var name2 = '实时数据';
var nullchart = [];
for (var i = 0; i < 24; i++) {
	nullchart.push(i);
}
var upColor = '#68C5CC';
var upBorderColor = '#19B7CF';
var downColor = '#68C5CC';
var downBorderColor = '#19B7CF';
var color = ['rgba(104, 197, 204, 0.73)', 'rgba(51,51,204, 0.23)',
		'rgba(171, 226, 98, 0.62)'];
var borderColor = ['rgb(25, 183, 207)', 'rgb(51,51,204,)', 'rgb(163, 222, 84)'];
var echart_option = {
	tooltip : {
		trigger : 'axis',
		axisPointer : {
			type : 'cross'
		},
		formatter : function(params) {
			var res = params[0].seriesName + ' ' + params[0].name + ': '
					+ params[0].value;
			for (var i = 1; i < params.length; i++) {

				res += '<br/>' + params[i].seriesName + '  前值 : '
						+ params[i].value[1] + '<br/>' + params[i].seriesName
						+ '  后值 : ' + params[i].value[2];
				res += '<br/>' + params[i].seriesName + '  最小 : '
						+ params[i].value[3] + '<br/>' + params[i].seriesName
						+ '  最大 : ' + params[i].value[4];

			}
			return res;
		}
	},
	legend : {
		data : [name1, name2]
	},
	grid : {
		left : '10%',
		right : '10%',
		bottom : '15%'
	},
	xAxis : {
		type : 'category',
		scale : true,
		boundaryGap : false,
		axisLine : {
			onZero : false
		},
		splitLine : {
			show : false
		},
		data : nullchart,
		axisLabel : {
			formatter : function(value) {
				return value;
			}
		}
	},
	yAxis : {
		scale : true,
		splitArea : {
			show : true
		}
	},
	dataZoom : [{
				type : 'inside',
				start : 0,
				end : 100
			}, {
				show : true,
				type : 'slider',
				y : '90%',
				start : 50,
				end : 100
			}],
	series : []
};
var serie = {
	name : name1,
	type : 'candlestick',
	data : {},
	itemStyle : {
		normal : {
			color : upColor,
			color0 : downColor,
			borderColor : upBorderColor,
			borderColor0 : downBorderColor
		}
	},
	markLine : {
		symbol : ['none', 'none'],
		data : [[{
					name : 'from lowest to highest',
					type : 'min',
					valueDim : 'lowest',
					symbol : 'circle',
					symbolSize : 10,
					label : {
						normal : {
							show : false
						},
						emphasis : {
							show : false
						}
					}
				}, {
					type : 'max',
					valueDim : 'highest',
					symbol : 'circle',
					symbolSize : 10,
					label : {
						normal : {
							show : false
						},
						emphasis : {
							show : false
						}
					}
				}]]
	}
}
var line = {
	name : name2,
	type : 'line',
	data : {},
	smooth : true,
	lineStyle : {
		normal : {
			opacity : 0.2
		}
	}
}
var iscapacitywork = false;
var isdevicework = false;
var isoutservework = false;
var isindexinfo = false;
var isweek = true
var top_split = [];
var bottom_spli = [];
var middle_split = [];
for (var i = 0; i < 1000; i++) {
	var b_arr = [];
	b_arr.push(i);
	b_arr.push(25);
	bottom_spli.push(b_arr);
	var s_arr = [];
	s_arr.push(i);
	s_arr.push(55);
	middle_split.push(s_arr);
	var t_arr = [];
	t_arr.push(i);
	t_arr.push(20);
	top_split.push(t_arr);
}
var histroy_trend = {
	tooltip : { // 提示框
		trigger : 'axis', // 触发类型：坐标轴触发
		axisPointer : { // 坐标轴指示器配置项
			type : 'cross' // 指示器类型，十字准星
		},
		formatter : function(params) {
			if (params.length > 3) {
				var res = params[0].seriesName + ': ' + (params[0].value[1])
						+ '<br/>';
				res += params[1].seriesName + '数量 : ' + (params[1].value[2])
						+ '<br/>';
				return res;
			}

		}
	},
	xAxis : {
		type : 'category',
		scale : true,
		data : []
	},
	yAxis : {
		splitLine : {
			show : false
		},
		max : 100
	},
	legend : {
		data : [{
					'name' : "历史健康度"
				}, {
					'name' : "投诉"
				},{
                    'name' : "警戒区"
                }/*,{
                    'name' : "观察区"
                }*/]
	},
	dataZoom : [{
				type : 'slider',
				startValue : 0,
				endValue : 60
			}],
	series : [{
				name : '历史健康度',
				type : 'line',
				data : [],
				/*markLine : {
					silent : true,
					data : [{
								yAxis : 60
							}]
				},*/
				label : {
					emphasis : {
						show : true,
						formatter : function(param) {
							return "健康度";
						},
						position : 'top'
					}
				},
				itemStyle : {
					normal : {
						color : 'rgb(46,199,201)',
						lineStyle : {
							color : 'rgb(46,199,201)'
						}
					}
				}
			}, {
				name : '投诉',
				data : [],
				type : 'scatter',
				symbolSize : function(data) {
					return data[2] * 12;
				},
				label : {
					emphasis : {
						show : true,
						formatter : function(param) {
							return "投诉";
						},
						position : 'top'
					}
				},
				itemStyle : {
					normal : {
						shadowBlur : 10,
						shadowColor : 'rgba(166,136,224, 0.2)',
						shadowOffsetY : 5,
						color : new echarts.graphic.RadialGradient(0.4, 0.2, 1,
								[{
											offset : 0,
											color : 'rgb(166,136,224)'
										}, {
											offset : 1,
											color : 'rgb(166,136,224)'
										}])
					}
				}
			},{
                name : '警戒区',
                data : [],
                type : 'scatter',
                symbolSize : function(data) {
                    return data[3] * 20;
                },
                label : {
                    emphasis : {
                        show : true,
                        formatter : function(param) {
                            return "警戒区";
                        },
                        position : 'top'
                    }
                },
                itemStyle : {
                    normal : {
                        shadowBlur : 10,
                        shadowColor : 'rgba(216,122,128, 0.2)',
                        shadowOffsetY : 5,
                        color : new echarts.graphic.RadialGradient(0.4, 0.2, 1,
                                [{
                                            offset : 0,
                                            color : 'rgb(216,122,128)'
                                        }, {
                                            offset : 1,
                                            color : 'rgb(216,122,128)'
                                        }])
                    }
                }
            },/*{
                name : '观察区',
                data : [],
                type : 'scatter',
                symbolSize : function(data) {
                    return data[4] * 20;
                },
                label : {
                    emphasis : {
                        show : true,
                        formatter : function(param) {
                            return "观察区";
                        },
                        position : 'top'
                    }
                },
                itemStyle : {
                    normal : {
                        shadowBlur : 10,
                        shadowColor : 'rgba(248,172,89, 0.2)',
                        shadowOffsetY : 5,
                        color : new echarts.graphic.RadialGradient(0.4, 0.2, 1,
                                [{
                                            offset : 0,
                                            color : 'rgb(248,172,89)'
                                        }, {
                                            offset : 1,
                                            color : 'rgb(248,172,89)'
                                        }])
                    }
                }
            }, */{
				name : '',
				type : 'line',
				smooth : true,
				symbol : "none",
				stack : true,
				itemStyle : {
					normal : {
						opacity : 0.2,
						color : 'rgba(231,133,131,0.2)',
						lineStyle : {
							opacity : 0.2,
							color : 'rgba(231,133,131,0.2)'
						},
						areaStyle : {
							type : 'default'
						}
					}
				},
				data : bottom_spli
			}, {
				name : '',
				type : 'line',
				smooth : true,
				symbol : "none",
				stack : true,
				itemStyle : {
					normal : {
						opacity : 0.2,
						color : 'rgba(231,233,131,0.2)',
						lineStyle : {
							opacity : 0.2,
							color : 'rgba(231,233,131,0.2)'
						},
						areaStyle : {
							type : 'default'
						}
					}
				},
				data : middle_split
			}, {
				name : '',
				type : 'line',
				smooth : true,
				symbol : "none",
				stack : true,
				itemStyle : {
					normal : {
						opacity : 0.2,
						color : 'rgba(172,231,131,0.2)',
						lineStyle : {
							opacity : 0.2,
							color : 'rgba(172,231,131,0.2)'
						},
						areaStyle : {
							type : 'default'
						}
					}
				},
				data : top_split
			}]
}
function oneweek() {
	$("#trendinweek").addClass("btn-info");
	$("#trendinweek").removeClass("btn-white");
	$("#trendinmonth").addClass("btn-white");
	$("#trendinmonth").removeClass("btn-info");
	$("#trendinselect").removeClass("btn-info");
	$("#trendinselect").addClass("btn-white");
	$("#timeselect").css("display", "none");
	historyTrendQuery();
}
function onemonth() {
	$("#trendinmonth").addClass("btn-info");
	$("#trendinmonth").removeClass("btn-white");
	$("#trendinweek").removeClass("btn-info");
	$("#trendinweek").addClass("btn-white");
	$("#trendinselect").removeClass("btn-info");
	$("#trendinselect").addClass("btn-white");
	$("#timeselect").css("display", "none");
	historyTrendQuery("month");
}
var top_split2 = [];
var bottom_spli2 = [];
var middle_split2 = [];
for (var i = 0; i < 24; i++) {
	var b_arr = [];
	b_arr.push(i);
	b_arr.push(25);
	bottom_spli2.push(b_arr);
	var s_arr = [];
	s_arr.push(i);
	s_arr.push(55);
	middle_split2.push(s_arr);
	var t_arr = [];
	t_arr.push(i);
	t_arr.push(20);
	top_split2.push(t_arr);
}
var rt_health = {
	tooltip : { // 提示框
		trigger : 'axis', // 触发类型：坐标轴触发
		axisPointer : { // 坐标轴指示器配置项
			type : 'cross' // 指示器类型，十字准星
		},
		formatter : function(params) {
			if (params.length > 3) {
				var res = params[0].seriesName + ': ' + (params[0].value[1])
						+ '<br/>';
				return res;
			}

		}
	},
	xAxis : {
		type : 'category',
		data : []
	},
	yAxis : {
		splitLine : {
			show : false
		},
		max : 100
	},
	legend : {
		data : [{
					'name' : "实时健康度"
				}]
	},
	dataZoom : [{
				type : 'slider',
				startValue : 0,
				endValue : 23
			}],
	series : [{
				name : '实时健康度',
				type : 'line',
				data : [],
				/*markLine : {
					silent : true,
					data : [{
								yAxis : 60
							}]
				},*/
				label : {
					emphasis : {
						show : true,
						formatter : function(param) {
							return "健康度";
						},
						position : 'top'
					}
				},
				itemStyle : {
					normal : {
						color : 'rgb(46,199,201)',
						lineStyle : {
							color : 'rgb(46,199,201)'
						}
					}
				}
			}, {
				name : '',
				type : 'line',
				smooth : true,
				symbol : "none",
				stack : true,
				itemStyle : {
					normal : {
						opacity : 0.2,
						color : 'rgba(231,133,131,0.2)',
						lineStyle : {
							opacity : 0.2,
							color : 'rgba(231,133,131,0.2)'
						},
						areaStyle : {
							type : 'default'
						}
					}
				},
				data : bottom_spli
			}, {
				name : '',
				type : 'line',
				smooth : true,
				symbol : "none",
				stack : true,
				itemStyle : {
					normal : {
						opacity : 0.2,
						color : 'rgba(231,233,131,0.2)',
						lineStyle : {
							opacity : 0.2,
							color : 'rgba(231,233,131,0.2)'
						},
						areaStyle : {
							type : 'default'
						}
					}
				},
				data : middle_split
			}, {
				name : '',
				type : 'line',
				smooth : true,
				symbol : "none",
				stack : true,
				itemStyle : {
					normal : {
						opacity : 0.2,
						color : 'rgba(172,231,131,0.2)',
						lineStyle : {
							opacity : 0.2,
							color : 'rgba(172,231,131,0.2)'
						},
						areaStyle : {
							type : 'default'
						}
					}
				},
				data : top_split2
			}]
}
function timeselect() {
	$("#trendinselect").addClass("btn-info");
	$("#trendinselect").removeClass("btn-white");
	$("#timeselect").css("display", "inline");
	$("#trendinmonth").addClass("btn-white");
	$("#trendinmonth").removeClass("btn-info");
	$("#trendinweek").removeClass("btn-info");
	$("#trendinweek").addClass("btn-white");
}
function workoneweek() {
	$("#workinweek").addClass("btn-info");
	$("#workinweek").removeClass("btn-white");
	$("#workinmonth").addClass("btn-white");
	$("#workinmonth").removeClass("btn-info");
	$("#workinselect").removeClass("btn-info");
	$("#workinselect").addClass("btn-white");
	$("#worktimeselect").css("display", "none");
	workQuery("week");
}
function workonemonth() {
	$("#workinmonth").addClass("btn-info");
	$("#workinmonth").removeClass("btn-white");
	$("#workinweek").removeClass("btn-info");
	$("#workinweek").addClass("btn-white");
	$("#workinselect").removeClass("btn-info");
	$("#workinselect").addClass("btn-white");
	$("#worktimeselect").css("display", "none");
	workQuery("month");
}
function worktimeselect() {
	$("#workinselect").addClass("btn-info");
	$("#workinselect").removeClass("btn-white");
	$("#worktimeselect").css("display", "inline");
	$("#workinmonth").addClass("btn-white");
	$("#workinmonth").removeClass("btn-info");
	$("#workinweek").removeClass("btn-info");
	$("#workinweek").addClass("btn-white");
}
function historyTrendQuery(type, start, end) {
	$.ajax({
				url : healthtrendurl,
				data : {
					'cellname' : cellname,
					'start' : start,
					'end' : end,
					'type' : type
				},
				type : "POST",
				dataType : "json",
				success : function(data, status) {
					var list = data.rows;
					var axis = [];
					var data2 = [];
					for (var z = 0; z < list.length; z++) {
						var timer = list[z].time;
						var ratio = list[z].ratio;
						//var perworks = list[z].perworks;
						//var deviceworks = list[z].deviceworks;
						//var osworks = list[z].osworks;
						var complaints = list[z].complaints;
                        var fault = list[z].result_fault;
                        var warn = list[z].result_warnning;
						var temp = []
						axis.push(timer);
						temp.push(timer);
						temp.push(ratio);
						temp.push(complaints);
                        temp.push(fault);
                        temp.push(warn);
						data2.push(temp);
					}
					histroy_trend.xAxis.data = axis;
					histroy_trend.series[0].data = data2;
					histroy_trend.series[1].data = data2;
                    histroy_trend.series[2].data = data2;
                    //histroy_trend.series[3].data = data2;
					ratiotrend.setOption(histroy_trend);
				}
			});
}
function query() {
	var start = $("#starttime").val();
	var end = $("#endtime").val();
	historyTrendQuery("select", start, end);
}
function query2() {
	var start = $("#start").val();
	var end = $("#end").val();
	workQuery("select", start, end);
}
function workQuery(type, start, end) {
	if (iscapacitywork) {
		if ("week" == type) {
			$.ajax({
						url : capacityweekurl,
						data : {
							'cellname' : cellname
						},
						type : "POST",
						dataType : "json",
						success : function(data, status) {
							var list = data.rows;
							$("#table_list_work").jqGrid('clearGridData');
							$("#table_list_work").jqGrid('setGridParam', {
										datatype : 'local',
										data : list,
										page : 1
									}).trigger("reloadGrid");
						}
					});
		} else if ("month" == type) {
			$.ajax({
						url : capacitymonthurl,
						data : {
							'cellname' : cellname
						},
						type : "POST",
						dataType : "json",
						success : function(data, status) {
							var list = data.rows;
							$("#table_list_work").jqGrid('clearGridData');
							$("#table_list_work").jqGrid('setGridParam', {
										datatype : 'local',
										data : list,
										page : 1
									}).trigger("reloadGrid");
						}
					});
		} else {
			$.ajax({
						url : '/sdas/capacitywork/gettable',
						data : {
							'cellname' : cellname,
							'starttime' : start,
							'endtime' : end
						},
						type : "POST",
						dataType : "json",
						success : function(data, status) {
							var list = data.rows;
							$("#table_list_work").jqGrid('clearGridData');
							$("#table_list_work").jqGrid('setGridParam', {
										datatype : 'local',
										data : list,
										page : 1
									}).trigger("reloadGrid");
						}
					});
		}
	} else if (isdevicework) {
		if ("week" == type) {
			$.ajax({
						url : deviceweekurl,
						data : {
							'cellname' : cellname
						},
						type : "POST",
						dataType : "json",
						success : function(data, status) {
							var list = data.rows;
							$("#table_list_work2").jqGrid('clearGridData');
							$("#table_list_work2").jqGrid('setGridParam', {
										datatype : 'local',
										data : list,
										page : 1
									}).trigger("reloadGrid");
						}
					});
		} else if ("month" == type) {
			$.ajax({
						url : devicemonthurl,
						data : {
							'cellname' : cellname
						},
						type : "POST",
						dataType : "json",
						success : function(data, status) {
							var list = data.rows;
							$("#table_list_work2").jqGrid('clearGridData');
							$("#table_list_work2").jqGrid('setGridParam', {
										datatype : 'local',
										data : list,
										page : 1
									}).trigger("reloadGrid");
						}
					});
		} else {
			$.ajax({
						url : deviceurl,
						data : {
							'cellname' : cellname,
							'starttime' : start,
							'endtime' : end
						},
						type : "POST",
						dataType : "json",
						success : function(data, status) {
							var list = data.rows;
							$("#table_list_work2").jqGrid('clearGridData');
							$("#table_list_work2").jqGrid('setGridParam', {
										datatype : 'local',
										data : list,
										page : 1
									}).trigger("reloadGrid");
						}
					});
		}
	} else if (isoutservework) {
		if ("week" == type) {
			$.ajax({
						url : outserviceweek,
						data : {
							'cellname' : cellname
						},
						type : "POST",
						dataType : "json",
						success : function(data, status) {
							var list = data.rows;
							$("#table_list_work3").jqGrid('clearGridData');
							$("#table_list_work3").jqGrid('setGridParam', {
										datatype : 'local',
										data : list,
										page : 1
									}).trigger("reloadGrid");
						}
					});
		} else if ("month" == type) {
			$.ajax({
						url : outservicemonth,
						data : {
							'cellname' : cellname
						},
						type : "POST",
						dataType : "json",
						success : function(data, status) {
							var list = data.rows;
							$("#table_list_work3").jqGrid('clearGridData');
							$("#table_list_work3").jqGrid('setGridParam', {
										datatype : 'local',
										data : list,
										page : 1
									}).trigger("reloadGrid");
						}
					});
		} else {
			$.ajax({
						url : outservicemonth,
						data : {
							'cellname' : cellname,
							'starttime' : start,
							'endtime' : end
						},
						type : "POST",
						dataType : "json",
						success : function(data, status) {
							var list = data.rows;
							$("#table_list_work3").jqGrid('clearGridData');
							$("#table_list_work3").jqGrid('setGridParam', {
										datatype : 'local',
										data : list,
										page : 1
									}).trigger("reloadGrid");
						}
					});
		}
	}
}
$(function() {
	/*
	 * 投诉工单
	 */
	$.ajax({
				url : complainturl,
				data : {
					'cellname' : cellname
				},
				type : "POST",
				dataType : "json",
				success : function(data, status) {
					var list = data.rows;
					refreshJqGrid(list);

				}
			});
	/*
	 * 性能工单
	 */
	$.ajax({
				url : capacityweekurl,
				data : {
					'cellname' : cellname
				},
				type : "POST",
				dataType : "json",
				success : function(data, status) {
					var list = data.rows;
					refreshJqGrid_capacity(list);
					iscapacitywork = true;
				}
			});
	/*
	 * 指标权重
	 */
	/*$.ajax({
				url : weighturl,
				data : {
					'cellname' : cellname
				},
				type : "POST",
				dataType : "json",
				success : function(data, status) {
					var list = data.rows;
					refreshJqGrid_weight(list);
				}
			});*/
    /*
     * 历史表格
     */
    $.ajax({
                url : "/sdas/cell/healthtable",
                data : {
                    'cellname' : cellname
                },
                type : "POST",
                dataType : "json",
                success : function(data, status) {
                    var list = data.rows;
                    refresh_healthtable(list);
                }
     });
	/*
	 * K线图
	 */
	$.ajax({
		url : "/sdas/cell/belonggroup",
		data : {
			'cellname' : cellname
		},
		type : "POST",
		dataType : "json",
		success : function(data, status) {
			var group = data.group;
			$.ajax({
				url : "/sdas/cell/groupindexs",
				type : "post",
				dataType : "json",
				data : {
					'type' : group
				},
				success : function(data, status) {
					var list = data.rows;
					$("#group_index").children().remove();
					for (var i = 0; i < list.length; i++) {
						var item = list[i];
						var type = item.cell_code;
						var index = item.indeicator_code;
						var option;
                        var name = cellname;
						if (i == 0) {
							option = $('<li onclick=cellindex("'
									+ name
									+ '","'
									+ index
									+ '") class="active"><a data-toggle="tab" aria-expanded="true">'
									+ item.indeicator_name + '</a></li>');
						} else {
							option = $('<li onclick=cellindex("'
									+ name
									+ '","'
									+ index
									+ '") class=""><a data-toggle="tab" aria-expanded="false">'
									+ item.indeicator_name + '</a></li>');
						}

						$("#group_index").append(option);
					}
					$("li.active").trigger("click");
				}
			});
		}
	});
	/*
	 * 历史曲线
	 */
	$.ajax({
				url : healthtrendurl,
				data : {
					'cellname' : cellname
				},
				type : "POST",
				dataType : "json",
				success : function(data, status) {
					var list = data.rows;
					var axis = [];
					var data2 = [];
                    for (var z = 0; z < list.length; z++) {
                        var timer = list[z].time;
                        var ratio = list[z].ratio;
                        //var perworks = list[z].perworks;
                        //var deviceworks = list[z].deviceworks;
                        //var osworks = list[z].osworks;
                        var complaints = list[z].complaints;
                        var fault = list[z].result_fault;
                        var warn = list[z].result_warnning;
                        var temp = []
                        axis.push(timer);
                        temp.push(timer);
                        temp.push(ratio);
                        temp.push(complaints);
                        temp.push(fault);
                        temp.push(warn);
                        data2.push(temp);
                    }
                    histroy_trend.xAxis.data = axis;
                    histroy_trend.series[0].data = data2;
                    histroy_trend.series[1].data = data2;
                    histroy_trend.series[2].data = data2;
                    //histroy_trend.series[3].data = data2;
					ratiotrend.setOption(histroy_trend);
				}
			});
	/**
	 * 实时健康度
	 */
	$.ajax({
				url : "/sdas/cell/rthealth",
				data : {
					'cellname' : cellname
				},
				type : "POST",
				dataType : "json",
				success : function(data, status) {
					var list = data.rows;
					var axis = [];
					var data2 = [];
					for (var z = 0; z < list.length; z++) {
						var timer = list[z].time;
						var ratio = list[z].ratio;
						var temp = []
						axis.push(timer);
						temp.push(timer);
						temp.push(ratio);
						data2.push(temp);
					}
					rt_health.xAxis.data = axis;
					rt_health.series[0].data = data2;
					rtratio.setOption(rt_health);
				}
			});
	////////////
	/*
	 * 异常预警
	 */
	$.ajax({
				url : alarm_url,
				data : {
					'cellname' : cellname
				},
				type : "POST",
				dataType : "json",
				success : function(data, status) {
					var list = data.rows;
					alarmJqGrid(list);
					setInterval((function() {
								refreshAlarm()
							}), 5 * 60 * 1000);
				}
			});
	//实时简况度
	rtRatio();
});
function refreshAlarm() {
	console.info("refresh");
	$.ajax({
				url : alarm_url,
				data : {
					'cellname' : cellname
				},
				type : "POST",
				dataType : "json",
				success : function(data, status) {
					var list = data.rows;
					alarmJqGrid(list);
				}
			});
}
function cellindex(cellcode, indexcode) {
	$.ajax({
		url : "/sdas/cell/index",
		type : "post",
		dataType : "json",
		data : {
			'cellname' : cellcode,
			'index' : indexcode
		},
		success : function(data, status) {
			if (data.success) {
				var scatter = data.rows;
				var temp0 = splitData(scatter[0]);
				var rtdata = calculateRT(temp0.values);
				line.data = rtdata;
				echart_option.series[0] = line;
				var k = scatter.length;
				for (var i = 0; i < k; i++) {
					var serie1 = {
						name : name1,
						type : 'candlestick',
						data : [],
						itemStyle : {
							normal : {
								color : upColor,
								color0 : downColor,
								borderColor : upBorderColor,
								borderColor0 : downBorderColor
							}
						},
						markLine : {
							symbol : ['none', 'none'],
							data : [{
										name : 'from lowest to highest',
										type : 'min',
										valueDim : 'lowest',
										symbol : 'circle',
										symbolSize : 10,
										label : {
											normal : {
												show : false
											},
											emphasis : {
												show : false
											}
										}
									}, {
										type : 'max',
										valueDim : 'highest',
										symbol : 'circle',
										symbolSize : 10,
										label : {
											normal : {
												show : false
											},
											emphasis : {
												show : false
											}
										}
									}]
						}
					}
					if (i == 0) {
						var name = "簇心" + i;
						serie1.name = name;
						serie1.data = temp0.values;
						serie1.itemStyle.normal.color = color[i];
						serie1.itemStyle.normal.color0 = color[i];
						serie1.itemStyle.normal.borderColor = borderColor[i];
						serie1.itemStyle.normal.borderColor0 = borderColor[i];
					} else {
						var temp = splitData(scatter[i]);
						var name = "簇心" + i;
						serie1.name = name;
						serie1.data = temp.values;
						if (i < 3) {
							serie1.itemStyle.normal.color = color[i];
							serie1.itemStyle.normal.color0 = color[i];
							serie1.itemStyle.normal.borderColor = borderColor[i];
							serie1.itemStyle.normal.borderColor0 = borderColor[i];
						}
					}

					echart_option.series[i + 1] = serie1;
				}
				chart_mb.setOption(echart_option);
			} else {
				echart_option.series.push(line);
				echart_option.series.push(serie);
				chart_mb.setOption(echart_option);
			}
		}
	});
}

function refreshJqGrid(list) {
	$("#table_list_complain").jqGrid({
		data : list,
		datatype : "local",
		height : "auto",
		autowidth : true,
		shrinkToFit : true,
		rowNum : 10,
		rowList : [10, 20, 30],
		colNames : ['受理时间', '受理号码','服务请求类别' ,'问题细项', '常住小区1', '常住小区2', '常住小区3'],
		colModel : [{
					name : 'record_time',
					index : 'record_time',
					width : 40,
					formatter : function(cellvalue, options, rowObject) {
						return $.hd_jqGrid.dateTimeFormatter(cellvalue);
					}
				}, {
					name : 'phone_number',
					index : 'phone_number',
					width : 40
				}, {
                    name : 'servicerequesttype',
                    index : 'servicerequesttype',
                    width : 100
                },{
					name : 'complaint_detailinfo',
					index : 'complaint_detailinfo',
					width : 60
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
		pager : "#pager_list_complain",
		viewrecords : true,
		hidegrid : false,
		gridComplete : function() {
			// 获取某列的每一行id
			var ids = jQuery("#table_list_complain").jqGrid("getDataIDs");
			for (var i = 0; i < ids.length; i++) {
				var id = ids[i];
                var link = "/sdas/general/cellhome/";
				var live_cellname1 = $("#table_list_complain").getCell(id,
						'live_cellname1');
                if(live_cellname1.indexOf("<a")<0){
                    var url1 = '<a href=javascript:gotocellhome("' +link +'","' + live_cellname1
                        + '")>' + live_cellname1 + '</a>';
                    $("#table_list_complain").jqGrid('setRowData', id, {
                            live_cellname1 : url1});
                }
				var live_cellname2 = $("#table_list_complain").getCell(id,
						'live_cellname2');
                if(live_cellname2.indexOf("<a")<0){
                    var url2 = '<a href=javascript:gotocellhome("' +link +'","' + live_cellname2
                        + '")>' + live_cellname2 + '</a>';
                    $("#table_list_complain").jqGrid('setRowData', id, {
                            live_cellname2 : url2
                        });
                }
				var live_cellname3 = $("#table_list_complain").getCell(id,
						'live_cellname3');
				
				if(live_cellname3.indexOf("<a")<0){
                    var url3 = '<a href=javascript:gotocellhome("' +link +'","' + live_cellname3
                        + '")>' + live_cellname3 + '</a>';
                            
                    $("#table_list_complain").jqGrid('setRowData', id, {
                            live_cellname3 : url3
                        });
                }								
			}
		}
	});
	// Add responsive to jqGrid
	$(window).bind('resize', function() {
				var width = $('.jqGrid_wrapper').width();
				$('#table_list_complain').setGridWidth(width);
			});
}

function refreshJqGrid_capacity(list) {
	$("#table_list_work").jqGrid({
				data : list,
				datatype : "local",
				height : "auto",
				autowidth : true,
				shrinkToFit : true,
				rowNum : 10,
				rowList : [10, 20, 30],
				colNames : ['发生时间', '监控内容', '监控时值'],
				colModel : [{
							name : 'occurrence_time',
							index : 'occurrence_time',
							width : 40,
							formatter : function(cellvalue, options, rowObject) {
								return $.hd_jqGrid.dateTimeFormatter(cellvalue);
							}
						}, {
							name : 'monitor_content',
							index : 'monitor_content',
							width : 40
						}, {
							name : 'monitor_value',
							index : 'monitor_value',
							width : 45
						}],
				pager : "#pager_list_work",
				viewrecords : true,
				hidegrid : false
			});
	$(window).bind('resize', function() {
				var width = $('.jqGrid_wrapper').width();
				$('#table_list_work').setGridWidth(width);
			});
}

function refreshJqGrid_device(list) {
	$("#table_list_work2").jqGrid({
				data : list,
				datatype : "local",
				height : "auto",
				autowidth : true,
				shrinkToFit : true,
				rowNum : 10,
				rowList : [10, 20, 30],
				colNames : ['建单时间', '网元名称', '告警标准名'],
				colModel : [

				{
							name : 'build_bill_time',
							index : 'build_bill_time',
							width : 35,
							formatter : function(cellvalue, options, rowObject) {
								return $.hd_jqGrid.dateTimeFormatter(cellvalue);
							}
						}, {
							name : 'cell_name',
							index : 'cell_name',
							width : 30
						}, {
							name : 'net_alarm_name',
							index : 'net_alarm_name',
							width : 30
						}],
				pager : "#pager_list_work2",
				viewrecords : true,
				hidegrid : false
			});
	$(window).bind('resize', function() {
				var width = $('.panel-body').width();
				$('#table_list_work2').setGridWidth(width);
			});
}

function refreshJqGrid_outserver(list) {
	$("#table_list_work3").jqGrid({
				data : list,
				datatype : "local",
				height : "auto",
				autowidth : true,
				shrinkToFit : true,
				rowNum : 10,
				rowList : [10, 20, 30],
				colNames : ['发生时间', '小区名称', '故障类型'],
				colModel : [

				{
							name : 'start_time',
							index : 'start_time',
							width : 30,
							formatter : function(cellvalue, options, rowObject) {
								return $.hd_jqGrid.dateTimeFormatter(cellvalue);
							}
						}, {
							name : 'cell_name',
							index : 'cell_name',
							width : 35
						}, {
							name : 'fault_type',
							index : 'fault_type',
							width : 30
						}],
				pager : "#pager_list_work3",
				viewrecords : true,
				hidegrid : false
			});
	$(window).bind('resize', function() {
				var width = $('.panel-body').width();
				$('#table_list_work3').setGridWidth(width);
			});
}

function refreshJqGrid_indexifo(list) {
	$("#table_list_index").jqGrid({
				data : list,
				datatype : "local",
				height : "auto",
				autowidth : true,
				shrinkToFit : true,
				rowNum : 10,
				rowList : [10, 20, 30],
				colNames : ['索引', '数据类型', '指标名称', '最新值'],
				colModel : [

				{
							name : 'id',
							index : 'id',
							width : 20
						}, {
							name : 'type',
							index : 'type',
							width : 30
						}, {
							name : 'dataname',
							index : 'dataname',
							width : 40
						}, {
							name : 'datavalue',
							index : 'datavalue',
							width : 20
						}],
				pager : "#pager_list_index",
				viewrecords : true,
				hidegrid : false
			});
	$(window).bind('resize', function() {
				var width = $('.panel-body').width();
				$('#table_list_index').setGridWidth(width);
			});
}

function switchwork(url, params) {

	$.ajax({
				url : url,
				data : {
					'cellname' : params
				},
				type : "POST",
				dataType : "json",
				success : function(data, status) {
					var list = data.rows;
					if (url == "/sdas/capacitywork/oneweek") {
						if (!iscapacitywork) {
							refreshJqGrid_capacity(list);
							iscapacitywork = true;
							isdevicework = false;
							isoutservework = false;
						}
					} else if (url == "/sdas/devicework/oneweek") {
						if (!isdevicework) {
							refreshJqGrid_device(list);
							isdevicework = true;
							iscapacitywork = false;
							isoutservework = false;
						}
					} else if (url == "/sdas/outserverwork/oneweek") {
						if (!isoutservework) {
							refreshJqGrid_outserver(list);
							isoutservework = true;
							iscapacitywork = false;
							isdevicework = false;
						}
					} else if (url == "/sdas/cellindex/mrinfo") {
						if (!isindexinfo) {
							refreshJqGrid_indexifo(list);
							isindexinfo = true;
							isoutservework = false;
							iscapacitywork = false;
							isdevicework = false;
						}
					}

				}
			});
}
function refreshJqGrid_weight(list) {
    $("#table_list_weight").jqGrid({
        data : list,
        datatype : "local",
        height : "auto",
        autowidth : false,
        shrinkToFit : false,
        rowNum : 10,
        rowList : [10, 20, 30],
        colNames : ['指标名称', '0点', '1点', '2点', '3点', '4点', '5点', '6点', '7点',
                '8点', '9点', '10点', '11点', '12点', '13点', '14点', '15点', '16点',
                '17点', '18点', '19点', '20点', '21点', '22点', '23点'],
        colModel : [{
                    name : 'indeicator_name',
                    index : '指标名称',
                    width : 180
                }, {
                    name : 'range0',
                    index : '0点',
                    width : 100
                }, {
                    name : 'range1',
                    index : '1点',
                    width : 100
                }, {
                    name : 'range2',
                    index : '2点',
                    width : 100
                }, {
                    name : 'range3',
                    index : '3点',
                    width : 100
                }, {
                    name : 'range4',
                    index : '4点',
                    width : 100
                }, {
                    name : 'range5',
                    index : '5点',
                    width : 100
                }, {
                    name : 'range6',
                    index : '6点',
                    width : 100
                }, {
                    name : 'range7',
                    index : '7点',
                    width : 100
                }, {
                    name : 'range8',
                    index : '8点',
                    width : 100
                }, {
                    name : 'range9',
                    index : '9点',
                    width : 100
                }, {
                    name : 'range10',
                    index : '10点',
                    width : 100
                }, {
                    name : 'range11',
                    index : '11点',
                    width : 100
                }, {
                    name : 'range12',
                    index : '12点',
                    width : 100
                }, {
                    name : 'range13',
                    index : '13点',
                    width : 100
                }, {
                    name : 'range14',
                    index : '14点',
                    width : 100
                }, {
                    name : 'range15',
                    index : '15点',
                    width : 100
                }, {
                    name : 'range16',
                    index : '16点',
                    width : 100
                }, {
                    name : 'range17',
                    index : '17点',
                    width : 100
                }, {
                    name : 'range18',
                    index : '18点',
                    width : 100
                }, {
                    name : 'range19',
                    index : '19点',
                    width : 100
                }, {
                    name : 'range20',
                    index : '20点',
                    width : 100
                }, {
                    name : 'range21',
                    index : '21点',
                    width : 100
                }, {
                    name : 'range22',
                    index : '22点',
                    width : 100
                }, {
                    name : 'range23',
                    index : '23点',
                    width : 100
                }],
        pager : "#pager_list_weight",
        viewrecords : true,
        hidegrid : false
    });
}
function refresh_healthtable(list) {
	var html="<tr style='background-color:#F5F5F6;'><th>时间</th>";
	$.each(list[0].moments,function(i,e){
		html+="<th>"+i+"点</th>";
	});
	html+="</tr>";
	$("#table_list_healthtable").append(html);
	$("#table_list_healthtable").append("<tbody>");
	$.each(list,function(m,n){
		var str="<tr><td>"+n.yyyyMMdd+"</td>";
		$.each(n.moments,function(j,k){
			if(k.result==-1){//查询不到
				str+="<td class='gray'>"+k.ratio+"</td>";
			}else if(k.result==0){//Fault
				str+="<td class='red'>"+k.ratio+"</td>";
			}else if(k.result==1){//Normal
				str+="<td>"+k.ratio+"</td>";
			}else if(k.result==2){//Warning
				str+="<td class='yellow'>"+k.ratio+"</td>";
			}else if(k.result==3){//Unkonw
				str+="<td class='gray'>"+k.ratio+"</td>";
			}else if(k.result==4){//Error
				str+="<td class='gray'>"+k.ratio+"</td>";
			}
		});
		str+="</tr>";
		$("#table_list_healthtable").append(str);
	});
	$("#table_list_healthtable").append("</tbody>");
	////
	/*$('body').addClass('loaded');
    $('#loader-wrapper .load_title').remove();*/
}

function scroll() {

	$("#table tr:first").appendTo($("#table"));

}

setInterval(function() {
			scroll()
		}, 10000);

function splitData(rawData) {
	var categoryData = [];
	var values = []
	for (var i = 0; i < rawData.length; i++) {
		categoryData.push(rawData[i].splice(0, 1)[0]);
		values.push(rawData[i])
	}
	return {
		categoryData : categoryData,
		values : values
	};
}

function calculateRT(data) {
	var result = [];
	for (var i = 0, len = data.length; i < len; i++) {
		result.push(data[i][0]);
	}
	return result;
}

function switchindex(indeicator_id) {
	$.ajax({
				url : index,
				type : "post",
				data : {
					'index' : indeicator_id,
					'cellname' : cellname
				},
				dataType : "json",
				success : function(data, status) {
					var data0 = [];
					var rtdata = [];
					echart_option.series[0].data = data0;
					chart_mb.setOption(echart_option);
					if (data.success) {
						var scatter = data.rows;
						var data0 = splitData(scatter);
						var rtdata = calculateRT(data0.values);
						echart_option.series[0].data = data0.values;
						chart_mb.setOption(echart_option);
					} else {
						var data0 = [];
						echart_option.series[0].data = data0;
						chart_mb.setOption(echart_option);
					}

				},
				error : function(data, status) {
					alert(status);
				}
			});

}
//
function alarmJqGrid(list) {
	$("#alarm_table").jqGrid({
				data : list,
				datatype : "local",
				height : "auto",
				autowidth : true,
				shrinkToFit : true,
				rowNum : 10,
				rowList : [10, 20, 30],
				colNames : ['时间', '指标名称', '次数'],
				colModel : [{
							name : 'time',
							index : 'time',
							width : 60
						}, {
							name : 'alarm_name',
							index : 'alarm_name',
							width : 60
						}, {
							name : 'alarm_counts',
							index : 'alarm_counts',
							width : 40
						}],
				pager : "#pager_alarm_table",
				viewrecords : true,
				hidegrid : false
			});
	// Add responsive to jqGrid
	$(window).bind('resize', function() {
				var width = $('.jqGrid_wrapper').width();
				$('#alarm_table').setGridWidth(width);
			});
}
setInterval(function() {
			rtRatio()
		}, 5 * 60 * 1000);

var refreshcount = 0;
function rtRatio() {
	if (refreshcount < 23) {
		refreshcount++;
	} else {
		refreshcount = 0;
	}
	$.ajax({
				url : '/sdas/cell/rtratio',
				type : "post",
				data : {
					'cellname' : cellname,
					'count' : refreshcount
				},
				dataType : "json",
				success : function(data, status) {
					var ratio = data.ratio;
					if (ratio != undefined && ratio != "" && ratio != null) {
						$("#b_ratio").text(ratio);
						if (ratio > 80) {
							$("#h_ratio").css("color", "green");
						} else if (ratio > 25 && ratio <= 80) {
							$("#h_ratio").css("color", "#B9C83F");
						} else {
							$("#h_ratio").css("color", "red");
						}
						$("#h_ratio").css("display", "block");
						$("#h3_ratio").css("display", "block");
					} else {
						$("#h_ratio").css("display", "none");
						$("#h3_ratio").css("display", "none");
					}

				}
			});
}
function gotocellhome(url, cellname, station, scene, band) {

	var a_parent = $(".page-tabs-content", window.parent.document);
	var iframe_parent = $("#content-main", window.parent.document);

	var item = $('<a href="javascript:;" class="active J_menuTab" data-id="'
			+ url + '">日常监控 <i class="fa fa-times-circle"></i></a>');
	var content = $('<iframe class="J_iframe" name="iframe10" width="100%" height="100%" src="'
			+ url
			+ '?name='
			+ cellname
			+ '&stationname='
			+ station
			+ '&cover_scene='
			+ scene
			+ '&used_band='
			+ band
			+ '" frameborder="0" data-id="' + url + '" seamless></iframe>');

	a_parent.children("a").removeClass("active");

	if (a_parent.has('a[data-id="' + url + '"]').length > 0) {

		a_parent.children('a[data-id="' + url + '"]').addClass("active");
		iframe_parent.children("iframe").css("display", "none");
		iframe_parent.children().remove('iframe[data-id="' + url + '"]');
		content.css("display", "inline");
		iframe_parent.prepend(content);
	} else {

		content.css("display", "inline");
		a_parent.append(item);
		iframe_parent.children("iframe").css("display", "none");
		iframe_parent.prepend(content);
	}
}