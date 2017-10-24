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
								<table id="table_list_alarm"></table>
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
							<a href="javascript:iframeconvert('/sdas/indexalarm/','小区指标异常预警')"><h5>小区指标异常预警</h5></a>
							<div style="float: right;" class="ibox-tools">
								<select><option>全部</option>
									<option>PRB利用率</option>
									<option>切换出成功率</option>
								</select>
							</div>
						</div>
						<div class="ibox-content">
							<div class="jqGrid_wrapper">
								<table id="table_list_alarm_index"></table>
								<div id="pager_list_alarm_index"></div>
							</div>
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
								<table id="table_list_work"></table>
								<div id="pager_list_work"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
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

	<script type="text/javascript" src="${context}/js/home.js"></script>
</body>
</html>