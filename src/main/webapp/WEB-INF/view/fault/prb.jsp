<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="${context}/lib/hplus/js/plugins/layer/laydate/laydate.js"></script>
<style type="text/css">
.tab-content>.tab-pane, .pill-content>.pill-pane {
	display: block; /* undo display:none */
	height: 0; /* height:0 is also invisible */
	overflow-y: hidden; /* no-overflow */
}

.tab-content>.active, .pill-content>.active {
	height: auto; /* let the content decide it */
} /* bootstrap hack end */
</style>
</head>
<body>
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="tabs-container">
			<ul class="nav nav-tabs" id="topTabs">
				<li class="active"><a href="#tab-11" data-toggle="tab"
					aria-expanded="true">新PRB利用率(4次连续)</a></li>
				<li class=""><a href="#tab-12" data-toggle="tab"
					aria-expanded="false">新切换出成功率(4次连续)</a></li>
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
												<div class="lazur-bg p-xl"
													style="padding: 5px; border-radius: 8px;">
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
													<div class="jqGrid_wrapper"
														style="margin: 0; padding: 0; height:300px;width: 100%; overflow: auto;">
														<table class="table" id="alarm_table"></table>
														<div id="pager_alarm_table"></div>
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
										<h5>关键指标</h5>
										<div class="ibox-tools">
											<div class="btn-group" id="datePicker">
												<button class="btn btn-info datePicker" type="button">今日</button>
												<button class="btn btn-white datePicker" type="button">一周</button>
												<button class="btn btn-white datePicker" type="button">一月</button>
												<button class="btn btn-white datePicker" type="button">按时间选择</button>
												<div id="timeselect" style="display: none; float: left;">
													<input
														style="margin-left: 5px; margin-top: -7px !important;"
														id="starttime_prb" class="layer-date starttime"
														placeholder="请输入开始时间"
														onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
													<span id="span"
														style="margin-top: -10px; display: inline !important;"
														class="input-group-addon">到</span> <input
														style="margin-top: -7px !important;"
														class="layer-date endtime" id="endtime_prb"
														placeholder="请输入结束时间"
														onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
													<button class="btn btn-info search" type="button"
														onclick="query('prb')">确定</button>
												</div>
											</div>
										</div>
									</div>
									<div class="ibox-content">
										<div class="tabs-container">
											<ul class="nav nav-tabs" id="tab">

												<li class="active"><a href="#tab-1" data-toggle="tab"
													aria-expanded="true">关联指标对比</a></li>
												<li class=""><a href="#tab-2" data-toggle="tab"
													aria-expanded="false">用户面最大激活UE数</a></li>
												<li class=""><a href="#tab-3" data-toggle="tab"
													aria-expanded="false">PUCCH SR 资源使用量</a></li>
												<li class=""><a href="#tab-4" data-toggle="tab"
													aria-expanded="true">CCE聚合度为2的次数</a></li>
												<li class=""><a href="#tab-5" data-toggle="tab"
													aria-expanded="true">小区载频PUSCH实际使用PRB个数</a></li>
												<li class=""><a href="#tab-6" data-toggle="tab"
													aria-expanded="true">小区上行信道实际使用PRB个数</a></li>

											</ul>
											<div class="tab-content">
												<div id="tab-1" class="tab-pane active">
													<div class="panel-body">
														<div id="rrc" style="width: 850px; height: 400px"></div>
														<div id="up_prb_rate" style="width: 850px; height: 400px"></div>
														<div id="down_prb_rate"
															style="width: 850px; height: 400px"></div>
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
										<div class="ibox-tools">
											<div class="btn-group">
												<button class="btn btn-white workPicker" type="button">今日</button>
												<button class="btn btn-white workPicker" type="button">一周</button>
												<button class="btn btn-info workPicker" type="button">一月</button>
												<button class="btn btn-white workPicker" type="button">按时间选择</button>
												<div id="timeselect_work_switch"
													style="display: none; float: left;">
													<input
														style="margin-left: 5px; margin-top: -7px !important;"
														id="starttime_work" class="layer-date"
														placeholder="请输入开始时间"
														onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
													<span id="span_work"
														style="margin-top: -10px; display: inline !important;"
														class="input-group-addon">到</span> <input
														style="margin-top: -7px !important;" class="layer-date"
														id="endtime_work" placeholder="请输入结束时间"
														onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
													<button class="btn btn-info search" type="button"
														onclick="query_work('')">确定</button>
												</div>
											</div>
										</div>
									</div>
									<div class="ibox-content">
										<div class="jqGrid_wrapper"
											style="margin: 0; padding: 0; width: 100%; height:400px overflow: auto;">
											<div class="table" id="work_table"></div>
											<div id="pager_work_table"></div>
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
													<div class="jqGrid_wrapper"
														style="margin: 0; padding: 0; height:300px;width: 100%; overflow: auto;">
														<table class="table" id="alarm_table_switch"></table>
														<div id="pager_alarm_table_switch"></div>
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
										<h5>关键指标</h5>
										<div class="ibox-tools">
											<div class="btn-group">
												<button class="btn btn-info datePicker" type="button">今日</button>
												<button class="btn btn-white datePicker" type="button">一周</button>
												<button class="btn btn-white datePicker" type="button">一月</button>
												<button class="btn btn-white datePicker" type="button">按时间选择</button>
												<div id="timeselect_switch"
													style="display: none; float: left;">
													<input
														style="margin-left: 5px; margin-top: -7px !important;"
														id="starttime_switch" class="layer-date"
														placeholder="请输入开始时间"
														onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
													<span id="span_switch"
														style="margin-top: -10px; display: inline !important;"
														class="input-group-addon">到</span> <input
														style="margin-top: -7px !important;" class="layer-date"
														id="endtime_switch" placeholder="请输入结束时间"
														onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
													<button class="btn btn-info search" type="button"
														onclick="query('switch')">确定</button>
												</div>
											</div>
										</div>
									</div>
									<div class="ibox-content">
										<div class="tabs-container">
											<ul class="nav nav-tabs" id="tab_switch">
												<li class="active"><a href="#tab-2-1" data-toggle="tab"
													aria-expanded="true">关联指标对比</a></li>
												<li class=""><a href="#tab-2-2" data-toggle="tab"
													aria-expanded="false">YY-RRC连接建立成功率</a></li>
												<li class=""><a href="#tab-2-3" data-toggle="tab"
													aria-expanded="false">YY-无线接通率</a></li>
											</ul>
											<div class="tab-content">
												<div id="tab-2-1" class="tab-pane active">
													<div class="panel-body">
														<div id="switch_mon" style="width: 850px; height: 400px"></div>
														<div id="switch_success_rate"
															style="width: 850px; height: 400px"></div>
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
										<div class="ibox-tools">
											<div class="btn-group">
												<button class="btn btn-white workPicker" type="button">今日</button>
												<button class="btn btn-white workPicker" type="button">一周</button>
												<button class="btn btn-info workPicker" type="button">一月</button>
												<button class="btn btn-white workPicker" type="button">按时间选择</button>
												<div id="timeselect_work_switch"
													style="display: none; float: left;">
													<input
														style="margin-left: 5px; margin-top: -7px !important;"
														id="starttime_work_switch" class="layer-date"
														placeholder="请输入开始时间"
														onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
													<span id="span_work_switch"
														style="margin-top: -10px; display: inline !important;"
														class="input-group-addon">到</span> <input
														style="margin-top: -7px !important;" class="layer-date"
														id="endtime_work_switch" placeholder="请输入结束时间"
														onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
													<button class="btn btn-info search" type="button"
														onclick="query_work('work_switch')">确定</button>
												</div>
											</div>
										</div>
									</div>
									<div class="ibox-content">
										<div class="jqGrid_wrapper"
											style="margin: 0; padding: 0; width: 100%; height:400px overflow: auto;">
											<div class="table" id="work_table_switch"></div>
											<div id="pager_work_table_switch"></div>
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
	<script type="text/javascript">
		var cellname = '${cellname}';
	</script>

	<script type="text/javascript" src="${context}/js/fault/prb.js"></script>
</body>
</html>