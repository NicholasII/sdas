/**
 * 预警页面
 * by dq 
 * 2017年10月13日下午9:10:31
 * TODO
 */
var alarmurl = ctx + "/alarm/all";

$(function(){
    $.jgrid.defaults.styleUI = 'Bootstrap';
    $.ajax({
        url: alarmurl,
        type:"GET",
        dataType:"json",
        success:function(data,status){
            var list = data.rows;
            refreshJqGrid(list);
        }
    });   
    $("#timeselect").change(function(){
        if ($("#timeselect").val()=='按时间段') {
            $("#starttime").removeAttr("disabled");
            $("#endtime").removeAttr("disabled");
            $("#starttime").attr("placeholder",'请输入开始时间');
        }else if($("#timeselect").val()=='按日期'){
            $("#endtime").attr("disabled",true);
            $("#starttime").attr("placeholder",'请输入发生时间');
        }else {
            $("#starttime").attr("disabled",true);
            $("#endtime").attr("disabled",true);
            $("#starttime").attr("placeholder",'请输入开始时间');
        }
    });
});

function refreshJqGrid(list){
    $("#table_list_1").jqGrid({
        data:list,
        datatype: "local",
        height: "auto",
        autowidth: true,
        shrinkToFit: true,
        rowNum: 10,
        rowList: [10, 20, 30],
        colNames: ['发生时间', '小区名称', '次数'],
        colModel: [
            {
                name: 'yyyyMMdd',
                index: 'yyyyMMdd',
                width: 150
            },
            {
                name: 'cell_code',
                index: 'cell_code',
                width: 250
            },
            {
                name: 'count',
                index: 'count',
                width: 50
            }
        ],
        pager: "#pager_list_1",
        viewrecords: true,
        hidegrid: false
    });
    $(window).bind('resize', function () {
        var width = $('.jqGrid_wrapper').width();
        $('#table_list_1').setGridWidth(width);

    });
}
function select(){
    var name = $("#name").val();
    var starttime = $("#starttime").val();
    var endtime = $("#endtime").val();
    $("#table_list_1").jqGrid("clearGridData");
    $.ajax({
        url:alarmurl,
        type:"post",
        dataType:"json",
        data:{
            'cellname':name,
            'starttime':starttime,
            'endtime':endtime,
        },
        success:function(data,status){
            var list = data.rows;
            $("#table_list_1").jqGrid('setGridParam',{
                  datatype:'local',
                  data : list,   
                  page:1
            }).trigger("reloadGrid");
        },
        error:function(data,status){
            alert(status);
        }
    });
}