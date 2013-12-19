package cn.com.sinosoft.ebusiness.service.user.personal.service.facade;

import javax.servlet.http.HttpSession;

import cn.com.sinosoft.ebusiness.service.user.personal.domain.AutoUserDto;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPersonal;
public interface UserService {
	
	
	public void setCurrentUser(HttpSession session,GeUserPersonal gup) throws Exception;
	
	public AutoUserDto autoRegisterAndLogin(AutoUserDto au,HttpSession session) throws Exception;

}
