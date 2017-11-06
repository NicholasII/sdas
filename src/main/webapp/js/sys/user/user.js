/**
 * 用户管理js
 */
var api_getPagedList = ctx + '/system/user/list';
var api_insertUrl = ctx + '/system/user/insert';
var api_detail = ctx + '/system/user/one';
var api_deleteSelected = ctx + '/system/user/delete';
$(function(){
    /*$('#modalForm').bootstrapValidator({
        feedbackIcons : {
            valid : 'glyphicon glyphicon-ok',
            invalid : 'glyphicon glyphicon-remove',
            validating : 'glyphicon glyphicon-refresh'
        },
        fields : {
            userId : {
                validators : {
                    notEmpty : { message : $message("ErrorMustInput",[ '用户ID' ]) },
                    stringLength: { min: 5,  max: 16,
                        message: $message("ErrorLength2",['用户ID','5','16']) }
                } 
            },
            password : {
                validators : { notEmpty : { message : $message("ErrorMustInput",[ '密码' ]) } }
            },
            username : {
                validators : {
                    notEmpty : { message : $message("ErrorMustInput",[ '用户名' ]) },
                    stringLength: { min: 2,  max: 16, message: $message("ErrorLength2",['用户名','2','16']) } 
                } 
            },
            email : {
                validators : {
                    emailAddress: { message: $message("ErrorFormat",['邮箱']) },
                    stringLength: { min: 0, max: 50, message: $message("ErrorLength",['邮箱','50'])  }
                }
            },
            address : {
                validators : {
                    stringLength: {  min: 0, max: 255, message: $message("ErrorLength",['地址','255']) }
                }
            },
            mobile : {
                validators : {
                    stringLength: {  min: 0, max: 11, message: $message("ErrorLength",['手机','11']) },
                    regexp: { regexp: /^(1[0-9])\d{9}$/, message: $message("ErrorFormat",['手机号码']) } 
                }
            },
            tel : {
                validators : {
                    stringLength: { min: 0, max: 13, message: $message("ErrorLength",['电话号码','13']) },
                    regexp: { regexp: /^(0[0-9]{2,3}-)?([2-9][0-9]{6,7})$/, message: $message("ErrorFormat",['电话号码']) } 
                }
            },
            rolename : {
                validators : { notEmpty : { message : $message("ErrorMustInput",[ '角色' ]) } }
            },
            birthday : {
                validators : {
                        notEmpty : { message : $message("ErrorMustInput",[ '生日' ]) },
                        date: { format: 'YYYY-MM-DD hh:mm:ss', message: $message("ErrorFormat",[ '生日' ]) }
                }
            }
        }
    }).on('success.form.bv', function(e) {
        e.preventDefault();
        var $form = $(e.target);
        var bv = $form.data('bootstrapValidator');
        if($("#isNew").val()== 0) {
            //编辑保存
            doAjax(POST,api_saveEdit,$form.serialize(),saveSuccess);
        }
        else if($("#isNew").val() == 1) {
            //新增保存
            doAjax(POST,api_newSave,$form.serialize(),saveSuccess);
        }
    });*/
    
    $('#userInfoTable').bootstrapTable({
        cache : false,
        striped : true,
        pagination : true,
        toolbar : '#toolbar',
        pageSize : 5,
        pageNumber : 1,
        pageList : [ 5, 10, 20 ],
        clickToSelect : true,
        sidePagination : 'server',// 设置为服务器端分页
        columns : [ 
            { field : "", checkbox : true },
            { field : "userId", title : "用户ID", align : "center", valign : "middle" },
            { field : "username", title : "用户名", align : "center", valign : "middle" },
            { field : 'rolename', title : '角色', align : "center", valign : "middle" },
            { field : "email", title : "邮箱", align : "center", valign : "middle" },
            { field : "birthday", title : "生日", align : "center", valign : "middle" },
            { field : "address", title : "地址", align : "center", valign : "middle" },
            { field : "tel", title : "座机", align : "center", valign : "middle" },
            { field : "mobile", title : "手机", align : "center", valign : "middle" },
            { field : 'userLocked', title : '用户账号状态', align : "center", valign : "middle",formatter:function(data,row){
                if(data==0){
                    return "正常";
                }else{
                    return "锁定";
                }
            } },
            { field : "opration", title : "操作", align : "center", valign : "middle",
                formatter : function(value, row, index) {
                    return detailBtn(row.userId);
                }
            } 
        ],
        onPageChange : function(size, number) {
            searchUserInfo();
        },
        formatNoMatches : function() {
            return NOT_FOUND_DATAS;
        }
    });
    
    searchUserInfo();
});
function searchUserInfo() {
    var data = getFormJson("searchForm");//获取查询条件
    commonRowDatas("userInfoTable", data, api_getPagedList, "commonCallback", true);
}
//查看用户详细信息
function checkDetail(userId) {
    var data={
        userId:userId
    };
    doAjax(POST, api_detail, data, checkDetailSuccess)
}
//新增用户
function addNew(){
    $('#title').html('');
    $('#title').append("添加用户");//设置modal的标题
    //$('#userBirthday').val('');
    $("#isNew").val('1');
    $('#myModal').modal({show:true,backdrop: 'static', keyboard: false});
}
// 关闭modal画面
function closemodal() {
    // 关闭前先清空modal画面的form表单
    //$('#modalForm').data('bootstrapValidator').resetForm(true);
    //将hidden项清空
    $("#isNew").val('');
    $("#orgId").val('');
    $("#roleId").val('');
    $('#title').html('');//设置modal的标题
    $("#modalForm #userId").removeAttr("readonly");
    $('#myModal').modal('hide');
}
//插入数据
function insert(){
     var data = getFormJson("modalForm");//获取查询条件
     $.ajax({
        url : api_insertUrl,
        type : 'post',
        data : data,
        success : function(data,status){
            searchUserInfo();
        }
     })
}
// 点击编辑按钮向后台请求要查询的数据
function editRow() {
    var selectRows = selectedCount("userInfoTable");
    if (selectRows == 0){
        showOnlyMessage(ERROR,$message("ErrorNoSelectEdit",null));
    }else if(selectRows > 1){
        showOnlyMessage(ERROR,$message("ErrorSelectMultiEdit",null));
    }
    else{
        var row = selectedRows("userInfoTable");
        $("#isNew").val('0');
        checkDetail(row[0].userId);
    }
}
//查看用户详细信息
function checkDetail(userId) {
    var data={
        userId:userId
    };
    doAjax(POST, api_detail, data, checkDetailSuccess)
}

function checkDetailSuccess(response){
    if(response != null && response != undefined)    {
        $("#modalForm #userId").attr("readonly","readonly");
        $("#modalForm #userId").val(response.userId);
        $("#modalForm #password").val(response.password);
        $("#modalForm #address").val(response.address);
        $("#modalForm #tel").val(response.tel);
        $("#modalForm #birthday").val(response.birthday);
        $("#modalForm #username").val(response.username);
        $("#modalForm #email").val(response.email);
        $("#modalForm #mobile").val(response.mobile);
        $("#modalForm #rolename").val(response.rolename);
        $("#modalForm #userLocked").val(response.userLocked);
//        $('#modalForm #userLocked').selectpicker('refresh');
        $('#title').html('');
        $('#title').append("编辑用户信息");//设置modal的标题
        //$('#myModal').modal('show');
        $('#myModal').modal({show:true,backdrop: 'static', keyboard: false});
    }
}
//删除一行记录
function deleteRow() {
    var rowCount = selectedCount("userInfoTable");
    if (rowCount > 0) {
        // 获取选中行
        var rows = selectedRows("userInfoTable");
        var rowIds = "";
        for ( var i = 0; i < rows.length; i++) {
            rowIds += rows[i].userId + ",";
        }
        var data = {
            userId:rowIds       
        }
        showConfirm(sureDelete, IF_DELETE_INFO, POST, api_deleteSelected, data, searchUserInfo);
    } else {
        showOnlyMessage(ERROR, $message("ErrorSelectNoDelete", null));
    }

}
function sureDelete(type, url, data, success) {
    doAjax(POST, url, data, success);
}
//将密码用md5加密
function EncryptPassword(){
    Encrypt("password");
}