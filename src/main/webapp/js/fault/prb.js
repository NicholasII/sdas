/**
 * by dq 
 * 2017年9月13日上午10:46:07
 * TODO
 */	
var option3 = {
	legend : {
		left : 'left',
		data : [ 'MR-RRC连接建立最大用户数', '集团-502上行PRB平均利用率', '集团-502下行PRB平均利用率' ]
	},
	xAxis : {
		type : 'value'

	},
	yAxis : {
		type : 'value',
	},
	series : [ {
		name : "MR-RRC连接建立最大用户数",
		type : 'line',
		lineStyle : {
			normal : {
				color : '#CE0000',
			}
		},
		data : []

	}, {
		name : '集团-502上行PRB平均利用率',
		type : 'line',
		lineStyle : {
			normal : {
				color : '#8600FF'
			}
		},
		data : []
	}, {
		name : '集团-502下行PRB平均利用率',
		type : 'line',
		lineStyle : {
			normal : {
				color : '#9AFF02'
			}
		},
		data : []
	} ]

};

var rrcoption = {
		legend : {
			left : 'left',
			data : [ 'MR-RRC连接建立最大用户数' ]
		},
		xAxis : {
			type : 'value'
		},
		yAxis : {
			type : 'value',
		},
		series : [{
			name:'MR-RRC连接建立最大用户数',
			type : 'line',
			lineStyle : {
				normal : {
					color : '#CE0000'
				}
			},
			data : []
		}]
};
var upprboption = {
		legend : {
			left : 'left',
			data : [ '集团-502上行PRB平均利用率' ]
		},
		xAxis : {
			type : 'value'
		},
		yAxis : {
			type : 'value',
		},
		series : [{
			name:'集团-502上行PRB平均利用率',
			type : 'line',
			lineStyle : {
				normal : {
					color : '#8600FF'
				}
			},
			data : []
		}]
};
var downprboption = {
		legend : {
			left : 'left',
			data : [ '集团-502下行PRB平均利用率' ]
		},
		xAxis : {
			type : 'value'
		},
		yAxis : {
			type : 'value',
		},
		series : [{
			name:'集团-502下行PRB平均利用率',
			type : 'line',
			lineStyle : {
				normal : {
					color : '#9AFF02'
				}
			},
			data : []
		}]
};

var switch_countoption = {
		legend : {
			left : 'left',
			data : [ '小区间切换出准备请求次数' ]
		},
		xAxis : {
			type : 'value'
		},
		yAxis : {
			type : 'value',
		},
		series : [{
			name:'小区间切换出准备请求次数',
			type : 'line',
			lineStyle : {
				normal : {
					color : '#CE0000'
				}
			},
			data : []
		}]
};
var switch_successoption = {
		legend : {
			left : 'left',
			data : [ '切换出成功率' ]
		},
		xAxis : {
			type : 'value'
		},
		yAxis : {
			type : 'value',
		},
		series : [{
			name:'切换出成功率',
			type : 'line',
			lineStyle : {
				normal : {
					color : '#CE0000'
				}
			},
			data : []
		}]
};

$.ajax({
	url: "/sdas/fault/prb",
	type:"get",
	dataType:"json",
	success:function(data,status){
		if (data.success) {
			var list = data.rows;
			var rrcdata = [];
			var upprbdata = [];
			var downprbdata = [];
			for(var i = 0;i<list.length;i++){
				var rrc1 = [];
				var upprb1 = [];
				var downprb1 = [];
				var item = list[i];
				rrc1[0] = i*0.25;
				rrc1[1] = parseInt(item.rrc);
				upprb1[0] = i*0.25;
				upprb1[1] = parseInt(item.upprb);
				downprb1[0] = i*0.25;
				downprb1[1] = parseInt(item.downprb);
				rrcdata.push(rrc1);
				upprbdata.push(upprb1);
				downprbdata.push(downprb1);
			}
			rrcoption.series[0].data = rrcdata;
			rrc.setOption(rrcoption);
			upprboption.series[0].data = upprbdata;
			up_prb.setOption(upprboption);
			downprboption.series[0].data = downprbdata;
			down_prb.setOption(downprboption);
			option3.series[0].data = rrcdata;
			option3.series[1].data = upprbdata;
			option3.series[2].data = downprbdata;
			relate_index.setOption(option3);
		}
		
	}
}); 
function switchs(){
	$.ajax({
		url: "/sdas/fault/prb",
		type:"get",
		dataType:"json",
		success:function(data,status){
			if (data.success) {
				var list = data.rows;
				var succdata = [];
				for(var i = 0;i<list.length;i++){
					var temp = [];	
					var item = list[i];
					temp[0] = i*0.25;
					temp[1] = parseInt(item.switchs);				
					succdata.push(temp);
				}
				switch_successoption.series[0].data = succdata;
				switchsucc.setOption(switch_successoption);
			}			
		}
	}); 
}