/**
 * 导入历史数据列表
 */

function drawTables(type){
	 $('#historyTable').bootstrapTable({
		 cache : false,
	        striped : true,
	        pagination : true,
	        toolbar : '#pager_Table',
	        pageSize : 10,
	        pageNumber : 1,
	        pageList : [ 5, 10, 20 ],
	        clickToSelect : true,
	        sidePagination : 'server',// 设置为服务器端分页
	        columns: [{
	              field: 'starttime',
	              title: '上传时间',
	              width:500,
	              formatter:function(value,row,index){
	                  var jsDate = new Date(value);
	                  var UnixTimeToDate = jsDate.getFullYear() + '/' + (jsDate.getMonth() + 1) + '/'+jsDate.getDate()+ ' ' + jsDate.getHours() + ':' + jsDate.getMinutes() + ':' + jsDate.getSeconds();
	                   return UnixTimeToDate;
	                 }
	          },{
	              field: 'endtime',
	              title: '结束时间',
	              width:500,
	              formatter:function(value,row,index){
	                  var jsDate = new Date(value);
	                  var UnixTimeToDate = jsDate.getFullYear() + '/' + (jsDate.getMonth() + 1) + '/'+jsDate.getDate()+ ' ' + jsDate.getHours() + ':' + jsDate.getMinutes() + ':' + jsDate.getSeconds();
	                   return UnixTimeToDate;
	                 }
	          },
	          {
	              field: 'filename',
	              title: '文件名称',
	              width:500
	          },{
	              field: 'type',
	              title: '文件类型',
	              width:500
	          },{
	              field: 'result',
	              title: '任务状态',
	              width:500,
	              formatter:function(value,row,index){
	                 if(value=="0"){
	                	 return "失败";
	                 }else if(value=="1"){
	                	 return "成功";
	                 }
	                 }
	          }],
	        onPageChange : function(size, number) {
	        	var data = {};
	    	 	data.type=type;
	    	    commonRowDatas("historyTable", data, "/sdas/log/file/list", "commonCallback", true);
	        },
	        formatNoMatches : function() {
	            return "NOT_FOUND_DATAS";
	        }
	    });
	 	var data = {};
	 	data.type=type;
	    commonRowDatas("historyTable", data, "/sdas/log/file/list", "commonCallback", true);
}
function back(){
	top.$("#offline").attr('src',"/sdas/data/offline");
}