package cn.com.sinosoft.ebusiness.service.user.personal.service.spring;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import cn.com.sinosoft.ebusiness.service.user.personal.service.facade.UpdateLoginService;

public class UpdateLoginServiceSpringImpl implements UpdateLoginService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	
	public static UpdateLoginService service;
	
	/**
	 * 解锁帐号
	 * @param userAccount
	 */
	public void unlockUserAccount(String userAccount) {
		StringBuffer sql = new StringBuffer("update ge_user_personal set lockUserAccount = 'N' ,locktime = '', loginFailedCount = 0 where ");
		
		if (userAccount.indexOf("@") > 0) {
			sql.append(" email = ?");
		} else {
			sql.append(" mobilePhone = ?");
		}
		jdbcTemplate.update(sql.toString(), userAccount);
	}

	/**
	 * 修改登录失败错误次数与帐号锁定时间
	 */
	@Override
	public boolean updateLoginFailedCount(String userAccount, Integer loginFailedCount) {
		try {
			StringBuffer sql = new StringBuffer("update ge_user_personal set loginFailedCount = "
					+ (loginFailedCount + 1));
			
			if (loginFailedCount == 2) {
				sql.append(" , lockUserAccount = 'Y' ")
				   .append(" , lockTime = to_date('" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "', 'yyyy-MM-dd HH24:mi:ss')");
				final String userID = userAccount;
				taskExecutor.execute(new Runnable() {
					@Override
					public void run() {
						 try {
//							Thread.sleep(1000 * 5);//5秒总之后解锁帐号
							Thread.sleep(1000 * 60 * 60 * 2);//2小时之后解锁帐号
							unlockUserAccount(userID);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				});
			}
			if (userAccount.indexOf("@") > 0) {
				sql.append(" where email = ?");
			} else {
				sql.append(" where mobilePhone = ?");
			}
			
			jdbcTemplate.update(sql.toString(), userAccount);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	public static void updateUserLoginFailedCount(String userAccName) {
		if (StringUtils.isBlank(userAccName))
			return;
		
		userAccName = userAccName.replaceAll("&#64;", "@").replaceAll("&#95;", "_").replaceAll("&#46;", ".");
		
		if (service == null) {
			GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
			ctx.load(new String[]{
					"classpath:/spring/applicationContext.xml",
					"classpath:/spring/applicationContext-updateLogin.xml",
					"classpath:/spring/applicationContext-dataAccess.xml",
			"classpath:/spring/applicationContext-hibernate.xml"});
			ctx.refresh();	
			service = ctx.getBean("updateLoginService", UpdateLoginService.class);
		}
		
		Integer count = service.getUserLoginFailedCount(userAccName);
		
		service.updateLoginFailedCount(userAccName, count);
	}

	@Override
	public Integer getUserLoginFailedCount(String userAccount) {
		StringBuffer sql = new StringBuffer("select loginFailedCount from ge_user_personal where ");
		
		if (userAccount.indexOf("@") > 0) {
			sql.append(" email = '" + userAccount + "'");
		} else {
			sql.append(" mobilePhone = '" + userAccount + "'");
		}
		
		Integer count = this.jdbcTemplate.queryForInt(sql.toString());
		
		return count;
	}
}
