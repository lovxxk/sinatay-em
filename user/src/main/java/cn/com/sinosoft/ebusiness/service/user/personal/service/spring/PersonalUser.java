package cn.com.sinosoft.ebusiness.service.user.personal.service.spring;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPersonal;

public class PersonalUser extends User {
	private static final long serialVersionUID = 1L;

	private GeUserPersonal geUserPersonal;
	
	private String loginType; //1:登录名 2：邮箱 3:身份证 4:鹤卡
	
	public PersonalUser(String username, String password, GeUserPersonal geUserPersonal,
			boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, 
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
		this.geUserPersonal = geUserPersonal;
	}

	public GeUserPersonal getGeUserPersonal() {
		return geUserPersonal;
	}

	public void setGeUserPersonal(GeUserPersonal geUserPersonal) {
		this.geUserPersonal = geUserPersonal;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	
}
