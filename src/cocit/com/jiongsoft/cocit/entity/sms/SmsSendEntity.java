package com.jiongsoft.cocit.entity.sms;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.jiongsoft.cocit.entity.annotation.CocField;
import com.jiongsoft.cocit.entity.annotation.CocGroup;
import com.jiongsoft.cocit.entity.annotation.CocOperation;
import com.jiongsoft.cocit.entity.annotation.CocTable;
import com.jiongsoft.cocit.entity.impl.BaseCocEntity;
import com.jiongsoft.cocit.utils.ActionUtil;

/**
 * 短信发送实体类：即下行短信，该类的实体对象表示一次短信发送记录。
 * 
 * @author jiongsoft
 * 
 */
@Entity
@CocTable(name = "短信发送管理", code = "SmsSendEntity", actionPathPrefix = ActionUtil.ACTION_PATH_PREFIX, orderby = 1//
// 操作按钮
, actions = { @CocOperation(name = "发送短信", typeCode = 101, mode = "c")//
		, @CocOperation(name = "删除", typeCode = 299, mode = "d") //
		, @CocOperation(name = "查看短信", typeCode = 102, mode = "v") //
}// end: actions
// 业务分组
, groups = { //
@CocGroup(name = "基本信息", code = "basic"//
// 业务字段
, fields = { @CocField(name = "短信主题", mode = "*:N v:S c:M e:M", property = "title", desc = "发送本次短信的主题是什么？")//
		, @CocField(name = "手机号码", mode = "*:N v:S c:M e:M", property = "mobiles", desc = "多个手机号码之间用“,”逗号分隔")//
		, @CocField(name = "短信内容", mode = "*:N v:S c:M e:M", property = "content", desc = "最多256个汉字")//
		, @CocField(name = "定时发送", mode = "*:N v:S c:E e:E", property = "sendTime", pattern = "yyyy-MM-dd HH-mm-ss")//
		, @CocField(name = "发送结果", mode = "*:N v:S", property = "result", desc = "描述短信发送是否成功！") //
		, @CocField(name = "上次结余(条)", mode = "*:N v:S", property = "balance", desc = "上次发送完成后剩下多少短信余额(条)") //
		, @CocField(name = "本次计费(条)", mode = "*:N v:S", property = "cost", desc = "本次发送需要消费多少短信费用(条)") //
		, @CocField(name = "提交时间", mode = "*:N v:S", property = "created", pattern = "yyyy-MM-dd HH-mm-ss") //
}// end: fields
) // end: BzGrp
}// end: groups
)
public class SmsSendEntity extends BaseCocEntity {

	@Column(length = 64)
	String title;

	@Column(columnDefinition = "text")
	String mobiles;

	@Column(length = 512)
	String content;

	Date sendTime;

	@Column(length = 128)
	String result;

	int cost;

	int balance;

	Date created;
}
