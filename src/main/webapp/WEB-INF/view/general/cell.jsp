<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>小区综合页面</title>
<%@ include file="/include/common.jsp"%>
<script type="text/javascript" src="${context }/include/time.js"></script>
<script src="${context}/lib/hplus/js/plugins/layer/laydate/laydate.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=EmXf0NLcNCvBO5hdDliGtvC9D5v6GA5K"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/Heatmap/2.0/src/Heatmap_min.js"></script>

	
<style type="text/css">
	td {
		margin-left: 10px;
		margin-right: 10px
	}
	input{
	    padding-top: 5px;padding-bottom: 5px;margin-top: 0px;margin-bottom: 0px
	}
</style>
</head>
<body>
	<script type="text/javascript">
		var cellname = '${cellname}';
		var stationname = '${stationname}';
		var coverscene = '${coverscene}';
		var usedband = '${usedband}';
	</script>
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row" style="text-align: center;">
			<h1 style="margin: 0 auto;">
				<b>${cellname}</b>小区日常监控
			</h1>
			<div>
				<h1 id="h_ratio" style="color: green;display: none;">
					<b id="b_ratio">90</b>
					
				</h1>
				<h3 id="h3_ratio" style="display: none;">健康度</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-6">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>指标异常预警</h5>
						<div class="ibox-tools">
							<a href="#"><i>更多...</i></a>
						</div>
					</div>
					<div class="ibox-content">
					<div class="jqGrid_wrapper"
							style="margin: 0; padding: 0; width: 100%; overflow: auto;">
							<table class="table" id="alarm_table"></table>
							<div id="pager_alarm_table"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>小区基本情况</h5>
					</div>
					<div class="ibox-content">
						<div style="height: 350px;">
							<div id="allmap"
								style="text-align: center; height: 85%; width: 100%"></div>
							<table style="text-align: center; margin: 0 auto;">
								<tr>
									<td><span style="text-align: center;"><h4>小区名称：</h4></span></td>
									<td><span style="text-align: center;"><h5>${cellname}</h5></span></td>
									<td><span style="text-align: center;"><h4>&nbsp;&nbsp;覆盖场景：</h4></span></td>
									<td><span style="text-align: center;"><h5>${coverscene}</h5></span></td>
								</tr>
								<tr>
									<td><span style="text-align: center;"><h4>基站名称：</h4></span></td>
									<td><span style="text-align: center;"><h5>${stationname}</h5></span></td>
									<td><span style="text-align: center;"><h4>&nbsp;&nbsp;用户频段：</h4></span></td>
									<td><span style="text-align: center;"><h5>${usedband}</h5></span></td>

								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox-title">
					<h5>小区健康度实时曲线</h5>
					<div class="ibox-tools">						
					</div>
				</div>
				<div class="ibox-content">
					<div id="rtratio" style="height: 300px;"></div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="tabs-container">
					<ul id="group_index" class="nav nav-tabs">
					
					</ul>
					<div class="tab-content">
						<div class="tab-pane active">
							<div class="panel-body">
								<div id="mb" style="height: 350px"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row" style="display: none;">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>指标权重</h5>
						<div class="ibox-tools"></div>
					</div>
					<div class="ibox-content">
						<div class="jqGrid_wrapper"
							style="margin: 0; padding: 0; width: 100%; overflow: auto;">
							<table class="table" id="table_list_weight"></table>
							<div id="pager_list_weight"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>其他指标</h5>
						<div class="ibox-tools"></div>
					</div>
					<div class="ibox-content">
						<table class="table">
							<tbody>
								<tr>
									<td>
										<button type="button" class="btn btn-danger m-r-sm">12</button>
										MRO覆盖率大于等于负110DBM
									</td>
									<td>
										<button type="button" class="btn btn-primary m-r-sm">28</button>
										服务小区电平(MRO)
									</td>
									<td>
										<button type="button" class="btn btn-info m-r-sm">15</button>
										SINR平均值
									</td>
								</tr>
								<tr>
									<td>
										<button type="button" class="btn btn-info m-r-sm">20</button>
										UE发射功率余量平均值
									</td>
									<td>
										<button type="button" class="btn btn-success m-r-sm">40</button>
										上行丢包率QCI1
									</td>
									<td>
										<button type="button" class="btn btn-danger m-r-sm">30</button>
										下行丢包率QCI1
									</td>
								</tr>
								<tr>
									<td>
										<button type="button" class="btn btn-warning m-r-sm">20</button>
										...
									</td>
									<td>
										<button type="button" class="btn btn-default m-r-sm">40</button>
										...
									</td>
									<td>
										<button type="button" class="btn btn-warning m-r-sm">30</button>
										...
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>

		<script type="text/javascript">
			var capacityweekurl = ctx + "/capacitywork/oneweek";
			var capacitymonthurl = ctx + "/capacitywork/onemonth";
			var deviceurl = ctx + "/devicework/getlist";
			var deviceweekurl = ctx + "/devicework/oneweek";
			var devicemonthurl = ctx + "/devicework/onemonth";
			var complainturl = ctx + "/complain/getcelllist";
			var outservice = ctx + "/outserverwork/getlist";
			var outserviceweek = ctx + "/outserverwork/oneweek";
			var outservicemonth = ctx + "/outserverwork/onemonth";
			var indexurl = ctx;
		</script>
		<div class="row">
			<div class="ibox-title">
					<h5>工单信息</h5>
					<div class="ibox-tools">
						<div class="btn-group">
							<button class="btn btn-info" id="workinweek" type="button" onclick="javascript:workoneweek()">一周</button>
							<button class="btn btn-white" id="workinmonth" type="button" onclick="javascript:workonemonth()">一月</button>
							<button class="btn btn-white" id="workinselect" type="button" onclick="javascript:worktimeselect()">按时间选择</button>
							<div id="worktimeselect" style="display: none;">
								<input id="start" style="margin-left:5px;margin-top: -7px !important;"
									class="layer-date" placeholder="请输入开始时间"
									onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
								<span id="span" style="margin-top: -10px ;display: inline !important;"
									class="input-group-addon">到</span> 
								<input id="end"
									style="margin-top: -7px !important;" class="layer-date"
									placeholder="请输入结束时间"
									onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
								<button class="btn btn-info" type="button" onclick="javascript:query2()">确定</button>
							</div>
						</div>
					</div>
			</div>
			<div class="col-sm-12">
				<div class="tabs-container">
					<ul class="nav nav-tabs">
						<li
							onclick="switchwork('/sdas/capacitywork/oneweek','${cellname}')"
							class="active"><a data-toggle="tab" href="#tab-1"
							aria-expanded="true">性能工单</a></li>
						<li onclick="switchwork('/sdas/devicework/oneweek','${cellname}')"
							class=""><a data-toggle="tab" href="#tab-2"
							aria-expanded="false">设备工单</a></li>
						<li
							onclick="switchwork('/sdas/outserverwork/oneweek','${cellname}')"
							class=""><a data-toggle="tab" href="#tab-3"
							aria-expanded="false">退服工单</a></li>
						<li onclick="switchwork('/sdas/cellindex/mrinfo','${cellname}')"
							class=""><a data-toggle="tab" href="#tab-4"
							aria-expanded="false">指标信息</a></li>
					</ul>
					<div class="tab-content">
						<div id="tab-1" class="tab-pane active">
							<div class="panel-body">
								<div class="jqGrid_wrapper">
									<table class="table" id="table_list_work"></table>
									<div id="pager_list_work"></div>
								</div>
							</div>
						</div>
						<div id="tab-2" class="tab-pane">
							<div class="panel-body">
								<table class="table" id="table_list_work2"></table>
								<div id="pager_list_work2"></div>
							</div>
						</div>
						<div id="tab-3" class="tab-pane">
							<div class="panel-body">
								<table class="table" id="table_list_work3"></table>
								<div id="pager_list_work3"></div>
							</div>
						</div>
						<div id="tab-4" class="tab-pane">
							<div class="panel-body">
								<table class="table" id="table_list_index"></table>
								<div id="pager_list_index"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<a href="${context}/complain/page"><h5>投诉信息</h5></a>
						<div class="ibox-tools"></div>
					</div>
					<div class="ibox-content">
						<div class="jqGrid_wrapper">
							<table class="table" id="table_list_complain"></table>
							<div id="pager_list_complain"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox-title">
					<h5>小区健康度历史趋势</h5>
					<div class="ibox-tools">
						<div class="btn-group">
							<button class="btn btn-info" id="trendinweek" type="button" onclick="javascript:oneweek()">一周</button>
							<button class="btn btn-white" id="trendinmonth" type="button" onclick="javascript:onemonth()">一月</button>
							<button class="btn btn-white" id="trendinselect" type="button" onclick="javascript:timeselect()">按时间选择</button>
							<div id="timeselect" style="display: none;">
								<input id="starttime" style="margin-left:5px;margin-top: -7px !important;"
									class="layer-date" placeholder="请输入开始时间"
									onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
								<span id="span" style="margin-top: -10px ;display: inline !important;"
									class="input-group-addon">到</span> 
								<input id="endtime"
									style="margin-top: -7px !important;" class="layer-date"
									placeholder="请输入结束时间"
									onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
								<button class="btn btn-info" type="button" onclick="javascript:query()">确定</button>
							</div>
						</div>
					</div>
				</div>
				<div class="ibox-content">
					<div id="ratiotrend" style="height: 300px;"></div>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		// 百度地图API功能
		var map = new BMap.Map("allmap"); // 创建Map实例
		map.centerAndZoom(new BMap.Point(113.270856, 23.137463), 15); // 初始化地图,设置中心点坐标和地图级别
		map.addControl(new BMap.MapTypeControl()); //添加地图类型控件
		map.setCurrentCity("广州"); // 设置地图显示的城市 此项是必须设置的
		map.enableScrollWheelZoom(true); //开启鼠标滚轮缩放
		var marker = new BMap.Marker(new BMap.Point(113.270856, 23.137463));
		map.addOverlay(marker);
		
	</script>
	<script type="text/javascript" src="${context}/js/general/heatMap.js"></script>	


	<script type="text/javascript">
		var chart_mb = echarts.init($("#mb").get(0));
		var rtratio = echarts.init($("#rtratio").get(0));
		var ratiotrend = echarts.init($("#ratiotrend").get(0));
	</script>

    <script type="text/javascript" src="${context}/js/general/cell.js"></script>
</body>
</html>