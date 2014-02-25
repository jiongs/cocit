<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>{$title}</title>
<script src="{$scriptPath}/jquery.js" type="text/javascript"></script>
<script src="{$scriptPath}/demsy.utils.js" type="text/javascript"></script>
<script type="text/javascript">
checkHeartbeat_demsy("{$jsessionid}");
try {
	var doc=window.parent.document;
	var INDEX_MIDDLE_COLS = doc.getElementById("middle").cols;
	var INDEX_ROWS = doc.getElementById("index").rows;
}
catch (r) {
}
function hideLeft() {
	try {
		INDEX_MIDDLE_COLS = doc.getElementById("middle").cols;
		doc.getElementById("middle").cols = "0,5,*";
	}
	catch (r) {
	}
}
function showLeft(width) {
	try {
		if (width) {
			INDEX_MIDDLE_COLS = width + ",5,*";
			doc.getElementById("middle").cols = INDEX_MIDDLE_COLS;
			return;
		}
		doc.getElementById("middle").cols = INDEX_MIDDLE_COLS;
	}
	catch (r) {
	}
}
function hideTop() {
	try {
		INDEX_ROWS = doc.getElementById("index").rows;
		var top2Height = 35;
		try {
			var top2 = document.getElementById("top2");
			if (top2.clientHeight == 0) {
				setTimeout("hideTop();", 100);
				return;
			}
			top2Height = top2.clientHeight + 5;
		}
		catch (e) {
		}
		doc.getElementById("index").rows = top2Height + ",*,0";
	}
	catch (r) {
	}
}
function showTop(height) {
	try {
		if (height) {
			INDEX_ROWS = height + ",*,0";
			doc.getElementById("index").rows = INDEX_ROWS;
			return;
		}
		doc.getElementById("index").rows = INDEX_ROWS;
	}
	catch (r) {
	}
}
function setPageWidth(width) {
	try {
		if (width) {
			doc.getElementById("page").cols = "*," + width + ",*";
		} else {
			doc.getElementById("page").cols = "0,*,0";
		}
	}
	catch (r) {
	}
}
function logout(){
	$.post("{$contextPath}/logout", null, function(json) {
        if(json.success){
            if(json.redirect){
                window.parent.location.href=json.redirect;
            }else{
            	window.parent.location.href="{$contextPath}/admin";
            }
        }else{
            alert(json.message);
        }
	}, "json");
}
function exit(){
	$.post("{$contextPath}/logout", null, function(json) {
        if(json.success){
            window.parent.close();
        }else{
            alert(json.message);
        }
	}, "json");
}
</script>
<style>
.top_bg {
    background: #7bb8e1;
	padding: 0;
	margin: 0;
    width: 100%;
    height: 86px;
	overflow: hidden;
}

.top_bg .left {
    background: url(/upload/xinhouziguan/images/admin/top-left.png) repeat-x center top;
	align: left;
	text-align: left;
	width: 10px;
    height: 86px;
}

.top_bg .text {
    background: url(/upload/xinhouziguan/images/admin/top-text.png) no-repeat center top;
    align: left;
    text-align: left;
    width: 950px;
    height: 86px;
}
.top_bg .right {
    background: url(/upload/xinhouziguan/images/admin/top-right.png) repeat-x center top;
	align: right;
	text-align: right;
    height: 86px;
}

.top_info {
	width: 100%;
    background: #9db8dd url(/upload/xinhouziguan/images/admin/bar.png) repeat-x center center;
	padding: 0;
	margin: 0;
    height: 25px;
	overflow: hidden;
}

.top_info .top_menu {
	text-align: right;
	font-size: 12px;
	font-weight: bold;
	v-align: middle;
	white-space: nowrap;
	color: #585858;
	padding: 2px 5px 2px 10px;
}

.top_info .top_logininfo{
	padding: 5px 0 0 0;
	font-size: 13px;
	color: #585858;
	float: left;
	font-weight: bold;
    /*
	filter: Dropshadow(offx=1, offy=0, color=#585858)
		Dropshadow(offx=0, offy=1, color=#585858)
		Dropshadow(offx=-1, offy=0, color=#585858)
		Dropshadow(offx=0, offy=-1, color=#585858);
        */
}

.top_info .top_dateinfo {
	padding: 5px 0 0 0;
	font-size: 13px;
	color: #585858;
	float: left;
	font-weight: bold;
    /*
	filter: Dropshadow(offx=1, offy=0, color=#585858)
		Dropshadow(offx=0, offy=1, color=#585858)
		Dropshadow(offx=-1, offy=0, color=#585858)
		Dropshadow(offx=0, offy=-1, color=#585858);
        */
}

.top_info .arrows1 {
	cursor: pointer;
	padding: 5px 0 0 0;
}

.top_info .arrows2 {
	cursor: pointer;
	padding: 2px 5px 0 0;
}

a {
	white-space: nowrap;
	color: #330099;
	text-decoration: none
}

a:hover {
	white-space: nowrap;
	color: #FF0000;
	text-decoration: underline
}

</style>
</head>
<body>
	<div id="top1" class="top_bg">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td class="left">&nbsp;</td>
				<td class="text">&nbsp;</td>
				<td class="right">&nbsp;</td>
			</tr>
		</table>
	</div>
	<table id="top2" class="top_info" width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="5" align="left" valign="top">
			     <div class="arrows1"><img id="imgLeftRight" src="{$contextPath}/themes2/images/index_btn_arrow_left.jpg" /></div>
			</td>
			<td width="5">&nbsp;</td>
			<td class="top_logininfo">
                            登录帐号：{$user.username}&nbsp;{if $user.name != null}用户名称：{$user.name}{/if}
            </td>
			<td width="40">&nbsp;</td>
			<td width="40">&nbsp;</td>
            <td width="60" class="top_menu" align="right" style="background: url(/upload/xinhouziguan/images/admin/icon2.png) no-repeat left center;"><a href="javascript:logout();">退出系统</a></td>
            <td width="60" class="top_menu" align="right" style="background: url(/upload/xinhouziguan/images/admin/icon5.png) no-repeat left center;"><a href="{$contextPath}/admin/chgpwdform" target="body">修改密码</a></td>
			<td width="60" class="top_menu" align="right" style="background: url(/upload/xinhouziguan/images/admin/icon3.png) no-repeat left center;"><a href="{$contextPath}/index" target="_index_">网站首页</a></td>
			<td width="60" class="top_menu" align="right" style="background: url(/upload/xinhouziguan/images/admin/icon4.png) no-repeat left center;"><a href="{$contextPath}{$rightUrl}" target="body">后台首页</a></td>
			<td width="10">&nbsp;</td>
			<td width="5" align="right" valign="top">
			     <div class="arrows2"><img id="imgTopDown" src="{$contextPath}/themes2/images/index_btn_arrow_up.jpg" /></div>
			</td>
		</tr>
	</table>
</body>
</html>

<script type="text/javascript">
    $("#imgLeftRight").click(function() {
        if(this.src.indexOf('left')>0){
          hideLeft();
          this.src='{$contextPath}/themes2/images/index_btn_arrow_right.jpg'
        }else{
          showLeft();
          this.src='{$contextPath}/themes2/images/index_btn_arrow_left.jpg'
        }
    });
    $("#imgTopDown").click(function() {
        if(this.src.indexOf('up')>0){
          document.getElementById('top1').style.display='none';
          hideTop();
          this.src='{$contextPath}/themes2/images/index_btn_arrow_down.jpg'
        }else{
          document.getElementById('top1').style.display='block';
          showTop();
          this.src='{$contextPath}/themes2/images/index_btn_arrow_up.jpg'
        }
    });

</script>
