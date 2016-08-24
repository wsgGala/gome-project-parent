function checkPhone (){

	var phone = $('#phone').val();
	/*if (phone == "") {
		$('#phone').focus().css({
			border: "1px solid red",
			boxShadow: "0 0 2px red"
		});
		$('#userCue').html("<font color='red'><b>×手机号不能为空</b></font>");

	}*/

}

//邮箱验证
function checkEmail () {

	var email = $("#email").val();


}

$(function(){

	/*$('#switch_qlogin').click(function() {
		project.controller.login();
	});
	$('#reg').click(function() {
		project.controller.register();
	});*/
	project.controller.login();
	project.controller.register();




});

var project = {

	controller : {

		register : function () {

			$('#reg').click(function() {

				var phone = $('#phone').val();
				if (phone == "") {
					$('#phone').focus().css({
						border: "1px solid red",
						boxShadow: "0 0 2px red"
					});
					$('#userCue').html("<font color='red'><b>×手机号不能为空</b></font>");
					return false;
				}else {
					$('#phone').css({
						border: "1px solid #D7D7D7",
						boxShadow: "none"
					});

				}


				var checkPhoen = /^1[3|4|5|7|8][0-9]{9}$/;
				if (!checkPhoen.test(phone)) {
					$('#phone').focus().css({
						border: "1px solid red",
						boxShadow: "0 0 2px red"
					});
					$('#userCue').html("<font color='red'><b>×手机号码不正确</b></font>");
					return false;

				}else {
					$('#phone').css({
						border: "1px solid #D7D7D7",
						boxShadow: "none"
					});

				}

				//邮箱验证
				var email = $('#email').val();
				var checkEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((.[a-zA-Z0-9_-]{2,3}){1,2})$/;
				if (!checkEmail.test(email)) {
					$('#email').focus().css({
						border: "1px solid red",
						boxShadow: "0 0 2px red"
					});
					$('#userCue').html("<font color='red'><b>×邮箱格式不正确</b></font>");
					return false;
				} else {
					$('#email').css({
						border: "1px solid #D7D7D7",
						boxShadow: "none"
					});

				}

				var pwdmin = 6;
				if ($('#password').val().length < pwdmin) {
					$('#password').focus();
					$('#userCue').html("<font color='red'><b>×密码不能小于" + pwdmin + "位</b></font>");
					return false;
				}
				if ($('#password2').val() != $('#password').val()) {
					$('#password2').focus();
					$('#userCue').html("<font color='red'><b>×两次密码不一致！</b></font>");
					return false;
				}

				//var sqq = /^[1-9]{1}[0-9]{4,9}$/;
				var yaoqingma = $('#yaoqingma').val();
				if (yaoqingma == "") {
					$('#yaoqingma').focus().css({
						border: "1px solid red",
						boxShadow: "0 0 2px red"
					});
					$('#userCue').html("<font color='red'><b>×请填写邀请码</b></font>");return false;
				} else {
					$('#yaoqingma').css({
						border: "1px solid #D7D7D7",
						boxShadow: "none"
					});

				}

				$('#regUser').submit();
			});

		},

		login : function () {



		}

	}

}

