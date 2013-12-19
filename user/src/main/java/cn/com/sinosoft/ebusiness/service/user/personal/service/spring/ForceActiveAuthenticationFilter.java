package cn.com.sinosoft.ebusiness.service.user.personal.service.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPersonal;
import cn.com.sinosoft.ebusiness.service.user.personal.service.spring.LoginUserUnActiveException;
import cn.com.sinosoft.ebusiness.service.user.personal.service.spring.PersonalUser;

//public class EnterpriseAuthenticationFilter  extends UsernamePasswordAuthenticationFilter{
public class ForceActiveAuthenticationFilter  extends AbstractAuthenticationProcessingFilter{

	private UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter ;
	
	private final static  Logger logger = LoggerFactory.getLogger(ForceActiveAuthenticationFilter.class);

	public ForceActiveAuthenticationFilter() {
		 super("/j_spring_security_check_force");
		 usernamePasswordAuthenticationFilter = new UsernamePasswordAuthenticationFilter();
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
	
		Authentication authentication = usernamePasswordAuthenticationFilter.attemptAuthentication(request, response);
		PersonalUser personalUser = (PersonalUser)authentication.getPrincipal();
		String password = (String)authentication.getCredentials();
		GeUserPersonal geUserPersonal = personalUser.getGeUserPersonal();
		geUserPersonal.setPwd(password);
		/**校验用户是否激活*/	
		if("2".equals(personalUser.getGeUserPersonal().getStatus())){
			//geUserPersonal.setPwd(geUserPersonal.getp)
			throw new LoginUserUnActiveException("unActive",geUserPersonal);
		}else if("0".equals(personalUser.getGeUserPersonal().getStatus())){
			// 无效
			throw new LoginUserUnActiveException("cancel",geUserPersonal);
		}
		
		return authentication;
	}
	
	@Override
	public void setAuthenticationManager(AuthenticationManager  authenticationManager ){
		super.setAuthenticationManager(authenticationManager);
		System.out.println("-------------------set auth----------");
		usernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManager);
	}
	
    
   
}
