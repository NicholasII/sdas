<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<%@ include file="/include/common.jsp"%>
<title>广州移动基站数据分析系统</title>
<!-- <title>基于大数据的基站小区智能维护系统</title> -->
<link href="${context}/lib/hplus/css/login.css" rel="stylesheet">
<script>
        if(window.top!==window.self){window.top.location=window.location};
    </script>
<script type="text/javascript" src="${context}/js/login/login.js"></script>

</head>

<body class="signin">
	<div class="signinpanel">
		<div class="row">
			<div class="col-sm-5 loginform"
				style="position: fixed; padding-top: 40px;">
				<div
					style="width: 640px; position: fixed; margin-top: -130px; margin-left: -200px; height: 80px">
					<div class="signin-info" style="height: 100%">
						<div class="logopanel m-b">
							<img src="${context}/lib/hplus/img/logo_1.jpg"
								style="float: left; margin-right: 10px; border-radius: 15px; top: 12px">
							<h1 style="float: left">广州移动基站维护数据分析系统</h1>
							<!-- <h1 style="float: left">基于大数据的基站小区智能维护系统</h1> -->
						</div>
						<!--  <h4>欢迎使用: <strong>广州移动基站维护数据分析系统</strong></h4> -->

					</div>
				</div>
				<form id="defaultForm" method="post">
					<div class="form-group" style="margin-top: 1px">
						<lable class="sr-only" for="username">用户名</lable>
						<input type="text" name="username" id="username"
							class="form-control uname" placeholder="用户名" />
					</div>
					<div class="form-group">
						<lable class="sr-only" for="password">密码</lable>
						<input type="password" name="password" id="password"
							class="form-control pword m-b" placeholder="密码" />
					</div>
					<div class="row">
						<div class="col-xs-5">
							<input style="color: black;" type="text" name="kaptcha"
								id="kaptcha" class="form-control" maxlength="4"
								placeholder="验证码" />
						</div>
						<div class="col-xs-5" style="padding: 0px; height: 20px;">
							<img src="kaptcha.jpg" id="kaptchaImage" />
						</div>
					</div>
					<div class="row">
						<div class="clearfix"></div>
					</div>
					<c:if test="${not empty errormsg}">
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
					</c:if>
					<div class="row">
						<!-- <div class="col-xs-5" align="left">
							<a href="#" style="color:#fff">忘记密码?</a>
						</div> -->
						<div class="col-xs-6" align="left">
							<a href="#" onclick="changeCode()" class="text-right">看不清?换一张</a>
						</div>
					</div>
					<button type="submit" class="btn btn-success btn-block">登录</button>
				</form>
			</div>
		</div>
		<div class="signup-footer">
			<div class="pull-left">
				<!--  &copy; 2015 All Rights Reserved. -->
			</div>
		</div>
	</div>
</body>

</html>