package cn.com.sinosoft.ebusiness.member.emailManage.web;

import ins.framework.common.QueryRule;
import ins.framework.web.Struts2Action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.jfree.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.businessModule.bindPolicy.domain.BindPolicy;
import cn.com.sinosoft.businessModule.bindPolicy.service.facade.BindPolicyService;
import cn.com.sinosoft.ebusiness.infomanage.domain.EmailInfo;
import cn.com.sinosoft.ebusiness.sale.service.facade.SmsSendService;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPersonal;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.MobilePhoneCode;
import cn.com.sinosoft.ebusiness.service.user.personal.service.facade.GeUserPersonalService;
import cn.com.sinosoft.ebusiness.tools.WSClientHelper;
import cn.com.sinosoft.ebusiness.xmltransfer.EmailCreater;
import cn.com.sinosoft.ebusiness.xmltransfer.SubscribedCreater;

public class EmailSubscribedAction extends Struts2Action {
	private static final long serialVersionUID = 2393916264264268659L;
	/**
	 * @ProjectName:online
	 * @Package:
	 * @ClassName:
	 * @Description: 邮箱订阅管理
	 * @Author: jack_xiao
	 * @CreateDate: 2013-9-11
	 * @UpdateUser:
	 * @UpdateDate:
	 * @UpdateRemark:
	 * @Version: v1.0
	 * 
	 */
	// 用户信息
	private GeUserPersonal customer;
	// 所有订阅电子函件邮箱
	private List<EmailInfo> emailInfos = new ArrayList<EmailInfo>();
	// 已订阅电子函件信息
	private List<EmailInfo> subEmailInfos = new ArrayList<EmailInfo>();
	// 还未订阅电子函件信息
	private List<EmailInfo> notSubEmailInfos = new ArrayList<EmailInfo>();
	// 既订阅电子与订阅纸质
	private List<EmailInfo> paperAndEmailInfos = new ArrayList<EmailInfo>();
	// 未订阅电子函件信息个数
	private int notSubCount = 0;
	// 已订阅电子函件信息个数
	private int subCount = 0;
	private String subType;
	// 是否订阅
	private boolean isSubscribed = false;
	// 是由有绑定的保单号
	private boolean isBindPolicy = false;
	// 是否信息完善
	private boolean isInfoComplete = false;
	// 订阅保单json字符串
	private String subJsonStr = "";
	// 取消订阅保单json字符串
	private String cancleSubJsonStr = "";
	// 邮箱修改json字符串
	private String changeEmailJsonStr;
	// 手机验证码
	private String checkNo = "";
	@Autowired
	public GeUserPersonalService geUserPersonalService;
	@Autowired
	private SubscribedCreater subscribedCreater;
	@Autowired
	private WSClientHelper wsclientHelper;
	private String flag;
	private String desc;
	// 校验手机是否成功 Y成功 N失败
	private String checkPhone;
	// 获得绑定保单信息
	@Autowired
	private BindPolicyService bindPolicyService;
	// 电子函件管理类
	@Autowired
	private EmailCreater emailCreater;
	@Autowired
	private SmsSendService smsSendService;
	private static Logger log = LoggerFactory
			.getLogger(EmailSubscribedAction.class);

	/**
	 * 查询是否有订阅信息: 1 ： 若未有绑定的保单的列表 显示开通电子函件服务页面 ：（1）开通确定用户信息完善 （2） 跳转到用户绑定保单页面 2
	 * ： 若绑定的保单都是未订阅，显示为订阅页面，订阅之后刷新未订阅页面 并显示订阅保单的数量。 3 ： 若有绑定的保单有订阅信息，就显示订阅信息，
	 * 并显示未订阅信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String subscribedEmail() throws Exception {
		customer = (GeUserPersonal) super.getSession().getAttribute(
				"geUserPersonal");
		log.info("用户名：" +customer.getUserName()+"查看电子函件订阅信息......");
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("CUSTOMERID", customer.getUserID());
		List<BindPolicy> listBindPolicy = bindPolicyService
				.findPolicyByQueryRule(queryRule);

		// 未绑定保单 显示开通服务界面
		if (listBindPolicy.size() == 0) {
			isBindPolicy = false;
			return "open";
		} else {
			// 所有绑定保单电子函件信息
			// 请求报文：查询当前用户所有订阅、未订阅信息
			Document docRequest;
			Document docResponse;
			String sRequest;
			String sResponse;
			Map<String, Object> result;
			// 接收报文信息
			Map<String, Object> map = new HashMap<String, Object>();
			// 保存绑定的保单参数
			map.put("policys", listBindPolicy);

			// 请求与解析报文
			docRequest = emailCreater.createXml(map);
			log.info("邮箱订阅请求报文docRequest......:"+docRequest);
			System.out.println("邮箱订阅请求报文docRequest......:"+docRequest);
			sRequest = wsclientHelper.doc2String(docRequest);
			log.info("邮箱订阅请求报文sRequest......:"+sRequest);
			System.out.println("邮箱订阅请求报文sRequest......:"+sRequest);
			sResponse = wsclientHelper.submitGBK(sRequest);
			docResponse = wsclientHelper.string2Doc(sResponse);
			result = emailCreater.Xml2Object(docResponse);
			// 返回结果集
			flag = (String) result.get("flag");
			desc = (String) result.get("desc");
			
			if (flag.equals("1")) {
				emailInfos = (List<EmailInfo>) result.get("emailInfos");
				// 处理显示订阅或者未订阅页面 未订阅页面：没有订阅
				for (EmailInfo emailInfo : emailInfos) {
					if (emailInfo.getSubType().equals("01")
							|| emailInfo.getSubType().equals("03")) {
						// 已订阅
						subEmailInfos.add(emailInfo);
					} else {// 未订阅
						notSubEmailInfos.add(emailInfo);
					}
				}
				// 处理显示界面 1:订阅界面 提示为订阅数目 0 不提示订阅信息
				if (subEmailInfos.size() == 0) {
					// 未订阅 notSubscribed 不需要显示数量 显示订阅数目
					System.out.println(notSubEmailInfos.size());
					notSubCount = notSubEmailInfos.size();
					return "notSubscribed";
				} else {
					// 显示订阅信息 提示用户未订阅数量
					notSubCount = notSubEmailInfos.size();
					return "subscribed";
				}
			} else {
				log.info("系统错误.....:解析报文出错！");
				throw new Exception("系统错误.....:解析报文出错！");
			}

		}
	}

	@SuppressWarnings("unchecked")
	public String notSubscribe() {

		try {
			customer = (GeUserPersonal) super.getSession().getAttribute(
					"geUserPersonal");

			// 获得该用户下绑定的保单
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("CUSTOMERID", customer.getUserID());
			List<BindPolicy> listBindPolicy = bindPolicyService
					.findPolicyByQueryRule(queryRule);
			Document docRequest;
			Document docResponse;
			String sRequest;
			String sResponse;
			Map<String, Object> result;
			// 接收报文信息
			Map<String, Object> map = new HashMap<String, Object>();
			// 保存绑定的保单参数
			map.put("policys", listBindPolicy);
			docRequest = emailCreater.createXml(map);
			sRequest = wsclientHelper.doc2String(docRequest);
			sResponse = wsclientHelper.submitGBK(sRequest);

			docResponse = wsclientHelper.string2Doc(sResponse);
			result = emailCreater.Xml2Object(docResponse);
			flag = (String) result.get("flag");
			desc = (String) result.get("desc");
			emailInfos = (List<EmailInfo>) result.get("emailInfos");
			if (emailInfos.size() != 0) {
				// 处理显示订阅或者未订阅页面 未订阅页面：没有订阅
				for (EmailInfo emailInfo : emailInfos) {
					if (emailInfo.getSubType().equals("01")
							|| emailInfo.getSubType().equals("03")) {
						// 已订阅
						subEmailInfos.add(emailInfo);
					} else {// 未订阅
						notSubEmailInfos.add(emailInfo);
					}
				}
				// 显示未订阅信息 提示用户订阅数量
				notSubCount = notSubEmailInfos.size();
				subCount = subEmailInfos.size();

				return SUCCESS;
			} else {
				throw new Exception("系统错误.....:该用户所有绑定的保单无订阅信息");
			}
		} catch (Exception e) {
			Log.info("系统错误......：" + e.getMessage());
			e.printStackTrace();
		}

		return SUCCESS;
	}

	/**
	 * 用户信息是否完善 1,姓名 性别 出生日期 证件类型 证件号码 验证5要素
	 * 
	 */
	public String checkUserInfoComplete() {

		customer = (GeUserPersonal) super.getSession().getAttribute(
				"geUserPersonal");
		if (customer.getBirthday() == null || customer.getBirthday().equals("")
				|| customer.getSex() == null || customer.getSex().equals("")
				|| customer.getUserName() == null
				|| customer.getUserName().equals("")
				|| customer.getIdentifyType() == null
				|| customer.getIdentifyType().equals("")
				|| customer.getIdentifyNumber() == null
				|| customer.getIdentifyNumber().equals("")) {
			isInfoComplete = false;

			return SUCCESS;

		} else {
			isInfoComplete = true;

			return SUCCESS;
		}
	}

	/**
	 * 功能点描述：02：纸质通知书 04：都不寄送 这2中方式未订阅电子函件的保单订阅 02->01 04->01
	 * 
	 * @author jack_xiao
	 * @return
	 * @throws Exception
	 */
	public String subscribed() throws Exception {
		// 订阅对象
		EmailInfo emaillInfo = null;
		JSONArray jsonArray = new JSONArray(subJsonStr);
		int iSize = jsonArray.length();

		System.out.println("Size:" + iSize);
		for (int i = 0; i < iSize; i++) {
			emaillInfo = new EmailInfo();
			// 获得一个json数组
			JSONObject jsonObj = jsonArray.getJSONObject(i);

			emaillInfo.setPolicyNo(jsonObj.getString("policyNo"));
			emaillInfo.setEmail(jsonObj.getString("email"));
			// 设置订阅方式
			emaillInfo.setSubType("01");
			// 每个单 发送一次请求报文
			subReqToResByPars(emaillInfo);

		}

		return SUCCESS;
	}

	/**
	 * 功能描述： 取消订阅
	 * 
	 * @return
	 * @throws Exception
	 */
	public String cancelEmail() throws Exception {
		// 订阅对象
		EmailInfo emaillInfo = null;
		JSONObject jsonObj = new JSONObject(cancleSubJsonStr);
		emaillInfo = new EmailInfo();
		// 获得一个json数组
		emaillInfo.setPolicyNo(jsonObj.getString("policyNo"));
		emaillInfo.setEmail(jsonObj.getString("email"));
		// 设置订阅方式
		emaillInfo.setSubType("0" + jsonObj.getString("subType"));
		// 每个单 发送一次请求报文
		subReqToResByPars(emaillInfo);

		return SUCCESS;

	}

	/**
	 * 功能描述： 邮箱修改
	 * 
	 * 
	 * @return
	 * @throws Exception
	 */
	public String changeEmail() throws Exception {
		// 订阅对象
		EmailInfo emaillInfo = null;
		JSONObject jsonObj = new JSONObject(changeEmailJsonStr);
		emaillInfo = new EmailInfo();
		// 获得一个json数组
		emaillInfo.setPolicyNo(jsonObj.getString("policyNo"));
		emaillInfo.setEmail(jsonObj.getString("email"));
		// 设置订阅方式
		emaillInfo.setSubType("0" + jsonObj.getString("subType"));
		// 每个单 发送一次请求报文
		subReqToResByPars(emaillInfo);

		return SUCCESS;

	}

	/**
	 * 功能描述： 手机验证码获得
	 * 
	 * 
	 * @return
	 */
	public String sendPhoneDynamicNumber() throws Exception {
		customer = (GeUserPersonal) super.getSession().getAttribute(
				"geUserPersonal");
		String phoneNum = customer.getMobilePhone();
		//查询当前手机发送验证码是否超过次数
		Integer count = geUserPersonalService.getTransmissionTimesByAccount(phoneNum);
		if (count > 50) {
				flag="1";
				desc="非常抱歉，今天您进行相关网站操作，验证码发送已累计超过50次，请您明天再试。";
			return SUCCESS;
		}		
		// 用户手机号
		String sender = "9005";
		checkNo = createCheckNo();
		// 短信内容
		String msgComment = "尊敬的用户，您正在修改邮箱地址，验证码为:" + checkNo + "。该验证码有效期为30分钟，请及时输入该验证码进行邮箱修改。";
		//保存当前用户手机号码到数据库中
		geUserPersonalService.saveMobilePhoneCode(phoneNum, checkNo);
		//请求核心发送验证码
		smsSendService.smsSend(false, "", null, "123", phoneNum, msgComment,
				sender, "");
		//统计当前时间该手机号发送验证码的次数
		geUserPersonalService.saveTransmissionTimes(phoneNum, count + 1);
		return SUCCESS;
	}
	/**
	 * 功能：校验手机是否输入正确
	 * 
	 */
	public String checkPhone(){
		log.info("手机验证码校验....");
		//获得请求中的输入的校验码
		String validPhoneNo=getRequest().getParameter("validPhoneNo");
		//获得当前用户的手机号
		customer = (GeUserPersonal) super.getSession().getAttribute(
				"geUserPersonal");
		String phoneNum = customer.getMobilePhone();
		
		MobilePhoneCode mobilePhoneCode = geUserPersonalService.findMobilePhoneCode(phoneNum);
		if (mobilePhoneCode == null) {
			checkPhone="error";
			 
		}
		else if (!validPhoneNo.equals(mobilePhoneCode.getCode())) {
			checkPhone="N";
		}else{
			checkPhone="Y";
		}
		
		return SUCCESS;
	}
	/**
	 * 生成随机数
	 * 
	 * @return
	 */
	private String createCheckNo() {
		String number = ("" + (Math.random() * 10000000)).substring(0, 6)
				.replace(".", "8");
		return number;
	}

	private void subReqToResByPars(EmailInfo emailInfo) throws Exception {
		Document docRequest;
		Document docResponse;
		String sRequest;
		String sResponse;
		Map<String, String> result;
		// 创建报文请求并响应
		Map<String, Object> requestParms = new HashMap<String, Object>();

		requestParms.put("contNo", emailInfo.getPolicyNo());
		requestParms.put("email", emailInfo.getEmail());
		requestParms.put("subType", emailInfo.getSubType());

		// 创造请求报文Dom对象
		docRequest = subscribedCreater.createXml(requestParms);
		sRequest = wsclientHelper.doc2String(docRequest);
		sResponse = wsclientHelper.submitGBK(sRequest);
		docResponse = wsclientHelper.string2Doc(sResponse);

		result = subscribedCreater.Xml2Object(docResponse);

		flag = result.get("flag");
		desc = result.get("desc");
	}

	public boolean getIsSubscribed() {
		return isSubscribed;
	}

	public void setSubscribed(boolean isSubscribed) {
		this.isSubscribed = isSubscribed;
	}

	public boolean getIsBindPolicy() {
		return isBindPolicy;
	}

	public void setBindPolicy(boolean isBindPolicy) {
		this.isBindPolicy = isBindPolicy;
	}

	public boolean getIsInfoComplete() {
		return isInfoComplete;
	}

	public void setInfoComplete(boolean isInfoComplete) {
		this.isInfoComplete = isInfoComplete;
	}



	public List<EmailInfo> getEmailInfos() {
		return emailInfos;
	}

	public void setEmailInfos(List<EmailInfo> emailInfos) {
		this.emailInfos = emailInfos;
	}

	public List<EmailInfo> getSubEmailInfos() {
		return subEmailInfos;
	}

	public void setSubEmailInfos(List<EmailInfo> subEmailInfos) {
		this.subEmailInfos = subEmailInfos;
	}

	public List<EmailInfo> getNotSubEmailInfos() {
		return notSubEmailInfos;
	}

	public void setNotSubEmailInfos(List<EmailInfo> notSubEmailInfos) {
		this.notSubEmailInfos = notSubEmailInfos;
	}

	public WSClientHelper getWsclientHelper() {
		return wsclientHelper;
	}

	public void setWsclientHelper(WSClientHelper wsclientHelper) {
		this.wsclientHelper = wsclientHelper;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getNotSubCount() {
		return notSubCount;
	}

	public void setNotSubCount(int notSubCount) {
		this.notSubCount = notSubCount;
	}

	public String getCancleSubJsonStr() {
		return cancleSubJsonStr;
	}

	public void setCancleSubJsonStr(String cancleSubJsonStr) {
		this.cancleSubJsonStr = cancleSubJsonStr;
	}

	public String getSubJsonStr() {
		return subJsonStr;
	}

	public void setSubJsonStr(String subJsonStr) {
		this.subJsonStr = subJsonStr;
	}

	public int getSubCount() {
		return subCount;
	}

	public void setSubCount(int subCount) {
		this.subCount = subCount;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public List<EmailInfo> getPaperAndEmailInfos() {
		return paperAndEmailInfos;
	}

	public void setPaperAndEmailInfos(List<EmailInfo> paperAndEmailInfos) {
		this.paperAndEmailInfos = paperAndEmailInfos;
	}

	public String getChangeEmailJsonStr() {
		return changeEmailJsonStr;
	}

	public void setChangeEmailJsonStr(String changeEmailJsonStr) {
		this.changeEmailJsonStr = changeEmailJsonStr;
	}

	public String getCheckNo() {
		return checkNo;
	}

	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}

	public String getCheckPhone() {
		return checkPhone;
	}

	public void setCheckPhone(String checkPhone) {
		this.checkPhone = checkPhone;
	}
    
}
