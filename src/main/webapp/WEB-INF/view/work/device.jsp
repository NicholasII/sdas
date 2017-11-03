<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/include/common.jsp"%>
<link href="${context}/style/loader.css" rel="stylesheet"
	type="text/css">
<script src="${context}/lib/hplus/js/plugins/layer/laydate/laydate.js"></script>
<script type="text/javascript" src="${context}/js/work/device.js"></script>
<style type="text/css">
	input {
		padding-top: 5px;
		padding-bottom: 5px;
		margin-top: 10px;
		margin-bottom: 10px
	}

	.jqGrid_wrapper::-webkit-scrollbar {
		width: 16px;
		height: 10px;
		background-color: #F5F5F5;
	}

	/*定义滚动条轨道 内阴影+圆角*/
	.jqGrid_wrapper::-webkit-scrollbar-track {
		-webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
		border-radius: 10px;
		background-color: #F5F5F5;
	}

	/*定义滑块 内阴影+圆角*/
	::-webkit-scrollbar-thumb  
	.jqGrid_wrapper {
		border-radius: 10px;
		-webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, .3);
		background-color: #555;
	}
</style>
</head>
<body>
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-16">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>设备工单列表</h5>
						<div class="ibox-tools">
						<div class="btn-group" id="datePicker">
								<button class="btn btn-info datePicker" type="button">全部</button>
								<button class="btn btn-white datePicker" type="button">今日</button>
								<button class="btn btn-white datePicker" type="button">一周</button>
								<button class="btn btn-white datePicker" type="button">一月</button>
								<button class="btn btn-white datePicker" type="button">按时间选择</button>
								<div id="timeselect" style="display: none;float: left;">
									<input style="margin-left:5px;margin-top: -7px !important;" id="starttime"
										class="layer-date starttime" placeholder="请输入开始时间"
										onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
									<span id="span" style="margin-top: -10px ;display: inline !important;"
										class="input-group-addon">到</span> 
									<input  style="margin-top: -7px !important;" class="layer-date endtime" id="endtime"
										placeholder="请输入结束时间"
										onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
									<button class="btn btn-info search" type="button" onclick="select()">确定</button>
								</div>
							</div>
						</div>
					</div>
					<div class="ibox-content">
						<!-- <label>小区名称</label> <input
							style="padding-top: 5px; padding-bottom: 5px; margin-top: 10px; margin-bottom: 10px"
							type="text" placeholder="请输入小区名称" id="name" name="name">


						<label>所属区域</label> <input
							style="padding-top: 5px; padding-bottom: 5px; margin-top: 10px; margin-bottom: 10px"
							type="text" placeholder="请输入所属区域" id="name" name="name">


						<label>监控内容</label> <input
							style="padding-top: 5px; padding-bottom: 5px; margin-top: 10px; margin-bottom: 10px"
							type="text" placeholder="请输入监控内容" id="name" name="name">


						<button class="btn btn-white" onclick="javascript:select()">查询</button> -->
						<button class="btn btn-white" onclick="">导入</button>
						<button class="btn btn-white" onclick="">导出</button>

					</div>
					<div class="ibox-content">
						<div class="jqGrid_wrapper" style="margin: 0; padding: 0; width: 100%; overflow: auto;">
							<table class="table" id="table_list_1"></table>
							<div id="pager_list_1"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>