<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/include/common.jsp"%>
<style type="text/css">
input[type='textarea'] {
	height: 80px
}

.col-sm-3, .col-sm-8 {
	margin-bottom: 5px
}

.timeselect {
	float: left
}

.timeselect input {
	margin-left: 5px;
	margin-top: -7px !important;
	height: 33px;
	border-radius: 3px;
	border: 1px solid #e5e6e7;
}

.form-group label {
	width: 110px;
	padding-right: 0px;
	margin-top: 5px;
}

.form-group .col-sm-8 {
	padding-left: 0px
}

.col-sm-4 {
	margin-left: 60px
}
</style>
<script type="text/javascript" src="${context}/lib/hplus/js/plugins/layer/laydate/laydate.js"></script>
<script type="text/javascript" src="${context}/js/task.js"></script>
</head>
<body>
	<div class="ibox-content">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox-title">
					<h5>数据存储</h5>
				</div>
				<div class="ibox-content">
					<div class="col-sm-4">
						<div class="panel panel-success">
							<div class="panel-heading">切片</div>
							<div class="panel-body">
								<div class="form-group">
									<label class="col-sm-3 control-label">源目录：</label>
									<div class="col-sm-8">
										<input type="text" placeholder="Source" id="Source" class="form-control">
										<span class="help-block m-b-none">格式：'hdfs://ip:port/data/*.csv'</span>
									</div>
									<label class="col-sm-3 control-label">输出目录：</label>
									<div class="col-sm-8">
										<input type="text" placeholder="Output" id="Output" class="form-control">
										<span class="help-block m-b-none">格式：'hdfs://ip:port/data/output'</span>
									</div>
									<label class="col-sm-3 control-label">是否删除输出目录：</label>
									<div class="col-sm-8" style="margin-top: 10px">

										<input type="radio" checked="checked" value="1"
											name="deleteOut" style="margin-left: 20px"><span>是</span>
										<input type="radio" checked="" value="0" name="deleteOut"
											style="margin-left: 20px"><span>否</span>

									</div>
									<div class="col-sm-4 col-sm-offset-3" style="margin-top: 10px">
										<button class="btn btn-info search" type="button"
											onclick="section('')">确定</button>
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-12">
				<div class="ibox-title">
					<h5>模式库挖掘</h5>
				</div>
				<div class="ibox-content">
					<div class="col-sm-4">
						<div class="panel panel-success">
							<div class="panel-heading">模式库挖掘</div>
							<div class="panel-body">
								<div class="form-group">
									<label class="col-sm-3 control-label">选择计算时间：</label>
									<div class="col-sm-8">
										<div class="timeselect">
											<input id="model_time" class="layer-date"
												placeholder="请输入计算时间"
												onclick="laydate({istime: false, format: 'YYYY-MM'})">
											<button class="btn btn-info search" type="button"
												onclick="calculate()">确定</button>
										</div>
									</div>
									<label class="col-sm-3 control-label">计算结果：</label>
									<div class="col-sm-8">
										<input type="textarea" placeholder="计算结果" class="form-control" id="m_area">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-12">
				<div class="ibox-title">
					<h5>离线健康度计算</h5>
				</div>
				<div class="ibox-content">

					<div class="col-sm-4">
						<div class="panel panel-success">
							<div class="panel-heading">离线计算小区健康度</div>
							<div class="panel-body">
								<div class="form-group">
									<label class="col-sm-3 control-label">选择计算时间：</label>
									<div class="col-sm-8">
										<div class="timeselect" style="float: left;">
											<input id="Health_time" class="layer-date"
												placeholder="请输入计算时间"
												onclick="laydate({istime: false, format: 'YYYY-MM'})">
											<button class="btn btn-info search" type="button"
												onclick="calculate()">确定</button>
										</div>
									</div>
									<label class="col-sm-3 control-label">计算结果：</label>
									<div class="col-sm-8">
										<input type="textarea" placeholder="计算结果" class="form-control" id="h_area">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-12">
				<div class="ibox-title">
					<h5>健康判断</h5>
				</div>
				<div class="ibox-content">
					<div class="col-sm-4">
						<div class="panel panel-success">
							<div class="panel-heading">离线计算小区健康比率信息</div>
							<div class="panel-body">
								<div class="form-group">
									<label class="col-sm-3 control-label">选择计算时间：</label>
									<div class="col-sm-8">
										<div class="timeselect">
											<input id="Percen_time" class="layer-date"
												placeholder="请输入计算时间"
												onclick="laydate({istime: false, format: 'YYYY-MM'})">
											<button class="btn btn-info search" type="button"
												onclick="calculate()">确定</button>
										</div>
									</div>
									<label class="col-sm-3 control-label">计算结果：</label>
									<div class="col-sm-8">
										<input type="textarea" placeholder="计算结果" class="form-control" id="p_area">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-12">
				<div class="ibox-title">
					<h5>指标预警</h5>
				</div>
				<div class="ibox-content">
					<div class="col-sm-4">
						<div class="panel panel-success">
							<div class="panel-heading">指标预警</div>
							<div class="panel-body">
								<div class="form-group">
									<label class="col-sm-3 control-label">选择计算时间：</label>
									<div class="col-sm-8">
										<div class="timeselect">
											<input id="alarm_time" class="layer-date"
												placeholder="请输入计算时间"
												onclick="laydate({istime: false, format: 'YYYY-MM'})">
											<button class="btn btn-info search" type="button"
												onclick="calculate()">确定</button>
										</div>
									</div>
									<label class="col-sm-3 control-label">计算结果：</label>
									<div class="col-sm-8">
										<input type="textarea" placeholder="计算结果" class="form-control" id="a_area">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>