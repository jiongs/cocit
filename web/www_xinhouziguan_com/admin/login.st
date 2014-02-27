<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>{$title}</title>
<script src="{$scriptPath}/jquery.js" type="text/javascript"></script>
<script src="{$scriptPath}/demsy.utils.js" type="text/javascript"></script>
<script src="{$scriptPath}/demsy.ui.js" type="text/javascript"></script>
<script type="text/javascript">
function logout(){
    $.post("{$contextPath}/logout", null, function(json) {
        if(json.success){
            if(json.redirect){
                window.parent.location.href=json.redirect+"?_date_="+new Date().getTime();
            }else{
                window.parent.location.href="{$contextPath}/admin?_date_="+new Date().getTime();
            }
        }else{
            alert(json.message);
        }
    }, "json");
}
</script>
</head>

<style>
html,body {
	font-size: 12px;
	padding: 0px;
	margin: 0px;
	height: 100%;
	width: 100%;
}
a{
	color: #346699;
	text-decoration: none;
}
a:hover{
	text-decoration: underline;
}

.login_box {
    background: url(/upload/xinhouziguan/images/admin/login-bg.png) no-repeat center top;
    border: 1px solid #a3a3a3;
	text-align: left;
	width: 936px;
	height: 500px;
	align: left;
	position: relative;
	top: 50px;
	padding: 180px 0 0 0;
}
.login_logo{
    position: absolute; 
    top: 225px;
    left: 150px;
    width: 190px; 
    height:190px; 
    background: url(/upload/xinhouziguan/images/admin/logo.png) no-repeat center center;
}
.login_line{
    position: absolute; 
    top: 225px;
    left: 360px;
    width: 3px; 
    height:267px;
    background: url(/upload/xinhouziguan/images/admin/line.png) no-repeat center center;
}
.login_lock{
    position: absolute; 
    top: 260px;
    left: 630px;
    width: 73px; 
    height: 91px;
    background: url(/upload/xinhouziguan/images/admin/lock.png) no-repeat center center;
}
.login_form{
    position: absolute; 
    top: 225px;
    left: 380px;
    width: 400px; 
    height:300px;
}
.login_title{
    background: url(/upload/xinhouziguan/images/admin/icon1.png) no-repeat left center;
    padding: 0 0 0 25px;
    height: 36px;
    color: #000000;
}
.login_submit_button{
    background: url(/upload/xinhouziguan/images/admin/button.png) no-repeat left center;
    height: 24px;
    width: 60px;
    border: 0;
    cursor: pointer;
}
.login_desc td{
    padding-top: 20px;
    line-height: 20px;
    font-size: 12px;
}
.login_desc .label{
    color: #000000;
}
.login_errorinfo {
	position: relative;
	top: -20px;
	text-align: left;
	align: left;
	width: 772px;
	color: red;
	font-size: 15px;
	text-align: left;
}

.label {
	color: #346699;
	text-decoration: none;
	width: 50px;
	text-align: right;
	padding: 10px;
}

.input {
	height: 25px;
}
.input input {
	width: 150px;
	height: 18px;
	border: 1px solid #346699;
    font-size: 12px;
}

.input select {
    font-size: 12px;
	width: 152px;
	height: 22px;
	border: 1px solid #346699;
}
.input input.checkbox{
    width: 16px;
    height: 16px;
    margin: 10px 0 10px 0;
    border: 0;
    padding: 0;
}
</style>
<body class="login_body">
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>&nbsp;</td>
			<td width="50%">
				<div class="login_box">
				    <div class="login_logo">&nbsp;</div>
                    <div class="login_line">&nbsp;</div>
                    <div class="login_lock">&nbsp;</div>
				    <div class="login_form">
					{if $user != null}
    					<table width="100%" border="0" align="center" cellpadding="1" cellspacing="1">
                            <tr>
                                    <td colspan=2 class="login_title">登录</td>
                            </tr>
    						<tr>
    							<td class="label"></td>
    							<td class="input">{$user.username} 已经登录! </td>
    						</tr>
    						<tr>
    							<td class="label"></td>
    							<td class="input"><a href="javascript:logout();">重新登录</a>&nbsp;&nbsp;<a target="_parent" href="{$contextPath}/admin">进入系统</a></td>
    						</tr>
    					</table>
					{else}
    					<form onsubmit="return false;">
    						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td colspan=2 class="login_title">登录</td>
                                </tr>
    							<tr>
    								<td class="label">类型：</td>
    								<td class="input"><select id="_loginrealm_" name="_loginrealm_">
                                            <option value="01">管理员登录</option>
                                            <option value=""></option>
    								</select></td>
    							</tr>
    							<tr>
    								<td class="label">帐号：</td>
    								<td class="input"><input type="text" id="_loginuser_" name="_loginuser_" /></td>
    							</tr>
    							<tr>
    								<td class="label">密码：</td>
    								<td class="input"><input type="password" id="_loginpwd_" name="_loginpwd_" /></td>
    							</tr>
                                <tr>
                                    <td class="label"></td>
                                    <td class="input">
                                    	<table><tr>
                                    		<td><input type="checkbox" class="checkbox" id="rememberMe" name="rememberMe" /></td>
                                    		<td><label for="rememberMe">记住密码</label></td>
                                    		</tr></table>
                                    </td>
                                </tr>
    							<tr align="left">
    								<td align="right">&nbsp;</td>
    								<td style="padding: 5px 0 0 0;">
    								    <input class="login_submit_button" onclick='this.form.action="{$contextPath}/login"' name="Submit" type="submit" value="" />
    								</td>
    							</tr>
                                <tr class="login_desc">
                                    <td class="label" valign="top">注意：</td>
                                    <td>
                                        <div>1、不要在公共场合保留登录信息。</div>
                                        <div>2、尽量避免多人使用同一帐号。</div>
                                        <div>3、为保证您的帐号安全，离开电脑时请注销登录。</div>
                                    </td>
                                </tr>
    						</table>
    					</form>
    				{/if}
				</div>
			</td>
			<td>&nbsp;</td>
		</tr>
	</table>
</body>
</html>

<script type="text/javascript">
	// Read username and password from cookie
	var form = document.forms[0];
	$("#rememberMe", form).each(function() {
		var _loginuser_ = getCookie("_loginuser_");
		if (_loginuser_) {
			this.checked = true;
			$("#_loginrealm_", form).val(getCookie("_loginrealm_"));
			$("#_loginuser_", form).val(_loginuser_);
			$("#_loginpwd_", form).val(getCookie("_loginpwd_"));
		}
	});
	$(".login_submit_button").click(function() {
		var form = this.form;
        var $form=$(form);
        $form.block();
        var data=$form.serialize()+"&clientWidth="+document.body.clientWidth+"&clientHeight="+document.body.clientHeight;
        $.post(this.form.action, data, function(json) {
            if(json.success){
						
				$("#rememberMe", $form).each(function() {
					if (this.checked) {
						setCookie("_loginrealm_", form["_loginrealm_"].value);
						setCookie("_loginuser_", form["_loginuser_"].value);
						setCookie("_loginpwd_", form["_loginpwd_"].value);
					} else {
						removeCookie("_loginrealm_");
						removeCookie("_loginuser_");
						removeCookie("_loginpwd_");
					}
				});
						
	            if(json.redirect){
	                window.location.href=json.redirect;
	            }else{
	            	window.location.href="{$contextPath}/admin";
	            }
            }else{
                alert(json.message);
            }
		}, "json");
        

		return false;
	});

</script>

<link href="{$stylePath}/jquery.ui.core.css" rel="stylesheet" type="text/css" media="screen" />
<link href="{$stylePath}/jquery.ui.theme.css" rel="stylesheet" type="text/css" media="screen" />
<link href="{$stylePath}/jquery.ui.button.css" rel="stylesheet" type="text/css" media="screen" />
<link href="{$stylePath}/demsy.bizform.css" rel="stylesheet" type="text/css" media="screen" />

<script src="{$scriptPath}/jquery.ui.core.js" type="text/javascript"></script>
<script src="{$scriptPath}/jquery.ui.widget.js" type="text/javascript"></script>
<script src="{$scriptPath}/jquery.ui.button.js" type="text/javascript"></script>
