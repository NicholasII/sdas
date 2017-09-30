/**
 * by dq 
 * 2017年9月14日下午4:04:56
 * TODO
 */
$.jgrid.defaults.styleUI = 'Bootstrap';
var index = ctx + "/cell/index";
var name1='历史统计数据';
var name2='实时数据';
var echart_option = {
	    tooltip: {
	        trigger: 'axis',
	        axisPointer: {
	            type: 'cross'
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
	        data: {},
	        scale: true,
	        boundaryGap : false,
	        axisLine: {onZero: false},
	        splitLine: {show: false},
	        splitNumber: 20,
	        min: 'dataMin',
	        max: 'dataMax'
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
	            markPoint: {
	                label: {
	                    normal: {
	                        formatter: function (param) {
	                            return param != null ? Math.round(param.value) : '';
	                        }
	                    }
	                },
	                data: [
	                    {
	                        name: 'XX标点',
	                        coord: ['2013/5/31', 2300],
	                        value: 2300,
	                        itemStyle: {
	                            normal: {color: 'rgb(41,60,85)'}
	                        }
	                    },
	                    {
	                        name: 'highest value',
	                        type: 'max',
	                        valueDim: 'highest'
	                    },
	                    {
	                        name: 'lowest value',
	                        type: 'min',
	                        valueDim: 'lowest'
	                    },
	                    {
	                        name: 'average value on close',
	                        type: 'average',
	                        valueDim: 'close'
	                    }
	                ],
	                tooltip: {
	                    formatter: function (param) {
	                        return param.name + '<br>' + (param.data.coord || '');
	                    }
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
	                    ],
	                    {
	                        name: 'min line on close',
	                        type: 'min',
	                        valueDim: 'close'
	                    },
	                    {
	                        name: 'max line on close',
	                        type: 'max',
	                        valueDim: 'close'
	                    }
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

var nullchart = [];
for(var i=0;i<24;i++){
	var sub = [];
	
}
$(function(){
	
	$.ajax({
		url: "/sdas/complain/getcelllist",
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
        	    var url="<a href=/sdas/work/complaint/?cellname='"+datas+"'>"+names+"</a>";
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
function scroll(){
  
    $("#table tr:first").appendTo($("#table"));
    
}

setInterval(() => {
	scroll()
}, 1000);

var upColor = '#ec0000';
var upBorderColor = '#8A0000';
var downColor = '#00da3c';
var downBorderColor = '#008F28';

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

function calculateMA(data0,dayCount) {
    var result = [];
    for (var i = 0, len = data0.values.length; i < len; i++) {
        if (i < dayCount) {
            result.push('-');
            continue;
        }
        var sum = 0;
        for (var j = 0; j < dayCount; j++) {
            sum += data0.values[i - j][1];
        }
        result.push(sum / dayCount);
    }
    return result;
}

$.ajax({
	url : index,
	type : "get",
	data : {
		'index' : '14'
	},
	dataType : "json",
	success : function(data, status) {
		var scatter = data.rows;
		var data0 = splitData(scatter);
		echart_option.xAxis.data = data0.categoryData;
		echart_option.series[0].data = data0.values; 
		var data1 = calculateMA(data0,5)
		echart_option.series[1].data = data1;					
		chart_mb.setOption(echart_option);
	},
	error : function(data, status) {
		alert(status);
	}
});
function switchindex(indeicator_id){
	$.ajax({
		url : index,
		type : "get",
		data : {
			'index' : indeicator_id
		},
		dataType : "json",
		success : function(data, status) {
			if (data.success) {
				var scatter = data.rows;
				var data0 = splitData(scatter);
				echart_option.xAxis.data = data0.categoryData;
				echart_option.series[0].data = data0.values; 
				var data1 = calculateMA(data0,5)
				echart_option.series[1].data = data1;
				
				chart_mb.setOption(echart_option);
			}else {
				var scatter = [];
				var data0 = splitData(scatter);
				echart_option.xAxis.data = data0.categoryData;
				echart_option.series[0].data = data0.values; 
				var data1 = calculateMA(data0,5)
				echart_option.series[1].data = data1;
				
				chart_mb.setOption(echart_option);
			}
			
			

		},
		error : function(data, status) {
			alert(status);
		}
	});

}
