package cn.com.sinosoft.ebusiness.sys.permission.service.spring;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import cn.com.sinosoft.ebusiness.sys.permission.domain.GeOperator;

public class MisUser extends User {

	private GeOperator geOperator;
	
	public MisUser(String username, String password, GeOperator geOperator,
			boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, 
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
		this.geOperator = geOperator;
	}

	public GeOperator getGeOperator() {
		return geOperator;
	}

	public void setGeOperator(GeOperator geOperator) {
		this.geOperator = geOperator;
	}

	

}
