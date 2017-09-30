<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>小区综合页面</title>
<%@ include file="/include/common.jsp"%>
<script type="text/javascript" src="${context }/include/time.js"></script>

<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=EmXf0NLcNCvBO5hdDliGtvC9D5v6GA5K"></script>
<style type="text/css">
td{
 margin-left: 10px;
 margin-right: 10px
}
</style>
</head>
<body>
    <script type="text/javascript">
     	var cellname = '${cellname}';
    </script>
	<div class="wrapper wrapper-content animated fadeInRight">
	    <div style="text-align: center;"><h1 style="margin: 0 auto;"><em>${cellname}</em>小区日常监控</h1></div>
		<div class="row">
			<div class="col-sm-6">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>预警信息</h5>
						<div class="ibox-tools"><a href="#"><i>更多...</i></a></div>
					</div>
					<div class="ibox-content">
						<div class="jqGrid_wrapper">
							<table class="table">
								<thead>
									<tr>
										<th>时间</th>
										<th>次数</th>
										<th>指标值</th>
									</tr>
								</thead>
								<tbody id="table">
									<tr style="color: red;">
										<td>2017-08-26 20:00</td>
										<td>10</td>
										<td>[YY-无线接通率]=20%</td>
									</tr>
									<tr>
										<td>2017-08-26 19:15</td>
										<td>5</td>
										<td>[上行PRB利用率] = 64.44,[下行PRB利用率] = 61.95</td>
									</tr>
									<tr>
										<td>2017-08-26 15:00</td>
										<td>3</td>
										<td>[RRC连接最大数] = 215.00</td>
									</tr>
									<tr style="color: red;">
										<td>2017-08-26 20:00</td>
										<td>10</td>
										<td>[YY-无线接通率]=20%</td>
									</tr>
									<tr>
										<td>2017-08-26 19:15</td>
										<td>5</td>
										<td>[上行PRB利用率] = 64.44,[下行PRB利用率] = 61.95</td>
									</tr>
									<tr>
										<td>2017-08-26 15:00</td>
										<td>3</td>
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
							<div id="allmap" style="text-align: center;height: 85%; width: 100%"></div>
							<table style="text-align: center;margin: 0 auto;">
								<tr>
									<td><span style="text-align: center;"><h4>
												<em>基站名称：</em>
											</h4></span></td>
									<td><span style="text-align: center;"><h5>广州香江家私F-ZLH</h5></span></td>
									<td><span style="text-align: center;"><h4>
												&nbsp;&nbsp;<em>覆盖场景：</em>
											</h4></span></td>
									<td><span style="text-align: center;"><h5>城区道路</h5></span></td>
								</tr>
								<tr>
								    <td><span style="text-align: center;"><h4>
												<em>基站名称：</em>
											</h4></span></td>
									<td><span style="text-align: center;"><h5>广州香江家私F-ZLH</h5></span></td>
									<td><span style="text-align: center;"><h4>
												&nbsp;&nbsp;<em>覆盖场景：</em>
											</h4></span></td>
									<td><span style="text-align: center;"><h5>城区道路</h5></span></td>
									
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-10">
				<div class="tabs-container">
					<ul class="nav nav-tabs">
						<li onclick="switchindex('14')" class="active"><a data-toggle="tab" href="#tab-1"
							aria-expanded="true">MR-总流量（KB）</a></li>
						<li onclick="switchindex('17')" class=""><a data-toggle="tab" href="#tab-2"
							aria-expanded="false">YY-RRC连接建立成功率</a></li>
						<li onclick="switchindex('20')" class=""><a data-toggle="tab" href="#tab-3"
							aria-expanded="false">YY-ERAB建立成功率</a></li>
						<li onclick="switchindex('21')" class=""><a data-toggle="tab" href="#tab-4"
							aria-expanded="false">YY-无线掉线率</a></li>
						<li onclick="switchindex('24')" class=""><a data-toggle="tab" href="#tab-5"
							aria-expanded="false">YY-切换成功率</a></li>
						<li onclick="switchindex('27')" class=""><a data-toggle="tab" href="#tab-6"
							aria-expanded="false">YY-无线接通率</a></li>
						<li onclick="switchindex('32')" class=""><a data-toggle="tab" href="#tab-7"
							aria-expanded="false">用户面最大激活UE数</a></li>
						<li onclick="switchindex('48')" class=""><a data-toggle="tab" href="#tab-8"
							aria-expanded="false">小区载频PUSCH实际使用PRB个数</a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active">
							<div class="panel-body">
								<div id="mb" style="height: 350px"></div>
							</div>
						</div>
						<!-- <div id="tab-2" class="tab-pane">
							<div class="panel-body">
              					<div id="yyttc" style="height: 350px"></div>
							</div>
						</div>
						<div id="tab-3" class="tab-pane">
							<div class="panel-body">
								<div id="yyerab" style="height: 300px"></div>
							</div>
						</div>
						<div id="tab-4" class="tab-pane">
							<div class="panel-body">
								<div id="yylostconn" style="height: 350px"></div>
							</div>
						</div>
						<div id="tab-5" class="tab-pane">
							<div class="panel-body">
								<div id="yyswitch" style="height: 350px"></div>
							</div>
						</div>
						<div id="tab-6" class="tab-pane">
							<div class="panel-body">
								<div id="yyconn" style="height: 350px"></div>
							</div>
						</div>
						<div id="tab-7" class="tab-pane">
							<div class="panel-body">
								<div id="mastue" style="height: 350px"></div>
							</div>
						</div>
						<div id="tab-8" class="tab-pane">
							<div class="panel-body">
								<div id="prb" style="height: 350px"></div>
							</div>
						</div> -->
						
					</div>
				</div>
			</div>
			<div class="col-sm-2">
				<div class="ibox float-e-margins">
					<div class="ibox-content">
						<div class="jqGrid_wrapper">
							<table class="table">
								<thead>
									<tr>
										<th>指标名称</th>
										<th>权重占比</th>
									</tr>
								</thead>
								<tbody id="table">
									<tr style="color: red;">
										<td>MR-总流量</td>
										<td>10</td>
									</tr>
									<tr>
										<td>YY-RRC连接建立成功率</td>
										<td>5</td>
									
									</tr>
									<tr>
										<td>YY-ERAB建立成功率</td>
										<td>3</td>
									</tr>
									<tr style="color: red;">
										<td>YY-无线接通率</td>
										<td>10</td>
									</tr>
									<tr>
										<td>YY-无线掉线率</td>
										<td>5</td>
									</tr>
									<tr>
										<td>YY-切换成功率</td>
										<td>3</td>
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
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>MR指标</h5>
						<div class="ibox-tools"></div>
					</div>
					<div class="ibox-content">
						
							<div class="col-sm-2">
								<div class="widget style1 navy-bg">
									<div class="row vertical-align">
										<div class="col-xs-3">
											<i class="fa fa-thumbs-up fa-3x"></i>
										</div>
										<div class="col-xs-9 text-right">
											<span>MRO覆盖率≥-110</span>
											<h2 class="font-bold">260</h2>

										</div>
									</div>
								</div>
							</div>
							<div class="col-sm-2">
								<div class="widget style1 navy-bg">
									<div class="row vertical-align">
										<div class="col-xs-3">
											<i class="fa fa-cloud fa-3x"></i>
										</div>
										<div class="col-xs-9 text-right">
											<span>服务小区电平</span>
											<h2 class="font-bold">260</h2>

										</div>
									</div>
								</div>
							</div>
							<div class="col-sm-2">
								<div class="widget style1 navy-bg">
									<div class="row vertical-align">
										<div class="col-xs-3">
											<i class="fa fa-euro fa-3x"></i>
										</div>
										<div class="col-xs-9 text-right">
											<span>SINR平均值</span>
											<h2 class="font-bold">260</h2>

										</div>
									</div>
								</div>
							</div>
				
							<div class="col-sm-2">
								<div class="widget style1 navy-bg">
									<div class="row vertical-align">
										<div class="col-xs-3">
											<i class="fa fa-rss fa-3x"></i>
										</div>
										<div class="col-xs-9 text-right">
											<span>UE发射功率余量</span>
											<h2 class="font-bold">260</h2>


										</div>
									</div>
								</div>
							</div>
							<div class="col-sm-2">
								<div class="widget style1 navy-bg">
									<div class="row vertical-align">
										<div class="col-xs-3">
											<i class="fa fa-shield fa-3x"></i>
										</div>
										<div class="col-xs-9 text-right">
											<span>上行丢包率QCI1</span>
											<h2 class="font-bold">260</h2>

										</div>
									</div>
								</div>
							</div>
							<div class="col-sm-2">
								<div class="widget style1 navy-bg">
									<div class="row vertical-align">
										<div class="col-xs-3">
											<i class="fa fa-shield fa-3x"></i>
										</div>
										<div class="col-xs-9 text-right">
											<span>下行丢包率QCI1</span>
											<h2 class="font-bold">260</h2>
										</div>
									</div>
								</div>
							</div>
					
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-6">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>指标信息</h5>
						<div class="ibox-tools"></div>
					</div>
					<div class="ibox-content">
						<div class="jqGrid_wrapper">
							<table class="table">
								<thead>
									<tr>
										<th>时间</th>
										<th>次数</th>
										<th>指标值</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>2017-08-26 20:00</td>
										<td>10</td>
										<td>[YY-无线接通率]=20%</td>
									</tr>
									<tr>
										<td>2017-08-26 19:15</td>
										<td>5</td>
										<td>[上行PRB利用率] = 64.44,[下行PRB利用率] = 61.95</td>
									</tr>
									<tr>
										<td>2017-08-26 15:00</td>
										<td>3</td>
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
						<h5>性能工单</h5>
						<div class="ibox-tools"></div>
					</div>
					<div class="ibox-content">
						<div class="jqGrid_wrapper">
							<table class="table">
								<thead>
									<tr>
										<th>时间</th>
										<th>次数</th>
										<th>指标值</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>2017-08-26 20:00</td>
										<td>10</td>
										<td>[YY-无线接通率]=20%</td>
									</tr>
									<tr>
										<td>2017-08-26 19:15</td>
										<td>5</td>
										<td>[上行PRB利用率] = 64.44,[下行PRB利用率] = 61.95</td>
									</tr>
									<tr>
										<td>2017-08-26 15:00</td>
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
			<div class="col-sm-6">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>设备工单</h5>
						<div class="ibox-tools"></div>
					</div>
					<div class="ibox-content">
						<div class="jqGrid_wrapper">
							<table class="table">
								<thead>
									<tr>
										<th>时间</th>
										<th>次数</th>
										<th>指标值</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>2017-08-26 20:00</td>
										<td>10</td>
										<td>[YY-无线接通率]=20%</td>
									</tr>
									<tr>
										<td>2017-08-26 19:15</td>
										<td>5</td>
										<td>[上行PRB利用率] = 64.44,[下行PRB利用率] = 61.95</td>
									</tr>
									<tr>
										<td>2017-08-26 15:00</td>
										<td>3</td>
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
						<h5>故障工单</h5>
						<div class="ibox-tools"></div>
					</div>
					<div class="ibox-content">
						<div class="jqGrid_wrapper">
							<table class="table">
								<thead>
									<tr>
										<th>时间</th>
										<th>次数</th>
										<th>指标值</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>2017-08-26 20:00</td>
										<td>10</td>
										<td>[YY-无线接通率]=20%</td>
									</tr>
									<tr>
										<td>2017-08-26 19:15</td>
										<td>5</td>
										<td>[上行PRB利用率] = 64.44,[下行PRB利用率] = 61.95</td>
									</tr>
									<tr>
										<td>2017-08-26 15:00</td>
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
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<a href="${context}/complain/page"><h5>投诉信息</h5></a>
						<div class="ibox-tools"></div>
					</div>
					<div class="ibox-content">
						<div class="jqGrid_wrapper">
							<table class="table" id="table_list_1"></table>
							<div id="pager_list_1"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
	</div>
	<script type="text/javascript">
		// 百度地图API功能
		var map = new BMap.Map("allmap"); // 创建Map实例
		map.centerAndZoom(new BMap.Point(113.270856,23.137463), 11); // 初始化地图,设置中心点坐标和地图级别
		map.addControl(new BMap.MapTypeControl()); //添加地图类型控件
		map.setCurrentCity("广州"); // 设置地图显示的城市 此项是必须设置的
		map.enableScrollWheelZoom(true); //开启鼠标滚轮缩放
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
		

	</script>
	
	<script type="text/javascript" src="${context}/js/general/cell.js"></script>
</body>
</html>