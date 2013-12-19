package cn.com.sinosoft.ebusiness.service.user.personal.service.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cn.com.sinosoft.ebusiness.service.user.common.domain.LoginUserInfo;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPersonal;

public class ActiveAuthenticationFilter  extends UsernamePasswordAuthenticationFilter{
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		
		Authentication authentication = super.attemptAuthentication(request, response);
		LoginUserInfo loginUserInfo = (LoginUserInfo)authentication.getPrincipal();
		String password = (String)authentication.getCredentials();
		GeUserPersonal userPersonal = loginUserInfo.getCustomer();
		userPersonal.setPwd(password);
		
		return authentication;
	}
	
    
  
}
