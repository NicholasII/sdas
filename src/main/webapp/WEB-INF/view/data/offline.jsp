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
		} else if (status.indexOf("fail") >= 0) {
			showOnlyMessage(ERROR, status);
		}
	</script>
	<div class="ibox-content" id="offline">
		<div class="row">
			<div class="col-sm-6">
				<div class="panel panel-success">
					<div class="panel-heading">中兴网管指标数据</div>
					<div class="panel-body">
					<div>
								<span><i>备注：</i> </span> <span>请选择小区一天的网管数据</span>
							</div>
						<form id="form1" action="${context}/data/upload?type=network" method="post"
							enctype="multipart/form-data">
							<input id="time" name="time" style="display: inline;padding: -10px;margin: -10px;height: 39px;margin-right: 10px;"
										class="btn btn-white layer-date starttime" placeholder="请选择计算模式年月"
										onclick="laydate({istime: true, format: 'YYYYMM'})">
							<input class="btn btn-white" type="file" name="file" id="file1" style="display: inline;"  accept=".csv"> <br> <br><input
								class="btn btn-white" type="reset" value="重选"> <input 
								class="btn btn-white" type="button" value="上传" onclick="submit_upload('#file1','#form1')">
								<input class="btn btn-white" type="button" value="查看上传记录" onclick="openIframe('中兴网管指标数据')"> 
								<progress id="progress" style="display: none">正在上传...</progress>
						</form>
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="panel panel-success">
					<div class="panel-heading">性能工单</div>
					<div class="panel-body">
						<div>
								<span><i>备注：</i> </span> <span>请选择单个性能工单表格文件</span>
						</div>
						<form id="form2" action="${context}/data/upload?type=capacity"
							method="post" enctype="multipart/form-data">
							<input class="btn btn-white" type="file" name="file" id="file2" accept=".xls"> 
								<br>
								<input class="btn btn-white" type="reset" value="重选"> <input id="submit1"
								class="btn btn-white" type="button" value="上传"  onclick="submit_upload('#file2','#form2')">
								<input class="btn btn-white" type="button" value="查看上传记录" onclick="openIframe('性能工单数据')"> 
							<progress id="progress1" max="200" style="display: none">正在上传...</progress>
						</form>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-sm-6">
				<div class="panel panel-success">
					<div class="panel-heading">中兴网管指标原始数据</div>
					<div class="panel-body">
					<div>
								<span><i>备注：</i> </span> <span>可选择多个文件！</span>
						</div>
						<form id="form3" action="${context}/data/upload?type=file" method="post"
							enctype="multipart/form-data">
							<input class="btn btn-white" multiple="multiple" type="file" name="file" id="file3" style="display: inline;" onchange="file_upload(this)"/> <br><br>
							<input
								class="btn btn-white" type="reset" value="重选"> <input
								class="btn btn-white" type="button" value="上传" onclick="submit_upload('#file3','#form3')">
								<input class="btn btn-white" type="button" value="查看上传记录" onclick="openIframe('中兴网管指标原始数据')"> 
								<progress id="progress" style="display: none">正在上传...</progress>
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
						<form id="form4" action="${context}/data/upload?type=fault" method="post"
							enctype="multipart/form-data">

							<input class="btn btn-white" type="file" name="file" id="file4"
								multiple="multiple" accept="text/*"> <br> <input
								class="btn btn-white" type="reset" value="重选"> <input
								class="btn btn-white" type="button" value="上传" onclick="submit_upload('#file4','#form4')">

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
						<form id="form5" action="${context}/data/upload?type=complaint" method="post"
							enctype="multipart/form-data">

							<input class="btn btn-white" type="file" name="file" id="file5"
								multiple="multiple" accept="text/*"> <br> <input
								class="btn btn-white" type="reset" value="重选"> <input
								class="btn btn-white" type="button" value="上传" onclick="submit_upload('#file5','#form5')">

						</form>

					</div>

				</div>
			</div>
		</div>
		<div class="row" style="display: none !important;">
			<div class="col-sm-6">
				<div class="panel panel-success">
					<div class="panel-heading">可视化小区退服</div>
					<div class="panel-body">
						<!-- <p>容量：2.4T</p>
						<p>采样频率：实时数据</p>
						<p>最后采样时间：2017-08-01</p> -->
						<form id="form6" action="${context}/data/upload?type=outservice"
							method="post" enctype="multipart/form-data">

							<input class="btn btn-white" type="file" name="file" id="file6"
								multiple="multiple" accept="text/*"> <br> <input
								class="btn btn-white" type="reset" value="重选"> <input
								class="btn btn-white" type="button" value="上传" onclick="submit_upload('#file6','#form6')">

						</form>
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="panel panel-success">
					<div class="panel-heading">投诉详情数据</div>
					<div class="panel-body">
						<form id="form7" action="" method="post" enctype="multipart/form-data">
							<input class="btn btn-white" type="file" name="file" id="file7"
								multiple="multiple" accept="text/*"> <br> <input
								class="btn btn-white" type="reset" value="重选"> <input
								class="btn btn-white" type="button" value="上传" onclick="submit_upload('#file7','#form7')">

						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>