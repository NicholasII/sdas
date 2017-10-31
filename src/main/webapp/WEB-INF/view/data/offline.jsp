<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/include/common.jsp"%>
</head>
<body>
	<div class="ibox-content">
		<div class="row">
			<div class="col-sm-6">
				<div class="panel panel-success">
					<div class="panel-heading">中兴网管指标数据</div>
					<div class="panel-body">
						<!-- <p>容量：2.4T</p>
						<p>采样频率：15min</p>
						<p>最后采样时间：2017-08-01</p> -->
						<form action="${context}/data/upload?type='network'" method="post"
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
					<div class="panel-heading">性能工单</div>
					<div class="panel-body">
						<!-- <p>容量：2.4T</p>
						<p>采样频率：1天</p>
						<p>最后采样时间：2017-08-01</p> -->
						<form action="${context}/data/upload?type='capacity'"
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
		<div class="row">
			<div class="col-sm-6">
				<div class="panel panel-success">
					<div class="panel-heading">故障工单</div>
					<div class="panel-body">
						<!-- <p>容量：2.4T</p>
						<p>采样频率：15min</p>
						<p>最后采样时间：2017-08-01</p> -->
						<form action="${context}/data/upload?type='fault'" method="post"
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
						<form action="${context}/data/upload?type='complaint'"
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
		<div class="row">
			<div class="col-sm-6">
				<div class="panel panel-success">
					<div class="panel-heading">可视化小区退服</div>
					<div class="panel-body">
						<!-- <p>容量：2.4T</p>
						<p>采样频率：实时数据</p>
						<p>最后采样时间：2017-08-01</p> -->
						<form action="${context}/data/upload?type='outservice'"
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
	</div>
</body>
</html>