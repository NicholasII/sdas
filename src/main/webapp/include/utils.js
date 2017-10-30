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
/**
 * 从子iframe跳转到另一个iframe
 */
function iframeconvert(url,name,param){
	var a_parent = $(".page-tabs-content",window.parent.document);  //父ifream <a>
	var iframe_parent = $("#content-main",window.parent.document);  //父iframe <iframe>
	
	var item = $('<a href="javascript:;" class="active J_menuTab" data-id="'+url+'">'+name+' <i class="fa fa-times-circle"></i></a>');
	var content = $('<iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="'+url+'?name='+param+'" frameborder="0" data-id="'+url+'" seamless></iframe>');
	
	a_parent.children("a").removeClass("active");
	

	if(a_parent.has('a[data-id="'+url+'"]').length>0){ //当父窗口中有子ifream
		
		a_parent.children('a[data-id="'+url+'"]').addClass("active");
		iframe_parent.children("iframe").css("display","none");
		iframe_parent.children().remove('iframe[data-id="'+url+'"]');
		content.css("display","inline");
		iframe_parent.prepend(content);
	}else {//父窗口中木有子ifream
		
		content.css("display","inline");
		a_parent.append(item);
		iframe_parent.children("iframe").css("display","none");
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
 * @param type
 * @param url
 * @param data
 * @param success
 */
function docommonAjax(type, url, data, success) {
	$.ajax({
		type : type,
		url : url,
		data : data,
		dataType : JSON,
		success : function(response,status) {
			var result = response;
			/*// 消息
			var errList = result.errList;
			// 返回的消息类型
			var errType = result.errType;
			// 后台有消息返回
			if (errList != undefined && errList.length != 0) {
				showErrMsgFromBack(errType, errList); // 显示错误消息
				// 回调函数
				eval(success)(response);
				return;
			} else {
				// 回调函数
				eval(success)(response);
			}*/
		},
		error : function(jqXHR, exception) {
			/*if (jqXHR.status === 0) {
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
			}*/
		}
	});
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
//获取form中的数据，并将其转换成ajax需要的数据格式
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
 * 共同回调函数
 * @param response 后台传回的数据
 * @param gridid 表格控件的id
 * @param boolean 是否需要分页条件
 */
