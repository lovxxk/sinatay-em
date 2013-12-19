package cn.com.sinosoft.ebusiness.service.user.personal.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;
import ins.framework.utils.StringUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.time.DateUtils;
import org.dom4j.Document;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import cn.com.sinosoft.businessModule.bindPolicy.domain.BindPolicy;
import cn.com.sinosoft.businessModule.bindPolicy.service.facade.BindPolicyService;
import cn.com.sinosoft.businessModule.order.domain.OrderForm;
import cn.com.sinosoft.ebusiness.ali.config.AlipayConfig;
import cn.com.sinosoft.ebusiness.ali.util.AlipaySubmit;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.InlineFile;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.service.facade.BizCommonService;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.service.facade.SendEmailService;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.service.spring.mail.CheckedMailException;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.service.spring.mail.MailService;
import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeCode;
import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeFunctionSwitch;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeCodeService;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeFunctionSwitchService;
import cn.com.sinosoft.ebusiness.infomanage.domain.PolicyList;
import cn.com.sinosoft.ebusiness.log.LocusTrace;
import cn.com.sinosoft.ebusiness.service.user.common.domain.LoginUserInfo;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeResultsDto;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPersonal;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.MobilePhoneCode;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.PersonalUserDto;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.TransmissionTimes;
import cn.com.sinosoft.ebusiness.service.user.personal.service.facade.GeUserPersonalService;
import cn.com.sinosoft.ebusiness.sys.moduleException.businessException.BasicBizInfoCommonException;
import cn.com.sinosoft.ebusiness.sys.moduleException.businessException.BizConfigCommonException;
import cn.com.sinosoft.ebusiness.sys.moduleException.businessException.UserServiceException;
import cn.com.sinosoft.ebusiness.sys.moduleException.businessException.portalModuleException;
import cn.com.sinosoft.ebusiness.tools.BT;
import cn.com.sinosoft.ebusiness.tools.WSClientHelper;
import cn.com.sinosoft.ebusiness.weibo4j.Users;
import cn.com.sinosoft.ebusiness.weibo4j.http.AccessToken;
import cn.com.sinosoft.ebusiness.xmltransfer.PolicyListCreater;
import cn.com.sinosoft.portalModule.interfacePortal.webService.service.WebService;
import cn.com.sinosoft.util.encode.Md5;
import cn.com.sinosoft.util.io.FilePathUtil;
import cn.com.sinosoft.util.io.PropertyFileUtils;
import cn.com.sinosoft.util.security.springSecurity.SpringSecurityUtils;

import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;

/**
 * 个人服务实现类
 * 
 *  
 * 
 */
public class GeUserPersonalServiceSpringImpl extends
		GenericDaoHibernate<GeUserPersonal, String> implements
		GeUserPersonalService {
	private static Logger log = LoggerFactory
			.getLogger(GeUserPersonalServiceSpringImpl.class);

	private static final String imageOnlineAbsolutePath = "";

	private WebService webService;
	private GeFunctionSwitchService geFunctionSwitchService;
	private MailService mailService;
	private BizCommonService bizCommonService;
	private GeCodeService geCodeService;
	
	private BindPolicyService  bindPolicyService;
	
	@Autowired
	private PolicyListCreater policyListCreater;
	@Autowired
	private WSClientHelper wsclientHelper;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SendEmailService sendEmailService;
	
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	
	@Override
	public List<PolicyList> findPolicyList(String userId) {
		// 查询保单绑定表中的保单号.
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("CUSTOMERID", userId);
		List<BindPolicy> listBindPolicy = bindPolicyService.findPolicyByQueryRule(queryRule);
		List<PolicyList> listPolicyList = new ArrayList<PolicyList>();

		// 查询保单列表
		if (listBindPolicy.size() == 0) {
			return listPolicyList;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("listBindPolicy", listBindPolicy);
		try {
			Document docRequest = policyListCreater.createXml(map);
			String sRequest = wsclientHelper.doc2String(docRequest);
			String sResponse = wsclientHelper.submitGBK(sRequest);
			Document docResponse = wsclientHelper.string2Doc(sResponse);
			if (BT.isSuccess(docResponse)) {
				listPolicyList = policyListCreater.Xml2Object(docResponse);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listPolicyList;
	}

	public BizCommonService getBizCommonService() {
		return bizCommonService;
	}

	public void setBizCommonService(BizCommonService bizCommonService) {
		this.bizCommonService = bizCommonService;
	}

	public MailService getMailService() {
		return mailService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	public WebService getWebService() {
		return webService;
	}

	public void setWebService(WebService webService) {
		this.webService = webService;
	}

	public GeFunctionSwitchService getGeFunctionSwitchService() {
		return geFunctionSwitchService;
	}

	public void setGeFunctionSwitchService(
			GeFunctionSwitchService geFunctionSwitchService) {
		this.geFunctionSwitchService = geFunctionSwitchService;
	}


	@Override
	public List registerWithLDAP(GeUserPersonal geUserPersonal) {
		List resultList = new ArrayList();

		/*** 默认值 **/
		geUserPersonal.setUserID(getUserID());
		/** 设置客户号 */
		geUserPersonal.setPwd(new Md5().toMD5(geUserPersonal.getPwd()));
		/** 密码MD5加密 */
		geUserPersonal.setIntegral(new BigDecimal(0));
		/** 设置积分 */
		geUserPersonal.setCheckStatus("0");
		/** 设置验真 */
		geUserPersonal.setUserSource("1");
		/** 设置用户来源 */
		geUserPersonal.setUserLevel("1");
		/** 设置用户等级 */
		geUserPersonal.setUserRank("T");
		/** 设置寿险用户等级 */
		geUserPersonal.setMakeDate(new Date());
		/** 创建时间 */
		geUserPersonal.setRaInd("0");
		/** 设置用户RA认证状态，0-未认证，1-已认证 */
		if (activeSwitchIsOpen()) {
			geUserPersonal.setStatus("2");
			/** 设置用户的处理状态为2，未激活 */
		} else {
			resultList.add("noActive");
			geUserPersonal.setStatus("1");
			/** 设置用户的处理状态为1，已激活 */
		}
		/** 设置用户所在地区（从页面接收到的市级地区代码） */
		geUserPersonal.setAreaCode(geUserPersonal.getCity());
		
		if (LDAPSwitchIsOpen()) {
			/** 判断功能开关 */
			GeResultsDto geResults = insertUserToLDAP(geUserPersonal).getGeResults();
			List list = geResults.getResult();
			if (list != null && list.contains("1")) {
				/** 判断交易是否成功 */
				saveUser(geUserPersonal);
			} else if (list.contains("2")) { // 客户号重复
				registerWithLDAP(geUserPersonal);
			}
			resultList.addAll(list);
			return resultList;
		} else {
			if (!isUnique("USERID", geUserPersonal.getUserID())) {
				/** 用户号重复 */
				registerWithLDAP(geUserPersonal);
			}
			if (!isUnique("USERACCOUNT", geUserPersonal.getUserAccount())) {
				/** 3-用户名重复 */
				resultList.add("3");
			}
			if (!isUnique("EMAIL", geUserPersonal.getEmail())) {
				/** 4-邮箱重复 */
				resultList.add("4");
			}
			if ("01".equals(geUserPersonal.getIdentifyType())&&(!isIdentifyUnique("IDENTIFYTYPE","01","IDENTIFYNUMBER",geUserPersonal.getIdentifyNumber()))) {
				/** 5-身份证重复 */
				resultList.add("5");
			}
			/**校验唯一性成功 */
			if ((resultList.contains("noActive") && resultList.size() == 1)|| resultList.size() == 0) {
				if (saveUser(geUserPersonal)) {
					resultList.add("1");
				} else {
					resultList.add("0");
					/** 0-保存本地库失败 */
				}
			}
			return resultList;
		}
	}

	public GeUserPersonal getUserPersonalForLoginWithLDAP(
			GeUserPersonal geUserPersonal) {
		QueryRule queryRule = QueryRule.getInstance();
		
		if (!StringUtils.isBlank(geUserPersonal.getEmail())) {
			/** 邮箱登陆 */
			queryRule.addEqual("email", geUserPersonal.getEmail().trim());
			//queryRule.addSql("lower(email) = '"+geUserPersonal.getEmail().trim().toLowerCase()+"'");
		}
		if (!StringUtils.isBlank(geUserPersonal.getUserAccount())) {
			/** 注册账户登陆 */
			queryRule.addEqual("userAccount", geUserPersonal.getUserAccount().trim());
		}
		if (!StringUtils.isBlank(geUserPersonal.getPiCardNo())) {
			/** 鹤卡登陆 */
			queryRule.addEqual("piCardNo", geUserPersonal.getPiCardNo().trim());
		}
		if (!StringUtils.isBlank(geUserPersonal.getIdentifyNumber())) {
			/** 身份证号登陆 */
			queryRule.addEqual("identifyNumber", geUserPersonal.getIdentifyNumber().trim());
		}

		GeUserPersonal geUser = findUser(queryRule);
		/** 查询本地 */
		if (geUser != null) {
			if ("0".equals(geUser.getStatus())) {
				//throw UserServiceException.newInstanceCode("021");
				return geUser;
			} else {
				return geUser;
			}
		} else {
			if (LDAPSwitchIsOpen()) {
				/** 判断是否有功能开关 */
				PersonalUserDto personalUser = findUserFromLDAP(geUserPersonal);
				String result = personalUser.getGeResults().getResult().get(0);
				/** 查询结果，1-成功，2-失败(查询信息不存在) */
				if ("1".equals(result)) {
					geUser = sycToLocal(personalUser);
					return geUser;
				}
			}
			return null;
		}

	}

	@Override
	public String activeAccountWithLDAP(GeUserPersonal geUserPersonal,
			String oldActiveCode, String newActiveCode) {
		String checkStatus = "2";
		/** 1-邮箱验真 2-手机验真,默认为2 */
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("userID", geUserPersonal.getUserID());
		if (StringUtils.isBlank(oldActiveCode)) {
			/** 原激活码为空则激活方式为邮箱方式 */
			checkStatus = "1";
			GeUserPersonal geUserPer = findUser(queryRule);
			oldActiveCode = geUserPer.getActiveCode();
			if (StringUtils.isBlank(oldActiveCode)) {// 库中原激活码为空
				return "03";// 原激活码为空，需要重新发送激活码或重新选择手机激活
			}
		}

		if (newActiveCode.equals(oldActiveCode)) {

			/** 修改状态 **/
			GeUserPersonal geUP = new GeUserPersonal();
			geUP.setUserID(geUserPersonal.getUserID());
			geUP.setStatus("1");
			List list = this.updateGeUserPersonalWithLDAP(geUP);

			if (list.contains("1")) {
				/** 修改是否验真 */
				Map map = new HashMap();
				map.put("status", "1");
				map.put("checkStatus", "1");
				/** 1-邮箱验真 2-手机验真 */
				updateUserPart(map, geUserPersonal.getUserID());
				return "01";
				/** 01表示激活成功 */
			} else {
				return "00";
				/** 00表示激活失败 */
			}
		} else {
			return "02";
			/** 02表示激活码输入错误 */
		}
	}

	@Override
	public List resetPasswordWithLDAP(GeUserPersonal geUserPersonal)
			throws CheckedMailException, UnsupportedEncodingException {
		List resultList = new ArrayList();
		/** 存放结果编码 */
		int type = 0;
		/** 标记重置密码的方式 1-（账户+手机） 0-邮箱 */

		/** 校验客户是否存在 1.通过用户名判断重置方式 2.手机重置则只校验本地库 3.邮箱重置先校验本地库，本地库没有则校验LDAP */
		GeUserPersonal geUP = new GeUserPersonal();
		QueryRule queryRule = QueryRule.getInstance();
		if (!StringUtils.isBlank(geUserPersonal.getUserAccount())) {
			type = 1;
			queryRule.addEqual("userAccount", geUserPersonal.getUserAccount().trim());
			queryRule.addEqual("mobilePhone", geUserPersonal.getMobilePhone().trim());
			geUP = findUser(queryRule);
			if (geUP == null) {
				resultList.add("01");
				/** 该客户不存在 */
				return resultList;
			}
		} else {
			queryRule.addEqual("email", geUserPersonal.getEmail().trim());
			geUP = findUser(queryRule);

			if (geUP == null) {
				/** 本地库没有则查询LDAP */
				if (LDAPSwitchIsOpen()) {
					/** 判断是否有LDAP功能开关 */
					PersonalUserDto personalUser = findUserFromLDAP(geUserPersonal);
					if (personalUser.getGeResults().getResult().contains("1")) {
						/** LDAP存在 */
						geUP = sycToLocal(personalUser);
						/** 同步到本地库 */
					} else {
						resultList.add("01");
						/** 该客户不存在 */
						return resultList;
					}
				} else {
					resultList.add("01");
					/** 该客户不存在 */
					return resultList;
				}
			}
		}

		/** 产生新密码（规则待定）并设置到对象 */
		String newPasswordString = GeUserPersonalUtil.getInitPassword();
		/** 产生新密码 */
		List rList = new ArrayList();
		if (LDAPSwitchIsOpen()) {
			/** 判断是否有功能开关 */
			GeUserPersonal gePersonal = new GeUserPersonal();
			gePersonal.setPwd(new Md5().toMD5(newPasswordString));
			gePersonal.setUserID(geUP.getUserID());
			GeResultsDto geResults = updateUserToLDAP(gePersonal)
					.getGeResults();
			rList = geResults.getResult();
		} else {
			geUP.setPwd(new Md5().toMD5(newPasswordString));
			super.update(geUP);
			rList.add("1");
		}

		/** 发送新密码 */
		if (rList.contains("1")) {
			if (type == 1) {
				if (sendSMIsOpen()) {
					// 默认第一个参数传姓名，没有传登录名
					//GeUserPersonal geUserPersonal_new = this.findUserByPK(geUserPersonal.getUserID());
					String name = "";
					
					if(geUP!=null){
						if (geUP.getUserName() != null&& !"".equals(geUP.getUserName()))
							name = geUP.getUserName();
						else
							name = geUP.getUserAccount();
					}

					if (bizCommonService.sendSMFromLife(geUP.getMobilePhone(),
							"11", new String[] { name, newPasswordString })) {
						resultList.add("1");
						/** 重置密码成功 */
					} else {
						resultList.add("02");
						/** 重置密码失败 */
					}
				} else {
					resultList.add("1");
					/** 重置密码成功 */
				}
			} else {
				// ---重置密码发送邮件开始---
				String title = "找回密码通知";// 邮件标题
				Map<String, String> model = new HashMap<String, String>();// 填充vm里的${}，括号里的名和map里的key对应
				mailService.setVelocityFilePath("/mail/mailModel_resetPersonal.vm"); // 设置vm文件路径
				String mail = geUP.getEmail();// "yuanhuijing@sinosoft.com.cn"
												// ;//geUP.getEmail();
				String[] address = new String[] { mail };
				mailService.setSubject(title);
				// 邮件内容有图片时对图片的处理
				String[] picNameStrings = {
						"china_life_logo.jpg", "china_life_pen.jpg",
						"china_life_php.jpg", "china_life_pic1.jpg",
						"china_life_pic4.jpg", "china_life_pic5.jpg" };
				List<InlineFile> list = new ArrayList<InlineFile>();
				String locationString = getProjectLocalPath();// 获取工程路径
				for (int i = 0; i < picNameStrings.length; i++) {
					File image = new File(imageOnlineAbsolutePath
							+ picNameStrings[i]);// 图片文件的本地物理路径
					InlineFile inlineFileOne = new InlineFile();
					inlineFileOne.setInlineFile(image);
					inlineFileOne.setInlineFileName(picNameStrings[i]);// 图片文件名
					list.add(inlineFileOne);
				}
				model.put("newPasswordString", toUtf8(newPasswordString));
				// 发送到 邮箱
				mailService.sendMail(address, null, list, model, title);
				// ---重置密码发送邮件结束---

				resultList.add("1");
				/** 重置密码成功 */
			}
			return resultList;
		} else {
			resultList.add("02");
			/** 02代表该用户存在但重置密码失败 */
			return resultList;
		}
	}

	@Override
	public List updateGeUserPersonalWithLDAP(GeUserPersonal geUserPersonal){
		try {
			List resultList = new ArrayList();
			GeUserPersonal geUser = findUserByPK(geUserPersonal.getUserID());
			if ("3".equals(geUser.getStatus())) {
				throw UserServiceException.newInstanceCode("020");
			}
			
			if (LDAPSwitchIsOpen()) {
				/** 判断是否打开LDAP开关 */
				GeResultsDto geResults = updateUserToLDAP(geUserPersonal).getGeResults();
				if (geResults == null || geResults.getResult() == null) {
					throw UserServiceException.newInstanceCode("012");
				}
				return geResults.getResult();
			} else {
				if ((!StringUtils.isBlank(geUserPersonal.getUserAccount()))&&(!geUserPersonal.getUserAccount().equals(geUser.getUserAccount()))&&(!isUnique("USERACCOUNT", geUserPersonal.getUserAccount()))) {
					/** 3-用户名重复 */
					resultList.add("3");
				}
				if ((!StringUtils.isBlank(geUserPersonal.getEmail()))&&(!geUserPersonal.getEmail().equals(geUser.getEmail()))&&(!isUnique("EMAIL", geUserPersonal.getEmail()))) {
					/** 4-邮箱重复 */
					resultList.add("4");
				}
				if ("01".equals(geUserPersonal.getIdentifyType())&&(!(("01".equals(geUser.getIdentifyType())&&geUser.getIdentifyNumber().equals(geUserPersonal.getIdentifyNumber()))))&&(!isIdentifyUnique("IDENTIFYTYPE","01","IDENTIFYNUMBER",geUserPersonal.getIdentifyNumber()))) {
					
					/** 5-身份证重复 */
					resultList.add("5");
				}
				if ((!StringUtils.isBlank(geUserPersonal.getPiCardNo()))&&(!geUserPersonal.getPiCardNo().equals(geUser.getPiCardNo()))&&(!isUnique("PICARDNO", geUserPersonal.getPiCardNo()))) {
					/** 6-鹤卡号重复 */
					resultList.add("6");
				}
				if (resultList.size() == 0) {
					/** 没打开开关则直接修改本地库 */
					Map map = GeUserPersonalUtil.getGeUserPersonalMap(geUserPersonal);
					updateUserPart(map, geUserPersonal.getUserID());
					resultList.add("1");
				}
				return resultList;
			}
		} catch (UserServiceException e) {
			throw e;
		}
	}

	@Override
	public List updatePasswordWithLDAP(GeUserPersonal geUserPersonal,
			String newPassword) {
		List list = new ArrayList();

		/** 判断输入的原密码是否正确 */
		QueryRule queryRule = QueryRule.getInstance();
		/** 获取QueryRule对象的Instance */
		queryRule.addEqual("userID", geUserPersonal.getUserID());
		queryRule.addEqual("pwd", new Md5().toMD5(geUserPersonal.getPwd()));

		GeUserPersonal userInfoLocal = findUser(queryRule);
		if (userInfoLocal == null) {
			list.add("00");
			/** 00表示用户输入的原密码错误 */
			return list;
		} else {
			if ("3".equals(userInfoLocal.getStatus())) {
				throw UserServiceException.newInstanceCode("020");
			}
			/** 修改密码 */
			geUserPersonal.setPwd(new Md5().toMD5(newPassword));
			/** 加密 */
			if (LDAPSwitchIsOpen()) {
				/** 判断是否有功能开关 */
				GeResultsDto geResults = updateUserToLDAP(geUserPersonal)
						.getGeResults();
				if (geResults == null) {
					throw UserServiceException.newInstanceMsg("@Code:EU05002@Message:LDAP修改返回结果集为null");
				}
				return geResults.getResult();
			} else {
				userInfoLocal.setPwd(new Md5().toMD5(newPassword));
				super.update(userInfoLocal);
				list.add("1");
				return list;
				/** 1-成功 */
			}
		}

	}

	@Override
	public GeUserPersonal findUserByPK(String userID) {
		try {
			return super.findUnique("userID", userID);
		} catch (Exception e) {
			throw UserServiceException.newInstanceCode("006", e);
		}
	}

	@Override
	public GeUserPersonal findUser(QueryRule queryRule) {
		try {
			GeUserPersonal geUserPersonal = super.findUnique(queryRule);
			return geUserPersonal;
		} catch (Exception e) {
			e.printStackTrace();
			throw UserServiceException.newInstanceCode("007", e);
		}
	}

	@Override
	@LocusTrace(setDesc="分页查询前台个人用户")
	public Page findUser(QueryRule queryRule, int pageNo, int pageSize) {
		try {
			return super.find(queryRule, pageNo, pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw UserServiceException.newInstanceCode("008", e);
		}
	}

	public boolean saveUser(GeUserPersonal geUserPersonal) {
		try {
			super.save(geUserPersonal);
			return true;
		} catch (Exception e) {
			throw UserServiceException.newInstanceCode("009", e);
		}
		
	}

	@Override
	public boolean updateUser(GeUserPersonal geUserPersonal,
			String[] unUpdateStrings) {
		if (unUpdateStrings == null)
			unUpdateStrings = new String[] { "" };
		try {
			GeUserPersonal update = super.findUnique("userID",
					geUserPersonal.getUserID());
			BeanUtils.copyProperties(geUserPersonal, update, unUpdateStrings);
			super.update(update);
			return true;
		} catch (BeansException e) {
			throw UserServiceException.newInstanceCode("010", e);
		} catch (Exception e) {
			throw UserServiceException.newInstanceCode("011", e);
		}
	}

	@Override
	public boolean updateUserPart(Map map, String pk)
			throws UserServiceException {
		try {
			GeUserPersonal update = super.findUnique("userID", pk);
			if (update == null)
				return true;
			Set set = map.keySet();
			Iterator iterator = set.iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				Object value = map.get(key);
				GeUserPersonalUtil.setColumnValue(update, key, value);
			}
			super.update(update);
			return true;
		} catch (BeansException e) {
			throw UserServiceException.newInstanceCode("010", e);
		} catch (Exception e) {
			throw UserServiceException.newInstanceCode("011", e);
		}
	}

	
	@Override
	public PersonalUserDto findUserFromLDAP(GeUserPersonal geUserPersonal)
			throws UserServiceException {
		try {
			List list = webService.sendRPCRequestXML(geUserPersonal, "EU05003");
			return (PersonalUserDto) list.get(0);
		} catch (portalModuleException e) {
			String messStr = e.getMsg();
			if (StringUtils.isBlank(messStr)) {
				logger.info("客户号为：" + geUserPersonal.getUserID() + "用户查询LDAP出现异常，异常信息：" + e.getMessage());
				throw UserServiceException.newInstanceMsg("@Code:EU05003@Message:LDAP查询数据出现异常，无异常详细信息",e);
			} else {
				logger.info("客户号为：" + geUserPersonal.getUserID() + "用户查询LDAP出现异常，异常信息：" + messStr);
				throw UserServiceException.newInstanceMsg("@Code:EU05003@Message:LDAP查询数据出现异常，异常信息：" + messStr ,e);
			}
		} catch (Exception e) {
			logger.info("客户号为：" + geUserPersonal.getUserID()+ "的用户查询LDAP出现异常，异常信息：" + e.getMessage());
			throw UserServiceException.newInstanceMsg("@Code:EU05003@Message:LDAP查询数据出现异常", e);
		}
	}

	@Override
	public PersonalUserDto insertUserToLDAP(GeUserPersonal geUserPersonal)
			throws UserServiceException {
		try {
			List list = webService.sendRPCRequestXML(geUserPersonal, "EU05001");
			return (PersonalUserDto) list.get(0);
		} catch (portalModuleException e) {
			String messStr = e.getMsg();
			if (StringUtils.isBlank(messStr)) {
				throw UserServiceException.newInstanceMsg("@Code:EU05001@Message:LDAP插入数据出现异常，无异常详细信息",e);
			} else {
				logger.info("客户号为：" + geUserPersonal.getUserID() + "用户插入LDAP出现异常，异常信息：" + messStr);
				throw UserServiceException.newInstanceMsg("@Code:EU05001@Message:LDAP插入数据出现异常，异常信息：" + messStr ,e);
			}
		} catch (Exception e) {
			logger.info("客户号为：" + geUserPersonal.getUserID()+ "的用户插入LDAP出现异常，异常信息：" + e.getMessage());
			throw UserServiceException.newInstanceMsg("@Code:EU05001@Message:LDAP插入数据出现异常", e);
		}
	}

	@Override
	public PersonalUserDto updateUserToLDAP(GeUserPersonal geUserPersonal)
			throws UserServiceException {
		List list = null;//用于指向调用ldap返回结果
		try {
			list = webService.sendRPCRequestXML(geUserPersonal, "EU05002");
		} catch (portalModuleException e) {
			String messStr = e.getMsg();
			if(StringUtils.isBlank(messStr)){
				throw UserServiceException.newInstanceMsg("@Code:EU05002@Message:LDAP修改数据出现异常，无异常详细信息");
			}else {
				String[] mesArrStrings = messStr.split("_");
				if ("4".equals(mesArrStrings[0])) {
					try {
						Map map = new HashMap();
						map.put("status", "3");
						updateUserPart(map, geUserPersonal.getUserID());
					} catch (UserServiceException ue) {
						throw e;
					} catch (Exception e2) {
						throw UserServiceException.newInstanceCode("011", e2);
					}
				} else {
					logger.info("客户号为：" + geUserPersonal.getUserID() + "的用户修改LDAP出现异常，异常信息：" + messStr);
					throw UserServiceException.newInstanceMsg("@Code:EU05002@Message:修改LDAP出现异常，异常信息：" + messStr, e);
				}
			}
		} catch (Exception e) {
			logger.info("客户号为：" + geUserPersonal.getUserID()+ "的用户修改LDAP出现异常，详细：" + e.getMessage());
			throw UserServiceException.newInstanceMsg("@Code:EU05002@Message:LDAP修改数据出现异常", e);
		}
		
		if (list == null || list.get(0) == null) {//ldap返回结果为null
			throw UserServiceException.newInstanceMsg("@Code:EU05002@Message:修改个人信息时查询不到修改接口信息或LDAP返回的业务结果为空");
		}
		return (PersonalUserDto) list.get(0);
	}

	
	@Override
	public String getUserID() {
		try {
			Calendar c = Calendar.getInstance();
			return c.getTimeInMillis() + "";
		} catch (Exception e) {
			throw UserServiceException.newInstanceCode("013", e);
		}
	}

	@Override
	public String getActiveCode() {
		try {
			Random r = new Random();
			int random = Math.abs(r.nextInt()) % (int) (Math.pow(10, 4));
			int length = (random + "").length();
			String result = random * ((int) Math.pow(10, 4 - length)) + "";
			return result;
		} catch (Exception e) {
			throw UserServiceException.newInstanceCode("014", e);
		}
	}
	public boolean sendUserMessage(GeUserPersonal geUserPersonal,String proposalNo,String production)throws UnsupportedEncodingException, CheckedMailException {
		try {
			// ---用户注册信息发送邮件开始---
			String title = "感谢您购买本公司保险产品";// 邮件标题
			Map<String, String> model = new HashMap<String, String>();// 填充vm里的${}，括号里的名和map里的key对应
			mailService.setVelocityFilePath("/mail/mailModel_acountMessage.vm"); // 设置vm文件路径
			String mail = geUserPersonal.getEmail();
			String[] address = new String[] { mail };
			mailService.setSubject(title);
			// 邮件内容有图片时对图片的处理
			String[] picNameStrings = { "china_life_logo.jpg",
					"china_life_pen.jpg", "china_life_php.jpg",
					"china_life_pic1.jpg", "china_life_pic4.jpg",
					"china_life_pic5.jpg" };
			List<InlineFile> list = new ArrayList<InlineFile>();
			String locationString = getProjectLocalPath();// 获取工程路径
			for (int i = 0; i < picNameStrings.length; i++) {
				File image = new File(imageOnlineAbsolutePath
						+ picNameStrings[i]);// 图片文件的本地物理路径
				InlineFile inlineFileOne = new InlineFile();
				inlineFileOne.setInlineFile(image);
				inlineFileOne.setInlineFileName(picNameStrings[i]);// 图片文件名
				list.add(inlineFileOne);
			}
			model.put("userAccount", toUtf8(geUserPersonal.getUserAccount()));
			model.put("passWord", toUtf8(geUserPersonal.getPwd()));
			model.put("proposalNo", proposalNo);
			model.put("production", toUtf8(production));
			String userName = geUserPersonal.getUserName();
			String sex = geUserPersonal.getSex();
			if("1".equals(sex)){
				userName = userName + "先生";
				model.put("userName", toUtf8(userName));
			}else if("2".equals(sex)){
				userName = userName + "女士";
				model.put("userName", toUtf8(userName));
			}
			
			// 发送到 邮箱
			mailService.sendMail(address, null, list, model, title);
			// ---激活码发送邮件结束---
			return true;
		}  catch (BasicBizInfoCommonException e) {
			throw UserServiceException.newInstanceCode("022", e);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw UserServiceException.newInstanceCode("019",e);
		}
		
	}
	@Override
	public boolean sendActiveCode(GeUserPersonal geUserPersonal,
			String sendType, String activeCode) throws UnsupportedEncodingException, CheckedMailException {

			try {
				if ("1".equals(sendType)) {
					/** 调用第三方接口发送手机短信 */
					if (sendSMIsOpen()) {
						// 默认第一个参数传姓名，没有传登录名
						GeUserPersonal geUserPersonal_new = this.findUserByPK(geUserPersonal.getUserID());

						String name = "";
						if(geUserPersonal_new!=null){
							if (geUserPersonal_new.getUserName() != null&& !"".equals(geUserPersonal_new.getUserName()))
								name = geUserPersonal_new.getUserName();
							else
								name = geUserPersonal_new.getUserAccount();
						}

						return bizCommonService.sendSMFromLife(
								geUserPersonal.getMobilePhone(), "1", new String[] {
										name, activeCode });
					} else {
						return true;
					}
				} else {
					// ---激活码发送邮件开始---
					String title = "激活码通知";// 邮件标题
					Map<String, String> model = new HashMap<String, String>();// 填充vm里的${}，括号里的名和map里的key对应
					mailService.setVelocityFilePath("/mail/mailModel_active.vm"); // 设置vm文件路径
					String mail = geUserPersonal.getEmail();
					String[] address = new String[] { mail };
					mailService.setSubject(title);
					// 邮件内容有图片时对图片的处理
					String[] picNameStrings = {
							"china_life_logo.jpg", "china_life_pen.jpg",
							"china_life_php.jpg", "china_life_pic1.jpg",
							"china_life_pic4.jpg", "china_life_pic5.jpg" };
					List<InlineFile> list = new ArrayList<InlineFile>();
					String locationString = getProjectLocalPath();// 获取工程路径
					for (int i = 0; i < picNameStrings.length; i++) {
						File image = new File(imageOnlineAbsolutePath
								+ picNameStrings[i]);// 图片文件的本地物理路径
						InlineFile inlineFileOne = new InlineFile();
						inlineFileOne.setInlineFile(image);
						inlineFileOne.setInlineFileName(picNameStrings[i]);// 图片文件名
						list.add(inlineFileOne);
					}
					model.put("activeCode", toUtf8(activeCode));
					// 发送到 邮箱
					mailService.sendMail(address, null, list, model, title);
					// ---激活码发送邮件结束---
					return true;
				}
			} catch (BasicBizInfoCommonException e) {
				throw UserServiceException.newInstanceCode("022", e);
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw UserServiceException.newInstanceCode("019",e);
			}
		
	}

	/**
	 * 将LDAP数据保存到本地库
	 * 
	 * @param personalUser
	 * @return
	 * @throws Exception
	 */
	private GeUserPersonal sycToLocal(PersonalUserDto personalUser) {
		try {
			GeUserPersonal geUserPersonal = new GeUserPersonal();

			BeanUtils.copyProperties(personalUser, geUserPersonal);
			if (geUserPersonal.getMakeDate() == null) {
				/** personalUser--转换->geUser（待定） */
				geUserPersonal.setMakeDate(new Date());
			}
			if (geUserPersonal.getIntegral() == null) {
				geUserPersonal.setIntegral(new BigDecimal(0));
			}
			if (StringUtils.isBlank(geUserPersonal.getUserLevel())) {
				geUserPersonal.setUserLevel("1");
			}
			saveUser(geUserPersonal);
			return geUserPersonal;
		} catch (BeansException e) {
			throw UserServiceException.newInstanceCode("010", e);
		} catch (Exception e) {
			// TODO: handle exception
			throw UserServiceException.newInstanceCode("015", e);
		}
	}

	/**
	 * 判断LDAP功能开关是否开通
	 * 
	 * @return
	 */
	private boolean LDAPSwitchIsOpen() {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("functiontId", "LDAP");
		GeFunctionSwitch geFunctionSwitch;
		try {
			geFunctionSwitch = geFunctionSwitchService
					.findGeFunctionSwitch(queryRule);
		} catch (BizConfigCommonException e) {
			throw UserServiceException.newInstanceCode("016", e);
		}
		if (geFunctionSwitch == null)
			return false;
		if ("1".equals(geFunctionSwitch.getStatus())) {
			/** 属性开关状态(0 无效 1 有效 2未开通) */
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断用户激活功能开关是否开通
	 * 
	 * @return
	 */
	public boolean activeSwitchIsOpen() {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("functiontId", "sendActiveCode");
		GeFunctionSwitch geFunctionSwitch;
		try {
			geFunctionSwitch = geFunctionSwitchService
					.findGeFunctionSwitch(queryRule);
		} catch (BizConfigCommonException e) {
			throw UserServiceException.newInstanceCode("017", e);
		}
		if (geFunctionSwitch == null)
			return false;
		if ("1".equals(geFunctionSwitch.getStatus())) {
			/** 属性开关状态(0 无效 1 有效 2未开通) */
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 查询唯一性
	 * 
	 * @param columnName
	 *            列名
	 * @param value
	 *            值
	 * @return
	 */
	private boolean isUnique(String columnName, String value) {
		try {
			Session session = super.getSession();
			String sql = "select count(1) sum from GE_USER_PERSONAL  where "
					+ columnName + " = ?";
			List list = session.createSQLQuery(sql)
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
					.setString(0, value).list();
			session.flush();
			Map map = (Map) list.get(0);
			BigDecimal sum = (BigDecimal) map.get("SUM");
			if (sum.intValue() > 0) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			throw UserServiceException.newInstanceCode("018", e);
		}
	}
	
	/**
	 * 查询唯一性
	 * 
	 * @param columnName1
	 *            列名1
	 * @param value1
	 *            值1
	 * @param columnName2
	 *            列名2
	 * @param value1
	 *            值2
	 * @return
	 */
	private boolean isIdentifyUnique(String columnName1, String value1, String columnName2, String value2) {
		try {
			Session session = super.getSession();
			String sql = "select count(1) sum from GE_USER_PERSONAL  where "
					+ columnName1 + " = ? and " + columnName2 + " = ?" ;
			List list = session.createSQLQuery(sql)
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
					.setString(0, value1).setString(1, value2).list();
			session.flush();
			Map map = (Map) list.get(0);
			BigDecimal sum = (BigDecimal) map.get("SUM");
			if (sum.intValue() > 0) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			throw UserServiceException.newInstanceCode("018", e);
		}
	}

	@Override
	public boolean valPwd(String uid, String pwd) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("userID", uid);
		queryRule.addEqual("pwd", new Md5().toMD5(pwd));
		GeUserPersonal userInfoLocal = findUser(queryRule);
		if (userInfoLocal == null)
			return false;
		return true;
	}

	/**
	 * 获取项目物理路径
	 * 
	 * @return 项目路径
	 * @throws Exception
	 *             未找到路径
	 */
	public static String getProjectLocalPath() {
		try {
			String path = GeUserPersonalServiceSpringImpl.class.getResource("")
					.getFile();// 此处 ComplaintAction.class为获取当前类，及方法所在action
			path = URLDecoder.decode(path, "UTF-8");
			if (path.lastIndexOf("/WEB-INF") > -1) {
				path = path.substring(0, path.lastIndexOf("/WEB-INF"));
				if (path.lastIndexOf("/") > -1) {
					path = path.substring(0, path.lastIndexOf("/"));
				}
				if (path.lastIndexOf("/") > -1) {
					path = path.substring(0, path.lastIndexOf("/"));
				}
				String temp = "";
				if (StringUtils.isNotBlank(path) && path.length() >= 5)
					temp = path.substring(0, 5);
				if ("file:".equalsIgnoreCase(temp)) {
					if (path.lastIndexOf("/") > -1) {
						path = path.substring(5);
					}
				}
			}
			return path;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 中文转码方法
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String toUtf8(String code) throws UnsupportedEncodingException {
		return new String(new String(code).getBytes("UTF-8"), "ISO-8859-1"); // 内容主体有中文则需要转码
	}

	public boolean sendSMIsOpen() {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("functiontId", "sendSMFromLife");
		GeFunctionSwitch geFunctionSwitch;
		try {
			geFunctionSwitch = geFunctionSwitchService
					.findGeFunctionSwitch(queryRule);
		} catch (BizConfigCommonException e) {
			throw UserServiceException.newInstanceCode("023", e);
		}
		if (geFunctionSwitch == null)
			return false;
		if ("1".equals(geFunctionSwitch.getStatus())) {
			/** 属性开关状态(0 无效 1 有效 2未开通) */
			return true;
		} else {
			return false;
		}
	}
	//生成六位数随机密码
	public String getRandomPwd(){
		final int maxNum = 36;
		int i;//生成的随机数
		int count = 0;//生成密码的长度
		char[] str = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','p','q','r','s','t','u','v','w','x','y','z','1','2','3','4','5','6','7','8','9'};
		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while(count<6){
			i = Math.abs(r.nextInt(maxNum));
			if(i>=0&&i<str.length){
				pwd.append(str[i]);
				count ++;
			}
		}
		return pwd.toString();
	}
	public static void main(String[] args) {
		GeUserPersonalServiceSpringImpl geUser = new GeUserPersonalServiceSpringImpl();
		String pwd = geUser.getRandomPwd();
		System.out.println(pwd);
	}
	@Override
	public GeUserPersonal registerFromWap(GeUserPersonal geUserPersonal)
			throws UserServiceException {
		List resultList = new ArrayList();

//		String pwd = "111111";//密码随机生成，发送到邮箱
		String pwd = getRandomPwd();
		/*** 默认值 **/
		geUserPersonal.setUserID(getUserID());
		/** 设置客户号 */
		geUserPersonal.setPwd(new Md5().toMD5(pwd));
		/** 密码MD5加密 */
		geUserPersonal.setIntegral(new BigDecimal(0));
		/** 设置积分 */
		geUserPersonal.setCheckStatus("0");
		/** 设置验真 */
		geUserPersonal.setUserSource("1");
		/** 设置用户来源 */
		geUserPersonal.setUserLevel("1");
		/** 设置用户等级 */
		geUserPersonal.setUserRank("T");
		/** 设置寿险用户等级 */
		geUserPersonal.setMakeDate(new Date());
		/** 创建时间 */
		geUserPersonal.setRaInd("0");
		/** 设置用户RA认证状态，0-未认证，1-已认证 */
		
		/** 登录次数 */
		geUserPersonal.setLoginNum(0);
		/** 设置用户的处理状态为1，已激活 */
		geUserPersonal.setStatus("1");
		
		/** 设置用户的用户名 */
		geUserPersonal.setUserAccount("M" + geUserPersonal.getUserID());
		/** 设置用户的婚姻状态 */
		geUserPersonal.setMarriageStatus("9");
		
		if (LDAPSwitchIsOpen()) {
			/** 判断功能开关 */
			GeResultsDto geResults = insertUserToLDAP(geUserPersonal).getGeResults();
			List list = geResults.getResult();
			if (list != null && list.contains("1")) {
				/** 判断交易是否成功 */
				saveUser(geUserPersonal);
			} else if (list.contains("2")) { // 客户号重复
				registerFromWap(geUserPersonal);
			}
			resultList.addAll(list);
		} else {
			if (!isUnique("USERID", geUserPersonal.getUserID())) {
				/** 用户号重复 */
				registerWithLDAP(geUserPersonal);
			}
			if (!isUnique("USERACCOUNT", geUserPersonal.getUserAccount())) {
				/** 3-用户名重复 */
				resultList.add("3");
			}
			if (!isUnique("EMAIL", geUserPersonal.getEmail())) {
				/** 4-邮箱重复 */
				resultList.add("4");
			}
			if ("01".equals(geUserPersonal.getIdentifyType())&&(!isIdentifyUnique("IDENTIFYTYPE","01","IDENTIFYNUMBER",geUserPersonal.getIdentifyNumber()))) {
				/** 5-身份证重复 */
				resultList.add("5");
			}
			/**校验唯一性成功 */
			if (resultList.size() == 0) {
				if (saveUser(geUserPersonal)) {
					resultList.add("1");
				} else {
					resultList.add("0");
					/** 0-保存本地库失败 */
				}
			}
		}
		
		GeUserPersonal geUser = new GeUserPersonal();
		BeanUtils.copyProperties(geUserPersonal, geUser, new String[]{"results"});
		geUser.setPwd(pwd);
		geUser.setResults(resultList);
		return geUser;
	}

	@Override
	public List getInfoFromCode(List<String> list) {
		if (list != null) {
			List<String> resultList = new ArrayList<String>();
			if (list.contains("1")) {
				resultList.add("1");
			} 
			if (list.contains("3")) {
				resultList.add("用户名重复");
			}
			if (list.contains("4")) {
				resultList.add("邮箱重复");
			}
			if (list.contains("5")) {
				resultList.add("身份证重复");
			}
			if (list.contains("6")) {
				resultList.add("鹤卡号重复");
			}
			if (list.contains("7")) {
				resultList.add("更新数据库失败");
			}
			return resultList;
		} else return null;
	}
	
	public List<GeUserPersonal> getUserListByName(String userAccount)
			throws UserServiceException {
		List<GeUserPersonal> userList = null;
		try{
			String sql = "select * from ge_user_personal t  where lower(t.useraccount)=lower('"+userAccount+"')";
			SQLQuery query = super.getSession().createSQLQuery(sql);
			query.addEntity(GeUserPersonal.class);
			userList = query.list();
		}catch(UserServiceException e){
			throw e;
		}
		return userList;
	}
	

	/**
	 * 用户注册成功
	 */
	@Override
	public void saveUserPersonal(GeUserPersonal userPersonal, HttpServletRequest request) throws Exception{
		try {
			String userAccount = userPersonal.getUserAccount();
			
			if (userAccount.indexOf("@") > 0) {
				userPersonal.setEmail(userAccount);
			} else {
				userPersonal.setMobilePhone(userAccount);
				userPersonal.setActive(true);
			}
			userPersonal.setPwd(new Md5().toMD5(userPersonal.getPwd()));
			super.save(userPersonal);
		} catch (Exception e) {
			log.error("user register failed,save user error:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 用户注册成功，向用户注册的邮箱地址发送邮件，激活帐号
	 */
	@Override
	public void activationAccount(GeUserPersonal geUserPersonal) {
		try {
			String avtiveDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(DateUtils.addDays(new Date(), 1));
			String activateUrl = getSinatayUrl() + "/register/userPersonalAcivate.do?id=" + geUserPersonal.getUserID() + "&date=" + Base64.encodeBase64String(avtiveDate.getBytes());
			
			Map<String,String> map = new HashMap<String, String>();
			map.put("email", geUserPersonal.getEmail());
			map.put("activateUrl", activateUrl);
			sendEmailService.sendActivateRegistEmail("",map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取当前路径
	 * @return
	 */
	private String getSinatayUrl() {
		PropertyFileUtils.init(FilePathUtil.getClassBuildPath() + "config/sinatay_config.properties");
		
		Properties properties = PropertyFileUtils.getProperties();
		
		return properties.getProperty("sinatayUrl");
	}

	/**
	 * 校验客户注册时输入的邮箱是否已存在。
	 * true:不存在
	 * false:已存在
	 */
	@Override
	public boolean checkUserPersonalName(String email) {
		try {
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("email", email);

			List<GeUserPersonal> userPersonals = super.find(queryRule);

			return userPersonals.isEmpty() ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 根据用户名密码校验是否为系统用户
	 */
	@Override
	public GeUserPersonal validateUserPersonal(GeUserPersonal userPersonal) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("GeUserPersonalName", userPersonal.getUserAccount())
				.addEqual("password", userPersonal.getPwd());

		List<GeUserPersonal> GeUserPersonals = super.find(queryRule);

		if (GeUserPersonals.isEmpty()) {
			return null;
		}
		return GeUserPersonals.get(0);
	}
	
	/**
	 * DWR 校验身份证号是否已存在
	 * @return
	 */
	public boolean userPersonalNumberValidate(String number) {
		String hql = "from " + GeUserPersonal.class.getName() + " where identfyNumber = ?";
		
		@SuppressWarnings("unchecked")
		List<GeUserPersonal> list = this.findByHql(hql, number);
		
		return list.isEmpty() ? true : false;
	}

	/**
	 * 生成支付宝快捷登录需要的参数
	 * 
	 * @return
	 */
	public static Map<String, String> generateAlipayLoginParamter() {
		// 把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();

		try {
			String target_service = "user.auth.quick.login";
			
			PropertyFileUtils.init(FilePathUtil.getClassBuildPath() + "config/sinatay_config.properties");
			Properties properties = PropertyFileUtils.getProperties();
			String sinatayUrl = properties.getProperty("sinatayUrl");
			// 必填
			// 必填，页面跳转同步通知页面路径
			String return_url = sinatayUrl + "/login/alipayLoginSuccess.do";

			// 防钓鱼时间戳
			String anti_phishing_key = AlipaySubmit.query_timestamp();
			// 若要使用请调用类文件submit中的query_timestamp函数

			// 客户端的IP地址
			// String exter_invoke_ip =
			// InetAddress.getLocalHost().getHostAddress();
			String exter_invoke_ip = InetAddress.getLocalHost().getHostAddress();
			// 非局域网的外网IP地址，如：221.0.0.1

			sParaTemp.put("service", "alipay.auth.authorize");
			sParaTemp.put("partner", AlipayConfig.partner);
			sParaTemp.put("_input_charset",AlipayConfig.input_charset);
			sParaTemp.put("target_service", target_service);
			sParaTemp.put("return_url", return_url);
			sParaTemp.put("anti_phishing_key", anti_phishing_key);
			sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sParaTemp;
	}
	
	@Override
	public boolean userPersonalMobileValidate (String mobile) {
		String hql = "from " + GeUserPersonal.class.getName() + " where mobilePhone = ?";
		
		@SuppressWarnings("unchecked")
		List<GeUserPersonal> list = this.findByHql(hql, mobile);
		System.out.println("list.size(): "+list.size());
		return list.isEmpty() ? true : false;
	}

	@Override
	public GeUserPersonal sinaLogin(HttpServletRequest request ) {
		GeUserPersonal geUserPersonal = null;
		try {
			cn.com.sinosoft.ebusiness.weibo4j.Oauth sinaOauth = new cn.com.sinosoft.ebusiness.weibo4j.Oauth();
			AccessToken sinaAccessToken = sinaOauth.getAccessTokenByCode(request.getParameter("code"));
			String access_token = sinaAccessToken.getAccessToken();
			String uid = sinaAccessToken.getUid();
			Users sina = new Users();
			sina.client.setToken(access_token);

			cn.com.sinosoft.ebusiness.weibo4j.model.User user = sina.showUserById(uid);
			
			geUserPersonal = validateUniqueId(user.getId());
			if (geUserPersonal == null) {
				geUserPersonal = new GeUserPersonal();
				//唯一ID
				geUserPersonal.setUniqueId(user.getId());
				//微博名称
				geUserPersonal.setAlias(user.getScreenName());
				//性别
				geUserPersonal.setSex(user.getGender().equals("m") ? "1" : (user.getGender().equals("f") ? "2" : "0"));
				geUserPersonal.setActive(true);
				
				saveUser(geUserPersonal);
			} else {
				geUserPersonal.setAlias(user.getScreenName());
				geUserPersonal.setSex(user.getGender().equals("m") ? "1" : (user.getGender().equals("f") ? "2" : "0"));
				
				updateGeUserPersonalWithLDAP(geUserPersonal);
			}
			//处理Spring security
			getAuthorities(geUserPersonal, user.getScreenName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return geUserPersonal;
	}


	/**
	 * 重置密码时验证邮箱或手机号是否合法
	 * 
	 * @return
	 */
	@Override
	public GeUserPersonal checkMailOrPhone(String resetPassword) {
		String hql = "from " + GeUserPersonal.class.getName() + " where mobile = ? or email = ?";

		@SuppressWarnings("unchecked")
		List<GeUserPersonal> userPersonals = findByHql(hql, resetPassword, resetPassword);

		return userPersonals.isEmpty() ? null : userPersonals.get(0);
	}

	@Override
	public GeUserPersonal resetPassword(String param) {
		String uuid = UUID.randomUUID().toString();
		String password = uuid.substring(0, uuid.indexOf("-"));

		// 获取用户信息
		GeUserPersonal GeUserPersonal = checkMailOrPhone(param);
		GeUserPersonal.setPwd(password);

		if (param.indexOf("@") > 0) {// 通过邮箱取回密码
			resetPasswordByMail(GeUserPersonal);
		} else {// 通过手机号码取回密码
			resetpasswordByPhone(GeUserPersonal);
		}

		this.update(GeUserPersonal);

		return GeUserPersonal;
	}

	private void resetpasswordByPhone(GeUserPersonal GeUserPersonal) {

	}

	/**
	 * 通过邮箱取回密码
	 * 
	 * @param GeUserPersonal
	 */
	private void resetPasswordByMail(GeUserPersonal geUserPersonal) {
		try {
			if(geUserPersonal != null && StringUtils.isNotBlank(geUserPersonal.getEmail())){
				Map<String,String> map = new HashMap<String, String>();
				map.put("email", geUserPersonal.getEmail());
				map.put("pwd", geUserPersonal.getPwd());
				sendEmailService.sendResetPasswordEmail("信泰保险提醒：您正在进行取回密码操作，请您完成验证",map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 通过手机号码取回密码
	 * 
	 * @param GeUserPersonal
	 * @return
	 */
	private String generateMailContent(GeUserPersonal GeUserPersonal) {
		StringBuffer content = new StringBuffer();

		content.append("尊敬的客户 ，您在信泰人寿注册的帐号密码已被重置为 ");
		content.append(GeUserPersonal.getPwd());
		content.append("。您可以使用手机号或邮箱进行登录。");

		return content.toString();
	}

	/**
	 * 支付宝快捷登录成功之后，获取登录用户信息
	 */
	@Override
	public GeUserPersonal alipayLoginInfo(HttpServletRequest request) {
		GeUserPersonal userPersonal = null;
		try {
			String user_id = request.getParameter("user_id");
			String real_name = new String(request.getParameter("real_name"));
			
			String token = request.getParameter("token");
			String sign = request.getParameter("sign");
			String is_success = request.getParameter("is_success");
			String sign_type = request.getParameter("sign_type");
			String notify_id = request.getParameter("notify_id");
			
			userPersonal = validateUniqueId(user_id);
			
			//支付宝登录后获取的token，支付时使用
			request.getSession().setAttribute("token", token);
			
			if (userPersonal == null) {
				userPersonal = new GeUserPersonal();
				//支付宝登录唯一ID
				userPersonal.setUniqueId(user_id);
				//昵称（支付宝姓名）
				userPersonal.setAlias(real_name);
				userPersonal.setActive(true);
				
				saveUser(userPersonal);
			} else {
				//昵称（支付宝姓名）
				userPersonal.setAlias(real_name);
				updateUserPersonal(userPersonal);
			}
			//处理Spring security
			getAuthorities(userPersonal, real_name);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return userPersonal;
	}
	
	/**
	 * 处理Spring security
	 * @param userPersonal
	 * @param real_name
	 */
	public void getAuthorities(GeUserPersonal userPersonal, String real_name) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new GrantedAuthorityImpl("ROLE_PERSONALUSER"));
		LoginUserInfo loginUserInfo =  new LoginUserInfo(real_name, "123456", userPersonal, true, 
				 true, true, true, authorities);
		PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(loginUserInfo, loginUserInfo.getPassword(),loginUserInfo.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		System.out.println(SpringSecurityUtils.getCurrentUser());
		
		
//		PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(userDetails,
//				userDetails.getPassword(), userDetails.getAuthorities());
//
//		if (request != null) {
//			authentication.setDetails(new WebAuthenticationDetails(request));
//		}
//
//		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	/**
	 * 验证当前手机号是否已注册
	 */
	@Override
	public boolean checkUserPersonalMobile(String mobile) {
		try {
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("mobile", mobile);

			List<GeUserPersonal> userPersonal = super.find(queryRule);

			return userPersonal.isEmpty() ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 根据userId获取User对象
	 */
	@Override
	public GeUserPersonal getUserPersonalByUserId(String userId) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("userID", userId);

		List<GeUserPersonal> list = super.find(queryRule);
		
		return list.isEmpty() ? null : list.get(0);
	}
	
	/**
	 * 手机动态码验证
	 * @param mobile
	 * @param input
	 * @return
	 */
	public String validateGeUserPersonalInfo(String mobile, String input){
		String num = getPhoneDynamicNumber(mobile);
		
		if (input.equals(num))
			return "success";
			
		return "failed";
	}

	/**
	 * 获取手机动态验证码
	 * @param mobile
	 * @return
	 */
	public String getPhoneDynamicNumber(String mobile) {
		
		return "1122";
	}

	@Override
	public GeUserPersonal updateUserPersonal(GeUserPersonal userPersonal) {
		GeUserPersonal update = super.get(userPersonal.getUserID());
		List<String> ignorePropertyList = new ArrayList<String>();
		if(update.isActive())
			ignorePropertyList.add("active");
		String[] ignoreProperties = new String[ignorePropertyList.size()];
		cn.com.sinosoft.util.spring.BeanUtils.copyProperties(userPersonal, update, ignorePropertyList.toArray(ignoreProperties));
		super.update(update);
		return update;
	}

	/**
	 * 根据用户名判断是否存在该用户
	 */
	@Override
	public GeUserPersonal getUserPersonalByName(String username) {
		String hql = "from " + GeUserPersonal.class.getName() + " where (userAccount = ? or email = ? or mobilePhone = ?)";

		List<GeUserPersonal> list = new ArrayList<GeUserPersonal>();
		try {
			list = super.findByHql(hql, username, username, username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public GeUserPersonal qqLogin(HttpServletRequest request) {
		GeUserPersonal userPersonal = null;
		log.error("start process qqlogin.");
		try {
			log.error("qq_connect_state"+request.getSession().getAttribute("qq_connect_state"));
			com.qq.connect.javabeans.AccessToken accessTokenObj = new Oauth().getAccessTokenByRequest(request);
			
			String accessToken = null, openID = null;

			if (accessTokenObj.getAccessToken().equals("")) {
				log.error("没有获取到响应参数");
			} else {
				accessToken = accessTokenObj.getAccessToken();
				long tokenExpireIn = accessTokenObj.getExpireIn();

				// 利用获取到的accessToken 去获取当前用的openid -------- start
				OpenID openIDObj = new OpenID(accessToken);
				openID = openIDObj.getUserOpenID();
				
				UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
				UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
				
				log.error("userInfoBean:" + userInfoBean.getNickname() + ","
						+ userInfoBean.getGender() + userInfoBean.getMsg() + ","
						+ userInfoBean.getRet() + ","
						+ userInfoBean.getAvatar().getAvatarURL100());
				
				userPersonal = validateUniqueId(openID);
				//首次使用QQ快捷登录
				if (userPersonal == null) {
					userPersonal = new GeUserPersonal();
					//QQ登录保存唯一ID
					userPersonal.setUniqueId(openID);
					//昵称
					userPersonal.setAlias(userInfoBean.getNickname());
					//1》男；2》女
					userPersonal.setSex("男".equals(userInfoBean.getGender()) ? "1" : "2");
					userPersonal.setActive(true);
					
					saveUser(userPersonal);
				} else {
					userPersonal.setAlias(userInfoBean.getNickname());
					userPersonal.setSex("男".equals(userInfoBean.getGender()) ? "1" : "2");
					
					updateUserPersonal(userPersonal);
				}

				//处理Spring security
				getAuthorities(userPersonal, userInfoBean.getNickname());
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("exception message:"+e.getCause().getMessage());
		}
		return userPersonal;
	}

	/**
	 * 针对快捷登录,查询该帐号是否登录过
	 */
	@Override
	public GeUserPersonal validateUniqueId(String id) {
		String hql = "from " + GeUserPersonal.class.getName() + " where uniqueId = ?";
		
		List<GeUserPersonal> list = findByHql(hql, id);
		
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public GeUserPersonal getUserPersonalByEmailOrPhone(String emailOrPhone) {
		String sql = "from " + GeUserPersonal.class.getName()
				+ " where email = ? or mobilePhone = ?";
		List<GeUserPersonal> list = findByHql(sql, emailOrPhone, emailOrPhone);
		
		return list.isEmpty() ? null : list.get(0);
	}

	/**
	 * 通过向邮箱发送验证码的形式取回密码
	 */
	@Override
	public String sendValidateCodeByEmail(String email) {
		String validateCode = null;
		try {
			validateCode = new Random().nextInt(999999) + "";
			validateCode = org.apache.commons.lang.StringUtils.leftPad(validateCode, 4, '0');
			if(StringUtils.isNotBlank(email)){
				Map<String,String> map = new HashMap<String, String>();
				map.put("email", email);
				map.put("validateCode", validateCode);
				sendEmailService.sendResetPasswordEmail("信泰保险提醒：您正在进行取回密码操作，请您完成验证",map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			return validateCode;
		}
		
	}

	@Override
	public String getIdTypes() {
		List<GeCode> identifyTypeList = geCodeService.findAllByGeCodeType("IdentifyType");
		
		StringBuffer result = new StringBuffer("[");
		for (GeCode code : identifyTypeList) {
			result.append("{name:'" + code.getCodeCName() + "',value:'" + code.getId().getCodeCode() + "'},");
		}
		if (!identifyTypeList.isEmpty()) {
			result = new StringBuffer(result.toString().substring(0, result.toString().length() - 1));
		}
		result.append("];");
		
		return result.toString();
	}

	public void setGeCodeService(GeCodeService geCodeService) {
		this.geCodeService = geCodeService;
	}

	@Override
	public String getRelatedToApplicants() {
		List<GeCode> insRelaToAppList = geCodeService.findAllByGeCodeType("applicantAndInsured");
		
		StringBuffer result = new StringBuffer("[");
		result.append("{name:'请选择',value:'-1'}");
		for (GeCode code : insRelaToAppList) {
			if(!code.getId().getCodeCode().equals("0"))
				result.append(",{name:'" + code.getCodeCName() + "',value:'" + code.getId().getCodeCode() + "'}");
		}
		result.append("];");
		
		return result.toString();
	}

	@Override
	public GeUserPersonal getUserPersonalByUserAccount(String userAccount) {
		String sql = "from " + GeUserPersonal.class.getName() + " where userAccount = ? ";
		
		List<GeUserPersonal> list = findByHql(sql, userAccount);
		
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public GeUserPersonal checkLogin(String userName, String password) {
		String hql = "from " + GeUserPersonal.class.getName() + " where (userAccount = ? or email = ? or mobilePhone = ? or identifyNumber = ?) and pwd = ?";
		String md5Password = "";
		if(StringUtils.isNotBlank(password)) md5Password = new Md5().toMD5(password);
		List list = this.findByHql(hql, userName, userName, userName, userName, md5Password);
		
		return list.isEmpty() ? null : (GeUserPersonal)list.get(0);
	}


	/**
	 * true:表示用户有修改过数据，则查询结果为空
	 * false：表示没有修改过数据，则可以查询到之前的数据
	 */
	@Override
	public boolean checkChanged(GeUserPersonal user) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("userID", user.getUserID());
		queryRule.addEqual("userName", user.getUserName());
		queryRule.addEqual("identifyType", user.getIdentifyType());
		queryRule.addEqual("identifyNumber", user.getIdentifyNumber());
		queryRule.addEqual("sex", user.getSex());
		queryRule.addEqual("birthday", user.getBirthday());
		
		List<GeUserPersonal> list = this.find(queryRule);
		
		if (!list.isEmpty())
			evict(list.get(0));
		
		return list.isEmpty() ? true : false;
	}

	@Override
	public GeUserPersonal findUsers(QueryRule queryRule)
			throws UserServiceException {
		List<GeUserPersonal> users = super.find(queryRule);
		GeUserPersonal geUserPersonal = null;
		if(users!=null && !users.isEmpty()){
			geUserPersonal = users.get(0);
		}
		return geUserPersonal;
	}

	/**
	 * 是否绑定过保单
	 */
	@Override
	public boolean checkBindPolicy(String userID) {
		String hql = "from " + BindPolicy.class.getName() + " where customerId = ?";
		
		List list = this.findByHql(hql, userID);
		
		return list.isEmpty() ? false : true;
	}

	@Override
	public String checkPasswordGrade(String pwd) {
		String result = "";
		String regEx = "^[0-9]*$";
		boolean rs = Pattern.compile(regEx).matcher(pwd).find();
		if (rs && pwd.length() <= 8) {
			result = "0";//低
		} else if (rs && pwd.length() > 8) {
			result = "1";//中
		} else if (!rs && pwd.length() <= 8) {
			result = "1";//高
		} else {
			result = "2";
		}
		
		return result;
	}

	@Override
	public String sendCodeByEmail(String email) {

		try {
			String validateCode = new Random().nextInt(999999) + "";
			validateCode = org.apache.commons.lang.StringUtils.leftPad(validateCode, 6, '0');
			Map<String,String> map = new HashMap<String, String>();
			map.put("email", email);
			map.put("validateCode", validateCode);
			sendEmailService.sendResetPasswordEmail("信泰保险提醒：您正在进行绑定邮箱操作，请您完成验证",map);
			return validateCode;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public void setBindPolicyService(BindPolicyService bindPolicyService) {
		this.bindPolicyService = bindPolicyService;
	}

	/**
	 * 是否购买过保单
	 */
	@Override
	public boolean findPolicyByOrder(String userID) {
		String hql = "from " + OrderForm.class.getName() + " where personalUserSerialNo = '" + userID + "' and orderStatus in (62, 71, 72, 81, 84)";
		List list = findByHql(hql);
		
		return list.isEmpty() ? false : true;
	}

	public int countUserPersonalByMobile(String mobile) {
		StringBuffer sql = new StringBuffer("select ");
		sql.append("count(userID)");
		sql.append(" from ");
//		sql.append(schema);
		sql.append("GE_USER_PERSONAL where mobilePhone =");
		sql.append(" '"+mobile+"'");
		return jdbcTemplate.queryForInt(sql.toString());
	}
	
	public int existCustomerByUserAccount(String value) {
		StringBuffer sql = new StringBuffer("select ");
		sql.append("count(userID)");
		sql.append(" from ");
//		sql.append(schema);
		sql.append("GE_USER_PERSONAL where USERACCOUNT =");
		sql.append(" '"+value+"'");
		return jdbcTemplate.queryForInt(sql.toString());
	}
	
	public int checkIsCustomer(String date){
		StringBuffer sql = new StringBuffer("select ");
		sql.append("count(userID)");
		sql.append(" from ");
//		sql.append(schema);
		sql.append("GE_USER_PERSONAL where USERACCOUNT =");
		sql.append(" '"+date+"' ");
		sql.append(" or email =");
		sql.append(" '"+date+"' ");
		sql.append(" or mobilePhone =");
		sql.append(" '"+date+"' ");
		return jdbcTemplate.queryForInt(sql.toString());
	}

	/***
	 * 根据身份证号查询用户信息
	 */
	@Override
	public List<GeUserPersonal> getUserPersonalByIdNum(String idNum, String pwd) {
		List<GeUserPersonal> list = new ArrayList<GeUserPersonal>();
		try {
			StringBuffer hql = new StringBuffer("from " + GeUserPersonal.class.getName() + " where identifyNumber = ?");
			
			if (org.apache.commons.lang.StringUtils.isNotBlank(pwd)) {
				hql.append(" and pwd = ?");
				list = super.findByHql(hql.toString(), idNum, new Md5().toMD5(pwd));
			} else {
				list = super.findByHql(hql.toString(), idNum);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 解锁帐号
	 * @param userId
	 */
	public void unlockUserAccount(String userId) {
		String sql = "update ge_user_personal set lockUserAccount = 'N' , lockTime = '', loginFailedCount = 0 where userID = '" + userId + "'";
		this.jdbcTemplate.update(sql.toString());
	}

	/**
	 * 修改登录失败错误次数与帐号锁定时间
	 */
	@Override
	public boolean updateLoginFailedCount(GeUserPersonal geUserPersonal) {
		try {
			StringBuffer sql = new StringBuffer("update ge_user_personal set loginFailedCount = "
					+ (geUserPersonal.getLoginFailedCount() + 1));
			
			if (geUserPersonal.getLoginFailedCount() == 2) {
				sql.append(" , lockUserAccount = 'Y' ")
					.append(" , lockTime = to_date('" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "', 'yyyy-MM-dd HH24:mi:ss')");
				final String userId = geUserPersonal.getUserID();
				taskExecutor.execute(new Runnable() {
					@Override
					public void run() {
						 try {
//							Thread.sleep(1000 * 10);//10秒钟之后解锁帐号
							Thread.sleep(1000 * 60 * 60 * 2);//2小时之后解锁帐号
							unlockUserAccount(userId);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				});
			}
			sql.append(" where userID = '" + geUserPersonal.getUserID() + "'");
			
			this.jdbcTemplate.update(sql.toString());
		} catch (DataAccessException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 用户登录成功后，更新部分字段信息 
	 */
	@Override
	public void updateGeUserPersonalBySql(GeUserPersonal customer) {
		StringBuffer str = new StringBuffer("update ge_user_personal");
		Date currentLoginTime = customer.getCurrentLogintime();
		if (currentLoginTime == null) {
			currentLoginTime = new Date();
		}
		str.append(" set lastLoginTime = to_date('" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentLoginTime) + "', 'yyyy-MM-dd HH24:mi:ss')");
		str.append(" , currentLogintime = to_date('" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "', 'yyyy-MM-dd HH24:mi:ss')");
		str.append(" , passwordGrade = ?");
		str.append(" , lockUserAccount = 'N'");
		str.append(" , loginFailedCount = 0");
		str.append(" , lockTime = null where userID = '");
		str.append(customer.getUserID());
		str.append("'");
		
		jdbcTemplate.update(str.toString(), checkPasswordGrade(customer.getPwd()));
	}
	
	/**
	 *  查询某一手机号或邮箱当天发送验证码的次数
	 * @param account
	 * @return
	 */
	public Integer getTransmissionTimesByAccount(String account) {
		String hql = "from " + TransmissionTimes.class.getName() + " where account = ? and transactionDate = ?";
		
		List<TransmissionTimes> list = findByHql(hql, account, new Date());
		
		if (list.isEmpty())
			return 0;
		
		return list.get(0).getTrans_Times();
	}
	
	/**
	 * 每次发送验证码之后，记录发送次数
	 * @param account
	 * @param count
	 */
	public void saveTransmissionTimes(String account, Integer count) {
		String sql = "from " + TransmissionTimes.class.getName() + " where account = ? and transactionDate = ?";
		
		List<TransmissionTimes> list = findByHql(sql, account, new Date());
		TransmissionTimes times = null;
		if (list.isEmpty()) {
			times = new TransmissionTimes();
			times.setAccount(account);
		} else {
			times = list.get(0);
			times.setUpdateTime(new Date());
		}
		
		times.setTrans_Times(count);
		
		super.save(times);
	}
	
	public MobilePhoneCode findMobilePhoneCode(String phoneNum) {
		String hql = "from " + MobilePhoneCode.class.getName() + " where mobilePhone = ?";
		
		List<MobilePhoneCode> list = findByHql(hql, phoneNum);
		
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	/***
	 * 保存手机号及验证码至数据库中
	 */
	@Override
	public void saveMobilePhoneCode(String phoneNum, String validateCode) {
		MobilePhoneCode mobilePhoneCode = findMobilePhoneCode(phoneNum);
		if (mobilePhoneCode == null) {
			mobilePhoneCode = new MobilePhoneCode();
			mobilePhoneCode.setMobilePhone(phoneNum);
		} else {
			mobilePhoneCode.setUpdateTime(new Date());
		}
		
		mobilePhoneCode.setCode(validateCode);
		
		super.save(mobilePhoneCode);
	}
	
}
