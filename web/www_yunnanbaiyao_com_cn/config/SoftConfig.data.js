[ {
	name : '【短信】参数设置',
	code : 'sms.xxx',
	value : '',
	desc : "设置短信功能相关的参数配置项，配置项前缀“sms.”",
	orderby : 20,
	children : [ {
		name : '【短信】通道类型',
		code : 'sms.type',
		value : 'emay',
		desc : "zucp: 漫道短信，emay: 亿美短信，zr: 展仁短信",
		orderby : 21
	}, {
		name : '【短信】通道登录帐号',
		code : 'sms.uid',
		value : '3SDK-KYJ-0130-KJXQL',
		orderby : 22
	}, {
		name : '【短信】通道登录密码',
		code : 'sms.pwd',
		value : '356860',
		orderby : 23
	}, {
		name : '【短信】通道序列号',
		code : 'sms.key',
		value : '147080',
		desc : '目前该配置项只用于emay短信通道。',
		orderby : 24
	}, {
		name : '【短信】通道URL地址',
		code : 'sms.url',
		value : '',
		orderby : 25
	}, {
		name : '【短信】代理服务器主机IP',
		code : 'sms.proxy.host',
		value : '',
		desc : '发送短信时需要连接到第三方服务器，这里设置的是本机服务器上网需要的代理服务器。',
		orderby : 26
	}, {
		name : '【短信】代理服务器主机端口',
		code : 'sms.proxy.port',
		value : '',
		desc : '发送短信时需要连接到第三方服务器，这里设置的是本机服务器上网需要的代理服务器。',
		orderby : 27
	}, {
		name : '【短信】短信签名',
		code : 'sms.signature',
		value : '【云南白药】',
		desc : '短信签名将放在短信内容的前面一起发送到手机上',
		orderby : 28
	}, {
		name : '【短信】“手机验证码”短信内容',
		code : 'sms.verify_code_content',
		value : '验证码：请在网页表单中输入您的验证码：%s',
		desc : '用户输入手机号码并点击“获取验证码”按钮后，将发送该短信到指定的手机上。参数说明：“%s”表示由系统随机生成的字母和数字组成的验证码。',
		orderby : 29
	}, {
		name : '【短信】“走进云南白药”短信邀请函',
		code : 'sms.visit.invitation',
		value : '邀请函：尊敬的%s先生/女士：您好，感谢您对云南白药的关注。我们诚邀您参加于%s在云南白药产业园区举办的“走进云南白药”活动，届时欢迎您的到来。验证码：%s',
		desc : '报名成功后将发送该邀请函到报名时提供的手机上。参数说明：第1个“%s”表示“姓名”，第2个“%s”表示“参观时间”，第3个“%s”表示“验证码”',
		orderby : 30
	} ]
} ]