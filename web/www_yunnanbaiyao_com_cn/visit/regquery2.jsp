<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.*,com.jiongsoft.cocit.*,com.jiongsoft.cocit.util.*,com.jiongsoft.ynby.entity.*,com.jiongsoft.cocit.orm.*,com.jiongsoft.cocit.orm.expr.*,com.jiongsoft.cocit.entity.*,com.jiongsoft.cocit.ui.model.*,com.jiongsoft.cocit.action.*"%>
<%
	JSPModel model = (JSPModel) request.getAttribute("obj");
	ActionHelper actionHelper = model.get("actionHelper");
	Orm orm = actionHelper.orm;
	BeanFactory beanFactory = Cocit.getBeanFactory();
	Class<WebContentEntity> contentType = beanFactory.getWebContentEntityType();
	WebContentEntity entity;
	String tel = request.getParameter("entity.tel");
	String telVerifyCode = request.getParameter("entity.telVerifyCode");
	VisitActivityRegister reg = null;
	String error = null;
	try {
		HttpUtil.checkSmsVerifyCode(request, tel, telVerifyCode, null);
		reg = orm.get(VisitActivityRegister.class, Expr.eq("tel", tel).addDesc("id"));
		if(reg.getActivity().getPlanDate().getTime() <= System.currentTimeMillis()){
			reg = null;
			error = "没有查询到满足条件的报名记录！";
		}
	} catch (Throwable e) {
		error = e.getMessage();
	}
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="走进云南白药" />
<meta name="description" content="走进云南白药" />

<title>走进云南白药</title>
<link href="<%=model.getContextPath()%>/visit/css/style2.css" rel="stylesheet" type="text/css" media="screen" />
<!--[if lt IE 7]>
        <link href="<%=model.getContextPath()%>/visit/css/style2_ie6.css" rel="stylesheet" type="text/css" media="screen" />
<![endif]-->

<script src="/jCocit/common/jquery.min.js" type="text/javascript"></script>
<script src="<%=model.getContextPath()%>/visit/js/visit.js" type="text/javascript"></script>
</head>
<body>
<%
	if(reg == null){
%>
	<script type="text/javascript">
	alert("<%=error %>");
	window.location.href="/jsp/visit:regquery1";
	</script>
</body>
</html>
<%    
	return;
	}
%>
	<div id="top">
		<div id="top_menu">
			<table>
				<tr>
					<td style="padding-right: 70px;"><img src="<%=model.getContextPath()%>/visit/images2/logo_h.png" /></td>
					<td><a href="/index.html" class="link_btn btn1"><div></div></a></td>
					<td><a href="/ui/46" class="link_btn btn2"><div></div></a></td>
					<td><a href="/ui/12" class="link_btn btn3"><div></div></a></td>
					<td><a href="/ui/14" class="link_btn btn4"><div></div></a></td>
					<td><a href="/ui/13" class="link_btn btn5"><div></div></a></td>
					<td><a href="/ui/33/11:263" class="link_btn btn6"><div></div></a></td>
					<td><a href="/jsp/visit:index" class="link_btn btn7"><div></div></a></td>
				</tr>
			</table>
		</div>
	</div>

	<div id="page">
		<div id="body">
			<div style="height: 70px;"></div>
			<div class="body_bg">
				<div style="background: #fff; position: relative; top: 70px; height: 30px;"></div>
			</div>
			<div id="bottom_menu">
				<div class="link_btn btn1"><a href="/jsp/visit:index" class="btn1"><div></div></a></div>
				<div class="link_btn btn2"><a href="/jsp/visit:intro" class="btn2"><div></div></a></div>
				<div class="link_btn btn3"><a href="/jsp/visit:regstep1" class="btn3"><div></div></a></div>
				<div class="link_btn btn4"><a href="/jsp/visit:jcsj" class="btn4"><div></div></a></div>
				<div class="link_btn btn5"><a href="/jsp/visit:regquery1" class="btn5"><div></div></a></div>
				<div class="link_btn btn6"><a href="/jsp/visit:info?catalog=201309-12" class="btn6"><div></div></a></div>
				<div class="link_btn btn7"><a href="/jsp/visit:contactUs" class="btn7"><div></div></a></div>
			</div>
				
			<div style="background: #fff; font-size: 32px; color: #276fc3; padding: 5px 0 0 50px;" class="ie6-weight-font">预约查询</div>
			<div style="background: #fff; padding: 30px 0 30px 0; text-align: center;">
				<table class="stepbarbox" align="center" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<td class="stepbar_text">Step1 输入手机号查询</td>
						<td></td>
						<td class="stepbar_text">Step2 修改报名信息</td>
						<td></td>
						<td class="stepbar_text">Step3 获取活动邀请函</td>
					</tr>
					<tr>
						<td class="stepbar_1"><hr /></td>
						<td class="stepbar_dot"></td>
						<td class="stepbar_2"><hr /></td>
						<td class="stepbar_dot"></td>
						<td class="stepbar"><hr /></td>
					</tr>
				</table>
			</div>
			<div style="background: #fff; padding: 20px 20px 20px 55px;">
				<%
					entity = orm.get(contentType, Expr.eq("catalogCode", "201309-08").addAsc("orderby").addDesc("id"));
				%>
				<div style="font-size: 24px; color: #ff5335;" class="ie6-weight-font"><%=entity == null ? "" : entity.getName()%>：</div>
				<div style="margin-top: 20px; font-size: 16px; color: #333; line-height: 35px; padding-left: 20px;"><%=entity == null ? "" : entity.getContentText()%></div>
			</div>
			<div style="background: #fff; padding: 0px 20px 20px 55px;">
				<div style="font-size: 24px; color: #01b0f0;" class="ie6-weight-font">个人信息：</div>
				<div style="margin-top: 30px;">
					<form onsubmit="return false;" method="post">
						<table align="center">
							<tr>
								<td class="reg_input_label">参观时间</td>
								<td class="reg_input_box">
									<select name="entity.activity.id">
										<% 
									    CocCalendar thisMonth = CocCalendar.now();
									    List<VisitActivity> list = orm.query(VisitActivity.class,Expr.gt("planDate", thisMonth.get()).addAsc("planDate").setPager(1, 8));
									    for(VisitActivity v : list){
										%>
										<option value="<%=v.getId()%>" <%=v.getId()==reg.getActivity().getId()?"selected":"" %>><%=v.getName() %></option>
										<%
										} 
										%>
									</select>
								</td>
							</tr>
							<tr>
								<td class="reg_input_label">姓名：</td>
								<td class="reg_input_box"><input type="text" name="entity.name" value="<%=reg.getName() %>" /><span class="required">&nbsp;</span></td>
							</tr>
							<tr>
								<td class="reg_input_label">性别：</td>
								<td class="reg_input_box">
								<input style="width: 16px; height: 16px; border: 0;" type="radio" name="entity.sex" value="0" <%= reg.getSex()==0?"checked":"" %> />&nbsp;男&nbsp;&nbsp;
								<input style="width: 16px; height: 16px; border: 0;" type="radio" name="entity.sex" value="1" <%= reg.getSex()==1?"checked":"" %> />&nbsp;女</td>
							</tr>
							<tr>
								<td class="reg_input_label">身份证号：</td>
								<td class="reg_input_box"><input type="text" name="entity.code" value="<%=reg.getCode() %>" /><span class="required">&nbsp;</span></td>
							</tr>
							<tr>
								<td class="reg_input_label">手机号码：</td>
								<td class="reg_input_box">
									<input type="hidden" name="entity.telVerifyCode" value="<%=reg.getTelVerifyCode() %>" />
									<input type="text" name="entity.tel" value="<%=reg.getTel() %>" readonly />	
								</td>
							</tr>
							<tr>
								<td class="reg_input_label">参观人数：</td>
								<td class="reg_input_box"><input type="text" name="entity.personNumber" value="<%=reg.getPersonNumber() %>" /></td>
							</tr>
							<tr>
								<td class="reg_input_label">QQ号码：</td>
								<td class="reg_input_box"><input type="text" name="entity.qq" value="<%=reg.getQq() %>" /></td>
							</tr>
							<tr>
								<td class="reg_input_label">邮箱地址：</td>
								<td class="reg_input_box"><input type="text" name="entity.email" value="<%=reg.getEmail() %>" /></td>
							</tr>
							<tr>
								<td class="reg_input_label">工作单位：</td>
								<td class="reg_input_box"><input type="text" name="entity.unit" value="<%=reg.getUnit() %>" /></td>
							</tr>
							<tr>
								<td class="reg_input_label">自驾车牌号：</td>
								<td class="reg_input_box"><input type="text" name="entity.carCode" value="<%=reg.getCarCode() %>" /></td>
							</tr>
							<tr>
								<td class="reg_input_label" valign="top">备注：</td>
								<td class="reg_input_box"><textarea name="entity.desc" style="height: 60px;"><%=reg.getDesc() %></textarea></td>
							</tr>
							<tr>
								<td class="reg_input_label">
									<input type="hidden" name="entity.id" value="<%=reg.getId() %>" />
								</td>
								<td style="padding-top: 20px;">
									<div style="padding-left: 40px;"><input style="width: 16px; height: 16px; border: 0;" type="checkbox" name="agree" value="1" />同意活动声明</div>
									<div>
										<br/>
										<a href="javascript: submitRegStep2(this)"><img class="reg_submit" src="<%=model.getContextPath()%>/visit/images2/button_submit.png" /></a>&nbsp;&nbsp;&nbsp;&nbsp; 
										<a href="/jsp/visit:regstep1"><img class="reg_btn_cancel" src="<%=model.getContextPath()%>/visit/images2/button_cancel.png" /></a>
									</div>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<div class="body_bg_bottom"></div>
		</div>
	</div>

	<div id="bottom">
		<div id="copyright">
			<%
				entity = orm.get(contentType, Expr.eq("catalogCode", "201309-03").addAsc("orderby").addDesc("id"));
			%>
			<%=entity == null ? "" : entity.getContentText()%>
		</div>
	</div>
</body>
</html>