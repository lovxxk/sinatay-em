package cn.com.sinosoft.ebusiness.service.user.common.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPersonal;

public class LoginUserInfo extends User {
	
	public GeUserPersonal customer;

	private static final long serialVersionUID = 1L;
	
	public LoginUserInfo(String username, String password,GeUserPersonal customer, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
		
		this.customer = customer;
	}

	public GeUserPersonal getCustomer() {
		return customer;
	}

	public void setCustomer(GeUserPersonal customer) {
		this.customer = customer;
	}
	
}
