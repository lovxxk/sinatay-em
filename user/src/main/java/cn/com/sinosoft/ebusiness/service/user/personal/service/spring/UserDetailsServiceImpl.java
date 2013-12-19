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
 * ʵ��SpringSecurity��UserDetailsService�ӿ�,ʵ�ֻ�ȡ�û�Detail��Ϣ�Ļص�����.
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
	 * ��ȡ�û�Details��Ϣ�Ļص�����.
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		/**�жϵ�¼��ʽ**/
		String tailString = username.substring(username.length()-2);
		if(!"^1".equals(tailString)){
			return null;
		}else{
			username = username.substring(0, username.length()-2);
		}
		username = username.trim();
		GeUserPersonal geUserPersonal = new GeUserPersonal();
		geUserPersonal.setUserAccount(null);
		
		
		String loginType; //1:��¼�� 2������ 3:���֤ 4:�׿�
		if(username.contains("@")){/**�ж��û���������û���/����/���֤��/�׿����е���һ�֣�����Ϊnull*/
			geUserPersonal.setEmail(username);/**������������û��������õ�������*/
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
			throw new UsernameNotFoundException("�û�������","");
		}

		Set<GrantedAuthority> grantedAuths = obtainGrantedAuthorities(geUserPersonal);

		//-- mini-webʾ��������������, ��ʱȫ����Ϊtrue. --//
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		PersonalUser pu =  new cn.com.sinosoft.ebusiness.service.user.personal.service.spring.PersonalUser(username, geUserPersonal.getPwd(), 
				geUserPersonal, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuths);  
		
		pu.setLoginType(loginType);// ���õ�½����
		
		UserDetails userdetails = pu;
		return userdetails;
	}

	/**
	 * ����û����н�ɫ��Ȩ�޼���.
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
