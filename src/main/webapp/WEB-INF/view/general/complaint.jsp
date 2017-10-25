<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>投诉信息总页</title>
<%@ include file="/include/common.jsp"%>
<script src="${context}/lib/hplus/js/plugins/layer/laydate/laydate.js"></script>
<script type="text/javascript" src="${context}/include/time.js"></script>
<script type="text/javascript" src="${context}/include/utils.js"></script>
</head>
<body>
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-16">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>投诉信息</h5>
						<div class="ibox-tools"></div>
					</div>
					<div class="ibox-content">
                        <div class="form-group" style="float: left;width: 20%;" id="data_1">
                            <label class="sr-only">选择时间</label>
                            <div class="input-group date">
                                <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                <input type="text" class="form-control" value="">
                            </div>
                        </div>
						<div class="form-group" style="float: left">
							<label class="sr-only">小区名称</label> <input type="text"
								placeholder="请输入小区名称" id="name" name="name" class="form-control">
						</div>
						
						<button class="btn btn-white" onclick="javascript:select()">查询</button>
						
						<div class="form-group" style="margin-left:5px;margin-right:5px;float: left">
                               <input type="checkbox" checked=""><label for="checkbox6">集中投诉</label>
                        </div>
                        <button class="btn btn-white" onclick="">导入</button>
						<button class="btn btn-white" onclick="">导出</button>

					</div>
					<div class="ibox-content">
						<div class="jqGrid_wrapper">
							<table id="table_list_1"></table>
							<div id="pager_list_1"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
        var celldata;
        var cellname;
        var flag = "${empty rows}";  
        if(flag!="true"){  
        	celldata = '${rows}';
        	cellname = '${cellname}';
        	$("#name").val(cellname);
        }
    </script>

	<script type="text/javascript" src="${context}/js/general/complaint.js"></script>
</body>
</html>