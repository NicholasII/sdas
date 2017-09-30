<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
	<%@ include file="/include/common.jsp"%>
    <title>广州移动基站数据分析系统</title>
    <link href="${context}/lib/hplus/css/login.css" rel="stylesheet">
    <script>
        if(window.top!==window.self){window.top.location=window.location};
    </script>
    <script type="text/javascript" src="${context}/js/login/login.js"></script>

</head>

<body class="signin">
    <div class="signinpanel">
        <div class="row">
            <div class="col-sm-7">
                <div class="signin-info">
                    <div class="logopanel m-b">
                        <h1>广州移动基站数据分析系统</h1>
                    </div>
                    <div class="m-b"></div>
                    <h4>欢迎使用 <strong>广州移动基站数据分析系统</strong></h4>
                    <ul class="m-b">
                        <!-- <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势一</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势二</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势三</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势四</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势五</li> -->
                    </ul>
                    <strong>还没有账号? <a href="#">立即注册&raquo;</a></strong>
                </div>
            </div>
            <div class="col-sm-5">
                <form id="defaultForm" method="post" action="${context}/main">
                    <h4 class="no-margins">登录：</h4>
                    <p class="m-t-md">登录到SDAS后台系统</p>
                    <div class="form-group">
                    	<lable class="sr-only" for="username">用户名</lable>
                    	<input type="text" name="username" id="username" class="form-control uname" placeholder="用户名"/>
                    </div>
                    <div class="form-group">
                    	<lable class="sr-only" for="password">密码</lable>
                    	<input type="password" name="password" id="password" class="form-control pword m-b" placeholder="密码" />
                    </div>
                    <%-- <c:if test="${env != 'dev' }">
                    <div class="row" style="padding-bottom: 15px;">
                    	<div class="col-xs-5">
                    		<input type="text" name="kaptcha" id="kaptcha" class="form-control" maxlength="4" placeholder="验证码"/>
                    	</div>
                  		<div class="col-xs-*" style="padding: 0px;height: 32px;">
							<img src="${ctx }/getKaptchaImage.do?javascript:june.timestamp();" id="kaptchaImage" /> 
                    	</div>
                    </div>
                    </c:if> --%>
                    <div class="row">
                    	<div class="clearfix"></div>
                    </div>
					<%-- <c:if test="${not empty errormsg}">
						<div class="alert alert-danger">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							<strong>${errormsg}</strong>
						</div>
					</c:if>
					<c:if test="${not empty logout}">
						<div class="alert alert-success">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							<strong>${logout}</strong>
						</div>
					</c:if> --%>
					<div class="row">
						<div class="col-xs-5" align="left">
							<a href="#">忘记密码了?</a>
						</div>
                    	<%-- <c:if test="${env != 'dev' }">
						<div class="col-xs-6" align="right">
							<a href="#" onclick="changeCode()" class="text-right">看不清?换一张</a>
						</div>
						</c:if> --%>
					</div>
                    <button type="submit" class="btn btn-success btn-block">登录</button>
                </form>
            </div>
        </div>
        <div class="signup-footer">
            <div class="pull-left">
                &copy; 2015 All Rights Reserved.
            </div>
        </div>
    </div>
</body>

</html>