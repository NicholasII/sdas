<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/include/common.jsp"%>
<script type="text/javascript" src="${context}/js/data/logfile.js"></script>
</head>
<body>
	<div class="ibox-content" id="historyTables">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox-title">
					<h1>上传历史记录</h1>
				</div>
				<div class="ibox-content">
					<div class="col-sm-12">
						<div class="ibox-content">
							<div id="historyTable"></div>
							<div id="pager_Table"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			console.info("${type}");
			if("${type}"!=""){
				drawTables("${type}");
			}
			
		});
	</script>
</body>
</html>