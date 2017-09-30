<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="tabs-container">
			<ul class="nav nav-tabs">
				<li class="active"><a href="#tab-11" data-toggle="tab"
					aria-expanded="true">新PRB利用率(4次连续)</a></li>
				<li onclick="switchs()" class=""><a href="#tab-12"
					data-toggle="tab" aria-expanded="false">新切换出成功率(4次连续)</a></li>
			</ul>
		</div>
		<div class="tab-content">
			<div id="tab-11" class="tab-pane active">
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-12"
							style="padding-left: 5px; padding-right: 5px;">
							<div class="ibox float-e-margins"
								style="padding-left: 0px; padding-right: 0px;">
								<div class="ibox-title">
									<h5>${cellname}</h5>
								</div>
								<div class="ibox-content" style="padding-top: 30px;">
									<div class="row">
										<div class="col-sm-2">
											<div class="lazur-bg p-xl" style="padding: 5px;">
												<h3>
													<b>判断规则</b>
												</h3>
												<span style="display: block; font-size: 15px;">新PRB利用率(4次连续)告警监控门限：“连续4个15分钟上行PRB利用率>60%，RRC连接最大数>200或下行PRB利用率>60%，RRC连接最大数>200”</span>
											</div>
										</div>
										<div class="col-sm-10">
											<div>
												<h3 style="float: left;">PRB指标异常预警</h3>
											</div>
											<div class="ibox-content">
												<table class="table">
													<thead>
														<tr>
															<th>时间</th>
															<th>小区名称</th>
															<th>指标值</th>
														</tr>
													</thead>
													<tbody id="table1">
														<tr>
															<td>2017-08-26 20:00</td>
															<td>${cellname}</td>
															<td>[上行PRB利用率] = 70.5</td>
														</tr>
														<tr>
															<td>2017-08-26 19:15</td>
															<td>${cellname}</td>
															<td>[下行PRB利用率] = 61.95</td>
														</tr>
														<tr>
															<td>2017-08-26 15:00</td>
															<td>${cellname}</td>
															<td>[RRC连接最大数] = 215.00</td>
														</tr>
													</tbody>
												</table>
											</div>
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
									<h5>关键指标</h5>
									<div class="ibox-tools"></div>
								</div>
								<div class="ibox-content">
									<div class="tabs-container">
										<ul class="nav nav-tabs">

											<li class="active"><a href="#tab-1" data-toggle="tab"
												aria-expanded="true">关联指标对比</a></li>
											<li class=""><a href="#tab-2" data-toggle="tab"
												aria-expanded="false">上行PRB利用率</a></li>
											<li class=""><a href="#tab-3" data-toggle="tab"
												aria-expanded="false">下行PRB利用率</a></li>
											<li class=""><a href="#tab-4" data-toggle="tab"
												aria-expanded="true">MR-RRC连接建立最大用户数</a></li>

										</ul>
										<div class="tab-content">
											<div id="tab-1" class="tab-pane active">
												<div class="panel-body">
													<div id="relate_index" style="height: 400px"></div>
												</div>
											</div>
											<div id="tab-2" class="tab-pane active">
												<div class="panel-body">
													<div id="up_prb" style="height: 300px"></div>
												</div>
											</div>
											<div id="tab-3" class="tab-pane active">
												<div class="panel-body">
													<div id="down_prb" style="height: 300px"></div>
												</div>
											</div>
											<div id="tab-4" class="tab-pane active">
												<div class="panel-body">
													<div id="rrc" style="height: 300px"></div>
												</div>
											</div>
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
				</div>
			</div>
			<div id="tab-12" class="tab-pane">
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-12"
							style="padding-left: 5px; padding-right: 5px;">
							<div class="ibox float-e-margins"
								style="padding-left: 0px; padding-right: 0px;">
								<div class="ibox-title">
									<h5>${cellname}</h5>
								</div>
								<div class="ibox-content" style="padding-top: 30px;">
									<div class="row">
										<div class="col-sm-2">
											<div class="lazur-bg p-xl" style="padding: 5px;">
												<h3>
													<b>判断规则</b>
												</h3>
												<span style="display: block; font-size: 15px;">新切换出成功率(4次连续)告警监控门限：“小区间切换出准备请求次数>100,且切换出成功率≤80%”或“小区间切换出准备请求次数>100,80%≤切换出成功率≤90%;”</span>
											</div>
										</div>
										<div class="col-sm-10">
											<div>
												<h3 style="float: left;">切换出成功率异常预警</h3>
											</div>
											<div class="ibox-content">
												<table class="table">
													<thead>
														<tr>
															<th>时间</th>
															<th>小区名称</th>
															<th>指标值</th>
														</tr>
													</thead>
													<tbody id="table1">
														<tr>
															<td>2017-08-26 20:00</td>
															<td>${cellname}</td>
															<td>[小区间切换出准备请求次数] = 170 [切换出成功率] = 70%</td>
														</tr>
														<tr>
															<td>2017-08-26 19:15</td>
															<td>${cellname}</td>
															<td>[小区间切换出准备请求次数] = 190 [切换出成功率] = 60%</td>
														</tr>
														<tr>
															<td>2017-08-26 15:00</td>
															<td>${cellname}</td>
															<td>[小区间切换出准备请求次数] = 270 [切换出成功率] = 70%</td>
														</tr>
													</tbody>
												</table>
											</div>
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
									<h5>关键指标</h5>
									<div class="ibox-tools"></div>
								</div>
								<div class="ibox-content">
									<div class="tabs-container">
										<ul class="nav nav-tabs">
											<li class="active"><a href="#tab-1" data-toggle="tab"
												aria-expanded="true">关联指标对比</a></li>
											<li class=""><a href="#tab-2" data-toggle="tab"
												aria-expanded="false">小区间切换出准备请求次数</a></li>
											<li class=""><a href="#tab-3" data-toggle="tab"
												aria-expanded="false">切换出成功率</a></li>
										</ul>
										<div class="tab-content">
											<div id="tab-1" class="tab-pane active">
												<div class="panel-body">
													<div id="switch_relate" style="height: 300px"></div>

												</div>
											</div>
											<div id="tab-2" class="tab-pane active">
												<div class="panel-body">
													<div id="switch_count" style="height: 300px"></div>
												</div>
											</div>
											<div id="tab-3" class="tab-pane active">
												<div class="panel-body">
													<div id="switch_success" style="height: 300px"></div>
												</div>
											</div>
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
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
			var up_prb = echarts.init($("#up_prb").get(0));
			var down_prb = echarts.init($("#down_prb").get(0));
			var relate_index = echarts.init($("#relate_index").get(0));
			var rrc = echarts.init($("#rrc").get(0));
			var switchsucc = echarts.init($("#switch_success").get(0));
			var switchcount = echarts.init($("#switch_count").get(0));
			var switchrelate = echarts.init($("#switch_relate").get(0));
		</script>
	<script type="text/javascript" src="${context}/js/fault/prb.js"></script>
</body>
</html>