(function($) {
	$(document).ready(function() {
		$("#entity_image").upload();
	});
})(jQuery);
function validateUserForm(elements){
	if (elements["entity.name"].value.trim().length == 0) {
		alert("用户名称必须填写！");
		elements["entity.name"].focus();
		return falae;
	}
	if (elements["entity.code"].value.trim().length == 0) {
		alert("登录帐号必须填写！");
		elements["entity.code"].focus();
		return falae;
	}
	if (elements["entity.pwdQuestion"].value.trim().length == 0) {
		alert("密码问题必须填写！");
		elements["entity.pwdQuestion"].focus();
		return falae;
	}
	if (elements["entity.pwdAnswer"].value.trim().length == 0) {
		alert("密码答案必须填写！");
		elements["entity.pwdAnswer"].focus();
		return falae;
	}
	if (elements["entity.tel"].value.trim().length == 0) {
		alert("手机号码必须填写！");
		elements["entity.tel"].focus();
		return falae;
	}
	if (elements["entity.qq"].value.trim().length == 0) {
		alert("QQ号码必须填写！");
		elements["entity.qq"].focus();
		return falae;
	}
	if (elements["cocit_verify_code"].value.trim().length == 0) {
		alert("验证码必须填写！");
		elements["cocit_verify_code"].focus();
		return falae;
	}
	
	return true;
}
function submitModify(btn){
	var form = $(btn.form);
	var elements = form[0].elements;
	
	if (elements["entity.id"].value.trim().length == 0) {
		alert("您尚未登录，请先登录！");
		elements["entity.id"].focus();
		return falae;
	}
	
	if(!validateUserForm(elements)){
		return false;
	}

	var $btn = $(btn).attr("disabled", true).addClass("disabled_button");
	$.ajax({
		type : 'POST',
		url : "/coc/saveEntityRow/74:_WebUser:e/"+elements["entity.id"].value,
		async : false,
		data : $(form).serialize(),
		dataType : "json",
		success : function(json) {
			if (json.statusCode == 200) {
				alert("修改个人资料成功！");
				window.location.href = "/ui/21";
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
function submitRegester(btn) {
	var form = $(btn.form);
	var elements = form[0].elements;
	
	if (elements["entity.rawPassword"].value.trim().length == 0) {
		alert("登录密码必须填写！");
		elements["entity.rawPassword"].focus();
		return falae;
	}
	if (elements["entity.rawPassword2"].value.trim().length == 0) {
		alert("确认密码必须填写！");
		elements["entity.rawPassword2"].focus();
		return falae;
	}
	
	if(!validateUserForm(elements)){
		return false;
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