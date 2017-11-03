<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/include/common.jsp"%>
<script src="${context}/lib/hplus/js/plugins/layer/laydate/laydate.js"></script>
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
.SelectYel{
	color: #DFCD15;
}
.loading_bk{
	display:none;
    height: 80%;
    width: 100%;
    min-height:310px;
    background-color: #777;
    position: absolute;
    z-index: 999;
    opacity: 0.6;
    text-align: center;
    }
.loading{
	display:none;
	color:#fff;
    margin-left: 40%;
    margin-top: 10%;
    position: absolute;
    z-index: 9999;
    text-align: center;
    }
.loading span{font-size: 16px; margin-left: 10px;}
.loading img{height:30px}
</style>
</head>
<body style="margin-left: 5px;margin-right: 5px;">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-16">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>性能工单列表</h5>
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
						<div>
							<!-- <label>时间选择</label> <select id="timeselect"
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
								onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"> -->

							<label>小区名称</label> <input type="text" placeholder="请输入小区名称"
								id="name" name="name"> <label>所属区域</label> <select
								id="area"
								style="padding-top: 5px; padding-bottom: 5px; margin-top: 10px; margin-bottom: 10px">
								<option>全部</option>
							</select> <label>监控内容</label> <select id="content"
								style="padding-top: 5px; padding-bottom: 5px; margin-top: 10px; margin-bottom: 10px">
								<option>全部</option>
								<option>新切换出成功率(4次连续)</option>
								<option>新PRB利用率(4次连续)</option>
							</select>
							</select> <label>判断结果</label> <select id="result"
								style="padding-top: 5px; padding-bottom: 5px; margin-top: 10px; margin-bottom: 10px">
								<option>全部工单</option>
								<option>高度可疑</option>
								<option>可疑工单</option>
								<option>正常工单</option>
							</select>							
							<button class="btn btn-white" onclick="javascript:select()">查询</button>																					
							<button class="btn btn-white" onclick="">导出</button>

							<div>
								<form action="${context}/work/import/capacity" method="post"
									enctype="multipart/form-data"
									style="display: inline !important;">
									<input style="display: inline !important;"
										class="btn btn-white" type="file" name="file"
										multiple="multiple" accept="application/vnd.ms-excel">
									<input class="btn btn-white" type="submit" value="导入">
									<input class="btn btn-white" type="reset" value="重选">
								</form>
								<button style="padding-top: 5px; padding-bottom: 5px; margin-top: 10px; margin-bottom: 10px" 
								class="btn btn-white" onclick="javascript:validate()">工单验证</button>
							</div>

							<!-- <label for="checkbox6"><input id="doubtwork"
								type="checkbox" name="doubtwork">可疑工单</label> -->
						</div>


					</div>
					<div class="footer" style="height: 60px;">
							<div>
								<span><i>备注：</i> </span> <span>红色为高度可疑工单；绿色为符合条件工单；黄色为可疑工单</span>
							</div>
						</div>
					<div class="jqGrid_wrapper">
						<!-- loading -->
						<div class="loading_bk" id="loadbk"></div>
						<div class="loading" id="load"><img src="${context}/lib/hplus/css/plugins/blueimp/img/loading.gif"><span>内容加载中...</span></div>
						<!-- loading -->
						
						<table class="table" id="table_list_1"></table>
						<div id="pager_list_1"></div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>