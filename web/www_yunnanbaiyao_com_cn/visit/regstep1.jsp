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
				
			<div style="background: #fff; font-size: 32px; color: #276fc3; padding: 5px 0 0 50px;" class="ie6-weight-font">报名向导</div>
			<div style="background: #fff; padding: 30px 0 30px 0; text-align: center;">
				<table class="stepbarbox" align="center" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<td class="stepbar_text">Step1 选择参观时间</td>
						<td></td>
						<td class="stepbar_text">Step2 填写报名信息</td>
						<td class="stepbar_text">Step3 获取活动邀请函</td>
					</tr>
					<tr>
						<td class="stepbar_1"><hr /></td>
						<td class="stepbar_dot"></td>
						<td class="stepbar"><hr /></td>
						<td class="stepbar"><hr /></td>
					</tr>
				</table>
			</div>
			<div style="background: #fff; padding: 30px 0 30px 0; text-align: center;">
				<form method="post" action="/jsp/visit:regstep2">
					<table width="900" align="center">
						<tr>
							<%
								DateUtil thisMonth = DateUtil.now();
												    //thisMonth.setDay(1).setTime(0, 0, 0, 0);
												    List<VisitActivity> list = orm.query(VisitActivity.class,Expr.gt("planDate", thisMonth.get()).addAsc("planDate").setPager(1, 4));
												    int len = list.size();
												    int month = 0;
												    DateUtil tmp;
												    DateUtil day1 = null;
												    //DateUtil day2 = null;
												    VisitActivity activity1 = null;
												    VisitActivity activity2 = null;
												    int remain1 = 0;
												    //int remain2 = 0;
												    long now = new Date().getTime() + 10 * 60000;//允许10分钟时间报名，否则会过期。
												    for (int i = 0; i < len; i++) {
													    boolean valid1 = true;
													    //boolean valid2 = true;
														activity1 = list.get(i);
														day1 = DateUtil.make(activity1.getPlanDate());
														month = day1.getMonth();
														remain1 = activity1.getPlanPersonNumber() - activity1.getRegisterPersonNumber();
													    if(activity1.getExpiredTo() !=null && now > activity1.getExpiredTo().getTime() ){
													    	valid1 = false;
													    }
													    if(activity1.getExpiredFrom() !=null && now < activity1.getExpiredFrom().getTime() ){
													    	valid1 = false;
													    }

													    /*
														i++;
														if (i < len) {
														    activity2 = list.get(i);
														    day2 = DateUtil.make(activity2.getPlanDate());
														    remain2 = activity2.getPlanPersonNumber() - activity2.getRegisterPersonNumber();
														    if(activity2.getExpiredTo() !=null && now > activity2.getExpiredTo().getTime()){
														    	valid2 = false;
														    }
														    if(activity2.getExpiredFrom() !=null && now < activity2.getExpiredFrom().getTime() ){
														    	valid2 = false;
														    }
														    
														    if (day2.getMonth() != month) {
																i--;
																day2 = null;
														    }
														}
														*/
							%>
							<td align="center">
								<table>
									<tr>
										<td colspan=2 class="reg_month"><%=DateUtil.NLS_MONTHS[month]%></td>
									</tr>
									<tr>
										<td class="reg_day<%=(valid1?"":" invalid")%>" value="<%=activity1.getEntityGuid()%>" title="报名时间: <%=DateUtil.format(activity1.getExpiredFrom(), "yyyy-MM-dd HH:mm")%> 至 <%=DateUtil.format(activity1.getExpiredTo(), "yyyy-MM-dd HH:mm")%>">
												<div><%=day1.getDay()%><span class="ri">日</span><span class="time"><%=DateUtil.format(day1.get(), "HH:mm")%></span></div>
												<div style="margin-top: 5px;"><span>剩余<span class="reg_personNum"><%=remain1 >= 0 ? remain1 : 0%></span>名额</span></div>
										</td>
									</tr>
								</table>
							</td>
							<%
							    	day1 = null;
									//day2 = null;
							    }// end: for
							%>
						</tr>
						<tr>
							<td colspan=4 style="padding-top: 50px;"><input type="hidden" name="guid" value="" /> <a href="javascript: submitRegStep1()"><img class="reg_submit" src="<%=model.getContextPath()%>/visit/images2/button_submit.png" /></a>&nbsp;&nbsp;&nbsp;&nbsp; <a href="/jsp/visit:index"><img class="reg_btn_cancel" src="<%=model.getContextPath()%>/visit/images2/button_cancel.png" /></a></td>
						</tr>
					</table>
				</form>
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