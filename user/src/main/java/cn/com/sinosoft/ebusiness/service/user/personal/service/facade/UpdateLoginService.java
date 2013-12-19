package cn.com.sinosoft.ebusiness.service.user.personal.service.facade;


public interface UpdateLoginService {

	boolean updateLoginFailedCount(String userId, Integer loginFailedCount);

	Integer getUserLoginFailedCount(String userAccName);

}
