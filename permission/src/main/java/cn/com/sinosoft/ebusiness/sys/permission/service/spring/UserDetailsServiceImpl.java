package cn.com.sinosoft.ebusiness.sys.permission.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeOperator;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeDeptGroupRoleService;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeOperatorService;

import com.google.common.collect.Sets;

/**
 * ʵ��SpringSecurity��UserDetailsService�ӿ�,ʵ�ֻ�ȡ�û�Detail��Ϣ�Ļص�����.
 *  
 * @since 2011-09-21
 */
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {
	private GeOperatorService geOperatorService;
	private GeDeptGroupRoleService geDeptGroupRoleService;

	@Autowired
	public void setGeOperatorService(GeOperatorService geOperatorService) {
		this.geOperatorService = geOperatorService;
	}

	@Autowired
	public void setGeDeptGroupRoleService(
			GeDeptGroupRoleService geDeptGroupRoleService) {
		this.geDeptGroupRoleService = geDeptGroupRoleService;
	}



	/**
	 * ��ȡ�û�Details��Ϣ�Ļص�����.
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		GeOperator geOperator = geOperatorService.findOperatorByPK(username);
		if(geOperator == null){
			throw new UsernameNotFoundException("","�û�������");
		}
		
		if("02".equals(geOperator.getState())){
			throw new LoginUserException("���û�״̬Ϊ�����ã���¼ʧ��","���û�״̬Ϊ�����ã���¼ʧ��");
		}

		Set<GrantedAuthority> grantedAuths = obtainGrantedAuthorities(geOperator);

		//-- mini-webʾ��������������, ��ʱȫ����Ϊtrue. --//
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		UserDetails userdetails = new cn.com.sinosoft.ebusiness.sys.permission.service.spring.MisUser(username, geOperator.getPwd(), 
				geOperator, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuths);
		
		
		return userdetails;
	}

	/**
	 * ����û����н�ɫ��Ȩ�޼���.
	 */
	private Set<GrantedAuthority> obtainGrantedAuthorities(GeOperator geOperator) {
		Set<GrantedAuthority> authSet = Sets.newHashSet();
		List list = new ArrayList();
		if("1".equals(geOperator.getFlag())){/**���ڳ�����Ȩ��*/
			list = geDeptGroupRoleService.findAllAuthorityIds();
		}else{
			list = geDeptGroupRoleService.findAuthoritysByOId(geOperator.getOperatorid());
		}
		if(list != null){
			for(int i = 0; i < list.size(); i++){
				authSet.add(new GrantedAuthorityImpl((String)list.get(i)));
			}
		}
		authSet.add(new GrantedAuthorityImpl("ROLE_PERSONALUSER"));
		return authSet;
	}
}
