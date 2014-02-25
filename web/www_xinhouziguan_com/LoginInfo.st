<style>
.login_info_table td{
	padding: 0 5px 0 5px;
}
.login_info_table .button{
	cursor: pointer;
}
</style>
<table class="login_info_table" align="right">
{if $login !=null}
		<tr>
			<td>登录用户：{$login.username}</td>
			<td><input class="button" onclick='window.document.location.href="/ui/21";' value="个人中心" type="submit" /></td>
			<td><input class="button" id="relogin_btn" value="重新登录" type="submit" /></td>
		</tr>
	{else}
		<tr>
			<td><input class="button" onclick='window.document.location.href="/ui/22";' value="登录" type="submit" /></td>
			<td><input class="button" onclick='window.document.location.href="/ui/23";' value="注册" type="submit" /></td>
		</tr>
{/if}
</table>

<script type="text/javascript">
$(function() {
	$("#relogin_btn").click(function() {
		$.post("/logout", "", function(json) {
            window.document.location.href="/ui/22";
		}, "json");

		return false;
	});
});
</script>