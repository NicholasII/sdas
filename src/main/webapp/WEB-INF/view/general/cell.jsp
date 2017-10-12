<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>小区综合页面</title>
<%@ include file="/include/common.jsp"%>
<script type="text/javascript" src="${context }/include/time.js"></script>
<script src="${context}/lib/hplus/js/plugins/layer/laydate/laydate.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=EmXf0NLcNCvBO5hdDliGtvC9D5v6GA5K"></script>
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
	</script>
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row" style="text-align: center;">
			<h1 style="margin: 0 auto;">
				<b>${cellname}</b>小区日常监控
			</h1>
			<div>
				<h1 style="color: green;">
					<b>90</b>
				</h1>
				<h3>健康度</h3>
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
						<div class="jqGrid_wrapper">
							<table class="table">
								<thead>
									<tr>
										<th>时间</th>
										<th>指标值</th>
									</tr>
								</thead>
								<tbody id="table">
									<tr style="color: red;">
										<td>2017-08-26 20:00</td>
										<td>[YY-无线接通率]=20%</td>
									</tr>
									<tr>
										<td>2017-08-26 19:15</td>
										<td>[上行PRB利用率] = 64.44,[下行PRB利用率] = 61.95</td>
									</tr>
									<tr>
										<td>2017-08-26 15:00</td>
										<td>[RRC连接最大数] = 215.00</td>
									</tr>
									<tr>
										<td>2017-08-26 20:00</td>
										<td>[YY-无线接通率]=20%</td>
									</tr>
									<tr>
										<td>2017-08-26 19:15</td>
										<td>[上行PRB利用率] = 64.44,[下行PRB利用率] = 61.95</td>
									</tr>
									<tr>
										<td>2017-08-26 15:00</td>
										<td>[RRC连接最大数] = 215.00</td>
									</tr>
								</tbody>
							</table>
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
									<td><span style="text-align: center;"><h4>
												<em>小区名称：</em>
											</h4></span></td>
									<td><span style="text-align: center;"><h5>${cellname}</h5></span></td>
									<td><span style="text-align: center;"><h4>
												&nbsp;&nbsp;<em>覆盖场景：</em>
											</h4></span></td>
									<td><span style="text-align: center;"><h5>城区道路</h5></span></td>
								</tr>
								<tr>
									<td><span style="text-align: center;"><h4>
												<em>基站名称：</em>
											</h4></span></td>
									<td><span style="text-align: center;"><h5>${stationname}</h5></span></td>
									<td><span style="text-align: center;"><h4>
												&nbsp;&nbsp;<em>用户频段：</em>
											</h4></span></td>
									<td><span style="text-align: center;"><h5>F频段</h5></span></td>

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
					<h5>小区健康度历史趋势</h5>
					<div class="ibox-tools">
						<div class="btn-group">
							<button class="btn btn-info" id="trendinweek" type="button">一周</button>
							<button class="btn btn-white" id="trendinmonth" type="button">一月</button>
							<button class="btn btn-white" id="trendinselect" type="button">按时间选择</button>
							<div id="timeselect" style="display: none;">
								<input id="starttime" style="margin-top: -3px !important;"
									class="layer-date" placeholder="请输入开始时间"
									onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
								<span id="span" style="margin-top: -10px ;display: inline !important;"
									class="input-group-addon">到</span> 
								<input id="endtime"
									style="margin-top: -3px !important;" class="layer-date"
									placeholder="请输入结束时间"
									onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
							</div>
						</div>
					</div>
				</div>
				<div class="ibox-content">
					<div id="ratiotrend" style="height: 300px;"></div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="tabs-container">
					<ul id="group_index" class="nav nav-tabs">
						<!-- <li onclick="switchindex('14')" class="active"><a
							data-toggle="tab" href="#tab-1" aria-expanded="true">MR-总流量（KB）</a></li>
						<li onclick="switchindex('17')" class=""><a data-toggle="tab"
							href="#tab-2" aria-expanded="false">YY-RRC连接建立成功率</a></li>
						<li onclick="switchindex('20')" class=""><a data-toggle="tab"
							href="#tab-3" aria-expanded="false">YY-ERAB建立成功率</a></li>
						<li onclick="switchindex('21')" class=""><a data-toggle="tab"
							href="#tab-4" aria-expanded="false">YY-无线掉线率</a></li>
						<li onclick="switchindex('24')" class=""><a data-toggle="tab"
							href="#tab-5" aria-expanded="false">YY-切换成功率</a></li>
						<li onclick="switchindex('27')" class=""><a data-toggle="tab"
							href="#tab-6" aria-expanded="false">YY-无线接通率</a></li>
						<li onclick="switchindex('32')" class=""><a data-toggle="tab"
							href="#tab-7" aria-expanded="false">用户面最大激活UE数</a></li>
						<li onclick="switchindex('48')" class=""><a data-toggle="tab"
							href="#tab-8" aria-expanded="false">小区载频PUSCH实际使用PRB个数</a></li> -->
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
			var capacityurl = ctx + "/capacitywork/gettable";
			var deviceurl = ctx + "/devicework/getlist";
			var complainturl = ctx + "/complain/getcelllist";
			var outservice = ctx + "/outserverwork/getlist";
			var indexurl = ctx;
		</script>
		<div class="row">
			<div class="col-sm-12">
				<div class="tabs-container">
					<ul class="nav nav-tabs">
						<li
							onclick="switchwork('/sdas/capacitywork/gettable','${cellname}')"
							class="active"><a data-toggle="tab" href="#tab-1"
							aria-expanded="true">性能工单</a></li>
						<li onclick="switchwork('/sdas/devicework/getlist','${cellname}')"
							class=""><a data-toggle="tab" href="#tab-2"
							aria-expanded="false">设备工单</a></li>
						<li
							onclick="switchwork('/sdas/outserverwork/getlist','${cellname}')"
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

	</div>
	<script type="text/javascript">
		// 百度地图API功能
		var map = new BMap.Map("allmap"); // 创建Map实例
		map.centerAndZoom(new BMap.Point(113.270856, 23.137463), 13); // 初始化地图,设置中心点坐标和地图级别
		map.addControl(new BMap.MapTypeControl()); //添加地图类型控件
		map.setCurrentCity("广州"); // 设置地图显示的城市 此项是必须设置的
		map.enableScrollWheelZoom(true); //开启鼠标滚轮缩放
		var marker = new BMap.Marker(new BMap.Point(113.270856, 23.137463));
		map.addOverlay(marker);
	</script>


	<script type="text/javascript">
		var chart_mb = echarts.init($("#mb").get(0));
		/* var chart_yyttc = echarts.init($("#yyttc").get(0));
		var chart_yyerab = echarts.init($("#yyerab").get(0));
		var chart_yylostconn = echarts.init($("#yylostconn").get(0));
		var chart_yyswitch = echarts.init($("#yyswitch").get(0));
		var chart_yyconn = echarts.init($("#yyconn").get(0));
		var chart_mastue = echarts.init($("#mastue").get(0));
		var chart_prb = echarts.init($("#prb").get(0)); */
		var ratiotrend = echarts.init($("#ratiotrend").get(0));
	</script>

	<script type="text/javascript" src="${context}/js/general/cell.js"></script>
</body>
</html>