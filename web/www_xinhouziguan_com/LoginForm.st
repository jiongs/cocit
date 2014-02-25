<div style="height: 31px; background: url(/upload/xinhouziguan/images/jlb/titlebg.jpg) no-repeat;width: 342px;"></div>
<div style="background: #fbf7f8;width: 340px; height: 100%;margin-bottom: 20px;border: 1px solid #c9c9c9;">
<form id="form_{$ui.uiid}" onsubmit="return false;">
    <input name="_loginrealm_" value="_WebUser" type="hidden" />
	<table style="margin: 20px 0 0 35px;" width="100%" height="100%;">
		<tr style="background: url(/upload/xinhouziguan/images/jlb/user.jpg) no-repeat left center;">
			<td width="20"></td>
			<td style="padding: 10px 2px 10px 20px;text-align: right; width: 35px;"></td>
			<td style="padding: 10px;"><input type="text" id="_loginuser_" name="_loginuser_" style="background: transparent; height: 30px; width: 170px;font-size: 16px;border: 0px solid #c9c9c9;" /></td>
		</tr>
		<tr style="background: url(/upload/xinhouziguan/images/jlb/pwd.jpg) no-repeat left center;">
			<td width="20"></td>
			<td style="padding: 10px 2px 10px 20px;;text-align: right;width: 35px;"></td>
			<td style="padding: 10px;"><input type="password" id="_loginpwd_" name="_loginpwd_" style="background: transparent; height: 30px; width:170px;font-size: 16px;border: 0px solid #c9c9c9;" /></td>
		</tr>
		<tr>
			<td colspan="3" style="text-align: center;padding-top: 20px;">
				<div style="margin-left: -55px;">
				<input class="login" onclick='this.form.action="{$contextPath}/login"' src="/upload/xinhouziguan/images/jlb/btn1.jpg" type="image" />
				<input onclick='window.document.location.href="{$itemUrl}";' src="/upload/xinhouziguan/images/jlb/btn2.jpg" type="image" />
				</div>
			</td>
		</tr>
	</table>
</form>
</div>
<script type="text/javascript">
$(function() {
	var form=$("#form_{$ui.uiid}");
	$(".login", form).click(function() {
		form.block(); 
		$.post(this.form.action, form.serialize(), function(json) {
            if(json.success){
            	//$("#block{$ui.id}").load("{$loadBlockUrl}{$block.id}");
            	//window.document.location.reload();
            	window.document.location.href="/ui/21";
            }else{
                form.get(0).reset();
                alert(json.message);
            }
		}, "json");

		return false;
	});
});
</script>
