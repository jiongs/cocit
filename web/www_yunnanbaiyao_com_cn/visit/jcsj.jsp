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
	Class<WebCatalogEntity> catalogType = beanFactory.getWebCatalogEntityType();
	
	WebCatalogEntity root = orm.get(catalogType, Expr.eq("code", "201309-10"));
	List<WebCatalogEntity> catalogList = orm.query(catalogType, Expr.eq("parent", root).addAsc("orderby").addDesc("id"));
	
	WebCatalogEntity catalog = null;
	String catalogGuid = request.getParameter("catalog");
	if(catalogGuid == null || catalogGuid.trim().length() == 0){
		if(catalogList != null && catalogList.size() > 0){
			catalog = catalogList.get(0);
		}
	}else{
		catalog = orm.get(catalogType, Expr.eq("entityGuid", catalogGuid));
	}
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="走进云南白药" />
<meta name="description" content="走进云南白药" />

<title>走进云南白药</title>
<link href="<%=model.getContextPath()%>/visit/css/style2.css" rel="stylesheet" type="text/css" media="screen" />
<link href="/jCocit/css/min/jCocit.web.KandyTabs.css" rel="stylesheet" type="text/css" media="screen" />
<!--[if lt IE 7]>
        <link href="<%=model.getContextPath()%>/visit/css/style2_ie6.css" rel="stylesheet" type="text/css" media="screen" />
<![endif]-->
<script src="/jCocit/common/jquery.min.js" type="text/javascript"></script>
<script src="/jCocit/js/src/jCocit.web.KandyTabs.js" type="text/javascript"></script>
<script src="<%=model.getContextPath()%>/visit/js/visit.js" type="text/javascript"></script>
<style rel="stylesheet" type="text/css" media="screen">
#slideimgs.kandySlide { clear: both; margin: 0 0 20px!important;}
#slideimgs.kandySlide .tabtitle { position: absolute; display: block; bottom: 2px;right:2px;}
#slideimgs.kandySlide .tabcont { position: absolute; text-valign:top; left:0;top:0;}
#slideimgs.kandySlide .tabcont img{valign: top;vertical-align: top;padding: 0; margin: 0;top:0;left:0;}
#slideimgs.kandySlide .tabcont div{padding: 5px 0 0 2px;font-weight: bold;background:#ffffff; color:#000; text-indent:10px; font-size:14px}
</style>
	<script type="text/javascript">
$(document).ready(function(){

	$("#slideimgs").KandyTabs({
		classes:"kandySlide",
		action:"slifade",
		stall:5000,
		type:"slide",
		auto:true,
		process:true,
		direct:"left",
		//resize:false,
		custom:function(b,c,i){
				$("p",c).hide();
				c.eq(i).find("p").slideDown();
			  },
		done:function(b,c,t){
				$("p",c).fadeTo(500,.4).hide();
				c.first().find("p").slideDown();
			  }
	});

});
	</script>
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
				<div class="link_btn btn1">
					<a href="/jsp/visit:index" class="btn1"><div></div></a>
				</div>
				<div class="link_btn btn2">
					<a href="/jsp/visit:intro" class="btn2"><div></div></a>
				</div>
				<div class="link_btn btn3">
					<a href="/jsp/visit:regstep1" class="btn3"><div></div></a>
				</div>
				<div class="link_btn btn4">
					<a href="/jsp/visit:jcsj" class="btn4"><div></div></a>
				</div>
				<div class="link_btn btn5">
					<a href="/jsp/visit:regquery1" class="btn5"><div></div></a>
				</div>
				<div class="link_btn btn6">
					<a href="/jsp/visit:info?catalog=201309-12" class="btn6"><div></div></a>
				</div>
				<div class="link_btn btn7">
					<a href="/jsp/visit:contactUs" class="btn7"><div></div></a>
				</div>
			</div>

			<div style="background: #fff; padding: 30px">
				<table width="100%">
					<tr>
						<td valign="top" width="140px">
							<div style="height: 510px; position: relative;">
								<div style="height: 100%; width: 5px; background: #aeee00; left: 50px; top: 10px; position: absolute;"></div>
								<div style="position: absolute; top: -40px; left: 10px; font-size: 22px; color: #ff5335;">精彩瞬间</div>
								<div style="position: absolute; top: 0px; left: 18px; width: 68px; height: 68px;">
									<img src="<%=model.getContextPath()%>/visit/images2/jcicon.png" />
								</div>
								<%
									int len = catalogList.size();
															for(int i=0; i<len; i++){
																WebCatalogEntity cata = catalogList.get(i);
																String strDate = cata.getName().substring(0, 8);
																Date date = DateUtil.parse(strDate, "yyyyMMdd");
																int top = 83 + (i * 85);
								%>
								<div style="position: absolute; top: <%=top%>px; left: 18px; width: 68px; height: 68px;">
									<a href="/jsp/visit:jcsj?catalog=<%=cata.getEntityGuid()%>">
									<%
										if(cata.getId().equals(catalog.getId())){
									%>
									<img src="<%=model.getContextPath()%>/visit/images2/date2.png" />
									<%
										}else{
									%>
									<img src="<%=model.getContextPath()%>/visit/images2/date.png" />
									<%
										}
									%>
									<div style="position: absolute; cursor: pointer; left:0; top: 10px; text-align: center; width: 68px; font-size: 16px; color: #fff;" class="ie6-weight-font"><%=DateUtil.format(date, "yyyy")%></div>
									<div style="position: absolute; cursor: pointer; left:0; top: 35px; text-align: center; width: 68px; font-size: 16px; color: #333;" class="ie6-weight-font"><%=DateUtil.format(date, "MM.dd")%></div>
									</a>
								</div>
								<%
									} 
								%>
							</div>
						</td>
						<td valign="top">

							<div style="margin-top: 5px; background: #c8ebff; border: 0px solid #cccccc; width: 714px; color: #01b0f0; font-size: 18px; padding:  2px 0 2px 10px;"><%=catalog.getName() %></div>
							<div style="border: 2px solid #cccccc; width: 720px; height: 480px;padding:0;margin:0;">

											<ol id="slideimgs" style="padding:0;margin:0;">
					                        	<%
					                        		String contextDir = Cocit.getContextDir();
					                        		String srcPic="", bigPic="", href="", detailImg="", thumbPic="", id="", title="",longdesc="";
					                        		List<WebContentEntity> list = orm.query(contentType, Expr.eq("catalog", catalog).addAsc("orderby").addDesc("id"));
					                        		for(WebContentEntity obj : list){
					                        			srcPic = obj.getImagePath();
					                        			bigPic = ImageUtil.makeThumbImage(contextDir, srcPic, 720, 480);
					                        			title = obj.getName();
					                        	%>
	                        					<li style="padding:0;margin:0;"><img width="720" height="480" src="<%=bigPic %>" /><div><%=title %></div></li>
	                            				<% }// end: for %>
											</ol>

							</div>


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