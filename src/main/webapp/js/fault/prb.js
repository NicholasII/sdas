/**
 * by dq 
 * 2017年9月13日上午10:46:07
 * TODO
 */	
var Interval;
$(function(){
	PRBCharts(); 
});

function PRBCharts(){
	$.ajax({
		url: "/sdas/fault/prb",
		type:"get",
		dataType:"json",
		async:false,
		success:function(data,status){
			if (data.success) {
				var list = data.rows;
				var rrcdata = [];
				var upprbdata = [];
				var downprbdata = [];
				var times=[];
				for(var i = 0;i<list.length;i++){
					var item = list[i];
					//时间格式化
					var timestamp = new Date(item.timestamp);
					var date=timestamp.toLocaleDateString().replace(/\//g, "-") + " " + timestamp.toTimeString().substr(0, 8);
					times.push(date);
					rrcdata.push(parseInt(item.rrc));
					upprbdata.push(parseInt(item.upprb));
					downprbdata.push(parseInt(item.downprb));
				}
				drawEcharts("#rrc",'MR-RRC连接建立最大用户数',times,rrcdata,'#CE0000');
				drawEcharts("#up_prb_rate",'集团-502上行PRB平均利用率',times,upprbdata,'#8600FF');
				drawEcharts("#down_prb_rate",'集团-502下行PRB平均利用率',times,downprbdata,'#9AFF02');
			}
			
		}
	});
	if(Interval!=undefined){
		clearInterval(Interval);
	}
	Interval=setInterval(function(){PRBCharts()},5*60*1000);
}
function switchCharts(){
	$.ajax({
		url: "/sdas/fault/switch",
		type:"get",
		dataType:"json",
		async:false,
		success:function(data,status){
			if (data.success) {
				var list = data.rows;
				var yymon = [];
				var yysucces = [];
				var times=[];
				for(var i = 0;i<list.length;i++){
					var item = list[i];
					//时间格式化
					var timestamp = new Date(item.timestamp);
					var date=timestamp.toLocaleDateString().replace(/\//g, "-") + " " + timestamp.toTimeString().substr(0, 8);
					times.push(date);
					yymon.push(parseInt(item.yymon));
					yysucces.push(item.yysucces.replace(/%/, ""));
				}
				drawEcharts("#switch_mon",'YY-切换成功率分母',times,yymon,'#CE0000');
				drawEcharts("#switch_success_rate",'YY-切换成功率',times,yysucces,'#8600FF');
			}
			
		}
	});
	if(Interval!=undefined){
		clearInterval(Interval);
	}
	Interval=setInterval(function(){switchCharts()},5*60*1000);
}
function simpleCharts(url,attr,id,title,color){
	$.ajax({
		url: url,
		type:"get",
		dataType:"json",
		success:function(data,status){
			if (data.success) {
				var list = data.rows;
				var data = [];
				var times=[];
				for(var i = 0;i<list.length;i++){
					var item=list[i];
					//时间格式化
					var timestamp = new Date(item.timestamp);
					var date=timestamp.toLocaleDateString().replace(/\//g, "-") + " " + timestamp.toTimeString().substr(0, 8);
					times.push(date);
					if(url.indexOf("switch")>-1){
						data.push(item[attr].replace(/%/, ""));
					}else{
						data.push(item[attr]);
					}
					
				}
				drawEcharts(id,title,times,data,color);
			}
			
		}
	});
	if(Interval!=undefined){
		clearInterval(Interval);
	}
	Interval=setInterval(function(){simpleCharts(url,attr,id,title,color)},5*60*1000);
}
function drawEcharts(id,title,times,data,color){
	var mycharts= echarts.init($(id).get(0));
	var option= {
			legend : {
				left : 'left',
				data : [ title ]
			},
			tooltip : {
		        trigger: 'axis',
		        formatter: function (params) {
		        	var str=params[0].name+"</br>"+params[0].seriesName+" : ";
		        	if(id.indexOf("rate")>-1){
		        		return str+params[0].value+"%";
		        	}else{
		        		return str+params[0].value;
		        	}
               }
		    },
			xAxis : {
				type : 'category',
				data:times
			},
			yAxis : {
				type : 'value',
			},
			series : [{
				name:title,
				type : 'line',
				itemStyle : {
					normal : {
						color : color
					}
				},
				data : data
			}]
	};
	mycharts.setOption(option);
	mycharts.resize();
	/*window.onresize = function(){
	    myChart.resize(); 
	});*/
	//mycharts.resize();
}