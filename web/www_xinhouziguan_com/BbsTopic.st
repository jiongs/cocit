<script src="{$scriptPath}/demsy.xheditor.js" type="text/javascript"></script>
<style>
.topic_form table, .topic_table{
    BORDER-COLLAPSE: collapse;
    border: 1px solid #e0dcdd;
}
.topic_table a, .current_position a{
    color: #000000;
    text-decoration:none;
}
.topic_table a:hover, .current_position a:hover{
    text-decoration:underline;
}
.topic_table_header{
    background: url(/upload/xinhouziguan/images/zclt/bg2.png) repeat-x;
    height: 31px;
}
.item2 td, .item1 td{
    border: 1px solid #e0dcdd;
    padding: 5px;
}
td.user{
	width: 200px;
}
.header_position>td, .header_stat>td {
    border: 1px solid #ffffff;
}
.item2 td{
    border-top: 0 solid #e0dcdd;
}
.item1 div, .item2 div{
    padding: 5px;
}
.topic_table_row_odd{
    background: #efefef;
    height: 30px;
}
.topic_table_row_even{
    height: 30px;
}
.topic_table_pager{
    background: #e3e3e3;
    height: 35px;
}
.topic_table .posticon{
    text-align: center;
}
.topic_form{
    margin-top: 10px;
}
.topic_form .header{
    background: #434748;
    text-align: center;
    height: 30px;
}
.topic_form .header th{
    color: #ffffff;
}
.topic_form table td{
    border: 1px solid #e3e3e3;
    height: 30px;
    padding: 2px 5px 2px 5px;
}
.topic_form table .title{
    background: #efefef;
}
.topic_form .submitButton{
    margin-top: 5px;
    background: #fff url(/upload/xinhouziguan/images/zclt/button2.png) no-repeat;
    height: 26px;
    width: 102px;
    border: 0px solid #fff;
}
.topic_form .input{
    border: 1px solid #c5c5c5;
}
.current_position{
    font-weight: bold;
}
</style>
<div class="t"></div>
<div class="d">
	<a id="top"></a>
            <div class="current_position">
                <table width="100%" cellpadding=0 cellspace=0 border=0><tr>
                    <td style="background: url(/upload/xinhouziguan/images/zclt/left.png) no-repeat left top;height: 47px;width: 14px;"></td>
                    <td style="background: url(/upload/xinhouziguan/images/zclt/bg.png) repeat-x; height: 47px;"><img src="/upload/xinhouziguan/images/zclt/position.png" style="margin-top: -2px;" /> <a href="/ui/11">论坛首页</a> <span style="color: #999999">&gt;</span> <a href="{$forumUrl}">{tostring bean=$forum name="name"}</a> <span style="color: #999999">&gt;</span>  {tohtml bean=$data prop="title"}</td>
                    <td style="background: url(/upload/xinhouziguan/images/zclt/right.png) no-repeat -1px 0;height: 47px;width: 14px;"></td>
                </tr></table>
            </div>
            <div colspan=6 class="topic_stat" style="height: 16px;padding: 20px 5px 5px 5px;">
                <img src="/upload/xinhouziguan/images/zclt/stat.png" style="margin-top: -2px;" /> <span style="color: #999999">今日：</span>{$topicCountToday} <span style="color: #999999">&nbsp;&nbsp;昨日：</span>{$topicCountYesterday} <span style="color: #999999">&nbsp;&nbsp;总帖数：</span>{$topicCountTotal}
            </div>
        {if $pager != null}
        <div class="">
           <table width="100%" cellpadding=0 cellspace=0 border=0>
        	<tr class="topic_table_pager" style="background: #ffffff;">
            <td colspan=2><span class="msg">&nbsp;共 {$pager.totalPage} 页 {$pager.totalRecord} 条回帖，当前显示第 {$pager.pageIndex} 页 </span> {if $pager.totalPage > 0}{if $pager.pageIndex != 1} <a
                href='javascript: ui.gotoPage("block{$block.id}","page=1");' title="第一页">第一页</a>&nbsp; <a href='javascript: ui.gotoPage("block{$block.id}","page={$pager.prePage}");' title="上一页">上一页</a>&nbsp;
                {/if} {if $pager.pageIndex != $pager.totalPage} <a href='javascript: ui.gotoPage("block{$block.id}","page={$pager.nextPage}");' title="下一页">下一页</a>&nbsp; <a
                href='javascript: ui.gotoPage("block{$block.id}","page={$pager.totalPage}");' title="最后页">最后页</a>&nbsp; {/if}{/if}</td>
            <td style="text-align: right;padding: 2px 10px 0 0;">
                <a href="#bottom" style="align: right;"><img src='/upload/xinhouziguan/images/zclt/button4.png' /></a>
                <a href="{$forumUrl}#bottom" style="align: right;"><img src='/upload/xinhouziguan/images/zclt/button1.png' /></a>
            </td>
        	</tr></table>
        </div>
        {/if}
    <table class="topic_table" width='100%' align=center cellpadding=0 cellspacing=0 border=0>
        {foreach from=$pager.result item="obj" key="idx"} {if $obj != null} {if $css == "even"}{assign var="css" value="odd"}{else}{assign var="css" value="even"}{/if} {if $idx != 0} 
        {math equation="(pageIndex-1)*pageSize+idx" pageIndex=$pager.pageIndex pageSize=$pager.pageSize idx=$idx assign="idx"} {/if}
        <tr class='item1 {$css} row{tostring bean=$idx pattern="#"}'>
            <td valign="top" class="user">
                <div class="img">
                    {if $obj.author.image != null}
                            <img src="{$obj.author.image}" />
                    {elseif $obj.author.logo != null}
                            <img src="{$obj.author.logo}" />
                    {elseif $forum.teamlogo != null}
                            <img src="/upload/xinhouziguan/images/zclt/user.png" />
                    {/if}
                </div>
            {if $obj.author.id != null}
                <div class="u_name"><span>名称：</span> {if $obj.author.name == null}{$obj.author.username}{else}{$obj.author.name}{/if}</div>
                {if $obj.author.grade != null}
                    <div class="u_level"><span>等级：</span> {tohtml bean=$obj.author.grade prop="name"}</div>
                {/if}
                <div class="u_code"><span>帐号：</span> {$obj.createdBy}</div>
                <div class="u_date"><span>注册：</span> {tostring bean=$obj.author prop="created" pattern="yyyy-MM-dd"}</div>
                {if $obj.author.desc != null && $obj.author.desc != ""}
                    <div class="u_sign"><span>签名：</span> {tohtml bean=$obj.author prop="desc"}</div>
                {/if}
            {else}
                <div><span>来自：</span> {ipname bean=$obj prop="createdIP"}</div>                
            {/if}
            </td>
            <td class="content" colspan=2>
            {if $obj.warning != null}
            	<span style='border: 1px solid #D1D5DB; padding: 10px; background: #FFFEF5;'>{tohtml bean=$obj prop="warning"}</span>
            {else}
            	{if $obj.catalog != null}<img src='{$contextPath}/themes2/images/bbs/posticons/{$obj.catalog}' />&nbsp;{/if}
            	{tohtml bean=$obj prop="content"}
            {/if}
            </td>
        </tr>
        <tr class='item2 {$css}'>
            <td width="180px"><img src='{$contextPath}/themes2/images/bbs/images/posttime.gif' />&nbsp;{tostring bean=$obj prop="created" pattern="yyyy-MM-dd HH:mm"}</td>
            <td>{if $idx != 0}<img src='{$contextPath}/themes2/images/bbs/images/ip.gif' />&nbsp;<a href="#bottom" style="color:#000000;" onclick="ref('row{tostring bean=$idx pattern="#"}')">引用</a>{/if}</td>
            <td class="idx" align="right">{if $idx==0}楼主{else}{tostring bean=$idx pattern="#"} 楼{/if}
            <a href="#top"><img src='{$contextPath}/themes2/images/bbs/images/gotop.gif' /></a>
            <a href="#bottom"><img src='{$contextPath}/themes2/images/bbs/images/gobottom.gif' /></a>
            </td>
        </tr>
        {/if} {/foreach}
        {if $pager != null}
        <tr class="topic_table_pager">
            <td colspan=2><span class="msg">&nbsp;共 {$pager.totalPage} 页 {$pager.totalRecord} 条回帖，当前显示第 {$pager.pageIndex} 页 </span> {if $pager.totalPage > 0}{if $pager.pageIndex != 1} <a
                href='javascript: ui.gotoPage("block{$block.id}","page=1");' title="第一页">第一页</a>&nbsp; <a href='javascript: ui.gotoPage("block{$block.id}","page={$pager.prePage}");' title="上一页">上一页</a>&nbsp;
                {/if} {if $pager.pageIndex != $pager.totalPage} <a href='javascript: ui.gotoPage("block{$block.id}","page={$pager.nextPage}");' title="下一页">下一页</a>&nbsp; <a
                href='javascript: ui.gotoPage("block{$block.id}","page={$pager.totalPage}");' title="最后页">最后页</a>&nbsp; {/if}{/if}</td>
            <td style="text-align: right;padding: 2px 10px 0 0;">
                <a href="{$forumUrl}#bottom" style="align: right;"><img src='/upload/xinhouziguan/images/zclt/button1.png' /></a>
            </td>
        </tr>
        {/if}
    </table>
    <a id="bottom"></a>
    <div class="topic_form">
        <form id="form_{$ui.uiid}" action="" style="text-align: right;" onsubmit="return false;">
            <input type="hidden" name="data.forum.id" value="{$data.obj.forum.id}" />
            <input type="hidden" name="data.topic.id" value="{$data.obj.id}" />
            <input type="hidden" name="data.name" value="RE:{$data.name}" />
            <table width='100%' align=center cellpadding=0 cellspacing=0 border=0>
                <tr class='header'>
                    <th colspan=2>回帖</th>
                </tr>
                <tr>
                    <td class="title">回贴分类</td>
                    <td align="left">
                    <input type="radio" name="data.catalog" value="" checked="true" />
                    <input type="radio" name="data.catalog" id="em45" value="em45.gif" /><label for="em45"><img src="{$contextPath}/themes2/images/bbs/posticons/em45.gif" /></label>
                    <input type="radio" name="data.catalog" id="em46" value="em46.gif" /><label for="em46"><img src="{$contextPath}/themes2/images/bbs/posticons/em46.gif" /></label>
                    <input type="radio" name="data.catalog" id="em47" value="em47.gif" /><label for="em47"><img src="{$contextPath}/themes2/images/bbs/posticons/em47.gif" /></label>
                    <input type="radio" name="data.catalog" id="em48" value="em48.gif" /><label for="em48"><img src="{$contextPath}/themes2/images/bbs/posticons/em48.gif" /></label>
                    <input type="radio" name="data.catalog" id="em49" value="em49.gif" /><label for="em49"><img src="{$contextPath}/themes2/images/bbs/posticons/em49.gif" /></label>
                    <input type="radio" name="data.catalog" id="em50" value="em50.gif" /><label for="em50"><img src="{$contextPath}/themes2/images/bbs/posticons/em50.gif" /></label>
                    <input type="radio" name="data.catalog" id="em51" value="em51.gif" /><label for="em51"><img src="{$contextPath}/themes2/images/bbs/posticons/em51.gif" /></label>
                    <input type="radio" name="data.catalog" id="em52" value="em52.gif" /><label for="em52"><img src="{$contextPath}/themes2/images/bbs/posticons/em52.gif" /></label>
                    <input type="radio" name="data.catalog" id="em53" value="em53.gif" /><label for="em53"><img src="{$contextPath}/themes2/images/bbs/posticons/em53.gif" /></label>
                    <input type="radio" name="data.catalog" id="em54" value="em54.gif" /><label for="em54"><img src="{$contextPath}/themes2/images/bbs/posticons/em54.gif" /></label>
                    <input type="radio" name="data.catalog" id="em55" value="em55.gif" /><label for="em55"><img src="{$contextPath}/themes2/images/bbs/posticons/em55.gif" /></label>
                    <input type="radio" name="data.catalog" id="em56" value="em56.gif" /><label for="em56"><img src="{$contextPath}/themes2/images/bbs/posticons/em56.gif" /></label>
                    </td>
                </tr>
                <tr>
                    <td class="title">回复内容</td>
                    <td align="left"><textarea style="width: 99%; height: 200px;" id="textarea_{$ui.uiid}" name="data.content"></textarea></td>
                </tr>
                <tr>
                    <td class="title">查看方式</td>
                    <td align="left"><select class="input" name="data.viewMode" style="width: 200px" onchange="
                    	if(this.value=='4'){
                    		$('#bbsviewusers').show();
                    	}else{
                    		$('#bbsviewusers').hide();
                    	}" >
                    	<option value="0">允许所有用户查看该帖</option>
                    	<option value="1">允许登录用户查看该帖</option>
                    	<option value="3">只有版主可以查看该帖</option>
                    	<option value="4">允许指定用户查看该帖</option>
                    </select>
                    </td>
                </tr>
                <tr id="bbsviewusers"  style="display:none;">
                    <td class="title">指定用户</td>
                    <td align="left"><input type="text" class="input" name="data.viewUsers" style="width: 50%;" />(注：填写逗号分隔的帐号列表)</td>
                </tr>
            </table>
            <input type="submit" class="submitButton" style="cursor:pointer;" value="" />
        </form>
    </div>
</div>
<div class="b"></div>
<script type="text/javascript">
var editor{$ui.uiid}=null;
var ref=function(row){
    var content=$(".content",$("."+row)).get(0).innerHTML;
    if(editor{$ui.uiid}){
    	editor{$ui.uiid}.setSource("<div style='background:#FFFEF5;border:1px solid #D1D5DB;padding:3px;padding-bottom:10px;margin: 0 0 10px 10px;'>"+content+"</div><br />");
    	editor{$ui.uiid}.focus();
    	editor{$ui.uiid}.setTextCursor(true);
    }else{
    	textarea_{$ui.uiid}.value=content;
    }
}
$(function() {
    var $form=$("#form_{$ui.uiid}");
    editor{$ui.uiid}=$('#textarea_{$ui.uiid}').xheditor({
    	{if $login != null}
        upLinkUrl:"{$uploadUrl}",
        upLinkExt:"zip,rar,txt",
        upImgUrl:"{$uploadUrl}",
        upImgExt:"jpg,jpeg,gif,png",
        upFlashUrl:"{$uploadUrl}",
        upFlashExt:"swf",
        upMediaUrl:"{$uploadUrl}",
        upMediaExt:"wmv,avi,wma,mp3,mid",
        {if $login.roleType < 90}
        tools:'full'
        {/if}
        {else}
        tools:'simple'
        {/if}
    });
    $(".submitButton", $form).click(function() {
        $form.block();
        var form=$form.get(0); 
        $.post("{$commentUrl}", $form.serialize(), function(json) {
            alert(json.message);
            if(json.success){
                form.reset();
            	ui.gotoPage("block{$block.id}","page={if $pager.totalPage == null}1{else}{$pager.totalPage}{/if}");
            }
        }, "json");

        return false;
    });
});
</script>
