package com.kmetop.demsy.comlib.impl.sft.lybbs;

import static com.kmetop.demsy.biz.BizConst.TYPE_BZFORM_EDIT_N;
import static com.kmetop.demsy.biz.BizConst.TYPE_BZFORM_NEW;
import static com.kmetop.demsy.comlib.LibConst.BIZCATA_WEB;
import static com.kmetop.demsy.comlib.LibConst.ORDER_WEB_FORUM_CATALOG;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.jiongsoft.cocit.entity.annotation.CocField;
import com.jiongsoft.cocit.entity.annotation.CocGroup;
import com.jiongsoft.cocit.entity.annotation.CocOperation;
import com.jiongsoft.cocit.entity.annotation.CocTable;
import com.kmetop.demsy.comlib.biz.field.Upload;
import com.kmetop.demsy.comlib.web.IBbsForum;
import com.kmetop.demsy.lang.Cls;

@Entity
@Table(name = "lybbs_db")
@CocTable(name = "论坛分类设置", code = IBbsForum.SYS_CODE, catalog = BIZCATA_WEB, orderby = ORDER_WEB_FORUM_CATALOG//
, actions = {
//
		@CocOperation(name = "添加", typeCode = TYPE_BZFORM_NEW, mode = "c")//
		, @CocOperation(name = "设置版主", typeCode = TYPE_BZFORM_EDIT_N, mode = "bu1")//
		, @CocOperation(jsonData = "CommonBizAction.data.js") //
}//
, groups = { @CocGroup(name = "基本信息", code = "basic"//
, fields = {
//
		@CocField(property = "name", gridOrder = 1)//
		, @CocField(property = "teamlogo", gridOrder = 2) //
		, @CocField(property = "parent")//
		// , @CocField(property = "privateforum") //
		// , @CocField(property = "dbpassword") //
		// , @CocField(property = "indexforum") //
		, @CocField(property = "type", gridOrder = 3) //
		, @CocField(property = "checkPostStatus") //
		, @CocField(property = "adminUsers", gridOrder = 4) //
		, @CocField(property = "desc") //
		, @CocField(property = "topicnumber", gridOrder = 5)//
		, @CocField(property = "replynumber", gridOrder = 6) //
		, @CocField(property = "orderby", gridOrder = 6) //
})
// , @CocGroup(name = "定时状态", code = "timestatus"//
// , fields = {
// //
// @CocField(property = "timestatus")//
// , @CocField(property = "timefromhour")//
// , @CocField(property = "timefromminute") //
// , @CocField(property = "timetohour") //
// , @CocField(property = "timetominute") //
// })
}// end groups
)
public class LybbsDb implements IBbsForum {
	@Id
	@GeneratedValue(generator = "SftIdGen", strategy = GenerationType.TABLE)
	@TableGenerator(name = "SftIdGen", table = "DEMSY_00000000", pkColumnName = "id_key", valueColumnName = "next_hi", allocationSize = 1, initialValue = 20)
	protected Short id;

	/*
	 * 基本信息
	 */
	@ManyToOne
	@Column(name = "parentid")
	@CocField(name = "上级论坛", gridField = false)
	protected LybbsDb parent;

	@Column(length = 30, name = "dbname")
	@CocField(name = "论坛名称", mode = "c:M e:M")
	protected String name;

	@Column(name = "dbdescription", length = 256)
	@CocField(name = "论坛描述")
	protected String desc;

	@Column(length = 1)
	@CocField(name = "保密论坛", options = "1:是,0:否", mode = "c:M e:M", disabledNavi = true, desc = "普通用户需要保密密码或者被允许访问保密论坛才能够进入。")
	protected Character privateforum = '0';

	@Column(length = 30)
	@CocField(name = "保密论坛密码", password = true)
	protected String dbpassword;

	@Column(length = 1)
	@CocField(name = "是否打开", options = "1:打开,0:关闭", mode = "c:M e:M", disabledNavi = true, desc = "可以暂时关闭这个论坛，此时只允许管理员访问！")
	protected Character indexforum = '1';

	@Column(length = 1)
	@CocField(name = "允许发帖", options = "1:禁止,0:允许", mode = "c:M e:M", disabledNavi = true, desc = "允许在此论坛发帖？ 可以禁止在此论坛发帖，而只允许在它的二级论坛下面发帖。")
	protected Character disablepost = '0';

	@Column(length = 20, name = "startnewthreads")
	@CocField(name = "论坛类型", options = "yes:正规论坛,all:开放论坛", mode = "c:M e:M", desc = "1. 正规论坛-只允许注册会员发言2. 开放论坛-允许所有人发言 3. 评论论坛-坛主和版主允许发言，其他注册用户只能回复 4. 精华区-只允许版主和坛主发言和操作 5. 认证论坛-除坛主和版主外，其他注册用户发言需要认证")
	protected String type;// ,follow:评论论坛,no:只读精华,cert:认证论坛

	@Column(length = 255)
	@CocField(name = "概述图片", uploadType = "*.bmp;*.jpg;*.gif;*.png;*.swf;*.flv", mode = "c:M e:M")
	protected Upload teamlogo;

	@Column(length = 1, name = "isvalidate")
	@CocField(name = "发帖审核", options = "1:审核,0:不审核", mode = "c:M e:M", desc = "此论坛发帖是否需要审核？ 如果需要审核，则必须由管理员审核后才可以显示。")
	protected byte checkPostStatus;

	@Column(length = 150, name = "dbadmin")
	@CocField(name = "论坛版主", mode = "bu1:E", desc = "网站会员登录帐号，可以有多个，之间以英文逗号“,”分隔！请不要有空格或其它符号字符！")
	protected String adminUsers;

	/*
	 * 论坛统计
	 */
	@CocField(name = "主题数", mode = "*:N v:S")
	protected Integer topicnumber;

	@CocField(name = "回复数", mode = "*:N v:S")
	protected Integer replynumber;

	/*
	 * 论坛定时状态
	 */
	@CocField(name = "定时状态", options = "0:打开,1:验证,2:浏览,3:关闭", disabledNavi = true)
	protected byte timestatus;

	@CocField(name = "起始时", options = "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23", disabledNavi = true)
	protected byte timefromhour;

	@CocField(name = "分", options = "0,5,10,15,20,25,30,35,40,45,50,55", disabledNavi = true)
	protected byte timefromminute;

	@CocField(name = "截止时", options = "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23", disabledNavi = true)
	protected byte timetohour;

	@CocField(name = "分", options = "0,5,10,15,20,25,30,35,40,45,50,55", disabledNavi = true)
	protected byte timetominute;

	@CocField(name = "显示顺序", mode = "*:N v:S c:M e:M")
	protected Integer orderby;

	@Override
	public int hashCode() {
		if (id == null) {
			return super.hashCode();
		}
		return 37 * 17 + id.hashCode();
	}

	@Override
	public boolean equals(Object that) {
		if (that == null)
			return false;

		if (!Cls.getType(getClass()).equals(Cls.getType(that.getClass()))) {
			return false;
		}

		LybbsDb thatEntity = (LybbsDb) that;
		if (id == null || id == 0 || thatEntity.id == null || thatEntity.id == 0) {
			return this == that;
		}

		return thatEntity.id.equals(id);
	}

	public String toString() {
		return name;
	}

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String dbname) {
		this.name = dbname;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String dbdescription) {
		this.desc = dbdescription;
	}

	public Character getPrivateforum() {
		return privateforum;
	}

	public void setPrivateforum(Character privateforum) {
		this.privateforum = privateforum;
	}

	public String getDbpassword() {
		return dbpassword;
	}

	public void setDbpassword(String dbpassword) {
		this.dbpassword = dbpassword;
	}

	public Character getIndexforum() {
		return indexforum;
	}

	public void setIndexforum(Character indexforum) {
		this.indexforum = indexforum;
	}

	public Character getDisablepost() {
		return disablepost;
	}

	public void setDisablepost(Character disablepost) {
		this.disablepost = disablepost;
	}

	public String getType() {
		return type;
	}

	public void setType(String startnewthreads) {
		this.type = startnewthreads;
	}

	public Upload getTeamlogo() {
		return teamlogo;
	}

	public void setTeamlogo(Upload teamlogo) {
		this.teamlogo = teamlogo;
	}

	public String getAdminUsers() {
		return adminUsers;
	}

	public void setAdminUsers(String dbadmin) {
		this.adminUsers = dbadmin;
	}

	public byte getTimestatus() {
		return timestatus;
	}

	public void setTimestatus(byte timestatus) {
		this.timestatus = timestatus;
	}

	public byte getTimefromhour() {
		return timefromhour;
	}

	public void setTimefromhour(byte timefromhour) {
		this.timefromhour = timefromhour;
	}

	public byte getTimefromminute() {
		return timefromminute;
	}

	public void setTimefromminute(byte timefromminute) {
		this.timefromminute = timefromminute;
	}

	public byte getTimetohour() {
		return timetohour;
	}

	public void setTimetohour(byte timetohour) {
		this.timetohour = timetohour;
	}

	public byte getTimetominute() {
		return timetominute;
	}

	public void setTimetominute(byte timetominute) {
		this.timetominute = timetominute;
	}

	public Integer getTopicnumber() {
		return topicnumber;
	}

	public void setTopicnumber(Integer topicnumber) {
		this.topicnumber = topicnumber;
	}

	public Integer getReplynumber() {
		return replynumber;
	}

	public void setReplynumber(Integer replynumber) {
		this.replynumber = replynumber;
	}

	public LybbsDb getParent() {
		return parent;
	}

	public void setParent(LybbsDb parent) {
		this.parent = parent;
	}

	public Integer getOrderby() {
		return orderby;
	}

	public void setOrderby(Integer orderby) {
		this.orderby = orderby;
	}

	public byte getCheckPostStatus() {
		return checkPostStatus;
	}

	public void setCheckPostStatus(byte checkPost) {
		this.checkPostStatus = checkPost;
	}
}