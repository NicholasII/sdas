//点击图片，更换验证码
 $(function(){
     $('#kaptchaImage').click(function () {//生成验证码
     	$(this).hide().attr('src', ctx + '/kaptcha.jpg?'+Math.floor(Math.random()*100)).fadeIn();
 	    event.cancelBubble=true;
     });
 });

 //点击链接，更换验证码
 function changeCode() {
 	$('#kaptchaImage').hide().attr('src', ctx + '/kaptcha.jpg?'+Math.floor(Math.random()*100)).fadeIn();
 	event.cancelBubble=true;
 }


$(document).ready(function() {
	$('#defaultForm').bootstrapValidator({
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			username : {
				message : 'The username is not valid',
				validators : {
					notEmpty : {
						message : '请输入用户名'
					}
				}
			},
			password : {
				validators : {
					notEmpty : {
						message : '请输入密码'
					}
				}
			},
            kaptcha : {
                validators : {
                    notEmpty : {
                        message : '请输入验证码'
                    }
                }
            }
		}
	}).on('success.form.bv', function(e) {
		Encrypt("password");//密码加密
		$('#defaultForm').attr('action', ctx + '/main');
	});

});


//将密码用md5加密
function EncryptPassword(){
	Encrypt("password");
}