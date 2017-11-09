<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/include/common.jsp"%>
<script src="${context}/lib/hplus/js/plugins/layer/laydate/laydate.js"></script>
<script type="text/javascript" src="${context}/js/data/offline.js"></script>
</head>
<body>
	<script type="text/javascript">
		var status = '${success}';
		if (status == 'success') {
			showOnlyMessage(INFO, "导入数据成功！");
		} else if (status.indexOf("fail")>=0) {
			showOnlyMessage(ERROR, status);
		}
	</script>
	<div class="ibox-content">
		<div class="row">
			<div class="col-sm-6">
				<div class="panel panel-success">
					<div class="panel-heading">中兴网管指标数据</div>
					<div class="panel-body">
						<form id="form1" action="${context}/data/upload?type=network" method="post"
							enctype="multipart/form-data">
							<input id="time" name="time" style="display: inline;padding: -10px;margin: -10px;height: 39px;margin-right: 10px;"
										class="btn btn-white layer-date starttime" placeholder="请选择时间"
										onclick="laydate({istime: true, format: 'YYYYMM'})">
							<input class="btn btn-white" type="file" name="file" style="display: inline;"
								multiple="multiple" accept=".csv"> <br> <br><input
								class="btn btn-white" type="reset" value="重选"> <input id="submit"
								class="btn btn-white" type="submit" value="上传">
								<progress id="progress" style="display: none">正在上传...</progress>
						</form>
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="panel panel-success">
					<div class="panel-heading">性能工单</div>
					<div class="panel-body">
						<form id="form2" action="${context}/data/upload?type=capacity"
							method="post" enctype="multipart/form-data">
							<input class="btn btn-white" type="file" name="file" accept=".xls"> <br> <input
								class="btn btn-white" type="reset" value="重选"> <input id="submit1"
								class="btn btn-white" type="submit" value="上传">
							<progress id="progress1" max="200" style="display: none">正在上传...</progress>
						</form>
					</div>
				</div>
			</div>			
		</div>
		<div class="row" style="display: none!important;">
			<div class="col-sm-6">
				<div class="panel panel-success">
					<div class="panel-heading">故障工单</div>
					<div class="panel-body">
						<!-- <p>容量：2.4T</p>
						<p>采样频率：15min</p>
						<p>最后采样时间：2017-08-01</p> -->
						<form action="${context}/data/upload?type=fault" method="post"
							enctype="multipart/form-data">

							<input class="btn btn-white" type="file" name="file"
								multiple="multiple" accept="text/*"> <br> <input
								class="btn btn-white" type="reset" value="重选"> <input
								class="btn btn-white" type="submit" value="上传">

						</form>
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="panel panel-success">
					<div class="panel-heading">投诉数据</div>
					<div class="panel-body">
						<!-- <p>容量：2.4T</p>
						<p>采样频率：1天</p>
						<p>最后采样时间：2017-08-01</p> -->
						<form action="${context}/data/upload?type=complaint"
							method="post" enctype="multipart/form-data">

							<input class="btn btn-white" type="file" name="file"
								multiple="multiple" accept="text/*"> <br> <input
								class="btn btn-white" type="reset" value="重选"> <input
								class="btn btn-white" type="submit" value="上传">

						</form>

					</div>

				</div>
			</div>
		</div>
		<div class="row" style="display: none!important;">
			<div class="col-sm-6">
				<div class="panel panel-success">
					<div class="panel-heading">可视化小区退服</div>
					<div class="panel-body">
						<!-- <p>容量：2.4T</p>
						<p>采样频率：实时数据</p>
						<p>最后采样时间：2017-08-01</p> -->
						<form action="${context}/data/upload?type=outservice"
							method="post" enctype="multipart/form-data">

							<input class="btn btn-white" type="file" name="file"
								multiple="multiple" accept="text/*"> <br> <input
								class="btn btn-white" type="reset" value="重选"> <input
								class="btn btn-white" type="submit" value="上传">

						</form>
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="panel panel-success">
					<div class="panel-heading">投诉详情数据</div>
					<div class="panel-body">
						<form action="" method="post" enctype="multipart/form-data">
							<input class="btn btn-white" type="file" name="file"
								multiple="multiple" accept="text/*"> <br> <input
								class="btn btn-white" type="reset" value="重选"> <input
								class="btn btn-white" type="submit" value="上传">

						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>