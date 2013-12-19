package cn.com.sinosoft.ebusiness.service.user.common.service.spring;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import cn.com.sinosoft.ebusiness.service.user.common.domain.LoginUserInfo;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPersonal;
import cn.com.sinosoft.ebusiness.service.user.personal.service.facade.GeUserPersonalService;
import cn.com.sinosoft.util.time.DateUtils;

import com.google.common.collect.Sets;

public class LoginUserInfoService implements UserDetailsService {

	private static Logger log = LoggerFactory.getLogger(LoginUserInfoService.class);
	
	@Autowired
	public GeUserPersonalService geUserPersonalService;
	
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		log.error("��ʼУ���û����Ƿ����:" + dateFormat(new Date()));
		GeUserPersonal geUserPersonal = geUserPersonalService.getUserPersonalByName(username);
		log.error("У���û����Ƿ���ڽ���:" + dateFormat(new Date()));
		if (geUserPersonal == null) {
			throw new BadCredentialsException("�û���������", "�û���������");
		}
		if (geUserPersonal.isLockUserAccount()) {
			//����ϵͳ����ʱ��ĳЩ�ʺŴ��ڱ�������״̬�������ʺű�����ʱ������жϣ��������Сʱ֮���ʺŻ����ڱ�������״̬������Ҫ���½��н�������
			if (DateUtils.addHours(geUserPersonal.getLockTime(), 2).after(new Date())) {
				final String userID = geUserPersonal.getUserID();
				taskExecutor.execute(new Runnable() {
					@Override
					public void run() {
						 try {
//							Thread.sleep(1000 * 5);//5����֮������ʺ�
							Thread.sleep(1000 * 60 * 60 * 2);//2Сʱ֮������ʺ�
							geUserPersonalService.unlockUserAccount(userID);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				});
				throw new BadCredentialsException("������������ѳ���3�Σ����˻��ѱ�������������2Сʱ���ٵ�½", "������������ѳ���3�Σ����˻��ѱ�������������2Сʱ���ٵ�½");
			}
		}
		if (!geUserPersonal.isActive()) {
			String message = "�ʺ�δ���<a href='../../../register/sendEmailToActive.do?userAccount="
					+ username + "'>��������</a>";
			throw new BadCredentialsException(message, message);
		}
		Set<GrantedAuthority> grantedAuths = obtainGrantedAuthorities();
		
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		LoginUserInfo loginUserInfo =  new LoginUserInfo(geUserPersonal.getUserAccount(), geUserPersonal.getPwd(), geUserPersonal,
				enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuths);  
		
		UserDetails userdetails = loginUserInfo;
		
		return userdetails;
	}
	
	/**
	 * У���û�¼����Ƿ��ǺϷ������֤��
	 * @param idNumber
	 * @return
	 */
	public boolean checkIdNum(String idNumber) {
		String pattern = "((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65|71|81|82|91)\\d{4})((((19|20)(([02468][048])|([13579][26]))0229))|((20[0-9][0-9])|(19[0-9][0-9]))((((0[1-9])|(1[0-2]))((0[1-9])|(1\\d)|(2[0-8])))|((((0[1,3-9])|(1[0-2]))(29|30))|(((0[13578])|(1[02]))31))))((\\d{3}(x|X))|(\\d{4}))";  
		Pattern p = Pattern.compile(pattern);  
		Matcher m = p.matcher(idNumber);  
		return m.matches(); 
	}

	/**
	 * ����û����н�ɫ��Ȩ�޼���.
	 */
	private Set<GrantedAuthority> obtainGrantedAuthorities() {
		Set<GrantedAuthority> authSet = Sets.newHashSet();
		List list = new ArrayList();
//		if("1".equals(geOperator.getFlag())){/**���ڳ�����Ȩ��*/
//			list = geDeptGroupRoleService.findAllAuthorityIds();
//		}else{
//			list = geDeptGroupRoleService.findAuthoritysByOId(geOperator.getOperatorid());
//		}
//		if(list != null){
//			for(int i = 0; i < list.size(); i++){
//				authSet.add(new GrantedAuthorityImpl((String)list.get(i)));
//			}
//		}
		authSet.add(new GrantedAuthorityImpl("ROLE_PERSONALUSER"));
		return authSet;
	}
	
	public String dateFormat(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}
}
