(function($) {
	$(document).ready(function() {
		$("#entity_image").upload();
	});
})(jQuery);


function submitRegester(btn) {
	var form = $(btn.form);
	var elements=form[0].elements;
	if (elements["entity.name"].value.trim().length == 0) {
		alert("用户名称必须填写！");
		elements["entity.name"].focus();
		return;
	}
	if (elements["entity.code"].value.trim().length == 0) {
		alert("登录帐号必须填写！");
		elements["entity.code"].focus();
		return;
	}
	if (elements["entity.rawPassword"].value.trim().length == 0) {
		alert("登录密码必须填写！");
		elements["entity.rawPassword"].focus();
		return;
	}
	if (elements["entity.rawPassword2"].value.trim().length == 0) {
		alert("确认密码必须填写！");
		elements["entity.rawPassword2"].focus();
		return;
	}
	if (elements["entity.pwdQuestion"].value.trim().length == 0) {
		alert("密码问题必须填写！");
		elements["entity.pwdQuestion"].focus();
		return;
	}
	if (elements["entity.pwdAnswer"].value.trim().length == 0) {
		alert("密码答案必须填写！");
		elements["entity.pwdAnswer"].focus();
		return;
	}
	if (elements["entity.tel"].value.trim().length == 0) {
		alert("手机号码必须填写！");
		elements["entity.tel"].focus();
		return;
	}
	if (elements["entity.qq"].value.trim().length == 0) {
		alert("QQ号码必须填写！");
		elements["entity.qq"].focus();
		return;
	}
	
	var $btn = $(btn).attr("disabled", true).addClass("disabled_button");
	$.ajax({
		type : 'POST',
		url : "/coc/saveEntityRow/0:_WebUser:c/",
		async : false,
		data : $(form).serialize(),
		dataType : "json",
		success : function(json) {
			if (json.statusCode == 200) {
				alert("注册成功！");
				window.location.href = "/ui/22";
			} else {
				alert(json.message);
			}
		},
		error : function(jqXHR, statusText, responseError) {
			alert(responseError);
		},
		complete : function() {
			$btn.attr("disabled", false).removeClass("disabled_button");
		}
	});
}