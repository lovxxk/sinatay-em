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
 * 实现SpringSecurity的UserDetailsService接口,实现获取用户Detail信息的回调函数.
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
	 * 获取用户Details信息的回调函数.
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		GeOperator geOperator = geOperatorService.findOperatorByPK(username);
		if(geOperator == null){
			throw new UsernameNotFoundException("","用户名错误");
		}
		
		if("02".equals(geOperator.getState())){
			throw new LoginUserException("该用户状态为不可用，登录失败","该用户状态为不可用，登录失败");
		}

		Set<GrantedAuthority> grantedAuths = obtainGrantedAuthorities(geOperator);

		//-- mini-web示例中无以下属性, 暂时全部设为true. --//
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		UserDetails userdetails = new cn.com.sinosoft.ebusiness.sys.permission.service.spring.MisUser(username, geOperator.getPwd(), 
				geOperator, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuths);
		
		
		return userdetails;
	}

	/**
	 * 获得用户所有角色的权限集合.
	 */
	private Set<GrantedAuthority> obtainGrantedAuthorities(GeOperator geOperator) {
		Set<GrantedAuthority> authSet = Sets.newHashSet();
		List list = new ArrayList();
		if("1".equals(geOperator.getFlag())){/**用于超级大权限*/
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
