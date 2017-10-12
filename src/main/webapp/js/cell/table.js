/**
 * 小组
 */
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
var color = ['rgba(104, 197, 204, 0.73)',
    'rgba(51,51,204, 0.53)', 'rgba(171, 226, 98, 0.62)'
];
var borderColor = ['rgb(25, 183, 207)', 'rgb(51,51,204,)',
    'rgb(163, 222, 84)'
];
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
            }
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
	        
	    ]
};
var serie = {
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
                        ]
                    ]
                }
            }
$(function(){
	$.jgrid.defaults.styleUI = 'Bootstrap';
	$.ajax({
		url: "/sdas/cell/getcelllist",
		type:"POST",
		data:{
			'type':'I'
		},
		dataType:"json",
		success:function(data,status){
			var list = data.rows;
			refreshJqGrid(list);
			$('body').addClass('loaded');
	        $('#loader-wrapper .load_title').remove();
		}
	}); 
	$.ajax({
		url:"/sdas/cell/groupindexs",
		type:"post",
		dataType:"json",
		data:{
			'type':'I'
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
			$(".active").trigger("click");
		}
	});
	/*
	 * 指标权重
	 */
	$.ajax({
		url: '/sdas/group/weight',
		data:{
			'type':'I'
		},
		type:"POST",
		dataType:"json",
		success:function(data,status){
			var list = data.rows;
			refreshJqGrid_weight(list);
		}
	});
	/*
	 * 覆盖场景
	 */
	$.ajax({
		url: "/sdas/cell/group",
		type:"GET",
		dataType:"json",
		success:function(data,status){
			var list = data.rows;
			for(var i=0;i<list.length;i++){
				var option = $('<option>'+list[i]+'</option>');
				$("#scene").append(option);
			}
			
		}
	});
});
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
                width: 180
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
function refreshJqGrid(list){
	$("#table_list_1").jqGrid({
		data:list,
		datatype: "local",
        height: "auto",
        autowidth: true,
        shrinkToFit: true,
        rowNum: 10,
        rowList: [10, 20, 30],
        colNames: ['小区名称','基站','分组' ,'覆盖场景','健康监控','指标监控'],
        colModel: [
            {
                name: 'network_name',
                index: 'network_name',
                width: 100
            },
            {
                name: 'station_name',
                index: 'station_name',
                width: 30,
                hidden:true
            },
            {
                name: 'group_type',
                index: 'group_type',
                width: 30,
                hidden:true
            },
            {
                name: 'cover_scene',
                index: 'cover_scene',
                width: 30
            },
            {
                name: 'index',
                index: '健康监控',
                width: 30
            },
            {
                name: '指标监控',
                index: '指标监控',
                width: 40
                
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
        	    var station_name = $("#table_list_1").getCell(id,'station_name');
        	    var link2 = "/sdas/general/cellhome/";
        	    var url2='<a href=javascript:gotocellhome("'+link2+'","'+names+'","'+station_name+'")>健康监控</a>';
        	    var link3 = "/sdas/fault/page";      	    
        	    var url3='<a href=javascript:gotoprb("'+link3+'","'+names+'")>指标监控</a>';
        	    $("#table_list_1").jqGrid('setRowData',id,{index:url2});
        	    $("#table_list_1").jqGrid('setRowData',id,{指标监控:url3});
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
	var type = $("#level").val();
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
function select2(type){
	$("#table_list_1").jqGrid("clearGridData");
	$.ajax({
		url:"/sdas/cell/getcelllist",
		type:"post",
		dataType:"json",
		data:{
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
	$.ajax({
		url:"/sdas/cell/groupindexs",
		type:"post",
		dataType:"json",
		data:{
			'type':type
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
					option = $('<li onclick=groupindex("'+type+'","'+index+'") class="active"><a href="#tab-'+i+'" data-toggle="tab" aria-expanded="true">'+item.indeicator_name+'</a></li>');
				}else {
					option = $('<li onclick=groupindex("'+type+'","'+index+'") class=""><a href="#tab-'+i+'" data-toggle="tab" aria-expanded="false">'+item.indeicator_name+'</a></li>');
				}
				 
				$("#group_index").append(option);
			}
			
		}
	});
	/*
	 * 指标权重
	 */
	$.ajax({
		url: '/sdas/group/weight',
		data:{
			'type':type
		},
		type:"POST",
		dataType:"json",
		success:function(data,status){
			if (data.success) {
				var list = data.rows;
				$("#table_list_weight").jqGrid("clearGridData");
				$("#table_list_weight").jqGrid('setGridParam',{  // 重新加载数据
				      datatype:'local',
				      data : list,   //  newdata 是符合格式要求的需要重新加载的数据 
				      page:1
				}).trigger("reloadGrid");
			}
			
		}
	});
}
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
                var series = [];
                for(var i=0;i<scatter.length;i++){
                    var temp = splitData(scatter[i]); 
                    var name = "簇心" +i;
                    serie.name = name;
                    serie.data = temp.values;
                    if(i<3){
                        serie.itemStyle.normal.color = color[i];
                        serie.itemStyle.normal.color0 = color[i];
                        serie.itemStyle.normal.borderColor = borderColor[i];
                        serie.itemStyle.normal.borderColor0 = borderColor[i];
                    }                    
                    series.push(serie);
                }
                echart_option.series = series;
				chart_mb.setOption(echart_option);
			}else {					
				echart_option.series = series; 
				chart_mb.setOption(echart_option);
			}
		}
	});
}
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

function clear(){
	$("#name").val("");
	$("#scene").val("");
	$("#type").val("");
}
$("#clear").click(function() {
	clear();
});

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

