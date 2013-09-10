package com.jiongsoft.cocit.sms.impl;

import java.util.ArrayList;
import java.util.List;

import cn.emay.sdk.client.api.Client;
import cn.emay.sdk.client.api.MO;

import com.jiongsoft.cocit.Cocit;
import com.jiongsoft.cocit.CocitHttpContext;
import com.jiongsoft.cocit.cocsoft.CocSoft;
import com.jiongsoft.cocit.cocsoft.CocSoftConfig;
import com.jiongsoft.cocit.sms.SmsClient;
import com.jiongsoft.cocit.utils.Log;
import com.jiongsoft.cocit.utils.StringUtil;

/**
 * 亿美嵌入型短信接口：http://www.emay.cn
 * 
 * <UL>
 * <LI>0 成功
 * <LI>-1 系统异常
 * <LI>-2 客户端异常
 * <LI>-9001 序列号格式错误
 * <LI>-9002 密码格式错误
 * <LI>-9003 客户端key格式错误
 * <LI>-9025 客户端请求sdk5超时
 * <LI>-101 命令不被支持
 * <LI>-104 请求超过限制
 * <LI>-110 号码注册激活失败
 * <LI>-1100 序列号错误，序列号不存在内存中或尝试攻击的用户
 * <LI>-1103 序列号key错误
 * <LI>-1105 注册号状态异常，未用1
 * <LI>-1107 注册号状态异常，停用3
 * <LI>-1108 注册号状态异常，停止5
 * <LI>-126 路由信息失败
 * <LI>-1104 路由失败，请联系系统管理员
 * <LI>-190 数据操作失败
 * <LI>-1901 数据库更新操作失败
 * <LI>303 客户端网络超时或网络故障
 * <LI>305 服务器端返回错误，错误的返回值（返回值不是数字字符串）
 * <LI>999 操作频繁
 * </UL>
 * 
 * @author yongshan.ji
 * 
 */
public class EmaySDKSmsClient implements SmsClient {

	private Client emayClient;

	private String proxyHost;
	private int proxyPort;

	private String uid;
	private String pwd;// 本地明文密码
	private String key;// 序列号

	public EmaySDKSmsClient() {
		CocitHttpContext ctx = Cocit.getHttpContext();

		if (ctx != null) {
			CocSoft soft = ctx.getSoft();

			this.proxyHost = soft.getConfig(CocSoftConfig.CFG_PROXY_HOST, "");
			this.proxyPort = soft.getConfig(CocSoftConfig.CFG_PROXY_PORT, 80);

			this.uid = soft.getConfig(CocSoftConfig.CFG_UID, "");
			this.pwd = soft.getConfig(CocSoftConfig.CFG_PWD, "");
			this.key = soft.getConfig("sms.key", "");

			try {

				emayClient = new Client(uid, key);
				int registResult = emayClient.registEx(pwd);

				Log.info("EmaySmsClient.new: registResult=%s {uid:%s, pwd:%s, key:%s, proxyHost:%s, proxyPort:%s}", registResult, uid, pwd, key, proxyHost, proxyPort);

			} catch (Exception e) {
				Log.error("EmaySmsClient.new: 失败！{uid:%s, pwd:%s, key:%s, proxyHost:%s, proxyPort:%s}", uid, pwd, key, proxyHost, proxyPort);
			}
		}
	}

	@Override
	public String queryBalance() {

		if (emayClient == null) {
			Log.error("EmaySmsClient.queryBalance: 失败！{emayClient:null}");

			return null;
		}

		try {
			double ret = this.emayClient.getBalance();

			Log.info("EmaySmsClient.queryBalance: ret=%s", ret);

			return "" + ret;
		} catch (Exception e) {
			Log.error("EmaySmsClient.queryBalance: 失败！", e);
		}

		return null;
	}

	/**
	 * <p>
	 * 参数：
	 * <UL>
	 * <LI>mobiles 手机号码(群发为字符串数组推荐最多为200个手机号码或以内)
	 * <LI>smsContent 短信内容(最多500个汉字或1000个纯英文，emay服务器程序能够自动分割；亿美有多个通道为客户提供服务，所以分割原则采用最短字数的通道为分割短信长度的规则，请客户应用程序不要自己分割短信以免造成混乱)
	 * <LI>addSerial 扩展号 (长度小于15的字符串) 用户可通过扩展号自定义短信类别
	 * <LI>smsPriority 优先级(级别从1到5的正整数，数字越大优先级越高，越先被发送)
	 * 
	 * <p>
	 * 返回值：
	 * <UL>
	 * <UL>
	 * <LI>0 成功
	 * <LI>-1 系统异常
	 * <LI>-2 客户端异常
	 * <LI>-9001 序列号格式错误
	 * <LI>-9002 密码格式错误
	 * <LI>-9003 客户端key格式错误
	 * <LI>-9016 发送短信包大小超出范围
	 * <LI>-9017 发送短信内容格式错误
	 * <LI>-9019 发送短信优先级格式错误
	 * <LI>-9020 发送短信手机号格式错误
	 * <LI>-9022 发送短信唯一序列值错误
	 * <LI>-9025 客户端请求sdk5超时
	 * <LI>-101 命令不被支持
	 * <LI>-104 请求超过限制
	 * <LI>-117 发送短信失败
	 * <LI>-126 路由信息失败
	 * <LI>-1104 路由失败，请联系系统管理员
	 * <LI>101 客户端网络故障
	 * <LI>307 目标电话不符合规则，电话号码必须是以0，1开头
	 * <LI>303 由于客户端网络问题导致信息发送超时，该信息是否成功下发无法确定
	 * <LI>305 服务器端返回错误，错误的返回值（返回值不是数字字符串）
	 * </UL>
	 */
	@Override
	public String send(String mobiles, String content, String extCode, String time, String rrid) {

		if (emayClient == null) {
			Log.info("EmaySmsClient.send: 失败！{emayClient:null}");

			return null;
		}

		int ret = this.emayClient.sendSMS(StringUtil.toArray(mobiles, ","), content, 5);

		Log.info("EmaySmsClient.send: result=%s {mobiles:%s,  content:%s,  extCode:%s,  time:%s,  rrid:%s}", ret, mobiles, content, extCode, time, rrid);

		return "" + ret;
	}

	@Override
	public List<String[]> receive() {

		if (emayClient == null) {
			Log.info("EmaySmsClient.receive: 失败！{emayClient:null}");

			return null;
		}

		List result = new ArrayList();

		try {

			List<MO> moList = this.emayClient.getMO();
			if (moList != null) {
				for (MO mo : moList) {
					String[] arr = new String[3];

					arr[0] = mo.getMobileNumber();
					arr[1] = mo.getSentTime();
					arr[2] = mo.getSmsContent();

					Log.debug("%s/%s/%s", arr[0], arr[1], arr[2]);

					result.add(arr);
				}
			}

			Log.info("EmaySmsClient.receive: moList=%s", moList == null ? "<NULL>" : moList.size());

		} catch (Exception e) {
			Log.error("EmaySmsClient.receive: 失败！", e);

			result = null;
		}

		return result;
	}

}
