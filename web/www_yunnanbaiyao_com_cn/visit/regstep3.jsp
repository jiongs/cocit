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
	
	String guid = request.getParameter("guid");
	VisitActivityRegister register = null;
	if(guid != null && guid.trim().length() > 0)
		register = orm.get(VisitActivityRegister.class,Expr.eq("entityGuid", guid));
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
	if(register==null){
%>
	<script type="text/javascript">
	window.location.href="/jsp/visit:regstep1";
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
				
			<div style="background: #fff; font-size: 32px; color: #276fc3; padding: 5px 0 0 50px;" class="ie6-weight-font">报名向导</div>
			<div style="background: #fff; padding: 30px 0 30px 0; text-align: center;">
				<table class="stepbarbox" align="center" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<td class="stepbar_text">Step1 选择参观时间</td>
						<td></td>
						<td class="stepbar_text">Step2 填写报名信息</td>
						<td></td>
						<td class="stepbar_text">Step3 获取活动邀请函</td>
						<td></td>
					</tr>
					<tr>
						<td class="stepbar_1"><hr /></td>
						<td class="stepbar_dot"></td>
						<td class="stepbar_2"><hr /></td>
						<td class="stepbar_dot"></td>
						<td class="stepbar_3"><hr /></td>
						<td class="stepbar_dot"></td>
					</tr>
				</table>
			</div>
			<div style="background: #fff; padding: 20px 20px 20px 75px;">
				<%
					entity = orm.get(contentType, Expr.eq("catalogCode", "201309-09").addAsc("orderby").addDesc("id"));
				%>
				<table>
					<tr>
						<td><img src="<%=model.getContextPath()%>/visit/images2/ok.png" /></td>
						<td>
							<div style="font-size: 24px; color: #ff5335; padding: 0 20px;"><%=entity == null ? "恭喜你报名成功！" : entity.getName()%></div>
							<div style="margin-top: 20px; font-size: 16px; color: #333; line-height: 35px; padding: 20px;"><%=entity == null ? "恭喜您报名成功！我们将会发送邀请函和验证码到您的手机上，请注意查收。解释凭邀请函和相关证件参加活动！" : entity.getContentText()%></div>
							<div style="background: #fff; padding: 0px 20px"><a href="/jsp/visit:index"><img src="<%=model.getContextPath()%>/visit/images2/go.png" /></a></div>
						</td>
					</tr>
				</table>
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