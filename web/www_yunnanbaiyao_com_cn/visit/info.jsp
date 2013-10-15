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

	String guid = request.getParameter("guid");
	String catalogCode = request.getParameter("catalog");
	
	WebContentEntity entity = null;
	if(guid != null){
	    entity = orm.get(contentType, Expr.eq("entityGuid", guid));
	}else if(catalogCode != null){
	    entity = orm.get(contentType, Expr.eq("catalogCode", catalogCode));
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
				
			<div style="background: #fff; padding: 30px;">
				<%
					WebContentEntity next = null;
					WebContentEntity prev = null;
					if (entity != null) {
						next = orm.get(contentType,Expr.gt("id", entity.getId()).and(Expr.eq("catalog", entity.getCatalog())));
						prev = orm.get(contentType,Expr.lt("id", entity.getId()).and(Expr.eq("catalog", entity.getCatalog())));
				%>
				<div style="text-align: center; font-size: 20px; color: #0068b7; padding: 20px;" class="ie6-weight-font"><%=entity.getName()%></div>
				<div style="color: #333; padding-bottom:30px; line-height: 30px; width: 900px; overflow: wrap; font-size: 16px;"><%=entity.getContentText()%></div>
				<table width="900px" class="navi_buttons">
					<tr>
						<td align="left"><%if(prev != null){ %><a href="/jsp/visit:info?guid=<%=prev.getEntityGuid() %>">&lt; 上一条</a><%} %></td>
						<td align="right"><%if(next != null){ %><a href="/jsp/visit:info?guid=<%=next.getEntityGuid() %>">下一条 &gt;</a><%} %></td>
					</tr>
				</table>
				<%
					}
				%>
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