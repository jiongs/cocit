<link href="/jCocit/css/min/jCocit.ui.upload.css" rel="stylesheet" type="text/css" media="screen" />

<script src="/jCocit/js/min/jCocit.utils.js" type="text/javascript"></script>
<script src="/jCocit/js/min/jCocit.ui.upload.js" type="text/javascript"></script>
<script src="/www_xinhouziguan_com/website.js" type="text/javascript"></script>
<style>
	.register_table{
		width: 989px;
	}
	.register_titlebg{
		width: 989px; 
		height: 39px; 
		font-weight: bold;
		padding: 15px 0 0 15px;
	}
	.register_table .label{
		height: 30px;
		width: 80px;
		text-align: right;
		padding: 5px;
	}
	.register_table .input{
		height: 30px;
	}
	.register_table .input input{
		height: 22px;
		width: 300px;
		border: 1px solid #a8a8a8;
	}
	.register_table .required{
		background: url(/upload/xinhouziguan/images/jlb/required.jpg) no-repeat 5px 8px;
		padding: 5px;
	}
	.register_table .Cb{
		height: 30px;
	}
	.disabled_button{
		opacity: 0.3;
		filter: alpha(opacity = 30);
		cursor: default;
	}
</style>
<form>
<input type="hidden" name="entity.id" value="{tostring bean=$data prop="id"}" />
<div class="register_titlebg" style="background: url(/upload/xinhouziguan/images/jlb/titlebg2.jpg) no-repeat;">
	账户信息
</div>
<table class="register_table">
	<tr>
		<td class="label"><span class="required">&nbsp;&nbsp;</span>用户名称</td>
		<td class="input"><input type="text" name="entity.name" value="{tostring bean=$data prop="name"}" /></td>
	</tr>
	<tr>
		<td class="label"><span class="required">&nbsp;&nbsp;</span>登录帐号</td>
		<td class="input"><input type="text" name="entity.code" value="{tostring bean=$data prop="code"}" readonly="true" /></td>
	</tr>
	<tr>
		<td class="label"><span class="required">&nbsp;&nbsp;</span>密码问题</td>
		<td class="input"><input type="text" name="entity.pwdQuestion" value="{tostring bean=$data prop="pwdQuestion"}" /></td>
	</tr>
	<tr>
		<td class="label"><span class="required">&nbsp;&nbsp;</span>密码答案</td>
		<td class="input"><input type="text" name="entity.pwdAnswer" value="{tostring bean=$data prop="pwdAnswer"}" /></td>
	</tr>
	<tr>
		<td class="label">个人头像</td>
		<td class="input"><div style="position: relative;width: 300px;">
			<input type="file" id="entity_image" class="upload" name="entity.image" value=" value="{tostring bean=$data prop="image"}" data-options="
									fileTypeExts : '*.jpg; *.gif; *.png',
									comboWidth : 300,
									comboHeight : 22
								" /></div></td>
	</tr>
</table>
<div class="register_titlebg" style="background: url(/upload/xinhouziguan/images/jlb/titlebg2.jpg) no-repeat;">
	联系方式
</div>
<table class="register_table">
	<tr>
		<td class="label"><span class="required">&nbsp;&nbsp;</span>手机号码</td>
		<td class="input"><input type="text" name="entity.tel" value="{tostring bean=$data prop="tel"}" /></td>
	</tr>
	<tr>
		<td class="label"><span class="required">&nbsp;&nbsp;</span>QQ 号码</td>
		<td class="input"><input type="text" name="entity.qq" value="{tostring bean=$data prop="qq"}" /></td>
	</tr>
	<tr>
		<td class="label">电子邮件</td>
		<td class="input"><input type="text" name="entity.email" value="{tostring bean=$data prop="email"}" /></td>
	</tr>
	<tr>
		<td class="label">邮政编码</td>
		<td class="input"><input type="text" name="entity.postCode" value="{tostring bean=$data prop="postCode"}" /></td>
	</tr>
	<tr>
		<td class="label">通讯地址</td>
		<td class="input"><input type="text" name="entity.postAddress" value="{tostring bean=$data prop="postAddress"}" /></td>
	</tr>
	<tr>
		<td class="label">MSN</td>
		<td class="input"><input type="text" name="entity.msn" value="{tostring bean=$data prop="msn"}" /></td>
	</tr>
	<tr>
		<td class="label"><span class="required">&nbsp;&nbsp;</span>验证码</td>
		<td class="input"><input type="text" name="cocit_verify_code" style="width: 50px;" />&nbsp;<img src="/coc/getImgVerifyCode" /></td>
	</tr>
</table>
<div class="register_titlebg" style="background: url(/upload/xinhouziguan/images/jlb/titlebg2.jpg) no-repeat;">
	个人签名
</div>
<table class="register_table">
	<tr>
		<td class="input"><textarea name="entity.desc" style="height: 80px; width: 980px; border: 1px solid #a8a8a8; padding: 2px;"> {tostring bean=$data prop="desc"}</textarea></td>
	</tr>
	<tr>
		<td style="text-align: center;padding: 20px;">
			<input onclick="
					try{
						submitModify(this);
					}catch(e){
					} 
					return false;
				" type="image" src="/upload/xinhouziguan/images/jlb/btn3.jpg" /></td>
	</tr>
</table>
</form>