<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.*,com.jiongsoft.cocit.*,com.jiongsoft.cocit.orm.*,com.jiongsoft.cocit.orm.expr.*,com.jiongsoft.cocit.entity.*,com.jiongsoft.cocit.ui.model.*,com.jiongsoft.cocit.action.*"%>
<%
    JSPModel model = (JSPModel) request.getAttribute("obj");
    ActionHelper actionHelper = model.get("actionHelper");
    Orm orm = actionHelper.orm;
    BeanFactory beanFactory = Cocit.getBeanFactory();
    Class<WebContentEntity> contentType = beanFactory.getWebContentEntityType();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="走进云南白药" />
<meta name="description" content="走进云南白药" />

<title>走进云南白药</title>
<link href="<%=model.getContextPath()%>/visit/css/style.css" rel="stylesheet" type="text/css" media="screen" />
<!--[if lt IE 7]>
        <link href="<%=model.getContextPath()%>/visit/css/style_ie6.css" rel="stylesheet" type="text/css" media="screen" />
<![endif]-->

<script src="/jCocit/common/jquery.min.js" type="text/javascript"></script>
<script src="<%=model.getContextPath()%>/visit/js/visit.js" type="text/javascript"></script>
</head>
<body>
	<div id="body">
		<div id="top_menu">
			<table>
				<tr>
					<td><a href="/index.html"><div class="link_btn btn1"></div></a></td>
					<td><a href="/ui/46"><div class="link_btn btn2"></div></a></td>
					<td><a href="/ui/12"><div class="link_btn btn3"></div></a></td>
					<td style="padding: 0 15px 0 15px;"><img src="<%=model.getContextPath()%>/visit/images/logo.png" /></td>
					<td><a href="/ui/14"><div class="link_btn btn4"></div></a></td>
					<td><a href="/ui/13"><div class="link_btn btn5"></div></a></td>
					<td><a href="/ui/33/11:263"><div class="link_btn btn6"></div></a></td>
					<td><a href="/jsp/visit:index"><div class="link_btn btn7"></div></a></td>
				</tr>
			</table>
		</div>
		<div id="regester">
			<a href="/jsp/visit:regstep1"><div></div></a>
		</div>
		<div id="notice_bg"></div>
		<div id="bottom_menu">
			<div class="link_btn btn1"><a href="/jsp/visit:index" class="btn1"><div></div></a></div>
			<div class="link_btn btn2"><a href="/jsp/visit:intro" class="btn2"><div></div></a></div>
			<div class="link_btn btn3"><a href="/jsp/visit:regstep1" class="btn3"><div></div></a></div>
			<div class="link_btn btn4"><a href="/jsp/visit:jcsj" class="btn4"><div></div></a></div>
			<div class="link_btn btn5"><a href="/jsp/visit:regquery1" class="btn5"><div></div></a></div>
			<div class="link_btn btn6"><a href="/jsp/visit:info?catalog=201309-12" class="btn6"><div></div></a></div>
			<div class="link_btn btn7"><a href="/jsp/visit:contactUs" class="btn7"><div></div></a></div>
		</div>
		<div id="notice">
			<ul>
				<%
				    List<WebContentEntity> list = orm.query(contentType, Expr.eq("catalogCode", "201309-02").addAsc("orderby").addDesc("id").setPager(1, 2));
				    for (WebContentEntity entity : list) {
						String name = entity.getName();
						int nameLen = name.length();
						if (nameLen > 25) {
						    name = name.substring(0, 25) + "...";
						}
				%>
				<li><a href="/jsp/visit:info?guid=<%=entity.getEntityGuid()%>"><%=name%></a></li>
				<%
				    }
				%>
			</ul>
		</div>
		<div id="copyright">
			<%
			    WebContentEntity entity = orm.get(contentType,Expr.eq("catalogCode", "201309-03").addAsc("orderby").addDesc("id"));
			%>
			<%=entity == null ? "" : entity.getContentText()%>
		</div>
	</div>
</body>
</html>