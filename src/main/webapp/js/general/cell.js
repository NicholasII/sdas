/**
 * by dq 
 * 2017年9月14日下午4:04:56
 * TODO
 */
$.jgrid.defaults.styleUI = 'Bootstrap';
var index = ctx + "/cell/index";
var weighturl = ctx + "/cell/weight";
var healthtrendurl = ctx + "/cell/healthtrend";
var name1='历史分析';
var name2='实时数据';
var nullchart = [];
for(var i=0;i<24;i++){
	nullchart.push(i);
}
var upColor ='#68C5CC';
var upBorderColor = '#19B7CF';
var downColor = '#68C5CC';
var downBorderColor = '#19B7CF';
var echart_option = {
	    tooltip: {
	        trigger: 'axis',
	        axisPointer: {
	            type: 'cross'
	        },
	        formatter: function (params) {
            	var res = params[0].seriesName + ' ' + params[0].name;
            	res += '<br/>  前值 : ' + params[0].value[1] + '<br/>  后值 : ' + params[0].value[2];
            	res += '<br/>  最小 : ' + params[0].value[3] + '<br/>  最大 : ' + params[0].value[4];
            	return res;
        	}
	    },
	    legend: {
	        data: [name1, name2]
	    },
	    grid: {
	        left: '10%',
	        right: '10%',
	        bottom: '15%'
	    },
	    xAxis: {
	    	type: 'category',
	        scale: true,
	        boundaryGap : false,
	        axisLine: {onZero: false},
	        splitLine: {show: false},
	        data :  nullchart,
            axisLabel : {
                formatter : function(value){
                	return value;
            	}
            },
	    },
	    yAxis: {
	        scale: true,
	        splitArea: {
	            show: true
	        }
	    },
	    dataZoom: [
	        {
	            type: 'inside',
	            start: 0,
	            end: 100
	        },
	        {
	            show: true,
	            type: 'slider',
	            y: '90%',
	            start: 50,
	            end: 100
	        }
	    ],
	    series: [
	        {
	            name: name1,
	            type: 'candlestick',
	            data: {},
	            itemStyle: {
	                normal: {
	                    color: upColor,
	                    color0: downColor,
	                    borderColor: upBorderColor,
	                    borderColor0: downBorderColor
	                }
	            },	            
	            markLine: {
	                symbol: ['none', 'none'],
	                data: [
	                    [
	                        {
	                            name: 'from lowest to highest',
	                            type: 'min',
	                            valueDim: 'lowest',
	                            symbol: 'circle',
	                            symbolSize: 10,
	                            label: {
	                                normal: {show: false},
	                                emphasis: {show: false}
	                            }
	                        },
	                        {
	                            type: 'max',
	                            valueDim: 'highest',
	                            symbol: 'circle',
	                            symbolSize: 10,
	                            label: {
	                                normal: {show: false},
	                                emphasis: {show: false}
	                            }
	                        }
	                    ]/*,
	                    {
	                        name: 'min line on close',
	                        type: 'min',
	                        valueDim: 'close'
	                    },
	                    {
	                        name: 'max line on close',
	                        type: 'max',
	                        valueDim: 'close'
	                    }*/
	                ]
	            }
	        },
	        {
	            name: name2,
	            type: 'line',
	            data: {},
	            smooth: true,
	            lineStyle: {
	                normal: {opacity: 0.5}
	            }
	        }
	    ]
};
var iscapacitywork = false;
var isdevicework = false;
var isoutservework = false;
var isindexinfo = false;

$(function(){
	/*
	 * 故障工单
	 */
	$.ajax({
		url: complainturl,
		data:{
			'cellname':cellname
		},
		type:"POST",
		dataType:"json",
		success:function(data,status){
			var list = data.rows;
			refreshJqGrid(list);
			
		}
	});
	/*
	 * 性能工单
	 */
	$.ajax({
		url: capacityurl,
		data:{
			'cellname':cellname
		},
		type:"POST",
		dataType:"json",
		success:function(data,status){
			var list = data.rows;
			refreshJqGrid_capacity(list);
			
		}
	});
	/*
	 * 指标权重
	 */
	$.ajax({
		url: weighturl,
		data:{
			'cellname':cellname
		},
		type:"POST",
		dataType:"json",
		success:function(data,status){
			var list = data.rows;
			refreshJqGrid_weight(list);
		}
	});
	$.ajax({
		url: "/sdas/cell/belonggroup",
		data:{
			'cellname':cellname
		},
		type:"POST",
		dataType:"json",
		success:function(data,status){
			var group = data.group;
			$.ajax({
				url:"/sdas/cell/groupindexs",
				type:"post",
				dataType:"json",
				data:{
					'type':group
				},
				success:function(data,status){
					var list = data.rows;
					$("#group_index").children().remove();
					for (var i = 0; i < list.length; i++) {
						var item = list[i];
						var type = item.cell_code;
						var index = item.indeicator_code;
						var option;
						if (i==0) {
							option = $('<li onclick=groupindex("'+type+'","'+index+'") class="active"><a data-toggle="tab" aria-expanded="true">'+item.indeicator_name+'</a></li>');
						}else {
							option = $('<li onclick=groupindex("'+type+'","'+index+'") class=""><a data-toggle="tab" aria-expanded="false">'+item.indeicator_name+'</a></li>');
						}
						 
						$("#group_index").append(option);
					}
					$("li.active").trigger("click");
				}
			});
		}
	});
	/*
	 * 简况度曲线
	 */
	$.ajax({
		url: healthtrendurl,
		data:{
			'cellname':cellname
		},
		type:"POST",
		dataType:"json",
		success:function(data,status){
			var list = data.rows;
			ratiotrend.setOption(option={
					tooltip: {
			            trigger: 'axis'
			        },
			        xAxis: {
			            type:'value',
			            max:23
			        },
			        yAxis: {
			            splitLine: {
			                show: false
			            }
			        },
			        toolbox: {
			            left: 'center',
			            feature: {
			                dataZoom: {
			                    yAxisIndex: 'none'
			                },
			                restore: {},
			                saveAsImage: {}
			            }
			        },
			        dataZoom: [{
			                type: 'slider',
			            	startValue: 6,
				            endValue: 15
			        }],
			        visualMap: {
			            top: 10,
			            right: 10,
			            pieces: [{
			                gt: 0,
			                lte: 50,
			                color: '#096'
			            }, {
			                gt: 50,
			                lte: 100,
			                color: '#ffde33'
			            }, {
			                gt: 100,
			                lte: 150,
			                color: '#ff9933'
			            }, {
			                gt: 150,
			                lte: 200,
			                color: '#cc0033'
			            }, {
			                gt: 200,
			                lte: 300,
			                color: '#660099'
			            }, {
			                gt: 300,
			                color: '#7e0023'
			            }],
			            outOfRange: {
			                color: '#999'
			            }
			        },
			        series: {
			            name: '历史健康度',
			            type: 'line',
			            data: list,
			            markLine: {
			                silent: true,
			                data: [{
			                    yAxis: 50
			                }, {
			                    yAxis: 100
			                }, {
			                    yAxis: 150
			                }, {
			                    yAxis: 200
			                }, {
			                    yAxis: 300
			                }]
			            }
			        }
			});
		}
	});
});
function groupindex(cellcode,indexcode) {
	$.ajax({
		url:"/sdas/cell/groupindexcontent",
		type:"post",
		dataType:"json",
		data:{
			'grouptype':cellcode,
			'indexid':indexcode
		},
		success:function(data,status){
			if (data.success) {
				var scatter = data.rows;
				var data0 = splitData(scatter);			
				var rtdata = calculateMA(data0.values);				
				echart_option.series[0].data = data0.values; 
				echart_option.series[1].data = rtdata;					
				chart_mb.setOption(echart_option);
			}else {
				var data0 = [];			
				var rtdata = [];
				
				echart_option.series[0].data = data0; 
				echart_option.series[1].data = rtdata;	
				chart_mb.setOption(echart_option);
			}
		}
	});
}
function refreshJqGrid(list){
	$("#table_list_complain").jqGrid({
		data:list,
		datatype: "local",
        height: "auto",
        autowidth: true,
        shrinkToFit: true,
        rowNum: 10,
        rowList: [10, 20, 30],
        colNames: ['时间', '电话', '常住小区1', '常住小区2','常住小区3'],
        colModel: [
            {
                name: 'record_time',
                index: 'record_time',
                width: 40,
                formatter:function(cellvalue, options, rowObject) {  
                    return $.hd_jqGrid.dateTimeFormatter(cellvalue);  
                }
            },
            {
                name: 'phone_number',
                index: 'phone_number',
                width: 40
            },
            {
                name: 'live_cellname1',
                index: 'live_cellname1',
                width: 60
            },
            {
                name: 'live_cellname2',
                index: 'live_cellname2',
                width: 60
            },
            {
                name: 'live_cellname3',
                index: 'live_cellname3',
                width: 60
            }
        ],
        pager: "#pager_list_complain",
        viewrecords: true,
        hidegrid: false,
        gridComplete:function(){
        	//获取某列的每一行id
        	var ids = jQuery("#table_list_complain").jqGrid("getDataIDs");
        	for(var i=0;i<ids.length;i++){
        	    var id = ids[i];
        	    var names = $("#table_list_complain").getCell(id,'countnum');
        	    var datas = $("#table_list_complain").getCell(id,'cellname');
        	    var url="<a href=/sdas/work/complaint/?cellname='"+datas+"'>"+names+"</a>";
        	    $("#table_list_complain").jqGrid('setRowData',id,{countnum:url});
        	}
        }
	});
	// Add responsive to jqGrid
    $(window).bind('resize', function () {
        var width = $('.jqGrid_wrapper').width();
        $('#table_list_complain').setGridWidth(width);
    });
}
function refreshJqGrid_capacity(list){
	$("#table_list_work").jqGrid({
		data:list,
		datatype: "local",
        height: "auto",
        autowidth: true,
        shrinkToFit: true,
        rowNum: 10,
        rowList: [10, 20, 30],
        colNames: ['发生时间', '监控内容', '监控时值'],
        colModel: [
            {
                name: 'occurrence_time',
                index: 'occurrence_time',
                width: 40,
                formatter:function(cellvalue, options, rowObject) {  
                    return $.hd_jqGrid.dateTimeFormatter(cellvalue);  
                }
            },
            {
                name: 'monitor_content',
                index: 'monitor_content',
                width: 40
            },
            {
                name: 'monitor_value',
                index: 'monitor_value',
                width: 45
            }
        ],
        pager: "#pager_list_work",
        viewrecords: true,
        hidegrid: false
	});
    $(window).bind('resize', function () {
        var width = $('.jqGrid_wrapper').width();
        $('#table_list_work').setGridWidth(width);
    });
}
function refreshJqGrid_device(list){
	$("#table_list_work2").jqGrid({
		data:list,
		datatype: "local",
        height: "auto",
        autowidth: true,
        shrinkToFit: true,
        rowNum: 10,
        rowList: [10, 20, 30],
        colNames: ['建单时间','网元名称','告警标准名'],
        colModel: [
        	
            {
                name: 'build_bill_time',
                index: 'build_bill_time',
                width: 35,
                formatter:function(cellvalue, options, rowObject) {  
                    return $.hd_jqGrid.dateTimeFormatter(cellvalue);  
                }
            },
            {
                name: 'cell_name',
                index: 'cell_name',
                width: 30
            },
            {
                name: 'net_alarm_name',
                index: 'net_alarm_name',
                width: 30
            }
        ],
        pager: "#pager_list_work2",
        viewrecords: true,
        hidegrid: false
	});
    $(window).bind('resize', function () {
        var width = $('.panel-body').width();
        $('#table_list_work2').setGridWidth(width);
    });
}
function refreshJqGrid_outserver(list){
	$("#table_list_work3").jqGrid({
		data:list,
		datatype: "local",
        height: "auto",
        autowidth: true,
        shrinkToFit: true,
        rowNum: 10,
        rowList: [10, 20, 30],
        colNames: ['发生时间','小区名称','故障类型'],
        colModel: [
        	
            {
                name: 'start_time',
                index: 'start_time',
                width: 30,
                formatter:function(cellvalue, options, rowObject) {  
                    return $.hd_jqGrid.dateTimeFormatter(cellvalue);  
                }
            },
            {
                name: 'cell_name',
                index: 'cell_name',
                width: 35
            },
            {
                name: 'fault_type',
                index: 'fault_type',
                width: 30
            }
        ],
        pager: "#pager_list_work3",
        viewrecords: true,
        hidegrid: false
	});
    $(window).bind('resize', function () {
        var width = $('.panel-body').width();
        $('#table_list_work3').setGridWidth(width);
    });
}
function refreshJqGrid_indexifo(list){
	$("#table_list_index").jqGrid({
		data:list,
		datatype: "local",
        height: "auto",
        autowidth: true,
        shrinkToFit: true,
        rowNum: 10,
        rowList: [10, 20, 30],
        colNames: ['索引','数据类型','指标名称','最新值'],
        colModel: [
        	
            {
                name: 'id',
                index: 'id',
                width: 20
            },
            {
                name: 'type',
                index: 'type',
                width: 30
            },
            {
                name: 'dataname',
                index: 'dataname',
                width: 40
            },
            {
                name: 'datavalue',
                index: 'datavalue',
                width: 20
            }
        ],
        pager: "#pager_list_index",
        viewrecords: true,
        hidegrid: false
	});
    $(window).bind('resize', function () {
        var width = $('.panel-body').width();
        $('#table_list_index').setGridWidth(width);
    });
}
function switchwork(url,params) {

	$.ajax({
		url: url,
		data:{
			'cellname':params
		},
		type:"POST",
		dataType:"json",
		success:function(data,status){
			var list = data.rows;
			if (url=="/sdas/capacitywork/gettable") {
				if (!iscapacitywork) {
					refreshJqGrid_capacity(list);
					iscapacitywork = true;
				}
			} else if (url=="/sdas/devicework/getlist") {
				if (!isdevicework) {
					refreshJqGrid_device(list);
					isdevicework = true;
				}			
			}else if(url=="/sdas/outserverwork/getlist"){
				if (!isoutservework) {
					refreshJqGrid_outserver(list);
					isoutservework = true;
				}				
			}else if (url=="/sdas/cellindex/mrinfo") {
				if (!isindexinfo) {
					refreshJqGrid_indexifo(list);
					isindexinfo = true;
				}
			}
			
		}
	});
}
function refreshJqGrid_weight(list){
	$("#table_list_weight").jqGrid({
		data:list,
		datatype: "local",
        height: "auto",
        autowidth: false,
        shrinkToFit: false,
        rowNum: 10,
        rowList: [10, 20, 30],
        colNames: ['指标名称', '0点', '1点', '2点','3点','4点', '5点', '6点','7点','8点', '9点', '10点','11点','12点', '13点', '14点','15点','16点', '17点', '18点','19点','20点', '21点', '22点','23点'],
        colModel: [
            {
                name: 'indeicator_name',
                index: '指标名称',
                width: 180,
            },
            {
                name: 'range0',
                index: '0点',
                width: 100
            },
            {
                name: 'range1',
                index: '1点',
                width: 100
            },
            {
                name: 'range2',
                index: '2点',
                width: 100
            },
            {
                name: 'range3',
                index: '3点',
                width: 100
            },
            {
                name: 'range4',
                index: '4点',
                width: 100
            },
            {
                name: 'range5',
                index: '5点',
                width: 100
            },{
                name: 'range6',
                index: '6点',
                width: 100
            },
            {
                name: 'range7',
                index: '7点',
                width: 100
            },
            {
                name: 'range8',
                index: '8点',
                width: 100
            },
            {
                name: 'range9',
                index: '9点',
                width: 100
            },
            {
                name: 'range10',
                index: '10点',
                width: 100
            },
            {
                name: 'range11',
                index: '11点',
                width: 100
            },
            {
                name: 'range12',
                index: '12点',
                width: 100
            },{
                name: 'range13',
                index: '13点',
                width: 100
            },
            {
                name: 'range14',
                index: '14点',
                width: 100
            },
            {
                name: 'range15',
                index: '15点',
                width: 100
            },
            {
                name: 'range16',
                index: '16点',
                width: 100
            },
            {
                name: 'range17',
                index: '17点',
                width: 100
            },
            {
                name: 'range18',
                index: '18点',
                width: 100
            },{
                name: 'range19',
                index: '19点',
                width: 100
            },
            {
                name: 'range20',
                index: '20点',
                width: 100
            },
            {
                name: 'range21',
                index: '21点',
                width: 100
            },
            {
                name: 'range22',
                index: '22点',
                width: 100
            },
            {
                name: 'range23',
                index: '23点',
                width: 100
            }
        ],
        pager: "#pager_list_weight",
        viewrecords: true,
        hidegrid: false  
	});
   /* $(window).bind('resize', function () {
        var width = $('.jqGrid_wrapper').width();
        $('#table_list_weight').setGridWidth(width);
    });*/
}


function scroll(){
  
    $("#table tr:first").appendTo($("#table"));
    
}

setInterval(() => {
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
        categoryData: categoryData,
        values: values
    };
}


function calculateMA(data) {
    var result = [];
    for (var i = 0, len = data.length; i < len; i++) {
        result.push(data[i][0]);
    }
    return result;
}
$.ajax({
	url : index,
	type : "post",
	data : {
		'index' : '14',
		'cellname':cellname
	},
	dataType : "json",
	success : function(data, status) {
		if (data.success) {
			var scatter = data.rows;
			var data0 = splitData(scatter);			
			//var rtdata = calculateMA(data0.values);
			echart_option.series[0].data = data0.values; 
			//echart_option.series[1].data = rtdata;					
			chart_mb.setOption(echart_option);
		}else {		
			var data0 = [];
			var rtdata = [];
			
			echart_option.series[0].data = data0; 
			//echart_option.series[1].data = rtdata;	
			chart_mb.setOption(echart_option);
		}
		
	},
	error : function(data, status) {
		alert(status);
	}
});
function switchindex(indeicator_id){
	$.ajax({
		url : index,
		type : "post",
		data : {
			'index' : indeicator_id,
			'cellname':cellname
		},
		dataType : "json",
		success : function(data, status) {
			var data0 = [];			
			var rtdata = [];
			
			echart_option.series[0].data = data0; 
			//echart_option.series[1].data = rtdata;	
			chart_mb.setOption(echart_option);
			if (data.success) {
				var scatter = data.rows;
				var data0 = splitData(scatter);			
				var rtdata = calculateMA(data0.values);
				echart_option.series[0].data = data0.values; 
				//echart_option.series[1].data = rtdata;					
				chart_mb.setOption(echart_option);
			}else {
				var data0 = [];			
				//var rtdata = [];
				
				echart_option.series[0].data = data0; 
				//echart_option.series[1].data = rtdata;	
				chart_mb.setOption(echart_option);
			}
			
			

		},
		error : function(data, status) {
			alert(status);
		}
	});

}
