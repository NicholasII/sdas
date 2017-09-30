<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="/include/common.jsp"></jsp:include>
<script type="text/javascript" src="/june.web.new/js/charts/graph.js"></script>

</head>
<body>
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-6">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>A类小区</h5>
						<div class="ibox-tools"></div>
					</div>
					<div class="ibox-content">
						<div class="jqGrid_wrapper">
							<table id="table_list_1"></table>
							<div id="pager_list_1"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>B类小区</h5>
						<div class="ibox-tools"></div>
					</div>
					<div class="ibox-content">
						<div class="jqGrid_wrapper">
							<table id="table_list_2"></table>
							<div id="pager_list_2"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-6">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>C类小区</h5>
						<div class="ibox-tools"></div>
					</div>
					<div class="ibox-content">
						<div class="jqGrid_wrapper">
							<table id="table_list_3"></table>
							<div id="pager_list_3"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>D类小区</h5>
						<div class="ibox-tools"></div>
					</div>
					<div class="ibox-content">
						<div class="jqGrid_wrapper">
							<table id="table_list_4"></table>
							<div id="pager_list_4"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-6">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>工单验证_可疑工单</h5>
						<div class="ibox-tools"></div>
					</div>
					<div class="ibox-content">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>#</th>
									<th>数据</th>
									<th>用户</th>
									<th>值</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>1</td>
									<td><span class="line">5,3,2,-1,-3,-2,2,3,5,2</span></td>
									<td>张三</td>
									<td class="text-navy"><i class="fa fa-level-up"></i> 50%</td>
								</tr>
								<tr>
									<td>2</td>
									<td><span class="line">5,3,9,6,5,9,7,3,5,2</span></td>
									<td>李四</td>
									<td class="text-warning"><i class="fa fa-level-down"></i>
										-20%</td>
								</tr>
								<tr>
									<td>3</td>
									<td><span class="line">1,6,3,9,5,9,5,3,9,6,4</span></td>
									<td>王麻子</td>
									<td class="text-navy"><i class="fa fa-level-up"></i> 26%</td>
								</tr>
							</tbody>
						</table>
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
						<div class="echarts" id="echarts-line-chart"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-6">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>小区健康异常预警</h5>
						<div class="ibox-tools"></div>
					</div>
					<div class="ibox-content">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>#</th>
									<th>数据</th>
									<th>用户</th>
									<th>值</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>1</td>
									<td><span class="line">5,3,2,-1,-3,-2,2,3,5,2</span></td>
									<td>张三</td>
									<td class="text-navy"><i class="fa fa-level-up"></i> 50%</td>
								</tr>
								<tr>
									<td>2</td>
									<td><span class="line">5,3,9,6,5,9,7,3,5,2</span></td>
									<td>李四</td>
									<td class="text-warning"><i class="fa fa-level-down"></i>
										-20%</td>
								</tr>
								<tr>
									<td>3</td>
									<td><span class="line">1,6,3,9,5,9,5,3,9,6,4</span></td>
									<td>王麻子</td>
									<td class="text-navy"><i class="fa fa-level-up"></i> 26%</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>小区指标异常预警</h5>
						<div class="ibox-tools"></div>
					</div>
					<div class="ibox-content">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>#</th>
									<th>数据</th>
									<th>用户</th>
									<th>值</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>1</td>
									<td><span class="line">5,3,2,-1,-3,-2,2,3,5,2</span></td>
									<td>张三</td>
									<td class="text-navy"><i class="fa fa-level-up"></i> 50%</td>
								</tr>
								<tr>
									<td>2</td>
									<td><span class="line">5,3,9,6,5,9,7,3,5,2</span></td>
									<td>李四</td>
									<td class="text-warning"><i class="fa fa-level-down"></i>
										-20%</td>
								</tr>
								<tr>
									<td>3</td>
									<td><span class="line">1,6,3,9,5,9,5,3,9,6,4</span></td>
									<td>王麻子</td>
									<td class="text-navy"><i class="fa fa-level-up"></i> 26%</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-6">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>性能监控告警统计</h5>
						<div class="ibox-tools"></div>
					</div>
					<div class="ibox-content">
						<div class="echarts" id="echarts-radar-chart"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>