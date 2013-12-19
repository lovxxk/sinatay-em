package cn.com.sinosoft.ebusiness.init.listener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import cn.com.sinosoft.ebusiness.service.user.common.domain.LoginUserInfo;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPersonal;
import cn.com.sinosoft.ebusiness.service.user.personal.enums.PersonalLoginType;
import cn.com.sinosoft.ebusiness.service.user.personal.service.facade.GeUserPersonalService;
import cn.com.sinosoft.util.cookie.CookieManager;
import cn.com.sinosoft.util.cookie.EncryptionUtil;
import cn.com.sinosoft.util.security.springSecurity.SpringSecurityUtils;

public class AutoLoginFilter implements Filter {
	public void destroy() {
	}
	
	public GeUserPersonalService geUserPersonalService;

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		
		LoginUserInfo loginUser = SpringSecurityUtils.getCurrentUser();
		GeUserPersonal user = (GeUserPersonal) request.getSession().getAttribute("geUserPersonal");
		if (loginUser != null || user != null) {
			chain.doFilter(request, response);
		} else {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				String cookieValue = CookieManager.getValue("autoLoginUser", cookies);
				if (StringUtils.isNotBlank(cookieValue)) {
					if (SpringSecurityUtils.getCurrentUser() == null) {
						String userAccount = EncryptionUtil.decrypt(cookieValue);
						if (userAccount != null) {
							if (geUserPersonalService == null)
								geUserPersonalService = getGeUserPersonalService();
							GeUserPersonal userPersonal = geUserPersonalService.getUserPersonalByUserAccount(userAccount);
							//可以自动登录
							if (userPersonal != null) {
								getAuthorities(userPersonal, userAccount);
								request.getSession().setAttribute("geUserPersonal", userPersonal);
								request.getSession().setAttribute("personalLoginType", PersonalLoginType.userLogin);
							}
						}
					}
				}
			}
			chain.doFilter(request, response);
		}
	}
	
	private void getAuthorities(GeUserPersonal userPersonal, String userAccount) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new GrantedAuthorityImpl("ROLE_PERSONALUSER"));
		LoginUserInfo loginUserInfo =  new LoginUserInfo(userAccount, "123456", userPersonal, true, 
				 true, true, true, authorities);
		PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(loginUserInfo, loginUserInfo.getPassword(),loginUserInfo.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		System.out.println(SpringSecurityUtils.getCurrentUser());
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
	public GeUserPersonalService getGeUserPersonalService() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load(new String[]{
				"classpath:/spring/applicationContext.xml",
				"classpath:/spring/applicationContext-user.xml",
				"classpath:/spring/applicationContext-bizConfig.xml",
				"classpath:/spring/applicationContext-dataAccess.xml",
				"classpath:/spring/applicationContext-hibernate.xml"});
		ctx.refresh();
		GeUserPersonalService userPersonalService = ctx.getBean("geUserPersonalService", GeUserPersonalService.class);
		
		return userPersonalService;
	}

	public void setGeUserPersonalService(GeUserPersonalService geUserPersonalService) {
		this.geUserPersonalService = geUserPersonalService;
	}
	
}