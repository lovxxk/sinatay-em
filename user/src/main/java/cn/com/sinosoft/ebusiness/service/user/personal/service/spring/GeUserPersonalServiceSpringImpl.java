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
 * ���˷���ʵ����
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
		// ��ѯ�����󶨱��еı�����.
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("CUSTOMERID", userId);
		List<BindPolicy> listBindPolicy = bindPolicyService.findPolicyByQueryRule(queryRule);
		List<PolicyList> listPolicyList = new ArrayList<PolicyList>();

		// ��ѯ�����б�
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

		/*** Ĭ��ֵ **/
		geUserPersonal.setUserID(getUserID());
		/** ���ÿͻ��� */
		geUserPersonal.setPwd(new Md5().toMD5(geUserPersonal.getPwd()));
		/** ����MD5���� */
		geUserPersonal.setIntegral(new BigDecimal(0));
		/** ���û��� */
		geUserPersonal.setCheckStatus("0");
		/** �������� */
		geUserPersonal.setUserSource("1");
		/** �����û���Դ */
		geUserPersonal.setUserLevel("1");
		/** �����û��ȼ� */
		geUserPersonal.setUserRank("T");
		/** ���������û��ȼ� */
		geUserPersonal.setMakeDate(new Date());
		/** ����ʱ�� */
		geUserPersonal.setRaInd("0");
		/** �����û�RA��֤״̬��0-δ��֤��1-����֤ */
		if (activeSwitchIsOpen()) {
			geUserPersonal.setStatus("2");
			/** �����û��Ĵ���״̬Ϊ2��δ���� */
		} else {
			resultList.add("noActive");
			geUserPersonal.setStatus("1");
			/** �����û��Ĵ���״̬Ϊ1���Ѽ��� */
		}
		/** �����û����ڵ�������ҳ����յ����м��������룩 */
		geUserPersonal.setAreaCode(geUserPersonal.getCity());
		
		if (LDAPSwitchIsOpen()) {
			/** �жϹ��ܿ��� */
			GeResultsDto geResults = insertUserToLDAP(geUserPersonal).getGeResults();
			List list = geResults.getResult();
			if (list != null && list.contains("1")) {
				/** �жϽ����Ƿ�ɹ� */
				saveUser(geUserPersonal);
			} else if (list.contains("2")) { // �ͻ����ظ�
				registerWithLDAP(geUserPersonal);
			}
			resultList.addAll(list);
			return resultList;
		} else {
			if (!isUnique("USERID", geUserPersonal.getUserID())) {
				/** �û����ظ� */
				registerWithLDAP(geUserPersonal);
			}
			if (!isUnique("USERACCOUNT", geUserPersonal.getUserAccount())) {
				/** 3-�û����ظ� */
				resultList.add("3");
			}
			if (!isUnique("EMAIL", geUserPersonal.getEmail())) {
				/** 4-�����ظ� */
				resultList.add("4");
			}
			if ("01".equals(geUserPersonal.getIdentifyType())&&(!isIdentifyUnique("IDENTIFYTYPE","01","IDENTIFYNUMBER",geUserPersonal.getIdentifyNumber()))) {
				/** 5-���֤�ظ� */
				resultList.add("5");
			}
			/**У��Ψһ�Գɹ� */
			if ((resultList.contains("noActive") && resultList.size() == 1)|| resultList.size() == 0) {
				if (saveUser(geUserPersonal)) {
					resultList.add("1");
				} else {
					resultList.add("0");
					/** 0-���汾�ؿ�ʧ�� */
				}
			}
			return resultList;
		}
	}

	public GeUserPersonal getUserPersonalForLoginWithLDAP(
			GeUserPersonal geUserPersonal) {
		QueryRule queryRule = QueryRule.getInstance();
		
		if (!StringUtils.isBlank(geUserPersonal.getEmail())) {
			/** �����½ */
			queryRule.addEqual("email", geUserPersonal.getEmail().trim());
			//queryRule.addSql("lower(email) = '"+geUserPersonal.getEmail().trim().toLowerCase()+"'");
		}
		if (!StringUtils.isBlank(geUserPersonal.getUserAccount())) {
			/** ע���˻���½ */
			queryRule.addEqual("userAccount", geUserPersonal.getUserAccount().trim());
		}
		if (!StringUtils.isBlank(geUserPersonal.getPiCardNo())) {
			/** �׿���½ */
			queryRule.addEqual("piCardNo", geUserPersonal.getPiCardNo().trim());
		}
		if (!StringUtils.isBlank(geUserPersonal.getIdentifyNumber())) {
			/** ���֤�ŵ�½ */
			queryRule.addEqual("identifyNumber", geUserPersonal.getIdentifyNumber().trim());
		}

		GeUserPersonal geUser = findUser(queryRule);
		/** ��ѯ���� */
		if (geUser != null) {
			if ("0".equals(geUser.getStatus())) {
				//throw UserServiceException.newInstanceCode("021");
				return geUser;
			} else {
				return geUser;
			}
		} else {
			if (LDAPSwitchIsOpen()) {
				/** �ж��Ƿ��й��ܿ��� */
				PersonalUserDto personalUser = findUserFromLDAP(geUserPersonal);
				String result = personalUser.getGeResults().getResult().get(0);
				/** ��ѯ�����1-�ɹ���2-ʧ��(��ѯ��Ϣ������) */
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
		/** 1-�������� 2-�ֻ�����,Ĭ��Ϊ2 */
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("userID", geUserPersonal.getUserID());
		if (StringUtils.isBlank(oldActiveCode)) {
			/** ԭ������Ϊ���򼤻ʽΪ���䷽ʽ */
			checkStatus = "1";
			GeUserPersonal geUserPer = findUser(queryRule);
			oldActiveCode = geUserPer.getActiveCode();
			if (StringUtils.isBlank(oldActiveCode)) {// ����ԭ������Ϊ��
				return "03";// ԭ������Ϊ�գ���Ҫ���·��ͼ����������ѡ���ֻ�����
			}
		}

		if (newActiveCode.equals(oldActiveCode)) {

			/** �޸�״̬ **/
			GeUserPersonal geUP = new GeUserPersonal();
			geUP.setUserID(geUserPersonal.getUserID());
			geUP.setStatus("1");
			List list = this.updateGeUserPersonalWithLDAP(geUP);

			if (list.contains("1")) {
				/** �޸��Ƿ����� */
				Map map = new HashMap();
				map.put("status", "1");
				map.put("checkStatus", "1");
				/** 1-�������� 2-�ֻ����� */
				updateUserPart(map, geUserPersonal.getUserID());
				return "01";
				/** 01��ʾ����ɹ� */
			} else {
				return "00";
				/** 00��ʾ����ʧ�� */
			}
		} else {
			return "02";
			/** 02��ʾ������������� */
		}
	}

	@Override
	public List resetPasswordWithLDAP(GeUserPersonal geUserPersonal)
			throws CheckedMailException, UnsupportedEncodingException {
		List resultList = new ArrayList();
		/** ��Ž������ */
		int type = 0;
		/** �����������ķ�ʽ 1-���˻�+�ֻ��� 0-���� */

		/** У��ͻ��Ƿ���� 1.ͨ���û����ж����÷�ʽ 2.�ֻ�������ֻУ�鱾�ؿ� 3.����������У�鱾�ؿ⣬���ؿ�û����У��LDAP */
		GeUserPersonal geUP = new GeUserPersonal();
		QueryRule queryRule = QueryRule.getInstance();
		if (!StringUtils.isBlank(geUserPersonal.getUserAccount())) {
			type = 1;
			queryRule.addEqual("userAccount", geUserPersonal.getUserAccount().trim());
			queryRule.addEqual("mobilePhone", geUserPersonal.getMobilePhone().trim());
			geUP = findUser(queryRule);
			if (geUP == null) {
				resultList.add("01");
				/** �ÿͻ������� */
				return resultList;
			}
		} else {
			queryRule.addEqual("email", geUserPersonal.getEmail().trim());
			geUP = findUser(queryRule);

			if (geUP == null) {
				/** ���ؿ�û�����ѯLDAP */
				if (LDAPSwitchIsOpen()) {
					/** �ж��Ƿ���LDAP���ܿ��� */
					PersonalUserDto personalUser = findUserFromLDAP(geUserPersonal);
					if (personalUser.getGeResults().getResult().contains("1")) {
						/** LDAP���� */
						geUP = sycToLocal(personalUser);
						/** ͬ�������ؿ� */
					} else {
						resultList.add("01");
						/** �ÿͻ������� */
						return resultList;
					}
				} else {
					resultList.add("01");
					/** �ÿͻ������� */
					return resultList;
				}
			}
		}

		/** ���������루��������������õ����� */
		String newPasswordString = GeUserPersonalUtil.getInitPassword();
		/** ���������� */
		List rList = new ArrayList();
		if (LDAPSwitchIsOpen()) {
			/** �ж��Ƿ��й��ܿ��� */
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

		/** ���������� */
		if (rList.contains("1")) {
			if (type == 1) {
				if (sendSMIsOpen()) {
					// Ĭ�ϵ�һ��������������û�д���¼��
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
						/** ��������ɹ� */
					} else {
						resultList.add("02");
						/** ��������ʧ�� */
					}
				} else {
					resultList.add("1");
					/** ��������ɹ� */
				}
			} else {
				// ---�������뷢���ʼ���ʼ---
				String title = "�һ�����֪ͨ";// �ʼ�����
				Map<String, String> model = new HashMap<String, String>();// ���vm���${}�������������map���key��Ӧ
				mailService.setVelocityFilePath("/mail/mailModel_resetPersonal.vm"); // ����vm�ļ�·��
				String mail = geUP.getEmail();// "yuanhuijing@sinosoft.com.cn"
												// ;//geUP.getEmail();
				String[] address = new String[] { mail };
				mailService.setSubject(title);
				// �ʼ�������ͼƬʱ��ͼƬ�Ĵ���
				String[] picNameStrings = {
						"china_life_logo.jpg", "china_life_pen.jpg",
						"china_life_php.jpg", "china_life_pic1.jpg",
						"china_life_pic4.jpg", "china_life_pic5.jpg" };
				List<InlineFile> list = new ArrayList<InlineFile>();
				String locationString = getProjectLocalPath();// ��ȡ����·��
				for (int i = 0; i < picNameStrings.length; i++) {
					File image = new File(imageOnlineAbsolutePath
							+ picNameStrings[i]);// ͼƬ�ļ��ı�������·��
					InlineFile inlineFileOne = new InlineFile();
					inlineFileOne.setInlineFile(image);
					inlineFileOne.setInlineFileName(picNameStrings[i]);// ͼƬ�ļ���
					list.add(inlineFileOne);
				}
				model.put("newPasswordString", toUtf8(newPasswordString));
				// ���͵� ����
				mailService.sendMail(address, null, list, model, title);
				// ---�������뷢���ʼ�����---

				resultList.add("1");
				/** ��������ɹ� */
			}
			return resultList;
		} else {
			resultList.add("02");
			/** 02������û����ڵ���������ʧ�� */
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
				/** �ж��Ƿ��LDAP���� */
				GeResultsDto geResults = updateUserToLDAP(geUserPersonal).getGeResults();
				if (geResults == null || geResults.getResult() == null) {
					throw UserServiceException.newInstanceCode("012");
				}
				return geResults.getResult();
			} else {
				if ((!StringUtils.isBlank(geUserPersonal.getUserAccount()))&&(!geUserPersonal.getUserAccount().equals(geUser.getUserAccount()))&&(!isUnique("USERACCOUNT", geUserPersonal.getUserAccount()))) {
					/** 3-�û����ظ� */
					resultList.add("3");
				}
				if ((!StringUtils.isBlank(geUserPersonal.getEmail()))&&(!geUserPersonal.getEmail().equals(geUser.getEmail()))&&(!isUnique("EMAIL", geUserPersonal.getEmail()))) {
					/** 4-�����ظ� */
					resultList.add("4");
				}
				if ("01".equals(geUserPersonal.getIdentifyType())&&(!(("01".equals(geUser.getIdentifyType())&&geUser.getIdentifyNumber().equals(geUserPersonal.getIdentifyNumber()))))&&(!isIdentifyUnique("IDENTIFYTYPE","01","IDENTIFYNUMBER",geUserPersonal.getIdentifyNumber()))) {
					
					/** 5-���֤�ظ� */
					resultList.add("5");
				}
				if ((!StringUtils.isBlank(geUserPersonal.getPiCardNo()))&&(!geUserPersonal.getPiCardNo().equals(geUser.getPiCardNo()))&&(!isUnique("PICARDNO", geUserPersonal.getPiCardNo()))) {
					/** 6-�׿����ظ� */
					resultList.add("6");
				}
				if (resultList.size() == 0) {
					/** û�򿪿�����ֱ���޸ı��ؿ� */
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

		/** �ж������ԭ�����Ƿ���ȷ */
		QueryRule queryRule = QueryRule.getInstance();
		/** ��ȡQueryRule�����Instance */
		queryRule.addEqual("userID", geUserPersonal.getUserID());
		queryRule.addEqual("pwd", new Md5().toMD5(geUserPersonal.getPwd()));

		GeUserPersonal userInfoLocal = findUser(queryRule);
		if (userInfoLocal == null) {
			list.add("00");
			/** 00��ʾ�û������ԭ������� */
			return list;
		} else {
			if ("3".equals(userInfoLocal.getStatus())) {
				throw UserServiceException.newInstanceCode("020");
			}
			/** �޸����� */
			geUserPersonal.setPwd(new Md5().toMD5(newPassword));
			/** ���� */
			if (LDAPSwitchIsOpen()) {
				/** �ж��Ƿ��й��ܿ��� */
				GeResultsDto geResults = updateUserToLDAP(geUserPersonal)
						.getGeResults();
				if (geResults == null) {
					throw UserServiceException.newInstanceMsg("@Code:EU05002@Message:LDAP�޸ķ��ؽ����Ϊnull");
				}
				return geResults.getResult();
			} else {
				userInfoLocal.setPwd(new Md5().toMD5(newPassword));
				super.update(userInfoLocal);
				list.add("1");
				return list;
				/** 1-�ɹ� */
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
	@LocusTrace(setDesc="��ҳ��ѯǰ̨�����û�")
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
				logger.info("�ͻ���Ϊ��" + geUserPersonal.getUserID() + "�û���ѯLDAP�����쳣���쳣��Ϣ��" + e.getMessage());
				throw UserServiceException.newInstanceMsg("@Code:EU05003@Message:LDAP��ѯ���ݳ����쳣�����쳣��ϸ��Ϣ",e);
			} else {
				logger.info("�ͻ���Ϊ��" + geUserPersonal.getUserID() + "�û���ѯLDAP�����쳣���쳣��Ϣ��" + messStr);
				throw UserServiceException.newInstanceMsg("@Code:EU05003@Message:LDAP��ѯ���ݳ����쳣���쳣��Ϣ��" + messStr ,e);
			}
		} catch (Exception e) {
			logger.info("�ͻ���Ϊ��" + geUserPersonal.getUserID()+ "���û���ѯLDAP�����쳣���쳣��Ϣ��" + e.getMessage());
			throw UserServiceException.newInstanceMsg("@Code:EU05003@Message:LDAP��ѯ���ݳ����쳣", e);
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
				throw UserServiceException.newInstanceMsg("@Code:EU05001@Message:LDAP�������ݳ����쳣�����쳣��ϸ��Ϣ",e);
			} else {
				logger.info("�ͻ���Ϊ��" + geUserPersonal.getUserID() + "�û�����LDAP�����쳣���쳣��Ϣ��" + messStr);
				throw UserServiceException.newInstanceMsg("@Code:EU05001@Message:LDAP�������ݳ����쳣���쳣��Ϣ��" + messStr ,e);
			}
		} catch (Exception e) {
			logger.info("�ͻ���Ϊ��" + geUserPersonal.getUserID()+ "���û�����LDAP�����쳣���쳣��Ϣ��" + e.getMessage());
			throw UserServiceException.newInstanceMsg("@Code:EU05001@Message:LDAP�������ݳ����쳣", e);
		}
	}

	@Override
	public PersonalUserDto updateUserToLDAP(GeUserPersonal geUserPersonal)
			throws UserServiceException {
		List list = null;//����ָ�����ldap���ؽ��
		try {
			list = webService.sendRPCRequestXML(geUserPersonal, "EU05002");
		} catch (portalModuleException e) {
			String messStr = e.getMsg();
			if(StringUtils.isBlank(messStr)){
				throw UserServiceException.newInstanceMsg("@Code:EU05002@Message:LDAP�޸����ݳ����쳣�����쳣��ϸ��Ϣ");
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
					logger.info("�ͻ���Ϊ��" + geUserPersonal.getUserID() + "���û��޸�LDAP�����쳣���쳣��Ϣ��" + messStr);
					throw UserServiceException.newInstanceMsg("@Code:EU05002@Message:�޸�LDAP�����쳣���쳣��Ϣ��" + messStr, e);
				}
			}
		} catch (Exception e) {
			logger.info("�ͻ���Ϊ��" + geUserPersonal.getUserID()+ "���û��޸�LDAP�����쳣����ϸ��" + e.getMessage());
			throw UserServiceException.newInstanceMsg("@Code:EU05002@Message:LDAP�޸����ݳ����쳣", e);
		}
		
		if (list == null || list.get(0) == null) {//ldap���ؽ��Ϊnull
			throw UserServiceException.newInstanceMsg("@Code:EU05002@Message:�޸ĸ�����Ϣʱ��ѯ�����޸Ľӿ���Ϣ��LDAP���ص�ҵ����Ϊ��");
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
			// ---�û�ע����Ϣ�����ʼ���ʼ---
			String title = "��л�����򱾹�˾���ղ�Ʒ";// �ʼ�����
			Map<String, String> model = new HashMap<String, String>();// ���vm���${}�������������map���key��Ӧ
			mailService.setVelocityFilePath("/mail/mailModel_acountMessage.vm"); // ����vm�ļ�·��
			String mail = geUserPersonal.getEmail();
			String[] address = new String[] { mail };
			mailService.setSubject(title);
			// �ʼ�������ͼƬʱ��ͼƬ�Ĵ���
			String[] picNameStrings = { "china_life_logo.jpg",
					"china_life_pen.jpg", "china_life_php.jpg",
					"china_life_pic1.jpg", "china_life_pic4.jpg",
					"china_life_pic5.jpg" };
			List<InlineFile> list = new ArrayList<InlineFile>();
			String locationString = getProjectLocalPath();// ��ȡ����·��
			for (int i = 0; i < picNameStrings.length; i++) {
				File image = new File(imageOnlineAbsolutePath
						+ picNameStrings[i]);// ͼƬ�ļ��ı�������·��
				InlineFile inlineFileOne = new InlineFile();
				inlineFileOne.setInlineFile(image);
				inlineFileOne.setInlineFileName(picNameStrings[i]);// ͼƬ�ļ���
				list.add(inlineFileOne);
			}
			model.put("userAccount", toUtf8(geUserPersonal.getUserAccount()));
			model.put("passWord", toUtf8(geUserPersonal.getPwd()));
			model.put("proposalNo", proposalNo);
			model.put("production", toUtf8(production));
			String userName = geUserPersonal.getUserName();
			String sex = geUserPersonal.getSex();
			if("1".equals(sex)){
				userName = userName + "����";
				model.put("userName", toUtf8(userName));
			}else if("2".equals(sex)){
				userName = userName + "Ůʿ";
				model.put("userName", toUtf8(userName));
			}
			
			// ���͵� ����
			mailService.sendMail(address, null, list, model, title);
			// ---�����뷢���ʼ�����---
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
					/** ���õ������ӿڷ����ֻ����� */
					if (sendSMIsOpen()) {
						// Ĭ�ϵ�һ��������������û�д���¼��
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
					// ---�����뷢���ʼ���ʼ---
					String title = "������֪ͨ";// �ʼ�����
					Map<String, String> model = new HashMap<String, String>();// ���vm���${}�������������map���key��Ӧ
					mailService.setVelocityFilePath("/mail/mailModel_active.vm"); // ����vm�ļ�·��
					String mail = geUserPersonal.getEmail();
					String[] address = new String[] { mail };
					mailService.setSubject(title);
					// �ʼ�������ͼƬʱ��ͼƬ�Ĵ���
					String[] picNameStrings = {
							"china_life_logo.jpg", "china_life_pen.jpg",
							"china_life_php.jpg", "china_life_pic1.jpg",
							"china_life_pic4.jpg", "china_life_pic5.jpg" };
					List<InlineFile> list = new ArrayList<InlineFile>();
					String locationString = getProjectLocalPath();// ��ȡ����·��
					for (int i = 0; i < picNameStrings.length; i++) {
						File image = new File(imageOnlineAbsolutePath
								+ picNameStrings[i]);// ͼƬ�ļ��ı�������·��
						InlineFile inlineFileOne = new InlineFile();
						inlineFileOne.setInlineFile(image);
						inlineFileOne.setInlineFileName(picNameStrings[i]);// ͼƬ�ļ���
						list.add(inlineFileOne);
					}
					model.put("activeCode", toUtf8(activeCode));
					// ���͵� ����
					mailService.sendMail(address, null, list, model, title);
					// ---�����뷢���ʼ�����---
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
	 * ��LDAP���ݱ��浽���ؿ�
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
				/** personalUser--ת��->geUser�������� */
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
	 * �ж�LDAP���ܿ����Ƿ�ͨ
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
			/** ���Կ���״̬(0 ��Ч 1 ��Ч 2δ��ͨ) */
			return true;
		} else {
			return false;
		}
	}

	/**
	 * �ж��û�����ܿ����Ƿ�ͨ
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
			/** ���Կ���״̬(0 ��Ч 1 ��Ч 2δ��ͨ) */
			return true;
		} else {
			return false;
		}
	}

	/**
	 * ��ѯΨһ��
	 * 
	 * @param columnName
	 *            ����
	 * @param value
	 *            ֵ
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
	 * ��ѯΨһ��
	 * 
	 * @param columnName1
	 *            ����1
	 * @param value1
	 *            ֵ1
	 * @param columnName2
	 *            ����2
	 * @param value1
	 *            ֵ2
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
	 * ��ȡ��Ŀ����·��
	 * 
	 * @return ��Ŀ·��
	 * @throws Exception
	 *             δ�ҵ�·��
	 */
	public static String getProjectLocalPath() {
		try {
			String path = GeUserPersonalServiceSpringImpl.class.getResource("")
					.getFile();// �˴� ComplaintAction.classΪ��ȡ��ǰ�࣬����������action
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
	 * ����ת�뷽��
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String toUtf8(String code) throws UnsupportedEncodingException {
		return new String(new String(code).getBytes("UTF-8"), "ISO-8859-1"); // ������������������Ҫת��
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
			/** ���Կ���״̬(0 ��Ч 1 ��Ч 2δ��ͨ) */
			return true;
		} else {
			return false;
		}
	}
	//������λ���������
	public String getRandomPwd(){
		final int maxNum = 36;
		int i;//���ɵ������
		int count = 0;//��������ĳ���
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

//		String pwd = "111111";//����������ɣ����͵�����
		String pwd = getRandomPwd();
		/*** Ĭ��ֵ **/
		geUserPersonal.setUserID(getUserID());
		/** ���ÿͻ��� */
		geUserPersonal.setPwd(new Md5().toMD5(pwd));
		/** ����MD5���� */
		geUserPersonal.setIntegral(new BigDecimal(0));
		/** ���û��� */
		geUserPersonal.setCheckStatus("0");
		/** �������� */
		geUserPersonal.setUserSource("1");
		/** �����û���Դ */
		geUserPersonal.setUserLevel("1");
		/** �����û��ȼ� */
		geUserPersonal.setUserRank("T");
		/** ���������û��ȼ� */
		geUserPersonal.setMakeDate(new Date());
		/** ����ʱ�� */
		geUserPersonal.setRaInd("0");
		/** �����û�RA��֤״̬��0-δ��֤��1-����֤ */
		
		/** ��¼���� */
		geUserPersonal.setLoginNum(0);
		/** �����û��Ĵ���״̬Ϊ1���Ѽ��� */
		geUserPersonal.setStatus("1");
		
		/** �����û����û��� */
		geUserPersonal.setUserAccount("M" + geUserPersonal.getUserID());
		/** �����û��Ļ���״̬ */
		geUserPersonal.setMarriageStatus("9");
		
		if (LDAPSwitchIsOpen()) {
			/** �жϹ��ܿ��� */
			GeResultsDto geResults = insertUserToLDAP(geUserPersonal).getGeResults();
			List list = geResults.getResult();
			if (list != null && list.contains("1")) {
				/** �жϽ����Ƿ�ɹ� */
				saveUser(geUserPersonal);
			} else if (list.contains("2")) { // �ͻ����ظ�
				registerFromWap(geUserPersonal);
			}
			resultList.addAll(list);
		} else {
			if (!isUnique("USERID", geUserPersonal.getUserID())) {
				/** �û����ظ� */
				registerWithLDAP(geUserPersonal);
			}
			if (!isUnique("USERACCOUNT", geUserPersonal.getUserAccount())) {
				/** 3-�û����ظ� */
				resultList.add("3");
			}
			if (!isUnique("EMAIL", geUserPersonal.getEmail())) {
				/** 4-�����ظ� */
				resultList.add("4");
			}
			if ("01".equals(geUserPersonal.getIdentifyType())&&(!isIdentifyUnique("IDENTIFYTYPE","01","IDENTIFYNUMBER",geUserPersonal.getIdentifyNumber()))) {
				/** 5-���֤�ظ� */
				resultList.add("5");
			}
			/**У��Ψһ�Գɹ� */
			if (resultList.size() == 0) {
				if (saveUser(geUserPersonal)) {
					resultList.add("1");
				} else {
					resultList.add("0");
					/** 0-���汾�ؿ�ʧ�� */
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
				resultList.add("�û����ظ�");
			}
			if (list.contains("4")) {
				resultList.add("�����ظ�");
			}
			if (list.contains("5")) {
				resultList.add("���֤�ظ�");
			}
			if (list.contains("6")) {
				resultList.add("�׿����ظ�");
			}
			if (list.contains("7")) {
				resultList.add("�������ݿ�ʧ��");
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
	 * �û�ע��ɹ�
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
	 * �û�ע��ɹ������û�ע��������ַ�����ʼ��������ʺ�
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
	 * ��ȡ��ǰ·��
	 * @return
	 */
	private String getSinatayUrl() {
		PropertyFileUtils.init(FilePathUtil.getClassBuildPath() + "config/sinatay_config.properties");
		
		Properties properties = PropertyFileUtils.getProperties();
		
		return properties.getProperty("sinatayUrl");
	}

	/**
	 * У��ͻ�ע��ʱ����������Ƿ��Ѵ��ڡ�
	 * true:������
	 * false:�Ѵ���
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
	 * �����û�������У���Ƿ�Ϊϵͳ�û�
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
	 * DWR У�����֤���Ƿ��Ѵ���
	 * @return
	 */
	public boolean userPersonalNumberValidate(String number) {
		String hql = "from " + GeUserPersonal.class.getName() + " where identfyNumber = ?";
		
		@SuppressWarnings("unchecked")
		List<GeUserPersonal> list = this.findByHql(hql, number);
		
		return list.isEmpty() ? true : false;
	}

	/**
	 * ����֧������ݵ�¼��Ҫ�Ĳ���
	 * 
	 * @return
	 */
	public static Map<String, String> generateAlipayLoginParamter() {
		// ������������������
		Map<String, String> sParaTemp = new HashMap<String, String>();

		try {
			String target_service = "user.auth.quick.login";
			
			PropertyFileUtils.init(FilePathUtil.getClassBuildPath() + "config/sinatay_config.properties");
			Properties properties = PropertyFileUtils.getProperties();
			String sinatayUrl = properties.getProperty("sinatayUrl");
			// ����
			// ���ҳ����תͬ��֪ͨҳ��·��
			String return_url = sinatayUrl + "/login/alipayLoginSuccess.do";

			// ������ʱ���
			String anti_phishing_key = AlipaySubmit.query_timestamp();
			// ��Ҫʹ����������ļ�submit�е�query_timestamp����

			// �ͻ��˵�IP��ַ
			// String exter_invoke_ip =
			// InetAddress.getLocalHost().getHostAddress();
			String exter_invoke_ip = InetAddress.getLocalHost().getHostAddress();
			// �Ǿ�����������IP��ַ���磺221.0.0.1

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
				//ΨһID
				geUserPersonal.setUniqueId(user.getId());
				//΢������
				geUserPersonal.setAlias(user.getScreenName());
				//�Ա�
				geUserPersonal.setSex(user.getGender().equals("m") ? "1" : (user.getGender().equals("f") ? "2" : "0"));
				geUserPersonal.setActive(true);
				
				saveUser(geUserPersonal);
			} else {
				geUserPersonal.setAlias(user.getScreenName());
				geUserPersonal.setSex(user.getGender().equals("m") ? "1" : (user.getGender().equals("f") ? "2" : "0"));
				
				updateGeUserPersonalWithLDAP(geUserPersonal);
			}
			//����Spring security
			getAuthorities(geUserPersonal, user.getScreenName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return geUserPersonal;
	}


	/**
	 * ��������ʱ��֤������ֻ����Ƿ�Ϸ�
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

		// ��ȡ�û���Ϣ
		GeUserPersonal GeUserPersonal = checkMailOrPhone(param);
		GeUserPersonal.setPwd(password);

		if (param.indexOf("@") > 0) {// ͨ������ȡ������
			resetPasswordByMail(GeUserPersonal);
		} else {// ͨ���ֻ�����ȡ������
			resetpasswordByPhone(GeUserPersonal);
		}

		this.update(GeUserPersonal);

		return GeUserPersonal;
	}

	private void resetpasswordByPhone(GeUserPersonal GeUserPersonal) {

	}

	/**
	 * ͨ������ȡ������
	 * 
	 * @param GeUserPersonal
	 */
	private void resetPasswordByMail(GeUserPersonal geUserPersonal) {
		try {
			if(geUserPersonal != null && StringUtils.isNotBlank(geUserPersonal.getEmail())){
				Map<String,String> map = new HashMap<String, String>();
				map.put("email", geUserPersonal.getEmail());
				map.put("pwd", geUserPersonal.getPwd());
				sendEmailService.sendResetPasswordEmail("��̩�������ѣ������ڽ���ȡ��������������������֤",map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ͨ���ֻ�����ȡ������
	 * 
	 * @param GeUserPersonal
	 * @return
	 */
	private String generateMailContent(GeUserPersonal GeUserPersonal) {
		StringBuffer content = new StringBuffer();

		content.append("�𾴵Ŀͻ� ��������̩����ע����ʺ������ѱ�����Ϊ ");
		content.append(GeUserPersonal.getPwd());
		content.append("��������ʹ���ֻ��Ż�������е�¼��");

		return content.toString();
	}

	/**
	 * ֧������ݵ�¼�ɹ�֮�󣬻�ȡ��¼�û���Ϣ
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
			
			//֧������¼���ȡ��token��֧��ʱʹ��
			request.getSession().setAttribute("token", token);
			
			if (userPersonal == null) {
				userPersonal = new GeUserPersonal();
				//֧������¼ΨһID
				userPersonal.setUniqueId(user_id);
				//�ǳƣ�֧����������
				userPersonal.setAlias(real_name);
				userPersonal.setActive(true);
				
				saveUser(userPersonal);
			} else {
				//�ǳƣ�֧����������
				userPersonal.setAlias(real_name);
				updateUserPersonal(userPersonal);
			}
			//����Spring security
			getAuthorities(userPersonal, real_name);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return userPersonal;
	}
	
	/**
	 * ����Spring security
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
	 * ��֤��ǰ�ֻ����Ƿ���ע��
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
	 * ����userId��ȡUser����
	 */
	@Override
	public GeUserPersonal getUserPersonalByUserId(String userId) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("userID", userId);

		List<GeUserPersonal> list = super.find(queryRule);
		
		return list.isEmpty() ? null : list.get(0);
	}
	
	/**
	 * �ֻ���̬����֤
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
	 * ��ȡ�ֻ���̬��֤��
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
	 * �����û����ж��Ƿ���ڸ��û�
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
				log.error("û�л�ȡ����Ӧ����");
			} else {
				accessToken = accessTokenObj.getAccessToken();
				long tokenExpireIn = accessTokenObj.getExpireIn();

				// ���û�ȡ����accessToken ȥ��ȡ��ǰ�õ�openid -------- start
				OpenID openIDObj = new OpenID(accessToken);
				openID = openIDObj.getUserOpenID();
				
				UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
				UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
				
				log.error("userInfoBean:" + userInfoBean.getNickname() + ","
						+ userInfoBean.getGender() + userInfoBean.getMsg() + ","
						+ userInfoBean.getRet() + ","
						+ userInfoBean.getAvatar().getAvatarURL100());
				
				userPersonal = validateUniqueId(openID);
				//�״�ʹ��QQ��ݵ�¼
				if (userPersonal == null) {
					userPersonal = new GeUserPersonal();
					//QQ��¼����ΨһID
					userPersonal.setUniqueId(openID);
					//�ǳ�
					userPersonal.setAlias(userInfoBean.getNickname());
					//1���У�2��Ů
					userPersonal.setSex("��".equals(userInfoBean.getGender()) ? "1" : "2");
					userPersonal.setActive(true);
					
					saveUser(userPersonal);
				} else {
					userPersonal.setAlias(userInfoBean.getNickname());
					userPersonal.setSex("��".equals(userInfoBean.getGender()) ? "1" : "2");
					
					updateUserPersonal(userPersonal);
				}

				//����Spring security
				getAuthorities(userPersonal, userInfoBean.getNickname());
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("exception message:"+e.getCause().getMessage());
		}
		return userPersonal;
	}

	/**
	 * ��Կ�ݵ�¼,��ѯ���ʺ��Ƿ��¼��
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
	 * ͨ�������䷢����֤�����ʽȡ������
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
				sendEmailService.sendResetPasswordEmail("��̩�������ѣ������ڽ���ȡ��������������������֤",map);
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
		result.append("{name:'��ѡ��',value:'-1'}");
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
	 * true:��ʾ�û����޸Ĺ����ݣ����ѯ���Ϊ��
	 * false����ʾû���޸Ĺ����ݣ�����Բ�ѯ��֮ǰ������
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
	 * �Ƿ�󶨹�����
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
			result = "0";//��
		} else if (rs && pwd.length() > 8) {
			result = "1";//��
		} else if (!rs && pwd.length() <= 8) {
			result = "1";//��
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
			sendEmailService.sendResetPasswordEmail("��̩�������ѣ������ڽ��а�������������������֤",map);
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
	 * �Ƿ��������
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
	 * �������֤�Ų�ѯ�û���Ϣ
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
	 * �����ʺ�
	 * @param userId
	 */
	public void unlockUserAccount(String userId) {
		String sql = "update ge_user_personal set lockUserAccount = 'N' , lockTime = '', loginFailedCount = 0 where userID = '" + userId + "'";
		this.jdbcTemplate.update(sql.toString());
	}

	/**
	 * �޸ĵ�¼ʧ�ܴ���������ʺ�����ʱ��
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
//							Thread.sleep(1000 * 10);//10����֮������ʺ�
							Thread.sleep(1000 * 60 * 60 * 2);//2Сʱ֮������ʺ�
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
	 * �û���¼�ɹ��󣬸��²����ֶ���Ϣ 
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
	 *  ��ѯĳһ�ֻ��Ż����䵱�췢����֤��Ĵ���
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
	 * ÿ�η�����֤��֮�󣬼�¼���ʹ���
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
	 * �����ֻ��ż���֤�������ݿ���
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
