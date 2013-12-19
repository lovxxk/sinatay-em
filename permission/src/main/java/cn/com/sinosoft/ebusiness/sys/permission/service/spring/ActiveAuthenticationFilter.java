package cn.com.sinosoft.ebusiness.sys.permission.service.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cn.com.sinosoft.ebusiness.sys.permission.domain.GeOperator;

public class ActiveAuthenticationFilter  extends UsernamePasswordAuthenticationFilter{
	
	private final static  Logger logger = LoggerFactory.getLogger(ActiveAuthenticationFilter.class);

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		Authentication authentication = super.attemptAuthentication(request, response);
		MisUser misUser = (MisUser)authentication.getPrincipal();
		String password = (String)authentication.getCredentials();
		GeOperator geOperator = misUser.getGeOperator();
		return authentication;
	}
	
    
   
}
