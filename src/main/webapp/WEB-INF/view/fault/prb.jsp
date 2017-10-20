<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.tab-content > .tab-pane,
.pill-content > .pill-pane {
display: block; /* undo display:none */
height: 0; /* height:0 is also invisible */
overflow-y: hidden; /* no-overflow */
}
.tab-content > .active,
.pill-content > .active {
height: auto; /* let the content decide it */
} /* bootstrap hack end */
</style>
</head>
<body>
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="tabs-container">
			<ul class="nav nav-tabs">
				<li class="active"><a href="#tab-11" data-toggle="tab"
					aria-expanded="true">新PRB利用率(4次连续)</a></li>
				<li class="" onclick="switchCharts()"><a href="#tab-12"
					data-toggle="tab" aria-expanded="false">新切换出成功率(4次连续)</a></li>
			</ul>
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

											<li class="active" onclick="PRBCharts()"><a
												href="#tab-1" data-toggle="tab" aria-expanded="true">关联指标对比</a></li>
											<li class=""
												onclick='simpleCharts("/sdas/fault/getprbothers","uecounts","#UE","用户面最大激活UE数","#2EC7C9")'>
												<a href="#tab-2" data-toggle="tab" aria-expanded="false">用户面最大激活UE数</a>
											</li>
											<li class=""
												onclick="simpleCharts('/sdas/fault/getprbothers','pucch','#PUCCH','PUCCH SR 资源使用量','#385Ad3')"><a
												href="#tab-3" data-toggle="tab" aria-expanded="false">PUCCH
													SR 资源使用量</a></li>
											<li class=""
												onclick="simpleCharts('/sdas/fault/getprbothers','cce','#CCE','CCE聚合度为2的次数','#823B93')"><a
												href="#tab-4" data-toggle="tab" aria-expanded="true">CCE聚合度为2的次数</a></li>
											<li class=""
												onclick="simpleCharts('/sdas/fault/getprbothers','puschprb','#PUSCH_PRB','小区载频PUSCH实际使用PRB个数','#27727B')"><a
												href="#tab-5" data-toggle="tab" aria-expanded="true">小区载频PUSCH实际使用PRB个数</a></li>
											<li class=""
												onclick="simpleCharts('/sdas/fault/getprbothers','uprealprb','#PRB_count','小区上行信道实际使用PRB个数','#9BCA63')"><a
												href="#tab-6" data-toggle="tab" aria-expanded="true">小区上行信道实际使用PRB个数</a></li>

										</ul>
										<div class="tab-content">
											<div id="tab-1" class="tab-pane active">
												<div class="panel-body">
													<div id="rrc" style="width:850px;height: 400px"></div>
													<div id="up_prb_rate" style="width:850px;height: 400px"></div>
													<div id="down_prb_rate" style="width:850px;height: 400px"></div>
												</div>
											</div>
											<div id="tab-2" class="tab-pane">
												<div class="panel-body">
													<div id="UE" style="height: 300px"></div>
												</div>
											</div>
											<div id="tab-3" class="tab-pane">
												<div class="panel-body">
													<div id="PUCCH" style="height: 300px"></div>
												</div>
											</div>
											<div id="tab-4" class="tab-pane">
												<div class="panel-body">
													<div id="CCE" style="height: 300px"></div>
												</div>
											</div>
											<div id="tab-5" class="tab-pane">
												<div class="panel-body">
													<div id="PUSCH_PRB" style="height: 300px"></div>
												</div>
											</div>
											<div id="tab-6" class="tab-pane">
												<div class="panel-body">
													<div id="PRB_count" style="height: 300px"></div>
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
											<li class="active" onclick="switchCharts()"><a
												href="#tab-2-1" data-toggle="tab" aria-expanded="true">关联指标对比</a></li>
											<li class=""
												onclick="simpleCharts('/sdas/fault/getswitchothers','yyrrc','#YY-RRC_rate','YY-RRC连接建立成功率','#9BCA63')"><a
												href="#tab-2-2" data-toggle="tab" aria-expanded="false">YY-RRC连接建立成功率</a></li>
											<li class=""
												onclick="simpleCharts('/sdas/fault/getswitchothers','yywire','#yywire_rate','YY-无线接通率','#385Ad3')"><a
												href="#tab-2-3" data-toggle="tab" aria-expanded="false">YY-无线接通率</a></li>
										</ul>
										<div class="tab-content">
											<div id="tab-2-1" class="tab-pane active">
													<div class="panel-body">
													<div id="switch_mon" style="width:850px;height: 400px"></div>
													<div id="switch_success_rate" style="width:850px;height: 400px"></div>
												</div>
											</div>
											<div id="tab-2-2" class="tab-pane">
												<div class="panel-body">
													<div id="YY-RRC_rate" style="height: 300px"></div>
												</div>
											</div>
											<div id="tab-2-3" class="tab-pane">
												<div class="panel-body">
													<div id="yywire_rate" style="height: 300px"></div>
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
		
	</div>
	<script type="text/javascript" src="${context}/js/fault/prb.js"></script>
</body>
</html>