<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/include/common.jsp"%>
<script src="${context}/lib/hplus/js/plugins/layer/laydate/laydate.js"></script>
<link href="${context}/style/loader.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${context}/include/time.js"></script>
<script type="text/javascript" src="${context}/js/work/outservice.js"></script>
</head>
<body>
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-16">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>退服工单列表</h5>
						<div class="ibox-tools"></div>
					</div>
					<div class="ibox-content">
						<div class="form-group" style="float: left; width: 20%;"
							id="data_1">
							<label class="sr-only">选择时间</label>
							<div class="input-group date">
								<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
								<input type="text" class="form-control" value="2014-11-11">
							</div>
						</div>
						<div class="form-group" style="float: left">
							<label class="sr-only">小区名称</label> <input type="text"
								placeholder="请输入小区名称" id="name" name="name" class="form-control">
						</div>
						<div class="form-group" style="float: left">
							<label class="sr-only">小区名称</label> <input type="text"
								placeholder="请输入所属区域" id="name" name="name" class="form-control">
						</div>
						<div class="form-group" style="float: left">
							<label class="sr-only">小区名称</label> <input type="text"
								placeholder="请输入监控内容" id="name" name="name" class="form-control">
						</div>

						<button class="btn btn-white" onclick="javascript:select()">查询</button>
						<button class="btn btn-white" onclick="">导入</button>
						<button class="btn btn-white" onclick="">导出</button>
					</div>
					<div class="ibox-content">
						<table class="table" id="table_list_1"></table>
						<div id="pager_list_1"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>