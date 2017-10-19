<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/include/common.jsp"%>
<script src="${context}/lib/hplus/js/plugins/layer/laydate/laydate.js"></script>
<script type="text/javascript" src="${context}/include/time.js"></script>
<script type="text/javascript" src="${context}/js/work/capacity.js"></script>
<style type="text/css">
input {
	padding-top: 5px;
	padding-bottom: 5px;
	margin-top: 10px;
	margin-bottom: 10px
}
.SelectRed{
	color:red;
}
.SelectGre{
	color:green;
}
</style>
</head>
<body style="margin-left: 5px;margin-right: 5px;">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-16">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>性能工单列表</h5>
						<div class="ibox-tools"></div>
					</div>
					<div class="ibox-content">
						<div>
							<label>时间选择</label> <select id="timeselect"
								style="padding-top: 5px; padding-bottom: 5px; margin-top: 10px; margin-bottom: 10px">
								<option>全部</option>
								<option>按时间段</option>
								<option>按日期</option>
							</select> <input id="starttime" style="margin-top: -10px !important;"
								class="layer-date" placeholder="请输入开始时间" disabled="disabled"
								onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
							<span id="span" style="display: inline !important;"
								class="input-group-addon">到</span> <input id="endtime"
								style="margin-top: -10px !important;" class="layer-date"
								placeholder="请输入结束时间" disabled="disabled"
								onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">

							<label>小区名称</label> <input type="text" placeholder="请输入小区名称"
								id="name" name="name"> <label>所属区域</label> <select
								id="area"
								style="padding-top: 5px; padding-bottom: 5px; margin-top: 10px; margin-bottom: 10px">
								<option></option>
							</select> <label>监控内容</label> <select id="content"
								style="padding-top: 5px; padding-bottom: 5px; margin-top: 10px; margin-bottom: 10px">
								<option>全部</option>
								<option>新切换出成功率(4次连续)</option>
								<option>新PRB利用率(4次连续)</option>
							</select>

							<button class="btn btn-white" onclick="javascript:select()">查询</button>
							<button class="btn btn-white" onclick="javascript:validate()">可疑工单验证</button>
							<label for="checkbox6"><input id="doubtwork"
								type="checkbox" name="doubtwork">可疑工单</label>
							<button class="btn btn-white" onclick="">导出</button>
							<form action="${context}/work/import" method="post" enctype="multipart/form-data">
								<input type="file" name="file" multiple="multiple" accept="application/vnd.ms-excel"> <input type="submit" value="导入">
								<input type="reset" value="重选">
							</form>
							
						</div>


					</div>
					<div class="jqGrid_wrapper">
						<table class="table" id="table_list_1"></table>
						<div id="pager_list_1"></div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>