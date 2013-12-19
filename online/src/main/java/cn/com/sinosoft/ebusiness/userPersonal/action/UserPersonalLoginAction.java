package cn.com.sinosoft.ebusiness.userPersonal.action;

import ins.framework.web.Struts2Action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import cn.com.sinosoft.ebusiness.service.user.common.domain.LoginUserInfo;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPersonal;
import cn.com.sinosoft.ebusiness.service.user.personal.enums.PersonalLoginType;
import cn.com.sinosoft.ebusiness.service.user.personal.service.facade.GeUserPersonalService;
import cn.com.sinosoft.ebusiness.service.user.personal.service.facade.UserService;
import cn.com.sinosoft.util.cookie.EncryptionUtil;
import cn.com.sinosoft.util.encode.Md5;
import cn.com.sinosoft.util.security.springSecurity.SpringSecurityUtils;
import cn.com.sinosoft.util.time.DateUtils;

public class UserPersonalLoginAction extends Struts2Action {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(UserPersonalLoginAction.class);

	public GeUserPersonal customer;

	public GeUserPersonalService geUserPersonalService;
	
	public UserService userService;

	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	/**
	 * �����¼ҳ��
	 * 
	 * @return
	 */
	public String login() {

		return LOGIN;
	}

	/**
	 * ϵͳ�û���¼
	 * 
	 * @return
	 */
	public String userPersonalLogin() {
		LoginUserInfo loginUserInfo = SpringSecurityUtils.getCurrentUser();
		if (loginUserInfo != null) {
			customer = loginUserInfo.getCustomer();
			log.error("��¼�ɹ�����ʼ�����û�����:" + dateFormat(new Date()));
			geUserPersonalService.updateGeUserPersonalBySql(customer);
			log.error("��¼�ɹ��������û����ݽ���:" + dateFormat(new Date()));

			// ��ǰ��¼�û���Ϣ
			getSession().setAttribute("geUserPersonal", customer);
			// ��ǰ�û���¼��ʽ
			getSession().setAttribute("personalLoginType", PersonalLoginType.userLogin);
			
			String remeber = (String) getRequest().getSession().getAttribute("remeber");
			if (StringUtils.isNotBlank(remeber) && remeber.equals("true")) {
				//�����û���
				Cookie cookie = new Cookie("autoLoginUser", EncryptionUtil.encrypt(customer.getUserAccount()));
				cookie.setPath("/");
				cookie.setMaxAge(60 * 60 * 24 * 7);
				getResponse().addCookie(cookie);
			}
		}
		
		return LOGIN;
	}

	/**
	 * ֧������ݵ�¼�ɹ�����ȡ��¼�ʺŵĻ�����Ϣ
	 * 
	 * @return
	 */
	public String alipayLoginSuccess() {
		log.info("alipay auth login success.");

		HttpServletRequest request = getRequest();
		GeUserPersonal userPersonal = geUserPersonalService.alipayLoginInfo(request);
		
		this.customer = userPersonal;
		
		getSession().setAttribute("geUserPersonal", userPersonal);
		getSession().setAttribute("personalLoginType", PersonalLoginType.aliLogin);

		return SUCCESS;
	}

	/**
	 * QQ��ݵ�¼
	 * 
	 * @return
	 */
	public String QQLogin() {

		return SUCCESS;
	}

	/**
	 * ��¼�ɹ�
	 * 
	 * @return
	 */
	public String QQLoginSuccess() {
		log.error("************************************************************************");
		GeUserPersonal userPersonal = geUserPersonalService.qqLogin(getRequest());

		this.customer = userPersonal;
		getSession().setAttribute("geUserPersonal", userPersonal);
		getSession().setAttribute("personalLoginType", PersonalLoginType.QQLogin);
		
		return SUCCESS;
	}

	/**
	 * ����΢����ݵ�¼
	 * 
	 * @return
	 */
	public String sinaLogin() {
		try {
			cn.com.sinosoft.ebusiness.weibo4j.Oauth oauth = new cn.com.sinosoft.ebusiness.weibo4j.Oauth();
			String url = oauth.authorize("code", "sina");
			getResponse().getOutputStream().print(
					"<script type='text/javascript'>window.location.href='" + url + "'</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}

	/**
	 * ����΢����ݵ�¼�ɹ�����ȡ��¼�ʺŻ�����Ϣ
	 * 
	 * @return
	 */
	public String sinaLoginSuccess() {
		try {
			GeUserPersonal geUserPersonal = geUserPersonalService.sinaLogin(getRequest());

			this.customer = geUserPersonal;
			getSession().setAttribute("geUserPersonal", geUserPersonal);
			getSession().setAttribute("personalLoginType", PersonalLoginType.sinaLogin);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * �������� �����û�¼����ֻ���
	 * 
	 * @return
	 */
	public String resetPassword() {
		String parameter = getRequest().getParameter("parameter");
		GeUserPersonal cus = geUserPersonalService.resetPassword(parameter);
		if (customer == null) {
			customer = new GeUserPersonal();
		}

		if (parameter.length() == 11 && parameter.indexOf("@") < 0) {
			customer.setMobilePhone(new String(Base64.encodeBase64(cus.getMobilePhone().getBytes())));
		} else {
			customer.setEmail(new String(Base64.encodeBase64(cus.getEmail().getBytes())));
		}
		return SUCCESS;
	}

	/**
	 * ��������ʱ��֤������ֻ����Ƿ�Ϸ�
	 * 
	 * @return
	 */
	public void validateMailOrPhone() {
		try {
			String resetPassword = getRequest().getParameter("resetPassword");
			GeUserPersonal cus = geUserPersonalService.checkMailOrPhone(resetPassword);
			PrintWriter writer = getResponse().getWriter();
			if (cus != null) {
				writer.write("success");
			} else {
				writer.write("false");
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("У����֤������쳣���쳣��Ϣ��" + e.getMessage());
		}
	}

	public String resetPasswordConfirm() {
		if (customer == null) {
			customer = new GeUserPersonal();
		}
		String email = customer.getEmail();
		if (StringUtils.isNotBlank(email)) {
			if (email.indexOf("@") < 0) {
				customer.setEmail(new String(Base64.decodeBase64(customer.getEmail().getBytes())));
			}
		}
		return SUCCESS;
	}

	/**
	 * �˳�
	 * @return
	 */
	public String loginOut() {
		customer = null;
		getSession().setAttribute("geUserPersonal", null);
		getSession().setAttribute("personalLoginType", null);
		
		SecurityContextHolder.getContext().setAuthentication(null);
		Cookie cookie = new Cookie("autoLoginUser", "");
		cookie.setPath("/");
		getResponse().addCookie(cookie);
		
		return SUCCESS;
	}
	
	public void remeber() {
		String flag = getRequest().getParameter("remeber");
		
		getRequest().getSession().setAttribute("remeber", flag);
	}
	
	public String serviceLogin(){
		String userName = getRequest().getParameter("userName");
		String password = getRequest().getParameter("password");
		String message = "success";
		PrintWriter writer =null;
		try {
			GeUserPersonal geUserPersonal = geUserPersonalService.getUserPersonalByEmailOrPhone(userName);
			if(geUserPersonal == null){
				message = "false";
			}
			GeUserPersonal user = geUserPersonalService.checkLogin(userName, password);
			if(user == null || !user.isActive()){
				message = "false";
			}
			if(geUserPersonal != null){
				//Spring��ȫ�����û���Ϣ
				userService.setCurrentUser(getSession(), geUserPersonal);
			}
			writer = getResponse().getWriter();
			writer.write(message);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}
		
		return NONE;
	}
	
	public String checkLogin() {
		PrintWriter writer = null;
		try {
			writer = getResponse().getWriter();
			
			String userName = getRequest().getParameter("userName"); 
			String password = getRequest().getParameter("password");
			String loginType = getRequest().getParameter("loginType");
			if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
				writer.write("userNameNull");
				return NONE;
			}
			
			//����û�¼��������֤��
			if (checkIdNum(userName)) {
				List<GeUserPersonal> userPersonals = geUserPersonalService.getUserPersonalByIdNum(userName, "");
				if (userPersonals.isEmpty()) {
					writer.write("idNumNull");
					return NONE;
				}
				if (userPersonals.size() > 1) {
					StringBuffer accounts = new StringBuffer("");
					for (GeUserPersonal geUserPersonal : userPersonals) {
						accounts.append(geUserPersonal.getUserAccount()).append("%%%");
					}
					if (StringUtils.isNotBlank(accounts.toString())) {
						accounts = new StringBuffer(accounts.substring(0, accounts.length() - 3));
					}
					writer.write(accounts.toString());
					return NONE;
				} else {
					GeUserPersonal geUserPersonal = userPersonals.get(0);
					//�ʺ��ѱ�����
					if (geUserPersonal.isLockUserAccount()) {
						writer.write("accountLock");
						return NONE;
					}
					//�������
					if (!new Md5().toMD5(password).equals(geUserPersonal.getPwd())) {
						geUserPersonalService.updateLoginFailedCount(geUserPersonal);
						writer.write("pwdError");
						return NONE;
					}
					//�����֤
					boolean bindPolicy = geUserPersonalService.checkBindPolicy(geUserPersonal.getUserID());
					if (!bindPolicy) {
						bindPolicy = geUserPersonalService.findPolicyByOrder(geUserPersonal.getUserID());
					}
					if (!bindPolicy) {
						writer.write("bindFalse");
						return NONE;
					}
				}
			} else {//�û���ͨ���ֻ���������е�¼
				GeUserPersonal geUserPersonal = geUserPersonalService.getUserPersonalByName(userName);
				if (geUserPersonal == null) {
					writer.write("null");
					return NONE;
				}
				if (geUserPersonal.isActive()) {
					if (geUserPersonal.isLockUserAccount()) {
						//����ϵͳ����ʱ��ĳЩ�ʺŴ��ڱ�������״̬�������ʺű�����ʱ������жϣ��������Сʱ֮���ʺŻ����ڱ�������״̬������Ҫ���½��н�������
						if (DateUtils.addHours(geUserPersonal.getLockTime(), 2).after(new Date())) {
							final String userID = geUserPersonal.getUserID();
							taskExecutor.execute(new Runnable() {
								@Override
								public void run() {
									try {
										//Thread.sleep(1000 * 5);//5����֮������ʺ�
										Thread.sleep(1000 * 60 * 60 * 2);//2Сʱ֮������ʺ�
										geUserPersonalService.unlockUserAccount(userID);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
							});
						}
						writer.write("lock");
						return NONE;
					}
					//У�������Ƿ���ȷ���������ȷ�����޸�����������
					if (!new Md5().toMD5(password).equals(geUserPersonal.getPwd())) {
						geUserPersonalService.updateLoginFailedCount(geUserPersonal);
						writer.write("pwdError");
						return NONE;
					}
				}
			}
			
			GeUserPersonal user = geUserPersonalService.checkLogin(userName, password);
			if (StringUtils.isNotBlank(loginType) && user != null) {
				//�����֤
				boolean bindPolicy = geUserPersonalService.checkBindPolicy(user.getUserID());
				if (!bindPolicy) {
					bindPolicy = geUserPersonalService.findPolicyByOrder(user.getUserID());
				}
				if (!bindPolicy) {
					writer.write("bindFalse");
					return NONE;
				}
			}
			//error���û����������
			//success���ɹ�
			//failed��δ����
			//lock:�ʺ�������
			if (user == null) {
				writer.write("error");
			} else if (user.isActive()) {
				writer.write("success");
				List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				authorities.add(new GrantedAuthorityImpl("ROLE_PERSONALUSER"));
				LoginUserInfo loginUserInfo =  new LoginUserInfo(user.getUserAccount(), "123456", user, true, true, true, true, authorities);
				PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(loginUserInfo, loginUserInfo.getPassword(),loginUserInfo.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);
				System.out.println(SpringSecurityUtils.getCurrentUser());
			} else {
				writer.write("failed");
			}
			writer.close();
		} catch (IOException e) {
			log.error("user login check login status failed." + e.getMessage());
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
		
		return NONE;
	}
	
	/**
	 * ͨ�����֤�Ž��е�¼��У���߼�
	 */
	public String idNumLogin() {
		PrintWriter out = null;
		try {
			out = getResponse().getWriter();
			String userAccount = getRequest().getParameter("userAccount");
			if (StringUtils.isBlank(userAccount)) {
				out.write("accountNull");
				return NONE;
			}
			
			List<GeUserPersonal> userPersonals = geUserPersonalService.getUserPersonalByIdNum(userAccount, "");
			if (userPersonals.isEmpty()) {
				out.write("customerNull");
				return NONE;
			}
			GeUserPersonal userPersonal = userPersonals.get(0);
			if (userPersonal.isLockUserAccount()) {
				out.write("lock");
				return NONE;
			}
			String pwd = getRequest().getParameter("pwd");
			if (StringUtils.isBlank(pwd)) {
				out.write("pwdNull");
				return NONE;
			}
			if (!new Md5().toMD5(pwd).equals(userPersonal.getPwd())) {
				geUserPersonalService.updateLoginFailedCount(userPersonal);
				out.write("pwdError");
				return NONE;
			}
			
			//�����֤
			boolean bindPolicy = geUserPersonalService.checkBindPolicy(userPersonal.getUserID());
			if (!bindPolicy) {
				bindPolicy = geUserPersonalService.findPolicyByOrder(userPersonal.getUserID());
			}
			if (!bindPolicy) {
				out.write("bindFalse");
				return NONE;
			}
			
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new GrantedAuthorityImpl("ROLE_PERSONALUSER"));
			LoginUserInfo loginUserInfo =  new LoginUserInfo(userPersonal.getUserAccount(), "123456", userPersonal, true, true, true, true, authorities);
			PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(loginUserInfo, loginUserInfo.getPassword(),loginUserInfo.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
			System.out.println(SpringSecurityUtils.getCurrentUser());
			
			out.print("success");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
		
		return NONE;
	}
	
	public void setCustomer(GeUserPersonal customer) {
		this.customer = customer;
	}

	public void setGeUserPersonalService(
			GeUserPersonalService geUserPersonalService) {
		this.geUserPersonalService = geUserPersonalService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public String checkUserName(){
		PrintWriter out = null;
		try {
			out = getResponse().getWriter();
			String userName = getRequest().getParameter("userName");
			if (StringUtils.isBlank(userName)) {
				out.write("paramError");
				return NONE;
			}
			//���֤�ŵ�¼
			if (checkIdNum(userName)) {
				List<GeUserPersonal> userPersonals = geUserPersonalService.getUserPersonalByIdNum(userName, "");
				if (userPersonals.isEmpty()) {
					out.write("idNumError");
					return NONE;
				}
				if (userPersonals.size() > 1) {
					StringBuffer accounts = new StringBuffer("");
					for (GeUserPersonal geUserPersonal : userPersonals) {
						accounts.append(geUserPersonal.getUserAccount()).append("%%%");
					}
					if (StringUtils.isNotBlank(accounts.toString())) {
						accounts = new StringBuffer(accounts.substring(0, accounts.length() - 3));
					}
					out.write(accounts.toString());
					return NONE;
				} else {
					GeUserPersonal geUserPersonal = userPersonals.get(0);
					//�ʺ��ѱ�����
					if (geUserPersonal.isLockUserAccount()) {
						out.write("accountLock");
						return NONE;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
		return NONE;
	}
	
	/**
	 * ��¼��ҳ�ύ��֮ǰ��֤�Ƿ������֤�ŵ�¼
	 * @return
	 */
	public String checkSubmitLoginForm () {
		PrintWriter out = null;
		try {
			out = getResponse().getWriter();
			String userName = getRequest().getParameter("userName");
			String password = getRequest().getParameter("password");
			if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
				out.write("paramError");
				return NONE;
			}
			//���֤�ŵ�¼
			if (checkIdNum(userName)) {
				List<GeUserPersonal> userPersonals = geUserPersonalService.getUserPersonalByIdNum(userName, "");
				//List<GeUserPersonal> userPersonalTwo = geUserPersonalService.getUserPersonalByIdNum(userName, "");
				if (userPersonals.isEmpty()) {
					out.write("idNumError");
					return NONE;
				}
				if (userPersonals.size() > 1) {
					StringBuffer accounts = new StringBuffer("");
					for (GeUserPersonal geUserPersonal : userPersonals) {
						accounts.append(geUserPersonal.getUserAccount()).append("%%%");
					}
					if (StringUtils.isNotBlank(accounts.toString())) {
						accounts = new StringBuffer(accounts.substring(0, accounts.length() - 3));
					}
					out.write(accounts.toString());
					return NONE;
				} else {
					GeUserPersonal geUserPersonal = userPersonals.get(0);
					//�ʺ��ѱ�����
					if (geUserPersonal.isLockUserAccount()) {
						out.write("accountLock");
						return NONE;
					}
					//�������
					/*if (!new Md5().toMD5(password).equals(geUserPersonal.getPwd())) {
						geUserPersonalService.updateLoginFailedCount(geUserPersonal);
						out.write("pwdError");
						return NONE;
					}*/
					//�����֤
					boolean bindPolicy = geUserPersonalService.checkBindPolicy(geUserPersonal.getUserID());
					if (!bindPolicy) {
						bindPolicy = geUserPersonalService.findPolicyByOrder(geUserPersonal.getUserID());
					}
					if (!bindPolicy) {
						out.write("bindFalse");
						return NONE;
					}
					out.write("idNumLogin");
					return NONE;
				}
			} else {
				out.write("accountLogin");
				return NONE;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
		return NONE;
	}

	
	/**
	 * У���û�¼����Ƿ��ǺϷ������֤��
	 * @param idNumber
	 * @return
	 */
	public boolean checkIdNum(String idNumber) {
		String pattern = "((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65|71|81|82|91)\\d{4})((((19|20)(([02468][048])|([13579][26]))0229))|((20[0-9][0-9])|(19[0-9][0-9]))((((0[1-9])|(1[0-2]))((0[1-9])|(1\\d)|(2[0-8])))|((((0[1,3-9])|(1[0-2]))(29|30))|(((0[13578])|(1[02]))31))))((\\d{3}(x|X))|(\\d{4}))";  
		Pattern p = Pattern.compile(pattern);  
		Matcher m = p.matcher(idNumber);  
		return m.matches(); 
	}

	public String dateFormat(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}
}
