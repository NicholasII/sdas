/**
 * by dq 
 * 2017年9月22日上午11:38:47
 * TODO 通用的工具类
 */
var POST = "POST";
var GET = "GET";
var ERROR = "error";
var INFO = "info";
var JSON = "json";
var LABELS = ['default','success','primary','info','warning','danger'];
var SP = "&nbsp;&nbsp;";
/** 提示_无符合条件的记录 */
var NOT_FOUND_DATAS = '无符合条件的记录';
/** 提示_是否删除 */
var IF_DELETE_INFO = "是否要删除选中的行?";
/**
 * iframe间跳转
 * @param {} url 目标iframe地址
 * @param {} params 参数对象
 * @param {} iframe iframe名称
 */
function iframeconvert(url,iframe,params) {

    var a_parent = $(".page-tabs-content", window.parent.document);
    var iframe_parent = $("#content-main", window.parent.document);

    var target_url = url;
    if (params != undefined) {
		for (var i = 0; i < params.length; i++) {
			if (i == 0) {
				target_url += "?" + params[i].key + "=" + params[i].value;
			} else {
				target_url += "&" + params[i].key + "=" + params[i].value;
			}
		}
	}
    
    var item = $('<a href="#" class="active J_menuTab" data-id="'
            + url + '">'+iframe+' <i class="fa fa-times-circle"></i></a>');
    var content = $('<iframe class="J_iframe" name="iframe10" width="100%" height="100%" src="'
            + target_url + '" frameborder="0" data-id="' + url + '" seamless></iframe>');

    a_parent.children("a").removeClass("active");

    if (a_parent.has('a[data-id="' + url + '"]').length > 0) {
        a_parent.children('a[data-id="' + url + '"]').addClass("active");
        iframe_parent.children("iframe").css("display", "none");
        iframe_parent.children().remove('iframe[data-id="' + url + '"]');
        content.css("display", "inline");
        iframe_parent.prepend(content);
    } else {
        content.css("display", "inline");
        a_parent.append(item);
        iframe_parent.children("iframe").css("display", "none");
        iframe_parent.prepend(content);
    }
}


/**
 * 非表格数据用ajax请求
 * 调用共同ajax方法，外部接口
 */
function doAjax(type, url, data, success) {
	docommonAjax(type, url, data, success);
}
/**
 * ajax共同方法
 * @param type get or post
 * @param url  请求地址
 * @param data 携带数据
 * @param success 成功后回调函数名称
 */
function docommonAjax(type, url, data, success) {
	$.ajax({
		type : type,
		url : url,
		data : data,
		success : function(response,status) {
			var result = response;
            if(typeof result == "string" &&result.constructor==String){
                var temp = eval('(' + result + ')'); 
                result = temp.rows
            }else{
                result = result.rows
            }
            eval(success)(result);//result可能是一个json数据或者是一个json对象，在传入的回调函数中解译
		},
		error : function(jqXHR, exception) {
			if (jqXHR.status === 0) {
				showOnlyMessage(ERROR, "服务器停止运行，请与管理员联系");
			} else if ((jqXHR.responseText).indexOf("401") > 0) {
				top.location.href = ctx + "/login/error?error=401";
			} else if ((jqXHR.responseText).indexOf("403") > 0) {
				top.location.href = ctx + "/login/error?error=403";
			} else if ((jqXHR.responseText).indexOf("500") > 0) {
				top.location.href = ctx + "/login/error?error=500";
			} else if (exception === 'parsererror') {
				showOnlyMessage(ERROR, "json数据解析错误");
			} else if (exception === 'timeout') {
				showOnlyMessage(ERROR, "请求超时，请重试");
			} else {
				showOnlyMessage(ERROR, "系统异常，请与管理员联系");
			}
		}
	});
}
/**
 * 获取表格分页信息
 * @param {} gridid
 * @param {} data
 * @param {} url
 * @param {} success
 * @param {} boolean
 */
function commonRowDatas(gridid, data, url, success, boolean) {
    if (boolean) {
        var options = $('#' + gridid).bootstrapTable('getOptions');
        // 获取当前页
        var currpage = options.pageNumber;
        if (currpage == 0) {
            currpage = 1;
        }
        // 获取当前页显示数据条数
        var pageSize = options.pageSize;
        data.currpage = currpage;
        data.pageSize = pageSize;
    }
    commonGridAjax('post', url, data, success, gridid, boolean);
}
/**
 * 表格数据ajax请求
 * @param {} type
 * @param {} url
 * @param {} data
 * @param {} success
 * @param {} gridid
 * @param {} boolean
 */
function commonGridAjax(type, url, data, success, gridid, boolean) {
    $.ajax({
        type : type,
        url : url,
        data : data,
        success : function(response) {
            var result = response;          
            if (success != null && success != "commonCallback") {
                eval(success)(response);
            } else if (success == "commonCallback") {
                if(typeof result == "string" &&result.constructor==String){
                    var temp = eval('(' + result + ')'); 
                    result = temp.rows
                }else{
                    result = result.rows
                }             
                commonCallback(result, gridid, url, data, boolean);
            }
        },
        error : function(jqXHR, exception) {
            if (jqXHR.status === 0) {
                showOnlyMessage(ERROR, "服务器停止运行，请与管理员联系");
            } else if ((jqXHR.responseText).indexOf("401") > 0) {
                top.location.href = contextPath + "/login/error?error=401";
            } else if ((jqXHR.responseText).indexOf("403") > 0) {
                top.location.href = contextPath + "/login/error?error=403";
            } else if ((jqXHR.responseText).indexOf("500") > 0) {
                top.location.href = contextPath + "/login/error?error=500";
            } else if (exception === 'parsererror') {
                showOnlyMessage(ERROR, "json数据解析错误");
            } else if (exception === 'timeout') {
                showOnlyMessage(ERROR, "请求超时，请重试");
            } else {
                showOnlyMessage(ERROR, "系统异常，请与管理员联系");
            }
        }

    });
}
/**
 * 通用表格数据ajax请求后回调函数
 * @param {} response
 * @param {} gridid
 * @param {} url
 * @param {} data
 * @param {} boolean
 */
function commonCallback(response, gridid, url, data, boolean) {
    if (gridid != null && gridid != "") {
        $("#" + gridid).bootstrapTable('load', response);
        // db中数据被删除了，检索的后一页没有数据，页面显示前一页的数据
        /*if(boolean){
            if (response.rows.length == 0 && response.total > 0) {
                data.currpage = data.currpage - 1;
                commonRowDatas(gridid, data, url, "commonCallback", boolean);
            }
        }*/
    }
}
/**
 * 共同取得一览数据的方法
 * @param gridid 表格id
 * @param data 请求参数
 * @param url 请求url
 * @param success 回调函数
 * @param boolean 是否分页
 */

//弹出后台传回的消息消息
//供内部调用
function showErrMsgFromBack(type, errList) {
	var str = "";
	for ( var i = 0; i < errList.length; i++) {
		str += (errList[i] + "</br>");
	}
	// 错误消息提示
	if (type == ERROR) {
		$.notify({
			icon : 'glyphicon glyphicon-remove-sign',
			title : '<strong>Error:</strong>',
			message : str
		}, {
			type : 'danger',
			placement : {
				from : "top",
				align : "center"
			}
		});
	}
	// 消息提示
	else if (type == "info") {
		$.notify({
			icon : 'glyphicon glyphicon-ok-sign',
			title : '<strong>Info:</strong>',
			message : str
		}, {
			type : 'success',
			placement : {
				from : "top",
				align : "center"
			}
		});
	}
	// 警告消息提示
	else if (type == "warning") {
		$.notify({
			icon : 'glyphicon glyphicon-warning-sign',
			title : '<strong>Warning:</strong>',
			message : str
		}, {
			type : 'warning',
			placement : {
				from : "top",
				align : "center"
			}
		});
	} else {
		$.notify({
			icon : 'glyphicon glyphicon-ok-sign',
			title : '<strong>Info:</strong>',
			message : str
		}, {
			type : 'success',
			placement : {
				from : "top",
				align : "center"
			}
		});
	}
}
/**
 * 获取form中的数据，并将其转换成ajax需要的数据格式
 * @param {} formId
 * @return {}
 */
function getFormJson(formId) {
	var o = {};
	var fid = "#" + formId;
	var a = $(fid).serializeArray();
	$.each(a, function() {
		if (o[this.name] !== undefined) {
			if (!o[this.name].push) {
				o[this.name] = [ o[this.name] ];
			}
			o[this.name].push(this.value.trim() || '');
		} else {
			o[this.name] = this.value.trim() || '';
		}
	});
	return o;
}
/**
 * 弹出确认框
 * @param funName
 * @param msg
 * @param type
 * @param url
 * @param date
 * @param success
 */
function showConfirm(funName, msg, type, url, date, success) {
    bootbox.setLocale("zh_CN");
    bootbox.confirm(msg, function(result) {
        if (result) {
            eval(funName)(type, url, date, success);
        }
    });
}
/**
 * 弹出提示消息
 * @param type
 * @param message
 */
function showOnlyMessage(type, message) {
    showParamMessage(type, message);
}
/**
 * 详情btn
 * @param value
 * @returns {String}
 */
function detailBtn(value) {
    //var html = '<span class="label label-sm label-info"><a href="#" onclick="checkDetail(\'' + value + '\')"><i class="fa fa-info"></i>详情</a></span>';
    var html = '<a href="javascript:;" class="btn btn-outline btn-circle btn-sm purple" onclick="checkDetail(\'' + value + '\')"><i class="fa fa-info"></i>详情</a>';
    return html;
}

/**
 * 编辑btn
 * @param value
 * @returns {String}
 */
function editBtn(value) {
    var html = '<a href="javascript:;" class="btn btn-outline btn-circle btn-sm purple" onclick="editDetail(\'' + value + '\')"><i class="fa fa-edit"></i>编辑</a>';
    return html;
}
/**
 * 获取选表格中行
 * @param {} id
 * @return {}
 */ 
function selectedRows(id) {
    var rowsObj = $('#' + id).bootstrapTable('getSelections');
    return rowsObj;
}
/** 
 * 获取表格选中的行数
 * @param {} id
 * @return {}
 */
function selectedCount(id) {
    var rows = $('#' + id).bootstrapTable('getAllSelections').length;
    return rows;
}
/**
 * 对input框进行md5加密
 * @param id
 */
function Encrypt(id) {
    if ($("#" + id).val() != null && $("#" + id).val() != "") {
        $("#" + id).val($.md5($("#" + id).val()));
    }
}