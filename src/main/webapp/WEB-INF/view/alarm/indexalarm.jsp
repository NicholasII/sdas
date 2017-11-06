<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/include/common.jsp"%>
<script src="${context}/lib/hplus/js/plugins/layer/laydate/laydate.js"></script>
<script type="text/javascript" src="${context}/js/alarm/indexalarm.js"></script>
<style type="text/css">
input {
	padding-top: 5px;
	padding-bottom: 5px;
	margin-top: 10px;
	margin-bottom: 10px;
	height: 35px;
    border-radius: 3px;
    border: 1px solid #999999;
}
select{    
    width: 160px;
    height: 35px;
    border-radius: 3px}
</style>
</head>
<body>
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-16">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>指标预警列表</h5>
						<div class="ibox-tools">
							<div class="btn-group" id="datePicker">
								<button class="btn btn-info datePicker" type="button">全部</button>
								<button class="btn btn-white datePicker" type="button">今日</button>
								<button class="btn btn-white datePicker" type="button">一周</button>
								<button class="btn btn-white datePicker" type="button">一月</button>
								<button class="btn btn-white datePicker" type="button">按时间选择</button>
								<div id="timeselect" style="display: none; float: left;">
									<input style="margin-left: 5px; margin-top: -7px !important;"
										id="starttime" class="layer-date starttime"
										placeholder="请输入开始时间"
										onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
									<span id="span"
										style="margin-top: -10px; display: inline !important;"
										class="input-group-addon">到</span> <input
										style="margin-top: -7px !important;"
										class="layer-date endtime" id="endtime" placeholder="请输入结束时间"
										onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
									<button class="btn btn-info search" type="button"
										onclick="select()">确定</button>
								</div>
							</div>
						</div>
					</div>
					<div class="ibox-content">
						<div>
							<label>小区名称</label> 
							<input type="text" placeholder="请输入小区名称" id="name" name="name"> 
							<label>指标类型：</label>
							<select name="type" id="type" value="">
									<option value="" name="type">全部</option>
									<option value="0" name="type">新PRB利用率(4次连续)</option>
									<option value="1" name="type">新切换出成功率(4次连续)</option>
								</select>
							<button class="btn btn-white" onclick="javascript:select()">查询</button>
						</div>
					</div>
					<div class="jqGrid_wrapper">
						<table id="table_list_1"></table>
						<div id="toolbar"></div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>