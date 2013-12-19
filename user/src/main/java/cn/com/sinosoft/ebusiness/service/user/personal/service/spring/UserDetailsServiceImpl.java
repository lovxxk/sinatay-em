package cn.com.sinosoft.ebusiness.service.user.personal.service.spring;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPersonal;
import cn.com.sinosoft.ebusiness.service.user.personal.service.facade.GeUserPersonalService;
import cn.com.sinosoft.ebusiness.sys.moduleException.businessException.UserServiceException;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeUserAuthorityService;

import com.google.common.collect.Sets;

/**
 * 实现SpringSecurity的UserDetailsService接口,实现获取用户Detail信息的回调函数.
 *  
 * @since 2011-09-21
 */
@Transactional(readOnly = false)
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private GeUserPersonalService geUserPersonalService;
	
	@Autowired
	private GeUserAuthorityService geUserAuthorityService;

	/**
	 * 获取用户Details信息的回调函数.
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		/**判断登录方式**/
		String tailString = username.substring(username.length()-2);
		if(!"^1".equals(tailString)){
			return null;
		}else{
			username = username.substring(0, username.length()-2);
		}
		username = username.trim();
		GeUserPersonal geUserPersonal = new GeUserPersonal();
		geUserPersonal.setUserAccount(null);
		
		
		String loginType; //1:登录名 2：邮箱 3:身份证 4:鹤卡
		if(username.contains("@")){/**判断用户输入的是用户名/邮箱/身份证号/鹤卡号中的哪一种，其他为null*/
			geUserPersonal.setEmail(username);/**可能是邮箱或用户名，设置到邮箱中*/
			loginType = "2";
		}else if(username.matches("^\\d+[xX]?$")){
			if(username.startsWith("95519")){
				geUserPersonal.setPiCardNo(username);
				loginType = "4";
			}else{
				geUserPersonal.setIdentifyType("01");
				geUserPersonal.setIdentifyNumber(username);
				loginType = "3";
			}
		}else{
			geUserPersonal.setUserAccount(username);
			loginType = "1";
		}
		
		try {
			geUserPersonal = geUserPersonalService.getUserPersonalForLoginWithLDAP(geUserPersonal);
		} catch (UserServiceException e) {
			throw new LoginUserUnActiveException("exception",e.showMessage());
		}
		
		if (geUserPersonal == null) {
			throw new UsernameNotFoundException("用户名错误","");
		}

		Set<GrantedAuthority> grantedAuths = obtainGrantedAuthorities(geUserPersonal);

		//-- mini-web示例中无以下属性, 暂时全部设为true. --//
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		PersonalUser pu =  new cn.com.sinosoft.ebusiness.service.user.personal.service.spring.PersonalUser(username, geUserPersonal.getPwd(), 
				geUserPersonal, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuths);  
		
		pu.setLoginType(loginType);// 设置登陆类型
		
		UserDetails userdetails = pu;
		return userdetails;
	}

	/**
	 * 获得用户所有角色的权限集合.
	 */
	private Set<GrantedAuthority> obtainGrantedAuthorities(GeUserPersonal geUserPersonal) {
		Set<GrantedAuthority> authSet = Sets.newHashSet();
		
		List list = geUserAuthorityService.findLevelAuthoritys(geUserPersonal.getUserLevel());
		if(list != null){
			for(int i = 0; i < list.size(); i++){
				authSet.add(new GrantedAuthorityImpl((String)list.get(i)));
			}
		}
		authSet.add(new GrantedAuthorityImpl("ROLE_PERSONALUSER"));
		return authSet;
	}
	
	public void get(){
		 Collection<GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		 for(GrantedAuthority authority :authorities){
			// authority.g
		 }
	}

	
}
