<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/include/common.jsp"%>
<link href="${context}/style/loader.css" rel="stylesheet"
	type="text/css">
<link
	href="${context}/lib/hplus/css/plugins/dataTables/dataTables.bootstrap.css"
	rel="stylesheet">
</head>

<body class="gray-bg">
	<!-- <div id="loader-wrapper">
		<div id="loader"></div>
		<div class="loader-section section-left"></div>
		<div class="loader-section section-right"></div>
		<div class="load_title">正在加载小区数据...</div>
	</div> -->

	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-16">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>小区列表</h5>
						<div class="ibox-tools"></div>
					</div>
					<div class="ibox-content">
						<div class="form-group">
							<label for="name">小区名称<input type="text"
								placeholder="请输入小区名称" id="name" name="name" class="form-control"></label>
							<label for="location">覆盖场景 <select
								class="form-control m-b" id="scene" name="scene">
									<option>全部</option>
							</select></label>
							<button style="margin-left: 5px;" class="btn btn-white"
								onclick="javascript:select()">查询</button>
							<button id="clear" type="reset" class="btn btn-white">清空</button>
						</div>
						<div class="footer" style="height: 60px;">
							<div>
								<span><i>备注：</i> </span> <span>I类分组：投诉工单连续三天有至少三次投诉的小区；
									II类分组：在除去I类分组的基础上，连续三天至少有5次性能工单的小区；
									III类分组：在全部数据集的基础上且除去I类II类小区的基础上，连续一周至少出现两次故障工单的小区
									IV类分组：在I类小组中出现过性能工单的小区； V类分组：其他标记为V类分组</span>
							</div>
						</div>
						<div class="tabs-container">
							<ul class="nav nav-tabs">
								<li onclick="javascript:switchGroup('I')" class="active"><a
									data-toggle="tab" aria-expanded="true">I类小区</a></li>
								<li onclick="javascript:switchGroup('II')" class=""><a
									data-toggle="tab" aria-expanded="false">II类小区</a></li>
								<li onclick="javascript:switchGroup('III')" class=""><a
									data-toggle="tab" aria-expanded="false">III类小区</a></li>
								<li onclick="javascript:switchGroup('IV')" class=""><a
									data-toggle="tab" aria-expanded="false">IV类小区</a></li>
								<li onclick="javascript:switchGroup('V')" class=""><a
									data-toggle="tab" aria-expanded="false">V类小区</a></li>
							</ul>
						</div>
						<div class="jqGrid_wrapper">
							<table class="table" id="table_list_1"></table>
							<div id="pager_list_1"></div>
						</div>
						<div class="ibox float-e-margins">
							<h5>小组健康模型</h5>
							<div class="ibox-content">
								<div class="tabs-container">
									<ul id="group_index" class="nav nav-tabs">

									</ul>
									<div class="tab-content">
										<div class="tab-pane active">
											<div class="panel-body">
												<div>
													<div id="kline" style="height: 350px"></div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="ibox float-e-margins" style="display: none;">
							<h5>指标权重</h5>
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
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var chart_mb = echarts.init($("#kline").get(0));
	</script>
	<script type="text/javascript" src="${context}/js/cell/group.js"></script>
</body>
</html>
