/**
 * by dq
 */
$.jgrid.defaults.styleUI = 'Bootstrap';
var params = {};
params.type = "A";
$.ajax({
	url : "/sada/group/getlistgroup",
	type : "GET",
	data : params,
	dataType : "json",
	success : function(data, status) {
		var list = data.rows;
		// Examle data for jqGrid					
		$("#table_list_1").jqGrid(
				{
					data : list,
					datatype : "local",
					height : "auto",
					autowidth : true,
					shrinkToFit : true,
					rowNum : 14,
					rowList : [ 10, 20, 30 ],
					colNames : [ 'group1_code', 'group_type', 'cell_code',
							'record_time' ],
					colModel : [ {
						name : 'group1_code',
						index : 'group1_code',
						width : 60
					}, {
						name : 'group_type',
						index : 'group_type',
						width : 60
					}, {
						name : 'cell_code',
						index : 'cell_code',
						width : 60
					}, {
						name : 'record_time',
						index : 'record_time',
						width : 60
					} ],
					pager : "#pager_list_1",
					viewrecords : true,
					hidegrid : false,
					gridComplete:function(){
			        	//获取某列的每一行id
			        	var ids = jQuery("#table_list_1").jqGrid("getDataIDs");
			        	for(var i=0;i<ids.length;i++){
			        	    var id = ids[i];
			        	    var names = $("#table_list_1").getCell(id,'cell_code');
			        	    var datas = $("#table_list_1").getCell(id,'cell_code');
			        	    /*var url="<a href='#' onclick='getdetail("+id+",&quot;"+datas+"&quot;)'>"+names+"</a>";*/
			        	    var url="<a href=/sada/detail/page/?name='"+datas+"'>"+names+"</a>";
			        	    $("#table_list_1").jqGrid('setRowData',id,{cell_code:url});
			        	}
			        }
				});
		// Add responsive to jqGrid
		$(window).bind('resize', function() {
			var width = $('.jqGrid_wrapper').width();
			$('#table_list_1').setGridWidth(width);

		});
	},
	error : function(data, status) {

	}
});
var params2 = {};
params2.type = "B";

$.ajax({
	url : "/sada/group/getlistgroup",
	type : "GET",
	data : params2,
	dataType : "json",
	success : function(data, status) {
		var list = data.rows;
		$("#table_list_2").jqGrid(
				{
					data : list,
					datatype : "local",
					height : "auto",
					autowidth : true,
					shrinkToFit : true,
					rowNum : 14,
					rowList : [ 10, 20, 30 ],
					colNames : [ 'group1_code', 'group_type', 'cell_code',
							'record_time' ],
					colModel : [ {
						name : 'group1_code',
						index : 'group1_code',
						width : 60
					}, {
						name : 'group_type',
						index : 'group_type',
						width : 60
					}, {
						name : 'cell_code',
						index : 'cell_code',
						width : 60
					}, {
						name : 'record_time',
						index : 'record_time',
						width : 60
					} ],
					pager : "#pager_list_2",
					viewrecords : true,
					hidegrid : false,
					gridComplete:function(){
			        	//获取某列的每一行id
			        	var ids = jQuery("#table_list_2").jqGrid("getDataIDs");
			        	for(var i=0;i<ids.length;i++){
			        	    var id = ids[i];
			        	    var names = $("#table_list_2").getCell(id,'cell_code');
			        	    var datas = $("#table_list_2").getCell(id,'cell_code');
			        	    /*var url="<a href='#' onclick='getdetail("+id+",&quot;"+datas+"&quot;)'>"+names+"</a>";*/
			        	    var url="<a href=/sada/detail/page/?name='"+datas+"'>"+names+"</a>";
			        	    $("#table_list_2").jqGrid('setRowData',id,{cell_code:url});
			        	}
			        }
				});
		$(window).bind('resize', function() {
			var width = $('.jqGrid_wrapper').width();
			$('#table_list_2').setGridWidth(width);

		});
	},
	error : function(data, status) {

	}
});
var params3 = {};
params.type = "C";
$.ajax({
	url : "/sada/group/getlistgroup",
	type : "GET",
	data : params,
	dataType : "json",
	success : function(data, status) {
		var list = data.rows;
		// Examle data for jqGrid					
		$("#table_list_3").jqGrid(
				{
					data : list,
					datatype : "local",
					height : "auto",
					autowidth : true,
					shrinkToFit : true,
					rowNum : 14,
					rowList : [ 10, 20, 30 ],
					colNames : [ 'group1_code', 'group_type', 'cell_code',
							'record_time' ],
					colModel : [ {
						name : 'group1_code',
						index : 'group1_code',
						width : 60
					}, {
						name : 'group_type',
						index : 'group_type',
						width : 60
					}, {
						name : 'cell_code',
						index : 'cell_code',
						width : 60
					}, {
						name : 'record_time',
						index : 'record_time',
						width : 60
					} ],
					pager : "#pager_list_3",
					viewrecords : true,
					hidegrid : false,
					gridComplete:function(){
			        	//获取某列的每一行id
			        	var ids = jQuery("#table_list_3").jqGrid("getDataIDs");
			        	for(var i=0;i<ids.length;i++){
			        	    var id = ids[i];
			        	    var names = $("#table_list_3").getCell(id,'cell_code');
			        	    var datas = $("#table_list_3").getCell(id,'cell_code');
			        	    /*var url="<a href='#' onclick='getdetail("+id+",&quot;"+datas+"&quot;)'>"+names+"</a>";*/
			        	    var url="<a href=/sada/detail/page/?name='"+datas+"'>"+names+"</a>";
			        	    $("#table_list_3").jqGrid('setRowData',id,{cell_code:url});
			        	}
			        }
				});
		// Add responsive to jqGrid
		$(window).bind('resize', function() {
			var width = $('.jqGrid_wrapper').width();
			$('#table_list_3').setGridWidth(width);

		});
	},
	error : function(data, status) {

	}
});
var params4 = {};
params.type = "D";
$.ajax({
	url : "/sada/group/getlistgroup",
	type : "GET",
	data : params,
	dataType : "json",
	success : function(data, status) {
		var list = data.rows;
		// Examle data for jqGrid					
		$("#table_list_4").jqGrid(
				{
					data : list,
					datatype : "local",
					height : "auto",
					autowidth : true,
					shrinkToFit : true,
					rowNum : 14,
					rowList : [ 10, 20, 30 ],
					colNames : [ 'group1_code', 'group_type', 'cell_code',
							'record_time' ],
					colModel : [ {
						name : 'group1_code',
						index : 'group1_code',
						width : 60
					}, {
						name : 'group_type',
						index : 'group_type',
						width : 60
					}, {
						name : 'cell_code',
						index : 'cell_code',
						width : 60
					}, {
						name : 'record_time',
						index : 'record_time',
						width : 60
					} ],
					pager : "#pager_list_4",
					viewrecords : true,
					hidegrid : false,
					gridComplete:function(){
			        	//获取某列的每一行id
			        	var ids = jQuery("#table_list_4").jqGrid("getDataIDs");
			        	for(var i=0;i<ids.length;i++){
			        	    var id = ids[i];
			        	    var names = $("#table_list_4").getCell(id,'cell_code');
			        	    var datas = $("#table_list_4").getCell(id,'cell_code');
			        	    /*var url="<a href='#' onclick='getdetail("+id+",&quot;"+datas+"&quot;)'>"+names+"</a>";*/
			        	    var url="<a href=/sada/detail/page/?name='"+datas+"'>"+names+"</a>";
			        	    $("#table_list_4").jqGrid('setRowData',id,{cell_code:url});
			        	}
			        }
				});
		// Add responsive to jqGrid
		$(window).bind('resize', function() {
			var width = $('.jqGrid_wrapper').width();
			$('#table_list_4').setGridWidth(width);

		});
	},
	error : function(data, status) {

	}
});
