<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="${context}/js/cell/table.js"></script>
<link href="${context}/style/loader.css" rel="stylesheet" type="text/css">
</head>
<body class="gray-bg">
	<div id="loader-wrapper">
		<div id="loader"></div>
		<div class="loader-section section-left"></div>
		<div class="loader-section section-right"></div>
		<div class="load_title">正在加载小区数据...</div>
	</div>

	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-16">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>小区列表</h5>
						<div class="ibox-tools"></div>
					</div>
					<div class="ibox-content">
						<div class="form-group" style="float: left">
							<label for="name" class="sr-only">小区名称</label> <input type="text"
								placeholder="请输入小区名称" id="name" name="name" class="form-control">
						</div>
						<div class="form-group" style="float: left">
							<label for="scene" class="sr-only">密码</label> <input type="text"
								placeholder="请输入覆盖场景" id="scene" name="scene"
								class="form-control">
						</div>
						<div class="form-group" style="float: left">
							<label for="type" class="sr-only">密码</label> <select
								class="form-control m-b" id="type" name="type">
								<option></option>
								<option>I</option>
								<option>II</option>
								<option>III</option>
								<option>IV</option>
								<option>V</option>
								<option>VI</option>
								<option>VII</option>
								<option>VIII</option>
							</select>
						</div>
						<button style="margin-left: 5px;" class="btn btn-white"
							onclick="javascript:select()">查询</button>
						<button class="btn btn-white" onclick="javascript:clear()">清空</button>
					</div>
					<div class="ibox-content">
						<div class="jqGrid_wrapper">
							<table class="table" id="table_list_1"></table>
							<div id="pager_list_1"></div>
							
						</div>

					</div>
					<div class="footer" style="height: 60px;">
						<div>
							<span><i>备注：</i> </span> <span>I类分组：投诉工单连续三天有至少三次投诉的小区；
								II类分组：在除去I类分组的基础上，连续三天至少有5次性能工单的小区；
								III类分组：在全部数据集的基础上且除去I类II类小区的基础上，连续一周至少出现两次故障工单的小区
								IV类分组：在I类小组中出现过性能工单的小区； V类分组：其他标记为V类分组</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
