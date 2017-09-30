/**
 * by dq 
 * 2017年9月14日下午8:56:35
 * TODO
 */
function bindDatePlug() {  
    $(".time").addClass('Wdate');  
    $(".time").on('focus', function() {  
        WdatePicker({  
            skin : 'whyGreen',  
            dateFmt : 'yyyy-MM-dd HH:mm:ss',  
            readOnly : true,  
            maxDate : "%y-%M-%d %H:%m:%s"  
        });  
    });  
}  
  
(function($) {    
    // jqgrid插件  
    $.hd_jqGrid = function(){};  
    $.extend($.hd_jqGrid, {  
        defaults : {  
            rightHtml : "<font color='green'>√</font>",  
            wrongHtml : "<font color='red'>×</font>",  
              
            date_yyyy_MM_dd_HH_mm_ss : "yyyy-MM-dd HH:mm:ss",  
            date_yyyy_MM_dd : "yyyy-MM-dd"  
        },  
          
        dateTimeFormatter : function(cellvalue, fmt) {  
            return (null != cellvalue && cellvalue > 0) ? $.method.dateFormat(cellvalue, fmt) : "";  
        },  
          
        statusFormatter : function(cellvalue) {  
            return cellvalue == 1 ? $.hd_jqGrid.defaults.rightHtml : $.hd_jqGrid.defaults.wrongHtml;  
        }  
    });  
})(jQuery);  
  
  
(function ($) {  
    $.method = function(){};  
    $.extend($.method, {          
        dateFormat : function( time, fmt) { // author: meizz  
            if(null == fmt || typeof fmt == "undefined" || $.trim(fmt).length == 0){  
                fmt = "yyyy-MM-dd HH:mm:ss";  
            }  
              
            if(typeof time == "number"){  
                time = new Date(time);  
            }  
              
            var o = {  
                "M+" : time.getMonth() + 1, // 月份  
                "d+" : time.getDate(), // 日  
                "h+" : time.getHours(), // 小时  
                "H+" : time.getHours(), // 小时  
                "m+" : time.getMinutes(), // 分  
                "s+" : time.getSeconds(), // 秒  
                "q+" : Math.floor((time.getMonth() + 3) / 3), // 季度  
                "S" : time.getMilliseconds() // 毫秒  
            };  
            if (/(y+)/.test(fmt)) {  
                fmt = fmt.replace(RegExp.$1, (time.getFullYear() + "").substr(4 - RegExp.$1.length));  
            }  
                  
            for ( var k in o) {  
                if (new RegExp("(" + k + ")").test(fmt)) {  
                    fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));  
                }  
            }  
            return fmt;  
        }  
    });  
})(jQuery);