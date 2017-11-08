/**
 * 离线数据导入的js
 */
$(function(){
$("#form1").submit(function(e) {
    $("#progress").css("display", "inline");
    $("#submit").attr("disabled", true);
});
$("#form2").submit(function() {
    $("#progress1").css("display", "inline");
    $("#submit1").attr("disabled", true);
});
});
