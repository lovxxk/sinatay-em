package cn.com.sinosoft.ebusiness.service.user.personal.service.spring;

import ins.framework.common.QueryRule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import cn.com.sinosoft.ebusiness.service.user.common.domain.LoginUserInfo;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.AutoUserDto;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPersonal;
import cn.com.sinosoft.ebusiness.service.user.personal.service.facade.GeUserPersonalService;
import cn.com.sinosoft.ebusiness.service.user.personal.service.facade.UserService;
import cn.com.sinosoft.util.encode.Md5;
import cn.com.sinosoft.util.encode.PasswordGen;

public class UserServiceImpl implements UserService {
	
	@Autowired
	public GeUserPersonalService gups;
	
	@Override
	public AutoUserDto autoRegisterAndLogin(AutoUserDto au,HttpSession session) throws Exception {
		
		//是否需要自动注册标志。1表示不存在手机或者邮箱同名的账户名，0 表示已存在邮箱相同的账户名，-1表示存在手机相同的用户名。
		
		if(session != null && session.getAttribute("geUserPersonal") != null){
			return au;
		}
		GeUserPersonal gupFound = null;
		QueryRule qr = QueryRule.getInstance();
		qr.addEqual("userAccount",au.getMobilePhone());
		gupFound = gups.findUser(qr);
		if(gupFound == null){
			qr = QueryRule.getInstance();
			qr.addEqual("userAccount",au.getEmail());
			gupFound = gups.findUsers(qr);
			if(gupFound != null){
				au.setRegFlag("-1");
				return au;
			}
		}else{
			au.setRegFlag("-1");
			return au;
		}
		
		GeUserPersonal gup = new GeUserPersonal();
		gup.setUserName(au.getFullName());
		gup.setSex(au.getGender()==null?"0":au.getGender().toString());
		gup.setBirthday(au.getBirthDate());
		gup.setIdentifyType(au.getIdType().toString());
		gup.setIdentifyNumber(au.getIdNumber());
		String pwd = PasswordGen.genSimplePwd();
		gup.setPwd(new Md5().toMD5(pwd));
		gup.setPasswordGrade(gups.checkPasswordGrade(pwd));
		gup.setCurrentLogintime(new Date());
		gup.setLastLoginTime(gup.getCurrentLogintime());
		gup.setEmail(au.getEmail());
		gup.setMobilePhone(au.getMobilePhone());
		gup.setUserAccount(gup.getMobilePhone());
		gup.setActive(true);
		gups.save(gup);
		
		setCurrentUser(session, gup);
		au.setPlaintextPassword(pwd);
		au.setRegFlag("1");
		return au;
	}

	@Override
	public void setCurrentUser(HttpSession session,GeUserPersonal gup) throws Exception {
		session.setAttribute("geUserPersonal", gup);
		session.setAttribute("personalLoginType","userLogin");
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new GrantedAuthorityImpl("ROLE_PERSONALUSER"));
		UserDetails userDetails = new LoginUserInfo(gup.getUserAccount(),gup.getPwd(),gup, true,true, true, true, authorities);
		Authentication authentication = new UsernamePasswordAuthenticationToken(
				userDetails, userDetails.getPassword(),
				userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(
				authentication);
		session.setAttribute(
				"SPRING_SECURITY_CONTEXT",
				SecurityContextHolder.getContext());
	}
	
}
