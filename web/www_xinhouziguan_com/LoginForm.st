<div style="height: 31px; background: url(/upload/xinhouziguan/images/jlb/titlebg.jpg) no-repeat;width: 342px;"></div>
<div style="background: #fbf7f8;width: 340px; height: 100%;margin-bottom: 20px;border: 1px solid #c9c9c9;">
<form id="form_{$ui.uiid}" onsubmit="return false;">
    <input name="_loginrealm_" value="_WebUser" type="hidden" />
	<table style="margin: 20px 0 0 40px;" width="100%" >
		<tr>
			<td style="background: url(/upload/xinhouziguan/images/jlb/user.jpg) no-repeat left center; padding: 5px 2px 0px 90px;height: 50px;">
				<input type="text" id="_loginuser_" name="_loginuser_" value="" style="background: transparent; height: 20px; width: 170px;font-size: 16px;border: 0px solid #c9c9c9;" />
			</td>
		</tr>
		<tr>
			<td style="background: url(/upload/xinhouziguan/images/jlb/pwd.jpg) no-repeat left center; padding: 5px 2px 0px 90px;height: 50px;">
				<input type="password" id="_loginpwd_" name="_loginpwd_" value="" style="background: transparent; height: 20px; width:170px;font-size: 16px;border: 0px solid #c9c9c9;" />
			</td>
		</tr>
		<tr>
			<td style="text-align: center;padding-top: 20px;">
				<div style="margin-left: -70px;">
					<input class="login" onclick='this.form.action="{$contextPath}/login"' src="/upload/xinhouziguan/images/jlb/btn1.jpg" type="image" />
					&nbsp;&nbsp;<input onclick='window.document.location.href="{$itemUrl}";' src="/upload/xinhouziguan/images/jlb/btn2.jpg" type="image" />
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
