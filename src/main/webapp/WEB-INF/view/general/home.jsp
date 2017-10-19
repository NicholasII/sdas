<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>Insert title here</title>
<%@ include file="/include/common.jsp"%>
<script type="text/javascript" src="${context }/include/time.js"></script>
<script type="text/javascript" src="${context }/include/utils.js"></script>
<link href="${context}/style/loader.css" rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=EmXf0NLcNCvBO5hdDliGtvC9D5v6GA5K"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/library/Heatmap/2.0/src/Heatmap_min.js"></script>
</head>
<body>
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox-title">
					<h1>预警</h1>
				</div>
				<div class="ibox-content">
					<div class="col-sm-6">
						<a href="javascript:iframeconvert('/sdas/alarm/','小区健康异常预警')"><h5>小区健康异常预警</h5></a>
						<div class="ibox-content">
							<div class="jqGrid_wrapper">
								<table class="table" id="table_list_alarm"></table>
								<div id="pager_list_alarm"></div>
							</div>
						</div>
					</div>
					<div class="col-sm-6">
						<h5>小区健康度分布图</h5>
						<div class="ibox-content">
							<div style="height: 300px;">
								<div id="allmap" style="text-align: center; height: 85%; width: 100%"></div>
							</div>
						</div>
					</div>
					<div class="col-sm-12">
						<div>
							<h5 style="float: left;">小区指标异常预警</h5>
							<div style="float: right;" class="ibox-tools">
								<select><option>全部</option>
									<option>PRB利用率</option>
									<option>切换出成功率</option>
								</select>
							</div>
						</div>
						<div class="ibox-content">
							<table class="table">
								<thead>
									<tr>
										<th>时间</th>
										<th>小区名称</th>
										<th>次数</th>
										<th>指标值</th>
									</tr>
								</thead>
								<tbody id="table1">
									<tr>
										<td>2017-08-26 20:00</td>
										<td>广州白云区朝阳工业区F-ZLH-1</td>
										<td>10</td>
										<td>[YY-无线接通率]=20%</td>
									</tr>
									<tr>
										<td>2017-08-26 19:15</td>
										<td>广州西区和辉花园D-ZLH-3</td>
										<td>5</td>
										<td>[下行PRB利用率] = 61.95</td>
									</tr>
									<tr>
										<td>2017-08-26 15:00</td>
										<td>广州白云区朝阳工业区F-ZLH-1</td>
										<td>3</td>
										<td>[RRC连接最大数] = 215.00</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox-title">
					<h1>工单验证</h1>
				</div>
				<div class="ibox-content">
					<div class="col-sm-12">
						<a href="javascript:iframeconvert('/sdas/work/capacity','性能工单')"><h5>可疑工单</h5></a>
						<div class="ibox-content">
							<div class="jqGrid_wrapper">
								<table class="table" id="table_list_2"></table>
								<div id="pager_list_2"></div>
							</div>
						</div>
					</div>
					<div class="col-sm-7" style="display: none;">
						<h5>性能工单数量统计</h5>
						<div class="ibox-content">
							<div id="line" style="height: 300px;"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row" style="display: none;">
			<div class="col-sm-6">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<a href="${context}/work/complaint"><h5>集中投诉</h5></a>

					</div>
					<div class="ibox-content">
						<div class="jqGrid_wrapper">
							<table class="table" id="table_list_1"></table>
							<div id="pager_list_1"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>性能工单原因统计</h5>
					</div>
					<div id="pie" style="height: 300px;"></div>
				</div>
			</div>
		</div>

	</div>
	<script type="text/javascript">
		var linechart = echarts.init($("#line").get(0));
		var data = [ 19, 1, 1, 21, 2, 3, 4, 5, 6, 2, 7, 8, 8, 1, 2, 3, 4, 5, 6,
				7, 6, 2, 2, 4 ];
		var option = {
			legend : {
				data : [ '日常工单', '可疑工单', '实时工单' ]
			},
			tooltip : {
				trigger : 'axis'
			},
			xAxis : {
				type : 'category',
				data : [ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
						17, 18, 19, 20, 21, 22, 23 ],
				axisLine : {
					lineStyles : {
						color : "#FFFFFF"
					}
				},
				axisLabel : {
					formatter : '{value}时'
				}
			},
			yAxis : {
				type : 'value',
				axisLine : {
					lineStyles : {
						color : "#FFFFFF"
					}
				},
				axisLabel : {
					formatter : '{value}个'
				}
			},
			series : [
					{
						name : "可疑工单",
						type : 'line',
						lineStyle : {
							normal : {
								color : '#DC143C'
							}
						},
						smooth : true,
						showSymbol : true,
						data : [ 1, 5, 21, 2, 3, 4, 15, 6, 2, 7, 8, 8, 1, 2, 3,
								4, 25, 6, 7, 6, 12, 2, 4 ]
					},
					{
						name : "日常工单",
						type : 'line',
						lineStyle : {
							normal : {
								color : '#0994E5'
							}
						},
						smooth : true,
						showSymbol : true,
						data : [ 1, 0, 21, 2, 3, 4, 5, 6, 2, 7, 8, 18, 1, 2, 3,
								4, 5, 6, 7, 16, 2, 2, 4 ]
					},
					{
						name : "实时工单",
						type : 'line',
						lineStyle : {
							normal : {
								color : '#FFFF00'
							}
						},
						smooth : true,
						showSymbol : true,
						data : [ 11, 10, 10, 2, 3, 4, 5, 6, 2, 7, 8, 8, 1, 2,
								3, 4, 15, 6, 7, 6, 2, 2, 4 ]
					} ]

		};
		linechart.setOption(option);
		var piechart = echarts.init($("#pie").get(0));
		var option1 = {
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b} : {c} ({d}%)"
			},
			legend : {
				orient : 'vertical',
				left : 'left',
				data : [ '新0流量(2次连续)', '新无线接通率(4次连续)', '新切换出成功率(4次连续)',
						'新无线接通率(4次连续)', '小区间切换出准备请求次数>100' ]
			},
			series : [ {
				name : '访问来源',
				type : 'pie',
				radius : '55%',
				center : [ '50%', '60%' ],
				data : [ {
					value : 335,
					name : '新0流量(2次连续)'
				}, {
					value : 310,
					name : '新无线接通率(4次连续)'
				}, {
					value : 234,
					name : '新切换出成功率(4次连续)'
				}, {
					value : 135,
					name : '新无线接通率(4次连续)'
				}, {
					value : 1548,
					name : '小区间切换出准备请求次数>100'
				} ],
				itemStyle : {
					emphasis : {
						shadowBlur : 10,
						shadowOffsetX : 0,
						shadowColor : 'rgba(0, 0, 0, 0.5)'
					}
				}
			} ]
		};
		piechart.setOption(option1);
	</script>
	<script type="text/javascript">
		// 百度地图API功能
		var map = new BMap.Map("allmap"); // 创建Map实例
		map.centerAndZoom(new BMap.Point(113.304979, 23.186708), 12); // 初始化地图,设置中心点坐标和地图级别
		map.addControl(new BMap.MapTypeControl()); //添加地图类型控件
		map.setCurrentCity("广州"); // 设置地图显示的城市 此项是必须设置的
		map.enableScrollWheelZoom(true); //开启鼠标滚轮缩放
	</script>
	<script type="text/javascript" src="${context}/js/general/heatMap.js"></script>
	<script type="text/javascript">
		var url = "${context}/general/healthgroup";
		var group = [];
		$.ajax({
			url : url,
			type : 'get',
			success : function(data, status) {
				var list = eval('(' + data + ')');
				list = list.rows;
				for (var i = 0; i < list.length; i++) {
					var temp = {};
					temp.lng = 113.27 + Math.random() * 0.1;
					temp.lat = 23.14 + Math.random() * 0.1;
					temp.count = list[i].ratio;
					group.push(temp);
				}
				heatmapOverlay = new BMapLib.HeatmapOverlay({
					"radius" : 20
				});
				map.addOverlay(heatmapOverlay);
				heatmapOverlay.setDataSet({
					data : group,
					max : 100
				});
			}
		});
	</script>

	<script type="text/javascript" src="${context }/js/home.js"></script>
</body>
</html>