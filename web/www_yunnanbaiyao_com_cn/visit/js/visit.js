(function($) {
	$(document).ready(function() {

		$(".reg_day").hover(function() {
			if ("0" != $("span.reg_personNum", this).html()) {
				$(this).addClass("reg_hoverDay");
			}
		}, function() {
			$(this).removeClass("reg_hoverDay");
		}).click(function() {
			if ($(".reg_personNum", this).html() != "0") {
				var $this = $(this);
				if ($this.hasClass("invalid")) {
					alert($this.attr("title"));
				} else {
					$(".reg_selectedDay").removeClass("reg_selectedDay");
					$this.addClass("reg_selectedDay");
				}
			}
		});

		try {
			var topHeight = $("#top").height();
			var bottomHeight = $("#bottom").height();
			var pageHeight = $("#page").height();
			var windowHeight = document.body.clientHeight;
			var h = windowHeight - topHeight - bottomHeight - 90;
			if (pageHeight < h) {
				$("#page").height(h);
			}
		} catch (e) {
		}
	});
})(jQuery);

function submitRegStep1() {
	var day = $(".reg_selectedDay").attr("value");
	if (day) {
		var form = $("form");
		form[0].elements["guid"].value = day;
		form.submit();
	} else {
		alert("请先选择参观时间！");
	}
}
function checkRegStep2() {
	var form = $("form");
	if (form[0].elements["entity.activity.id"].value.length == 0) {
		// alert("请先选择参观时间！");
		window.location.href = "/jsp/visit:regstep1";
	}
}
function getSmsVerifyCode() {
	var form = $("form");
	var tel = form[0].elements["entity.tel"].value;
	if (tel.length == 0) {
		alert("请先填写手机号码！");
		form[0].elements["entity.tel"].focus();
		return false;
	}

	var btn = $(".reg_get_verify_code").attr("disabled", true).addClass("disabled_button");

	$.ajax({
		type : 'POST',
		url : "/coc/getSmsVerifyCode/" + tel,
		async : false,
		data : "",
		dataType : "json",
		success : function(json) {
			alert(json.message);
		},
		error : function(jqXHR, statusText, responseError) {
			alert(responseError);
		},
		complete : function() {
			btn.attr("disabled", false).removeClass("disabled_button");
		}
	});

	return false;
}
function submitRegStep2(btn) {
	var form = $("form");
	var id = form[0].elements["entity.id"].value;
	var name = form[0].elements["entity.name"].value;
	var code = form[0].elements["entity.code"].value;
	var tel = form[0].elements["entity.tel"].value;
	var telVerifyCode = form[0].elements["entity.telVerifyCode"].value;
	if (name.trim().length == 0) {
		alert("姓名必须填写！");
		form[0].elements["entity.name"].focus();
		return;
	}
	if (!$(form[0].elements["entity.sex"]).is(':checked')) {
		alert("性别必须填写！");
		return;
	}
	if (code.trim().length == 0) {
		alert("身份证号必须填写！");
		form[0].elements["entity.code"].focus();
		return;
	}
	if (tel.trim().length == 0) {
		alert("手机号码必须填写！");
		form[0].elements["entity.tel"].focus();
		return;
	}
	if (telVerifyCode.trim().length == 0) {
		alert("手机短信验证码必须填写！");
		form[0].elements["entity.telVerifyCode"].focus();
		return;
	}
	if (!$(form[0].elements["agree"]).is(':checked')) {
		alert("不同意活动声明，不能报名！");
		return;
	}

	var $btn = $(btn).attr("disabled", true).addClass("disabled_button");
	$.ajax({
		type : 'POST',
		url : "/coc/saveEntityFormData/0:VisitActivityRegister:c/" + id,
		async : false,
		data : $(form).serialize(),
		dataType : "json",
		success : function(json) {
			if (json.statusCode == 200) {
				window.location.href = "/jsp/visit:regstep3?guid=" + json.data.entityGuid;
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

function addTeamMember() {
	var form = $("form");
	var elements = form[0].elements;
	var name = elements["name"].value;
	var age = elements["age"].value;
	var relationship = elements["relationship"].value;
	if (name.trim().length == 0) {
		alert("姓名必须填写！");
		elements["name"].focus();
		return;
	}
	if (!$(elements["sex"]).is(':checked')) {
		alert("性别必须填写！");
		return;
	}
	if (age.trim().length == 0) {
		alert("年龄必须填写！");
		elements["age"].focus();
		return;
	}
	if (relationship.trim().length == 0) {
		alert("成员关系必须填写！");
		elements["relationship"].focus();
		return;
	}
	var member = {};
	member["name"] = name;
	member["age"] = age;
	member["relationship"] = relationship;
	member["sex"] = $(elements["sex"]).val();
	member["tel"] = elements["tel"].value;
	member["qq"] = elements["qq"].value;
	member["email"] = elements["email"].value;
	member["unit"] = elements["unit"].value;
	member["carCode"] = elements["carCode"].value;
	var teamMembers = elements["entity.teamMembers"].value;
	var jsonMembers = [];
	// alert("teamMembers: " + teamMembers);
	if (teamMembers.trim().length > 0) {
		jsonMembers = teamMembers.toJson();
		// alert("jsonMembers: " + jsonMembers);
	}
	// alert("jsonMembers.length: " + jsonMembers.length);
	var index = jsonMembers.length;
	jsonMembers[index] = member;
	elements["entity.teamMembers"].value = $.toJsonString(jsonMembers);
	
	var one = $("<span class=\"reg_member\" onclick=\"editTeamMember("+index+")\"></span>");
	one.html(name);
	$("#teamMembersNames").append("&nbsp;").append(one);
}

function query(btn) {

	var form = $("form");
	var tel = form[0].elements["entity.tel"].value;
	var telVerifyCode = form[0].elements["entity.telVerifyCode"].value;
	if (tel.trim().length == 0) {
		alert("手机号码必须填写！");
		form[0].elements["entity.tel"].focus();
		return false;
	}
	if (telVerifyCode.trim().length == 0) {
		alert("手机短信验证码必须填写！");
		form[0].elements["entity.telVerifyCode"].focus();
		return false;
	}

	return true;
}