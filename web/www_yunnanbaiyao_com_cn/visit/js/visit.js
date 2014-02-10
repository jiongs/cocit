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
		try {
			$("#entity_teamXlsFile").upload();
		} catch (e) {
		}
		try {
			switchTeamRegType();
		} catch (e) {
		}
		try {
			makeTeamMemberNames();
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
function getSmsVerifyCode2() {
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
		url : "/coc/getSmsVerifyCode2/" + tel,
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
		alert("请先点击“阅读活动声明”并选中“同意活动声明”，否则不能报名！");
		return;
	}

	var $btn = $(btn).attr("disabled", true).addClass("disabled_button");
	$.ajax({
		type : 'POST',
		url : "/coc/saveEntityRow/0:VisitActivityRegister:c/" + id,
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
function switchTeamRegType(flag) {
	var form = $("form");
	var elements = form[0].elements;
	if (typeof flag == "undefined") {
		flag = $('input[name="entity.teamRegType"]:checked').val()._int()
	}
	if (flag == 1) {// 上传excel团队名单
		$(".excelImportTeam").show();
		$(".onlineEditTeam").hide();
	} else {// 在线填写团队名单
		$(".excelImportTeam").hide();
		$(".onlineEditTeam").show();
	}
}
function openTeamMember() {
	$('#teamMemberDialog').show();
	var form = $("form");
	var elements = form[0].elements;
	elements["orderby"].value = "";
	elements["name"].value = "";
	elements["code"].value = "";
	// elements["teamMemberRole"].value = "";
	$(elements["sex"]).attr("checked", false);
	elements["tel"].value = "";
	elements["qq"].value = "";
	elements["email"].value = "";
	elements["unit"].value = "";
	elements["carCode"].value = "";
}

function addTeamMember() {
	var form = $("form");
	var elements = form[0].elements;
	var name = elements["name"].value;
	var code = elements["code"].value;
	var tel = elements["tel"].value;
	// var teamMemberRole = elements["teamMemberRole"].value;
	if (name.trim().length == 0) {
		alert("姓名必须填写！");
		elements["name"].focus();
		return;
	}
	if (!$(elements["sex"]).is(':checked')) {
		alert("性别必须填写！");
		return;
	}
	if (code.trim().length == 0) {
		alert("身份证号必须填写！");
		elements["code"].focus();
		return;
	}
	if (tel.trim().length == 0) {
		alert("手机号码必须填写！");
		elements["tel"].focus();
		return;
	}
	// if (teamMemberRole.trim().length == 0) {
	// alert("成员关系必须填写！");
	// elements["teamMemberRole"].focus();
	// return;
	// }

	var teamMembers = elements["entity.teamMembers"].value;
	var jsonMembers = [];
	if (teamMembers.trim().length > 0) {
		jsonMembers = teamMembers.toJson();
	}
	var member = {};
	var orderby = elements["orderby"].value;
	if (orderby.trim().length == 0) {
		orderby = jsonMembers.length;
	} else {
		orderby = ("" + orderby)._int();
		member = jsonMembers[orderby];
	}

	member["orderby"] = orderby;
	member["id"] = elements["id"].value._int();
	member["name"] = name;
	member["code"] = code;
	// member["teamMemberRole"] = teamMemberRole;
	member["sex"] = $('input[name=sex]:checked').val()._int();
	member["tel"] = elements["tel"].value;
	member["qq"] = elements["qq"].value;
	member["email"] = elements["email"].value;
	member["unit"] = elements["unit"].value;
	member["carCode"] = elements["carCode"].value;

	jsonMembers[orderby] = member;
	elements["entity.teamMembers"].value = $.toJsonString(jsonMembers);

	makeTeamMemberNames();

	$('#teamMemberDialog').hide();
}

function editTeamMember(orderby) {
	$('#teamMemberDialog').show();
	var form = $("form");
	var elements = form[0].elements;

	var teamMembers = elements["entity.teamMembers"].value;
	var jsonMembers = [];
	if (teamMembers.trim().length > 0) {
		jsonMembers = teamMembers.toJson();
	}
	var member = jsonMembers[orderby];
	elements["orderby"].value = "" + orderby;
	elements["id"].value = member["id"];
	elements["name"].value = member["name"];
	elements["code"].value = member["code"];
	// elements["teamMemberRole"].value = member["teamMemberRole"];
	$('input:radio[name=sex]')[member["sex"]].checked = true;
	elements["tel"].value = member["tel"];
	elements["qq"].value = member["qq"];
	elements["email"].value = member["email"];
	elements["unit"].value = member["unit"];
	elements["carCode"].value = member["carCode"];
}

function deleteTeamMember(orderby) {
	var form = $("form");
	var elements = form[0].elements;
	var teamMembers = elements["entity.teamMembers"].value;
	var jsonMembers = [];
	if (teamMembers.trim().length > 0) {
		jsonMembers = teamMembers.toJson();
	}
	var member = jsonMembers[orderby];
	// if (member["id"] && member["id"].trim().length > 0) {
	member["status"] = 9;
	// } else {
	// jsonMembers.splice(orderby, 1);
	// }

	elements["entity.teamMembers"].value = $.toJsonString(jsonMembers);

	makeTeamMemberNames();
}
function makeTeamMemberNames() {
	var form = $("form");
	var elements = form[0].elements;
	var teamMembers = elements["entity.teamMembers"].value;
	var jsonMembers = [];
	if (teamMembers.trim().length > 0) {
		jsonMembers = teamMembers.toJson();
	}

	var $names = $("#teamMembersNames").html("");
	var $table = $("<table width=\"100%\"></table>").appendTo($names);
	var count = 0;
	var $tr;
	for ( var i = 0; i < jsonMembers.length; i++) {
		var mem = jsonMembers[i];
		if (mem["status"] && mem["status"] == 9) {
			continue;
		}
		if (count % 3 == 0) {
			$tr = $("<tr></tr>").appendTo($table);
		}
		count++;

		var one = $("<span class=\"reg_member\"><span onclick=\"editTeamMember(" + i + ")\"> " + mem["name"] + " </span>&nbsp;<span onclick=\"deleteTeamMember(" + i
				+ ")\" class=\"del_member\" style=\"display:none;padding: 0;\">X</span></span>");

		var $td = $("<td width=\"33%\"></td>").appendTo($tr);
		$td.hover(function() {
			$(".del_member", this).show();
		}, function() {
			$(".del_member", this).hide();
		});
		$td.append(one);
	}

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

function checkHeartbeat_coc() {
	$.ajax({
		type : 'POST',
		url : "/coc/chkHeartbeat/" + new Date().getTime(),
		async : true,
		data : "",
		dataType : "json",
		success : function(json) {
			_sto(checkHeartbeat, 10800000);//3*60*60*1000
		},
		error : function(jqXHR, statusText, responseError) {
			alert("访问服务器出错！"+responseError);
		}
	});
}
checkHeartbeat_coc();